package game.views;

import game.controllers.GameController;
import game.models.GameModel;
import general_views.Button;
import general_views.Dialog;
import general_views.Panel;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.metier.Question;
import models.metier.Reponse;


public class GameView extends JFrame {

	/*
	 * gamePanel : panel du jeu (avec la question, les réponses, les jokers etc...)
	 */
	private JPanel gamePanel;

	// Boutons des jokers et du "quitter"
	private JPanel panelJokers;
	private Button btn5050;
	private Button btnPublic;
	private Button btnCoupDeFil;
	private Button btnSwitch;

	// Panel de la question
	private Panel questionPanel;
	private JPanel panelTop;

	// Boutons des réponses proposées à la question posée
	private JPanel panelResponses;
	private Button btnRespA;
	private Button btnRespB;
	private Button btnRespC;
	private Button btnRespD;

	private GameController controller;
	
	private AudioClip backgroundSound = null;
	private AudioClip selectionSound = null;
	private AudioClip goodSound = null;
	private AudioClip wrongSound = null;
	private AudioClip palier1Sound = null;
	private AudioClip palier2Sound = null;
	private AudioClip palier3Sound = null;
	private AudioClip joker5050Sound = null;


	public GameView() {
		setTitle("Qui veut gagner des millions");

		controller = new GameController(this);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(controller);

		buildGUI();
		setSounds();

		controller.createNewGame();

		this.setContentPane(gamePanel);		
		this.pack();
		this.setVisible(true);
	}
	
	private void setSounds() {
		backgroundSound = Applet.newAudioClip(this.getClass().getClassLoader().getResource("sounds/background_sound.wav"));
		goodSound = Applet.newAudioClip(this.getClass().getClassLoader().getResource("sounds/good.wav"));		
		wrongSound = Applet.newAudioClip(this.getClass().getClassLoader().getResource("sounds/wrong.wav"));		
		palier1Sound = Applet.newAudioClip(this.getClass().getClassLoader().getResource("sounds/1500_sound.wav"));		
		palier2Sound = Applet.newAudioClip(this.getClass().getClassLoader().getResource("sounds/48000_sound.wav"));		
		palier3Sound = Applet.newAudioClip(this.getClass().getClassLoader().getResource("sounds/1000000_sound.wav"));		
		//joker5050Sound = Applet.newAudioClip(this.getClass().getClassLoader().getResource("sounds/sound_fity.wav"));		
		selectionSound = Applet.newAudioClip(this.getClass().getClassLoader().getResource("sounds/selection_question.wav"));	
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
		panelTop = new JPanel();
		panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.LINE_AXIS));
		panelTop.setPreferredSize(new Dimension(10 * caseWidht, 5 * caseHeight));

		// Panel jokers (bouton quit + 4 jokers)
		panelJokers = new JPanel(new GridLayout(5, 1));
		panelJokers.setPreferredSize(new Dimension(caseWidht, 5 * caseHeight));

		Button btnQuit = new Button("quitter.png", caseWidht, caseHeight);
		btnQuit.setActionCommand("quitWhithSaving");
		btnQuit.addActionListener(controller);
		btnQuit.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
		panelJokers.add(btnQuit);

		btn5050 = new Button("joker_5050.png", caseWidht, caseHeight);
		btn5050.setActionCommand("joker5050");
		btn5050.addActionListener(controller);
		btn5050.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
		panelJokers.add(btn5050);
		
		btnCoupDeFil = new Button("joker_appel.png", caseWidht, caseHeight);
		btnCoupDeFil.setActionCommand("jokerCoupDeFil");
		btnCoupDeFil.addActionListener(controller);
		btnCoupDeFil.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
		panelJokers.add(btnCoupDeFil);
		
		btnPublic = new Button("joker_public.png", caseWidht, caseHeight);
		btnPublic.setActionCommand("jokerPublic");
		btnPublic.addActionListener(controller);
		btnPublic.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
		panelJokers.add(btnPublic);

		btnSwitch = new Button("joker_switch.png", caseWidht, caseHeight);
		btnSwitch.setActionCommand("jokerSwitch");
		btnSwitch.addActionListener(controller);
		btnSwitch.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
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
		
		btnRespA = new Button("reponse_haut_gauche.png", 5 * caseWidht, caseHeight);
		btnRespA.setActionCommand("A");
		btnRespA.addActionListener(controller);
		btnRespA.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
		panelResponses.add(btnRespA);

		btnRespB = new Button("reponse_haut_droit.png", 5 * caseWidht, caseHeight);
		btnRespB.setActionCommand("B");
		btnRespB.addActionListener(controller);
		btnRespB.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
		panelResponses.add(btnRespB);

		btnRespC = new Button("reponse_bas_gauche.png", 5 * caseWidht, caseHeight);
		btnRespC.setActionCommand("C");
		btnRespC.addActionListener(controller);
		btnRespC.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
		panelResponses.add(btnRespC);

		btnRespD = new Button("reponse_bas_droit.png", 5 * caseWidht, caseHeight);
		btnRespD.setActionCommand("D");
		btnRespD.addActionListener(controller);
		btnRespD.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
		panelResponses.add(btnRespD);

		return panelResponses;
	}
	
	public GameController getController() {
		return controller;
	}

	public String askPseudo() {
		Dialog.textFieldDialog(this, "Nom du joueur", true, "Veuillez entrez le nom du joueur");
		String s = Dialog.choix;

		if(s.equals(""))
			return "John Doe";
		else
			return s;
	}

	public void setQuestion(String q) {
		if(questionPanel.getComponents().length == 0) {
			JLabel lbl = new JLabel("<html><head><style>div {font-size: 14px; width: 800px; margin-left: 40px; margin-right: 40px; text-align: center; padding-top: 10px; color: white; font-family: ConeriaScript;}</style></head><body><div>"+q+"</div></body></html>");  
			questionPanel.add(lbl);
		}
		else
			((JLabel)questionPanel.getComponent(0)).setText("<html><head><style>div {font-size: 14px; width: 800px; margin-left: 40px; margin-right: 40px; text-align: center; padding-top: 10px; color: white; font-family: ConeriaScript;}</style></head><body><div>"+q+"</div></body></html>");
		this.validate();
	}

	public void setRepA(String a) {
		btnRespA.removeAll();
		btnRespA.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
		a = "<span>A.</span> " + a;
		JLabel lbl = new JLabel("<html><head><style>div span { font-weight: bold; color: #F37800; } div {font-size: 14px; width: 500px; margin-left: 40px; margin-right: 140px; padding-top: 5px; color: white; font-family: ConeriaScript;}</style></head><body><div>"+a+"</div></body></html>");  
		btnRespA.add(lbl);
	}
	
	public void setRepA(String a, boolean select) {
		btnRespA.removeAll();
		btnRespA.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
		a = "<span>A.</span> " + a;
		JLabel lbl = new JLabel("<html><head><style>div span { font-weight: bold; color: white; } div {font-size: 14px; width: 500px; margin-left: 40px; margin-right: 140px; padding-top: 5px; color: black; font-family: ConeriaScript;}</style></head><body><div>"+a+"</div></body></html>");  
		btnRespA.add(lbl);
	}
	
	public void setRepB(String b) {
		btnRespB.removeAll();
		btnRespB.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
		b = "<span>B.</span> " + b;
		JLabel lbl = new JLabel("<html><head><style>div span { font-weight: bold; color: #F37800; } div {font-size: 14px; width: 500px; margin-left: 40px; margin-right: 140px; padding-top: 5px; color: white; font-family: ConeriaScript;}</style></head><body><div>"+b+"</div></body></html>");  
		btnRespB.add(lbl);
	}
	
	public void setRepB(String b, boolean select) {
		btnRespB.removeAll();
		btnRespB.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
		b = "<span>B.</span> " + b;
		JLabel lbl = new JLabel("<html><head><style>div span { font-weight: bold; color: white; } div {font-size: 14px; width: 500px; margin-left: 40px; margin-right: 140px; padding-top: 5px; color: black; font-family: ConeriaScript;}</style></head><body><div>"+b+"</div></body></html>");  
		btnRespB.add(lbl);
	}
	
	public void setRepC(String c) {
		btnRespC.removeAll();
		btnRespC.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
		c = "<span>C.</span> " + c;
		JLabel lbl = new JLabel("<html><head><style>div span { font-weight: bold; color: #F37800; } div {font-size: 14px; width: 500px; margin-left: 40px; margin-right: 140px; padding-top: 5px; color: white; font-family: ConeriaScript;}</style></head><body><div>"+c+"</div></body></html>");  
		btnRespC.add(lbl);
	}
	
	public void setRepC(String c, boolean select) {
		btnRespC.removeAll();
		btnRespC.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
		c = "<span>C.</span> " + c;
		JLabel lbl = new JLabel("<html><head><style>div span { font-weight: bold; color: white; } div {font-size: 14px; width: 500px; margin-left: 40px; margin-right: 140px; padding-top: 5px; color: black; font-family: ConeriaScript;}</style></head><body><div>"+c+"</div></body></html>");  
		btnRespC.add(lbl);
	}
	
	public void setRepD(String d) {
		btnRespD.removeAll();
		btnRespD.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
		d = "<span>D.</span> " + d;
		JLabel lbl = new JLabel("<html><head><style>div span { font-weight: bold; color: #F37800; } div {font-size: 14px; width: 500px; margin-left: 40px; margin-right: 140px; padding-top: 5px; color: white; font-family: ConeriaScript;}</style></head><body><div>"+d+"</div></body></html>");  
		btnRespD.add(lbl);
	}

	public void setRepD(String d, boolean select) {
		btnRespD.removeAll();
		btnRespD.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
		d = "<span>D.</span> " + d;
		JLabel lbl = new JLabel("<html><head><style>div span { font-weight: bold; color: white; } div {font-size: 14px; width: 500px; margin-left: 40px; margin-right: 140px; padding-top: 5px; color: black; font-family: ConeriaScript;}</style></head><body><div>"+d+"</div></body></html>");  
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

	public AudioClip getBackgroundSound() {
		return backgroundSound;
	}

	public AudioClip getGoodSound() {
		return goodSound;
	}
	
	public AudioClip getWrongSound() {
		return wrongSound;
	}
	
	public AudioClip getPalier1Sound() {
		return palier1Sound;
	}
	
	public AudioClip getPalier2Sound() {
		return palier2Sound;
	}
	
	public AudioClip getPalier3Sound() {
		return palier3Sound;
	}
	
	public AudioClip getJoker5050Sound() {
		return joker5050Sound;
	}
	
	public AudioClip getSelectionSound() {
		return selectionSound;
	}
	
	public  void changePyramid(int i) {
		
		Panel panelPyramide = new Panel("pyramide_palier"+i+".png", 4 * controller.getModel().getCaseWidth(), 5 * controller.getModel().getCaseHeight());
		panelTop.remove(2);
		panelTop.add(panelPyramide, 2);
		panelTop.validate();
	}
	public void writeQuestion() {

		// Affichage de la question
		clearAnswerPanels();
		Question q = controller.getModel().getQuestions().get(controller.getModel().getQuestionNb() - 1);
		setQuestion(q.getIntitule());
		setRepA(q.getReponses().get(0).getIntitule());
		setRepB(q.getReponses().get(1).getIntitule());
		setRepC(q.getReponses().get(2).getIntitule());
		setRepD(q.getReponses().get(3).getIntitule());
		setResponsesListeners(true, true, true, true);
	}

	public void clearAnswerPanels() {
		GameModel m = controller.getModel();

		panelResponses.remove(0);
		btnRespA = new Button("reponse_haut_gauche.png", 5 * m.getCaseWidth(), m.getCaseHeight());
		panelResponses.add(btnRespA, 0);

		panelResponses.remove(1);
		btnRespB = new Button("reponse_haut_droit.png", 5 * m.getCaseWidth(), m.getCaseHeight());
		panelResponses.add(btnRespB, 1);

		panelResponses.remove(2);
		btnRespC = new Button("reponse_bas_gauche.png", 5 * m.getCaseWidth(), m.getCaseHeight());
		panelResponses.add(btnRespC, 2);

		panelResponses.remove(3);
		btnRespD = new Button("reponse_bas_droit.png", 5 * m.getCaseWidth(), m.getCaseHeight());
		panelResponses.add(btnRespD, 3);
	}

	public boolean switchToSelected(String q) {
		GameModel m = controller.getModel();
		ArrayList<Reponse> v = m.getQuestions().get(m.getQuestionNb() - 1).getReponses();

		if(q.equals("A")) {
			panelResponses.remove(0);
			btnRespA = new Button("reponse_select_a.png", 5 * m.getCaseWidth(), m.getCaseHeight());
			setRepA(v.get(0).getIntitule(), true);
			panelResponses.add(btnRespA, 0);
		}
		else if(q.equals("B")) {
			panelResponses.remove(1);
			btnRespB = new Button("reponse_select_b.png", 5 * m.getCaseWidth(), m.getCaseHeight());
			setRepB(v.get(1).getIntitule(), true);
			panelResponses.add(btnRespB, 1);
		}
		else if(q.equals("C")) {
			panelResponses.remove(2);
			btnRespC = new Button("reponse_select_c.png", 5 * m.getCaseWidth(), m.getCaseHeight());
			setRepC(v.get(2).getIntitule(), true);
			panelResponses.add(btnRespC, 2);
		}
		else if(q.equals("D")) {
			panelResponses.remove(3);
			btnRespD = new Button("reponse_select_d.png", 5 * m.getCaseWidth(), m.getCaseHeight());
			setRepD(v.get(3).getIntitule(), true);
			panelResponses.add(btnRespD, 3);
		}
		this.validate();
		setResponsesListeners(false, false, false, false);
		return true;

	}
	
	public boolean switchToGood(String g) {
		GameModel m = controller.getModel();
		ArrayList<Reponse> v = m.getQuestions().get(m.getQuestionNb() - 1).getReponses();

		if(g.equals("A")) {
			panelResponses.remove(0);
			btnRespA = new Button("reponse_juste_a.png", 5 * m.getCaseWidth(), m.getCaseHeight());
			setRepA(v.get(0).getIntitule(), true);
			panelResponses.add(btnRespA, 0);
		}
		else if(g.equals("B")) {
			panelResponses.remove(1);
			btnRespB = new Button("reponse_juste_b.png", 5 * m.getCaseWidth(), m.getCaseHeight());
			setRepB(v.get(1).getIntitule(), true);
			panelResponses.add(btnRespB, 1);
		}
		else if(g.equals("C")) {
			panelResponses.remove(2);
			btnRespC = new Button("reponse_juste_c.png", 5 * m.getCaseWidth(), m.getCaseHeight());
			setRepC(v.get(2).getIntitule(), true);
			panelResponses.add(btnRespC, 2);
		}
		else if(g.equals("D")) {
			panelResponses.remove(3);
			btnRespD = new Button("reponse_juste_d.png", 5 * m.getCaseWidth(), m.getCaseHeight());
			setRepD(v.get(3).getIntitule(), true);
			panelResponses.add(btnRespD, 3);
		}
		this.validate();
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

	public int showAskFinalAnswer(String string) {
		Dialog.confirmDialog(this, "Dernier mot ?", true, string);
		return (Dialog.reponse) ? 1 : 0;
	}
	
	public void useJoker5050() {
		btn5050.removeActionListener(btn5050.getActionListeners()[0]);
		panelJokers.remove(1);
		btn5050 = new Button("joker_5050_utilise.png", 5 * controller.getModel().getCaseWidth(), controller.getModel().getCaseHeight());
		panelJokers.add(btn5050, 1);
		controller.getModel().setIs5050(true);
	}
	
	public void useJokerPublic() {
		btnPublic.removeActionListener(btnPublic.getActionListeners()[0]);
		panelJokers.remove(3);
		btnPublic = new Button("joker_public_utilise.png", 5 * controller.getModel().getCaseWidth(), controller.getModel().getCaseHeight());
		panelJokers.add(btnPublic, 3);
	}
	
	public void useJokerCoupDeFil() {
		btnCoupDeFil.removeActionListener(btnCoupDeFil.getActionListeners()[0]);
		panelJokers.remove(2);
		btnCoupDeFil = new Button("joker_appel_utilise.png", 5 * controller.getModel().getCaseWidth(), controller.getModel().getCaseHeight());
		panelJokers.add(btnCoupDeFil, 2);
	}
	
	public void changeBtnSwitchToUse() {
		btnSwitch = new Button("joker_switch_utilise.png", controller.getModel().getCaseWidth(), controller.getModel().getCaseHeight());
		btnSwitch.validate();
		//btnSwitch.removeActionListener(btnSwitch.getActionListeners()[0]);
		((JPanel)panelTop.getComponent(0)).remove(4);
		((JPanel)panelTop.getComponent(0)).add(btnSwitch, 4);
		panelTop.validate();
	}
	
}
