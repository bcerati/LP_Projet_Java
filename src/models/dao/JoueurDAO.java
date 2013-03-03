package models.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import models.metier.Question;
import models.metier.Reponse;

public class JoueurDAO {

	private static JoueurDAO instance = null;
	
	public static JoueurDAO getInstance() {

		if (JoueurDAO.instance == null)
			JoueurDAO.instance = new JoueurDAO();

		return JoueurDAO.instance;
	}
	
	private JoueurDAO() {

	}

	public int getNbJoueurs() {
		
		Connection co = (Connection)ConnexionMySQL.getInstance().getConnexion();
		String reqNb = "SELECT COUNT(*) AS nbJoueurs FROM joueur";

		Statement st = null;
		ResultSet res= null;
		int nb = 0;

		try {
			st = (Statement) co.createStatement();
			res = st.executeQuery(reqNb);
			res.next();
			nb = res.getInt("nbJoueurs");
			
		} catch (SQLException se) {
			System.out.println("Erreur requête SQL : " + se.getMessage());
		} finally {
			try {
				res.close();
				st.close();
			}
			catch (Exception e) {
				System.out.println("charge : erreur close "+e.getMessage());
			}
		}
		return nb;
	}

}
