package game.views;

import game.controllers.GameController;
import general_views.Button;
import general_views.Panel;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GameView extends JFrame {

	/*
	 * gamePanel : panel du jeu (avec la question, les réponses, les jokers etc...)
	 */
	private JPanel gamePanel;

	// Boutons des jokers et du "quitter"
	private Button btn5050;
	private Button btnPublic;
	private Button btnCoupDeFil;
	private Button btnSwitch;

	// Boutons des réponses proposées à la question posée
	private Button btnRespA;
	private Button btnRespB;
	private Button btnRespC;
	private Button btnRespD;

	private GameController controller;

	public GameView() {
		setTitle("Qui veut gagner des millions");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		controller = new GameController(this);
		controller.fillPseudo();

		buildGUI();

		this.setContentPane(gamePanel);		
		this.pack();
		this.setVisible(true);
	}
	
	public void buildGUI() {

		// Panel global qui contiendra tout
		gamePanel = new JPanel();
		gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.PAGE_AXIS));
	
		// On ajoute tout au panel principale (qui est en PAGE_AXIS (= de haut en bas))
		gamePanel.add(buildTop());
		gamePanel.add(buildQuestion());
		gamePanel.add(buildResponses());
	}
	
	public JPanel buildTop() {
		int caseWidht = controller.getModel().getCaseWidth(), caseHeight = controller.getModel().getCaseHeight();

		// Panel du haut (= bouton quit + jokers + logo + pyramide)
		JPanel panelTop = new JPanel();
		panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.LINE_AXIS));
		panelTop.setPreferredSize(new Dimension(10 * caseWidht, 5 * caseHeight));

		// Panel jokers (bouton quit + 4 jokers)
		JPanel panelJokers = new JPanel(new GridLayout(5, 1));
		panelJokers.setPreferredSize(new Dimension(caseWidht, 5 * caseHeight));

		Button btnQuit = new Button("quitter.png", caseWidht, caseHeight);
		btnQuit.setActionCommand("quitWhitoutSaving");
		btnQuit.addActionListener(new GameController(this));
		panelJokers.add(btnQuit);

		btn5050 = new Button("joker_5050.png", caseWidht, caseHeight);
		panelJokers.add(btn5050);
		
		btnCoupDeFil = new Button("joker_appel.png", caseWidht, caseHeight);
		panelJokers.add(btnCoupDeFil);
		
		btnPublic = new Button("joker_public.png", caseWidht, caseHeight);
		panelJokers.add(btnPublic);

		btnSwitch = new Button("joker_switch.png", caseWidht, caseHeight);
		panelJokers.add(btnSwitch);

		// Panel du logo du jeu
		Panel panelLogo = new Panel("logo.png", 5 * caseWidht, 5 * caseHeight);

		// Panel de la pyramide des gains
		Panel panelPyramide = new Panel("pyramide.png", 4 * caseWidht, 5 * caseHeight);
		
		// Remplissage du panel du haut (qui possède un layout en LINE_AXIS (= de gauche à droite))
		panelTop.add(panelJokers);
		panelTop.add(panelLogo);
		panelTop.add(panelPyramide);
		
		return panelTop;
	}
	
	public JPanel buildQuestion() {
		int caseWidht = controller.getModel().getCaseWidth(), caseHeight = controller.getModel().getCaseHeight();
		
		String s = "Quel pays a gagné la coupe du monde de Football en 2006 ?";
		JLabel lbl = new JLabel("<html><head><style>div {font-size: 14px; width: 800px; margin-left: 40px; margin-right: 40px; text-align: center; padding-top: 12px; color: white; font-family: ConeriaScript;}</style></head><body><div>"+s+"</div></body></html>");  
		
		Panel p = new Panel("question.png", 10 * caseWidht, caseHeight);
		p.add(lbl);
		
		return p;
	}
	
	public JPanel buildResponses() {
		int caseWidht = controller.getModel().getCaseWidth(), caseHeight = controller.getModel().getCaseHeight();

		// Panel des 4 réponses
		JPanel panelResponses = new JPanel(new GridLayout(2, 2));
		panelResponses.setPreferredSize(new Dimension(10 * caseWidht, 2 * caseHeight));
		
		btnRespA = new Button("reponse_haut_gauche.png", 5 * caseWidht, caseHeight);
		String s = "<span>A.</span> Les buveurs de thé";
		JLabel lbl = new JLabel("<html><head><style>div span { font-weight: bold; color: #F37800; } div {font-size: 14px; width: 500px; margin-left: 40px; margin-right: 40px; padding-top: 5px; color: white; font-family: ConeriaScript;}</style></head><body><div>"+s+"</div></body></html>");  
		btnRespA.add(lbl);
		panelResponses.add(btnRespA);

		btnRespB = new Button("reponse_haut_droit.png", 5 * caseWidht, caseHeight);
		s = "<span>B.</span> Les mangeurs de baguettes";
		lbl = new JLabel("<html><head><style>div span { font-weight: bold; color: #F37800; } div {font-size: 14px; width: 500px; margin-left: 40px; margin-right: 40px; padding-top: 5px; color: white; font-family: ConeriaScript;}</style></head><body><div>"+s+"</div></body></html>");  
		btnRespB.add(lbl);
		panelResponses.add(btnRespB);

		btnRespC = new Button("reponse_bas_gauche.png", 5 * caseWidht, caseHeight);
		s = "<span>C.</span> Les mangeur de pizzas";
		lbl = new JLabel("<html><head><style>div span { font-weight: bold; color: #F37800; } div {font-size: 14px; width: 500px; margin-left: 40px; margin-right: 40px; padding-top: 5px; color: white; font-family: ConeriaScript;}</style></head><body><div>"+s+"</div></body></html>");  
		btnRespC.add(lbl);
		panelResponses.add(btnRespC);

		btnRespD = new Button("reponse_bas_droit.png", 5 * caseWidht, caseHeight);
		s = "<span>D.</span> Les casques à pointe";
		lbl = new JLabel("<html><head><style>div span { font-weight: bold; color: #F37800; } div {font-size: 14px; width: 500px; margin-left: 40px; margin-right: 40px; padding-top: 5px; color: white; font-family: ConeriaScript;}</style></head><body><div>"+s+"</div></body></html>");  
		btnRespD.add(lbl);
		panelResponses.add(btnRespD);
		
		return panelResponses;
	}

	public GameController getController() {
		return controller;
	}	
}
