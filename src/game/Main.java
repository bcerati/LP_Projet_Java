package game;

import models.dao.ConnexionMySQL;
import game.views.HomeView;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ConnexionMySQL co = ConnexionMySQL.getInstance();
		new HomeView();
	}

}
