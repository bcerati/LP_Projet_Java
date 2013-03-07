package game.controllers;

import game.models.GameModel;
import game.views.GameView;
import general_views.Button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import models.dao.JoueurDAO;
import models.dao.QuestionDAO;
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
		JoueurDAO.getInstance().save(j);

		getModel().setCurrent_level(1);
		getModel().setQuestions(QuestionDAO.getInstance().findSixRandomByNiveau(1));

		gameView.writeQuestion();
		gameView.getBackgroundSound().loop();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final String actionCommand = e.getActionCommand();
		
		if(actionCommand.equals("quitWhitoutSaving")) {
			if(JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter ?", "Voulez-vous vraiment quitter ?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
				System.exit(0);
		}
		
		// On clique sur la réponse A
		else if(actionCommand.equals("A") || actionCommand.equals("B") || actionCommand.equals("C") || actionCommand.equals("D")) {
			
			if(askFinalAnswer()) {
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
		if(gameView.showAskFinalAnswer("C'est votre dernier mot ?") == JOptionPane.YES_OPTION)
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
		gameView.getBackgroundSound().stop();
		gameView.getGoodSound().play();
		new Thread(new Runnable() {
  	  		public void run() {
  	  			SwingUtilities.invokeLater(new Runnable() {
  	  				public void run() {
  	  					try {
  	  						Thread.sleep(1000);
      	  					System.out.println("NEEEEEEEEEEEEEEXT!");
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
      	  					System.out.println("PERDUUUUUUUUUUUUUUUUUUUU!");
  	  					} catch (InterruptedException e) {
  	  						e.printStackTrace();
  	  					}
  	  				}
  	  			});
  	  		}}).start();
	}

}
