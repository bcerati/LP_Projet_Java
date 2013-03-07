package game.views;

import game.controllers.HomeController;
import general_views.Button;
import general_views.Dialog;
import general_views.Panel;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class HomeView extends JFrame {
	
	/*
	 * Panel principal de l'accueil du jeu
	 */
	private JPanel homePanel;
	
	/*
	 * Bouton qui appelera les règles du jeu
	 */
	private Button rules;
	
	/*
	 * Bouton qui démarrera un jeu
	 */
	private Button newGame;

	/*
	 * Bouton qui quitera le jeu
	 */
	private Button quit;
	
	/*
	 * Pour jouer du son
	 */
	private AudioClip audioClip = null;

	private HomeController controller;

	public HomeView() {
		this.setTitle("Qui veut gagner des millions");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		controller = new HomeController(this);
		int caseWidth = controller.getModel().getCaseWidth(), caseHeight = controller.getModel().getCaseHeight();
		audioClip = Applet.newAudioClip(this.getClass().getClassLoader().getResource("sounds/home.wav"));
		audioClip.play();

		buildGUI();

		this.setSize(10 * caseWidth, 8 * caseHeight);
		this.setContentPane(homePanel);
		this.setVisible(true);
		setHomeListeners();
	}

	/*
	 * Construction du l'interface de la page d'accueil de l'admin
	 */
	public void buildGUI() {
		homePanel = new JPanel(new GridLayout(1, 2));

		homePanel.add(buildLogo());
		homePanel.add(buildOptions());
	}

	/*
	 * Construction du panel de droite, le logo
	 */
	public Panel buildLogo() {
		int caseWidth = controller.getModel().getCaseWidth(), caseHeight = controller.getModel().getCaseHeight();
		return new Panel("logo_home.png", 5 * caseWidth, 8 * caseHeight);
	}

	/*
	 * Construction des options du jeu (règles, démarer, quitter)
	 */
	public JPanel buildOptions() {
		JPanel options = new JPanel(new GridLayout(3, 1));
		int caseWidth = controller.getModel().getCaseWidth(), caseHeight = controller.getModel().getCaseHeight();

		rules = new Button("regles_home.png", 5 * caseWidth, (8 * caseHeight) / 3);
		rules.setActionCommand("rules");

		newGame = new Button("jouer_home.png", 5 * caseWidth, (8 * caseHeight) / 3);

		newGame.setActionCommand("newGame");

		quit = new Button("quitter_home.png", 5 * caseWidth, (8 * caseHeight) / 3);
		quit.setActionCommand("quit");

		options.add(rules);
		options.add(newGame);
		options.add(quit);
		return options;
	}

	public void showRules() {
		Dialog.messageDialog(this, "Règles du jeu", true, "Ici les règles");
	}

	public AudioClip getAudioClip() {
		return audioClip;
	}
	
	public void setHomeListeners() {
		rules.addActionListener(new HomeController(this));
		newGame.addActionListener(new HomeController(this));
		quit.addActionListener(new HomeController(this));
	}
	
}
