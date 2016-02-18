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

import data.Profile;
import data.User;

public class WaistSizeChart extends ApplicationFrame {
	private static final long serialVersionUID = 1L;

	private User user;
	private int month;
	private String monthName;
	private int year;
	private int nbError;
	
	public WaistSizeChart(String title, int month, int year, User user) {
		super(title);
		this.user = user;
		this.month = month;
		this.year = year;
		nbError = 0;
	}
	

	private XYDataset createDataset() {
		
		XYSeries waistSizeSeries = new XYSeries("Tour de taille");
		Profile profile = user.getProfile();
		
		int nbPractices = profile.getPhysicalDataList().size();
		int currentMonth, currentYear, i=nbPractices-1;

		Calendar cal = Calendar.getInstance();

		if (!profile.getPhysicalDataList().isEmpty()){
			cal.setTime(profile.getPhysicalDataList().get(i).getMeasureDate());
			currentMonth = cal.get(Calendar.MONTH)+1;
			currentYear = cal.get(Calendar.YEAR);	
			while ((currentMonth!=month || currentYear!=year) && i>0 && i<nbPractices){	
				if (currentYear > year){
					i--;
					cal.setTime(profile.getPhysicalDataList().get(i).getMeasureDate());
					currentYear = cal.get(Calendar.YEAR);
				}else{
					if (currentYear < year){
						i++;
						if (i<nbPractices){
							cal.setTime(profile.getPhysicalDataList().get(i).getMeasureDate());
							currentYear = cal.get(Calendar.YEAR);
						}
						else{
							nbError = 2;
						}
					}
					else{
						if (currentMonth > month){
							i--;
							cal.setTime(profile.getPhysicalDataList().get(i).getMeasureDate());
							currentMonth = cal.get(Calendar.MONTH)+1;
						}else{
							if (currentMonth < month){
								i++;
								if (i<nbPractices){
									cal.setTime(profile.getPhysicalDataList().get(i).getMeasureDate());
									currentMonth = cal.get(Calendar.MONTH)+1;
								}
								else{
									nbError = 2;
								}
							}
						}
					}
				}
			}

			if (i>=0 && i<nbPractices){
				monthName = user.getProfile().getPhysicalDataList().get(i).convertMonth(month);		
//				waistSizeSeries.add(0, null);
//				waistSizeSeries.add(32, null);
				do {
					cal.setTime(profile.getPhysicalDataList().get(i).getMeasureDate());
					currentMonth = cal.get(Calendar.MONTH)+1;
					currentYear = cal.get(Calendar.YEAR);
					if (currentMonth==month && currentYear==year){
						waistSizeSeries.add(cal.get(Calendar.DAY_OF_MONTH), profile.getPhysicalDataList().get(i).getWaistSize());
						System.out.println("date = "+cal.get(Calendar.DAY_OF_MONTH)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.YEAR)+" tour de taille = "+profile.getPhysicalDataList().get(i).getWaistSize());
					}
					i--;
				}while (currentMonth==month && currentYear==year && i>=0);
			}
		}
		else{
			nbError = 1;
		}

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(waistSizeSeries);

		return dataset;

	}

	private JFreeChart createChart(XYDataset dataset) {
		return ChartFactory.createXYLineChart("Courbe de tour de taille", monthName, "cm", dataset, PlotOrientation.VERTICAL, true, true, false);
	}	

	
	public ChartPanel showWaistSizePanel(){
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
