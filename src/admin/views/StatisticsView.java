package admin.views;

import java.util.Iterator;
import java.util.Set;

import javax.swing.JFrame;

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
	       String valeur = Double.toString(model.getData().get(cle));
	       double cle2 = (double)Integer.parseInt(cle);

		    dataset.setValue((int)cle2 + "€ (" + valeur + "%)", cle2);
	    }
	    
	    JFreeChart chart = ChartFactory.createPieChart(title, dataset, true, true, false);

	    ChartFrame frame = new ChartFrame("NAME", chart);
	    frame.pack();
	    frame.setVisible(true);
	}

	public static void questionStats(StatisticsModel model, String intitule) {
		System.out.println("TODO");
	}
	
}
