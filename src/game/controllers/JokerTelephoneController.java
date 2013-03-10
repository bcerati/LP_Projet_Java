package game.controllers;

import game.models.GameModel;
import game.views.GameView;
import game.views.JokerTelephoneView;
import general_views.Dialog;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import models.metier.Question;


public class JokerTelephoneController implements ActionListener, ItemListener {
	private JokerTelephoneView jokerTelephoneView;
	private GameView gameView;
	private GameModel model;
	private ArrayList<Integer> reponsesEnlevees;

	public JokerTelephoneController() {
		
	}
	
	public JokerTelephoneController(JokerTelephoneView jokerTelephoneView, GameModel model, GameView gameView) {
		this.setModel(model);
		this.jokerTelephoneView = jokerTelephoneView;
		this.gameView = gameView;
		this.reponsesEnlevees = new ArrayList<Integer>();
	}
	
	public JokerTelephoneController(JokerTelephoneView jokerTelephoneView, GameModel model, ArrayList<Integer> reponsesEnlevees, GameView gameView) {
		this.setModel(model);
		this.jokerTelephoneView = jokerTelephoneView;
		this.gameView = gameView;
		this.reponsesEnlevees = reponsesEnlevees;
	}
	
	public GameModel getModel() {
		return model;
	}
	
	public void setModel(GameModel model) {
		this.model = model;
	}
	
	public ArrayList<Integer> getReponsesEnlevees() {
		return reponsesEnlevees;
	}
	
	public void setReponsesEnlevees(ArrayList<Integer> reponsesEnlevees) {
		this.reponsesEnlevees = reponsesEnlevees;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		
		if (actionCommand.equals("appelerAmi")) {
			Dialog.confirmDialog(jokerTelephoneView, "Joker - Coup de fil à un ami - Confirmation", true, "<p style=\"text-align: center; font-weight: bold; font-size: 13px;\">Est-ce bien " + jokerTelephoneView.getComboboxSelectionString() + " que vous souhaitez appeller ?</p>");
			if (Dialog.reponse) {
				// On affiche le joker comme étant utilisé et on ferme la fenêtre du joker
				gameView.useJokerCoupDeFil();
				gameView.repaint();
				gameView.validate();
				jokerTelephoneView.dispose();
				
				int idReponseDonnee = 0;
				// Si personne n'a encore répondu à la question : aléatoire
				if (jokerTelephoneView.getIsAleatoire()) {
					Question currentQuestion = getModel().getQuestions().get(getModel().getQuestionNb() - 1);
					ArrayList<Integer> idReponsesQuestion = models.dao.ReponseDAO.findIdReponses(currentQuestion.getId());
					
					// Si le 50/50 a été utilisé :
					if (reponsesEnlevees.size() > 0) {
						ArrayList<Integer> idReponsesGardees = new ArrayList<Integer>();
						if (reponsesEnlevees.get(0) != 0 && reponsesEnlevees.get(1) != 0) {
							idReponsesGardees.add(idReponsesQuestion.get(0));
						}
						if (reponsesEnlevees.get(0) != 1 && reponsesEnlevees.get(1) != 1) {
							idReponsesGardees.add(idReponsesQuestion.get(1));
						}
						if (reponsesEnlevees.get(0) != 2 && reponsesEnlevees.get(1) != 2) {
							idReponsesGardees.add(idReponsesQuestion.get(2));
						}
						if (reponsesEnlevees.get(0) != 3 && reponsesEnlevees.get(1) != 3) {
							idReponsesGardees.add(idReponsesQuestion.get(3));
						}
						
						int indexReponse = new Random().nextInt(2);
						idReponseDonnee = idReponsesGardees.get(indexReponse);
					}
					
					// Si le 50/50 n'a pas été utilisé :
					else {
						int indexReponse = new Random().nextInt(4);
						idReponseDonnee = idReponsesQuestion.get(indexReponse);
					}
				}
				else {
					int indexPseudoSelectionne = jokerTelephoneView.getComboboxSelectionInt();
					Map<Integer, String> joueursRepondu = jokerTelephoneView.getJoueursRepondu();
					ArrayList<Integer> idReponsesJoueursRepondu = new ArrayList<Integer>(joueursRepondu.keySet());
					idReponseDonnee = idReponsesJoueursRepondu.get(indexPseudoSelectionne);
				}
				
				String reponse = models.dao.ReponseDAO.findById(idReponseDonnee);
				
				String reponseAmi = "";
				int phrase = new Random().nextInt(6);
				if (phrase == 0) {
					reponseAmi = "<p style=\"text-align: center; font-weight: bold; font-size: 13px;\">Je pense que la bonne réponse est : <span style='color: #f37800'>" + reponse + "</span> !</p>";
				}
				else if (phrase == 1) {
					reponseAmi = "<p style=\"text-align: center; font-weight: bold; font-size: 13px;\">La première idée qui m'est venue est : <span style='color: #f37800'>" + reponse + "</span>.</p>";
				}
				else if (phrase == 2) {
					reponseAmi = "<p style=\"text-align: center; font-weight: bold; font-size: 13px;\">Je n'en suis pas certaine, mais j'aurai plutôt dit : <span style='color: #f37800'>" + reponse + "</span>.</p>";
				}
				else if (phrase == 3) {
					reponseAmi = "<p style=\"text-align: center; font-weight: bold; font-size: 13px;\">Olala je n'en sais rien ! Peut-être : <span style='color: #f37800'>" + reponse + "</span>...</p>";
				}
				else if (phrase == 4) {
					reponseAmi = "<p style=\"text-align: center; font-weight: bold; font-size: 13px;\">Je suis presque sûre que la bonne réponse est : <span style='color: #f37800'>" + reponse + "</span> !</p>";
				}
				else if (phrase == 5) {
					reponseAmi = "<p style=\"text-align: center; font-weight: bold; font-size: 13px;\">A ta place j'aurai répondu : <span style='color: #f37800'>" + reponse + "</span> !</p>";
				}
				
				Dialog.messageDialog(jokerTelephoneView, "Joker - Coup de fil à un ami - Sa réponse", false, reponseAmi);
			}
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub 
		jokerTelephoneView.majPseudo();
	}
}