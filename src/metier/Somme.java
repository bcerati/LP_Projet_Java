package metier;

import java.io.IOException;

/**
 * @author
 * Niko
 */
public class Somme {

    private int valeur;
    
    /*
     * Constructeur sans paramêtre - initialise la valeur à 0
     */
    public Somme(){
        valeur = 0;
    }
    
    /*
     * Constructeur à 1 paramêtre
     * @param : int
     */
    public Somme(int s){
        valeur = s;
    }
    
    /*
     * Setter de la valeur
     * @param : int
     */
    public void setValeur(int v) throws IOException{
        if (v > -1) {
            valeur = v;
        }
        else {
            throw new IOException("La valeur doit être positive !");
        }
    }
    
    /*
     * Getter de la valeur
     * @return : int
     */
    public int getValeur(){
        return valeur;
    }

    /*
     * TEST
     */
        public static void main(String[] args) throws IOException{
            Somme s = new Somme();
            try {
                s.setValeur(-2);
            }
            catch (IOException ioe){
                System.out.println(ioe.getMessage());
            }
            System.out.println(s.getValeur());
    }
}
