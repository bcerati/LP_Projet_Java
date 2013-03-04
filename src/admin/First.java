package admin;

import java.io.File;
import java.io.IOException;

import  org.jfree.chart.ChartFactory;
import  org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import  org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import  org.jfree.data.general.DefaultPieDataset;
/**
* A simple introduction to using JFreeChart.
*/
public class First {
 
/**
* The starting point for the demo.
*
* @param  args ignored.
*/
 
	public static void main(String[] args) {
	    // Create a simple Bar chart
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    dataset.setValue(25, "Profit", "Jane");
	    dataset.setValue(50, "Profit", "Tom");
	    dataset.setValue(2, "Profit", "Jill");
	    dataset.setValue(23, "Profit", "John");
	    JFreeChart chart = ChartFactory.createBarChart("Comparison between Salesman", "Salesman", "Profit", dataset, PlotOrientation.VERTICAL, false, true, false);
	    
	    ChartFrame frame=new ChartFrame("First",chart);
	    frame.pack();
	    frame.setVisible(true);
	}

 
}