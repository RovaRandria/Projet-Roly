package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Calendar;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.hibernate.Session;
import org.jfree.chart.ChartPanel;
import org.jfree.ui.RefineryUtilities;

import chart.JoggingPerformancesChart;
import chart.WaistSizeChart;
import chart.WeightChart;
import data.DBConnection;
import data.User;


public class ChartGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private User user;
	private int currentMonth;
	private int currentYear;
	private JButton nextMonthWeightButton = new JButton("Suivant");
	private JButton previousMonthWeightButton = new JButton("Précédent");
	private JButton nextMonthWaistSizeButton = new JButton("Suivant");
	private JButton previousMonthWaistSizeButton = new JButton("Précédent");
	private JButton nextMonthJoggingPerfButton = new JButton("Suivant");
	private JButton previousMonthJoggingPerfButton = new JButton("Précédent");
	private WeightChart weightChart;
	private WaistSizeChart waistSizeChart;
	private JoggingPerformancesChart joggingPerfChart;

	private Box buttonMonthWeightBox = Box.createHorizontalBox();
	private Box buttonMonthWaistSizeBox = Box.createHorizontalBox();
	private Box buttonMonthJoggingPerfBox = Box.createHorizontalBox();
	private Box weightMainBox = Box.createVerticalBox();
	private Box waistSizeMainBox = Box.createVerticalBox();
	private Box joggingPerfMainBox = Box.createVerticalBox();

	private ChartPanel currentWeightChartPanel;
	private ChartPanel currentWaistSizeChartPanel;
	private ChartPanel currentJoggingPerfChartPanel;

	private JTabbedPane choiceTabbedPane = new JTabbedPane();

	private JPanel nextMonthWeightPanel = new JPanel();
	private JPanel previousMonthWeightPanel = new JPanel();
	private JPanel nextMonthWaistSizePanel = new JPanel();
	private JPanel previousMonthWaistSizePanel = new JPanel();
	private JPanel nextMonthJoggingPerfPanel = new JPanel();
	private JPanel previousMonthJoggingPerfPanel = new JPanel();
	private JPanel homePanel = new JPanel();

	private int mode;

	public ChartGUI(User user, int mode) {
		super();
		this.user = user;
		this.mode = mode;
		Calendar cal = Calendar.getInstance();
		currentMonth = cal.get(Calendar.MONTH)+1;
		currentYear = cal.get(Calendar.YEAR);

		initStyle();
		init();
		initActions();
	}
	public void init() {

		/*
		 * Weight chart
		 */
		if(mode == 0) {
			weightChart = new WeightChart("Courbe de poids", currentMonth, currentYear, user);	
			currentWeightChartPanel = weightChart.showWeightPanel();

			nextMonthWeightPanel.add(nextMonthWeightButton);
			previousMonthWeightPanel.add(previousMonthWeightButton);
			buttonMonthWeightBox.add(previousMonthWeightPanel);
			buttonMonthWeightBox.add(nextMonthWeightPanel);

			weightMainBox.add(currentWeightChartPanel);
			weightMainBox.add(buttonMonthWeightBox);


			/*
			 * Waist size chart
			 */
			waistSizeChart = new WaistSizeChart("Courbe de tour de taille", currentMonth, currentYear, user);	
			currentWaistSizeChartPanel = waistSizeChart.showWaistSizePanel();

			nextMonthWaistSizePanel.add(nextMonthWaistSizeButton);
			previousMonthWaistSizePanel.add(previousMonthWaistSizeButton);
			buttonMonthWaistSizeBox.add(previousMonthWaistSizePanel);
			buttonMonthWaistSizeBox.add(nextMonthWaistSizePanel);

			waistSizeMainBox.add(currentWaistSizeChartPanel);
			waistSizeMainBox.add(buttonMonthWaistSizeBox);


			/*
			 * Tab with different chart's choices
			 */
			choiceTabbedPane.addTab("Poids", weightMainBox);
			choiceTabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

			choiceTabbedPane.addTab("Tour de taille", waistSizeMainBox);
			choiceTabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

			homePanel.add(choiceTabbedPane);
		}
		if(mode == 1) {
			joggingPerfChart = new JoggingPerformancesChart("Performances jogging", currentMonth, currentYear, user);	
			currentJoggingPerfChartPanel = joggingPerfChart.showJoggingPerfPanel();

			nextMonthJoggingPerfPanel.add(nextMonthJoggingPerfButton);
			previousMonthWaistSizePanel.add(previousMonthJoggingPerfButton);
			buttonMonthJoggingPerfBox.add(previousMonthJoggingPerfPanel);
			buttonMonthJoggingPerfBox.add(nextMonthJoggingPerfPanel);

			joggingPerfMainBox.add(currentJoggingPerfChartPanel);
			joggingPerfMainBox.add(buttonMonthJoggingPerfBox);
			homePanel.add(joggingPerfMainBox);
		}
		RefineryUtilities.centerFrameOnScreen(this);
		this.add(homePanel);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
		setResizable(false);

	}

	public void initStyle(){

	}

	public void initActions(){
		previousMonthWeightButton.addActionListener(new previousMonthWeightAction());
		nextMonthWeightButton.addActionListener(new nextMonthWeightAction());
		nextMonthWaistSizeButton.addActionListener(new nextMonthWaistSizeAction());
		previousMonthWaistSizeButton.addActionListener(new previousMonthWaistSizeAction());
		previousMonthJoggingPerfButton.addActionListener(new previousMonthJoggingPerfAction());
		nextMonthJoggingPerfButton.addActionListener(new nextMonthJoggingPerfAction());
	}

	class previousMonthWeightAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			nextMonthWeightButton.setVisible(true);
			if (currentMonth==1){
				currentMonth=12;
				currentYear--;
			}else
				currentMonth--;

			System.out.println("Mois précédent : "+currentMonth+"/"+currentYear);

			weightChart = new WeightChart("Courbe de poids", currentMonth, currentYear, user);	
			currentWeightChartPanel.removeAll();
			currentWeightChartPanel.add(weightChart.showWeightPanel());
			weightMainBox.repaint();
			homePanel.repaint();
		}
	}

	class nextMonthWeightAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (currentMonth==12){
				currentMonth=1;
				currentYear++;
			}else
				currentMonth++;
			System.out.println("Mois suivant : "+currentMonth+"/"+currentYear);
			weightChart = new WeightChart("Courbe de poids", currentMonth, currentYear, user);	

			currentWeightChartPanel.removeAll();
			currentWeightChartPanel.add(weightChart.showWeightPanel());
			System.out.println("nb error = "+weightChart.getNbError());
			if (weightChart.getNbError()==2)
				nextMonthWeightButton.setVisible(false);
			else
				nextMonthWeightButton.setVisible(true);

			weightMainBox.repaint();
			homePanel.repaint();
		}
	}

	class previousMonthWaistSizeAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			nextMonthWaistSizeButton.setVisible(true);
			if (currentMonth==1){
				currentMonth=12;
				currentYear--;
			}else
				currentMonth--;

			System.out.println("Mois précédent : "+currentMonth+"/"+currentYear);

			waistSizeChart = new WaistSizeChart("Courbe de tour de taille", currentMonth, currentYear, user);	
			currentWaistSizeChartPanel.removeAll();
			currentWaistSizeChartPanel.add(waistSizeChart.showWaistSizePanel());
			waistSizeMainBox.repaint();
			homePanel.repaint();
		}
	}

	class nextMonthWaistSizeAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (currentMonth==12){
				currentMonth=1;
				currentYear++;
			}else
				currentMonth++;
			System.out.println("Mois suivant : "+currentMonth+"/"+currentYear);
			waistSizeChart = new WaistSizeChart("Courbe de tour de taille", currentMonth, currentYear, user);	

			currentWaistSizeChartPanel.removeAll();
			currentWaistSizeChartPanel.add(waistSizeChart.showWaistSizePanel());
			System.out.println("nb error = "+waistSizeChart.getNbError());
			if (waistSizeChart.getNbError()==2)
				nextMonthWaistSizeButton.setVisible(false);
			else
				nextMonthWaistSizeButton.setVisible(true);

			waistSizeMainBox.repaint();
			homePanel.repaint();
		}
	}
	
	class previousMonthJoggingPerfAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			nextMonthJoggingPerfButton.setVisible(true);
			if (currentMonth==1){
				currentMonth=12;
				currentYear--;
			}else
				currentMonth--;

			System.out.println("Mois précédent : "+currentMonth+"/"+currentYear);

			joggingPerfChart = new JoggingPerformancesChart("Performances jogging", currentMonth, currentYear, user);	
			currentJoggingPerfChartPanel.removeAll();
			currentJoggingPerfChartPanel.add(joggingPerfChart.showJoggingPerfPanel());
			joggingPerfMainBox.repaint();
			homePanel.repaint();
		}
	}
	
	class nextMonthJoggingPerfAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (currentMonth==12){
				currentMonth=1;
				currentYear++;
			}else
				currentMonth++;
			System.out.println("Mois suivant : "+currentMonth+"/"+currentYear);
			joggingPerfChart = new JoggingPerformancesChart("Performances jogging", currentMonth, currentYear, user);	

			currentJoggingPerfChartPanel.removeAll();
			currentJoggingPerfChartPanel.add(joggingPerfChart.showJoggingPerfPanel());
			System.out.println("nb error = "+joggingPerfChart.getNbError());
			if (joggingPerfChart.getNbError()==2)
				nextMonthJoggingPerfButton.setVisible(false);
			else
				nextMonthJoggingPerfButton.setVisible(true);

			joggingPerfMainBox.repaint();
			homePanel.repaint();
		}
	}

	public static void main(String[] args) {
		Session session = DBConnection.getSession();
		session.beginTransaction();
		User user = (User) session.get(User.class, "l");
		new ChartGUI(user,1);
	}
}
