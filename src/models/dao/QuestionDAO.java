package models.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
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
				String reqRep = "SELECT * FROM reponse r WHERE id_question="+res.getInt("id_question");
				st2 = (Statement) co.createStatement();
				res2 = st2.executeQuery(reqRep);
				aL = new ArrayList<Reponse>();

				while(res2.next()) {
					aL.add(new Reponse(res2.getInt("id_reponse"), res2.getString("intitule"), res2.getBoolean("is_juste")));
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
	
	public Question findOneById(int id) {
		
		Connection co = (Connection)ConnexionMySQL.getInstance().getConnexion();
		String reqQuestions = "SELECT * FROM question WHERE id_question=" + id;

		Statement st = null;
		ResultSet res= null;
		Question q = null;

		try {
			st = (Statement) co.createStatement();
			res = st.executeQuery(reqQuestions);

			ArrayList<Reponse> aL = null;
			while (res.next()) {
				
				Statement st2 = null;
				ResultSet res2 = null;
				String reqRep = "SELECT * FROM reponse r WHERE id_question="+id;
				st2 = (Statement) co.createStatement();
				res2 = st2.executeQuery(reqRep);
				aL = new ArrayList<Reponse>();

				while(res2.next()) {
					aL.add(new Reponse(res2.getInt("id_reponse"), res2.getString("intitule"), res2.getBoolean("is_juste")));
				}
				st2.close();
				res2.close();

				q = new Question(res.getInt("id_question"), res.getString("intitule"), aL, res.getInt("niveau"));
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
		return q;
	}
	
	public void delete(Question q) {

		// Suppression de la question
		Connection co = (Connection)ConnexionMySQL.getInstance().getConnexion();

		Statement st = null;

		try {
			st = (Statement) co.createStatement();
			st.executeUpdate("DELETE FROM question WHERE id_question=" + q.getId());
		} catch (SQLException se) {
			System.out.println("Erreur requête SQL : " + se.getMessage());
		} finally {
			try {
				st.close();
			}
			catch (Exception e) {
				System.out.println("charge : erreur close "+e.getMessage());
			}
		}

	}
	
	public int save(Question q) {
		ReponseDAO repDAO = ReponseDAO.getInstance();

		// UPDATE
		if(q.getId() != 0) {
			Connection co = (Connection)ConnexionMySQL.getInstance().getConnexion();

			PreparedStatement st = null;

			try {
				st = (PreparedStatement) co.prepareStatement("UPDATE question SET intitule=?, niveau=? WHERE id_question=?");
				st.setString(1, q.getIntitule());
				st.setInt(2, q.getNiveau());
				st.setInt(3, q.getId());
				st.executeUpdate();

				for(Reponse r : q.getReponses()) {
					repDAO.save(r, q.getId());
				}
				
			} catch (SQLException se) {
				System.out.println("Erreur requête SQL : " + se.getMessage());
			} finally {
				try {
					st.close();
				}
				catch (Exception e) {
					System.out.println("charge : erreur close "+e.getMessage());
				}
			}
			return q.getId();
		}

		// Nouvelle question
		else {
			Connection co = (Connection)ConnexionMySQL.getInstance().getConnexion();

			PreparedStatement st = null;
			ResultSet rs = null;
			int id = 0;

			try {
				st = (PreparedStatement) co.prepareStatement("INSERT INTO question(intitule, niveau) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
				st.setString(1, q.getIntitule());
				st.setInt(2, q.getNiveau());
				st.executeUpdate();
				rs = st.getGeneratedKeys();
				
				while(rs.next()) {
					id = rs.getInt(1);
				}
				
				for(Reponse r : q.getReponses()) {
					repDAO.save(r, id);
				}

			} catch (SQLException se) {
				System.out.println("Erreur requête SQL : " + se.getMessage());
			} finally {
				try {
					st.close();
				}
				catch (Exception e) {
					System.out.println("charge : erreur close "+e.getMessage());
				}
			}
			return id;
		}
	}

}
