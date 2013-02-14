package metier;
/**
 *
 * @author
 * Niko
 */
public class Reponse {
    private int id;
    private Question question;
    private String intitule;
    
    public Reponse(){
        id = 0;
        question = null;
        intitule = null;
    }
    
    public Reponse(int i, String in){
        id = i;
        intitule = in;
    }
    
    public int getId(){
        return id;
    }
    
    public void setId(int i){
        id = i;
    }
    
    public Question getQuestion(){
        return question;
    }
    
    public void setQuestion(Question q){
        question = q;
    }
    
    public String getIntitule(){
        return intitule;
    }
    
    public void setIntitule(String s){
        intitule = s;
    }
}
