package models.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import models.metier.Question;
import models.metier.Reponse;

public class ReponseDAO {

	private static ReponseDAO instance = null;
	
	public static ReponseDAO getInstance() {

		if (ReponseDAO.instance == null)
			ReponseDAO.instance = new ReponseDAO();

		return ReponseDAO.instance;
	}
	
	private ReponseDAO() {

	}

	public Vector<Reponse> findALlByQuestion(int q) {
		Vector<Reponse> v = new Vector<Reponse>();

		Connection co = (Connection)ConnexionMySQL.getInstance().getConnexion();
		String reqQuestions = "SELECT * FROM reponse r INNER JOIN appartient_reponse a ON r.id_reponse = a.id_reponse WHERE a.id_question=" + q + " ORDER BY r.id_reponse";

		Statement st = null;
		ResultSet res= null;

		try {
			st = (Statement) co.createStatement();
			res = st.executeQuery(reqQuestions);

			while (res.next()) {				
				v.add(new Reponse(res.getInt("r.id_reponse"), res.getString("r.intitule"), res.getBoolean("is_juste")));
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
		
		return v;
	}

}
