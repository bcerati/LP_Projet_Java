package game.views;

import game.controllers.JokerTelephoneController;
import game.models.GameModel;
import general_views.Button;
import general_views.Panel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.metier.Question;


public class JokerTelephoneView extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel jokerTelephonePanel;
	private JPanel panelListe;
	private JComboBox comboBoxJoueurs; 
	private Map<Integer, String> joueursRepondu;
	private Button coupDeFil;
	private JPanel panelJoueur;
	private JLabel pseudo;
	private String joueurPseudo;
	private boolean isAleatoire;
	
	private JokerTelephoneController jokerTelephoneController;

	public JokerTelephoneView() {
		
	}
	
	public JokerTelephoneView(GameModel model, GameView gameView) {
		setTitle("Joker - Coup de fil à un ami");

		jokerTelephoneController = new JokerTelephoneController(this, model, gameView);

		buildGUI();

		this.setSize(new Dimension(400, 300));
		this.setContentPane(jokerTelephonePanel);	
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public JokerTelephoneView(GameModel model, ArrayList<Integer> reponsesEnlevees, GameView gameView) {
		setTitle("Joker - Coup de fil à un ami");
		
		jokerTelephoneController = new JokerTelephoneController(this, model, reponsesEnlevees, gameView);
		
		buildGUI();

		this.setSize(new Dimension(400, 300));
		this.setContentPane(jokerTelephonePanel);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public JokerTelephoneController getController() {
		return jokerTelephoneController;
	}
	
	
	public String getComboboxSelectionString() {
		return (String) comboBoxJoueurs.getSelectedItem();
	}
	
	public int getComboboxSelectionInt() {
		return comboBoxJoueurs.getSelectedIndex();
	}
	
	public Map<Integer, String> getJoueursRepondu() {
		return joueursRepondu;
	}
	
	public void setJoueursRepondu(Map<Integer, String> mapJoueursRepondu) {
		joueursRepondu = mapJoueursRepondu;
	}
	
	public String getJoueurPseudo() {
		return joueurPseudo;
	}
	
	public void setJoueurPseudo(String pseudo) {
		joueurPseudo = pseudo;
	}
	
	public boolean getIsAleatoire() {
		return isAleatoire;
	}
	
	public void setIsAleatoire(boolean aleatoire) {
		isAleatoire = aleatoire;
	}
	
	public void buildGUI() {
		// Panel global qui contiendra tout
		jokerTelephonePanel = new JPanel();
		jokerTelephonePanel.setLayout(new BoxLayout(jokerTelephonePanel, BoxLayout.PAGE_AXIS));
	
		// On ajoute tout au panel principal (qui est en PAGE_AXIS (= de haut en bas))
		jokerTelephonePanel.add(buildListe());
		jokerTelephonePanel.add(buildBouton());
		jokerTelephonePanel.add(buildJoueur());
	}
	
	public JPanel buildListe() {
		int caseWidth = jokerTelephoneController.getModel().getCaseWidth(), caseHeight = jokerTelephoneController.getModel().getCaseHeight();
		
		JPanel panelListe = new JPanel();
		panelListe.setLayout(new GridLayout(1, 1));
		panelListe.setPreferredSize(new Dimension(4 * caseWidth, caseHeight));
		
		Question currentQuestion = jokerTelephoneController.getModel().getQuestions().get(jokerTelephoneController.getModel().getQuestionNb() - 1);
		setJoueursRepondu(models.dao.JoueurDAO.getJoueursRepondu(currentQuestion.getId()));
		
		comboBoxJoueurs = new JComboBox();
		// Si des joueurs ont déjà répondu à la question :
		if (joueursRepondu.size() > 0) {
			setIsAleatoire(false);
			
			// Si le 50/50 a été utilisé :
			if (jokerTelephoneController.getReponsesEnlevees().size() > 0) {
				ArrayList<Integer> idReponsesQuestion = models.dao.ReponseDAO.findIdReponses(currentQuestion.getId());
				ArrayList<Integer> idReponsesGardees = new ArrayList<Integer>();
				if (jokerTelephoneController.getReponsesEnlevees().get(0) != 0 && jokerTelephoneController.getReponsesEnlevees().get(1) != 0) {
					idReponsesGardees.add(idReponsesQuestion.get(0));
				}
				if (jokerTelephoneController.getReponsesEnlevees().get(0) != 1 && jokerTelephoneController.getReponsesEnlevees().get(1) != 1) {
					idReponsesGardees.add(idReponsesQuestion.get(1));
				}
				if (jokerTelephoneController.getReponsesEnlevees().get(0) != 2 && jokerTelephoneController.getReponsesEnlevees().get(1) != 2) {
					idReponsesGardees.add(idReponsesQuestion.get(2));
				}
				if (jokerTelephoneController.getReponsesEnlevees().get(0) != 3 && jokerTelephoneController.getReponsesEnlevees().get(1) != 3) {
					idReponsesGardees.add(idReponsesQuestion.get(3));
				}
				
				int idReponseGardee1 = idReponsesGardees.get(0);
				int idReponseGardee2 = idReponsesGardees.get(1);
				setJoueursRepondu(models.dao.JoueurDAO.getJoueursRepondu5050(idReponseGardee1, idReponseGardee2));
				ArrayList<String> pseudoJoueursRepondu = new ArrayList<String>(joueursRepondu.values());
				
				for (int joueur=0; joueur < pseudoJoueursRepondu.size(); joueur++) {
					comboBoxJoueurs.addItem(pseudoJoueursRepondu.get(joueur));
				}
			}
			// Sinon si le 50/50 n'a pas été utilisé :
			else {
				ArrayList<String> pseudoJoueursRepondu = new ArrayList<String>(joueursRepondu.values());
				
				for (int joueur=0; joueur < pseudoJoueursRepondu.size(); joueur++) {
					comboBoxJoueurs.addItem(pseudoJoueursRepondu.get(joueur));
				}
			}
		}
		
		// Sinon on génère de l'aléatoire :
		else {
			setIsAleatoire(true);
			for (int joueur=1; joueur < 51; joueur++) {
				comboBoxJoueurs.addItem("Joueur" + joueur);
			}
		}
		comboBoxJoueurs.addItemListener(jokerTelephoneController);
		comboBoxJoueurs.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
		comboBoxJoueurs.setBackground(Color.BLACK);
		comboBoxJoueurs.setForeground(Color.WHITE);
		comboBoxJoueurs.setEditable(true);
		comboBoxJoueurs.getEditor().getEditorComponent().setBackground(Color.decode("#FFFFFF"));
		comboBoxJoueurs.setBorder(new EmptyBorder(10, 20, 10, 20));
		comboBoxJoueurs.setSize(200, 50);
		comboBoxJoueurs.setSize(new Dimension(4 * caseWidth, caseHeight));
		panelListe.add(comboBoxJoueurs);
		
		return panelListe;
	}
	
	
	public JPanel buildBouton() {
		int caseWidth = jokerTelephoneController.getModel().getCaseWidth(), caseHeight = jokerTelephoneController.getModel().getCaseHeight();
		
		JPanel panelBouton = new JPanel();
		panelBouton.setLayout(new GridLayout(1, 1));
		panelBouton.setPreferredSize(new Dimension(4 * caseWidth, caseHeight));
		
		coupDeFil = new Button("boutonAppelAmi.png", 4 * caseWidth, caseHeight);
		coupDeFil.setActionCommand("appelerAmi");
		coupDeFil.addActionListener(jokerTelephoneController);
		coupDeFil.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
		panelBouton.add(coupDeFil);
		
		return panelBouton;
	}
	
	public JPanel buildJoueur() {
		int caseWidth = jokerTelephoneController.getModel().getCaseWidth(), caseHeight = jokerTelephoneController.getModel().getCaseHeight();
		
		panelJoueur = new JPanel(new GridLayout(1, 2));
		panelJoueur.setBackground(Color.BLACK);
		panelJoueur.setPreferredSize(new Dimension(4 * caseWidth, 2 * caseHeight));
		
		Panel photo = new Panel("joueurPhoto.png", 2 * caseWidth, 2 * caseHeight);
		panelJoueur.add(photo);
		
		setJoueurPseudo(getComboboxSelectionString());
		pseudo = new JLabel("<html><head><style>div span { font-weight: bold; color: #F37800; } div {font-size: 14px; width: 500px; margin-left: 20px; margin-right: 20px; padding-top: 5px; color: white; font-family: Trebuchet;}</style></head><body><div>" + joueurPseudo + "</div></body></html>");
		pseudo.setPreferredSize(new Dimension(2 * caseWidth, 2 * caseHeight));
		panelJoueur.add(pseudo);
		
		return panelJoueur;
	}
	
	public void majPseudo() {
		panelJoueur.remove(pseudo);
		setJoueurPseudo(getComboboxSelectionString());
		pseudo = new JLabel("<html><head><style>div span { font-weight: bold; color: #F37800; } div {font-size: 14px; width: 500px; margin-left: 20px; margin-right: 20px; padding-top: 5px; color: white; font-family: Trebuchet;}</style></head><body><div>" + joueurPseudo + "</div></body></html>");
		panelJoueur.add(pseudo);
		this.repaint();
		this.validate();
	}
}
