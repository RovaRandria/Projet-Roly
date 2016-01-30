package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.hibernate.Session;

import utils.AvailableExercises;
import utils.DataUtility;
import utils.DateNumbersList;
import data.DBConnection;
import data.Exercise;
import data.Practice;
import data.Profile;
import data.Sport;
import data.User;

public class PracticeManagerFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private User user;

	private PracticeManagerFrame instance = this;

	private JComboBox dayComboBox = new JComboBox(DateNumbersList.day().toArray());
	private JComboBox monthComboBox = new JComboBox(DateNumbersList.month().toArray());
	private JComboBox yearComboBox = new JComboBox(DateNumbersList.year().toArray());
	private JComboBox sportComboBox = new JComboBox();	
	private JComboBox exercisesComboBox = new JComboBox(AvailableExercises.getExercisesListString().toArray());

	private JButton addPracticeButton = new JButton("Ajouter");

	private JLabel dateLabel = new JLabel("Date : ");
	private JLabel sportLabel = new JLabel("Sport : ");
	private JLabel durationLabel = new JLabel("Durée : ");
	private JLabel placeLabel = new JLabel("Lieu : ");
	private JLabel performanceLabel = new JLabel("Performance : ");
	private JLabel exercisesLabel = new JLabel("Exercices : ");

	private JTextField durationTextField = new JTextField(25);
	private JTextField performanceTextField = new JTextField(25);
	private JTextField placeTextField = new JTextField(25);

	public PracticeManagerFrame() {
	}

	public PracticeManagerFrame(User user) {
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
		GridBagConstraints frameConstraints = new GridBagConstraints();

		ArrayList<String> sportString = new ArrayList<String>();
		for(int i = 0; i < user.getProfile().getSportsList().size(); i++) {
			sportString.add(user.getProfile().getSportsList().get(i).getName());
		}
		sportComboBox = new JComboBox(sportString.toArray());	
		frameConstraints.gridx = 0; 
		frameConstraints.gridy = 0; 
		this.add(sportLabel, frameConstraints);
		frameConstraints.gridx = 1; 
		frameConstraints.gridy = 0; 
		this.add(sportComboBox, frameConstraints);
		frameConstraints.gridx = 0; 
		frameConstraints.gridy = 1; 
		this.add(dateLabel, frameConstraints);
		frameConstraints.gridx = 1; 
		frameConstraints.gridy = 1; 
		this.add(dayComboBox, frameConstraints);
		frameConstraints.gridx = 2; 
		frameConstraints.gridy = 1; 
		this.add(monthComboBox, frameConstraints);
		frameConstraints.gridx = 3; 
		frameConstraints.gridy = 1; 
		this.add(yearComboBox, frameConstraints);
		frameConstraints.gridx = 0; 
		frameConstraints.gridy = 2; 
		this.add(placeLabel, frameConstraints);
		frameConstraints.gridx = 1; 
		frameConstraints.gridy = 2; 
		this.add(placeTextField, frameConstraints);
		frameConstraints.gridx = 0; 
		frameConstraints.gridy = 3; 
		this.add(durationLabel, frameConstraints);
		frameConstraints.gridx = 1; 
		frameConstraints.gridy = 3; 
		this.add(durationTextField, frameConstraints);
		frameConstraints.gridx = 0; 
		frameConstraints.gridy = 4; 
		this.add(performanceLabel, frameConstraints);
		frameConstraints.gridx = 1; 
		frameConstraints.gridy = 4; 
		this.add(performanceTextField, frameConstraints);
		frameConstraints.gridx = 0; 
		frameConstraints.gridy = 5; 
		this.add(exercisesLabel, frameConstraints);
		frameConstraints.gridx = 1; 
		frameConstraints.gridy = 5; 
		this.add(exercisesComboBox, frameConstraints);
		frameConstraints.gridx = 2; 
		frameConstraints.gridy = 5; 
		this.add(addPracticeButton, frameConstraints);
		session.getTransaction().commit();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setResizable(false);
	}

	public void initStyle() {
		//Font & Backgrounds Settings
	}

	public void initActions() {		
		addPracticeButton.addActionListener(new addPracticeAction());
	}

	private class addPracticeAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Session session = DBConnection.getSession();
			session.beginTransaction();
			Profile profile = (Profile) session.get(Profile.class, user.getProfile().getId());				  
			Sport sport = (Sport) session.get(Sport.class, sportComboBox.getSelectedItem().toString());
			Date date = DataUtility.createDate((Integer)dayComboBox.getSelectedItem(), (Integer)monthComboBox.getSelectedItem(), (Integer)yearComboBox.getSelectedItem());
			Practice practice = new Practice(sport, date, placeTextField.getText(), Float.parseFloat(durationTextField.getText()), performanceTextField.getText(), profile);
			Exercise exercise = (Exercise) session.get(Exercise.class, exercisesComboBox.getSelectedItem().toString());	
			practice.getExercisesList().add(exercise);
			profile.getPracticesList().add(practice);			  
			JOptionPane.showMessageDialog(instance, "Votre séance a bien été ajoutée !", "Séance ajoutée", JOptionPane.INFORMATION_MESSAGE);
			session.merge(profile);
			session.getTransaction().commit();
			instance.dispose();
		}
	}
}

