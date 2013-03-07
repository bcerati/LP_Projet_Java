package game.views;

import game.controllers.GameController;
import game.models.GameModel;
import general_views.Button;
import general_views.Panel;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import models.metier.Reponse;


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

	// Panel de la question
	private Panel questionPanel;

	// Boutons des réponses proposées à la question posée
	private JPanel panelResponses;
	private Button btnRespA;
	private Button btnRespB;
	private Button btnRespC;
	private Button btnRespD;

	private GameController controller;

	public GameView() {
		setTitle("Qui veut gagner des millions");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		controller = new GameController(this);

		buildGUI();

		controller.createNewGame();

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
		Panel panelPyramide = new Panel("pyramide_palier1.png", 4 * caseWidht, 5 * caseHeight);

		// Remplissage du panel du haut (qui possède un layout en LINE_AXIS (= de gauche à droite))
		panelTop.add(panelJokers);
		panelTop.add(panelLogo);
		panelTop.add(panelPyramide);
		
		return panelTop;
	}
	
	public JPanel buildQuestion() {
		int caseWidht = controller.getModel().getCaseWidth(), caseHeight = controller.getModel().getCaseHeight();
		
		questionPanel = new Panel("question.png", 10 * caseWidht, caseHeight);
		
		return questionPanel;
	}
	
	public JPanel buildResponses() {
		int caseWidht = controller.getModel().getCaseWidth(), caseHeight = controller.getModel().getCaseHeight();

		// Panel des 4 réponses
		panelResponses = new JPanel(new GridLayout(2, 2));
		panelResponses.setPreferredSize(new Dimension(10 * caseWidht, 2 * caseHeight));
		
		btnRespA = new Button("reponse.png", 5 * caseWidht, caseHeight);
		btnRespA.setActionCommand("A");
		btnRespA.addActionListener(controller);
		panelResponses.add(btnRespA);

		btnRespB = new Button("reponse.png", 5 * caseWidht, caseHeight);
		btnRespB.setActionCommand("B");
		btnRespB.addActionListener(controller);
		panelResponses.add(btnRespB);

		btnRespC = new Button("reponse.png", 5 * caseWidht, caseHeight);
		btnRespC.setActionCommand("C");
		btnRespC.addActionListener(controller);
		panelResponses.add(btnRespC);

		btnRespD = new Button("reponse.png", 5 * caseWidht, caseHeight);
		btnRespD.setActionCommand("D");
		btnRespD.addActionListener(controller);
		panelResponses.add(btnRespD);

		return panelResponses;
	}

	public GameController getController() {
		return controller;
	}

	public String askPseudo() {
		String s = JOptionPane.showInputDialog("Veuillez entrer votre pseudo :");

		if(s.equals(""))
			return "John Doe";
		else
			return s;
	}

	public void setQuestion(String q) {
		questionPanel.removeAll();
		JLabel lbl = new JLabel("<html><head><style>div {font-size: 14px; width: 800px; margin-left: 40px; margin-right: 40px; text-align: center; padding-top: 12px; color: white; font-family: ConeriaScript;}</style></head><body><div>"+q+"</div></body></html>");  
		questionPanel.add(lbl);
	}

	public void setRepA(String a) {
		btnRespA.removeAll();
		a = "<span>A.</span> " + a;
		JLabel lbl = new JLabel("<html><head><style>div span { font-weight: bold; color: #F37800; } div {font-size: 14px; width: 500px; margin-left: 40px; margin-right: 40px; padding-top: 5px; color: white; font-family: ConeriaScript;}</style></head><body><div>"+a+"</div></body></html>");  
		btnRespA.add(lbl);
	}
	
	public void setRepA(String a, boolean select) {
		btnRespA.removeAll();
		a = "<span>A.</span> " + a;
		JLabel lbl = new JLabel("<html><head><style>div span { font-weight: bold; color: white; } div {font-size: 14px; width: 500px; margin-left: 40px; margin-right: 40px; padding-top: 5px; color: black; font-family: ConeriaScript;}</style></head><body><div>"+a+"</div></body></html>");  
		btnRespA.add(lbl);
	}
	
	public void setRepB(String b) {
		btnRespB.removeAll();
		b = "<span>B.</span> " + b;
		JLabel lbl = new JLabel("<html><head><style>div span { font-weight: bold; color: #F37800; } div {font-size: 14px; width: 500px; margin-left: 40px; margin-right: 40px; padding-top: 5px; color: white; font-family: ConeriaScript;}</style></head><body><div>"+b+"</div></body></html>");  
		btnRespB.add(lbl);
	}
	
	public void setRepB(String b, boolean select) {
		btnRespB.removeAll();
		b = "<span>B.</span> " + b;
		JLabel lbl = new JLabel("<html><head><style>div span { font-weight: bold; color: white; } div {font-size: 14px; width: 500px; margin-left: 40px; margin-right: 40px; padding-top: 5px; color: black; font-family: ConeriaScript;}</style></head><body><div>"+b+"</div></body></html>");  
		btnRespB.add(lbl);
	}
	
	public void setRepC(String c) {
		btnRespC.removeAll();
		c = "<span>C.</span> " + c;
		JLabel lbl = new JLabel("<html><head><style>div span { font-weight: bold; color: #F37800; } div {font-size: 14px; width: 500px; margin-left: 40px; margin-right: 40px; padding-top: 5px; color: white; font-family: ConeriaScript;}</style></head><body><div>"+c+"</div></body></html>");  
		btnRespC.add(lbl);
	}
	
	public void setRepC(String c, boolean select) {
		btnRespC.removeAll();
		c = "<span>C.</span> " + c;
		JLabel lbl = new JLabel("<html><head><style>div span { font-weight: bold; color: white; } div {font-size: 14px; width: 500px; margin-left: 40px; margin-right: 40px; padding-top: 5px; color: black; font-family: ConeriaScript;}</style></head><body><div>"+c+"</div></body></html>");  
		btnRespC.add(lbl);
	}
	
	public void setRepD(String d) {
		btnRespD.removeAll();
		d = "<span>D.</span> " + d;
		JLabel lbl = new JLabel("<html><head><style>div span { font-weight: bold; color: #F37800; } div {font-size: 14px; width: 500px; margin-left: 40px; margin-right: 40px; padding-top: 5px; color: white; font-family: ConeriaScript;}</style></head><body><div>"+d+"</div></body></html>");  
		btnRespD.add(lbl);
	}

	public void setRepD(String d, boolean select) {
		btnRespD.removeAll();
		d = "<span>D.</span> " + d;
		JLabel lbl = new JLabel("<html><head><style>div span { font-weight: bold; color: white; } div {font-size: 14px; width: 500px; margin-left: 40px; margin-right: 40px; padding-top: 5px; color: black; font-family: ConeriaScript;}</style></head><body><div>"+d+"</div></body></html>");  
		btnRespD.add(lbl);
	}
	
	public Button getBtnRespA() {
		return btnRespA;
	}

	public void setBtnRespA(Button btnRespA) {
		this.btnRespA = btnRespA;
	}

	public Button getBtnRespB() {
		return btnRespB;
	}

	public void setBtnRespB(Button btnRespB) {
		this.btnRespB = btnRespB;
	}

	public Button getBtnRespC() {
		return btnRespC;
	}

	public void setBtnRespC(Button btnRespC) {
		this.btnRespC = btnRespC;
	}

	public Button getBtnRespD() {
		return btnRespD;
	}

	public void setBtnRespD(Button btnRespD) {
		this.btnRespD = btnRespD;
	}

	public boolean switchSelect(String q) {
		GameModel m = controller.getModel();
		ArrayList<Reponse> v = m.getQuestions().get(m.getQuestionNb() - 1).getReponses();

		if(q.equals("A")) {
			panelResponses.remove(0);
			btnRespA = new Button("question_select.png", 5 * m.getCaseWidth(), m.getCaseHeight());
			setRepA(v.get(0).getIntitule(), true);
			panelResponses.add(btnRespA, 0);
			this.validate();
		}
		else if(q.equals("B")) {
			panelResponses.remove(1);
			btnRespB = new Button("question_select.png", 5 * m.getCaseWidth(), m.getCaseHeight());
			setRepB(v.get(1).getIntitule(), true);
			panelResponses.add(btnRespB, 1);
			this.validate();
		}
		else if(q.equals("C")) {
			panelResponses.remove(2);
			btnRespC = new Button("question_select.png", 5 * m.getCaseWidth(), m.getCaseHeight());
			setRepC(v.get(2).getIntitule(), true);
			panelResponses.add(btnRespC, 2);
			this.validate();
		}
		else if(q.equals("D")) {
			panelResponses.remove(3);
			btnRespD = new Button("question_select.png", 5 * m.getCaseWidth(), m.getCaseHeight());
			setRepD(v.get(3).getIntitule(), true);
			panelResponses.add(btnRespD, 3);
			this.validate();
		}
		setResponsesListeners(false, false, false, false);
		return true;

	}
	
	public void setResponsesListeners(boolean a, boolean b, boolean c, boolean d) {

		for(int i = 0 ; i < btnRespA.getActionListeners().length ; i++)
			btnRespA.removeActionListener(btnRespA.getActionListeners()[i]);

		for(int i = 0 ; i < btnRespB.getActionListeners().length ; i++)
			btnRespB.removeActionListener(btnRespB.getActionListeners()[i]);

		for(int i = 0 ; i < btnRespC.getActionListeners().length ; i++)
			btnRespC.removeActionListener(btnRespC.getActionListeners()[i]);

		for(int i = 0 ; i < btnRespD.getActionListeners().length ; i++)
			btnRespD.removeActionListener(btnRespD.getActionListeners()[i]);

		if(a) {
			btnRespA.setActionCommand("A");
			btnRespA.addActionListener(controller);
		}
		
		if(b) {
			btnRespB.setActionCommand("B");
			btnRespB.addActionListener(controller);
		}
		
		if(c) {
			btnRespC.setActionCommand("C");
			btnRespC.addActionListener(controller);
		}
		
		if(d) {
			btnRespD.setActionCommand("D");
			btnRespD.addActionListener(controller);
		}

	}
}
