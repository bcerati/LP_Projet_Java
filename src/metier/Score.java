package metier;

import org.joda.time.DateTime;
/**
 * @author
 * Niko
 */
public class Score {

    /*
     * Déclaration des variables
     */
    private Joueur joueur;
    private int score;
    private DateTime date;

    /*
     * Constructeur avec 3 paramêtres
     * @param : joueur, int, datetime
     */
    public Score (Joueur j, int s, DateTime d) {
        joueur = j;
        score = s;
        date = d;
    }
    
    /*
     * Constructeur avec 2 paramêtres
     * @param : joueur, int
     */
    public Score (Joueur j, int s){
        joueur = j;
        score = s;
        date = null;
    }
    
    /*
     * Setter du score
     * @param : int
     */
    public void setScore(int s){
        score = s;
    }
    
    /*
     * Getter du score
     * @return : score
     */
    public int getScore(){
        return score;
    }
    
    /*
     * Setter du joueur
     * @param : joueur
     */
    public void setJoueur(Joueur j){
        joueur = j;
    }
    
    /*
     * Getter du joueur
     * @return : joueur
     */
    public Joueur getJoueur(){
        return joueur;
    }
    
    /*
     * Setter de la date
     */
    public void setDate(){
        date = DateTime.now();
    }
    
    /*
     * Getter de la date
     * @return : datetime
     */
    public DateTime getDate(){
        return date;
    }
    
    /*
     * Fonction qui affiche le nom + score + date
     */
    public void afficherScore(){
        System.out.println("Le joueur " + joueur.getNom() + " a réalisé un score de " + score + " le " + date.toString("E MM/dd/yyyy"));
    }
    
    /*
     * TEST
     */
    public static void main(String[] args){
        Joueur j = new Joueur (1,"niko");
        Score s = new Score(j, 10);
        s.setDate();
        s.afficherScore();
    }
}


