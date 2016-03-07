
package chart;

import java.awt.Color;
import java.awt.Paint;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.TextAnchor;

import utils.DataUtility;
import data.DBConnection;
import data.Exercise;
import data.Practice;
import data.Profile;
import data.User;

public class ClimbingPerformancesChart extends ApplicationFrame {
	private static final long serialVersionUID = 1L;

	private User user;
	private int month;
	private String monthName;
	private int year;
	private int nbError;

	public ClimbingPerformancesChart(String title, int month, int year, User user) {
		super(title);
		this.user = user;
		this.month = month;
		this.year = year;
		nbError = 0;
	}


	private CategoryDataset createDataset() {
		String series1 = "Performances escalade";

		String yellow = "Voie jaune";
		String orange = "Voie orange";
		String blue = "Voie bleue";
		String red = "Voie rouge";
		String white = "Voie blanc";
		String black = "Voie noire";
		String green = "Voie verte";

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		Session session = DBConnection.getSession();
		session.beginTransaction();
		user = (User) session.get(User.class, user.getPseudo());
		Profile profile = user.getProfile();

		List<Practice> practicesList = profile.getPracticesList();
		ArrayList<Practice> climbingPracticesList = new ArrayList<Practice>();
		for(int i = 0; i < practicesList.size(); i++) {
			if(practicesList.get(i).getSport().getName().equals("Escalade"))
				climbingPracticesList.add(practicesList.get(i));
		}
		session.getTransaction().commit();
		int nbPractices = climbingPracticesList.size();
		int currentMonth, currentYear, i=nbPractices-1;

		Calendar cal = Calendar.getInstance();

		monthName = DataUtility.convertMonth(month);

		if (!climbingPracticesList.isEmpty()){
			cal.setTime(climbingPracticesList.get(i).getDate());
			currentMonth = cal.get(Calendar.MONTH)+1;
			currentYear = cal.get(Calendar.YEAR);	
			while ((currentMonth!=month || currentYear!=year) && i>=0 && i<nbPractices){	
				cal.setTime(climbingPracticesList.get(i).getDate());
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
					cal.setTime(climbingPracticesList.get(i).getDate());
					currentMonth = cal.get(Calendar.MONTH)+1;
					currentYear = cal.get(Calendar.YEAR);
					if (currentMonth==month && currentYear==year){
						int yellowRouteNb = 0;
						int orangeRouteNb = 0;
						int blueRouteNb = 0;
						int redRouteNb = 0;
						int whiteRouteNb = 0;
						int blackRouteNb = 0;
						int greenRouteNb = 0;
						List<Exercise>	practiceExercise = new ArrayList<Exercise>();
						practiceExercise = climbingPracticesList.get(i).getExercisesList();
						for(int j = 0; j<practiceExercise.size(); j++) {
							if(practiceExercise.get(j).getName().equals("Voie jaune")){
								yellowRouteNb += practiceExercise.get(j).getNumberOfSeries();
							}
							else if(practiceExercise.get(j).getName().equals("Voie orange")){
								orangeRouteNb += practiceExercise.get(j).getNumberOfSeries();
							}
							else if(practiceExercise.get(j).getName().equals("Voie bleue")){
								blueRouteNb += practiceExercise.get(j).getNumberOfSeries();
							}
							else if(practiceExercise.get(j).getName().equals("Voie rouge")){
								redRouteNb += practiceExercise.get(j).getNumberOfSeries();
							}
							else if(practiceExercise.get(j).getName().equals("Voie blanche")){
								whiteRouteNb += practiceExercise.get(j).getNumberOfSeries();
							}
							else if(practiceExercise.get(j).getName().equals("Voie noire")){
								blackRouteNb += practiceExercise.get(j).getNumberOfSeries();
							}
							else if(practiceExercise.get(j).getName().equals("Voie verte")){
								greenRouteNb += practiceExercise.get(j).getNumberOfSeries();
							}
						}
						dataset.addValue(yellowRouteNb, series1, yellow);
						dataset.addValue(orangeRouteNb, series1, orange);
						dataset.addValue(blueRouteNb, series1, blue);
						dataset.addValue(redRouteNb, series1, red);
						dataset.addValue(whiteRouteNb, series1, white);
						dataset.addValue(blackRouteNb, series1, black);
						dataset.addValue(greenRouteNb, series1, green);
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
		final JFreeChart chart = ChartFactory.createBarChart("Performances en escalade", monthName+" "+year, "nombre de voies", dataset, PlotOrientation.VERTICAL, true, true, false);
		final CategoryPlot plot = chart.getCategoryPlot();
        plot.setNoDataMessage("NO DATA!");

        final CategoryItemRenderer renderer = new CustomRenderer(
            new Paint[] {Color.yellow, Color.orange, Color.blue,
                Color.red, Color.white, Color.black,
                Color.green}
        );
        renderer.setItemLabelsVisible(true);
        final ItemLabelPosition p = new ItemLabelPosition(
            ItemLabelAnchor.CENTER, TextAnchor.CENTER, TextAnchor.CENTER, 45.0
        );
        renderer.setPositiveItemLabelPosition(p);
        plot.setRenderer(renderer);
		return chart;
	}	



	public ChartPanel showClimbingPerfPanel(){
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

	class CustomRenderer extends BarRenderer {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Paint[] colors;

		public CustomRenderer(final Paint[] colors) {
			this.colors = colors;
		}

		public Paint getItemPaint(final int row, final int column) {
			return this.colors[column % this.colors.length];
		}
	}




}
