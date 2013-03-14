package admin.controllers;

import admin.models.StatisticsModel;
import admin.views.StatisticsView;

public class StatisticsController {

	private StatisticsView view;
	private StatisticsModel statModel;

	public StatisticsController(StatisticsView v) {
		view = v;
	}

}
