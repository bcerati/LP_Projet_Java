package game.views;

import game.controllers.HomeController;
import general_views.Button;
import general_views.Dialog;
import general_views.Panel;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Cursor;
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
		rules.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
		rules.setActionCommand("rules");

		newGame = new Button("jouer_home.png", 5 * caseWidth, (8 * caseHeight) / 3);
		newGame.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
		newGame.setActionCommand("newGame");

		quit = new Button("quitter_home.png", 5 * caseWidth, (8 * caseHeight) / 3);
		quit.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
		quit.setActionCommand("quit");

		options.add(rules);
		options.add(newGame);
		options.add(quit);
		return options;
	}

	public void showRules() {
		Dialog.messageDialog(this, "Règles du jeu", true, "<html><body><h1 style=\"text-align: center; margin-bottom: 30px;\">Règles du jeu</h1><div style=\"width: 400px; text-align: justify;\">Le jeu se déroule autour d'une série de questions dont les gains associés augmentent à chaque échelon, pour atteindre 1 000 000 € en fin de parcours. Je vous poserai, pour chaque échelon de la pyramide, une question et quatre réponses possibles. La difficulté des questions s'accroît avec la somme proposée. Une fois la question posée, le joueur a toutefois le choix d'abandonner ou non le jeu sans répondre et repartir avec les gains de l'échelon précédent.</div><div style=\"width: 400px; text-align: justify;\">Vous aurez quatres joker pour vous aider en cas de doute :</div><ul><li style=\"width: 400px; text-align: justify; padding-top:10px;\">le 50/50 : l'ordinateur élimine deux mauvaises réponses sur les quatre ; il a pour vocation de faciliter le choix.</li><li style=\"width: 400px; text-align: justify; padding-top:10px;\">l'appel à un ami : le candidat pose la question et propose les réponses à l'un de ses « amis » et ce dernier lui conseille, ou non, une des réponses proposées.</li><li style=\"width: 400px; text-align: justify; padding-top:10px;\">l'avis du public : les membres du public sont appelés à voter pour la réponse qui leur semble correcte. Les résultats sont ensuite représentés sous forme de pourcentages.</li><li style=\"width: 400px; text-align: justify; padding-top:10px;\">le switch : il permet à celui-ci d'abandonner la question en cours, et d'en passer à une autre de même valeur.</li></ul></body></html>");
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
