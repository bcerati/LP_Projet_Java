package models.metier;

import java.util.ArrayList;

/**
 *
 * @author
 * Niko
 */
public class Question {
    
    /*
     * Déclaration des variables
     */
    private int id;
    private String intitule;
    private int niveau;
    private ArrayList<Reponse> reponses;
    
    /*
     * Constructeur sans paramêtre - initialise tout à null ou 0 pour les int
     */
    public Question(){
        id = 0;
        intitule = null;
        reponses = new ArrayList<Reponse>();
        niveau = 0;
    }
    
    /*
     * Constructeur à 7 paramêtres
     * @param : int, String, 5x Reponse
     */
    public Question(int identifiant, String i, ArrayList<Reponse> reponses, int niv){
        id = identifiant;
        intitule = i;
        this.reponses = reponses;
        niveau = niv;
    }
    
    /*
     * Getter de l'identifiant
     * @return : int
     */
    public int getId(){
        return id;
    }
    
    /*
     * Setter de l'identifiant
     * @param : int
     */
    public void setId(int i){
        id = i;
    }
   
    /*
     * Getter de l'intitulé
     * @return : String
     */
    public String getIntitule(){
        return intitule;
    }
    
    /*
     * Setter de l'intitulé
     * @param : String
     */
    public void setIntitule(String s){
        intitule = s;
    }
    
    public ArrayList<Reponse> getReponses() {
		return reponses;
	}

	public void setReponses(ArrayList<Reponse> reponses) {
		this.reponses = reponses;
	}

	/*
     * Getter du niveau
     * @return : int
     */
    public int getNiveau(){
        return niveau;
    }
    
    /*
     * Setter du niveau
     * @param : int
     */
    public void setNiveau(int i){
        niveau = i;
    }
}
