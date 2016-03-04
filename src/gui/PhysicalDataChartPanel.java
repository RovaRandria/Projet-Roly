package gui;

import java.awt.event.KeyEvent;
import java.util.Calendar;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.jfree.chart.ChartPanel;

import chart.HipSizeChart;
import chart.WaistSizeChart;
import chart.WeightChart;
import data.User;


public class PhysicalDataChartPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private User user;
	private int currentMonth;
	private int currentYear;
	private JButton nextMonthWeightButton = new JButton("Suivant");
	private JButton previousMonthWeightButton = new JButton("Précédent");
	private JButton nextMonthWaistSizeButton = new JButton("Suivant");
	private JButton previousMonthWaistSizeButton = new JButton("Précédent");
	private JButton nextMonthHipSizeButton = new JButton("Suivant");
	private JButton previousMonthHipSizeButton = new JButton("Précédent");
	private WeightChart weightChart;
	private WaistSizeChart waistSizeChart;
	private HipSizeChart hipSizeChart;

	private Box weightButtonBox = Box.createHorizontalBox();
	private Box waistSizeButtonBox = Box.createHorizontalBox();
	private Box hipSizeButtonBox = Box.createHorizontalBox();
	private Box weightMainBox = Box.createVerticalBox();
	private Box waistSizeMainBox = Box.createVerticalBox();
	private Box joggingPerfMainBox = Box.createVerticalBox();
	private Box hipSizeMainBox = Box.createVerticalBox();

	private ChartPanel currentWeightChartPanel;
	private ChartPanel currentWaistSizeChartPanel;
	private ChartPanel currentHipSizeChartPanel;

	private JTabbedPane choiceTabbedPane = new JTabbedPane();

	private JPanel nextMonthWeightPanel = new JPanel();
	private JPanel previousMonthWeightPanel = new JPanel();
	private JPanel nextMonthWaistSizePanel = new JPanel();
	private JPanel previousMonthWaistSizePanel = new JPanel();
	private JPanel nextMonthHipSizePanel = new JPanel();
	private JPanel previousMonthHipSizePanel = new JPanel();

	public PhysicalDataChartPanel(User user) {
		super();
		this.user = user;
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
		weightChart = new WeightChart("Courbe de poids", currentMonth, currentYear, user);	
		currentWeightChartPanel = weightChart.showWeightPanel();

		nextMonthWeightPanel.add(nextMonthWeightButton);
		previousMonthWeightPanel.add(previousMonthWeightButton);
		weightButtonBox.add(previousMonthWeightPanel);
		weightButtonBox.add(nextMonthWeightPanel);

		weightMainBox.add(currentWeightChartPanel);
		weightMainBox.add(weightButtonBox);


		/*
		 * Waist size chart
		 */
		waistSizeChart = new WaistSizeChart("Courbe du tour de taille", currentMonth, currentYear, user);	
		currentWaistSizeChartPanel = waistSizeChart.showWaistSizePanel();

		nextMonthWaistSizePanel.add(nextMonthWaistSizeButton);
		previousMonthWaistSizePanel.add(previousMonthWaistSizeButton);
		waistSizeButtonBox.add(previousMonthWaistSizePanel);
		waistSizeButtonBox.add(nextMonthWaistSizePanel);

		waistSizeMainBox.add(currentWaistSizeChartPanel);
		waistSizeMainBox.add(waistSizeButtonBox);

			
		/*
		 * Weight chart
		 */
		hipSizeChart = new HipSizeChart("Courbe du tour de hanche", currentMonth, currentYear, user);	
		currentHipSizeChartPanel = hipSizeChart.showHipSizePanel();

		nextMonthHipSizePanel.add(nextMonthHipSizeButton);
		previousMonthHipSizePanel.add(previousMonthHipSizeButton);
		hipSizeButtonBox.add(previousMonthHipSizePanel);
		hipSizeButtonBox.add(nextMonthHipSizePanel);

		hipSizeMainBox.add(currentHipSizeChartPanel);
		hipSizeMainBox.add(hipSizeButtonBox);
			

		/*
		 * Tab with different chart's choices
		 */
		choiceTabbedPane.addTab("Poids", weightMainBox);
		choiceTabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		choiceTabbedPane.addTab("Tour de taille", waistSizeMainBox);
		choiceTabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

		choiceTabbedPane.addTab("Tour de hanche", hipSizeMainBox);
		choiceTabbedPane.setMnemonicAt(2, KeyEvent.VK_2);
			
		add(choiceTabbedPane);

	}

	public void initStyle(){

	}

	public void initActions(){

	}
	public int getCurrentMonth() {
		return currentMonth;
	}
	public void setCurrentMonth(int currentMonth) {
		this.currentMonth = currentMonth;
	}
	public int getCurrentYear() {
		return currentYear;
	}
	public void setCurrentYear(int currentYear) {
		this.currentYear = currentYear;
	}
	public JButton getNextMonthWeightButton() {
		return nextMonthWeightButton;
	}
	public void setNextMonthWeightButton(JButton nextMonthWeightButton) {
		this.nextMonthWeightButton = nextMonthWeightButton;
	}
	public JButton getPreviousMonthWeightButton() {
		return previousMonthWeightButton;
	}
	public void setPreviousMonthWeightButton(JButton previousMonthWeightButton) {
		this.previousMonthWeightButton = previousMonthWeightButton;
	}
	public JButton getNextMonthWaistSizeButton() {
		return nextMonthWaistSizeButton;
	}
	public void setNextMonthWaistSizeButton(JButton nextMonthWaistSizeButton) {
		this.nextMonthWaistSizeButton = nextMonthWaistSizeButton;
	}
	public JButton getPreviousMonthWaistSizeButton() {
		return previousMonthWaistSizeButton;
	}
	public void setPreviousMonthWaistSizeButton(JButton previousMonthWaistSizeButton) {
		this.previousMonthWaistSizeButton = previousMonthWaistSizeButton;
	}
	public WeightChart getWeightChart() {
		return weightChart;
	}
	public void setWeightChart(WeightChart weightChart) {
		this.weightChart = weightChart;
	}
	public WaistSizeChart getWaistSizeChart() {
		return waistSizeChart;
	}
	public void setWaistSizeChart(WaistSizeChart waistSizeChart) {
		this.waistSizeChart = waistSizeChart;
	}
	public ChartPanel getCurrentWeightChartPanel() {
		return currentWeightChartPanel;
	}
	public void setCurrentWeightChartPanel(ChartPanel currentWeightChartPanel) {
		this.currentWeightChartPanel = currentWeightChartPanel;
	}
	public ChartPanel getCurrentWaistSizeChartPanel() {
		return currentWaistSizeChartPanel;
	}
	public void setCurrentWaistSizeChartPanel(ChartPanel currentWaistSizeChartPanel) {
		this.currentWaistSizeChartPanel = currentWaistSizeChartPanel;
	}
	public Box getWeightMainBox() {
		return weightMainBox;
	}
	public void setWeightMainBox(Box weightMainBox) {
		this.weightMainBox = weightMainBox;
	}
	public Box getWaistSizeMainBox() {
		return waistSizeMainBox;
	}
	public void setWaistSizeMainBox(Box waistSizeMainBox) {
		this.waistSizeMainBox = waistSizeMainBox;
	}
	public Box getJoggingPerfMainBox() {
		return joggingPerfMainBox;
	}
	public void setJoggingPerfMainBox(Box joggingPerfMainBox) {
		this.joggingPerfMainBox = joggingPerfMainBox;
	}

	public HipSizeChart getHipSizeChart() {
		return hipSizeChart;
	}

	public void setHipSizeChart(HipSizeChart hipSizeChart) {
		this.hipSizeChart = hipSizeChart;
	}

	public JButton getNextMonthHipSizeButton() {
		return nextMonthHipSizeButton;
	}

	public void setNextMonthHipSizeButton(JButton nextMonthHipSizeButton) {
		this.nextMonthHipSizeButton = nextMonthHipSizeButton;
	}

	public JButton getPreviousMonthHipSizeButton() {
		return previousMonthHipSizeButton;
	}

	public void setPreviousMonthHipSizeButton(JButton previousMonthHipSizeButton) {
		this.previousMonthHipSizeButton = previousMonthHipSizeButton;
	}

	public ChartPanel getCurrentHipSizeChartPanel() {
		return currentHipSizeChartPanel;
	}

	public void setCurrentHipSizeChartPanel(ChartPanel currentHipSizeChartPanel) {
		this.currentHipSizeChartPanel = currentHipSizeChartPanel;
	}

	public JPanel getNextMonthHipSizePanel() {
		return nextMonthHipSizePanel;
	}

	public void setNextMonthHipSizePanel(JPanel nextMonthHipSizePanel) {
		this.nextMonthHipSizePanel = nextMonthHipSizePanel;
	}

	public JPanel getPreviousMonthHipSizePanel() {
		return previousMonthHipSizePanel;
	}

	public void setPreviousMonthHipSizePanel(JPanel previousMonthHipSizePanel) {
		this.previousMonthHipSizePanel = previousMonthHipSizePanel;
	}

	public Box getHipSizeMainBox() {
		return hipSizeMainBox;
	}

	public void setHipSizeMainBox(Box hipSizeMainBox) {
		this.hipSizeMainBox = hipSizeMainBox;
	}


}
