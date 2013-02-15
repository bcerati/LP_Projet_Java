package admin.controllers;

import models.metier.Question;
import models.metier.Reponse;

import java.util.Vector;

import admin.models.AdminQuestionModel;
import admin.views.AdminObservable;

public class AdminQuestionController {

	private AdminObservable view;
	private AdminQuestionModel model;

	public AdminQuestionController(AdminObservable v) {
		view = v;
	}

	/*
	 * Récupération des questions + remplissage de la JTable
	 */
	public void fillQuestionsTable() {
		this.model = new AdminQuestionModel();

		Vector<Question> v = new Vector<Question>();

		Reponse r = new Reponse(1, "int");
		Question q1 = new Question(1, "Intit IntitIntit IntitIntit Intit", r, r, r, r, r, 1);
		Question q2 = new Question(2, "Intit", r, r, r, r, r, 1);
		v.add(q1);
		v.add(q2);
		v.add(q2);
		v.add(q2);
		v.add(q2);
		v.add(q2);
		v.add(q2);
		v.add(q2);
		v.add(q2);
		v.add(q2);
		v.add(q2);
		v.add(q2);
		v.add(q2);
		v.add(q2);
		v.add(q2);
		v.add(q2);
		v.add(q2);
		v.add(q2);

		view.fillQuestion(model);
		model.setData(v);
		model.fireTableDataChanged();
	}
}
