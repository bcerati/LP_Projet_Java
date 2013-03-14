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

public class SommeDAO {

	private static SommeDAO instance = null;
	
	public static SommeDAO getInstance() {

		if (SommeDAO.instance == null)
			SommeDAO.instance = new SommeDAO();

		return SommeDAO.instance;
	}
	
	private SommeDAO() {

	}

	public HashMap<String, Double> getSommesStat() {
		
		Connection co = (Connection)ConnexionMySQL.getInstance().getConnexion();
		String reqSums = "" +
				"SELECT s.valeur, COUNT(j.score_final) AS nb FROM " +
				"somme s LEFT JOIN joueur_score j ON j.score_final = s.valeur " +
				"GROUP BY s.valeur ";

		Statement st = null;
		ResultSet res= null;
		HashMap<String, Double> m = new HashMap<String, Double>();
		double nb_joueurs = JoueurDAO.getInstance().getNbJoueurs();
		double d = 0, coef = 100.0 / nb_joueurs;

		try {
			st = (Statement) co.createStatement();
			res = st.executeQuery(reqSums);

			while(res.next()) {
				d = ((double)res.getInt("nb"))*coef;
				m.put(res.getString("s.valeur"), d);
			}
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
		return m;
	}
	
	public HashMap<String, Double> getQuestionStat(Question q) {
		Connection co = (Connection)ConnexionMySQL.getInstance().getConnexion();
		String reqPourcentages = "SELECT r.id_reponse, r.intitule, COUNT(*) AS nbRep FROM reponse r LEFT JOIN joueur_reponse jr ON jr.id_reponse = r.id_reponse WHERE jr.id_question="+q.getId()+" GROUP BY r.id_reponse UNION SELECT r.id_reponse, r.intitule, 0 AS nbRep FROM reponse r WHERE r.id_question="+q.getId()+" AND r.id_reponse NOT IN(SELECT id_reponse FROM joueur_reponse WHERE id_question="+q.getId()+")";

		Statement st = null;
		ResultSet res= null;
		HashMap<String, Double> m = new HashMap<String, Double>();

		try {
			st = (Statement) co.createStatement();
			res = st.executeQuery(reqPourcentages);

			while(res.next()) {
				m.put(res.getString(2), res.getDouble(3));
			}
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
		return m;
	}

}
