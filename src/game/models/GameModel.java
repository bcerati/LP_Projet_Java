package game.models;

import java.util.Vector;

import models.metier.Joueur;
import models.metier.Question;

public class GameModel {

	/*
	 * Ces deux variables contient la valeur par défaut de la largeur et hauteur d'une case (pour coïncider avec les images du jeu
	 */
	private int caseWidth;
	private int caseHeight;

	private Joueur joueur;
	
	private int currentLevel;
	
	private Vector<Question> questions;
	private int questionNb; // nième question posée dans le niveau (de 1 à 6 (avec le switch))
	private int questionNbPyramid; // nième question posée
	
	private int finalAnswer = -1;

	private boolean isCoupDeFil;
	private boolean is5050;
	private boolean isAmis;
	private boolean isSwitch;
	
	public static final int PALIER1 = 1500;
	public static final int PALIER2 = 48000;
	public static final int PALIER3 = 1000000;

	// Temps d'attente
	public static final int SUSPENS = 3000; // 3000
	public static final int WRONG = 3000; // 3000
	public static final int NEW_QUESTION = 3000; // 3000
	public static final int CHANGE_LEVEL1 = 9000; // 9000
	public static final int CHANGE_LEVEL2 = 5000; // 9000
	public static final int CHANGE_LEVEL3 = 12000; // 12000

	public GameModel(int w, int h) {
		currentLevel = 1;
		setCaseWidth(w);
		setCaseHeight(h);
		questionNb = 1;
		questionNbPyramid = 1;
		isCoupDeFil = false;
		is5050 = false;
		isAmis = false;
		isSwitch = false;
	}

	public GameModel() {
		this(100, 65);
	}

	public int getCaseWidth() {
		return caseWidth;
	}

	public void setCaseWidth(int caseWidth) {
		this.caseWidth = caseWidth;
	}

	public int getCaseHeight() {
		return caseHeight;
	}

	public void setCaseHeight(int caseHeight) {
		this.caseHeight = caseHeight;
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

	public int getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(int current_level) {
		this.currentLevel = current_level;
	}

	public Vector<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Vector<Question> questions) {
		this.questions = questions;
	}

	public int getQuestionNb() {
		return questionNb;
	}

	public void setQuestionNb(int questionNb) {
		this.questionNb = questionNb;
	}

	public int getQuestionNbPyramid() {
		return questionNbPyramid;
	}

	public void setQuestionNbPyramid(int questionNbPyramid) {
		this.questionNbPyramid = questionNbPyramid;
	}

	public boolean isCoupDeFil() {
		return isCoupDeFil;
	}

	public void setCoupDeFil(boolean isCoupDeFil) {
		this.isCoupDeFil = isCoupDeFil;
	}

	public boolean isIs5050() {
		return is5050;
	}

	public void setIs5050(boolean is5050) {
		this.is5050 = is5050;
	}

	public boolean isAmis() {
		return isAmis;
	}

	public void setAmis(boolean isAmis) {
		this.isAmis = isAmis;
	}

	public boolean isSwitch() {
		return isSwitch;
	}

	public void setSwitch(boolean isSwitch) {
		this.isSwitch = isSwitch;
	}

	public int getFinalAnswer() {
		return finalAnswer;
	}

	public void setFinalAnswer(int finalAnswer) {
		this.finalAnswer = finalAnswer;
	}

}
