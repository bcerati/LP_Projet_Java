package admin.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import models.dao.QuestionDAO;
import models.dao.ReponseDAO;
import models.dao.SommeDAO;
import models.metier.Question;
import models.metier.Reponse;

import admin.models.AdminQuestionsModel;
import admin.models.AdminResponsesModel;
import admin.models.StatisticsModel;
import admin.views.AdminView;
import admin.views.StatisticsView;

public class AdminQuestionController implements ItemListener, ActionListener, ListSelectionListener {

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

	@Override
	public void actionPerformed(ActionEvent e) {
	String actionCommand = e.getActionCommand();
	
		if(actionCommand.equals("Quitter"))
			System.exit(0);
		
		if(actionCommand.equals("statsSums")) {
			StatisticsModel model = new StatisticsModel();
			model.setData(SommeDAO.getInstance().getSommesStat());
			StatisticsView.sumStats(model, "Pourcentage des joueurs par somme");
		}

		if(actionCommand.equals("statsQuestions")) {
			Question q = questionsModel.getData().get(view.getQuestionsTable().getSelectedRow());
			StatisticsModel model = new StatisticsModel();
			model.setData(SommeDAO.getInstance().getQuestionStat(q));
			StatisticsView.questionStats(model, q.getIntitule());
		}

		// On a appuyé sur le bouton "ajouter une question"
		if(actionCommand.equals("add_question")) {
			view.getQuestionsTable().getSelectionModel().removeSelectionInterval(0,view.getQuestionsTable().getModel().getRowCount());
			view.getBtnDeleteQuestion().setVisible(false);
			view.getBtnStatsQuestion().setVisible(false);
			view.getRadioGroup().clearSelection();
		}

		// On a validé l'ajout (ou la modification) d'une question
		else if(actionCommand.equals("addOrEdit")) {
			String strQ = view.getjQuestion().getText(), strR1 = view.getjR1().getText(), strR2 = view.getjR2().getText(), strR3 = view.getjR3().getText(), strR4 = view.getjR4().getText();

			// Si tous les champs sont remplis
			if(strQ.length() * strR1.length() * strR2.length() * strR3.length() * strR4.length() > 0 && (view.getRadioJuste1().isSelected() || view.getRadioJuste2().isSelected() || view.getRadioJuste3().isSelected() || view.getRadioJuste4().isSelected())) {
				ArrayList<String> tmp = new ArrayList<String>();
				tmp.add(strR1);
				tmp.add(strR2);
				tmp.add(strR3);
				tmp.add(strR4);
				Set<String> tmpSet = new TreeSet<String>();
				tmpSet.addAll(tmp);

				// Si il n'y a pas deux réponses identiques
				if(tmpSet.size() == 4) {
				
				int id_question_autoselect = 0, niveau_select = 0; // pour savoir quelle question on devra sélectionner à la fin de l'action (plutôt que de revenir tout en haut de la liste ...)

				// Modification de la question
				if(view.getBtnValidGestion().getText().equals("Modifier la question")) {
					Question q = questionsModel.getData().get(view.getQuestionsTable().getSelectedRow());
					Reponse r1 = q.getReponses().get(0), r2 = q.getReponses().get(1), r3 = q.getReponses().get(2), r4 = q.getReponses().get(3);

					q.setIntitule(strQ);
					q.setNiveau(view.getBoxEdit().getSelectedIndex() + 1);

					r1.setIntitule(strR1);
					r1.setJuste(view.getRadioJuste1().isSelected());
					
					r2.setIntitule(strR2);
					r2.setJuste(view.getRadioJuste2().isSelected());

					r3.setIntitule(strR3);
					r3.setJuste(view.getRadioJuste3().isSelected());

					r4.setIntitule(strR4);
					r4.setJuste(view.getRadioJuste4().isSelected());
					
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
					r1.setJuste(view.getRadioJuste1().isSelected());

					r2.setIntitule(view.getjR2().getText());
					r2.setJuste(view.getRadioJuste2().isSelected());

					r3.setIntitule(view.getjR3().getText());
					r3.setJuste(view.getRadioJuste3().isSelected());

					r4.setIntitule(view.getjR4().getText());
					r4.setJuste(view.getRadioJuste4().isSelected());

					id_question_autoselect = QuestionDAO.getInstance().save(q);
					niveau_select = view.getBoxEdit().getSelectedIndex() + 1;
				}

				// On met à jour les données avec cette petite technique (jouer sur les évenements)
				this.view.getBoxNiveaux().setSelectedIndex(0);
				this.view.getBoxNiveaux().setSelectedIndex(1);
				this.view.getBoxNiveaux().setSelectedIndex(niveau_select - 1);

				// Sélection de la bonne ligne dans les données
				for(int i = 0 ; i < questionsModel.getData().size() ; i++) {
					if(questionsModel.getData().get(i).getId() == id_question_autoselect) {
						view.getQuestionsTable().getSelectionModel().removeSelectionInterval(0, questionsModel.getData().size());
						view.getQuestionsTable().getSelectionModel().addSelectionInterval(i, i);
					}
				}
			}
			else
				JOptionPane.showMessageDialog(view, "Il ne peut pas y avoir deux réponses identiques !", "Erreur", JOptionPane.ERROR_MESSAGE, null);
		}
			else
				JOptionPane.showMessageDialog(view, "Vous devez remplir tous les champs !", "Erreur", JOptionPane.ERROR_MESSAGE, null);
		}

		else if(actionCommand.equals("delete_question")) {

			if(view.deleteConfirm() == JOptionPane.YES_OPTION) {
				Question q = QuestionDAO.getInstance().findOneById(questionsModel.getData().get(this.view.getQuestionsTable().getSelectedRow()).getId());

				if(q != null) {
					
					if(!QuestionDAO.getInstance().isAlreadyAsked(q)) {
						QuestionDAO.getInstance().delete(q);
						this.questionsModel.getData().remove(this.view.getQuestionsTable().getSelectedRow());
						int actu = niveau_regarde;
						this.view.getBoxNiveaux().setSelectedIndex(niveau_regarde % 2);
						this.view.getBoxNiveaux().setSelectedIndex(actu - 1);
					}
					else
						JOptionPane.showMessageDialog(view, "Question déjà posée, vous ne pouvez plus la supprimer !", "Erreur", JOptionPane.ERROR_MESSAGE, null);
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
			view.getRadioJuste1().setSelected((q.getReponses().get(0).isJuste()) ? true : false);

			view.getjR2().setText(q.getReponses().get(1).getIntitule());
			view.getRadioJuste2().setSelected((q.getReponses().get(1).isJuste()) ? true : false);

			view.getjR3().setText(q.getReponses().get(2).getIntitule());
			view.getRadioJuste3().setSelected((q.getReponses().get(2).isJuste()) ? true : false);

			view.getjR4().setText(q.getReponses().get(3).getIntitule());
			view.getRadioJuste4().setSelected((q.getReponses().get(3).isJuste()) ? true : false);

			view.getBtnValidGestion().setText("Modifier la question");
			
			if(!QuestionDAO.getInstance().isAlreadyAsked(q)) {
				view.getBtnDeleteQuestion().setVisible(true);
				view.getBtnStatsQuestion().setVisible(false);
			}
			else {
				view.getBtnDeleteQuestion().setVisible(false);
				view.getBtnStatsQuestion().setVisible(true);
			}
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
			view.getRadioGroup().clearSelection();
			view.getBtnValidGestion().setText("Ajouter la nouvelle question");
		}
	}
}
