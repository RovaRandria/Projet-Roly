
package chart;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

import utils.DataUtility;
import data.DBConnection;
import data.Practice;
import data.Profile;
import data.User;
/**
 * Create the chart Panel for the jogging performances of a user
 * @author Rova
 *
 */
public class JoggingPerformancesChart extends ApplicationFrame {
	private static final long serialVersionUID = 1L;

	private User user;
	private int month;
	private String monthName;
	private int year;
	private int nbError;

	public JoggingPerformancesChart(String title, int month, int year, User user) {
		super(title);
		this.user = user;
		this.month = month;
		this.year = year;
		nbError = 0;
	}

	/**
	 * Builds a line chart for the jogging performances of a user
	 * @return
	 */
	private XYDataset createDataset() {

		XYSeries joggingSeries = new XYSeries("Performance jogging de " + user.getPseudo());

		Session session = DBConnection.getSession();
		session.beginTransaction();
		user = (User) session.get(User.class, user.getPseudo());
		Profile profile = user.getProfile();
		
		List<Practice> practicesList = profile.getPracticesList();
		ArrayList<Practice> joggingPracticesList = new ArrayList<Practice>();
		for(int i = 0; i < practicesList.size(); i++) {
			if(practicesList.get(i).getSport().getName().equals("Jogging"))
				joggingPracticesList.add(practicesList.get(i));
		}
		session.getTransaction().commit();
		int nbPractices = joggingPracticesList.size();
		int currentMonth, currentYear, i=nbPractices-1;

		Calendar cal = Calendar.getInstance();
		
		monthName = DataUtility.convertMonth(month);
		joggingSeries.add(0.9, null);
		joggingSeries.add(31.1, null);
		
		if (!joggingPracticesList.isEmpty()){
			cal.setTime(joggingPracticesList.get(i).getDate());
			currentMonth = cal.get(Calendar.MONTH)+1;
			currentYear = cal.get(Calendar.YEAR);	
			while ((currentMonth!=month || currentYear!=year) && i>=0 && i<nbPractices){	
				cal.setTime(joggingPracticesList.get(i).getDate());
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
					cal.setTime(joggingPracticesList.get(i).getDate());
					currentMonth = cal.get(Calendar.MONTH)+1;
					currentYear = cal.get(Calendar.YEAR);
					if (currentMonth==month && currentYear==year){
						joggingSeries.add(cal.get(Calendar.DAY_OF_MONTH), joggingPracticesList.get(i).getPerformance());
						System.out.println("Date = "+cal.get(Calendar.DAY_OF_MONTH)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.YEAR)+" -- Performance pour le jogging = "+joggingPracticesList.get(i).getPerformance());
					}
					i--;
				}while (currentMonth==month && currentYear==year && i>=0);
			}
		}
		else{
			nbError = 1;
		}

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(joggingSeries);

		return dataset;

	}

	private JFreeChart createChart(XYDataset dataset) {
		return ChartFactory.createXYLineChart("Performances en jogging", monthName+" "+year, "minutes", dataset, PlotOrientation.VERTICAL, true, true, false);
	}	



	public ChartPanel showJoggingPerfPanel(){
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
