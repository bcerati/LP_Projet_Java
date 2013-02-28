package admin.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import models.dao.QuestionDAO;
import models.dao.ReponseDAO;
import models.metier.Question;
import models.metier.Reponse;

import admin.models.AdminQuestionsModel;
import admin.models.AdminResponsesModel;
import admin.views.AdminView;

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
	
		// On a appuyé sur le bouton "ajouter une question"
		if(actionCommand.equals("add_question")) {
			view.getQuestionsTable().getSelectionModel().removeSelectionInterval(0,view.getQuestionsTable().getModel().getRowCount());
			view.getBtnDeleteQuestion().setVisible(false);
			view.getRadioGroup().clearSelection();
		}

		// On a validé l'ajout (ou la modification)
		else if(actionCommand.equals("addOrEdit")) {

			// Si tous les champs sont remplis
			if(view.getjQuestion().getText().length() * view.getjR1().getText().length() * view.getjR2().getText().length() * view.getjR3().getText().length() * view.getjR4().getText().length() > 0) {
				int id_question_autoselect = 0, niveau_select = 0; // pour savoir quelle question on devrait sélectionner à la fin de l'action (plutôt que de revenir tout en haut de la liste ...)

				// Modification de la question
				if(view.getBtnVal().getText().equals("Modifier la question")) {
					int row = view.getQuestionsTable().getSelectedRow();
					Question q = questionsModel.getData().get(row);
					Reponse r1 = q.getReponses().get(0), r2 = q.getReponses().get(1), r3 = q.getReponses().get(2), r4 = q.getReponses().get(3);

					q.setIntitule(view.getjQuestion().getText());
					q.setNiveau(view.getBoxEdit().getSelectedIndex() + 1);

					r1.setIntitule(view.getjR1().getText());
					r1.setJuste(view.getJuste1().isSelected());
					
					r2.setIntitule(view.getjR2().getText());
					r2.setJuste(view.getJuste2().isSelected());

					r3.setIntitule(view.getjR3().getText());
					r3.setJuste(view.getJuste3().isSelected());

					r4.setIntitule(view.getjR4().getText());
					r4.setJuste(view.getJuste4().isSelected());
					
					QuestionDAO.getInstance().save(q);
					id_question_autoselect = q.getId();
					niveau_select = view.getBoxEdit().getSelectedIndex() + 1;
				}
				
				// Ajout d'une nouvelle question
				else {
					Question q = new Question();
					Reponse r1 = new Reponse(), r2 = new Reponse(), r3 = new Reponse(), r4 = new Reponse();

					q.setIntitule(view.getjQuestion().getText());
					q.setNiveau(view.getBoxEdit().getSelectedIndex() + 1);
					ArrayList<Reponse> reponses = new ArrayList<Reponse>();
					reponses.add(r1);
					reponses.add(r2);
					reponses.add(r3);
					reponses.add(r4);
					q.setReponses(reponses);
					
					r1.setIntitule(view.getjR1().getText());
					r1.setJuste(view.getJuste1().isSelected());
					
					r2.setIntitule(view.getjR2().getText());
					r2.setJuste(view.getJuste2().isSelected());

					r3.setIntitule(view.getjR3().getText());
					r3.setJuste(view.getJuste3().isSelected());

					r4.setIntitule(view.getjR4().getText());
					r4.setJuste(view.getJuste4().isSelected());
					
					id_question_autoselect = QuestionDAO.getInstance().save(q);
					niveau_select = view.getBoxEdit().getSelectedIndex() + 1;
				}

				// On met à jour les données avec cette petite technique (jouer sur les évenements)
				this.view.getBox().setSelectedIndex(0);
				this.view.getBox().setSelectedIndex(1);
				this.view.getBox().setSelectedIndex(niveau_select - 1);

				// Sélection de la bonne ligne dans les données
				for(int i = 0 ; i < questionsModel.getData().size() ; i++) {
					if(questionsModel.getData().get(i).getId() == id_question_autoselect) {
						view.getQuestionsTable().getSelectionModel().removeSelectionInterval(0, questionsModel.getData().size());
						view.getQuestionsTable().getSelectionModel().addSelectionInterval(i, i);
					}
				}
				
			}
			else
				JOptionPane.showMessageDialog(view, "Vous devez remplir tous les champs !", "Erreur", JOptionPane.ERROR_MESSAGE, null);

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
			
			view.getBoxEdit().setSelectedIndex(niveau_regarde - 1);
			
			view.getjR1().setText(q.getReponses().get(0).getIntitule());
			view.getJuste1().setSelected((q.getReponses().get(0).isJuste()) ? true : false);

			view.getjR2().setText(q.getReponses().get(1).getIntitule());
			view.getJuste2().setSelected((q.getReponses().get(1).isJuste()) ? true : false);

			view.getjR3().setText(q.getReponses().get(2).getIntitule());
			view.getJuste3().setSelected((q.getReponses().get(2).isJuste()) ? true : false);

			view.getjR4().setText(q.getReponses().get(3).getIntitule());
			view.getJuste4().setSelected((q.getReponses().get(3).isJuste()) ? true : false);

			view.getBtnVal().setText("Modifier la question");
			view.getBtnDeleteQuestion().setVisible(true);
			view.getBtnAddQuestion().setVisible(true);

		}
		else {
			view.getjQuestion().setText("");
			view.getBoxEdit().setSelectedIndex(niveau_regarde - 1);

			view.getjR1().setText("");
			view.getjR2().setText("");
			view.getjR3().setText("");
			view.getjR4().setText("");
			view.getBtnAddQuestion().setVisible(false);
			view.getBtnDeleteQuestion().setVisible(false);

			view.getBtnVal().setText("Ajouter la nouvelle question");
		}

	}
}
