package game.controllers;

import game.models.GameModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class GameController implements ActionListener {

	private JFrame gameView;
	private GameModel model;

	public GameController(JFrame gameView) {
		model = new GameModel();
		this.gameView = gameView;
    }

	public GameModel getModel() {
		return model;
	}


	public void setModel(GameModel model) {
		this.model = model;
	}

	public void fillPseudo() {
		getModel().setPseudo(JOptionPane.showInputDialog("Entrez votre pseudo de joueur"));
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
