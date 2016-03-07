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
import chart.ClimbingPerformancesChart;
import chart.CyclingPerformancesChart;
import chart.HipSizeChart;
import chart.JoggingPerformancesChart;
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

public class MainGUI extends JFrame{

	public static void main(String[] args) {
		DataInit.createTables();
		DataInit.insertSports();
		DataInit.insertExercises();
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
	private JLabel background = new JLabel(new ImageIcon("./images/background.jpeg"));

	public MainGUI(String title) {
		super(title);
		login = new  Login(false);
		repaintFrame();
	}

	public void repaintFrame(){
		initStyle();
		init();
		initActions();
	}


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
		this.setContentPane(background);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(540, 680);
		setVisible(true);
		setLocationRelativeTo(null);
		//setResizable(false);
	}

	public void initStyle() {
		background.setLayout(new GridBagLayout());
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("./images/icon.png");
		instance.setIconImage(img);
	}

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
			//performanceChartPanel.getPreviousMonthClimbingPerfButton().addActionListener(new previousMonthClimbingPerfAction());
			//performanceChartPanel.getNextMonthClimbingPerfButton().addActionListener(new nextMonthClimbingPerfAction());
			performanceChartPanel.getPreviousMonthCyclingPerfButton().addActionListener(new previousMonthCyclingPerfAction());
			performanceChartPanel.getNextMonthCyclingPerfButton().addActionListener(new nextMonthCyclingPerfAction());

			performanceChartPanel.getPreviousMonthClimbingPerfButton().addActionListener(new previousMonthClimbingPerfAction());
			performanceChartPanel.getNextMonthClimbingPerfButton().addActionListener(new nextMonthClimbingPerfAction());
			//performanceChartPanel.getPreviousMonthSkiPerfButton().addActionListener(new previousMonthSkiPerfAction());
			//performanceChartPanel.getNextMonthSkiPerfButton().addActionListener(new nextMonthSkiPerfAction());
			//performanceChartPanel.getPreviousMonthBodybuildingPerfButton().addActionListener(new previousMonthBodybuildingPerfAction());
			//performanceChartPanel.getNextMonthBodybuildingPerfButton().addActionListener(new nextMonthBodybuildingPerfAction());
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
	}

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

	class showSearchProfileAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (searchProfilePanel==null)
				searchProfilePanel = new SearchProfilePanel(user);
			if (profilePanel!=null)
				profilePanel.setVisible(false);
			profilePanel = null;
			searchProfilePanel.setVisible(true);
			repaintFrame();
		}
	}
	
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

	class addPracticeAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Session session = DBConnection.getSession();
			session.beginTransaction();
			Profile profile = (Profile) session.get(Profile.class, user.getProfile().getId());				  
			Sport sport = (Sport) session.get(Sport.class, practicePanel.getSportName());
			Practice practice = null;
			Date date = DataUtility.createDate((Integer)practicePanel.getDayComboBox().getSelectedItem(), (Integer)practicePanel.getMonthComboBox().getSelectedItem(), (Integer)practicePanel.getYearComboBox().getSelectedItem());
			Calendar cal = Calendar.getInstance();
			Float duration = 0f, performance = 0f;
			int durationError = 0, performanceError = 0;
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
			if((date.before(DataUtility.createDate(1, (cal.get(Calendar.MONTH)+1), cal.get(Calendar.YEAR)))||date.after(DataUtility.createDate(cal.get(Calendar.DAY_OF_MONTH), (cal.get(Calendar.MONTH)+1), cal.get(Calendar.YEAR))))&&((practicesList .isEmpty())))
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
					Exercise squat = new Exercise("Squat", (Integer)practicePanel.getSquatComboBox().getSelectedItem());
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


	class updatePhysicalDataAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Session session = DBConnection.getSession();
			session.beginTransaction();
			Profile retrievedProfile = (Profile) session.get(Profile.class, user.getProfile().getId());

			Calendar cal = Calendar.getInstance();
			if(!retrievedProfile.getPhysicalDataList().isEmpty())
				cal.setTime(retrievedProfile.getPhysicalDataList().get(retrievedProfile.getPhysicalDataList().size()-1).getMeasureDate());
			PhysicalData p = new PhysicalData();
			
			int parseError = 0;
			
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
				
				if(date.before(DataUtility.createDate(1, (cal.get(Calendar.MONTH)+1), cal.get(Calendar.YEAR)))||date.after(DataUtility.createDate(cal.get(Calendar.DAY_OF_MONTH), (cal.get(Calendar.MONTH)+1), cal.get(Calendar.YEAR)))&&((!retrievedProfile.getPhysicalDataList().isEmpty())))
					JOptionPane.showMessageDialog(instance, "Veuillez saisir une date valide, du mois en cours !", "Date invalide", JOptionPane.ERROR_MESSAGE);
				else {
					for (int i = 0; i < retrievedProfile.getPhysicalDataList().size(); i++) {
						if(retrievedProfile.getPhysicalDataList().get(i).getMeasureDate().equals(p.getMeasureDate()))
							retrievedProfile.getPhysicalDataList().remove(i);	
					}
					retrievedProfile.getPhysicalDataList().add(p);
		
					JOptionPane.showMessageDialog(instance, "Vos données physiques ont bien été mises Ã  jour !", "Informations Ã  jour", JOptionPane.INFORMATION_MESSAGE);
					session.merge(retrievedProfile);
		
					session.getTransaction().commit();
					user.setProfile(retrievedProfile);
					physicalDataChartPanel.getUser().setProfile(retrievedProfile);
				}
			}
			else
				JOptionPane.showMessageDialog(instance, "Veuillez saisir des nombres valides !", "Valeurs incorrectes", JOptionPane.INFORMATION_MESSAGE);
			//physicalDataChartPanel.repaintPanel();
		}
	}	

	class searchProfileAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			searchProfilePanel.getResultPanel().removeAll();
			Session session = DBConnection.getSession();
			session.beginTransaction();
			searchProfilePanel.setRetrievedUser((User) session.get(User.class, searchProfilePanel.getSearchTextField().getText()));

			User user = (User) session.get(User.class, searchProfilePanel.getSearchTextField().getText());
			session.getTransaction().commit();
			if (user==null){
				searchProfilePanel.getResultLabel().setText("Aucun utilisateur trouvé.");
				searchProfilePanel.getResultPanel().add(searchProfilePanel.getResultLabel());
			}
			else{
				searchProfilePanel.setProfilePanel(new ProfilePanel(user, false));
//				Boolean isFriend = false;
//				for (int i = 0; i < user.getProfile().getFriends().size(); i++) {
//					if(user.getProfile().getFriends().get(i).getId().equals(retrievedUser.getProfile().getId())) {
//						isFriend = true;
//					}										
//				}
//				if(isFriend) {
//					resultPanel.add(friendsLabel);
//					resultPanel.add(showJoggingPerfChartButton);
//					resultPanel.add(showPhysicDataChartButton);
//				}					
//
//				else
//					resultPanel.add(addToFriendsButton);
				searchProfilePanel.getResultPanel().add(searchProfilePanel.getProfilePanel());
			}
			searchProfilePanel.repaintPanel();
			init();
			initStyle();
		}
	}
	
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
	
}
