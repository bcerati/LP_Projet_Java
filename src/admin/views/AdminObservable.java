package admin.views;

import admin.models.AdminQuestionModel;
import admin.models.AdminReponseModel;

public interface AdminObservable {

	public void fillQuestion(AdminQuestionModel model);
	public void fillReponse(AdminReponseModel model);
}
