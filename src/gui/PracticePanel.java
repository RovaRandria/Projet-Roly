package gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
	
	private String sportName;

	private JComboBox dayComboBox = new JComboBox(DataUtility.day().toArray());
	private JComboBox monthComboBox = new JComboBox(DataUtility.month().toArray());
	private JComboBox yearComboBox = new JComboBox(DataUtility.year().toArray());
	
	private JComboBox yellowClimbingRouteComboBox = new JComboBox(DataUtility.numberOfSeries().toArray());
	private JComboBox orangeClimbingRouteComboBox = new JComboBox(DataUtility.numberOfSeries().toArray());
	private JComboBox blueClimbingRouteComboBox = new JComboBox(DataUtility.numberOfSeries().toArray());
	private JComboBox redClimbingRouteComboBox = new JComboBox(DataUtility.numberOfSeries().toArray());
	private JComboBox whiteClimbingRouteComboBox = new JComboBox(DataUtility.numberOfSeries().toArray());
	private JComboBox blackClimbingRouteComboBox = new JComboBox(DataUtility.numberOfSeries().toArray());
	private JComboBox greenClimbingRouteComboBox = new JComboBox(DataUtility.numberOfSeries().toArray());
	
	private JComboBox greenTrackComboBox = new JComboBox(DataUtility.numberOfSeries().toArray());
	private JComboBox blueTrackComboBox = new JComboBox(DataUtility.numberOfSeries().toArray());
	private JComboBox redTrackComboBox = new JComboBox(DataUtility.numberOfSeries().toArray());
	private JComboBox blackTrackComboBox = new JComboBox(DataUtility.numberOfSeries().toArray());
	
	private JComboBox pushupComboBox = new JComboBox(DataUtility.numberOfSeries().toArray());
	private JComboBox situpComboBox = new JComboBox(DataUtility.numberOfSeries().toArray());
	private JComboBox pullupComboBox = new JComboBox(DataUtility.numberOfSeries().toArray());
	private JComboBox dipsComboBox = new JComboBox(DataUtility.numberOfSeries().toArray());
	private JComboBox squatComboBox = new JComboBox(DataUtility.numberOfSeries().toArray());
	private JComboBox benchPressComboBox = new JComboBox(DataUtility.numberOfSeries().toArray());

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

	public PracticePanel(User user, String sportName) {
		this.user = user;
		this.sportName = sportName;
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
		add(new JLabel("Date : "), frameConstraints);
		add(new JLabel("Lieu : "), frameConstraints);
		add(new JLabel("Durée : "), frameConstraints);
		if(sportName.equals("Jogging")||sportName.equals("Vélo"))
			add(new JLabel("Temps de course : "), frameConstraints);
		else if(sportName.equals("Escalade")) {
			add(new JLabel("Voie jaune : "), frameConstraints);
			add(new JLabel("Voie orange : "), frameConstraints);
			add(new JLabel("Voie bleue : "), frameConstraints);
			add(new JLabel("Voie rouge : "), frameConstraints);
			add(new JLabel("Voie blanche : "), frameConstraints);
			add(new JLabel("Voie noire : "), frameConstraints);
			add(new JLabel("Voie verte : "), frameConstraints);
		}
		else if(sportName.equals("Ski")) {
			add(new JLabel("Piste verte : "), frameConstraints);
			add(new JLabel("Piste bleue : "), frameConstraints);
			add(new JLabel("Piste rouge : "), frameConstraints);
			add(new JLabel("Piste noire : "), frameConstraints);
		}
		else if(sportName.equals("Musculation")) {
			add(new JLabel("Pompes : "), frameConstraints);
			add(new JLabel("Abdominaux : "), frameConstraints);
			add(new JLabel("Tractions : "), frameConstraints);
			add(new JLabel("Dips : "), frameConstraints);
			add(new JLabel("Squat : "), frameConstraints);
			add(new JLabel("Développés-couchés : "), frameConstraints);			
		}
		frameConstraints.anchor = GridBagConstraints.CENTER;
		frameConstraints.fill = GridBagConstraints.CENTER;
		frameConstraints.gridx = 1;
		frameConstraints.gridy = 2;
		frameConstraints.gridy = GridBagConstraints.RELATIVE;
		this.add(datePanel, frameConstraints);
		this.add(placeTextField, frameConstraints);
		this.add(durationTextField, frameConstraints);
		if(sportName.equals("Jogging")||sportName.equals("Vélo"))
			this.add(performanceTextField, frameConstraints);
		else if(sportName.equals("Escalade")) {			
			this.add(yellowClimbingRouteComboBox, frameConstraints);
			this.add(orangeClimbingRouteComboBox, frameConstraints);
			this.add(blueClimbingRouteComboBox, frameConstraints);
			this.add(redClimbingRouteComboBox, frameConstraints);
			this.add(whiteClimbingRouteComboBox, frameConstraints);
			this.add(blackClimbingRouteComboBox, frameConstraints);
			this.add(greenClimbingRouteComboBox, frameConstraints);
		}
		else if(sportName.equals("Ski")) {	
			this.add(greenTrackComboBox, frameConstraints);
			this.add(blueTrackComboBox, frameConstraints);
			this.add(redTrackComboBox, frameConstraints);
			this.add(blackTrackComboBox, frameConstraints);
		}
		else if(sportName.equals("Musculation")) {	
			this.add(pushupComboBox, frameConstraints);
			this.add(situpComboBox, frameConstraints);
			this.add(pullupComboBox, frameConstraints);
			this.add(dipsComboBox, frameConstraints);
			this.add(squatComboBox, frameConstraints);
			this.add(benchPressComboBox, frameConstraints);			
		}
		add(new JLabel("/* Affichage de toutes les séances */"), frameConstraints);	
		frameConstraints.gridwidth = 2;
		frameConstraints.gridx = 0;
		frameConstraints.insets = new Insets(30, 0, 5, 0);
		this.add(addPracticeButton, frameConstraints);
		frameConstraints.insets = new Insets(50, 0, 5, 0);
		this.add(backButton, frameConstraints);
		
		session.getTransaction().commit();
	}

	public void initStyle() {
		titleLabel.setFont(TITLE_FONT);
		dayComboBox.setOpaque(false);
				monthComboBox.setOpaque(false);
				yearComboBox.setOpaque(false);
				addPracticeButton.setOpaque(false);
				backButton.setOpaque(false);
				titleLabel.setOpaque(false);
				datePanel.setOpaque(false);
				this.setOpaque(false);
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getSportName() {
		return sportName;
	}

	public void setSportName(String sportName) {
		this.sportName = sportName;
	}

	public JComboBox getYellowClimbingRouteComboBox() {
		return yellowClimbingRouteComboBox;
	}

	public void setYellowClimbingRouteComboBox(JComboBox yellowClimbingRouteComboBox) {
		this.yellowClimbingRouteComboBox = yellowClimbingRouteComboBox;
	}

	public JComboBox getOrangeClimbingRouteComboBox() {
		return orangeClimbingRouteComboBox;
	}

	public void setOrangeClimbingRouteComboBox(JComboBox orangeClimbingRouteComboBox) {
		this.orangeClimbingRouteComboBox = orangeClimbingRouteComboBox;
	}

	public JComboBox getBlueClimbingRouteComboBox() {
		return blueClimbingRouteComboBox;
	}

	public void setBlueClimbingRouteComboBox(JComboBox blueClimbingRouteComboBox) {
		this.blueClimbingRouteComboBox = blueClimbingRouteComboBox;
	}

	public JComboBox getRedClimbingRouteComboBox() {
		return redClimbingRouteComboBox;
	}

	public void setRedClimbingRouteComboBox(JComboBox redClimbingRouteComboBox) {
		this.redClimbingRouteComboBox = redClimbingRouteComboBox;
	}

	public JComboBox getWhiteClimbingRouteComboBox() {
		return whiteClimbingRouteComboBox;
	}

	public void setWhiteClimbingRouteComboBox(JComboBox whiteClimbingRouteComboBox) {
		this.whiteClimbingRouteComboBox = whiteClimbingRouteComboBox;
	}

	public JComboBox getBlackClimbingRouteComboBox() {
		return blackClimbingRouteComboBox;
	}

	public void setBlackClimbingRouteComboBox(JComboBox blackClimbingRouteComboBox) {
		this.blackClimbingRouteComboBox = blackClimbingRouteComboBox;
	}

	public JComboBox getGreenClimbingRouteComboBox() {
		return greenClimbingRouteComboBox;
	}

	public void setGreenClimbingRouteComboBox(JComboBox greenClimbingRouteComboBox) {
		this.greenClimbingRouteComboBox = greenClimbingRouteComboBox;
	}

	public JComboBox getGreenTrackComboBox() {
		return greenTrackComboBox;
	}

	public void setGreenTrackComboBox(JComboBox greenTrackComboBox) {
		this.greenTrackComboBox = greenTrackComboBox;
	}

	public JComboBox getBlueTrackComboBox() {
		return blueTrackComboBox;
	}

	public void setBlueTrackComboBox(JComboBox blueTrackComboBox) {
		this.blueTrackComboBox = blueTrackComboBox;
	}

	public JComboBox getRedTrackComboBox() {
		return redTrackComboBox;
	}

	public void setRedTrackComboBox(JComboBox redTrackComboBox) {
		this.redTrackComboBox = redTrackComboBox;
	}

	public JComboBox getBlackTrackComboBox() {
		return blackTrackComboBox;
	}

	public void setBlackTrackComboBox(JComboBox blackTrackComboBox) {
		this.blackTrackComboBox = blackTrackComboBox;
	}

	public JComboBox getPushupComboBox() {
		return pushupComboBox;
	}

	public void setPushupComboBox(JComboBox pushupComboBox) {
		this.pushupComboBox = pushupComboBox;
	}

	public JComboBox getSitupComboBox() {
		return situpComboBox;
	}

	public void setSitupComboBox(JComboBox situpComboBox) {
		this.situpComboBox = situpComboBox;
	}

	public JComboBox getPullupComboBox() {
		return pullupComboBox;
	}

	public void setPullupComboBox(JComboBox pullupComboBox) {
		this.pullupComboBox = pullupComboBox;
	}

	public JComboBox getDipsComboBox() {
		return dipsComboBox;
	}

	public void setDipsComboBox(JComboBox dipsComboBox) {
		this.dipsComboBox = dipsComboBox;
	}

	public JComboBox getSquatComboBox() {
		return squatComboBox;
	}

	public void setSquatComboBox(JComboBox squatComboBox) {
		this.squatComboBox = squatComboBox;
	}

	public JComboBox getBenchPressComboBox() {
		return benchPressComboBox;
	}

	public void setBenchPressComboBox(JComboBox benchPressComboBox) {
		this.benchPressComboBox = benchPressComboBox;
	}

	public JLabel getTitleLabel() {
		return titleLabel;
	}

	public void setTitleLabel(JLabel titleLabel) {
		this.titleLabel = titleLabel;
	}

	public JPanel getDatePanel() {
		return datePanel;
	}

	public void setDatePanel(JPanel datePanel) {
		this.datePanel = datePanel;
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

