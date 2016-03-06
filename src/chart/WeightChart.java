package chart;

import java.util.Calendar;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

import utils.DataUtility;

import data.Profile;
import data.User;

public class WeightChart extends ApplicationFrame {
	private static final long serialVersionUID = 1L;

	private User user;
	private int month;
	private String monthName;
	private int year;
	private int nbError;
	
	public WeightChart(String title, int month, int year, User user) {
		super(title);
		this.user = user;
		this.month = month;
		this.year = year;
		nbError = 0;
	}
	

	private XYDataset createDataset() {
		
		XYSeries weightSeries = new XYSeries("Poids");
		Profile profile = user.getProfile();
		
		int nbPractices = profile.getPhysicalDataList().size();
		int currentMonth, currentYear, i=nbPractices-1;

		Calendar cal = Calendar.getInstance();

		monthName = DataUtility.convertMonth(month);
		weightSeries.add(0.9, null);
		weightSeries.add(31.1, null);
		if (!profile.getPhysicalDataList().isEmpty()){
			cal.setTime(profile.getPhysicalDataList().get(i).getMeasureDate());
			currentMonth = cal.get(Calendar.MONTH)+1;
			currentYear = cal.get(Calendar.YEAR);	

			while ((currentMonth!=month || currentYear!=year) && i>=0 && i<nbPractices){	
				cal.setTime(profile.getPhysicalDataList().get(i).getMeasureDate());
				currentYear = cal.get(Calendar.YEAR);
				currentMonth = cal.get(Calendar.MONTH)+1;
				
				if (currentYear > year){
					i--;
				}else{
					if (currentYear < year){
						i++;
						if (i>=nbPractices){
							nbError = 2;
						}
						else{
							nbError = 0;
						}
					}
					else{
						if (currentMonth > month){
							i--;
						}else{
							if (currentMonth < month){
								i++;
								if (i>=nbPractices){
									nbError = 2;
								}
								else{
									nbError = 0;
								}
							}
						}
					}
				}
			}
			
			if (i>=0 && i<nbPractices){		
				do {
					cal.setTime(profile.getPhysicalDataList().get(i).getMeasureDate());
					currentMonth = cal.get(Calendar.MONTH)+1;
					currentYear = cal.get(Calendar.YEAR);
					if (currentMonth==month && currentYear==year){
						weightSeries.add(cal.get(Calendar.DAY_OF_MONTH), profile.getPhysicalDataList().get(i).getWeight());
						System.out.println("Date = "+cal.get(Calendar.DAY_OF_MONTH)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.YEAR)+" -- Poids = "+profile.getPhysicalDataList().get(i).getWeight());
					}
					i--;
				}while (currentMonth==month && currentYear==year && i>=0);
			}
		}
		
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(weightSeries);

		return dataset;

	}

	private JFreeChart createChart(XYDataset dataset) {
		return ChartFactory.createXYLineChart("Courbe de poids", monthName+" "+year, "kilogrammes", dataset, PlotOrientation.VERTICAL, true, true, false);
	}	

	
	
	public ChartPanel showWeightPanel(){
		XYDataset dataset = createDataset();
		JFreeChart chart = createChart(dataset);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		return chartPanel;
	}


	public int getNbError() {
		return nbError;
	}


	public void setNbError(int nbError) {
		this.nbError = nbError;
	}
}
