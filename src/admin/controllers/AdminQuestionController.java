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
		
		view.resizeQuestionsTable();
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
			}
		
	}

	public AdminQuestionsModel getQuestionsModel() {
		return questionsModel;
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
	
		if(actionCommand.equals("add_question")) {

			view.getQuestionsTable().getSelectionModel().removeSelectionInterval(0,view.getQuestionsTable().getModel().getRowCount());
			view.getBtnDeleteQuestion().setVisible(false);
		}
	
		else if(actionCommand.equals("edit_question")) {
			int id_edit = questionsModel.getData().get(this.view.getQuestionsTable().getSelectedRow()).getId();
			new MajQuestionView(id_edit, false);			

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
				}
			}
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		Vector<Question> qList = (((AdminQuestionsModel) (view.getQuestionsTable().getModel())).getData());

		if(qList.size() > 0 && view.getQuestionsTable().getSelectedRow() >= 0) {
			Question q = qList.get(view.getQuestionsTable().getSelectedRow());
	
			view.getjQuestion().setText(q.getIntitule());
			view.getjR1().setText(q.getReponses().get(0).getIntitule());
			view.getjR2().setText(q.getReponses().get(1).getIntitule());
			view.getjR3().setText(q.getReponses().get(2).getIntitule());
			view.getjR4().setText(q.getReponses().get(3).getIntitule());
			view.getBtnVal().setText("Modifier la question");
			view.getBtnDeleteQuestion().setVisible(true);

		}
		else {
			view.getjQuestion().setText("");
			view.getjR1().setText("");
			view.getjR2().setText("");
			view.getjR3().setText("");
			view.getjR4().setText("");
			view.getBtnVal().setText("Ajouter la nouvelle question");
		}
	}
}
