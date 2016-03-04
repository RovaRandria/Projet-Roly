package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.hibernate.Session;

import data.DBConnection;
import data.User;

public class SportActivity extends JPanel {

	private static final long serialVersionUID = 1L;

	private User user;
	
	private JPanel sportPanel = new JPanel();
	
	private JButton showPhysicDataChartButton = new JButton("Voir évolution physique");
	
	private JScrollPane practicesJScrollPane = new JScrollPane();
	
	private JTextArea practicesTextArea = new JTextArea();
	
	private JLabel sportsLabel = new JLabel();
	private JLabel practicesLabel = new JLabel();
	
	public SportActivity() {
	}
	
	public SportActivity(User user) {
		this.user = user;
		initStyle();
		init();
		initActions();
	}
	
	public void init() {
		Session session = DBConnection.getSession();
		session.beginTransaction();
		user = (User) session.get(User.class, user.getPseudo());
		this.setLayout(new GridBagLayout());
		sportPanel.setLayout(new GridBagLayout());
		GridBagConstraints frameConstraints = new GridBagConstraints();
		frameConstraints.gridx = 0; 
		frameConstraints.gridy = 0; 
		sportPanel.add(sportsLabel, frameConstraints);
		sportsLabel.setText("Vous pratiquez : " + user.getProfile().displaySport());
		frameConstraints.gridx = 1; 
		frameConstraints.gridy = 0; 
		sportPanel.add(showPhysicDataChartButton, frameConstraints);
		frameConstraints.gridx = 0; 
		frameConstraints.gridy = 1; 
		sportPanel.add(practicesLabel, frameConstraints);
		practicesLabel.setText("Ses dernières séances : ");
		frameConstraints.gridx = 0; 
		frameConstraints.gridy = 2; 
		for(int i = 0; i < user.getProfile().getPracticesList().size(); i++) {
			practicesTextArea.append(user.getProfile().getPracticesList().get(i).toString()+"\n");
		}
		practicesTextArea.setEditable(false);
		practicesJScrollPane = new JScrollPane(practicesTextArea);

		sportPanel.setPreferredSize(new Dimension(700,100));
		practicesJScrollPane.setMinimumSize(new Dimension(500,100));

		frameConstraints.gridx = 0; 
		frameConstraints.gridy = 1; 
		this.add(practicesJScrollPane, frameConstraints);
		frameConstraints.gridx = 0; 
		frameConstraints.gridy = 0; 
		this.add(sportPanel, frameConstraints);
		session.getTransaction().commit();
	}
	
	public void initStyle() {
		//Font & Backgrounds Settings
	}
		
	public void initActions() {
		showPhysicDataChartButton.addActionListener(new showChartAction());
	}
	
	class showChartAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new PhysicalDataChartPanel(user);
		}
	}
	
}
