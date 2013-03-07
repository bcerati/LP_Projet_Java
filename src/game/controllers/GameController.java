package game.controllers;

import game.models.GameModel;
import game.views.GameView;
import general_views.Button;
import general_views.Dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.SwingUtilities;
import javax.swing.text.View;

import models.dao.JoueurDAO;
import models.dao.QuestionDAO;
import models.dao.ReponseDAO;
import models.metier.Joueur;
import models.metier.Question;
import models.metier.Reponse;


public class GameController implements ActionListener {

	private GameView gameView;
	private GameModel model;

	public GameController(GameView gameView) {
		model = new GameModel();
		this.gameView = gameView;
    }

	public GameModel getModel() {
		return model;
	}


	public void setModel(GameModel model) {
		this.model = model;
	}

	public void createNewGame() {

		// Création du joueur
		Joueur j = new Joueur(0, this.gameView.askPseudo());
		getModel().setJoueur(j);
		j.setId(JoueurDAO.getInstance().save(j));

		getModel().setCurrentLevel(1);
		getModel().setQuestions(QuestionDAO.getInstance().findSixRandomByNiveau(1));

		gameView.clearAnswerPanels();
		gameView.writeQuestion();
		gameView.getBackgroundSound().loop();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final String actionCommand = e.getActionCommand();
		
		if(actionCommand.equals("quitWhitoutSaving")) {
			Dialog.confirmDialog(gameView, "Quitter le jeu", true, "Voulez-vous vraiment quitter ?");
			if(Dialog.reponse)
				System.exit(0);
		}
		
		// On clique sur la réponse A
		else if(actionCommand.equals("A") || actionCommand.equals("B") || actionCommand.equals("C") || actionCommand.equals("D")) {
			
			if(askFinalAnswer()) {
				Question currentQuestion = model.getQuestions().get(model.getQuestionNb() - 1);
				Reponse currentRep = null;
				if(actionCommand.equals("A"))
					currentRep = currentQuestion.getReponses().get(0);
				else if(actionCommand.equals("B"))
					currentRep = currentQuestion.getReponses().get(1);
				else if(actionCommand.equals("C"))
					currentRep = currentQuestion.getReponses().get(2);
				else
					currentRep = currentQuestion.getReponses().get(3);
				
				ReponseDAO.getInstance().putPlayerResponse(model.getJoueur(), currentQuestion, currentRep);
				gameView.switchToSelected(actionCommand); // On sélectionne la réponse choisie (en orrange)

	      	  // On démarre une nouveau Thread pour faire patienter un peu (suspens toussa)
	      	  	new Thread(new Runnable() {
	      	  		public void run() {
	      	  			SwingUtilities.invokeLater(new Runnable() {
	      	  				public void run() {
	      	  					try {
	      	  						Thread.sleep(3000);
	      	  						if(answerCorrection(actionCommand)) {
	      	  							forward();
	      	  						}
	      	  						else {
	      	  							lose();
	      	  						}
	      	  					} catch (InterruptedException e) {
	      	  						e.printStackTrace();
	      	  					}
	      	  				}
	      	  			});
	      	  		}}).start();
      	  }

		}
	}
	
	private boolean askFinalAnswer() {
		if(gameView.showAskFinalAnswer("C'est votre dernier mot ?") == 1)
			return true;
		else
			return false;
	}

	private boolean answerCorrection(String questionNum) {
		boolean isJuste = false;
		ArrayList<Reponse> v = model.getQuestions().get(model.getQuestionNb() - 1).getReponses();
		String good = null;
		int currentSelected = 0;

		if(v.get(0).isJuste())
			good = "A";
		if(v.get(1).isJuste())
			good = "B";
		if(v.get(2).isJuste())
			good = "C";
		if(v.get(3).isJuste())
			good = "D";
			
		if(questionNum.equals("A")) {
			currentSelected = 0;
			if(v.get(0).isJuste())
				isJuste = true;
		}
		else if(questionNum.equals("B")) {
			currentSelected = 1;
			if(v.get(1).isJuste())
				isJuste = true;
		}
		else if(questionNum.equals("C")) {
			currentSelected = 2;
			if(v.get(2).isJuste())
				isJuste = true;
		}
		else {
			currentSelected = 3;
			if(v.get(3).isJuste())
				isJuste = true;
		}
		gameView.switchToGood(good, currentSelected);
		return isJuste;
	}
	
	private void forward() {
		gameView.getGoodSound().play();

		new Thread(new Runnable() {
  	  		public void run() {
  	  			SwingUtilities.invokeLater(new Runnable() {
  	  				public void run() {
  	  					try {
  	  						Thread.sleep(3000);

  	  						// Changement de palier
  	  						if(model.getQuestionNbPyramid() % 5 == 0) {
  	  							
	  	  						new Thread(new Runnable() {
	  	  			  	  		public void run() {
	  	  			  	  			SwingUtilities.invokeLater(new Runnable() {
	  	  			  	  				public void run() {
	  	  			  	  					try {
		  	  			  	  				if(model.getQuestionNbPyramid() == 5) {
		  	  	  								gameView.getPalier1Sound().play();
		  	  	  							}
		  	  			  	  				else if(model.getQuestionNbPyramid() == 10) {
				  	  								gameView.getPalier1Sound().play();
				  	  						}
	  	  			  	  						Thread.sleep(9000);
		  	  	  	  							model.setCurrentLevel(model.getCurrentLevel() + 1);
		  	    	  							gameView.clearAnswerPanels();
		  	    	  							model.setQuestionNbPyramid(model.getQuestionNbPyramid() + 1);
		  	    	  							model.setQuestionNb(1);
		  	    	  							model.setQuestions(QuestionDAO.getInstance().findSixRandomByNiveau(model.getCurrentLevel()));
		  	    	  							gameView.writeQuestion();
		  	    	  							gameView.validate();
		  	    	  							gameView.changePyramid(model.getQuestionNbPyramid());

	  	  			  	  					} catch (InterruptedException e) {
	  	  			  	  						e.printStackTrace();
	  	  			  	  					}
	  	  			  	  				}
	  	  			  	  			});
	  	  			  	  		}}).start();
  	  						}

  	  						// Simple changement de question
  	  						else {
  	  							gameView.clearAnswerPanels();
  	  							model.setQuestionNbPyramid(model.getQuestionNbPyramid() + 1);
  	  							model.setQuestionNb(model.getQuestionNb() + 1);
  	  							gameView.writeQuestion();
  	  							gameView.validate();
  	  							gameView.changePyramid(model.getQuestionNbPyramid());
  	  						}

  	  					} catch (InterruptedException e) {
  	  						e.printStackTrace();
  	  					}
  	  				}
  	  			});
  	  		}}).start();
	}

	private void lose() {
		gameView.getBackgroundSound().stop();
		gameView.getGoodSound().play();
		new Thread(new Runnable() {
  	  		public void run() {
  	  			SwingUtilities.invokeLater(new Runnable() {
  	  				public void run() {
  	  					try {
  	  						Thread.sleep(1000);

  	  						int valueDivise = model.getQuestionNbPyramid() / 5;

  	  						if(valueDivise == 0 || (valueDivise == 1 && model.getQuestionNbPyramid() == 5))
  	  							JoueurDAO.getInstance().addScore(model.getJoueur().getId(), 0);
  	  						else if(valueDivise == 1 || (valueDivise == 2 && model.getQuestionNbPyramid() == 10)) {
  	  							JoueurDAO.getInstance().addScore(model.getJoueur().getId(), model.PALIER1);
  	  						}
  	  						else if(valueDivise == 2 || (valueDivise == 3 && model.getQuestionNbPyramid() == 15)) {
  	  							JoueurDAO.getInstance().addScore(model.getJoueur().getId(), model.PALIER2);
  	  						}
  	  						

  	  					} catch (InterruptedException e) {
  	  						e.printStackTrace();
  	  					}
  	  				}
  	  			});
  	  		}}).start();
	}

}
