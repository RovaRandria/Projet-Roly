package gui;

import java.util.Calendar;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;

import chart.JoggingPerformancesChart;
import data.User;


public class PerformanceChartPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private User user;
	private int currentMonth;
	private int currentYear;
	private JButton nextMonthJoggingPerfButton = new JButton("Suivant");
	private JButton previousMonthJoggingPerfButton = new JButton("Précédent");
	private JoggingPerformancesChart joggingPerfChart;

	private Box joggingPerfButtonBox = Box.createHorizontalBox();
	private Box joggingPerfMainBox = Box.createVerticalBox();

	private ChartPanel currentJoggingPerfChartPanel;

	private JPanel previousMonthWaistSizePanel = new JPanel();
	private JPanel nextMonthJoggingPerfPanel = new JPanel();
	private JPanel previousMonthJoggingPerfPanel = new JPanel();

	public PerformanceChartPanel(User user) {
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
		joggingPerfChart = new JoggingPerformancesChart("Performances jogging", currentMonth, currentYear, user);
		currentJoggingPerfChartPanel = joggingPerfChart.showJoggingPerfPanel();

		nextMonthJoggingPerfPanel.add(nextMonthJoggingPerfButton);
		previousMonthWaistSizePanel.add(previousMonthJoggingPerfButton);
		joggingPerfButtonBox.add(previousMonthJoggingPerfPanel);
		joggingPerfButtonBox.add(nextMonthJoggingPerfPanel);

		joggingPerfMainBox.add(currentJoggingPerfChartPanel);
		joggingPerfMainBox.add(joggingPerfButtonBox);
		add(joggingPerfMainBox);
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

	public JPanel getPreviousMonthWaistSizePanel() {
		return previousMonthWaistSizePanel;
	}

	public void setPreviousMonthWaistSizePanel(JPanel previousMonthWaistSizePanel) {
		this.previousMonthWaistSizePanel = previousMonthWaistSizePanel;
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


}
