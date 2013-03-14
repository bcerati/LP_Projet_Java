package game.models;

public class JokerTelephoneModel {
	private int caseWidth;
	private int caseHeight;
	

	public JokerTelephoneModel(int w, int h) {
		setCaseWidth(w);
		setCaseHeight(h);
	}

	public JokerTelephoneModel() {
		caseHeight = 25;
		caseWidth = 50; // Pour avoir une fenêtre de 300 de largeur par 400 de hauteur
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
