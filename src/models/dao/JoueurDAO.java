package models.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import models.metier.Joueur;
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

	public int save(Joueur j) {

		// Nouveau joueur
		if(j.getId() == 0) {
			int id = -1;

			// Ajout d'un nouveau joueur
			Connection co = (Connection)ConnexionMySQL.getInstance().getConnexion();
	
			PreparedStatement st = null;
			ResultSet rs = null;

			try {
				st = (PreparedStatement) co.prepareStatement("INSERT INTO joueur(id_joueur, nom) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
				st.setInt(1, j.getId());
				st.setString(2, j.getNom());
				st.executeUpdate();
				rs = st.getGeneratedKeys();

				while(rs.next()) {
					id = rs.getInt(1);
				}
				return id;
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
			return -1;
		}

		// UPDATE
		else {
			return -1;
		}
	}

	public int getNbJoueurs() {
		
		Connection co = (Connection)ConnexionMySQL.getInstance().getConnexion();
		String reqNb = "SELECT COUNT(*) AS nbScore FROM joueur_score";

		Statement st = null;
		ResultSet res= null;
		int nb = 0;

		try {
			st = (Statement) co.createStatement();
			res = st.executeQuery(reqNb);
			res.next();
			nb = res.getInt("nbScore");
			
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

	public boolean addScore(int id, int somme) {

		// Ajout d'un nouveau score de joueur
		Connection co = (Connection)ConnexionMySQL.getInstance().getConnexion();

		PreparedStatement st = null;

		try {
			st = (PreparedStatement) co.prepareStatement("INSERT INTO joueur_score(id_joueur, date, score_final) VALUES(?, NOW(), ?)");
			st.setInt(1, id);
			st.setInt(2, somme);
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

}
