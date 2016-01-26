package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.hibernate.Session;

import utils.AvailableSports;
import data.DBConnection;
import data.Profile;
import data.Sport;


public class ProfileGUI extends JFrame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProfileGUI profileGUI = new ProfileGUI("Pass'Sport", "1");
	}
	
	private static final long serialVersionUID = 1L;
	private Profile profile;
	private String id;
	private AvailableSports availableSports;
	
	private JPanel homePanel = new JPanel();

	private JLabel nameLabel;
	private JLabel birthdateLabel;
	private JLabel genderLabel;
	
	private JComboBox sportComboBox = new JComboBox(availableSports.getSportsListString().toArray());
	
	public ProfileGUI(String title, String id) {
		super(title);
		this.id = id;
		profile = new Profile();
		initStyle();
		init();
		initActions();

	}
	private void init() {
		Session session = DBConnection.getSession();
		session.beginTransaction();
		Profile retrievedProfile = (Profile) session.get(Profile.class, id);
		
		//retrievedProfile.setSportsList(sportsList);
		session.close();
		List <Sport> sportsList = availableSports.getSportsList();
		
		
		nameLabel = new JLabel(retrievedProfile.getFirstName()+" "+retrievedProfile.getLastName());
		birthdateLabel = new JLabel(retrievedProfile.getBirthdate().toString());
		genderLabel = new JLabel(retrievedProfile.getGender().toString());
		//System.out.println("list sport = "+retrievedProfile.getSportsList().size());
		
		homePanel.add(nameLabel);
		homePanel.add(birthdateLabel);
		homePanel.add(genderLabel);
		homePanel.add(sportComboBox);
		this.add(homePanel);
		//this.setPreferredSize(new Dimension(800, 600));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setResizable(false);
	}
	
	private void initStyle() {
	//Font & Backgrounds Settings
	}
	
	public void initActions() {		

	}
	

	class NewRegistrationAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

		}
	}

}
