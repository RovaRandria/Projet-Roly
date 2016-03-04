package gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.hibernate.Session;

import utils.DataUtility;
import data.DBConnection;
import data.User;

public class PracticePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private User user;

	private JComboBox dayComboBox = new JComboBox(DataUtility.day().toArray());
	private JComboBox monthComboBox = new JComboBox(DataUtility.month().toArray());
	private JComboBox yearComboBox = new JComboBox(DataUtility.year().toArray());
	private JComboBox sportComboBox = new JComboBox();	
	private JComboBox exercisesComboBox = new JComboBox(DataUtility.getExercisesListString().toArray());
	private JComboBox colorComboBox = new JComboBox(DataUtility.getClimbingColorsString().toArray());

	private JButton addPracticeButton = new JButton("Ajouter");
	private JButton backButton = new JButton("Retour aux sports");

	private JLabel titleLabel = new JLabel("Mes séances de sports");

	private JTextField durationTextField;
	private JTextField performanceTextField;
	private JTextField placeTextField;
	
	private JPanel datePanel = new JPanel();

	private static final Font TITLE_FONT = new Font("Arial", Font.ITALIC|Font.BOLD, 20);
	
	public PracticePanel() {
	}

	public PracticePanel(User user) {
		this.user = user;
		initStyle();
		init();
		initActions();
	}

	public void init() {
		Session session = DBConnection.getSession();
		session.beginTransaction();
		user = (User) session.get(User.class, user.getPseudo());

		durationTextField = new JTextField(25);
		performanceTextField = new JTextField(25);
		placeTextField = new JTextField(25);
		
		ArrayList<String> sportString = new ArrayList<String>();
		for(int i = 0; i < user.getProfile().getSportsList().size(); i++) {
			sportString.add(user.getProfile().getSportsList().get(i).getName());
		}
		sportComboBox = new JComboBox(sportString.toArray());	
		
		Calendar cal = Calendar.getInstance();
		dayComboBox.setSelectedItem(cal.get(Calendar.DAY_OF_MONTH));
		monthComboBox.setSelectedItem(cal.get(Calendar.MONTH)+1);
		yearComboBox.setSelectedItem(cal.get(Calendar.YEAR));
		datePanel.add(dayComboBox);
		datePanel.add(monthComboBox);
		datePanel.add(yearComboBox);

		
		this.setLayout(new GridBagLayout());
		GridBagConstraints frameConstraints = new GridBagConstraints();
		
		frameConstraints.insets = new Insets(5, 0, 40, 0);
		frameConstraints.gridwidth = 2;
		frameConstraints.gridx = 0;
		frameConstraints.gridy = 0;
		add(titleLabel, frameConstraints);
		
		frameConstraints.insets = new Insets(5, 10, 5, 40);
		frameConstraints.gridwidth = 1;
		frameConstraints.anchor = GridBagConstraints.WEST;
		frameConstraints.fill = GridBagConstraints.NONE;
		frameConstraints.gridy = GridBagConstraints.RELATIVE;
		add(new JLabel("/* Affichage de toutes les séances */"), frameConstraints);
		add(new JLabel("Sport : "), frameConstraints);
		add(new JLabel("Date : "), frameConstraints);
		add(new JLabel("Lieu : "), frameConstraints);
		add(new JLabel("Durée : "), frameConstraints);
		add(new JLabel("Exercices : "), frameConstraints);
		add(new JLabel("Performance : "), frameConstraints);

		frameConstraints.anchor = GridBagConstraints.CENTER;
		frameConstraints.fill = GridBagConstraints.CENTER;
		frameConstraints.gridx = 1;
		frameConstraints.gridy = 2;
		this.add(sportComboBox, frameConstraints);
		frameConstraints.gridy = GridBagConstraints.RELATIVE;
		this.add(datePanel, frameConstraints);
		this.add(placeTextField, frameConstraints);
		this.add(durationTextField, frameConstraints);
		this.add(exercisesComboBox, frameConstraints);
		this.add(performanceTextField, frameConstraints);
		
		frameConstraints.gridwidth = 2;
		frameConstraints.gridx = 0;
		frameConstraints.insets = new Insets(30, 0, 5, 0);
		this.add(addPracticeButton, frameConstraints);
		frameConstraints.insets = new Insets(90, 0, 5, 0);
		this.add(backButton, frameConstraints);
		
		session.getTransaction().commit();
	}

	public void initStyle() {
		titleLabel.setFont(TITLE_FONT);
	}

	public void initActions() {		
		
	}


	public JComboBox getDayComboBox() {
		return dayComboBox;
	}

	public void setDayComboBox(JComboBox dayComboBox) {
		this.dayComboBox = dayComboBox;
	}

	public JComboBox getMonthComboBox() {
		return monthComboBox;
	}

	public void setMonthComboBox(JComboBox monthComboBox) {
		this.monthComboBox = monthComboBox;
	}

	public JComboBox getYearComboBox() {
		return yearComboBox;
	}

	public void setYearComboBox(JComboBox yearComboBox) {
		this.yearComboBox = yearComboBox;
	}

	public JComboBox getSportComboBox() {
		return sportComboBox;
	}

	public void setSportComboBox(JComboBox sportComboBox) {
		this.sportComboBox = sportComboBox;
	}

	public JComboBox getExercisesComboBox() {
		return exercisesComboBox;
	}

	public void setExercisesComboBox(JComboBox exercisesComboBox) {
		this.exercisesComboBox = exercisesComboBox;
	}

	public JComboBox getColorComboBox() {
		return colorComboBox;
	}

	public void setColorComboBox(JComboBox colorComboBox) {
		this.colorComboBox = colorComboBox;
	}

	public JButton getAddPracticeButton() {
		return addPracticeButton;
	}

	public void setAddPracticeButton(JButton addPracticeButton) {
		this.addPracticeButton = addPracticeButton;
	}

	public JTextField getDurationTextField() {
		return durationTextField;
	}

	public void setDurationTextField(JTextField durationTextField) {
		this.durationTextField = durationTextField;
	}

	public JTextField getPerformanceTextField() {
		return performanceTextField;
	}

	public void setPerformanceTextField(JTextField performanceTextField) {
		this.performanceTextField = performanceTextField;
	}

	public JTextField getPlaceTextField() {
		return placeTextField;
	}

	public void setPlaceTextField(JTextField placeTextField) {
		this.placeTextField = placeTextField;
	}

	public JButton getBackButton() {
		return backButton;
	}

	public void setBackButton(JButton backButton) {
		this.backButton = backButton;
	}
	
}

