package game.models;

public class GameModel {

	/*
	 * Ces deux variables contient la valeur par défaut de la largeur et hauteur d'une case (pour coïncider avec les images du jeu
	 */
	private int caseWidth;
	private int caseHeight;

	private String pseudo;

	public GameModel(int w, int h) {
		setCaseWidth(w);
		setCaseHeight(h);
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

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
}
