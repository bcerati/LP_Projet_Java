package game.models;

public class HomeModel {

	/*
	 * Ces deux variables contient la valeur par défaut de la largeur et hauteur d'une case (pour coïncider avec les images du jeu
	 */
	private int caseWidth;
	private int caseHeight;

	public HomeModel(int w, int h) {
		setCaseWidth(w);
		setCaseHeight(h);
	}

	public HomeModel() {
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

}
