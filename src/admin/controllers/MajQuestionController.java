package admin.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import models.dao.QuestionDAO;
import models.metier.Question;

import admin.views.MajQuestionView;


public class MajQuestionController implements ActionListener {
	private MajQuestionView view;
	private int id_question;
	
	public MajQuestionController(MajQuestionView v) {
		view = v;
	}
	
	public MajQuestionController(MajQuestionView v, int nb, boolean isAdd) {
		this(v);
		
		// Si c'est un ajout
		if(isAdd) {
			this.id_question = 0;
			fillAddForm(nb);
		}
		else {
			this.id_question = nb;
			fillEditForm(nb);
		}
	}

	private void fillEditForm(int id_question) {
		Question q = QuestionDAO.getInstance().findOneById(id_question);
		if(q != null) {
			this.view.getIntitule().setText(q.getIntitule());
			this.view.getBox().setSelectedIndex(q.getNiveau() - 1);
		}
	}

	private void fillAddForm(int niveau) {
		this.view.getBox().setSelectedIndex(niveau - 1);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		
		if(actionCommand.equals("close")) {
			this.view.dispose();
		}
		
		else if(actionCommand.equals("validation")) {
			String intitulle = this.view.getIntitule().getText();
			
			if(intitulle.length() > 0) {
				int niveau = this.view.getBox().getSelectedIndex() + 1;
				Question q = new Question(id_question, intitulle, null, niveau);
	
				QuestionDAO.getInstance().save(q);
				this.view.dispose();
			}
			else
				view.showErrorEmptyInput();
		}
	}

}
