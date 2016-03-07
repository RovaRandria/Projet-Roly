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

/**
 * Panel that show the charts about physical data
 * @author Angelique Nguyen & Rova Randrianantoanina
 * @version 1.0
 */	
public class PhysicalDataChartPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private User user;
	private int currentMonthWeight, currentMonthWaistSize, currentMonthHipSize;
	private int currentYearWeight, currentYearWaistSize, currentYearHipSize;
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

	/**
	 * Constructor
	 * @param user : charts are based on those user's performances
	 */
	public PhysicalDataChartPanel(User user) {
		super();
		this.user = user;
		Calendar cal = Calendar.getInstance();
		currentMonthWeight = cal.get(Calendar.MONTH)+1;
		currentYearWeight = cal.get(Calendar.YEAR);
		currentMonthWaistSize = cal.get(Calendar.MONTH)+1;
		currentYearWaistSize = cal.get(Calendar.YEAR);
		currentMonthHipSize = cal.get(Calendar.MONTH)+1;
		currentYearHipSize = cal.get(Calendar.YEAR);
		
		initStyle();
		init();
	}
	
	/**
	 * Method that reinitialize the panel
	 */
	public void repaintPanel(){
		initStyle();
		init();
	}
	
	/**
	 * Method that initialize the components on the panel
	 */
	public void init() {

		/*
		 * Weight chart
		 */
		weightChart = new WeightChart("Courbe de poids", currentMonthWeight, currentYearWeight, user);	
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
		waistSizeChart = new WaistSizeChart("Courbe du tour de taille", currentMonthWaistSize, currentYearWaistSize, user);	
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
		hipSizeChart = new HipSizeChart("Courbe du tour de hanche", currentMonthHipSize, currentYearHipSize, user);	
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

	/**
	 * Method that initialize the style of the components
	 */
	public void initStyle(){
		nextMonthWeightButton.setOpaque(false);
		previousMonthWeightButton.setOpaque(false);
		nextMonthWaistSizeButton.setOpaque(false);
		previousMonthWaistSizeButton.setOpaque(false);
		nextMonthHipSizeButton.setOpaque(false);
		previousMonthHipSizeButton.setOpaque(false);
		weightButtonBox.setOpaque(false);
		waistSizeButtonBox.setOpaque(false);
		hipSizeButtonBox.setOpaque(false);
		weightMainBox.setOpaque(false);
		waistSizeMainBox.setOpaque(false);
		hipSizeMainBox.setOpaque(false);
		this.setOpaque(false);
	}

	public int getCurrentMonthWeight() {
		return currentMonthWeight;
	}

	public void setCurrentMonthWeight(int currentMonthWeight) {
		this.currentMonthWeight = currentMonthWeight;
	}

	public int getCurrentMonthWaistSize() {
		return currentMonthWaistSize;
	}

	public void setCurrentMonthWaistSize(int currentMonthWaistSize) {
		this.currentMonthWaistSize = currentMonthWaistSize;
	}

	public int getCurrentMonthHipSize() {
		return currentMonthHipSize;
	}

	public void setCurrentMonthHipSize(int currentMonthHipSize) {
		this.currentMonthHipSize = currentMonthHipSize;
	}

	public int getCurrentYearWeight() {
		return currentYearWeight;
	}

	public void setCurrentYearWeight(int currentYearWeight) {
		this.currentYearWeight = currentYearWeight;
	}

	public int getCurrentYearWaistSize() {
		return currentYearWaistSize;
	}

	public void setCurrentYearWaistSize(int currentYearWaistSize) {
		this.currentYearWaistSize = currentYearWaistSize;
	}

	public int getCurrentYearHipSize() {
		return currentYearHipSize;
	}

	public void setCurrentYearHipSize(int currentYearHipSize) {
		this.currentYearHipSize = currentYearHipSize;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


}
