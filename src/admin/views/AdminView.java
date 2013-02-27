package admin.views;

import general_views.Button;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import admin.controllers.AdminQuestionController;
import admin.models.AdminQuestionsModel;
import admin.models.AdminResponsesModel;
import admin.views.AdminObservable;

public class AdminView extends JFrame implements AdminObservable {

	private JTable questionsTable;

	private AdminQuestionController controller;
	
	private Button btnAddQuestion;
	private Button btnDeleteQuestion;
	
	private JTextField jQuestion;
	private JTextField jR1;
	private JTextField jR2;
	private JTextField jR3;
	private JTextField jR4;
	private JButton btnVal;
	
	private JComboBox box;

	public AdminView() {
		this.setTitle("Gestion des questions");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.controller = new AdminQuestionController(this);

		buildGUI();

		this.controller.fillQuestionsTable();

		this.setSize(1000, 520);

		resizeQuestionsTable();
		questionsTable.getSelectionModel().addListSelectionListener(controller);
		questionsTable.getSelectionModel().removeSelectionInterval(0,questionsTable.getModel().getRowCount());

		this.setResizable(false);
		this.setVisible(true);
	}

	/*
	 * Construction de tout l'interface de "l'accueil" de l'admin
	 */
	public void buildGUI() {
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));

		JPanel pQuestions = this.buildQuestionsPanel();

		JPanel pForm = this.buildFormPanel();
		pForm.setPreferredSize(new Dimension(1000, 150));

		JPanel pTop = this.buildTopPanel();
		pTop.setPreferredSize(new Dimension(1000, 30));

		p.add(pTop);
		p.add(pQuestions);
		p.add(pForm);

		this.add(p);
	}

	/*
	 * Construction du panel qui comporte la liste de tous les boutons
	 */
	public JPanel buildTopPanel() {
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.LINE_AXIS));

		JPanel left = new JPanel();
		box = new JComboBox();
		box.addItemListener(controller);

		box.addItem("Facile");
		box.addItem("Moyen");
		box.addItem("Difficile");
		left.add(new JLabel("Niveau : "));
		left.add(box);
		
		JPanel right = new JPanel();
		right.setPreferredSize(new Dimension(700, 20));
		
		JLabel lbl = new JLabel("Administration des questions/réponses du jeu");
		lbl.setFont(new Font("Arial", Font.ITALIC, 17));
		right.add(lbl);

		p.add(left);
		p.add(right);

		return p;
	}
	
	public JPanel buildFormPanel() {
		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createTitledBorder("Gestion de la question")));
		p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
		
		jQuestion = new JTextField();
		
		jR1 = new JTextField();
		jR2 = new JTextField();
		jR3 = new JTextField();
		jR4 = new JTextField();

		JPanel pReponses1 = new JPanel();
		pReponses1.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 15));
		pReponses1.setLayout(new BoxLayout(pReponses1, BoxLayout.LINE_AXIS));
		
		JPanel pReponse2 = new JPanel();
		pReponse2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
		pReponse2.setLayout(new BoxLayout(pReponse2, BoxLayout.LINE_AXIS));

		JLabel lblA = new JLabel("A."), lblB = new JLabel("B."), lblC = new JLabel("C."), lblD = new JLabel("D.");
		lblA.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
		lblB.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
		lblC.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
		lblD.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
		
		pReponses1.add(lblA);
		pReponses1.add(jR1);
		
		pReponses1.add(lblB);
		pReponses1.add(jR2);
		
		pReponse2.add(lblC);
		pReponse2.add(jR3);

		pReponse2.add(lblD);
		pReponse2.add(jR4);

		p.add(jQuestion);
		p.add(pReponses1);
		p.add(pReponse2);

		btnVal = new JButton("Valider");
		p.add(btnVal);
		return p;
	}

	/*
	 * Construction du panel qui contient la liste des questions par niveau
	 */
	public JPanel buildQuestionsPanel() {
		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createTitledBorder("Questions saisies")));
		p.setLayout(new BoxLayout(p, BoxLayout.LINE_AXIS));
		
		questionsTable = new JTable();

		JScrollPane scrollPane = new JScrollPane(questionsTable);
		scrollPane.setPreferredSize(new Dimension(830, 400));

		GridLayout g = new GridLayout(7, 1);
		g.setVgap(5);
		JPanel btnQuestions = new JPanel(g);
		btnQuestions.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 5));

		btnAddQuestion = new Button("addQuestion.png", 190, 34);
		btnAddQuestion.setActionCommand("add_question");
		btnAddQuestion.addActionListener(controller);
		
		btnDeleteQuestion = new Button("deleteQuestion.png", 216, 34);
		btnDeleteQuestion.setActionCommand("delete_question");
		btnDeleteQuestion.addActionListener(controller);
		btnDeleteQuestion.setVisible(false);

		btnQuestions.add(new JPanel());
		btnQuestions.add(new JPanel());
		btnQuestions.add(btnAddQuestion);
		btnQuestions.add(btnDeleteQuestion);
		btnQuestions.add(new JPanel());
		btnQuestions.add(new JPanel());


		p.add(scrollPane);
		p.add(btnQuestions);
		return p;

	}

	/*
	 * Mise à la bonne taille du tableau de la liste des questions
	 */
	public void resizeQuestionsTable() {
		questionsTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		questionsTable.getColumnModel().getColumn(0).setPreferredWidth(850);
		questionsTable.getSelectionModel().addSelectionInterval(0, 0);
	}

	public JTable getQuestionsTable() {
		return questionsTable;
	}

	public void setQuestionsTable(JTable questionsTable) {
		this.questionsTable = questionsTable;
	}

	public JComboBox getBox() {
		return box;
	}

	public JTextField getjQuestion() {
		return jQuestion;
	}

	public JTextField getjR1() {
		return jR1;
	}

	public JTextField getjR2() {
		return jR2;
	}

	public JTextField getjR3() {
		return jR3;
	}

	public JTextField getjR4() {
		return jR4;
	}
	
	public JButton getBtnVal() {
		return btnVal;
	}

	public Button getBtnDeleteQuestion() {
		return btnDeleteQuestion;
	}

	@Override
	public void fillQuestions(AdminQuestionsModel model) {
		this.questionsTable.setModel(model);
		this.resizeQuestionsTable();
	}

	public int deleteConfirm() {
		return JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment supprimer cette question ?", "Confirmation", JOptionPane.YES_NO_OPTION);
	}
}
