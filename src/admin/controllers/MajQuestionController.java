package admin.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.dao.QuestionDAO;
import models.metier.Question;

import admin.views.MajQuestionView;


public class MajQuestionController implements ActionListener {
	private MajQuestionView view;
	private int id_question;
	
	public MajQuestionController(MajQuestionView v) {
		view = v;
	}
	
	public MajQuestionController(MajQuestionView v, int id_question) {
		this(v);
		this.id_question = id_question;

		fillForm(id_question);
	}

	private void fillForm(int id_question) {
		Question q = QuestionDAO.getInstance().findOneById(id_question);
		if(q != null) {
			this.view.getIntitule().setText(q.getIntitule());
			this.view.getBox().setSelectedIndex(q.getNiveau() - 1);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		
		if(actionCommand.equals("close")) {
			this.view.dispose();
		}
		
		else if(actionCommand.equals("validation")) {
			String intitulle = this.view.getIntitule().getText();
			int niveau = this.view.getBox().getSelectedIndex() + 1;
			Question q = new Question(id_question, intitulle, null, niveau);

			QuestionDAO.getInstance().save(q);
			this.view.dispose();
		}
	}

}
