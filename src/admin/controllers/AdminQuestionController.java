package admin.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import models.dao.QuestionDAO;
import models.dao.ReponseDAO;
import models.metier.Question;

import admin.models.AdminQuestionModel;
import admin.models.AdminReponseModel;
import admin.views.AdminObservable;
import admin.views.AdminView;

public class AdminQuestionController implements ItemListener, MouseListener {

	private AdminView view;
	private AdminQuestionModel model;
	private AdminReponseModel modelRep;
	private int niveau_regarde;

	public AdminQuestionController(AdminView v) {
		view = v;
		niveau_regarde = -1;
	}

	/*
	 * Récupération des questions + remplissage de la JTable
	 */
	public void fillQuestionsTable() {
		this.model = new AdminQuestionModel();

		if(niveau_regarde == -1)
			niveau_regarde = 1;

		view.fillQuestion(model);
		model.setData(QuestionDAO.getInstance().findALlByNiveau(niveau_regarde));
		model.fireTableDataChanged();

		if(model.getData() != null && model.getData().size() > 0)
			fillReponsesTable(model.getData().get(0).getId());
		else
			fillReponsesTable(0);

	}

	/*
	 * Récupération des questions + remplissage de la JTable
	 */
	public void fillReponsesTable(int id_question) {
		this.modelRep = new AdminReponseModel();

		view.fillReponse(modelRep);
		modelRep.setData(ReponseDAO.getInstance().findALlByQuestion(id_question));
		modelRep.fireTableDataChanged();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {

		if(niveau_regarde > 0) {
			Object item = e.getItem();
			
			if(item.equals("Facile"))
				niveau_regarde = 1;
			else if(item.equals("Moyen"))
				niveau_regarde = 2;
			else
				niveau_regarde = 3;

			this.fillQuestionsTable();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Vector<Question> q = (((AdminQuestionModel) (view.getQuestionsTable().getModel())).getData());
		fillReponsesTable(q.get(view.getQuestionsTable().getSelectedRow()).getId());
		
		//System.out.println(q.get(view.getQuestionsTable().getSelectedRow()).getReponses().size());
		
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
