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
	
	private int current_level;
	
	private Vector<Question> questions;
	private int questionNb;
	
	private boolean isCoupDeFil;
	private boolean is5050;
	private boolean isAmis;
	private boolean isSwitch;

	public GameModel(int w, int h) {
		setCaseWidth(w);
		setCaseHeight(h);
		questionNb = 1;
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

	public int getCurrent_level() {
		return current_level;
	}

	public void setCurrent_level(int current_level) {
		this.current_level = current_level;
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
}
