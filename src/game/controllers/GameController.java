package game.controllers;

import game.models.GameModel;
import game.models.JokerTelephoneModel;
import game.views.GameView;
import game.views.HomeView;
import game.views.JokerPublicView;
import game.views.JokerTelephoneView;
import general_views.Dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import models.dao.JoueurDAO;
import models.dao.QuestionDAO;
import models.dao.ReponseDAO;
import models.metier.Joueur;
import models.metier.Question;
import models.metier.Reponse;


public class GameController implements ActionListener, WindowListener {
	private GameView gameView;
	private GameModel model;
	private JokerPublicView jokerPublicView;
	private JokerTelephoneView jokerTelephoneView;
	private JokerTelephoneModel jokerTelephoneModel;
	private ArrayList<Integer> reponsesEnlevees;

	public GameController(GameView gameView) {
		model = new GameModel();
		this.gameView = gameView;
		jokerPublicView = new JokerPublicView();
		jokerTelephoneView = new JokerTelephoneView();
    }

	public GameModel getModel() {
		return model;
	}

	public void setModel(GameModel model) {
		this.model = model;
	}
	
	public void JokerPublicController(JokerPublicView jokerPublicView) {
		this.jokerPublicView = jokerPublicView;
    }
	
	public void JokerTelephoneController(JokerTelephoneView jokerTelephoneView) {
		jokerTelephoneModel = new JokerTelephoneModel();
		this.jokerTelephoneView = jokerTelephoneView;
    }

	public JokerTelephoneModel getJokerTelephoneModel() {
		return jokerTelephoneModel;
	}
	
	public void setJokerTelephoneModel(JokerTelephoneModel jokerTelephoneModel) {
		this.jokerTelephoneModel = jokerTelephoneModel;
	}
	
	public void createNewGame() {
		// Création du joueur
		Joueur j = new Joueur(0, this.gameView.askPseudo());
		getModel().setJoueur(j);
		j.setId(JoueurDAO.getInstance().save(j));

		getModel().setCurrentLevel(1);
		getModel().setQuestions(QuestionDAO.getInstance().findSixRandomByNiveau(1));

		// Suppression des variables utilisées avec le joker 50/50 lors du passage à une nouvelle question :
		getModel().setIs5050(false);
		reponsesEnlevees = new ArrayList<Integer>();
		if (reponsesEnlevees.size() > 0) {
			for (int r = 0; r < reponsesEnlevees.size(); r++) {
				reponsesEnlevees.remove(r);
			}
		}
		
		gameView.clearAnswerPanels();
		gameView.writeQuestion();
		gameView.getBackgroundSound().loop();
	}

	public int getFinalScore() {
		int somme_gagnee = 0;
		int qNb = model.getQuestionNbPyramid();
		switch (qNb) {
			case 1: somme_gagnee = 0; break;
			case 2: somme_gagnee = 200; break;
			case 3: somme_gagnee = 300; break;
			case 4: somme_gagnee = 500; break;
			case 5: somme_gagnee = 800; break;
			case 6: somme_gagnee = 1500; break;
			case 7: somme_gagnee = 3000; break;
			case 8: somme_gagnee = 6000; break;
			case 9: somme_gagnee = 12000; break;
			case 10: somme_gagnee = 24000; break;
			case 11: somme_gagnee = 48000; break;
			case 12: somme_gagnee = 72000; break;
			case 13: somme_gagnee = 100000; break;
			case 14: somme_gagnee = 150000; break;
			case 15: somme_gagnee = 300000; break;
			default: somme_gagnee = 0; break;
		}
		return somme_gagnee;
	}
	
	public void quitWithSaving() {
		int scoreFin = getFinalScore();
		Dialog.confirmDialog(gameView, "Quitter le jeu", true, "Voulez-vous vraiment quitter avec la somme définitive de " + scoreFin + "€ ?");
		if(Dialog.reponse) {
			JoueurDAO.getInstance().addScore(model.getJoueur().getId(), scoreFin);
			showHomePage();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final String actionCommand = e.getActionCommand();
		
		if(actionCommand.equals("quitWhithSaving")) {
			quitWithSaving();
		}
		
		// On clique sur le joker 50/50
		else if(actionCommand.equals("joker5050")) {
			joker5050();
		}
		
		// On clique sur le joker de vote du public
		else if (actionCommand.equals("jokerPublic")) {
			gameView.useJokerPublic();
			gameView.repaint();
			gameView.validate();
			
			if (model.isIs5050()) {
				reponsesEnlevees = getReponsesEnlevees();
				jokerPublicView = new JokerPublicView(model, reponsesEnlevees);
			}
			else {
				jokerPublicView = new JokerPublicView(model);
			}
		}
		
		// On clique sur le joker de coup de fil à un ami
		else if (actionCommand.equals("jokerCoupDeFil")) {
			if (model.isIs5050()) {
				reponsesEnlevees = getReponsesEnlevees();
				jokerTelephoneView = new JokerTelephoneView(model, reponsesEnlevees, gameView);
			}
			else {
				jokerTelephoneView = new JokerTelephoneView(model, gameView);
			}
		}
		
		// Joker switch
		else if(actionCommand.equals("jokerSwitch")) {
			switchQuestion();
		}
		
		// On clique sur la réponse A
		else if(actionCommand.equals("A") || actionCommand.equals("B") || actionCommand.equals("C") || actionCommand.equals("D")) {

			if(askFinalAnswer()) {
				
				// Fermeture des fenêtres des jokers
				jokerPublicView.getFrame().dispose();
				jokerTelephoneView.dispose();
				
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
				gameView.switchToSelected(actionCommand); // On sélectionne la réponse choisie (en orange)
				gameView.getSelectionSound().play();

	      	  // On démarre une nouveau Thread pour faire patienter un peu (suspens toussa)
	      	  	new Thread(new Runnable() {
	      	  		public void run() {
	      	  			SwingUtilities.invokeLater(new Runnable() {
	      	  				public void run() {
	      	  					try {
	      	  						Thread.sleep(model.SUSPENS);
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
	
	
	public void joker5050() {
		// On récupère dans quelle numéro de case est la réponse juste :
		int indexReponseJuste = 1;
		Question currentQuestion = model.getQuestions().get(model.getQuestionNb() - 1);
		ArrayList<Reponse> currentResponses = currentQuestion.getReponses();
		for (int indexReponse = 0; indexReponse < currentResponses.size(); indexReponse++) {
			Reponse reponse = currentResponses.get(indexReponse);
			if (reponse.isJuste()) {
				indexReponseJuste = indexReponse + 1;
			}
		}
		
		// On enlève 2 réponses fausses
		if (indexReponseJuste == 1) {
			gameView.setRepB("");
			gameView.setRepC("");
			gameView.getBtnRespB().removeActionListener(gameView.getBtnRespB().getActionListeners()[0]);
			gameView.getBtnRespC().removeActionListener(gameView.getBtnRespC().getActionListeners()[0]);
		}
		if (indexReponseJuste == 2) {
			gameView.setRepA("");
			gameView.setRepD("");
			gameView.getBtnRespA().removeActionListener(gameView.getBtnRespA().getActionListeners()[0]);
			gameView.getBtnRespD().removeActionListener(gameView.getBtnRespD().getActionListeners()[0]);
		}
		if (indexReponseJuste == 3) {
			gameView.setRepB("");
			gameView.setRepD("");
			gameView.getBtnRespB().removeActionListener(gameView.getBtnRespB().getActionListeners()[0]);
			gameView.getBtnRespD().removeActionListener(gameView.getBtnRespD().getActionListeners()[0]);
		}
		if (indexReponseJuste == 4) {
			gameView.setRepA("");
			gameView.setRepC("");
			gameView.getBtnRespA().removeActionListener(gameView.getBtnRespA().getActionListeners()[0]);
			gameView.getBtnRespC().removeActionListener(gameView.getBtnRespC().getActionListeners()[0]);
		}
		
		gameView.useJoker5050();
		gameView.repaint();
		gameView.validate();
		//gameView.getJoker5050Sound().play();
	}
	
	
	public ArrayList<Integer> getReponsesEnlevees() {
		ArrayList<Integer> reponsesEnlevees = new ArrayList<Integer>();
		
		JLabel lblA = (JLabel) gameView.getBtnRespA().getComponent(0);
		String respA = lblA.getText().split("</span>")[1].split("</div>")[0].trim();
		if (respA.equals("")) {
			reponsesEnlevees.add(0);
		}
		
		JLabel lblB = (JLabel) gameView.getBtnRespB().getComponent(0);
		String respB = lblB.getText().split("</span>")[1].split("</div>")[0].trim();
		if (respB.equals("")) {
			reponsesEnlevees.add(1);
		}
		
		JLabel lblC = (JLabel) gameView.getBtnRespC().getComponent(0);
		String respC = lblC.getText().split("</span>")[1].split("</div>")[0].trim();
		if (respC.equals("")) {
			reponsesEnlevees.add(2);
		}
		
		JLabel lblD = (JLabel) gameView.getBtnRespD().getComponent(0);
		String respD = lblD.getText().split("</span>")[1].split("</div>")[0].trim();
		if (respD.equals("")) {
			reponsesEnlevees.add(3);
		}
		
		return reponsesEnlevees;
	}
	
	
	private void switchQuestion() {
		// Fermeture des fenêtres des jokers
		jokerPublicView.getFrame().dispose();
		jokerTelephoneView.dispose();
		gameView.switchToGood(getGoodAnswer());
		model.setQuestionNb(model.getQuestionNb() + 1);
		
		new Thread(new Runnable() {
  	  		public void run() {
  	  			SwingUtilities.invokeLater(new Runnable() {
  	  				public void run() {
  	  					try {
  	  						Thread.sleep(2000);
	  	  					gameView.writeQuestion();
	  	  					gameView.changeBtnSwitchToUse();
  	  					} catch (InterruptedException e) {
  	  						e.printStackTrace();
  	  					}
  	  				}
  	  			});
  	  		}}).start();
	}

	private boolean askFinalAnswer() {
		if(model.getQuestionNbPyramid() <= 5 || gameView.showAskFinalAnswer("<p style=\"text-align: center; font-weight: bold; font-size: 13px;\">C'est votre dernier mot ?</p>") == 1)
			return true;
		else
			return false;
	}

	public String getGoodAnswer() {
		ArrayList<Reponse> v = model.getQuestions().get(model.getQuestionNb() - 1).getReponses();
		String good = null;

		if(v.get(0).isJuste())
			good = "A";
		if(v.get(1).isJuste())
			good = "B";
		if(v.get(2).isJuste())
			good = "C";
		if(v.get(3).isJuste())
			good = "D";
		return good;
	}

	private boolean answerCorrection(String questionNum) {
		boolean isJuste = false;
		ArrayList<Reponse> v = model.getQuestions().get(model.getQuestionNb() - 1).getReponses();
			
		if(questionNum.equals("A")) {
			if(v.get(0).isJuste())
				isJuste = true;
		}
		else if(questionNum.equals("B")) {
			if(v.get(1).isJuste())
				isJuste = true;
		}
		else if(questionNum.equals("C")) {
			if(v.get(2).isJuste())
				isJuste = true;
		}
		else {
			if(v.get(3).isJuste())
				isJuste = true;
		}
		gameView.switchToGood(getGoodAnswer());
		return isJuste;
	}
	
	private void forward() {
		gameView.getGoodSound().play();

		new Thread(new Runnable() {
  	  		public void run() {
  	  			SwingUtilities.invokeLater(new Runnable() {
  	  				public void run() {
  	  					try {
  	  						Thread.sleep(model.NEW_QUESTION);

  	  						// Changement de palier
  	  						if(model.getQuestionNbPyramid() % 5 == 0) {
  	  							
	  	  						new Thread(new Runnable() {
	  	  			  	  		public void run() {
	  	  			  	  			SwingUtilities.invokeLater(new Runnable() {
	  	  			  	  				public void run() {
	  	  			  	  					try {

		  	  			  	  					// On a gagné le millions
		  	  			  	  					if(model.getQuestionNbPyramid() == 15) {
		  	  			  	  						JoueurDAO.getInstance().addScore(model.getJoueur().getId(), 1000000);
		  	  			  	  						gameView.getGoodSound().stop();
			  	    	  							gameView.changePyramid(15);
			  	    	  							gameView.validate();
			  	  	  								gameView.getPalier3Sound().play();
		  	  			  	  						Thread.sleep(model.CHANGE_LEVEL3);
		  	  			  	  						Dialog.messageDialog(gameView, "", true, "Félicitation tu gagnes 1 000 000 d'euros !");
		  	  			  	  						showHomePage();
		  	  			  	  					}
		  	  			  	  					else {

				  	  			  	  				if(model.getQuestionNbPyramid() == 5) {
				  	  	  								gameView.getPalier1Sound().play();
			  	  			  	  						Thread.sleep(model.CHANGE_LEVEL1);
				  	  			  	  				}
				  	  			  	  				else if(model.getQuestionNbPyramid() == 10) {
						  	  								gameView.getPalier2Sound().play();
				  	  			  	  						Thread.sleep(model.CHANGE_LEVEL2);
				  	  			  	  				}
		  	  			  	  						
			  	  	  	  							model.setCurrentLevel(model.getCurrentLevel() + 1);
			  	    	  							gameView.clearAnswerPanels();
			  	    	  							model.setQuestionNbPyramid(model.getQuestionNbPyramid() + 1);
			  	    	  							model.setQuestionNb(1);
			  	    	  							model.setQuestions(QuestionDAO.getInstance().findSixRandomByNiveau(model.getCurrentLevel()));
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
	
	private void showHomePage() {
		new HomeView();
		gameView.dispose();
		// Fermeture des fenêtres des jokers
		jokerPublicView.getFrame().dispose();
		jokerTelephoneView.dispose();
	}

	private void lose() {
		// Fermeture des fenêtres des jokers
		jokerPublicView.getFrame().dispose();
		jokerTelephoneView.dispose();
		
		gameView.getBackgroundSound().stop();
		gameView.getWrongSound().play();
		new Thread(new Runnable() {
  	  		public void run() {
  	  			SwingUtilities.invokeLater(new Runnable() {
  	  				public void run() {
  	  					try {
  	  						Thread.sleep(model.WRONG);

  	  						int valueDivise = model.getQuestionNbPyramid() / 5;

  	  						if(valueDivise == 0 || (valueDivise == 1 && model.getQuestionNbPyramid() == 5)) {
  	  							Dialog.messageDialog(gameView, "Perdu !", true, "<p style=\"text-align: center; font-weight: bold; font-size: 13px;\">Vous avez perdu ! Vous repartez les poches vides avec 0€ !</p>");
  	  							JoueurDAO.getInstance().addScore(model.getJoueur().getId(), 0);
  	  						}
  	  							
  	  						else if(valueDivise == 1 || (valueDivise == 2 && model.getQuestionNbPyramid() == 10)) {
  	  							Dialog.messageDialog(gameView, "Perdu !", true, "<p style=\"text-align: center; font-weight: bold; font-size: 13px;\">Vous avez perdu ! Vous repartez tout de même avec 1500€ !</p>");
  	  							JoueurDAO.getInstance().addScore(model.getJoueur().getId(), model.PALIER1);
  	  						}
  	  						else if(valueDivise == 2 || (valueDivise == 3 && model.getQuestionNbPyramid() == 15)) {
  	  							Dialog.messageDialog(gameView, "Perdu !", true, "<p style=\"text-align: center; font-weight: bold; font-size: 13px;\">Vous avez perdu ! Vous repartez tout de même avec 48000€ !</p>");
  	  							JoueurDAO.getInstance().addScore(model.getJoueur().getId(), model.PALIER2);
  	  						}
  	  						showHomePage();
  	  						

  	  					} catch (InterruptedException e) {
  	  						e.printStackTrace();
  	  					}
  	  				}
  	  			});
  	  		}}).start();
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		quitWithSaving();
	}

	@Override
	public void windowActivated(WindowEvent e) { }

	@Override
	public void windowClosed(WindowEvent e) { }

	@Override
	public void windowDeactivated(WindowEvent e) { }

	@Override
	public void windowDeiconified(WindowEvent e) { }

	@Override
	public void windowIconified(WindowEvent e) { }

	@Override
	public void windowOpened(WindowEvent e) { }

}
