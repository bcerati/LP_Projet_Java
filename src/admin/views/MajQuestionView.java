package admin.views;

import general_views.Button;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import admin.controllers.MajQuestionController;


public class MajQuestionView extends JDialog {
	
	private MajQuestionController controller;
	
	private JTextField intitule;
	private JComboBox box;

	private Button btnValid;	
	private Button btnAbort;

	public MajQuestionView(int question_id) {
		setModal(true);

		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
		this.add(p);
		this.add(buildFieldPanel());

		controller = new MajQuestionController(this, question_id);

		btnAbort.setActionCommand("close");
		btnAbort.addActionListener(controller);

		this.pack();
		setVisible(true);
	}

	private JPanel buildFieldPanel() {

		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));

		JPanel fieldsPanel = new JPanel();
		fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.PAGE_AXIS));
		
		intitule = new JTextField();
		intitule.setBorder(new EmptyBorder(3, 0, 3, 0));

		JLabel lbl = new JLabel("Intitulé de la question : ");
		lbl.setBorder(new EmptyBorder(20, 0, 0, 0));

		fieldsPanel.add(lbl);
		fieldsPanel.add(intitule);

		box = new JComboBox();
		box.addItem("Facile");
		box.addItem("Moyen");
		box.addItem("Difficile");
		box.setBorder(new EmptyBorder(20, 0, 0, 0));
		fieldsPanel.add(box);

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBorder(new EmptyBorder(20, 0, 20, 0));

		btnValid = new Button("validation.png", 128, 128);
		
		btnAbort = new Button("abort.png", 128, 128);
		
		btnValid.setActionCommand("validation");
		btnValid.addActionListener(controller);

		buttonsPanel.add(btnValid);
		buttonsPanel.add(btnAbort);

		p.add(fieldsPanel);
		p.add(buttonsPanel);

		return p;
	}

	public JTextField getIntitule() {
		return intitule;
	}

	public void setIntitule(JTextField intitule) {
		this.intitule = intitule;
	}

	public JComboBox getBox() {
		return box;
	}

	public void setBox(JComboBox box) {
		this.box = box;
	}


}
