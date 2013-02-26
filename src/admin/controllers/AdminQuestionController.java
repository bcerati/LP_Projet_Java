package admin.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import models.dao.QuestionDAO;
import models.dao.ReponseDAO;
import models.metier.Question;

import admin.models.AdminQuestionsModel;
import admin.models.AdminResponsesModel;
import admin.views.AdminView;
import admin.views.MajQuestionView;

public class AdminQuestionController implements ItemListener, MouseListener, ActionListener, ListSelectionListener {

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

			this.fillQuestionsTable();
			this.view.visibilityBtn();
			}
		
	}

	public AdminQuestionsModel getQuestionsModel() {
		return questionsModel;
	}

	public AdminResponsesModel getResponsesModel() {
		return responsesModel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

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

	@Override
	public void actionPerformed(ActionEvent e) {
	String actionCommand = e.getActionCommand();
	
		if(actionCommand.equals("edit_question")) {
			int id_edit = questionsModel.getData().get(this.view.getQuestionsTable().getSelectedRow()).getId();
			new MajQuestionView(id_edit);			

			// Jusqu'à la fin du WHILE : on va reselectionner la bonne question dans le bon niveau !
			this.view.getBox().setSelectedIndex(1);
			
			boolean go = true;
			int n = 0, real_niveau = -1, line = -1;

			// On parcourt tous les niveau (à moins qu'on a retrouvé la bonne question avant)
			while(go && n < 3) {
				this.view.getBox().setSelectedIndex(n);

				for(int i = 0 ; i < questionsModel.getData().size() ; i++) {
					
					if(questionsModel.getData().get(i).getId() == id_edit) {
						real_niveau = n;
						line = i;
						go = false;
					}
				}
				n++;
			}
			this.view.getBox().setSelectedIndex(real_niveau);
			this.view.getQuestionsTable().getSelectionModel().setSelectionInterval(line, line);
		}

		else if(actionCommand.equals("delete_question")) {
			
			if(view.deleteConfirm() == JOptionPane.YES_OPTION) {
				Question q = QuestionDAO.getInstance().findOneById(questionsModel.getData().get(this.view.getQuestionsTable().getSelectedRow()).getId());
				
				if(q != null) {
					QuestionDAO.getInstance().delete(q);
					this.questionsModel.getData().remove(this.view.getQuestionsTable().getSelectedRow());
					int actu = niveau_regarde;
					this.view.getBox().setSelectedIndex(niveau_regarde % 2);
					this.view.getBox().setSelectedIndex(actu - 1);
					
					this.view.visibilityBtn();
				}
			}
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		Vector<Question> q = (((AdminQuestionsModel) (view.getQuestionsTable().getModel())).getData());
		
		try {
		if(q.size() > 0)
			fillReponsesTable(q.get(view.getQuestionsTable().getSelectedRow()).getId());
		} catch(Exception exc) {
			
		}
	}
}
