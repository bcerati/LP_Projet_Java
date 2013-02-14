package admin.views;

import general_views.Button;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import admin.controllers.AdminQuestionController;
import admin.models.AdminQuestionModel;
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

	public AdminView() {
		this.setTitle("Gestion des questions");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		buildGUI();
		
		this.controller = new AdminQuestionController(this);
		this.controller.fillQuestionsTable();

		this.setSize(1000, 520);

		resizeQuestionsTable();
		columnResponsesTable();

		this.setVisible(true);
	}

	/*
	 * Construction de tout l'interface de "l'accueil" de l'admin
	 */
	public void buildGUI() {
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));

		JPanel pTop = this.buildTopPanel();
		pTop.setPreferredSize(new Dimension(1000, 80));

		JPanel pQuestions = this.buildQuestionsPanel();

		JPanel pResponse = this.buildResponsesPanel();
		pResponse.setPreferredSize(new Dimension(1000, 200));

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
		JComboBox box = new JComboBox();
		box.addItem("Facile");
		box.addItem("Moyen");
		box.addItem("Difficile");
		left.add(new JLabel("Niveau : "));
		left.add(box);
		
		JPanel right = new JPanel();
		right.setPreferredSize(new Dimension(700, 80));

		btnAddQuestion = new Button("addQuestion.png", 190, 34);
		
		btnEditQuestion = new Button("editQuestion.png", 201, 34);
		btnEditQuestion.setVisible(false);
		
		btnDeleteQuestion = new Button("deleteQuestion.png", 216, 34);
		btnDeleteQuestion.setVisible(false);
		
		btnAddResponse = new Button("addResponse.png", 205, 34);
		btnAddResponse.setVisible(false);
		
		btnEditResponse = new Button("editResponse.png", 198, 34);
		btnEditResponse.setVisible(false);
		
		btnDeleteResponse = new Button("deleteResponse.png", 213, 34);
		btnDeleteResponse.setVisible(false);
		
		right.add(btnAddQuestion);
		right.add(btnEditQuestion);
		right.add(btnDeleteQuestion);
		right.add(btnAddResponse);
		right.add(btnEditResponse);
		right.add(btnDeleteResponse);
		
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
		p.add(scrollPane);
		return p;

	}

	/*
	 * Mise à la bonne taille du tableau de la liste des questions
	 */
	private void resizeQuestionsTable() {
		questionsTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		questionsTable.getColumnModel().getColumn(0).setPreferredWidth(850);
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

		p.add(scrollPane);
		return p;
	}

	/*
	 * Mise à la bonne taille du tableau de la liste des réponses
	 */
	private void columnResponsesTable() {
		responsesTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
	}

	@Override
	public void fillQuestion(AdminQuestionModel model) {
		this.questionsTable.setModel(model);
	}
}
