package gui;

import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.hibernate.Session;

import utils.DataUtility;
import chart.BodybuildingPerformancesChart;
import chart.ClimbingPerformancesChart;
import chart.CyclingPerformancesChart;
import chart.HipSizeChart;
import chart.JoggingPerformancesChart;
import chart.SkiPerformancesChart;
import chart.WaistSizeChart;
import chart.WeightChart;
import data.DBConnection;
import data.DataInit;
import data.Exercise;
import data.Gender;
import data.Login;
import data.PhysicalData;
import data.Practice;
import data.Profile;
import data.Sport;
import data.User;

/**
 * Main class from where we execute the application
 * @author Angelique Nguyen & Rova Randrianantoanina
 * @version 1.0
 */

public class MainGUI extends JFrame{

	
	public static void main(String[] args) {
		/*DataInit.createTables();
		DataInit.insertSports();
		DataInit.insertExercises();*/
		new MainGUI("Pass'Sport");
	}




	private static final long serialVersionUID = -6508626185123863757L;
	private Login login;
	private LoginPanel loginPanel;
	private ProfilePanel profilePanel;
	private User user;
	private JFrame instance = this;
	private RegistrationPanel registrationPanel;
	private InfoManagerPanel infoManagerPanel;
	private SportManagerPanel sportManagerPanel;
	private UpdatePhysicalDataPanel updatePhysicalDataPanel;
	private PhysicalDataChartPanel physicalDataChartPanel;
	private Box physicalDataBox;
	private PerformanceChartPanel performanceChartPanel;
	private PracticePanel practicePanel;
	private SearchProfilePanel searchProfilePanel;
	private boolean loginUpdate=false;
	private ComparisonChartPerformancePanel comparisonChartPerformancePanel;
	private JLabel background = new JLabel(new ImageIcon("./images/background.jpeg"));

	/**
	 * Constructor
	 * @param title : String that contains the window's title of the application
	 */
	public MainGUI(String title) {
		super(title);
		login = new  Login(false);
		repaintFrame();
	}

	/**
	 * Method that reinitialize the main frame
	 */
	public void repaintFrame(){
		initStyle();
		init();
		initActions();
	}

	/**
	 * Method that initialize the components on the frame
	 */
	public void init() {

		if (!login.isCoState()) {
			if (registrationPanel==null){
				if(loginPanel==null)
					loginPanel = new LoginPanel();
				background.add(loginPanel);
			}
			else{
				loginPanel.setVisible(false);
				background.add(registrationPanel);
			}
		}
		else{
			if (user!=null){
				if (infoManagerPanel!=null){
					background.add(infoManagerPanel);
				}
				else{
					if (physicalDataBox!=null){
						background.add(physicalDataBox);
					}
					else{
						if (sportManagerPanel!=null){
							background.add(sportManagerPanel);
						}
						else{
							if (practicePanel!=null){
								background.add(practicePanel);
							}
							else{
								if (performanceChartPanel!=null){
									background.add(performanceChartPanel);
								}
								else {
									if (searchProfilePanel!=null){
										background.add(searchProfilePanel);
									}
									else{
										if (comparisonChartPerformancePanel!=null){
											background.add(comparisonChartPerformancePanel);
										}
										else{
											if (profilePanel==null)
												profilePanel = new ProfilePanel(user, true);
											if (loginPanel!=null)	
												loginPanel.setVisible(false);
											background.add(profilePanel);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		this.setContentPane(background);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(540, 680);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	/**
	 * Method that initialize the style of the components
	 */
	public void initStyle() {
		background.setLayout(new GridBagLayout());
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("./images/icon.png");
		instance.setIconImage(img);
	}

	/**
	 * Method that initialize the action of all the buttons which are visible on the frame
	 */
	public void initActions() {		
		if (loginPanel!=null && loginPanel.isVisible() && !loginUpdate){
			loginUpdate = true;
			loginPanel.getConnectionButton().addActionListener(new connectionAction());
			loginPanel.getRegistrationButton().addActionListener(new showRegistrationAction());
		}
		if (registrationPanel!=null && registrationPanel.isVisible()){
			registrationPanel.getBackButton().addActionListener(new backLoginPanelAction());
			registrationPanel.getSubmitButton().addActionListener(new registrationAction());
		}
		if (profilePanel!=null && profilePanel.isVisible() && profilePanel.isMine()){
			profilePanel.getDisconnectionButton().addActionListener(new disconnectionAction());
			profilePanel.getUpdateInfoButton().addActionListener(new showUpdateInfoAction());
			profilePanel.getSportManagerButton().addActionListener(new showSportManagerAction());
			profilePanel.getShowPhysicalDataButton().addActionListener(new showPhysicalDataAction());
			profilePanel.getSearchButton().addActionListener(new showSearchProfileAction());
		}
		if (profilePanel!=null && profilePanel.isVisible() && !profilePanel.isMine()){
			profilePanel.getShowPerfButton().addActionListener(new backHomeAction());
		}
		if (infoManagerPanel!=null&& infoManagerPanel.isVisible()){
			infoManagerPanel.getUpdateInfoButton().addActionListener(new updateInfoAction());
			infoManagerPanel.getBackButton().addActionListener(new backHomeAction());
		}
		if (physicalDataBox!=null&& physicalDataBox.isVisible()){
			physicalDataChartPanel.getPreviousMonthWeightButton().addActionListener(new previousMonthWeightAction());
			physicalDataChartPanel.getNextMonthWeightButton().addActionListener(new nextMonthWeightAction());
			physicalDataChartPanel.getNextMonthWaistSizeButton().addActionListener(new nextMonthWaistSizeAction());
			physicalDataChartPanel.getPreviousMonthWaistSizeButton().addActionListener(new previousMonthWaistSizeAction());
			physicalDataChartPanel.getPreviousMonthHipSizeButton().addActionListener(new previousMonthHipSizeAction());
			physicalDataChartPanel.getNextMonthHipSizeButton().addActionListener(new nextMonthHipSizeAction());
			updatePhysicalDataPanel.getUpdatePhysicalDataButton().addActionListener(new updatePhysicalDataAction());
			updatePhysicalDataPanel.getBackHomeButton().addActionListener(new backHomeAction());
		}
		if (sportManagerPanel!=null && sportManagerPanel.isVisible()){
			sportManagerPanel.getAddSportButton().addActionListener(new addSportAction());
			sportManagerPanel.getRemoveSportButton().addActionListener(new removeSportAction());
			sportManagerPanel.getShowPerfChartButton().addActionListener(new showPerfChartAction());
			sportManagerPanel.getShowPracticePanelButton().addActionListener(new showPracticePanelAction());
			sportManagerPanel.getBackHomeButton().addActionListener(new backHomeAction());
		}
		if (performanceChartPanel!=null&& performanceChartPanel.isVisible()){
			performanceChartPanel.getPreviousMonthJoggingPerfButton().addActionListener(new previousMonthJoggingPerfAction());
			performanceChartPanel.getNextMonthJoggingPerfButton().addActionListener(new nextMonthJoggingPerfAction());
			performanceChartPanel.getPreviousMonthCyclingPerfButton().addActionListener(new previousMonthCyclingPerfAction());
			performanceChartPanel.getNextMonthCyclingPerfButton().addActionListener(new nextMonthCyclingPerfAction());

			performanceChartPanel.getPreviousMonthClimbingPerfButton().addActionListener(new previousMonthClimbingPerfAction());
			performanceChartPanel.getNextMonthClimbingPerfButton().addActionListener(new nextMonthClimbingPerfAction());			
			performanceChartPanel.getPreviousMonthSkiPerfButton().addActionListener(new previousMonthSkiPerfAction());
			performanceChartPanel.getNextMonthSkiPerfButton().addActionListener(new nextMonthSkiPerfAction());
			performanceChartPanel.getPreviousMonthBodybuildingPerfButton().addActionListener(new previousMonthBodybuildingPerfAction());
			performanceChartPanel.getNextMonthBodybuildingPerfButton().addActionListener(new nextMonthBodybuildingPerfAction());
			performanceChartPanel.getBackHomeButton().addActionListener(new backSportsPanelAction());
		}
		if (practicePanel!=null){
			practicePanel.getAddPracticeButton().addActionListener(new addPracticeAction());
			practicePanel.getBackButton().addActionListener(new backSportsPanelAction());
		}
		if (searchProfilePanel!=null){
			searchProfilePanel.getSearchButton().addActionListener(new searchProfileAction());
			searchProfilePanel.getBackHomeButton().addActionListener(new backHomeAction());
		}
		if (comparisonChartPerformancePanel!=null && comparisonChartPerformancePanel.isVisible()){
			comparisonChartPerformancePanel.getPreviousMonthJoggingPerfButton().addActionListener(new previousMonthJoggingComparisonAction());
			comparisonChartPerformancePanel.getNextMonthJoggingPerfButton().addActionListener(new nextMonthJoggingComparisonAction());
			comparisonChartPerformancePanel.getPreviousMonthClimbingPerfButton().addActionListener(new previousMonthComparisonClimbingPerfAction());
			comparisonChartPerformancePanel.getNextMonthClimbingPerfButton().addActionListener(new nextMonthComparisonClimbingPerfAction());
			comparisonChartPerformancePanel.getBackHomeButton().addActionListener(new backSearchProfilePanelAction());
		}
	}

	/**
	 * Action's inner class that goes back to the profile's panel
	 */
	class backHomeAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (profilePanel!=null)
				profilePanel.setVisible(true);
			if (registrationPanel!=null)
				registrationPanel.setVisible(false);
			if (infoManagerPanel!=null)
				infoManagerPanel.setVisible(false);
			if (physicalDataBox!=null)
				physicalDataBox.setVisible(false);
			if (sportManagerPanel!=null)
				sportManagerPanel.setVisible(false);
			if (performanceChartPanel!=null)
				performanceChartPanel.setVisible(false);
			if (practicePanel!=null)
				practicePanel.setVisible(false);
			if (physicalDataChartPanel!=null)
				physicalDataChartPanel.setVisible(false);
			if (updatePhysicalDataPanel!=null)
				updatePhysicalDataPanel.setVisible(false);
			if (searchProfilePanel!=null)
				searchProfilePanel.setVisible(false);
			
			registrationPanel = null;
			infoManagerPanel = null;
			physicalDataBox = null;
			physicalDataChartPanel = null;
			updatePhysicalDataPanel = null;
			sportManagerPanel = null;
			performanceChartPanel = null;
			practicePanel = null;
			searchProfilePanel = null;
			
			if (profilePanel==null){
				System.out.println("Listes des sports pratiqués = "+user.getProfile().displaySport());
				profilePanel = new ProfilePanel(user, true);
			}
			else
				profilePanel.repaint();
			
			repaintFrame();
		}
	}

	/**
	 * Action's inner class that goes back to the login's panel
	 */
	class backLoginPanelAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (profilePanel!=null)
				profilePanel.setVisible(false);
			if (registrationPanel!=null)
				registrationPanel.setVisible(false);
			if (infoManagerPanel!=null)
				infoManagerPanel.setVisible(false);
			if (physicalDataBox!=null)
				physicalDataBox.setVisible(false);
			if (sportManagerPanel!=null)
				sportManagerPanel.setVisible(false);
			if (performanceChartPanel!=null)
				performanceChartPanel.setVisible(false);
			if (practicePanel!=null)
				practicePanel.setVisible(false);		
			registrationPanel = null;
			infoManagerPanel = null;
			physicalDataBox = null;
			sportManagerPanel = null;
			performanceChartPanel = null;
			practicePanel = null;
			loginPanel.setVisible(true);
			repaintFrame();
		}
	}
	
	/**
	 * Action's inner class that goes back to the sports' panel
	 */
	class backSportsPanelAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (sportManagerPanel!=null)
				sportManagerPanel.setVisible(true);
			if (practicePanel!=null)
				practicePanel.setVisible(false);
			if (performanceChartPanel!=null)
				performanceChartPanel.setVisible(false);

			practicePanel = null;
			performanceChartPanel = null;
			sportManagerPanel = new SportManagerPanel(user);

			repaintFrame();
		}
	}
	
	/**
	 * Action's inner class that goes back to the search profile's panel
	 */
	class backSearchProfilePanelAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (searchProfilePanel!=null)
				searchProfilePanel.setVisible(true);
			if (comparisonChartPerformancePanel!=null)
				comparisonChartPerformancePanel.setVisible(false);

			comparisonChartPerformancePanel = null;
			searchProfilePanel = new SearchProfilePanel();

			repaintFrame();
		}
	}

	/**
	 * Action's inner class that show the registration form
	 */
	class showRegistrationAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (registrationPanel==null)
				registrationPanel = new RegistrationPanel();
			registrationPanel.setVisible(true);
			loginPanel.setVisible(false);
			repaintFrame();
		}
	}

	/**
	 * Action's inner class that show the form to update the informations of the user logged in
	 */
	class showUpdateInfoAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (infoManagerPanel==null)
				infoManagerPanel = new InfoManagerPanel(user);
			if (profilePanel!=null)
				profilePanel.setVisible(false);
			profilePanel = null;
			infoManagerPanel.setVisible(true);
			repaintFrame();
		}
	}

	/**
	 * Action's inner class that show the physical data of the user
	 */
	class showPhysicalDataAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (physicalDataChartPanel==null)
				physicalDataChartPanel = new PhysicalDataChartPanel(user);
			else
				physicalDataChartPanel.repaint();
			if (updatePhysicalDataPanel==null)
				updatePhysicalDataPanel = new UpdatePhysicalDataPanel(user);
			else
				updatePhysicalDataPanel.repaint();
			if (profilePanel!=null)
				profilePanel.setVisible(false);
			profilePanel = null;			
			physicalDataBox = Box.createVerticalBox();
			physicalDataBox.add(physicalDataChartPanel);
			physicalDataBox.add(updatePhysicalDataPanel);
			physicalDataBox.setVisible(true);
			physicalDataBox.setOpaque(false);
			repaintFrame();
		}
	}

	/**
	 * Action's inner class that show the form to update the sports' and practices' informations of the user
	 */
	class showSportManagerAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (sportManagerPanel==null)
				sportManagerPanel = new SportManagerPanel(user);
			if (profilePanel!=null)
				profilePanel.setVisible(false);
			profilePanel = null;
			sportManagerPanel.setVisible(true);
			repaintFrame();
		}
	}

	/**
	 * Action's inner class that show the charts of the user's performances
	 */
	class showPerfChartAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (performanceChartPanel==null)
				performanceChartPanel = new PerformanceChartPanel(user);
			if (sportManagerPanel!=null)
				sportManagerPanel.setVisible(false);
			sportManagerPanel = null;
			performanceChartPanel.setVisible(true);
			performanceChartPanel.setOpaque(false);
			repaintFrame();
		}
	}

	/**
	 * Action's inner class that show the form to update the informations of the user logged in
	 */
	class showPracticePanelAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(sportManagerPanel.getSportComboBox2().getSelectedItem() != null) {
				String sportName = sportManagerPanel.getSportComboBox2().getSelectedItem().toString();
				practicePanel = new PracticePanel(user, sportName);
				if (profilePanel!=null)
					profilePanel.setVisible(false);
				if (sportManagerPanel!=null)
					sportManagerPanel.setVisible(false);
	
				profilePanel = null;
				sportManagerPanel = null;
				practicePanel.setVisible(true);
				repaintFrame();
			}
			else
				JOptionPane.showMessageDialog(instance, "Vous devez ajoutez des sports à votre liste de sports pratiqués !", "Aucun sport sélecionné", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Action's inner class that show the search bar to look for other users
	 */
	class showSearchProfileAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (searchProfilePanel==null)
				searchProfilePanel = new SearchProfilePanel();
			if (profilePanel!=null)
				profilePanel.setVisible(false);
			profilePanel = null;
			searchProfilePanel.setVisible(true);
			repaintFrame();
		}
	}
	
	/**
	 * Action's inner class that show the comparison between 2 users
	 */
	class showComparisonPerfAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (comparisonChartPerformancePanel==null)
				comparisonChartPerformancePanel = new ComparisonChartPerformancePanel(user, searchProfilePanel.getProfilePanel().getUser());
			if (searchProfilePanel!=null)
				searchProfilePanel.setVisible(false);
			searchProfilePanel = null;
			comparisonChartPerformancePanel.setVisible(true);
			
			repaintFrame();
		}
	}
	
	/**
	 * Action's inner class that register a new user
	 */
	class registrationAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			if (registrationPanel.getPseudoTextField().getText().isEmpty()) {
				registrationPanel.getErrorLabel().setText("Veuillez saisir un pseudo.");
			}
			else if (registrationPanel.getPasswordTextField().getText().isEmpty()||(registrationPanel.getPasswordTextField().getText().length()<6)) {
				registrationPanel.getErrorLabel().setText("Veuillez saisir un mot de passe contenant 6 caractères minimum.");
			}
			else {
				User newUser = login.register(registrationPanel.getPseudoTextField().getText(), registrationPanel.getPasswordTextField().getText());
				if (newUser != null) {
					registrationPanel.getErrorLabel().setText(" ");
					user = newUser;
					login.setCurrentUser(user);
					login.setCoState(true);
					registrationPanel.setVisible(false);
					profilePanel = new ProfilePanel(user, true);
					registrationPanel = null;
					repaintFrame();
					JOptionPane.showMessageDialog(instance, "L'utilisateur " + user.getPseudo() + " a bien été créé ! Vous pouvez maintenant renseigner vos informations personnelles.", "Inscription réussie", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					registrationPanel.getErrorLabel().setText("Ce pseudo existe déjà.");
					instance.repaint();
				}
			}
		}
	}

	/**
	 * Action's inner class that connect a user
	 */
	class connectionAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Session session = DBConnection.getSession();
			session.beginTransaction();
			User retrievedUser = (User) session.get(User.class, loginPanel.getPseudoTextField().getText());
			switch(login.connect(loginPanel.getPseudoTextField().getText(), loginPanel.getPasswordTextField().getText(), retrievedUser, session)) {
			case 0 :
				loginPanel.getErrorLabel().setText("Cet utilisateur n'existe pas.");
				loginPanel.getMainBox().repaint();
				loginPanel.repaint();
				instance.repaint();
				break;
			case 1 :
				loginPanel.getErrorLabel().setText(" ");
				login.setCurrentUser(retrievedUser);
				login.setCoState(true);
				user = retrievedUser;
				loginPanel.getMainBox().repaint();
				loginPanel.repaint();
				if (profilePanel==null)
					profilePanel = new ProfilePanel(user, true);
				loginPanel.setVisible(false);
				repaintFrame();
				JOptionPane.showMessageDialog(instance, "Connexion réussie ! Bienvenue " + login.getCurrentUser().getPseudo() + " !", "Connexion réussie", JOptionPane.INFORMATION_MESSAGE);
				break;
			case 2 :
				loginPanel.getErrorLabel().setText("Mot de passe incorrect");
				loginPanel.getMainBox().repaint();
				loginPanel.repaint();
				instance.repaint();
				break;
			}


			session.getTransaction().commit();
			session.close();
		}
	}

	/**
	 * Action's inner class that disconnect a user
	 */
	class disconnectionAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			login.disconnect();
			if (profilePanel!=null)
				profilePanel.setVisible(false);
			profilePanel = null;
			loginPanel.setVisible(true);
			repaintFrame();
			JOptionPane.showMessageDialog(instance, "Vous avez bien été déconnecté !", "Déconnexion réussie", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	 * Action's inner class that update the user's informations
	 */
	class updateInfoAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Session session = DBConnection.getSession();
			session.beginTransaction();
			Profile retrievedProfile = (Profile) session.get(Profile.class, user.getProfile().getId());

			if (String.valueOf(infoManagerPanel.getPasswordField().getPassword()).isEmpty()||(String.valueOf(infoManagerPanel.getPasswordField().getPassword()).length()<6)){
				infoManagerPanel.getErrorLabel().setText("Veuillez saisir un mot de passe de 6 caractères minimum.");
			}

			else user.setPassword(String.valueOf(infoManagerPanel.getPasswordField().getPassword()));

			retrievedProfile.setFirstName(infoManagerPanel.getFirstNameTextField().getText());

			retrievedProfile.setLastName(infoManagerPanel.getLastNameTextField().getText());

			if(infoManagerPanel.getMaleRadioButton().isSelected())
				retrievedProfile.setGender(Gender.Homme);
			else if (infoManagerPanel.getFemaleRadioButton().isSelected())
				retrievedProfile.setGender(Gender.Femme);

			if(infoManagerPanel.getPrivateRadioButton().isSelected())
				retrievedProfile.setPrivacy(1);
			else if (infoManagerPanel.getPublicRadioButton().isSelected())
				retrievedProfile.setPrivacy(0);
			
			retrievedProfile.setBirthdate(DataUtility.createDate((Integer)infoManagerPanel.getBirthdayDayComboBox().getSelectedItem(), (Integer)infoManagerPanel.getBirthdayMonthComboBox().getSelectedItem(), (Integer)infoManagerPanel.getBirthdayYearComboBox().getSelectedItem()));
			JOptionPane.showMessageDialog(instance, "Vos informations ont bien été mises à jour !", "Informations à jour", JOptionPane.INFORMATION_MESSAGE);
			session.merge(retrievedProfile);
			user.setProfile(retrievedProfile);
			session.getTransaction().commit();
			infoManagerPanel.repaint();
			instance.repaint();
		}
	}	

	/**
	 * Action's inner class that add a new practiced sport for a user
	 */
	class addSportAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Session session = DBConnection.getSession();
			session.beginTransaction();
			Profile profile = (Profile) session.get(Profile.class, user.getProfile().getId());			  
			Sport sport = (Sport) session.get(Sport.class, sportManagerPanel.getSportComboBox().getSelectedItem().toString());

			if(!profile.getSportsList().contains(sport)) {
				profile.getSportsList().add(sport);			  
				JOptionPane.showMessageDialog(instance, "Le sport " + sport.getName() + " a bien été ajouté !", "Sport ajouté", JOptionPane.INFORMATION_MESSAGE);
				session.merge(profile);
				user.setProfile(profile);
			}
			else
				JOptionPane.showMessageDialog(instance, "Erreur : vous avez déjà indiqué " + sport.getName() + " !", "Erreur", JOptionPane.ERROR_MESSAGE);

			session.getTransaction().commit();
			sportManagerPanel.repaintPanel();
		}	
	}

	/**
	 * Action's inner class that remove a new practiced sport for a user
	 */
	class removeSportAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Session session = DBConnection.getSession();
			session.beginTransaction();
			Profile profile = (Profile) session.get(Profile.class, user.getProfile().getId());			  
			Sport sport = (Sport) session.get(Sport.class, sportManagerPanel.getSportComboBox().getSelectedItem().toString());
			Boolean practiced = false;

			for (int i = 0; i < profile.getSportsList().size(); i++) {
				if(profile.getSportsList().get(i).getName().equals(sport.getName())) {
					profile.getSportsList().remove(profile.getSportsList().get(i));
					session.merge(profile);
					JOptionPane.showMessageDialog(instance, "Le sport " + sport.getName() + " a bien été retiré !", "Sport retiré", JOptionPane.INFORMATION_MESSAGE);
					practiced = true;
				}										
			}

			if (practiced.equals(false))
				JOptionPane.showMessageDialog(instance, "Erreur : vous ne pratiquez pas " + sport.getName() + " !", "Erreur", JOptionPane.ERROR_MESSAGE);
			session.getTransaction().commit();
			
			sportManagerPanel.repaintPanel();
		}
	}

	/**
	 * Action's inner class that add a new practice for a user
	 */
	class addPracticeAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Session session = DBConnection.getSession();
			session.beginTransaction();
			Profile profile = (Profile) session.get(Profile.class, user.getProfile().getId());				  
			Sport sport = (Sport) session.get(Sport.class, practicePanel.getSportName());
			Practice practice = null;
			Date date = DataUtility.createDate((Integer)practicePanel.getDayComboBox().getSelectedItem(), (Integer)practicePanel.getMonthComboBox().getSelectedItem(), (Integer)practicePanel.getYearComboBox().getSelectedItem());
			Calendar cal = Calendar.getInstance();
			Calendar today = Calendar.getInstance();			
			Float duration = 0f, performance = 0f;
			int durationError = 0, performanceError = 0;
			boolean invalidDate = false;
			try {
				duration = Float.parseFloat(practicePanel.getDurationTextField().getText());
			 } catch (NumberFormatException ex) {
				 durationError = 1;
			}
			if(!profile.getPracticesList().isEmpty())
				cal.setTime(profile.getPracticesList().get(profile.getPracticesList().size()-1).getDate());
			List <Practice> practicesList = new ArrayList<Practice>();
			for(int i = 0; i<profile.getPracticesList().size(); i++){
				if(profile.getPracticesList().get(i).equals(sport.getName()))
					practicesList.add(profile.getPracticesList().get(i));
			}
			if((date.before(DataUtility.createDate(1, (cal.get(Calendar.MONTH)+1), cal.get(Calendar.YEAR)))||date.after(DataUtility.createDate(today.get(Calendar.DAY_OF_MONTH), (today.get(Calendar.MONTH)+1), today.get(Calendar.YEAR)))))
						invalidDate = true;
			if(!practicesList.isEmpty()&&invalidDate)
				JOptionPane.showMessageDialog(instance, "Veuillez saisir une date valide, du mois en cours !", "Date invalide", JOptionPane.ERROR_MESSAGE);
			else if(durationError == 1)
				JOptionPane.showMessageDialog(instance, "Veuillez saisir une durée valide !", "Durée invalide", JOptionPane.ERROR_MESSAGE);
			else {
				if(practicePanel.getSportName().equals("Jogging")||practicePanel.getSportName().equals("Vélo")) {

					try {
						performance = Float.parseFloat(practicePanel.getPerformanceTextField().getText());
					 } catch (NumberFormatException ex) {
						 performanceError = 1;
					}
					if(performanceError == 1)
						JOptionPane.showMessageDialog(instance, "Veuillez saisir un temps de course valide !", "Temps de course invalide", JOptionPane.ERROR_MESSAGE);
					else
						practice = new Practice(sport, date, practicePanel.getPlaceTextField().getText(), duration, performance, profile);
					}
				else if(practicePanel.getSportName().equals("Escalade")) {				
					ArrayList<Exercise> exercisesList = new ArrayList<Exercise>();
					Exercise yellowClimbingRoute = new Exercise("Voie jaune", (Integer)practicePanel.getYellowClimbingRouteComboBox().getSelectedItem());
					Exercise orangeClimbingRoute = new Exercise("Voie orange", (Integer)practicePanel.getOrangeClimbingRouteComboBox().getSelectedItem());
					Exercise blueClimbingRoute = new Exercise("Voie bleue", (Integer)practicePanel.getBlueClimbingRouteComboBox().getSelectedItem());
					Exercise redClimbingRoute = new Exercise("Voie rouge", (Integer)practicePanel.getRedClimbingRouteComboBox().getSelectedItem());
					Exercise whiteClimbingRoute = new Exercise("Voie blanche", (Integer)practicePanel.getWhiteClimbingRouteComboBox().getSelectedItem());
					Exercise blackClimbingRoute = new Exercise("Voie noire", (Integer)practicePanel.getBlackClimbingRouteComboBox().getSelectedItem());
					Exercise greenClimbingRoute = new Exercise("Voie verte", (Integer)practicePanel.getGreenClimbingRouteComboBox().getSelectedItem());
					exercisesList.add(yellowClimbingRoute);
					exercisesList.add(orangeClimbingRoute);
					exercisesList.add(blueClimbingRoute);
					exercisesList.add(redClimbingRoute);
					exercisesList.add(whiteClimbingRoute);
					exercisesList.add(blackClimbingRoute);
					exercisesList.add(greenClimbingRoute);
					practice = new Practice(sport, date, practicePanel.getPlaceTextField().getText(), duration, exercisesList, profile);
				}
				else if(practicePanel.getSportName().equals("Ski")) {				
					ArrayList<Exercise> exercisesList = new ArrayList<Exercise>();
					Exercise greenTrack = new Exercise("Piste verte", (Integer)practicePanel.getGreenTrackComboBox().getSelectedItem());
					Exercise blueTrack = new Exercise("Piste bleue", (Integer)practicePanel.getBlueTrackComboBox().getSelectedItem());
					Exercise redTrack = new Exercise("Piste rouge", (Integer)practicePanel.getRedTrackComboBox().getSelectedItem());
					Exercise blackTrack = new Exercise("Piste noire", (Integer)practicePanel.getBlackTrackComboBox().getSelectedItem());
					exercisesList.add(greenTrack);
					exercisesList.add(blueTrack);
					exercisesList.add(redTrack);
					exercisesList.add(blackTrack);
					practice = new Practice(sport, date, practicePanel.getPlaceTextField().getText(), duration, exercisesList, profile);

				}
				else if(practicePanel.getSportName().equals("Musculation")) {	
					ArrayList<Exercise> exercisesList = new ArrayList<Exercise>();
					Exercise pushup = new Exercise("Pompes", (Integer)practicePanel.getPushupComboBox().getSelectedItem());
					Exercise situp = new Exercise("Abdominaux", (Integer)practicePanel.getSitupComboBox().getSelectedItem());
					Exercise pullup = new Exercise("Tractions", (Integer)practicePanel.getPullupComboBox().getSelectedItem());
					Exercise dips = new Exercise("Dips", (Integer)practicePanel.getDipsComboBox().getSelectedItem());
					Exercise squat = new Exercise("Squats", (Integer)practicePanel.getSquatComboBox().getSelectedItem());
					Exercise benchPress = new Exercise("Développés-couchés", (Integer)practicePanel.getBenchPressComboBox().getSelectedItem());
					exercisesList.add(pushup);
					exercisesList.add(situp);
					exercisesList.add(pullup);
					exercisesList.add(dips);
					exercisesList.add(squat);
					exercisesList.add(benchPress);
					practice = new Practice(sport, date, practicePanel.getPlaceTextField().getText(), duration, exercisesList, profile);			
				}
				if(performanceError != 1) {
				for (int i = 0; i < profile.getPracticesList().size(); i++) {
						if(profile.getPracticesList().get(i).getDate().equals(practice.getDate())&&profile.getPracticesList().get(i).getSport().getName().equals(sport.getName()))
							profile.getPracticesList().remove(i);	
				}
				profile.getPracticesList().add(practice);				
				JOptionPane.showMessageDialog(instance, "Votre séance a bien été ajoutée !", "Séance ajoutée", JOptionPane.INFORMATION_MESSAGE);
				session.persist(profile);
				session.getTransaction().commit();
				}
			}
			practicePanel.repaint();
		}
	}

	/**
	 * Action's inner class that add a new physical data for a user
	 */
	class updatePhysicalDataAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Session session = DBConnection.getSession();
			session.beginTransaction();
			Profile retrievedProfile = (Profile) session.get(Profile.class, user.getProfile().getId());

			Calendar cal = Calendar.getInstance();
			Calendar today = Calendar.getInstance();
			if(!retrievedProfile.getPhysicalDataList().isEmpty())
				cal.setTime(retrievedProfile.getPhysicalDataList().get(retrievedProfile.getPhysicalDataList().size()-1).getMeasureDate());
			PhysicalData p = new PhysicalData();
			
			int parseError = 0;
			boolean invalidDate = false;
			
			try {
				p.setWeight(Float.parseFloat(updatePhysicalDataPanel.getWeightField().getText()));
				p.setHipSize(Float.parseFloat(updatePhysicalDataPanel.getHipSizeField().getText()));
				p.setWaistSize(Float.parseFloat(updatePhysicalDataPanel.getWaistSizeField().getText()));
			 } catch (NumberFormatException ex) {
				 parseError = 1;
			}
			if(parseError !=1) {
				Date date = DataUtility.createDate((Integer)updatePhysicalDataPanel.getDayComboBox().getSelectedItem(), (Integer)updatePhysicalDataPanel.getMonthComboBox().getSelectedItem(), (Integer)updatePhysicalDataPanel.getYearComboBox().getSelectedItem());
				p.setMeasureDate(date);
				
				if(date.before(DataUtility.createDate(1, (cal.get(Calendar.MONTH)+1), cal.get(Calendar.YEAR)))||date.after(DataUtility.createDate(today.get(Calendar.DAY_OF_MONTH), (today.get(Calendar.MONTH)+1), today.get(Calendar.YEAR))))
						invalidDate = true;
				if((!retrievedProfile.getPhysicalDataList().isEmpty())&& invalidDate)
					JOptionPane.showMessageDialog(instance, "Veuillez saisir une date valide, du mois en cours !", "Date invalide", JOptionPane.ERROR_MESSAGE);
				else {
					for (int i = 0; i < retrievedProfile.getPhysicalDataList().size(); i++) {
						if(retrievedProfile.getPhysicalDataList().get(i).getMeasureDate().equals(p.getMeasureDate()))
							retrievedProfile.getPhysicalDataList().remove(i);	
					}
					retrievedProfile.getPhysicalDataList().add(p);
		
					JOptionPane.showMessageDialog(instance, "Vos données physiques ont bien été mises à  jour !", "Informations à  jour", JOptionPane.INFORMATION_MESSAGE);
					session.merge(retrievedProfile);
		
					session.getTransaction().commit();
					user.setProfile(retrievedProfile);
					physicalDataChartPanel.getUser().setProfile(retrievedProfile);
				}
			}
			else
				JOptionPane.showMessageDialog(instance, "Veuillez saisir des nombres valides !", "Valeurs incorrectes", JOptionPane.INFORMATION_MESSAGE);
		}
	}	

	/**
	 * Action's inner class that search users by their pseudo
	 */
	class searchProfileAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			searchProfilePanel.getResultPanel().removeAll();
			Session session = DBConnection.getSession();
			session.beginTransaction();

			User user = (User) session.get(User.class, searchProfilePanel.getSearchTextField().getText());
			session.getTransaction().commit();
			if (user==null){
				searchProfilePanel.getResultLabel().setText("Aucun utilisateur trouvé.");
				searchProfilePanel.getResultPanel().add(searchProfilePanel.getResultLabel());
			}
			else{
				searchProfilePanel.setProfilePanel(new ProfilePanel(user, false));
				searchProfilePanel.getResultPanel().add(searchProfilePanel.getProfilePanel());
			}
			searchProfilePanel.repaintPanel();
			init();
			initStyle();
			searchProfilePanel.getProfilePanel().getShowPerfButton().addActionListener(new showComparisonPerfAction());
		}
	}
	
	/**
	 * Action's inner class that show the previous month of the weight chart
	 */
	class previousMonthWeightAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			physicalDataChartPanel.getNextMonthWeightButton().setVisible(true);
			if (physicalDataChartPanel.getCurrentMonthWeight()==1){
				physicalDataChartPanel.setCurrentMonthWeight(12);
				physicalDataChartPanel.setCurrentYearWeight(physicalDataChartPanel.getCurrentYearWeight()-1);
			}else
				physicalDataChartPanel.setCurrentMonthWeight(physicalDataChartPanel.getCurrentMonthWeight()-1);

			System.out.println("Mois précédent : "+physicalDataChartPanel.getCurrentMonthWeight()+"/"+physicalDataChartPanel.getCurrentYearWeight());

			physicalDataChartPanel.setWeightChart(new WeightChart("Courbe de poids", physicalDataChartPanel.getCurrentMonthWeight(), physicalDataChartPanel.getCurrentYearWeight(), user));	
			physicalDataChartPanel.getCurrentWeightChartPanel().removeAll();
			physicalDataChartPanel.getCurrentWeightChartPanel().add(physicalDataChartPanel.getWeightChart().showWeightPanel());
			physicalDataChartPanel.getWeightMainBox().repaint();
			physicalDataChartPanel.repaint();
		}
	}

	/**
	 * Action's inner class that show the next month of the weight chart
	 */
	class nextMonthWeightAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (physicalDataChartPanel.getCurrentMonthWeight()==12){
				physicalDataChartPanel.setCurrentMonthWeight(1);
				physicalDataChartPanel.setCurrentYearWeight(physicalDataChartPanel.getCurrentYearWeight()+1);
			}else
				physicalDataChartPanel.setCurrentMonthWeight(physicalDataChartPanel.getCurrentMonthWeight()+1);
			System.out.println("Mois suivant : "+physicalDataChartPanel.getCurrentMonthWeight()+"/"+physicalDataChartPanel.getCurrentYearWeight());
			physicalDataChartPanel.setWeightChart(new WeightChart("Courbe de poids", physicalDataChartPanel.getCurrentMonthWeight(), physicalDataChartPanel.getCurrentYearWeight(), user));	

			physicalDataChartPanel.getCurrentWeightChartPanel().removeAll();
			physicalDataChartPanel.getCurrentWeightChartPanel().add(physicalDataChartPanel.getWeightChart().showWeightPanel());
			if (physicalDataChartPanel.getWeightChart().getNbError()==2)
				physicalDataChartPanel.getNextMonthWeightButton().setVisible(false);
			else
				physicalDataChartPanel.getNextMonthWeightButton().setVisible(true);

			physicalDataChartPanel.getWeightMainBox().repaint();
			physicalDataChartPanel.repaint();
		}
	}

	/**
	 * Action's inner class that show the previous month of the waist size chart
	 */
	class previousMonthWaistSizeAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			physicalDataChartPanel.getNextMonthWaistSizeButton().setVisible(true);
			if (physicalDataChartPanel.getCurrentMonthWaistSize()==1){
				physicalDataChartPanel.setCurrentMonthWaistSize(12);
				physicalDataChartPanel.setCurrentYearWaistSize(physicalDataChartPanel.getCurrentYearWaistSize()-1);
			}else
				physicalDataChartPanel.setCurrentMonthWaistSize(physicalDataChartPanel.getCurrentMonthWaistSize()-1);

			System.out.println("Mois précédent : "+physicalDataChartPanel.getCurrentMonthWaistSize()+"/"+physicalDataChartPanel.getCurrentYearWaistSize());

			physicalDataChartPanel.setWaistSizeChart(new WaistSizeChart("Courbe de tour de taille", physicalDataChartPanel.getCurrentMonthWaistSize(), physicalDataChartPanel.getCurrentYearWaistSize(), user));	
			physicalDataChartPanel.getCurrentWaistSizeChartPanel().removeAll();
			physicalDataChartPanel.getCurrentWaistSizeChartPanel().add(physicalDataChartPanel.getWaistSizeChart().showWaistSizePanel());
			physicalDataChartPanel.getWaistSizeMainBox().repaint();
			physicalDataChartPanel.repaint();
		}
	}

	/**
	 * Action's inner class that show the next month of the waist size chart
	 */
	class nextMonthWaistSizeAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (physicalDataChartPanel.getCurrentMonthWaistSize()==12){
				physicalDataChartPanel.setCurrentMonthWaistSize(1);
				physicalDataChartPanel.setCurrentYearWaistSize(physicalDataChartPanel.getCurrentYearWaistSize()+1);
			}else
				physicalDataChartPanel.setCurrentMonthWaistSize(physicalDataChartPanel.getCurrentMonthWaistSize()+1);
			System.out.println("Mois suivant : "+physicalDataChartPanel.getCurrentMonthWaistSize()+"/"+physicalDataChartPanel.getCurrentYearWaistSize());
			physicalDataChartPanel.setWaistSizeChart(new WaistSizeChart("Courbe de tour de taille", physicalDataChartPanel.getCurrentMonthWaistSize(), physicalDataChartPanel.getCurrentYearWaistSize(), user));	

			physicalDataChartPanel.getCurrentWaistSizeChartPanel().removeAll();
			physicalDataChartPanel.getCurrentWaistSizeChartPanel().add(physicalDataChartPanel.getWaistSizeChart().showWaistSizePanel());
			System.out.println("nb error = "+physicalDataChartPanel.getWaistSizeChart().getNbError());
			if (physicalDataChartPanel.getWaistSizeChart().getNbError()==2)
				physicalDataChartPanel.getNextMonthWaistSizeButton().setVisible(false);
			else
				physicalDataChartPanel.getNextMonthWaistSizeButton().setVisible(true);

			physicalDataChartPanel.getWaistSizeMainBox().repaint();
			physicalDataChartPanel.repaint();
		}
	}

	/**
	 * Action's inner class that show the previous month of the hip size chart
	 */
	class previousMonthHipSizeAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			physicalDataChartPanel.getNextMonthHipSizeButton().setVisible(true);
			if (physicalDataChartPanel.getCurrentMonthHipSize()==1){
				physicalDataChartPanel.setCurrentMonthHipSize(12);
				physicalDataChartPanel.setCurrentYearHipSize(physicalDataChartPanel.getCurrentYearHipSize()-1);
			}else
				physicalDataChartPanel.setCurrentMonthHipSize(physicalDataChartPanel.getCurrentMonthHipSize()-1);

			System.out.println("Mois précédent : "+physicalDataChartPanel.getCurrentMonthHipSize()+"/"+physicalDataChartPanel.getCurrentYearHipSize());

			physicalDataChartPanel.setHipSizeChart(new HipSizeChart("Courbe de tour de taille", physicalDataChartPanel.getCurrentMonthHipSize(), physicalDataChartPanel.getCurrentYearHipSize(), user));	
			physicalDataChartPanel.getCurrentHipSizeChartPanel().removeAll();
			physicalDataChartPanel.getCurrentHipSizeChartPanel().add(physicalDataChartPanel.getHipSizeChart().showHipSizePanel());
			physicalDataChartPanel.getHipSizeMainBox().repaint();
			physicalDataChartPanel.repaint();
		}
	}

	/**
	 * Action's inner class that show the next month of the hip size chart
	 */
	class nextMonthHipSizeAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (physicalDataChartPanel.getCurrentMonthHipSize()==12){
				physicalDataChartPanel.setCurrentMonthHipSize(1);
				physicalDataChartPanel.setCurrentYearHipSize(physicalDataChartPanel.getCurrentYearHipSize()+1);
			}else
				physicalDataChartPanel.setCurrentMonthHipSize(physicalDataChartPanel.getCurrentMonthHipSize()+1);
			System.out.println("Mois suivant : "+physicalDataChartPanel.getCurrentMonthHipSize()+"/"+physicalDataChartPanel.getCurrentYearHipSize());
			physicalDataChartPanel.setHipSizeChart(new HipSizeChart("Courbe du tour de hanche", physicalDataChartPanel.getCurrentMonthHipSize(), physicalDataChartPanel.getCurrentYearHipSize(), user));	

			physicalDataChartPanel.getCurrentHipSizeChartPanel().removeAll();
			physicalDataChartPanel.getCurrentHipSizeChartPanel().add(physicalDataChartPanel.getHipSizeChart().showHipSizePanel());
			if (physicalDataChartPanel.getHipSizeChart().getNbError()==2)
				physicalDataChartPanel.getNextMonthHipSizeButton().setVisible(false);
			else
				physicalDataChartPanel.getNextMonthHipSizeButton().setVisible(true);

			physicalDataChartPanel.getHipSizeMainBox().repaint();
			physicalDataChartPanel.repaint();
		}
	}

	/**
	 * Action's inner class that show the previous month of the jogging's performance chart
	 */
	class previousMonthJoggingPerfAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			performanceChartPanel.getNextMonthJoggingPerfButton().setVisible(true);
			if (performanceChartPanel.getCurrentMonthJogging()==1){
				performanceChartPanel.setCurrentMonthJogging(12);
				performanceChartPanel.setCurrentYearJogging(performanceChartPanel.getCurrentYearJogging()-1);
			}else
				performanceChartPanel.setCurrentMonthJogging(performanceChartPanel.getCurrentMonthJogging()-1);

			System.out.println("Mois précédent : "+performanceChartPanel.getCurrentMonthJogging()+"/"+performanceChartPanel.getCurrentYearJogging());

			performanceChartPanel.setJoggingPerfChart(new JoggingPerformancesChart("Performances jogging", performanceChartPanel.getCurrentMonthJogging(), performanceChartPanel.getCurrentYearJogging(), user));	
			performanceChartPanel.getCurrentJoggingPerfChartPanel().removeAll();
			performanceChartPanel.getCurrentJoggingPerfChartPanel().add(performanceChartPanel.getJoggingPerfChart().showJoggingPerfPanel());
			performanceChartPanel.getJoggingPerfMainBox().repaint();
			performanceChartPanel.repaint();
		}
	}

	/**
	 * Action's inner class that show the next month of the jogging's performance chart
	 */
	class nextMonthJoggingPerfAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (performanceChartPanel.getCurrentMonthJogging()==12){
				performanceChartPanel.setCurrentMonthJogging(1);
				performanceChartPanel.setCurrentYearJogging(performanceChartPanel.getCurrentYearJogging()+1);
			}else
				performanceChartPanel.setCurrentMonthJogging(performanceChartPanel.getCurrentMonthJogging()+1);
			System.out.println("Mois suivant : "+performanceChartPanel.getCurrentMonthJogging()+"/"+performanceChartPanel.getCurrentYearJogging());
			performanceChartPanel.setJoggingPerfChart(new JoggingPerformancesChart("Performances jogging", performanceChartPanel.getCurrentMonthJogging(), performanceChartPanel.getCurrentYearJogging(), user));	

			performanceChartPanel.getCurrentJoggingPerfChartPanel().removeAll();
			performanceChartPanel.getCurrentJoggingPerfChartPanel().add(performanceChartPanel.getJoggingPerfChart().showJoggingPerfPanel());
			if (performanceChartPanel.getJoggingPerfChart().getNbError()==2)
				performanceChartPanel.getNextMonthJoggingPerfButton().setVisible(false);
			else
				performanceChartPanel.getNextMonthJoggingPerfButton().setVisible(true);

			performanceChartPanel.getJoggingPerfMainBox().repaint();
			performanceChartPanel.repaint();
		}
	}
	
	/**
	 * Action's inner class that show the previous month of the comparison of jogging's performance chart
	 */
	class previousMonthJoggingComparisonAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			comparisonChartPerformancePanel.getNextMonthJoggingPerfButton().setVisible(true);
			if (comparisonChartPerformancePanel.getCurrentMonthJogging()==1){
				comparisonChartPerformancePanel.setCurrentMonthJogging(12);
				comparisonChartPerformancePanel.setCurrentYearJogging(comparisonChartPerformancePanel.getCurrentYearJogging()-1);
			}else
				comparisonChartPerformancePanel.setCurrentMonthJogging(comparisonChartPerformancePanel.getCurrentMonthJogging()-1);

			System.out.println("Mois précédent : "+comparisonChartPerformancePanel.getCurrentMonthJogging()+"/"+comparisonChartPerformancePanel.getCurrentYearJogging());

			comparisonChartPerformancePanel.setJoggingPerfChart(new JoggingPerformancesChart("Performances jogging", comparisonChartPerformancePanel.getCurrentMonthJogging(), comparisonChartPerformancePanel.getCurrentYearJogging(), user, comparisonChartPerformancePanel.getUser2()));	
			comparisonChartPerformancePanel.getCurrentJoggingPerfChartPanel().removeAll();
			comparisonChartPerformancePanel.getCurrentJoggingPerfChartPanel().add(comparisonChartPerformancePanel.getJoggingPerfChart().showComparisonJoggingPerfPanel());
			comparisonChartPerformancePanel.getJoggingPerfMainBox().repaint();
			comparisonChartPerformancePanel.repaint();
		}
	}
	
	/**
	 * Action's inner class that show the next month of the jogging's performance chart
	 */
	class nextMonthJoggingComparisonAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (comparisonChartPerformancePanel.getCurrentMonthJogging()==12){
				comparisonChartPerformancePanel.setCurrentMonthJogging(1);
				comparisonChartPerformancePanel.setCurrentYearJogging(comparisonChartPerformancePanel.getCurrentYearJogging()+1);
			}else
				comparisonChartPerformancePanel.setCurrentMonthJogging(comparisonChartPerformancePanel.getCurrentMonthJogging()+1);
			System.out.println("Mois suivant : "+comparisonChartPerformancePanel.getCurrentMonthJogging()+"/"+comparisonChartPerformancePanel.getCurrentYearJogging());
			comparisonChartPerformancePanel.setJoggingPerfChart(new JoggingPerformancesChart("Performances jogging", comparisonChartPerformancePanel.getCurrentMonthJogging(), comparisonChartPerformancePanel.getCurrentYearJogging(), user, comparisonChartPerformancePanel.getUser2()));	

			comparisonChartPerformancePanel.getCurrentJoggingPerfChartPanel().removeAll();
			comparisonChartPerformancePanel.getCurrentJoggingPerfChartPanel().add(comparisonChartPerformancePanel.getJoggingPerfChart().showComparisonJoggingPerfPanel());
			if (comparisonChartPerformancePanel.getJoggingPerfChart().getNbError()==2)
				comparisonChartPerformancePanel.getNextMonthJoggingPerfButton().setVisible(false);
			else
				comparisonChartPerformancePanel.getNextMonthJoggingPerfButton().setVisible(true);

			comparisonChartPerformancePanel.getJoggingPerfMainBox().repaint();
			comparisonChartPerformancePanel.repaint();
		}
	}
	

	/**
	 * Action's inner class that show the previous month of the cycling's performance chart
	 */
	class previousMonthCyclingPerfAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			performanceChartPanel.getNextMonthCyclingPerfButton().setVisible(true);
			if (performanceChartPanel.getCurrentMonthCycling()==1){
				performanceChartPanel.setCurrentMonthCycling(12);
				performanceChartPanel.setCurrentYearCycling(performanceChartPanel.getCurrentYearCycling()-1);
			}else
				performanceChartPanel.setCurrentMonthCycling(performanceChartPanel.getCurrentMonthCycling()-1);

			System.out.println("Mois précédent : "+performanceChartPanel.getCurrentMonthCycling()+"/"+performanceChartPanel.getCurrentYearCycling());

			performanceChartPanel.setCyclingPerfChart(new CyclingPerformancesChart("Performances vélo", performanceChartPanel.getCurrentMonthCycling(), performanceChartPanel.getCurrentYearCycling(), user));	
			performanceChartPanel.getCurrentCyclingPerfChartPanel().removeAll();
			performanceChartPanel.getCurrentCyclingPerfChartPanel().add(performanceChartPanel.getCyclingPerfChart().showCyclingPerfPanel());
			performanceChartPanel.getCyclingPerfMainBox().repaint();
			performanceChartPanel.repaint();
		}
	}
	
	/**
	 * Action's inner class that show the next month of the cycling's performance chart
	 */
	class nextMonthCyclingPerfAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (performanceChartPanel.getCurrentMonthCycling()==12){
				performanceChartPanel.setCurrentMonthCycling(1);
				performanceChartPanel.setCurrentYearCycling(performanceChartPanel.getCurrentYearCycling()+1);
			}else
				performanceChartPanel.setCurrentMonthCycling(performanceChartPanel.getCurrentMonthCycling()+1);
			System.out.println("Mois suivant : "+performanceChartPanel.getCurrentMonthCycling()+"/"+performanceChartPanel.getCurrentYearCycling());
			performanceChartPanel.setCyclingPerfChart(new CyclingPerformancesChart("Performances vélo", performanceChartPanel.getCurrentMonthCycling(), performanceChartPanel.getCurrentYearCycling(), user));	

			performanceChartPanel.getCurrentCyclingPerfChartPanel().removeAll();
			performanceChartPanel.getCurrentCyclingPerfChartPanel().add(performanceChartPanel.getCyclingPerfChart().showCyclingPerfPanel());
			if (performanceChartPanel.getCyclingPerfChart().getNbError()==2)
				performanceChartPanel.getNextMonthCyclingPerfButton().setVisible(false);
			else
				performanceChartPanel.getNextMonthCyclingPerfButton().setVisible(true);

			performanceChartPanel.getCyclingPerfMainBox().repaint();
			performanceChartPanel.repaint();
		}
	}
	
	/**
	 * Action's inner class that show the previous month of the climbing's performance chart
	 */
	class previousMonthClimbingPerfAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			performanceChartPanel.getNextMonthClimbingPerfButton().setVisible(true);
			if (performanceChartPanel.getCurrentMonthClimbing()==1){
				performanceChartPanel.setCurrentMonthClimbing(12);
				performanceChartPanel.setCurrentYearClimbing(performanceChartPanel.getCurrentYearClimbing()-1);
			}else
				performanceChartPanel.setCurrentMonthClimbing(performanceChartPanel.getCurrentMonthClimbing()-1);

			System.out.println("Mois précédent : "+performanceChartPanel.getCurrentMonthClimbing()+"/"+performanceChartPanel.getCurrentYearClimbing());

			performanceChartPanel.setClimbingPerfChart(new ClimbingPerformancesChart("Performances escalade", performanceChartPanel.getCurrentMonthClimbing(), performanceChartPanel.getCurrentYearClimbing(), user));	
			performanceChartPanel.getCurrentClimbingPerfChartPanel().removeAll();
			performanceChartPanel.getCurrentClimbingPerfChartPanel().add(performanceChartPanel.getClimbingPerfChart().showClimbingPerfPanel());
			performanceChartPanel.getClimbingPerfMainBox().repaint();
			performanceChartPanel.repaint();
		}
	}

	/**
	 * Action's inner class that show the next month of the climbing's performance chart
	 */
	

	/**
	 * Action's inner class that show the next month of the climbing's performance chart
	 */
	class nextMonthClimbingPerfAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (performanceChartPanel.getCurrentMonthClimbing()==12){
				performanceChartPanel.setCurrentMonthClimbing(1);
				performanceChartPanel.setCurrentYearClimbing(performanceChartPanel.getCurrentYearClimbing()+1);
			}else
				performanceChartPanel.setCurrentMonthClimbing(performanceChartPanel.getCurrentMonthClimbing()+1);
			System.out.println("Mois suivant : "+performanceChartPanel.getCurrentMonthClimbing()+"/"+performanceChartPanel.getCurrentYearClimbing());
			performanceChartPanel.setClimbingPerfChart(new ClimbingPerformancesChart("Performances escalde", performanceChartPanel.getCurrentMonthClimbing(), performanceChartPanel.getCurrentYearClimbing(), user));	

			performanceChartPanel.getCurrentClimbingPerfChartPanel().removeAll();
			performanceChartPanel.getCurrentClimbingPerfChartPanel().add(performanceChartPanel.getClimbingPerfChart().showClimbingPerfPanel());
			if (performanceChartPanel.getClimbingPerfChart().getNbError()==2)
				performanceChartPanel.getNextMonthClimbingPerfButton().setVisible(false);
			else
				performanceChartPanel.getNextMonthClimbingPerfButton().setVisible(true);

			performanceChartPanel.getClimbingPerfMainBox().repaint();
			performanceChartPanel.repaint();
		}
	}
	
	/**
	 * Action's inner class that show the previous month of the climbing's performance chart
	 */
	class previousMonthComparisonClimbingPerfAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			comparisonChartPerformancePanel.getNextMonthClimbingPerfButton().setVisible(true);
			if (comparisonChartPerformancePanel.getCurrentMonthClimbing()==1){
				comparisonChartPerformancePanel.setCurrentMonthClimbing(12);
				comparisonChartPerformancePanel.setCurrentYearClimbing(comparisonChartPerformancePanel.getCurrentYearClimbing()-1);
			}else
				comparisonChartPerformancePanel.setCurrentMonthClimbing(comparisonChartPerformancePanel.getCurrentMonthClimbing()-1);

			System.out.println("Mois précédent : "+comparisonChartPerformancePanel.getCurrentMonthClimbing()+"/"+comparisonChartPerformancePanel.getCurrentYearClimbing());

			comparisonChartPerformancePanel.setClimbingPerfChart(new ClimbingPerformancesChart("Performances escalade", comparisonChartPerformancePanel.getCurrentMonthClimbing(), comparisonChartPerformancePanel.getCurrentYearClimbing(), user, comparisonChartPerformancePanel.getUser2()));	
			comparisonChartPerformancePanel.getCurrentClimbingPerfChartPanel().removeAll();
			comparisonChartPerformancePanel.getCurrentClimbingPerfChartPanel().add(comparisonChartPerformancePanel.getClimbingPerfChart().showComparisonClimbingPerfPanel());
			comparisonChartPerformancePanel.getClimbingPerfMainBox().repaint();
			comparisonChartPerformancePanel.repaint();
		}
	}

	/**
	 * Action's inner class that show the next month of the climbing's performance chart
	 */
	class nextMonthComparisonClimbingPerfAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (comparisonChartPerformancePanel.getCurrentMonthClimbing()==12){
				comparisonChartPerformancePanel.setCurrentMonthClimbing(1);
				comparisonChartPerformancePanel.setCurrentYearClimbing(comparisonChartPerformancePanel.getCurrentYearClimbing()+1);
			}else
				comparisonChartPerformancePanel.setCurrentMonthClimbing(comparisonChartPerformancePanel.getCurrentMonthClimbing()+1);
			System.out.println("Mois suivant : "+comparisonChartPerformancePanel.getCurrentMonthClimbing()+"/"+comparisonChartPerformancePanel.getCurrentYearClimbing());
			comparisonChartPerformancePanel.setClimbingPerfChart(new ClimbingPerformancesChart("Performances escalde", comparisonChartPerformancePanel.getCurrentMonthClimbing(), comparisonChartPerformancePanel.getCurrentYearClimbing(), user, comparisonChartPerformancePanel.getUser2()));	

			comparisonChartPerformancePanel.getCurrentClimbingPerfChartPanel().removeAll();
			comparisonChartPerformancePanel.getCurrentClimbingPerfChartPanel().add(comparisonChartPerformancePanel.getClimbingPerfChart().showComparisonClimbingPerfPanel());
			if (comparisonChartPerformancePanel.getClimbingPerfChart().getNbError()==2)
				comparisonChartPerformancePanel.getNextMonthClimbingPerfButton().setVisible(false);
			else
				comparisonChartPerformancePanel.getNextMonthClimbingPerfButton().setVisible(true);

			comparisonChartPerformancePanel.getClimbingPerfMainBox().repaint();
			comparisonChartPerformancePanel.repaint();
		}
	}
	
	class previousMonthBodybuildingPerfAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			performanceChartPanel.getNextMonthBodybuildingPerfButton().setVisible(true);
			if (performanceChartPanel.getCurrentMonthBodybuilding()==1){
				performanceChartPanel.setCurrentMonthBodybuilding(12);
				performanceChartPanel.setCurrentYearBodybuilding(performanceChartPanel.getCurrentYearBodybuilding()-1);
			}else
				performanceChartPanel.setCurrentMonthBodybuilding(performanceChartPanel.getCurrentMonthBodybuilding()-1);

			System.out.println("Mois précédent : "+performanceChartPanel.getCurrentMonthBodybuilding()+"/"+performanceChartPanel.getCurrentYearBodybuilding());

			performanceChartPanel.setBodybuildingPerfChart(new BodybuildingPerformancesChart("Performances musculation", performanceChartPanel.getCurrentMonthBodybuilding(), performanceChartPanel.getCurrentYearBodybuilding(), user));	
			performanceChartPanel.getCurrentBodybuildingPerfChartPanel().removeAll();
			performanceChartPanel.getCurrentBodybuildingPerfChartPanel().add(performanceChartPanel.getBodybuildingPerfChart().showBodybuildingPerfPanel());
			performanceChartPanel.getBodybuildingPerfMainBox().repaint();
			performanceChartPanel.repaint();
		}
	}

	/**
	 * Action's inner class that show the next month of the body building's performance chart
	 */
	class nextMonthBodybuildingPerfAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (performanceChartPanel.getCurrentMonthBodybuilding()==12){
				performanceChartPanel.setCurrentMonthBodybuilding(1);
				performanceChartPanel.setCurrentYearBodybuilding(performanceChartPanel.getCurrentYearBodybuilding()+1);
			}else
				performanceChartPanel.setCurrentMonthBodybuilding(performanceChartPanel.getCurrentMonthBodybuilding()+1);
			System.out.println("Mois suivant : "+performanceChartPanel.getCurrentMonthBodybuilding()+"/"+performanceChartPanel.getCurrentYearBodybuilding());
			performanceChartPanel.setBodybuildingPerfChart(new BodybuildingPerformancesChart("Performances musculation", performanceChartPanel.getCurrentMonthBodybuilding(), performanceChartPanel.getCurrentYearBodybuilding(), user));	

			performanceChartPanel.getCurrentBodybuildingPerfChartPanel().removeAll();
			performanceChartPanel.getCurrentBodybuildingPerfChartPanel().add(performanceChartPanel.getBodybuildingPerfChart().showBodybuildingPerfPanel());
			if (performanceChartPanel.getBodybuildingPerfChart().getNbError()==2)
				performanceChartPanel.getNextMonthBodybuildingPerfButton().setVisible(false);
			else
				performanceChartPanel.getNextMonthBodybuildingPerfButton().setVisible(true);

			performanceChartPanel.getBodybuildingPerfMainBox().repaint();
			performanceChartPanel.repaint();
		}
	}
	
	/**
	 * Action's inner class that show the previous month of the ski's performance chart
	 */
	class previousMonthSkiPerfAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			performanceChartPanel.getNextMonthSkiPerfButton().setVisible(true);
			if (performanceChartPanel.getCurrentMonthSki()==1){
				performanceChartPanel.setCurrentMonthSki(12);
				performanceChartPanel.setCurrentYearSki(performanceChartPanel.getCurrentYearSki()-1);
			}else
				performanceChartPanel.setCurrentMonthSki(performanceChartPanel.getCurrentMonthSki()-1);

			System.out.println("Mois précédent : "+performanceChartPanel.getCurrentMonthSki()+"/"+performanceChartPanel.getCurrentYearSki());

			performanceChartPanel.setSkiPerfChart(new SkiPerformancesChart("Performances ski", performanceChartPanel.getCurrentMonthSki(), performanceChartPanel.getCurrentYearSki(), user));	
			performanceChartPanel.getCurrentSkiPerfChartPanel().removeAll();
			performanceChartPanel.getCurrentSkiPerfChartPanel().add(performanceChartPanel.getSkiPerfChart().showSkiPerfPanel());
			performanceChartPanel.getSkiPerfMainBox().repaint();
			performanceChartPanel.repaint();
		}
	}

	/**
	 * Action's inner class that show the next month of the ski's performance chart
	 */
	class nextMonthSkiPerfAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (performanceChartPanel.getCurrentMonthSki()==12){
				performanceChartPanel.setCurrentMonthSki(1);
				performanceChartPanel.setCurrentYearSki(performanceChartPanel.getCurrentYearSki()+1);
			}else
				performanceChartPanel.setCurrentMonthSki(performanceChartPanel.getCurrentMonthSki()+1);
			System.out.println("Mois suivant : "+performanceChartPanel.getCurrentMonthSki()+"/"+performanceChartPanel.getCurrentYearSki());
			performanceChartPanel.setSkiPerfChart(new SkiPerformancesChart("Performances musculation", performanceChartPanel.getCurrentMonthSki(), performanceChartPanel.getCurrentYearSki(), user));	
			performanceChartPanel.getCurrentSkiPerfChartPanel().removeAll();
			performanceChartPanel.getCurrentSkiPerfChartPanel().add(performanceChartPanel.getSkiPerfChart().showSkiPerfPanel());
			if (performanceChartPanel.getSkiPerfChart().getNbError()==2)
				performanceChartPanel.getNextMonthSkiPerfButton().setVisible(false);
			else
				performanceChartPanel.getNextMonthSkiPerfButton().setVisible(true);

			performanceChartPanel.getSkiPerfMainBox().repaint();
			performanceChartPanel.repaint();
		}
	}


	
}
