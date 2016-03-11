package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.util.Calendar;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.jfree.chart.ChartPanel;

import chart.BodybuildingPerformancesChart;
import chart.ClimbingPerformancesChart;
import chart.CyclingPerformancesChart;
import chart.JoggingPerformancesChart;
import chart.SkiPerformancesChart;
import data.User;

/**
 * Panel that show the comparison of charts about performances
 * @author Angelique Nguyen & Rova Randrianantoanina
 * @version 1.0
 */	
public class ComparisonChartPerformancePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private User user1, user2;
	private int currentMonthCycling, currentMonthJogging, currentMonthClimbing, currentMonthBodybuilding, currentMonthSki;
	private int currentYearCycling, currentYearJogging, currentYearClimbing, currentYearBodybuilding, currentYearSki;
	
	private JButton nextMonthJoggingPerfButton = new JButton("Suivant");
	private JButton nextMonthClimbingPerfButton = new JButton("Suivant");
	private JButton nextMonthSkiPerfButton = new JButton("Suivant");
	private JButton nextMonthCyclingPerfButton = new JButton("Suivant");	
	private JButton nextMonthBodybuildingPerfButton = new JButton("Suivant");
	
	private JButton previousMonthJoggingPerfButton = new JButton("Précédent");
	private JButton previousMonthClimbingPerfButton = new JButton("Précédent");
	private JButton previousMonthSkiPerfButton = new JButton("Précédent");
	private JButton previousMonthCyclingPerfButton = new JButton("Précédent");
	private JButton previousMonthBodybuildingPerfButton = new JButton("Précédent");

	private Box joggingPerfButtonBox = Box.createHorizontalBox();
	private Box climbingPerfButtonBox = Box.createHorizontalBox();
	private Box cyclingPerfButtonBox = Box.createHorizontalBox();
	private Box skiPerfButtonBox = Box.createHorizontalBox();
	private Box bodybuildingPerfButtonBox = Box.createHorizontalBox();
	
	private Box joggingPerfMainBox = Box.createVerticalBox();
	private Box climbingPerfMainBox = Box.createVerticalBox();
	private Box cyclingPerfMainBox = Box.createVerticalBox();
	private Box skiPerfMainBox = Box.createVerticalBox();
	private Box bodybuildingPerfMainBox = Box.createVerticalBox();

	private ChartPanel currentJoggingPerfChartPanel;
	private ChartPanel currentClimbingPerfChartPanel;
	private ChartPanel currentSkiPerfChartPanel;
	private ChartPanel currentCyclingPerfChartPanel;
	private ChartPanel currentBodybuildingPerfChartPanel;

	private JPanel nextMonthJoggingPerfPanel = new JPanel();
	private JPanel nextMonthClimbingPerfPanel = new JPanel();
	private JPanel nextMonthCyclingPerfPanel = new JPanel();
	private JPanel nextMonthSkiPerfPanel = new JPanel();
	private JPanel nextMonthBodybuildingPerfPanel = new JPanel();
	
	private JPanel previousMonthJoggingPerfPanel = new JPanel();
	private JPanel previousMonthClimbingPerfPanel = new JPanel();
	private JPanel previousMonthSkiPerfPanel = new JPanel();
	private JPanel previousMonthCyclingPerfPanel = new JPanel();
	private JPanel previousMonthBodybuildingPerfPanel = new JPanel();
	
	private JTabbedPane choiceTabbedPane = new JTabbedPane();	
	
	private JoggingPerformancesChart joggingPerfChart;
	private CyclingPerformancesChart cyclingPerfChart;
	private ClimbingPerformancesChart climbingPerfChart;
	private BodybuildingPerformancesChart bodybuildingPerfChart;
	private SkiPerformancesChart skiPerfChart;

	private JButton backHomeButton = new JButton("Retour à la page de recherche");

	/**
	 * Constructor
	 * @param user : charts are based on 2 users
	 */
	public ComparisonChartPerformancePanel(User user1, User user2) {
		super();
		this.user1 = user1;
		this.user2 = user2;
		Calendar cal = Calendar.getInstance();
		currentMonthCycling = cal.get(Calendar.MONTH)+1;
		currentYearCycling = cal.get(Calendar.YEAR);
		currentMonthJogging = cal.get(Calendar.MONTH)+1;
		currentYearJogging = cal.get(Calendar.YEAR);
		currentMonthClimbing = cal.get(Calendar.MONTH)+1;
		currentYearClimbing = cal.get(Calendar.YEAR);
		currentMonthBodybuilding = cal.get(Calendar.MONTH)+1;
		currentYearBodybuilding = cal.get(Calendar.YEAR);
		currentMonthSki = cal.get(Calendar.MONTH)+1;
		currentYearSki = cal.get(Calendar.YEAR);
		
		initStyle();		
		init();
	}

	/**
	 * Method that initialize the components on the panel
	 */
	public void init() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints frameConstraints = new GridBagConstraints();
		
		joggingPerfChart = new JoggingPerformancesChart("Performances jogging", currentMonthJogging, currentYearJogging, user1, user2);
		currentJoggingPerfChartPanel = joggingPerfChart.showComparisonJoggingPerfPanel();

		nextMonthJoggingPerfPanel.add(nextMonthJoggingPerfButton);
		previousMonthJoggingPerfPanel.add(previousMonthJoggingPerfButton);
		joggingPerfButtonBox.add(previousMonthJoggingPerfPanel);
		joggingPerfButtonBox.add(nextMonthJoggingPerfPanel);

		joggingPerfMainBox.add(currentJoggingPerfChartPanel);
		joggingPerfMainBox.add(joggingPerfButtonBox);
		
		climbingPerfChart = new ClimbingPerformancesChart("Performances escalade", currentMonthClimbing, currentYearClimbing, user1, user2);
		currentClimbingPerfChartPanel = climbingPerfChart.showComparisonClimbingPerfPanel();

		nextMonthClimbingPerfPanel.add(nextMonthClimbingPerfButton);
		previousMonthClimbingPerfPanel.add(previousMonthClimbingPerfButton);
		climbingPerfButtonBox.add(previousMonthClimbingPerfPanel);
		climbingPerfButtonBox.add(nextMonthClimbingPerfPanel);

		climbingPerfMainBox.add(currentClimbingPerfChartPanel);
		climbingPerfMainBox.add(climbingPerfButtonBox);
		
		choiceTabbedPane.addTab("Performances jogging", joggingPerfMainBox);
		choiceTabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		
		choiceTabbedPane.addTab("Performances escalade", climbingPerfMainBox);
		choiceTabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		
		frameConstraints.insets = new Insets(5, 0, 10, 0);
		frameConstraints.gridx = 0;
		frameConstraints.gridy = 0;
		add(choiceTabbedPane, frameConstraints);
		frameConstraints.gridx = 0;
		frameConstraints.gridy = 1;
		add(backHomeButton, frameConstraints);
	}

	/**
	 * Method that initialize the style of the components
	 */
	public void initStyle(){
		nextMonthJoggingPerfButton.setOpaque(false);
		previousMonthJoggingPerfButton.setOpaque(false);
		joggingPerfButtonBox.setOpaque(false);
		joggingPerfMainBox.setOpaque(false);

		nextMonthCyclingPerfButton.setOpaque(false);
		previousMonthCyclingPerfButton.setOpaque(false);
		cyclingPerfButtonBox.setOpaque(false);
		cyclingPerfMainBox.setOpaque(false);
		
		nextMonthClimbingPerfButton.setOpaque(false);
		previousMonthClimbingPerfButton.setOpaque(false);
		climbingPerfButtonBox.setOpaque(false);
		climbingPerfMainBox.setOpaque(false);
		
		nextMonthBodybuildingPerfButton.setOpaque(false);
		previousMonthBodybuildingPerfButton.setOpaque(false);
		bodybuildingPerfButtonBox.setOpaque(false);
		bodybuildingPerfMainBox.setOpaque(false);
		
		nextMonthSkiPerfButton.setOpaque(false);
		previousMonthSkiPerfButton.setOpaque(false);
		skiPerfButtonBox.setOpaque(false);
		skiPerfMainBox.setOpaque(false);
		
		backHomeButton.setOpaque(false);
		this.setOpaque(false);
	}


	public int getCurrentMonthCycling() {
		return currentMonthCycling;
	}

	public void setCurrentMonthCycling(int currentMonthCycling) {
		this.currentMonthCycling = currentMonthCycling;
	}

	public int getCurrentMonthJogging() {
		return currentMonthJogging;
	}

	public void setCurrentMonthJogging(int currentMonthJogging) {
		this.currentMonthJogging = currentMonthJogging;
	}

	public int getCurrentYearCycling() {
		return currentYearCycling;
	}

	public void setCurrentYearCycling(int currentYearCycling) {
		this.currentYearCycling = currentYearCycling;
	}

	public int getCurrentYearJogging() {
		return currentYearJogging;
	}

	public void setCurrentYearJogging(int currentYearJogging) {
		this.currentYearJogging = currentYearJogging;
	}

	public JButton getNextMonthJoggingPerfButton() {
		return nextMonthJoggingPerfButton;
	}

	public void setNextMonthJoggingPerfButton(JButton nextMonthJoggingPerfButton) {
		this.nextMonthJoggingPerfButton = nextMonthJoggingPerfButton;
	}

	public JButton getPreviousMonthJoggingPerfButton() {
		return previousMonthJoggingPerfButton;
	}

	public void setPreviousMonthJoggingPerfButton(
			JButton previousMonthJoggingPerfButton) {
		this.previousMonthJoggingPerfButton = previousMonthJoggingPerfButton;
	}

	public JoggingPerformancesChart getJoggingPerfChart() {
		return joggingPerfChart;
	}

	public void setJoggingPerfChart(JoggingPerformancesChart joggingPerfChart) {
		this.joggingPerfChart = joggingPerfChart;
	}

	public Box getJoggingPerfButtonBox() {
		return joggingPerfButtonBox;
	}

	public void setJoggingPerfButtonBox(Box joggingPerfButtonBox) {
		this.joggingPerfButtonBox = joggingPerfButtonBox;
	}

	public Box getJoggingPerfMainBox() {
		return joggingPerfMainBox;
	}

	public void setJoggingPerfMainBox(Box joggingPerfMainBox) {
		this.joggingPerfMainBox = joggingPerfMainBox;
	}

	public ChartPanel getCurrentJoggingPerfChartPanel() {
		return currentJoggingPerfChartPanel;
	}

	public void setCurrentJoggingPerfChartPanel(
			ChartPanel currentJoggingPerfChartPanel) {
		this.currentJoggingPerfChartPanel = currentJoggingPerfChartPanel;
	}

	public JPanel getNextMonthJoggingPerfPanel() {
		return nextMonthJoggingPerfPanel;
	}

	public void setNextMonthJoggingPerfPanel(JPanel nextMonthJoggingPerfPanel) {
		this.nextMonthJoggingPerfPanel = nextMonthJoggingPerfPanel;
	}

	public JPanel getPreviousMonthJoggingPerfPanel() {
		return previousMonthJoggingPerfPanel;
	}

	public void setPreviousMonthJoggingPerfPanel(
			JPanel previousMonthJoggingPerfPanel) {
		this.previousMonthJoggingPerfPanel = previousMonthJoggingPerfPanel;
	}

	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public JButton getBackHomeButton() {
		return backHomeButton;
	}

	public void setBackHomeButton(JButton backHomeButton) {
		this.backHomeButton = backHomeButton;
	}

	public JButton getNextMonthClimbingPerfButton() {
		return nextMonthClimbingPerfButton;
	}

	public void setNextMonthClimbingPerfButton(JButton nextMonthClimbingPerfButton) {
		this.nextMonthClimbingPerfButton = nextMonthClimbingPerfButton;
	}

	public JButton getNextMonthSkiPerfButton() {
		return nextMonthSkiPerfButton;
	}

	public void setNextMonthSkiPerfButton(JButton nextMonthSkiPerfButton) {
		this.nextMonthSkiPerfButton = nextMonthSkiPerfButton;
	}

	public JButton getNextMonthCyclingPerfButton() {
		return nextMonthCyclingPerfButton;
	}

	public void setNextMonthCyclingPerfButton(JButton nextMonthCyclingPerfButton) {
		this.nextMonthCyclingPerfButton = nextMonthCyclingPerfButton;
	}

	public JButton getNextMonthBodybuildingPerfButton() {
		return nextMonthBodybuildingPerfButton;
	}

	public void setNextMonthBodybuildingPerfButton(
			JButton nextMonthBodybuildingPerfButton) {
		this.nextMonthBodybuildingPerfButton = nextMonthBodybuildingPerfButton;
	}

	public JButton getPreviousMonthClimbingPerfButton() {
		return previousMonthClimbingPerfButton;
	}

	public void setPreviousMonthClimbingPerfButton(
			JButton previousMonthClimbingPerfButton) {
		this.previousMonthClimbingPerfButton = previousMonthClimbingPerfButton;
	}

	public JButton getPreviousMonthSkiPerfButton() {
		return previousMonthSkiPerfButton;
	}

	public void setPreviousMonthSkiPerfButton(JButton previousMonthSkiPerfButton) {
		this.previousMonthSkiPerfButton = previousMonthSkiPerfButton;
	}

	public JButton getPreviousMonthCyclingPerfButton() {
		return previousMonthCyclingPerfButton;
	}

	public void setPreviousMonthCyclingPerfButton(
			JButton previousMonthCyclingPerfButton) {
		this.previousMonthCyclingPerfButton = previousMonthCyclingPerfButton;
	}

	public JButton getPreviousMonthBodybuildingPerfButton() {
		return previousMonthBodybuildingPerfButton;
	}

	public void setPreviousMonthBodybuildingPerfButton(
			JButton previousMonthBodybuildingPerfButton) {
		this.previousMonthBodybuildingPerfButton = previousMonthBodybuildingPerfButton;
	}

	public Box getClimbingPerfButtonBox() {
		return climbingPerfButtonBox;
	}

	public void setClimbingPerfButtonBox(Box climbingPerfButtonBox) {
		this.climbingPerfButtonBox = climbingPerfButtonBox;
	}

	public Box getCyclingPerfButtonBox() {
		return cyclingPerfButtonBox;
	}

	public void setCyclingPerfButtonBox(Box cyclingPerfButtonBox) {
		this.cyclingPerfButtonBox = cyclingPerfButtonBox;
	}

	public Box getSkiPerfButtonBox() {
		return skiPerfButtonBox;
	}

	public void setSkiPerfButtonBox(Box skiPerfButtonBox) {
		this.skiPerfButtonBox = skiPerfButtonBox;
	}

	public Box getBodybuildingPerfButtonBox() {
		return bodybuildingPerfButtonBox;
	}

	public void setBodybuildingPerfButtonBox(Box bodybuildingPerfButtonBox) {
		this.bodybuildingPerfButtonBox = bodybuildingPerfButtonBox;
	}

	public Box getClimbingPerfMainBox() {
		return climbingPerfMainBox;
	}

	public void setClimbingPerfMainBox(Box climbingPerfMainBox) {
		this.climbingPerfMainBox = climbingPerfMainBox;
	}

	public Box getCyclingPerfMainBox() {
		return cyclingPerfMainBox;
	}

	public void setCyclingPerfMainBox(Box cyclingPerfMainBox) {
		this.cyclingPerfMainBox = cyclingPerfMainBox;
	}

	public Box getSkiPerfMainBox() {
		return skiPerfMainBox;
	}

	public void setSkiPerfMainBox(Box skiPerfMainBox) {
		this.skiPerfMainBox = skiPerfMainBox;
	}

	public Box getBodybuildingPerfMainBox() {
		return bodybuildingPerfMainBox;
	}

	public void setBodybuildingPerfMainBox(Box bodybuildingPerfMainBox) {
		this.bodybuildingPerfMainBox = bodybuildingPerfMainBox;
	}

	public ChartPanel getCurrentClimbingPerfChartPanel() {
		return currentClimbingPerfChartPanel;
	}

	public void setCurrentClimbingPerfChartPanel(
			ChartPanel currentClimbingPerfChartPanel) {
		this.currentClimbingPerfChartPanel = currentClimbingPerfChartPanel;
	}

	public ChartPanel getCurrentSkiPerfChartPanel() {
		return currentSkiPerfChartPanel;
	}

	public void setCurrentSkiPerfChartPanel(ChartPanel currentSkiPerfChartPanel) {
		this.currentSkiPerfChartPanel = currentSkiPerfChartPanel;
	}

	public ChartPanel getCurrentCyclingPerfChartPanel() {
		return currentCyclingPerfChartPanel;
	}

	public void setCurrentCyclingPerfChartPanel(
			ChartPanel currentCyclingPerfChartPanel) {
		this.currentCyclingPerfChartPanel = currentCyclingPerfChartPanel;
	}

	public ChartPanel getCurrentBodybuildingPerfChartPanel() {
		return currentBodybuildingPerfChartPanel;
	}

	public void setCurrentBodybuildingPerfChartPanel(
			ChartPanel currentBodybuildingPerfChartPanel) {
		this.currentBodybuildingPerfChartPanel = currentBodybuildingPerfChartPanel;
	}

	public JPanel getNextMonthClimbingPerfPanel() {
		return nextMonthClimbingPerfPanel;
	}

	public void setNextMonthClimbingPerfPanel(JPanel nextMonthClimbingPerfPanel) {
		this.nextMonthClimbingPerfPanel = nextMonthClimbingPerfPanel;
	}

	public JPanel getNextMonthCyclingPerfPanel() {
		return nextMonthCyclingPerfPanel;
	}

	public void setNextMonthCyclingPerfPanel(JPanel nextMonthCyclingPerfPanel) {
		this.nextMonthCyclingPerfPanel = nextMonthCyclingPerfPanel;
	}

	public JPanel getNextMonthSkiPerfPanel() {
		return nextMonthSkiPerfPanel;
	}

	public void setNextMonthSkiPerfPanel(JPanel nextMonthSkiPerfPanel) {
		this.nextMonthSkiPerfPanel = nextMonthSkiPerfPanel;
	}

	public JPanel getNextMonthBodybuildingPerfPanel() {
		return nextMonthBodybuildingPerfPanel;
	}

	public void setNextMonthBodybuildingPerfPanel(
			JPanel nextMonthBodybuildingPerfPanel) {
		this.nextMonthBodybuildingPerfPanel = nextMonthBodybuildingPerfPanel;
	}

	public JPanel getPreviousMonthClimbingPerfPanel() {
		return previousMonthClimbingPerfPanel;
	}

	public void setPreviousMonthClimbingPerfPanel(
			JPanel previousMonthClimbingPerfPanel) {
		this.previousMonthClimbingPerfPanel = previousMonthClimbingPerfPanel;
	}

	public JPanel getPreviousMonthSkiPerfPanel() {
		return previousMonthSkiPerfPanel;
	}

	public void setPreviousMonthSkiPerfPanel(JPanel previousMonthSkiPerfPanel) {
		this.previousMonthSkiPerfPanel = previousMonthSkiPerfPanel;
	}

	public JPanel getPreviousMonthCyclingPerfPanel() {
		return previousMonthCyclingPerfPanel;
	}

	public void setPreviousMonthCyclingPerfPanel(
			JPanel previousMonthCyclingPerfPanel) {
		this.previousMonthCyclingPerfPanel = previousMonthCyclingPerfPanel;
	}

	public JPanel getPreviousMonthBodybuildingPerfPanel() {
		return previousMonthBodybuildingPerfPanel;
	}

	public void setPreviousMonthBodybuildingPerfPanel(
			JPanel previousMonthBodybuildingPerfPanel) {
		this.previousMonthBodybuildingPerfPanel = previousMonthBodybuildingPerfPanel;
	}

	public JTabbedPane getChoiceTabbedPane() {
		return choiceTabbedPane;
	}

	public void setChoiceTabbedPane(JTabbedPane choiceTabbedPane) {
		this.choiceTabbedPane = choiceTabbedPane;
	}

	public CyclingPerformancesChart getCyclingPerfChart() {
		return cyclingPerfChart;
	}

	public void setCyclingPerfChart(CyclingPerformancesChart cyclingPerfChart) {
		this.cyclingPerfChart = cyclingPerfChart;
	}

	public int getCurrentMonthClimbing() {
		return currentMonthClimbing;
	}

	public void setCurrentMonthClimbing(int currentMonthClimbing) {
		this.currentMonthClimbing = currentMonthClimbing;
	}

	public int getCurrentYearClimbing() {
		return currentYearClimbing;
	}

	public void setCurrentYearClimbing(int currentYearClimbing) {
		this.currentYearClimbing = currentYearClimbing;
	}

	public ClimbingPerformancesChart getClimbingPerfChart() {
		return climbingPerfChart;
	}

	public void setClimbingPerfChart(ClimbingPerformancesChart climbingPerfChart) {
		this.climbingPerfChart = climbingPerfChart;
	}

	public int getCurrentMonthBodybuilding() {
		return currentMonthBodybuilding;
	}

	public void setCurrentMonthBodybuilding(int currentMonthBodybuilding) {
		this.currentMonthBodybuilding = currentMonthBodybuilding;
	}

	public int getCurrentYearBodybuilding() {
		return currentYearBodybuilding;
	}

	public void setCurrentYearBodybuilding(int currentYearBodybuilding) {
		this.currentYearBodybuilding = currentYearBodybuilding;
	}

	public BodybuildingPerformancesChart getBodybuildingPerfChart() {
		return bodybuildingPerfChart;
	}

	public void setBodybuildingPerfChart(
			BodybuildingPerformancesChart bodybuildingPerfChart) {
		this.bodybuildingPerfChart = bodybuildingPerfChart;
	}

	public int getCurrentMonthSki() {
		return currentMonthSki;
	}

	public void setCurrentMonthSki(int currentMonthSki) {
		this.currentMonthSki = currentMonthSki;
	}

	public int getCurrentYearSki() {
		return currentYearSki;
	}

	public void setCurrentYearSki(int currentYearSki) {
		this.currentYearSki = currentYearSki;
	}

	public SkiPerformancesChart getSkiPerfChart() {
		return skiPerfChart;
	}

	public void setSkiPerfChart(SkiPerformancesChart skiPerfChart) {
		this.skiPerfChart = skiPerfChart;
	}

	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}
	
	
}
