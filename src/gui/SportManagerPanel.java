package gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.hibernate.Session;

import utils.DataUtility;
import data.DBConnection;
import data.Practice;
import data.User;

/**
 * Panel that show the form to manage sports and practices
 * @author Angelique Nguyen & Rova Randrianantoanina
 * @version 1.0
 */	
public class SportManagerPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private User user;
	
	private JComboBox sportComboBox, sportComboBox2;

	private JButton addSportButton = new JButton("Ajouter");	
	private JButton removeSportButton = new JButton("Supprimer");
	private JButton showPracticePanelButton = new JButton("Ajouter une séance");
	private JButton showPerfChartButton = new JButton("Voir mes performances");
	private JButton backHomeButton = new JButton("Retour au profil");

	private JLabel titleLabel = new JLabel("Mes activités sportives");
	private JLabel sportsLabel = new JLabel("Non renseigné");
	private JLabel sportLastPracticeLabel = new JLabel();
	private JLabel dateLastPracticeLabel = new JLabel();
	private JLabel placeLastPracticeLabel = new JLabel();
	private JLabel durationLastPracticeLabel = new JLabel();
	private JLabel exerciceLastPracticeLabel = new JLabel();
	private JLabel performanceLastPracticeLabel = new JLabel();
	private JLabel noSportsLabel = new JLabel("Pour pouvoir ajouter une séance, ajoutez un sport.");
	private JLabel sportsTitleLabel1;
	private JLabel sportsTitleLabel2;
	private JLabel sportsTitleLabel3;
	
	private JPanel addDeletePanel = new JPanel();
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	private static final Font TITLE_FONT = new Font("Arial", Font.ITALIC|Font.BOLD, 15);

	public SportManagerPanel() {
	}

	/**
	 * Constructor
	 * @param user : user logged in
	 * @see User
	 */
	public SportManagerPanel(User user) {
		this.user = user;
		init();
		initStyle();
	}

	/**
	 * Method that reinitialize the main panel
	 */
	public void repaintPanel(){
		init();
		initStyle();
	}

	/**
	 * Method that initialize the components on the panel
	 */
	public void init() {		
		Session session = DBConnection.getSession();
		session.beginTransaction();
		user = (User) session.get(User.class, user.getPseudo());
		if (user.getProfile().getSportsList().size()>0){
			sportsLabel.setText(user.getProfile().displaySport());
		}
		else{
			sportsLabel.setText("Non renseigné");
		}
		if (sportComboBox==null)
			sportComboBox = new JComboBox(DataUtility.getSportsListString().toArray());
		else{
			DefaultComboBoxModel model = new DefaultComboBoxModel(DataUtility.getSportsListString().toArray());
			sportComboBox.setModel(model);
		}
			


		addDeletePanel.add(addSportButton);
		addDeletePanel.add(removeSportButton);

		if (user.getProfile().getPracticesList().size()>0){
			Practice lastPractice = user.getProfile().getPracticesList().get(user.getProfile().getPracticesList().size()-1);
			if (sportLastPracticeLabel.getText().equals(""))
				sportLastPracticeLabel = new JLabel("Sport : "+lastPractice.getSport().getName());
			if (dateLastPracticeLabel.getText().equals(""))
				dateLastPracticeLabel = new JLabel("Date : "+sdf.format(lastPractice.getDate()));
			if (placeLastPracticeLabel.getText().equals(""))
				placeLastPracticeLabel = new JLabel("Lieu : "+lastPractice.getPlace());
			if (durationLastPracticeLabel.getText().equals(""))
				durationLastPracticeLabel = new JLabel("Durée (en minutes) : "+lastPractice.getDuration());
			if (performanceLastPracticeLabel.getText().equals(""))
				performanceLastPracticeLabel = new JLabel("Temps de course (en minutes) : "+lastPractice.getPerformance());
		}
		else{
			if (sportLastPracticeLabel.getText().equals(""))
				sportLastPracticeLabel = new JLabel("Non renseigné");
		}

		this.setLayout(new GridBagLayout());
		GridBagConstraints frameConstraints = new GridBagConstraints();

		frameConstraints.insets = new Insets(5, 0, 40, 0);
		frameConstraints.gridwidth = 2;
		frameConstraints.gridx = 0; 
		frameConstraints.gridy = 0; 
		add(titleLabel, frameConstraints);

		frameConstraints.anchor = GridBagConstraints.WEST;
		frameConstraints.fill = GridBagConstraints.NONE;
		frameConstraints.insets = new Insets(20, 0, 5, 20);
		frameConstraints.gridwidth = 1;
		if (sportsTitleLabel1==null)
			sportsTitleLabel1 = new JLabel("Sport(s) pratiqué(s) : ");
		frameConstraints.gridy = 1; 
		add(sportsTitleLabel1, frameConstraints);
		
		if (sportsTitleLabel2==null)
			sportsTitleLabel2 = new JLabel("Gérer mes sports : ");
		frameConstraints.gridy = 3; 
		add(sportsTitleLabel2, frameConstraints);
		
		if (sportsTitleLabel3==null)
			sportsTitleLabel3 = new JLabel("Dernière séance : ");
		frameConstraints.gridy = 5; 
		add(sportsTitleLabel3, frameConstraints);
		

		frameConstraints.gridx = 1;
		frameConstraints.gridy = 1; 
		frameConstraints.insets = new Insets(20, 20, 5, 0);
		add(sportsLabel, frameConstraints);
		frameConstraints.gridy = GridBagConstraints.RELATIVE; 
		frameConstraints.insets = new Insets(5, 20, 20, 0);
		add(showPerfChartButton, frameConstraints);
		frameConstraints.insets = new Insets(20, 20, 5, 0);
		add(sportComboBox, frameConstraints);
		frameConstraints.insets = new Insets(5, 20, 20, 0);
		add(addDeletePanel, frameConstraints);

		frameConstraints.insets = new Insets(3, 20, 3, 0);
		add(sportLastPracticeLabel, frameConstraints);
		if (user.getProfile().getPracticesList().size()>0){
			add(dateLastPracticeLabel, frameConstraints);
			add(placeLastPracticeLabel, frameConstraints);
			add(durationLastPracticeLabel, frameConstraints);
			add(exerciceLastPracticeLabel, frameConstraints);
			if((user.getProfile().getPracticesList().get(user.getProfile().getPracticesList().size()-1).getSport().getName().equals("Jogging"))||(user.getProfile().getPracticesList().get(user.getProfile().getPracticesList().size()-1).getSport().getName().equals("Vélo")))
				add(performanceLastPracticeLabel, frameConstraints);
		}
		
		
		frameConstraints.insets = new Insets(10, 20, 10, 0);
		frameConstraints.gridx = 1;
		
		if (user.getProfile().getSportsList().size()>0){
			ArrayList<String> sportString = new ArrayList<String>();
			for(int i = 0; i < user.getProfile().getSportsList().size(); i++) {
				sportString.add(user.getProfile().getSportsList().get(i).getName());
			}
			if (sportComboBox2==null)
				sportComboBox2 = new JComboBox(sportString.toArray());
			else{
				DefaultComboBoxModel model = new DefaultComboBoxModel(sportString.toArray());
				sportComboBox2.setModel(model);
			}
			if (sportComboBox2!=null)
				sportComboBox2.setVisible(true);
			if (showPracticePanelButton!=null)
				showPracticePanelButton.setVisible(true);
			if (noSportsLabel!=null)
				noSportsLabel.setVisible(false);
			add(sportComboBox2, frameConstraints);
			add(showPracticePanelButton, frameConstraints);
		}
		else{
			if (sportComboBox2!=null)
				sportComboBox2.setVisible(false);
			if (showPracticePanelButton!=null)
				showPracticePanelButton.setVisible(false);
			if (noSportsLabel!=null)
				noSportsLabel.setVisible(true);
			add(noSportsLabel, frameConstraints);
		}


		frameConstraints.gridx = 0;
		frameConstraints.gridwidth = 3;
		frameConstraints.anchor = GridBagConstraints.CENTER;
		frameConstraints.fill = GridBagConstraints.CENTER;
		frameConstraints.insets = new Insets(30, 0, 5, 0);
		add(backHomeButton, frameConstraints);
		
		session.getTransaction().commit();
	}

	/**
	 * Method that initialize the style of the components
	 */
	public void initStyle() {
		titleLabel.setFont(TITLE_FONT);
		addSportButton.setOpaque(false);
		removeSportButton.setOpaque(false);
		showPracticePanelButton.setOpaque(false);
		showPerfChartButton.setOpaque(false);
		backHomeButton.setOpaque(false);
		titleLabel.setOpaque(false);
		sportsLabel.setOpaque(false);
		sportLastPracticeLabel.setOpaque(false);
		dateLastPracticeLabel.setOpaque(false);
		placeLastPracticeLabel.setOpaque(false);
		durationLastPracticeLabel.setOpaque(false);
		exerciceLastPracticeLabel.setOpaque(false);
		performanceLastPracticeLabel.setOpaque(false);
		addDeletePanel.setOpaque(false);
		sportsTitleLabel1.setOpaque(false);
		sportsTitleLabel2.setOpaque(false);
		sportsTitleLabel3.setOpaque(false);
		this.setOpaque(false);
	}

	public JComboBox getSportComboBox() {
		return sportComboBox;
	}

	public void setSportComboBox(JComboBox sportComboBox) {
		this.sportComboBox = sportComboBox;
	}

	public JButton getAddSportButton() {
		return addSportButton;
	}

	public void setAddSportButton(JButton addSportButton) {
		this.addSportButton = addSportButton;
	}

	public JButton getRemoveSportButton() {
		return removeSportButton;
	}

	public void setRemoveSportButton(JButton removeSportButton) {
		this.removeSportButton = removeSportButton;
	}

	public JButton getShowPracticePanelButton() {
		return showPracticePanelButton;
	}

	public void setShowPracticePanelButton(JButton showPracticePanelButton) {
		this.showPracticePanelButton = showPracticePanelButton;
	}

	public JButton getShowPerfChartButton() {
		return showPerfChartButton;
	}

	public void setShowPerfChartButton(JButton showPerfChartButton) {
		this.showPerfChartButton = showPerfChartButton;
	}

	public JButton getBackHomeButton() {
		return backHomeButton;
	}

	public void setBackHomeButton(JButton backHomeButton) {
		this.backHomeButton = backHomeButton;
	}

	public JLabel getSportsLabel() {
		return sportsLabel;
	}

	public void setSportsLabel(JLabel sportsLabel) {
		this.sportsLabel = sportsLabel;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public JComboBox getSportComboBox2() {
		return sportComboBox2;
	}

	public void setSportComboBox2(JComboBox sportComboBox2) {
		this.sportComboBox2 = sportComboBox2;
	}

	public JLabel getTitleLabel() {
		return titleLabel;
	}

	public void setTitleLabel(JLabel titleLabel) {
		this.titleLabel = titleLabel;
	}

	public JLabel getSportLastPracticeLabel() {
		return sportLastPracticeLabel;
	}

	public void setSportLastPracticeLabel(JLabel sportLastPracticeLabel) {
		this.sportLastPracticeLabel = sportLastPracticeLabel;
	}

	public JLabel getDateLastPracticeLabel() {
		return dateLastPracticeLabel;
	}

	public void setDateLastPracticeLabel(JLabel dateLastPracticeLabel) {
		this.dateLastPracticeLabel = dateLastPracticeLabel;
	}

	public JLabel getPlaceLastPracticeLabel() {
		return placeLastPracticeLabel;
	}

	public void setPlaceLastPracticeLabel(JLabel placeLastPracticeLabel) {
		this.placeLastPracticeLabel = placeLastPracticeLabel;
	}

	public JLabel getDurationLastPracticeLabel() {
		return durationLastPracticeLabel;
	}

	public void setDurationLastPracticeLabel(JLabel durationLastPracticeLabel) {
		this.durationLastPracticeLabel = durationLastPracticeLabel;
	}

	public JLabel getExerciceLastPracticeLabel() {
		return exerciceLastPracticeLabel;
	}

	public void setExerciceLastPracticeLabel(JLabel exerciceLastPracticeLabel) {
		this.exerciceLastPracticeLabel = exerciceLastPracticeLabel;
	}

	public JLabel getPerformanceLastPracticeLabel() {
		return performanceLastPracticeLabel;
	}

	public void setPerformanceLastPracticeLabel(JLabel performanceLastPracticeLabel) {
		this.performanceLastPracticeLabel = performanceLastPracticeLabel;
	}

	public JPanel getAddDeletePanel() {
		return addDeletePanel;
	}

	public void setAddDeletePanel(JPanel addDeletePanel) {
		this.addDeletePanel = addDeletePanel;
	}
}