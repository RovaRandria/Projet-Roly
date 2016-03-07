
package chart;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

import utils.DataUtility;
import data.DBConnection;
import data.Exercise;
import data.Practice;
import data.Profile;
import data.User;

public class BodybuildingPerformancesChart extends ApplicationFrame {
	private static final long serialVersionUID = 1L;

	private User user;
	private int month;
	private String monthName;
	private int year;
	private int nbError;

	public BodybuildingPerformancesChart(String title, int month, int year, User user) {
		super(title);
		this.user = user;
		this.month = month;
		this.year = year;
		nbError = 0;
	}


	private CategoryDataset createDataset() {
		String series1 = "Performances musculation de " + user.getPseudo();

		String pushup = "Pompes";
		String situp = "Abdominaux";
		String pullup = "Tractions";
		String dips = "Dips";
		String squat = "Squats";
		String benchPress = "Développés-couchés";		

		int pushupNb = 0;
		int situpNb = 0;
		int pullupNb = 0;
		int dipsNb = 0;
		int squatNb = 0;
		int benchPressNb = 0;

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		Session session = DBConnection.getSession();
		session.beginTransaction();
		user = (User) session.get(User.class, user.getPseudo());
		Profile profile = user.getProfile();

		List<Practice> practicesList = profile.getPracticesList();
		ArrayList<Practice> bodybuildingPracticesList = new ArrayList<Practice>();
		for(int i = 0; i < practicesList.size(); i++) {
			if(practicesList.get(i).getSport().getName().equals("Musculation"))
				bodybuildingPracticesList.add(practicesList.get(i));
		}
		session.getTransaction().commit();
		int nbPractices = bodybuildingPracticesList.size();
		int currentMonth, currentYear, i=nbPractices-1;

		Calendar cal = Calendar.getInstance();

		monthName = DataUtility.convertMonth(month);

		if (!bodybuildingPracticesList.isEmpty()){
			cal.setTime(bodybuildingPracticesList.get(i).getDate());
			currentMonth = cal.get(Calendar.MONTH)+1;
			currentYear = cal.get(Calendar.YEAR);	
			while ((currentMonth!=month || currentYear!=year) && i>=0 && i<nbPractices){	
				cal.setTime(bodybuildingPracticesList.get(i).getDate());
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
					cal.setTime(bodybuildingPracticesList.get(i).getDate());
					currentMonth = cal.get(Calendar.MONTH)+1;
					currentYear = cal.get(Calendar.YEAR);
					if (currentMonth==month && currentYear==year){
						List<Exercise>	practiceExercise = new ArrayList<Exercise>();
						practiceExercise = bodybuildingPracticesList.get(i).getExercisesList();
						for(int j = 0; j<practiceExercise.size(); j++) {
							if(practiceExercise.get(j).getName().equals("Pompes")){
								pushupNb += practiceExercise.get(j).getNumberOfSeries();
							}
							else if(practiceExercise.get(j).getName().equals("Abdominaux")){
								situpNb += practiceExercise.get(j).getNumberOfSeries();
							}
							else if(practiceExercise.get(j).getName().equals("Tractions")){
								pullupNb += practiceExercise.get(j).getNumberOfSeries();
							}
							else if(practiceExercise.get(j).getName().equals("Dips")){
								dipsNb += practiceExercise.get(j).getNumberOfSeries();
							}
							else if(practiceExercise.get(j).getName().equals("Squats")){
								squatNb += practiceExercise.get(j).getNumberOfSeries();
							}
							else if(practiceExercise.get(j).getName().equals("Développés-couchés")){
								benchPressNb += practiceExercise.get(j).getNumberOfSeries();
							}
						}
						dataset.addValue(pushupNb, series1, pushup);
						dataset.addValue(situpNb, series1, situp);
						dataset.addValue(pullupNb, series1, pullup);
						dataset.addValue(dipsNb, series1, dips);
						dataset.addValue(squatNb, series1, squat);
						dataset.addValue(benchPressNb, series1, benchPress);
					}
					i--;
				}while (currentMonth==month && currentYear==year && i>=0);
			}
		}
		else{
			nbError = 1;
		}

		return dataset;

	}

	private JFreeChart createChart(CategoryDataset dataset) {
		final JFreeChart chart = ChartFactory.createBarChart("Performances en musculation", monthName+" "+year, "nombre de séries", dataset, PlotOrientation.VERTICAL, true, true, false);
		final CategoryPlot plot = chart.getCategoryPlot();
        plot.setNoDataMessage("Aucune donnée !");
		return chart;
	}	



	public ChartPanel showBodybuildingPerfPanel(){
		CategoryDataset dataset = createDataset();
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
