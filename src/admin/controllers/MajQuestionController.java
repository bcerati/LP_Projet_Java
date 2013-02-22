package admin.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.dao.QuestionDAO;
import models.metier.Question;

import admin.views.MajQuestionView;


public class MajQuestionController implements ActionListener {
	private MajQuestionView view;
	
	public MajQuestionController(MajQuestionView v) {
		view = v;
	}
	
	public MajQuestionController(MajQuestionView v, int id_question) {
		this(v);
		
		fillForm(id_question);
	}

	private void fillForm(int id_question) {
		Question q = QuestionDAO.getInstance().findOneById(id_question);
		if(q != null) {
			this.view.getIntitule().setText(q.getIntitule());
			this.view.getBox().setSelectedIndex(q.getNiveau() - 1);
			System.out.println(q.getReponses().get(1));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		
		if(actionCommand.equals("close")) {
			this.view.dispose();
		}
	}

}
