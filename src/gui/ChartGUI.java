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

import chart.WeightChart;
import data.DBConnection;
import data.User;


public class ChartGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private User user;
	private int currentMonth;
	private int currentYear;
	private JButton nextMonth = new JButton("Suivant");
	private JButton previousMonth = new JButton("Précédent");
	
	private Box buttonMonthBox = Box.createHorizontalBox();
	private Box weightMainBox = Box.createVerticalBox();
	private Box waistSiseMainBox = Box.createVerticalBox();
	
	private ChartPanel currentChartPanel;
	
	private JTabbedPane choiceTabbedPane = new JTabbedPane();
	
	private JPanel nextMonthPanel = new JPanel();
	private JPanel previousMonthPanel = new JPanel();
	private JPanel homePanel = new JPanel();

	
	public ChartGUI(User user) {
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
		WeightChart weightChart = new WeightChart("Courbe de poids", currentMonth, currentYear, user);	
		currentChartPanel = weightChart.showWeightPanel();
		
		nextMonthPanel.add(nextMonth);
		previousMonthPanel.add(previousMonth);
		buttonMonthBox.add(previousMonthPanel);
		buttonMonthBox.add(nextMonthPanel);
		
		weightMainBox.add(currentChartPanel);
		weightMainBox.add(buttonMonthBox);
		
		
		/*
		 * Tab with different chart's choices
		 */
		choiceTabbedPane.addTab("Poids", weightMainBox);
		choiceTabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		choiceTabbedPane.addTab("Tour de taille", waistSiseMainBox);
		choiceTabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		
		homePanel.add(choiceTabbedPane);
		
		RefineryUtilities.centerFrameOnScreen(this);
		this.add(homePanel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setResizable(false);
		
	}
	
	public void initStyle(){
		
	}
	
	public void initActions(){
		previousMonth.addActionListener(new previousMonthAction());
		nextMonth.addActionListener(new nextMonthAction());
	}
	
	private class previousMonthAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (currentMonth==1){
				currentMonth=12;
				currentYear--;
			}else
				currentMonth--;
				
			System.out.println("Mois précédent : "+currentMonth+"/"+currentYear);

			WeightChart weightChart = new WeightChart("Courbe de poids", currentMonth, currentYear, user);	
			currentChartPanel.removeAll();
			currentChartPanel = weightChart.showWeightPanel();
			//currentChartPanel.repaint();
			//instance.repaint();
			init();
		}
	}
	
	private class nextMonthAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (currentMonth==12){
				currentMonth=1;
				currentYear++;
			}else
				currentMonth++;
			System.out.println("Mois suivant : "+currentMonth+"/"+currentYear);
			WeightChart weightChart = new WeightChart("Courbe de poids", currentMonth, currentYear, user);	
			currentChartPanel.removeAll();
			currentChartPanel = weightChart.showWeightPanel();
			//currentChartPanel.repaint();
			//instance.repaint();
			init();
		}
	}

	
	public static void main(String[] args) {
		Session session = DBConnection.getSession();
		session.beginTransaction();
		User user = (User) session.get(User.class, "l");
		new ChartGUI(user);
	}
}
	