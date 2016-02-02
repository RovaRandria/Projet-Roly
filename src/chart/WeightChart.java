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
import org.jfree.ui.RefineryUtilities;

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
		XYDataset dataset = createDataset();
		JFreeChart chart = createChart(dataset);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		setContentPane(chartPanel);
		
		
		
		
		
//		 XYPlot xyPlot = (XYPlot) jfreechart.getPlot();
//	        xyPlot.setDomainCrosshairVisible(true);
//	        xyPlot.setRangeCrosshairVisible(true);
//	        XYItemRenderer renderer = xyPlot.getRenderer();
//	        renderer.setSeriesPaint(0, Color.blue);
//	        NumberAxis domain = (NumberAxis) xyPlot.getDomainAxis();
//	        domain.setRange(0.00, 1.00);
//	        domain.setTickUnit(new NumberTickUnit(0.1));
//	        domain.setVerticalTickLabels(true);
//	        NumberAxis range = (NumberAxis) xyPlot.getRangeAxis();
//	        range.setRange(0.0, 1.0);
//	        range.setTickUnit(new NumberTickUnit(0.1));
//	        return new ChartPanel(jfreechart);
		
		
		
		
		
		
		
		this.pack();
		RefineryUtilities.centerFrameOnScreen(this);
		this.setVisible(true);
	}

	private XYDataset createDataset() {
		XYSeries weightSeries = new XYSeries("Weight");
		Profile profile = user.getProfile();
		
		int nbPractices = profile.getPhysicalDataList().size();
		int currentMonth, currentYear, i=nbPractices;

		Calendar cal = Calendar.getInstance();
		if (!profile.getPhysicalDataList().isEmpty()){
			cal.setTime(profile.getPhysicalDataList().get(i-1).getMeasureDate());
			currentMonth = cal.get(Calendar.MONTH)+1;
			currentYear = cal.get(Calendar.YEAR);	
			while ((currentMonth!=month || currentYear!=year) && i>0){	
				if (currentYear > year){
					i--;
					cal.setTime(profile.getPhysicalDataList().get(i).getMeasureDate());
					currentYear = cal.get(Calendar.YEAR);
				}else{
					if (currentYear < year){
						i++;
						cal.setTime(profile.getPhysicalDataList().get(i).getMeasureDate());
						currentYear = cal.get(Calendar.YEAR);
					}
					else{
						if (currentMonth > month){
							i--;
							cal.setTime(profile.getPhysicalDataList().get(i).getMeasureDate());
							currentMonth = cal.get(Calendar.MONTH)+1;
						}else{
							if (currentMonth < month){
								i++;
								cal.setTime(profile.getPhysicalDataList().get(i).getMeasureDate());
								currentMonth = cal.get(Calendar.MONTH)+1;
							}
						}
					}
				}
			}
				

			if (i>=0){
				monthName = user.getProfile().getPhysicalDataList().get(i-1).convertMonth(month);		
				cal.setTime(profile.getPhysicalDataList().get(i-1).getMeasureDate());
				while (currentMonth==month && currentYear==year && i>=0){
//					System.out.println("-------------------");
//					System.out.println("i = "+i);
//					System.out.println("jour = "+cal.get(Calendar.DAY_OF_MONTH));
//					System.out.println("valeur poids = "+profile.getPhysicalDataList().get(i-1).getWeight());
					weightSeries.add(cal.get(Calendar.DAY_OF_MONTH), profile.getPhysicalDataList().get(i-1).getWeight());
					cal.setTime(profile.getPhysicalDataList().get(i-1).getMeasureDate());
					currentMonth = cal.get(Calendar.MONTH)+1;
					currentYear = cal.get(Calendar.YEAR);
				
					i--;
				}
			}
			else{
				System.out.println("l'indice i est inférieur à 0.");
			}
			
		}
		else{
			System.out.println("Aucune donnée physique n'a été renseignée.");
		}


//		XYSeries series2 = new XYSeries("Second");
//		series2.add(1.0, 5.0);
//		series2.add(2.0, 7.0);
//		series2.add(3.0, 6.0);
//		series2.add(4.0, 8.0);
//		series2.add(5.0, 4.0);
//		series2.add(6.0, 4.0);
//		series2.add(7.0, 2.0);
//		series2.add(8.0, 1.0);
//
//		XYSeries series3 = new XYSeries("Third");
//		series3.add(3.0, 4.0);
//		series3.add(4.0, 3.0);
//		series3.add(5.0, 2.0);
//		series3.add(6.0, 3.0);
//		series3.add(7.0, 6.0);
//		series3.add(8.0, 3.0);
//		series3.add(9.0, 4.0);
//		series3.add(10.0, 3.0);

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(weightSeries);

		return dataset;

	}

	private JFreeChart createChart(XYDataset dataset) {
		
		return ChartFactory.createXYLineChart("Courbe de poids", monthName, "kilogrammes", dataset, PlotOrientation.VERTICAL, true, true, false);
	}	

}
