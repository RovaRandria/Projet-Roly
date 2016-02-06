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

public class WeightChart extends ApplicationFrame {
	private static final long serialVersionUID = 1L;

	private User user;
	private int month;
	private String monthName;
	private int year;
	
	public WeightChart(String title, int month, int year, User user) {
		super(title);
		this.user = user;
		this.month = month;
		this.year = year;
	}
	

	private XYDataset createDataset() {
		
		XYSeries weightSeries = new XYSeries("Poids");
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
							System.out.println("Erreur à implémenter : annnée pas encore passée");
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
									System.out.println("Erreur à implémenter : mois pas encore passé");
								}
							}
						}
					}
				}
			}

			if (i>=0 && i<nbPractices){
				monthName = user.getProfile().getPhysicalDataList().get(i).convertMonth(month);		
				
				do {
					cal.setTime(profile.getPhysicalDataList().get(i).getMeasureDate());
					currentMonth = cal.get(Calendar.MONTH)+1;
					currentYear = cal.get(Calendar.YEAR);
					if (currentMonth==month && currentYear==year){
						weightSeries.add(cal.get(Calendar.DAY_OF_MONTH), profile.getPhysicalDataList().get(i).getWeight());
						System.out.println("date = "+cal.get(Calendar.DAY_OF_MONTH)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.YEAR)+" poids = "+profile.getPhysicalDataList().get(i).getWeight());
					}
					i--;
					//System.out.println("i = "+i+" currentMonth = "+currentMonth+" month = "+month+" currentYear = "+currentYear+" year = "+year);
				}while (currentMonth==month && currentYear==year && i>=0);
			}
			else{
				System.out.println("Erreur à implémenter : l'indice i est inférieur à 0 ou supérieur au nb de practice.");
			}
			
		}
		else{
			System.out.println("Aucune donnée physique n'a été renseignée.");
		}

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(weightSeries);

		return dataset;

	}

	private JFreeChart createChart(XYDataset dataset) {
		return ChartFactory.createXYLineChart("Courbe de poids", monthName, "kilogrammes", dataset, PlotOrientation.VERTICAL, true, true, false);
	}	

	
	
	public ChartPanel showWeightPanel(){
		XYDataset dataset = createDataset();
		JFreeChart chart = createChart(dataset);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		return chartPanel;
	}
}
