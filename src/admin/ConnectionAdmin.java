/**
 * Connexion à mySQL
 * 
 * @author AS
 * 
 */

package admin;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;


public class ConnectionAdmin {

	private static ConnectionAdmin instance = null;
	private Properties proprietes;

	private ConnectionAdmin() {
		this.chargePassword();
	}
	
	public boolean isPasswordCorrect(String psw) {
		return this.proprietes.getProperty("psw").equals(psw);
	}

	public static ConnectionAdmin getInstance() {
		return (instance == null) ? new ConnectionAdmin() : instance;
	}

	private void chargePassword() {

		ClassLoader cl = this.getClass().getClassLoader();
		URL url = cl.getResource("config/psw.properties");
		this.proprietes = new Properties();

		try {
			this.proprietes.loadFromXML(url.openStream());
		} catch (IOException ioe) {
			System.out.println("Problème à la lecture du fichier de password !");
			System.out.println(ioe.getMessage());
		}
	}
}
