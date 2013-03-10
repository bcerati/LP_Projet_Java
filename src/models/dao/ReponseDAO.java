package models.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import models.metier.Joueur;
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
	
	public boolean putPlayerResponse(Joueur j, Question q, Reponse r) {
		Connection co = (Connection)ConnexionMySQL.getInstance().getConnexion();

		PreparedStatement st = null;

		try {
			st = (PreparedStatement) co.prepareStatement("INSERT INTO joueur_reponse(id_joueur, id_question, id_reponse) VALUES(?, ?, ?)");
			st.setInt(1, j.getId());
			st.setInt(2, q.getId());
			st.setInt(3, r.getId());
			st.executeUpdate();
			return true;
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
		return false;
	}

	
	/**
	 * Fonction permettant de trouver les 4 ids de réponses en fonction de la question posée
	 * 
	 * @param L'id_question de la question posée
	 * @return La liste des id de réponses à la question
	 */
	public static ArrayList<Integer> findIdReponses(int id_question) {
		Connection co = (Connection)ConnexionMySQL.getInstance().getConnexion();
		String reqIdByNiveau = "SELECT id_reponse FROM reponse WHERE id_question=" + id_question;

		Statement st = null;
		ResultSet res= null;
		ArrayList<Integer> listResponses = new ArrayList<Integer>();

		try {
			st = (Statement) co.createStatement();
			res = st.executeQuery(reqIdByNiveau);
			
			while (res.next()) {
				listResponses.add(res.getInt("id_reponse")); 
			}
		}
		catch (SQLException se) {
			System.out.println("Erreur requête SQL : " + se.getMessage());
		}
		finally {
			try {
				res.close();
				st.close();
			}
			catch (Exception e) {
				System.out.println("charge : erreur close " + e.getMessage());
			}
		}
		
		return listResponses;
	}
	
	
	public static int findNbResponseByResponse(int id_question, int response_X, ArrayList<Integer> listResponses) {
		Connection co = (Connection)ConnexionMySQL.getInstance().getConnexion();
		int id_reponse_X = listResponses.get(response_X);
		String reqIdByNiveau = "SELECT * FROM joueur_reponse WHERE id_reponse=" + id_reponse_X;

		Statement st = null;
		ResultSet res= null;
		int nbResponse_X = 0;
		
		try {
			st = (Statement) co.createStatement();
			res = st.executeQuery(reqIdByNiveau);
			
			while (res.next()) {
				nbResponse_X++;
			}
		}
		catch (SQLException se) {
			System.out.println("Erreur requête SQL : " + se.getMessage());
		}
		finally {
			try {
				res.close();
				st.close();
			}
			catch (Exception e) {
				System.out.println("charge : erreur close " + e.getMessage());
			}
		}
		
		return nbResponse_X;
	}
	
	
	public static String findById(int idReponse) {
		Connection co = (Connection)ConnexionMySQL.getInstance().getConnexion();
		String reqQuestions = "SELECT intitule FROM reponse WHERE id_reponse=" + idReponse;

		Statement st = null;
		ResultSet res= null;
		String reponse = "";

		try {
			st = (Statement) co.createStatement();
			res = st.executeQuery(reqQuestions);
			res.next();
			reponse = res.getString("intitule");
			
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
		
		return reponse;
	}
	
	
	public int findResponseByIdJoueur(int idJoueur) {
		Connection co = (Connection)ConnexionMySQL.getInstance().getConnexion();
		String reqIdByNiveau = "SELECT * FROM joueur_reponse WHERE id_joueur=" + idJoueur;

		Statement st = null;
		ResultSet res= null;
		int idReponse = 0;
		
		try {
			st = (Statement) co.createStatement();
			res = st.executeQuery(reqIdByNiveau);
			res.next();
			idReponse = res.getInt("id_reponse");
		}
		catch (SQLException se) {
			System.out.println("Erreur requête SQL : " + se.getMessage());
		}
		finally {
			try {
				res.close();
				st.close();
			}
			catch (Exception e) {
				System.out.println("charge : erreur close " + e.getMessage());
			}
		}
		
		return idReponse;
	}
}
