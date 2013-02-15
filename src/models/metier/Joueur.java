package models.metier;

/**
 * @author
 * Niko
 */
public class Joueur {
    
    /*
     * Déclaration des variables
     */
    private int id;
    private String nom;
    private Score tab_score[];
    private Question tab_question[];
    
    /*
     * Constructeur à 2 paramêtres
     * @param : int, string
     */
    public Joueur(int identifiant, String n){
        id = identifiant;
        nom = n;
    }
    
    /*
     * Setter du nom
     * @param : string
     */
    public void setNom(String n){
        nom = n;
    }

    /*
     * Getter du nom
     * @return : string
     */
     public String getNom(){
        return nom;
    }
    
    /*
     * Getter de l'identifiant
     * @return : int
     */ 
    public int getId(){
        return id;
    }
    

    
    
}
