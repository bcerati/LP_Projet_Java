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

public class QuestionDAO {

	private static QuestionDAO instance = null;
	
	public static QuestionDAO getInstance() {

		if (QuestionDAO.instance == null)
			QuestionDAO.instance = new QuestionDAO();

		return QuestionDAO.instance;
	}
	
	private QuestionDAO() {

	}

	public Vector<Question> findALlByNiveau(int niv) {
		Vector<Question> v = new Vector<Question>();

		Connection co = (Connection)ConnexionMySQL.getInstance().getConnexion();
		String reqQuestions = "SELECT * FROM question WHERE niveau=" + niv + " ORDER BY id_question";

		Statement st = null;
		ResultSet res= null;

		try {
			st = (Statement) co.createStatement();
			res = st.executeQuery(reqQuestions);
			ArrayList<Reponse> aL = null;
			while (res.next()) {				
	
				Statement st2 = null;
				ResultSet res2 = null;
				String reqRep = "SELECT * FROM reponse r INNER JOIN appartient_reponse a ON r.id_reponse = a.id_reponse WHERE a.id_question="+res.getInt("id_question");
				st2 = (Statement) co.createStatement();
				res2 = st2.executeQuery(reqRep);
				aL = new ArrayList<Reponse>();

				while(res2.next()) {
					aL.add(new Reponse(res2.getInt("r.id_reponse"), res2.getString("r.intitule"), res2.getBoolean("a.is_juste")));
				}
				st2.close();
				res2.close();

				Question q = new Question(res.getInt("id_question"), res.getString("intitule"), aL, res.getInt("niveau"));
				v.add(q);
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
