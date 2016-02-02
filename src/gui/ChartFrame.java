package gui;

import java.util.Calendar;

import javax.swing.JFrame;

import chart.WeightChart;
import data.User;


public class ChartFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private User user;
	
//	private JButton nextMonth = new JButton("Suivant");
//	private JButton previousMonth = new JButton("Précédent");
//	
//	private JPanel homePanel = new JPanel();
	
	public ChartFrame(User user) {
		super();
		this.user = user;
		initStyle();
		init();
		initActions();
	}
	public void init() {
		Calendar cal = Calendar.getInstance();
		new WeightChart("Courbe de poids", cal.get(Calendar.MONTH)+1, cal.get(Calendar.YEAR), user);	
		
//		homePanel.add(nextMonth);
//		homePanel.add(previousMonth);
//		
//		this.add(homePanel);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		pack();
//		setVisible(true);
//		setResizable(false);
		
	}
	
	public void initStyle(){
		
	}
	
	public void initActions(){
		
	}
	
}
	