package models.metier;

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
    private Reponse reponse_juste;
    private Reponse reponse1;
    private Reponse reponse2;
    private Reponse reponse3;
    private Reponse reponse4;
    private int niveau;
    
    /*
     * Constructeur sans paramêtre - initialise tout à null ou 0 pour les int
     */
    public Question(){
        id = 0;
        intitule = null;
        reponse_juste = null;
        reponse1 = null;
        reponse2 = null;
        reponse3 = null; 
        reponse4 = null;
        niveau = 0;
    }
    
    /*
     * Constructeur à 7 paramêtres
     * @param : int, String, 5x Reponse
     */
    public Question(int identifiant, String i, Reponse r_j, Reponse r1, Reponse r2, Reponse r3, Reponse r4, int niv){
        id = identifiant;
        intitule = i;
        reponse_juste = r_j;
        reponse1 = r1;
        reponse2 = r2;
        reponse3 = r3;
        reponse4 = r4;
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
    
    /*
     * Getter de la réponse juste
     * @return : Reponse
     */
    public Reponse getReponseJuste(){
        return reponse_juste;
    }
    
    /*
     * Setter de la réponse juste
     * @param : Reponse
     */
    public void setReponseJuste(Reponse rj){
        reponse_juste = rj;
    }
    
    /*
     * Getter de la réponse 1
     * @return : Reponse
     */
    public Reponse getReponseUn(){
        return reponse1;
    }
    
    /*
     * Setter de la réponse 1
     * @param : Reponse
     */
    public void setReponseUn(Reponse r1){
        reponse1 = r1;
    }

    /*
     * Getter de la réponse 2
     * @return : Reponse
     */    
    public Reponse getReponseDeux(){
        return reponse2;
    }

    /*
     * Setter de la réponse 2
     * @param : Reponse
     */    
    public void setReponseDeux(Reponse r2){
        reponse2 = r2;
    }

    /*
     * Getter de la réponse 3
     * @return : Reponse
     */    
    public Reponse getReponseTrois(){
        return reponse3;
    }
    
    /*
     * Setter de la réponse 3
     * @param : Reponse
     */    
    public void setReponseTrois(Reponse r3){
        reponse3 = r3;
    }
    
    /*
     * Getter de la réponse 4
     * @return : Reponse
     */    
    public Reponse getReponseQuatre(){
        return reponse4;
    }
    
    /*
     * Setter de la réponse 4
     * @param : Reponse
     */    
    public void setReponseQuatre(Reponse r4){
        reponse4 = r4;
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
