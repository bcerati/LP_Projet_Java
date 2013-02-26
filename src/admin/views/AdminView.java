package admin.views;

import general_views.Button;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import admin.controllers.AdminQuestionController;
import admin.models.AdminQuestionsModel;
import admin.models.AdminResponsesModel;
import admin.views.AdminObservable;

public class AdminView extends JFrame implements AdminObservable {

	private JTable questionsTable;
	private JTable responsesTable;

	private AdminQuestionController controller;
	
	private Button btnAddQuestion;
	private Button btnEditQuestion;
	private Button btnDeleteQuestion;
	private Button btnAddResponse;	
	private Button btnDeleteResponse;
	private Button btnEditResponse;
	
	private JComboBox box;

	public AdminView() {
		this.setTitle("Gestion des questions");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.controller = new AdminQuestionController(this);

		buildGUI();

		this.controller.fillQuestionsTable();

		this.setSize(1000, 520);

		resizeQuestionsTable();
		columnResponsesTable();
		questionsTable.getSelectionModel().addListSelectionListener(controller);

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

		JPanel pResponse = this.buildResponsesPanel();
		pResponse.setPreferredSize(new Dimension(1000, 200));

		JPanel pTop = this.buildTopPanel();
		pTop.setPreferredSize(new Dimension(1000, 30));

		p.add(pTop);
		p.add(pQuestions);
		p.add(pResponse);

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

		btnEditQuestion = new Button("editQuestion.png", 201, 34);
		btnEditQuestion.setActionCommand("edit_question");
		btnEditQuestion.addActionListener(controller);
		
		btnDeleteQuestion = new Button("deleteQuestion.png", 216, 34);
		btnDeleteQuestion.setActionCommand("delete_question");
		btnDeleteQuestion.addActionListener(controller);

		btnQuestions.add(new JPanel());
		btnQuestions.add(new JPanel());
		btnQuestions.add(btnAddQuestion);
		btnQuestions.add(btnEditQuestion);
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

	/*
	 * Mise à la bonne taille du tableau de la liste des réponses
	 */
	public void resizeResponsesTable() {
		responsesTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		responsesTable.getColumnModel().getColumn(0).setPreferredWidth(850);
		responsesTable.getSelectionModel().addSelectionInterval(0, 0);
	}

	/*
	 * Construction du panel qui contient la liste des réponses associées à une question
	 */
	public JPanel buildResponsesPanel() {
		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createTitledBorder("Réponses associées")));
		p.setLayout(new BoxLayout(p, BoxLayout.LINE_AXIS));
		
		responsesTable = new JTable();
		responsesTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scrollPane = new JScrollPane(responsesTable);
		scrollPane.setPreferredSize(new Dimension(830, 190));
		
		GridLayout g = new GridLayout(3, 1);
		g.setVgap(5);
		JPanel btnResponses = new JPanel(g);
		btnResponses.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 5));

		btnAddResponse = new Button("addResponse.png", 205, 34);
		btnEditResponse = new Button("editResponse.png", 198, 34);
		btnDeleteResponse = new Button("deleteResponse.png", 213, 34);

		btnResponses.add(btnAddResponse);
		btnResponses.add(btnEditResponse);
		btnResponses.add(btnDeleteResponse);

		p.add(scrollPane);
		p.add(btnResponses);
		return p;
	}

	/*
	 * Mise à la bonne taille du tableau de la liste des réponses
	 */
	private void columnResponsesTable() {
		responsesTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
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

	@Override
	public void fillQuestions(AdminQuestionsModel model) {
		this.questionsTable.setModel(model);
		this.resizeQuestionsTable();
	}

	@Override
	public void fillResponses(AdminResponsesModel model) {
		this.responsesTable.setModel(model);
		this.resizeResponsesTable();
	}

	public void visibilityBtn() {

		if(this.controller.getQuestionsModel().getData().size() == 0) {
			this.btnDeleteQuestion.setVisible(false);
			this.btnEditQuestion.setVisible(false);

			this.btnAddResponse.setVisible(false);
			this.btnEditResponse.setVisible(false);
			this.btnDeleteResponse.setVisible(false);
		}
		else {
			this.btnDeleteQuestion.setVisible(true);
			this.btnEditQuestion.setVisible(true);

			this.btnAddResponse.setVisible(true);
			this.btnEditResponse.setVisible(true);
			this.btnDeleteResponse.setVisible(true);
		}
	}
	
	public int deleteConfirm() {
		return JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment supprimer cette question ?", "Confirmation", JOptionPane.YES_NO_OPTION);
	}
}
