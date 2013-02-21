package admin.controllers;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import models.dao.QuestionDAO;
import models.dao.ReponseDAO;
import models.metier.Question;

import admin.models.AdminQuestionsModel;
import admin.models.AdminResponsesModel;
import admin.views.AdminView;

public class AdminQuestionController implements ItemListener, MouseListener {

	private AdminView view;
	private AdminQuestionsModel questionsModel;
	private AdminResponsesModel responsesModel;
	private int niveau_regarde;

	public AdminQuestionController(AdminView v) {
		view = v;
		niveau_regarde = 1;
	}

	/*
	 * Récupération des questions + remplissage de la JTable
	 */
	public void fillQuestionsTable() {
		this.questionsModel = new AdminQuestionsModel();

		view.fillQuestions(questionsModel);
		questionsModel.setData(QuestionDAO.getInstance().findALlByNiveau(niveau_regarde));
		questionsModel.fireTableDataChanged();

		if(questionsModel.getData() != null && questionsModel.getData().size() > 0)
			fillReponsesTable(questionsModel.getData().get(0).getId());
		else
			fillReponsesTable(0);
		
		view.resizeQuestionsTable();
		view.resizeResponsesTable();
	}

	/*
	 * Récupération des questions + remplissage de la JTable
	 */
	public void fillReponsesTable(int id_question) {
		this.responsesModel = new AdminResponsesModel();

		view.fillResponses(responsesModel);
		responsesModel.setData(ReponseDAO.getInstance().findALlByQuestion(id_question));
		responsesModel.fireTableDataChanged();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {

			int state = e.getStateChange();
			
			if(state == ItemEvent.SELECTED) {
			Object item = e.getItem();
			
			if(item.equals("Facile"))
				niveau_regarde = 1;
			else if(item.equals("Moyen"))
				niveau_regarde = 2;
			else
				niveau_regarde = 3;

			System.out.println(niveau_regarde);
			this.fillQuestionsTable();
			}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Vector<Question> q = (((AdminQuestionsModel) (view.getQuestionsTable().getModel())).getData());
		fillReponsesTable(q.get(view.getQuestionsTable().getSelectedRow()).getId());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}
}
