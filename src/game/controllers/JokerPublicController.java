package game.controllers;

import java.util.ArrayList;
import java.util.Random;

import game.models.GameModel;
import game.views.JokerPublicView;


public class JokerPublicController {
	private JokerPublicView jokerPublicView;
	private GameModel model;
	private ArrayList<Integer> reponsesEnlevees;
	
	public JokerPublicController() {
		
	}
	
	public JokerPublicController(JokerPublicView jokerPublicView, GameModel model) {
		this.setModel(model);
		this.jokerPublicView = jokerPublicView;
		this.reponsesEnlevees = new ArrayList<Integer>();
	}

	public JokerPublicController(JokerPublicView jokerPublicView, GameModel model, ArrayList<Integer> reponsesEnlevees) {
		this.setModel(model);
		this.jokerPublicView = jokerPublicView;
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
	
	
	public ArrayList<Integer> getListePourcentages(int id_question) {
		ArrayList<Integer> listePourcentages = new ArrayList<Integer>();
		ArrayList<Integer> listResponses = models.dao.ReponseDAO.findIdReponses(id_question);
		
		int nbResponse_A = 0;
		int nbResponse_B = 0;
		int nbResponse_C = 0;
		int nbResponse_D = 0;
		
		// Si le joker 50/50 a été utilisé :
		if (reponsesEnlevees.size() > 0) {
			if (reponsesEnlevees.get(0) != 0 && reponsesEnlevees.get(1) != 0)
				nbResponse_A = models.dao.ReponseDAO.findNbResponseByResponse(id_question, 0, listResponses);
			if (reponsesEnlevees.get(0) != 1 && reponsesEnlevees.get(1) != 1)
				nbResponse_B = models.dao.ReponseDAO.findNbResponseByResponse(id_question, 1, listResponses);
			if (reponsesEnlevees.get(0) != 2 && reponsesEnlevees.get(1) != 2)
				nbResponse_C = models.dao.ReponseDAO.findNbResponseByResponse(id_question, 2, listResponses);
			if (reponsesEnlevees.get(0) != 3 && reponsesEnlevees.get(1) != 3)
				nbResponse_D = models.dao.ReponseDAO.findNbResponseByResponse(id_question, 3, listResponses);
		}
		// Sinon si le joker 50/50 n'a pas été utilisé
		else {
			nbResponse_A = models.dao.ReponseDAO.findNbResponseByResponse(id_question, 0, listResponses);
			nbResponse_B = models.dao.ReponseDAO.findNbResponseByResponse(id_question, 1, listResponses);
			nbResponse_C = models.dao.ReponseDAO.findNbResponseByResponse(id_question, 2, listResponses);
			nbResponse_D = models.dao.ReponseDAO.findNbResponseByResponse(id_question, 3, listResponses);
		}
		
		int pourcentageResponse_A = 0;
		int pourcentageResponse_B = 0;
		int pourcentageResponse_C = 0;
		int pourcentageResponse_D = 0;
		
		int nbTotalResponses = nbResponse_A + nbResponse_B + nbResponse_C + nbResponse_D;
		
		// Si aucune réponse n'a été donnée : Traitement aléatoire
		if (nbTotalResponses == 0) {
			nbTotalResponses = 100;
			
			// Si le joker 50/50 a été utilisé :
			if (reponsesEnlevees.size() > 0) {
				
				if (reponsesEnlevees.get(0) != 0 && reponsesEnlevees.get(1) != 0) {
					nbResponse_A = new Random().nextInt(nbTotalResponses + 1);
					pourcentageResponse_A = (int)(100 * ((float)nbResponse_A/nbTotalResponses));
					
					if (reponsesEnlevees.get(0) != 1 && reponsesEnlevees.get(1) != 1) {
						nbResponse_B = nbTotalResponses-nbResponse_A;
						pourcentageResponse_B = (int)(100 * ((float)nbResponse_B/nbTotalResponses));
					}
					else if (reponsesEnlevees.get(0) != 2 && reponsesEnlevees.get(1) != 2) {
						nbResponse_C = nbTotalResponses-nbResponse_A;
						pourcentageResponse_C = (int)(100 * ((float)nbResponse_C/nbTotalResponses));
					}
					else if (reponsesEnlevees.get(0) != 3 && reponsesEnlevees.get(1) != 3) {
						nbResponse_D = nbTotalResponses-nbResponse_A;
						pourcentageResponse_D = (int)(100 * ((float)nbResponse_D/nbTotalResponses));
					}
				}
				else if (reponsesEnlevees.get(0) != 1 && reponsesEnlevees.get(1) != 1) {
					nbResponse_B = new Random().nextInt(nbTotalResponses + 1);
					pourcentageResponse_B = (int)(100 * ((float)nbResponse_B/nbTotalResponses));
					
					if (reponsesEnlevees.get(0) != 2 && reponsesEnlevees.get(1) != 2) {
						nbResponse_C = nbTotalResponses-nbResponse_B;
						pourcentageResponse_C = (int)(100 * ((float)nbResponse_C/nbTotalResponses));
					}
					else if (reponsesEnlevees.get(0) != 3 && reponsesEnlevees.get(1) != 3) {
						nbResponse_D = nbTotalResponses-nbResponse_B;
						pourcentageResponse_D = (int)(100 * ((float)nbResponse_D/nbTotalResponses));
					}
				}
				else if (reponsesEnlevees.get(0) != 2 && reponsesEnlevees.get(1) != 2) {
					nbResponse_C = new Random().nextInt(nbTotalResponses + 1);
					pourcentageResponse_C = (int)(100 * ((float)nbResponse_C/nbTotalResponses));
					
					nbResponse_D = nbTotalResponses-nbResponse_C;
					pourcentageResponse_D = (int)(100 * ((float)nbResponse_D/nbTotalResponses));
				}
			}
			// Sinon s'il n'a pas été utilisé :
			else {
				nbResponse_A = new Random().nextInt(nbTotalResponses + 1);
				pourcentageResponse_A = (int)(100 * ((float)nbResponse_A/nbTotalResponses));
				
				if (nbResponse_A < nbTotalResponses) {
					int nbResponsesRestant = nbTotalResponses-nbResponse_A;
					
					nbResponse_B = new Random().nextInt(nbResponsesRestant + 1);
					pourcentageResponse_B = (int)(100 * ((float)nbResponse_B/nbTotalResponses));
					
					if (nbResponse_B < nbResponsesRestant) {
						nbResponsesRestant = nbResponsesRestant-nbResponse_B;
						
						nbResponse_C = new Random().nextInt(nbResponsesRestant + 1);
						pourcentageResponse_C = (int)(100 * ((float)nbResponse_C/nbTotalResponses));
						
						if (nbResponse_C < nbResponsesRestant) {
							nbResponsesRestant = nbResponsesRestant-nbResponse_C;
							
							nbResponse_D = nbResponsesRestant;
							pourcentageResponse_D = (int)(100 * ((float)nbResponse_D/nbTotalResponses));
						}
					}
				}
			}
		}
		else {
			// Calcul en fonction des réponses données
			pourcentageResponse_A = (int)(100 * ((float)nbResponse_A/nbTotalResponses));
			pourcentageResponse_B = (int)(100 * ((float)nbResponse_B/nbTotalResponses));
			pourcentageResponse_C = (int)(100 * ((float)nbResponse_C/nbTotalResponses));
			pourcentageResponse_D = (int)(100 * ((float)nbResponse_D/nbTotalResponses));
		}
		listePourcentages.add(pourcentageResponse_A);
		listePourcentages.add(pourcentageResponse_B);
		listePourcentages.add(pourcentageResponse_C);
		listePourcentages.add(pourcentageResponse_D);
		
		return listePourcentages;
	}
}