package game.views;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import game.controllers.JokerPublicController;
import game.models.GameModel;

import javax.swing.JFrame;

import models.metier.Question;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class JokerPublicView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JokerPublicController jokerPublicController;
	private GameModel model;
	private ChartFrame frame;

	public JokerPublicView() {
		frame = new ChartFrame("", null);
	}
	
	public JokerPublicView(GameModel model) {
		setModel(model);
		jokerPublicController = new JokerPublicController(this, model);
		
		createChart();
	}
	
	public JokerPublicView(GameModel model, ArrayList<Integer> reponsesEnlevees) {
		setModel(model);
		jokerPublicController = new JokerPublicController(this, model, reponsesEnlevees);
		
		createChart();
	}
	
	public JokerPublicController getController() {
		return jokerPublicController;
	}
	
	public GameModel getModel() {
		return model;
	}
	
	public void setModel(GameModel model) {
		this.model = model;
	}
	
	public ChartFrame getFrame() {
		return frame;
	}
	
	public void setFrame(ChartFrame frame) {
		this.frame = frame;
	}
	
	public void createChart() {
		Question currentQuestion = model.getQuestions().get(model.getQuestionNb() - 1);
		ArrayList<Integer> listePourcentages = jokerPublicController.getListePourcentages(currentQuestion.getId());
		
	    // Create a simple Bar chart
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    if (jokerPublicController.getReponsesEnlevees().size() > 0) {
	    	if (jokerPublicController.getReponsesEnlevees().get(0) != 0 && jokerPublicController.getReponsesEnlevees().get(1) != 0) {
	    		dataset.setValue(listePourcentages.get(0), "Pourcentage", "A ("+listePourcentages.get(0)+"%)");
	    	}
	    	if (jokerPublicController.getReponsesEnlevees().get(0) != 1 && jokerPublicController.getReponsesEnlevees().get(1) != 1) {
	    		dataset.setValue(listePourcentages.get(1), "Pourcentage", "B ("+listePourcentages.get(1)+"%)");
	    	}
	    	if (jokerPublicController.getReponsesEnlevees().get(0) != 2 && jokerPublicController.getReponsesEnlevees().get(1) != 2) {
	    		dataset.setValue(listePourcentages.get(2), "Pourcentage", "C ("+listePourcentages.get(2)+"%)");
	    	}
	    	if (jokerPublicController.getReponsesEnlevees().get(0) != 3 && jokerPublicController.getReponsesEnlevees().get(1) != 3) {
	    		dataset.setValue(listePourcentages.get(3), "Pourcentage", "D ("+listePourcentages.get(3)+"%)");
	    	}
	    }
	    else {
		    dataset.setValue(listePourcentages.get(0), "Pourcentage", "A ("+listePourcentages.get(0)+"%)");
		    dataset.setValue(listePourcentages.get(1), "Pourcentage", "B ("+listePourcentages.get(1)+"%)");
		    dataset.setValue(listePourcentages.get(2), "Pourcentage", "C ("+listePourcentages.get(2)+"%)");
		    dataset.setValue(listePourcentages.get(3), "Pourcentage", "D ("+listePourcentages.get(3)+"%)");
	    }
	    JFreeChart chart = ChartFactory.createBarChart(currentQuestion.getIntitule(), "Reponses", "Pourcentages", dataset, PlotOrientation.VERTICAL, false, true, false);
	    
	    
	    CategoryPlot topPlot = chart.getCategoryPlot();
        NumberAxis topAxis = (NumberAxis) topPlot.getRangeAxis();
        topAxis.setLowerBound(0);
        topAxis.setUpperBound(100);
        
	    chart.setBackgroundPaint(Color.BLACK);
	    chart.getTitle().setPaint(Color.WHITE);
	    
	    CategoryPlot plot = chart.getCategoryPlot();
	    plot.setRangeGridlinePaint(Color.decode("#f37800"));
	    plot.setBackgroundPaint(Color.WHITE);
	    BarRenderer barRenderer = (BarRenderer)plot.getRenderer();
	    barRenderer.setSeriesPaint(0, Color.decode("#1b1867"));
	    // Axe des X :
	    CategoryAxis xAxis = plot.getDomainAxis();
	    xAxis.setTickLabelPaint(Color.decode("#f37800"));
	    xAxis.setLabelPaint(Color.WHITE);
	    // Axe des Y :
	    NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
	    yAxis.setTickLabelPaint(Color.decode("#f37800"));
	    yAxis.setLabelPaint(Color.WHITE);
	    
	    
	    frame = new ChartFrame("Joker - Vote du public", chart);
	    frame.setPreferredSize(new Dimension(400, 300));
	    frame.setResizable(false);
	    frame.pack();
	    frame.setVisible(true);
	}
}
