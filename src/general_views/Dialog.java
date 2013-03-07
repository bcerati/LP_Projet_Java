package general_views;

import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Dialog {
	
	public static boolean reponse ; 
	public static String choix="" ; 
	
	private static JDialog dial;
	
	public static void confirmDialog(JFrame parent, String title, boolean modal, String message) {
		dial = new JDialog(parent, title, modal);

		
		/**
		 * Création des panels et modification de la couleur de fond
		 */
		JPanel panelText = new JPanel();
	    panelText.setBackground(new Color(33, 30, 104));

	    JPanel panelButton = new JPanel();
	    panelButton.setBackground(new Color(33, 30, 104));
	    
	    
	    /**
	     * Création des boutons
	     */
	    JButton button1 = new JButton("Oui");
	    JButton button2 = new JButton("Non");
	    
	    
	    /**
	     * On définit les actions des boutons
	     */
	    button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                reponse = true;
                dial.dispose();
            }
        });
	    
	    button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                reponse=false;
                dial.dispose();
            }
        });
	    
	    
	    /**
	     * Création du label + définition de la couleur du texte
	     */
	    JLabel label = new JLabel("<html><body><div style='width : 300px'>" + message + "</div></body></html>");
	    label.setForeground(Color.white);
	    
	    
	    /**
	     * Ajout des élément aux différents panels
	     */
	    panelText.add(label);
	    panelButton.add(button1);
	    panelButton.add(button2);
	    
	    
	    /**
	     * Ajout des panels à la fenêtre
	     */
	    dial.add(panelText, BorderLayout.CENTER);
	    dial.add(panelButton, BorderLayout.SOUTH);
	    
	    
	    /**
	     * On ajoute un keylistener sur le button1
	     */
	    button1.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent k) {
			     if (k.getKeyCode() == KeyEvent.VK_ENTER) {
		                reponse = true;
		                dial.dispose();
		          }
			     else {
			    	 System.out.println("bla");
			     }
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
	    	
	    });
	    
	    
	    /**
	     * Options de la fenêtre
	     */
	    dial.pack();
	    dial.setLocationRelativeTo(null);
	    dial.setResizable(false);
	    dial.setBackground(new Color(33, 30, 104));

	    dial.setVisible(true);
	    
	  }

	
	
	public static void messageDialog(JFrame parent, String title, boolean modal, String message){
	    dial = new JDialog(parent, title, modal);
	    
	    
	    /**
	     * Création panelText + couleur de fond
	     */
	    JPanel panelText = new JPanel();
	    panelText.setBackground(new Color(33, 30, 104));
	
	    
	    /**
	     * Création panelBouton + couleur de fond
	     */
	    JPanel panelBouton = new JPanel();
	    panelBouton.setBackground(new Color(33, 30, 104));
	    
	    
	    /**
	     * Création panel global + couleur de fond
	     */
	    JPanel windows = new JPanel();
	    windows.setLayout(new BoxLayout(windows, BoxLayout.PAGE_AXIS));
	    windows.setBackground(new Color(33, 30, 104));
	    
	    
	    /**
	     * Création du label + couleur du texte
	     */
	    JLabel label = new JLabel("<html><body><div style='width : auto'>" + message + "</div></body></html>");
	    label.setForeground(Color.white);
	    
	    
	    /**
	     * On crée le bouton 
	     */
	    JButton bouton1 = new JButton("Ok");
	    
	    
	    /**
	     * On affecte une action au bouton
	     */
	    bouton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                dial.dispose();
            }
        });

	    
	    /**
	     * On ajoute un keylistener sur le button1
	     */
	    bouton1.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent k) {
			     if (k.getKeyCode() == KeyEvent.VK_ENTER) {
		                dial.dispose();
		          }
			     else {
			    	 System.out.println("bla");
			     }
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
	    	
	    });	    
	    
	    
	    /**
	     * Ajout du label au panel
	     */
	    panelText.add(label);
	    
	    
	    /**
	     * Ajout du bouton au panel
	     */
	    panelBouton.add(bouton1);
	    
	    
	    /**
	     * Ajout du panel à la fenêtre
	     */
	    windows.add(panelText);
	    windows.add(panelBouton);
	    dial.add(windows);
	    
	    
	    /**
	     * Options de la fenêtre
	     */
	    dial.pack();
	    dial.setLocationRelativeTo(null);
	    dial.setResizable(false);
	    dial.setBackground(new Color(33, 30, 104));

	    dial.setVisible(true);
	    
	  }
	
	
	
	public static void textFieldDialog(JFrame parent, String title, boolean modal, String message){
		dial = new JDialog(parent, title, modal);
		
		
		/**
		 * Création panel général + couleur de fond
		 */
		JPanel windows = new JPanel();
	    windows.setLayout(new BoxLayout(windows, BoxLayout.PAGE_AXIS));
	    windows.setBackground(new Color(33, 30, 104));
	
	    
	    /**
	     * Création du panel de texte
	     */
	    JPanel panelText = new JPanel();
	    panelText.setBackground(new Color(33, 30, 104));
	    
	    
	    /**
	     * Création du panel de JTextField
	     */
	    JPanel panelTf = new JPanel();
	    panelTf.setBackground(new Color(33, 30, 104));
	    
	    
	    /**
	     * Création panel bouton + couleur de fond
	     */
	    JPanel panelButton = new JPanel();
	    panelButton.setBackground(new Color(33, 30, 104));
	    
	    
	    /**
	     * Création des boutons
	     */
	    JButton button1 = new JButton("Ok");
	    JButton button2 = new JButton("Annuler");
	    
	    
	    /**
	     * Création du label + couleur du texte
	     */
	    JLabel label = new JLabel("<html><body><div style=style='width : auto'>" + message + "</div></body></html>");
	    label.setForeground(Color.white);
	   
	    
	    /**
	     * Création du JTextField + taille + couleur de fond
	     */
	    final JTextField jtf = new JTextField();
	    jtf.setPreferredSize(new Dimension(150,20));

	    
	    /**
	     * On définit les actions des boutons
	     */
	    button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                choix = jtf.getText();
                dial.dispose();
            }
        });	 
	    
	    button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                choix = "";
                dial.dispose();
            }
        });


	    /**
	     * On ajoute un keyListener au JTextField
	     */
	    	jtf.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent key) {
			     if (key.getKeyCode() == KeyEvent.VK_ENTER) {
		                choix = jtf.getText();
		                dial.dispose();
		         }
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
	    	
	    });	   
	    
	    
	    /**
	     * On ajoute les boutons au panel
	     */
	    panelButton.add(button1);
	    panelButton.add(button2);
	    panelText.add(label);
	    panelTf.add(jtf);
	    
	    
	    /**
	     * On ajoute au panel global tous les éléments 
	     */
	    windows.add(panelText);
	    windows.add(panelTf);
	    windows.add(panelButton);
	    
	    
	    /**
	     * On ajoute le panel global à la fenêtre
	     */
	    dial.add(windows);
	    
	    
	    /**
	     * Options de la fenêtre
	     */
	    dial.pack();
	    dial.setLocationRelativeTo(null);
	    dial.setResizable(false);
	    dial.setBackground(new Color(33, 30, 104));
	    dial.setVisible(true);	
	}
	

}