
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
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

import utils.DataUtility;
import data.DBConnection;
import data.Exercise;
import data.Practice;
import data.Profile;
import data.User;

public class SkiPerformancesChart extends ApplicationFrame {
	private static final long serialVersionUID = 1L;

	private User user;
	private int month;
	private String monthName;
	private int year;
	private int nbError;

	public SkiPerformancesChart(String title, int month, int year, User user) {
		super(title);
		this.user = user;
		this.month = month;
		this.year = year;
		nbError = 0;
	}


	private CategoryDataset createDataset() {
		String series1 = "Performances ski";

		String green = "verte";
		String blue = "bleue";
		String red = "rouge";
		String black = "noire";		

		int blueRouteNb = 0;
		int redRouteNb = 0;
		int blackRouteNb = 0;
		int greenRouteNb = 0;

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		Session session = DBConnection.getSession();
		session.beginTransaction();
		user = (User) session.get(User.class, user.getPseudo());
		Profile profile = user.getProfile();

		List<Practice> practicesList = profile.getPracticesList();
		ArrayList<Practice> skiPracticesList = new ArrayList<Practice>();
		for(int i = 0; i < practicesList.size(); i++) {
			if(practicesList.get(i).getSport().getName().equals("Ski"))
				skiPracticesList.add(practicesList.get(i));
		}
		session.getTransaction().commit();
		int nbPractices = skiPracticesList.size();
		int currentMonth, currentYear, i=nbPractices-1;

		Calendar cal = Calendar.getInstance();

		monthName = DataUtility.convertMonth(month);

		if (!skiPracticesList.isEmpty()){
			cal.setTime(skiPracticesList.get(i).getDate());
			currentMonth = cal.get(Calendar.MONTH)+1;
			currentYear = cal.get(Calendar.YEAR);	
			while ((currentMonth!=month || currentYear!=year) && i>=0 && i<nbPractices){	
				cal.setTime(skiPracticesList.get(i).getDate());
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
					cal.setTime(skiPracticesList.get(i).getDate());
					currentMonth = cal.get(Calendar.MONTH)+1;
					currentYear = cal.get(Calendar.YEAR);
					if (currentMonth==month && currentYear==year){
						List<Exercise>	practiceExercise = new ArrayList<Exercise>();
						practiceExercise = skiPracticesList.get(i).getExercisesList();
						for(int j = 0; j<practiceExercise.size(); j++) {
							if(practiceExercise.get(j).getName().equals("Piste verte")){
								greenRouteNb += practiceExercise.get(j).getNumberOfSeries();
							}
							else if(practiceExercise.get(j).getName().equals("Piste bleue")){
								blueRouteNb += practiceExercise.get(j).getNumberOfSeries();
							}
							else if(practiceExercise.get(j).getName().equals("Piste rouge")){
								redRouteNb += practiceExercise.get(j).getNumberOfSeries();
							}
							else if(practiceExercise.get(j).getName().equals("Piste noire")){
								blackRouteNb += practiceExercise.get(j).getNumberOfSeries();
							}
						}
						dataset.addValue(blueRouteNb, series1, blue);
						dataset.addValue(redRouteNb, series1, red);
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
		JFreeChart chart = ChartFactory.createBarChart("Performances en ski", monthName+" "+year, "nombre de pistes", dataset, PlotOrientation.VERTICAL, true, true, false);
		CategoryPlot plot = chart.getCategoryPlot();
        plot.setNoDataMessage("Aucune donnée !");

        CategoryItemRenderer renderer = new CustomRenderer(
            new Paint[] {Color.green, Color.blue,
                Color.red, Color.black}
        );
        renderer.setItemLabelsVisible(true);
        plot.setRenderer(renderer);
		return chart;
	}	



	public ChartPanel showSkiPerfPanel(){
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

	@SuppressWarnings("serial")
	class CustomRenderer extends BarRenderer {
		private Paint[] colors;

		public CustomRenderer(final Paint[] colors) {
			this.colors = colors;
		}

		public Paint getItemPaint(final int row, final int column) {
			return this.colors[column % this.colors.length];
		}
	}




}
