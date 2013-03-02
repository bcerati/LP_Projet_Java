package admin.models;

import java.util.Vector;

public class StatisticsModel {

	private Vector<Double> data;

	public StatisticsModel() {
		data = new Vector<Double>();
	}

	public Vector<Double> getData() {
		return data;
	}

	public void setData(Vector<Double> data) {
		this.data = data;
	}

}
