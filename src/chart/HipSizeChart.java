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

public class HipSizeChart extends ApplicationFrame {
	private static final long serialVersionUID = 1L;

	private User user;
	private int month;
	private String monthName;
	private int year;
	private int nbError;
	
	public HipSizeChart(String title, int month, int year, User user) {
		super(title);
		this.user = user;
		this.month = month;
		this.year = year;
		nbError = 0;
	}
	

	private XYDataset createDataset() {
		
		XYSeries hipSizeSeries = new XYSeries("Tour de hanche");
		Profile profile = user.getProfile();
		
		int nbPractices = profile.getPhysicalDataList().size();
		int currentMonth, currentYear, i=nbPractices-1;

		Calendar cal = Calendar.getInstance();

		if (!profile.getPhysicalDataList().isEmpty()){
			cal.setTime(profile.getPhysicalDataList().get(i).getMeasureDate());
			currentMonth = cal.get(Calendar.MONTH)+1;
			currentYear = cal.get(Calendar.YEAR);	

			monthName = DataUtility.convertMonth(month);
			hipSizeSeries.add(0.9, null);
			hipSizeSeries.add(31.1, null);
			
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
						hipSizeSeries.add(cal.get(Calendar.DAY_OF_MONTH), profile.getPhysicalDataList().get(i).getHipSize());
						System.out.println("date = "+cal.get(Calendar.DAY_OF_MONTH)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.YEAR)+" poids = "+profile.getPhysicalDataList().get(i).getHipSize());
					}
					i--;
				}while (currentMonth==month && currentYear==year && i>=0);
			}
		}
		
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(hipSizeSeries);

		return dataset;

	}

	private JFreeChart createChart(XYDataset dataset) {
		return ChartFactory.createXYLineChart("Courbe de tour de hanche", monthName, "cm", dataset, PlotOrientation.VERTICAL, true, true, false);
	}	

	
	
	public ChartPanel showHipSizePanel(){
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
