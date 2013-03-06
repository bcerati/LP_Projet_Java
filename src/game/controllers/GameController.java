package game.controllers;

import game.models.GameModel;
import game.views.GameView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import models.dao.JoueurDAO;
import models.dao.QuestionDAO;
import models.metier.Joueur;
import models.metier.Question;


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
		
		// Affichage de la question
		Question q = model.getQuestions().get(model.getQuestionNb() - 1);
		gameView.setQuestion(q.getIntitule());
		gameView.setRepA(q.getReponses().get(0).getIntitule());
		gameView.setRepB(q.getReponses().get(1).getIntitule());
		gameView.setRepC(q.getReponses().get(2).getIntitule());
		gameView.setRepD(q.getReponses().get(3).getIntitule());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		
		if(actionCommand.equals("quitWhitoutSaving")) {
			if(JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter ?", "Voulez-vous vraiment quitter ?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
				System.exit(0);
		}
	}

}
