package models.metier;
/**
 *
 * @author
 * Niko
 */
public class Reponse {
    private int id;
    private String intitule;
    private boolean isJuste;
    
    public Reponse(){
        id = 0;
        intitule = null;
    }
    
    public Reponse(int i, String in, boolean isJuste){
        id = i;
        intitule = in;
        this.isJuste = isJuste;
    }
    
    public int getId(){
        return id;
    }
    
    public void setId(int i){
        id = i;
    }

    public boolean isJuste() {
		return isJuste;
	}

	public void setJuste(boolean isJuste) {
		this.isJuste = isJuste;
	}

	public String getIntitule(){
        return intitule;
    }
    
    public void setIntitule(String s){
        intitule = s;
    }
}
