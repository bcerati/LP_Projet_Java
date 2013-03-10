package game.controllers;

import game.models.HomeModel;
import game.views.GameView;
import game.views.HomeView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class HomeController implements ActionListener {

	private JFrame view;
	private HomeModel model;
	
	public HomeController(JFrame v) {
		model = new HomeModel();
		view = v;
	}

	public HomeModel getModel() {
		return model;
	}

	public void setModel(HomeModel model) {
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String actionCommand = e.getActionCommand();
		if(actionCommand.equals("quit"))
			System.exit(0);
		if(actionCommand.equals("rules"))
			((HomeView)view).showRules();
		if(actionCommand.equals("newGame")) {
			HomeView tmp = (HomeView) view;
			view = new GameView();
			tmp.getAudioClip().stop();
			tmp.dispose();

		}
	}
}
