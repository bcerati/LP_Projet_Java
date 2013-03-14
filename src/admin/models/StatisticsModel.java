package admin.models;

import java.util.HashMap;
import java.util.Map;

public class StatisticsModel {

	private Map<String, Double> data;

	public StatisticsModel() {
		data = new HashMap<String, Double>();
	}

	public Map<String, Double> getData() {
		return data;
	}

	public void setData(Map<String, Double> data) {
		this.data = data;
	}

}
