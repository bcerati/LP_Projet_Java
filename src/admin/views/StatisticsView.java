package admin.views;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import admin.models.StatisticsModel;

public class StatisticsView {

	public StatisticsView() {

	}
	
	public static void sumStats(StatisticsModel model, String title) {
		DefaultPieDataset dataset = new DefaultPieDataset();
	    
	    Set<String> cles = model.getData().keySet();
	    Iterator<String> it = cles.iterator();
	    while (it.hasNext()){
	       String cle = (String) it.next();
	       double valeur = model.getData().get(cle);
	       double cle2 = (double)Integer.parseInt(cle);

		    dataset.setValue((int)cle2 + "€ (" + ((int)(valeur*100))/100. + "%)", cle2);
	    }
	    
	    JFreeChart chart = ChartFactory.createPieChart(title, dataset, true, true, false);

	    ChartFrame frame = new ChartFrame("Statistiques par sommes", chart);
	    frame.pack();
	    frame.setVisible(true);
	}

	public static void questionStats(StatisticsModel model, String intitule) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    
		Set<String> cles = model.getData().keySet();
		Iterator<String> it = cles.iterator();

		ArrayList<String> tString = new ArrayList<String>();
		ArrayList<Double> tDouble = new ArrayList<Double>();

		while (it.hasNext()){
			String cle = (String) it.next();
			tString.add(cle);
			tDouble.add(model.getData().get(cle));
		}

		int s = 0;
		for(int i = 0 ; i < tDouble.size() ; i++) {
			s += tDouble.get(i);
		}

		for(int i = 0 ; i < tString.size() ; i++) {
			dataset.setValue(tDouble.get(i)*100/s, "Pourcentage", ((int)(100*tDouble.get(i)*100/s))/100. + "% : " + tString.get(i));
		}
		
		JFreeChart chart = ChartFactory.createBarChart("Pourcentage", "Intitulé de la réponse", "Pourcentage", dataset, PlotOrientation.VERTICAL, false, true, false);

		ChartFrame frame = new ChartFrame(intitule, chart);
		frame.setSize(1000, 600);
		frame.setVisible(true);
	}
	
}
