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
		String reqQuestions = "SELECT * FROM reponse r WHERE id_question=" + q + " ORDER BY r.id_reponse";

		Statement st = null;
		ResultSet res= null;

		try {
			st = (Statement) co.createStatement();
			res = st.executeQuery(reqQuestions);

			while (res.next()) {				
				v.add(new Reponse(res.getInt("id_reponse"), res.getString("intitule"), res.getBoolean("is_juste")));
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
	
	public void save(Reponse r, int id_question) {
		
		// UPDATE
		if(r.getId() != 0) {
			
			Connection co = (Connection)ConnexionMySQL.getInstance().getConnexion();

			PreparedStatement st = null;

			try {
				st = (PreparedStatement) co.prepareStatement("UPDATE reponse SET intitule=?, is_juste=? WHERE id_reponse=?");

				st.setString(1, r.getIntitule());
				st.setBoolean(2, r.isJuste());
				st.setInt(3, r.getId());
				st.executeUpdate();

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
		
		// Ajout
		else {
			Connection co = (Connection)ConnexionMySQL.getInstance().getConnexion();

			Statement st = null;

			try {
				st = (Statement) co.createStatement();
				st.executeUpdate("INSERT INTO reponse(intitule, id_question, is_juste) VALUES('" + r.getIntitule() + "', "+ id_question +", " + r.isJuste() + ")");
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
	}

}
