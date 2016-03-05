package gui;

import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.hibernate.Session;

import utils.DataUtility;
import chart.HipSizeChart;
import chart.JoggingPerformancesChart;
import chart.WaistSizeChart;
import chart.WeightChart;
import data.DBConnection;
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
		this.setContentPane(background);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(530, 630);
		setVisible(true);
		//setResizable(false);
	}

	public void initStyle() {
		background.setLayout(new GridBagLayout());
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("./images/icon.png");
		instance.setIconImage(img);
	}

	public void initActions() {		
		if (loginPanel!=null && loginPanel.isVisible()){
			loginPanel.getConnectionButton().addActionListener(new connectionAction());
			loginPanel.getRegistrationButton().addActionListener(new showRegistrationAction());
		}
		if (registrationPanel!=null && registrationPanel.isVisible()){
			registrationPanel.getBackButton().addActionListener(new backLoginPanelAction());
			registrationPanel.getSubmitButton().addActionListener(new registrationAction());
		}
		if (profilePanel!=null && profilePanel.isVisible()){
			profilePanel.getDisconnectionButton().addActionListener(new disconnectionAction());
			profilePanel.getUpdateInfoButton().addActionListener(new showUpdateInfoAction());
			profilePanel.getSportManagerButton().addActionListener(new showSportManagerAction());
			profilePanel.getShowPhysicalDataButton().addActionListener(new showPhysicalDataAction());
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
		if (performanceChartPanel!=null){
			performanceChartPanel.getPreviousMonthJoggingPerfButton().addActionListener(new previousMonthJoggingPerfAction());
			performanceChartPanel.getNextMonthJoggingPerfButton().addActionListener(new nextMonthJoggingPerfAction());
		}
		if (practicePanel!=null){
			practicePanel.getAddPracticeButton().addActionListener(new addPracticeAction());
			practicePanel.getBackButton().addActionListener(new backSportsPanelAction());
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

			registrationPanel = null;
			infoManagerPanel = null;
			physicalDataBox = null;
			sportManagerPanel = null;
			performanceChartPanel = null;
			practicePanel = null;
			if (profilePanel==null)
				profilePanel = new ProfilePanel(user, true);

			repaintFrame();
		}
	}

	class backSportsPanelAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (sportManagerPanel!=null)
				sportManagerPanel.setVisible(true);
			if (practicePanel!=null)
				practicePanel.setVisible(false);


			practicePanel = null;
			sportManagerPanel = new SportManagerPanel(user);

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
			if (updatePhysicalDataPanel==null)
				updatePhysicalDataPanel = new UpdatePhysicalDataPanel(user);
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
		private User user;
		@Override
		public void actionPerformed(ActionEvent e) {
			Session session = DBConnection.getSession();
			user = login.getCurrentUser();
			login.disconnect();
			session.close();
			if (profilePanel!=null)
				profilePanel.setVisible(false);
			profilePanel = null;
			loginPanel.setVisible(true);
			repaintFrame();
			JOptionPane.showMessageDialog(instance, this.user.getPseudo() + " a bien été déconnecté !", "Déconnexion réussie", JOptionPane.INFORMATION_MESSAGE);
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

			retrievedProfile.setBirthdate(DataUtility.createDate((Integer)infoManagerPanel.getBirthdayDayComboBox().getSelectedItem(), (Integer)infoManagerPanel.getBirthdayMonthComboBox().getSelectedItem(), (Integer)infoManagerPanel.getBirthdayYearComboBox().getSelectedItem()));
			JOptionPane.showMessageDialog(instance, "Vos informations ont bien été mises à jour !", "Informations à jour", JOptionPane.INFORMATION_MESSAGE);
			session.merge(retrievedProfile);

			session.getTransaction().commit();
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
			if(practicePanel.getSportName().equals("Jogging")||practicePanel.getSportName().equals("Vélo"))
				practice = new Practice(sport, date, practicePanel.getPlaceTextField().getText(), Float.parseFloat(practicePanel.getDurationTextField().getText()), practicePanel.getPerformanceTextField().getText(), profile);
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
				practice = new Practice(sport, date, practicePanel.getPlaceTextField().getText(), Float.parseFloat(practicePanel.getDurationTextField().getText()), exercisesList, profile);
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
				practice = new Practice(sport, date, practicePanel.getPlaceTextField().getText(), Float.parseFloat(practicePanel.getDurationTextField().getText()), exercisesList, profile);
				
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
				practice = new Practice(sport, date, practicePanel.getPlaceTextField().getText(), Float.parseFloat(practicePanel.getDurationTextField().getText()), exercisesList, profile);			
			}
			profile.getPracticesList().add(practice);			  
			JOptionPane.showMessageDialog(instance, "Votre séance a bien été ajoutée !", "Séance ajoutée", JOptionPane.INFORMATION_MESSAGE);
			session.persist(profile);
			session.getTransaction().commit();
			practicePanel.repaint();
		}
	}

	class updatePhysicalDataAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Session session = DBConnection.getSession();
			session.beginTransaction();
			Profile retrievedProfile = (Profile) session.get(Profile.class, user.getProfile().getId());

			Calendar calendar = Calendar.getInstance();

			PhysicalData p = new PhysicalData();
			p.setWeight(Float.parseFloat(updatePhysicalDataPanel.getWeightField().getText()));
			p.setHipSize(Float.parseFloat(updatePhysicalDataPanel.getHipSizeField().getText()));
			p.setWaistSize(Float.parseFloat(updatePhysicalDataPanel.getWaistSizeField().getText()));
			p.setMeasureDate(DataUtility.createDate(calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.YEAR)));

			for (int i = 0; i < retrievedProfile.getPhysicalDataList().size(); i++) {
				if(retrievedProfile.getPhysicalDataList().get(i).getMeasureDate().equals(p.getMeasureDate()))
					retrievedProfile.getPhysicalDataList().remove(i);	
			}
			retrievedProfile.getPhysicalDataList().add(p);

			JOptionPane.showMessageDialog(instance, "Vos données physiques ont bien été mises à jour !", "Informations à jour", JOptionPane.INFORMATION_MESSAGE);
			session.merge(retrievedProfile);

			session.getTransaction().commit();
			updatePhysicalDataPanel.repaint();
			physicalDataChartPanel.repaint();
			repaintFrame();
		}
	}	

	class previousMonthWeightAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("previous -----------------");
			physicalDataChartPanel.getNextMonthWeightButton().setVisible(true);
			if (physicalDataChartPanel.getCurrentMonth()==1){
				physicalDataChartPanel.setCurrentMonth(12);
				physicalDataChartPanel.setCurrentYear(physicalDataChartPanel.getCurrentYear()-1);
			}else
				physicalDataChartPanel.setCurrentMonth(physicalDataChartPanel.getCurrentMonth()-1);

			System.out.println("Mois précédent : "+physicalDataChartPanel.getCurrentMonth()+"/"+physicalDataChartPanel.getCurrentYear());

			physicalDataChartPanel.setWeightChart(new WeightChart("Courbe de poids", physicalDataChartPanel.getCurrentMonth(), physicalDataChartPanel.getCurrentYear(), user));	
			physicalDataChartPanel.getCurrentWeightChartPanel().removeAll();
			physicalDataChartPanel.getCurrentWeightChartPanel().add(physicalDataChartPanel.getWeightChart().showWeightPanel());
			physicalDataChartPanel.getWeightMainBox().repaint();
			physicalDataChartPanel.repaint();
		}
	}

	class nextMonthWeightAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("next -----------------");

			if (physicalDataChartPanel.getCurrentMonth()==12){
				physicalDataChartPanel.setCurrentMonth(1);
				physicalDataChartPanel.setCurrentYear(physicalDataChartPanel.getCurrentYear()+1);
			}else
				physicalDataChartPanel.setCurrentMonth(physicalDataChartPanel.getCurrentMonth()+1);
			System.out.println("Mois suivant : "+physicalDataChartPanel.getCurrentMonth()+"/"+physicalDataChartPanel.getCurrentYear());
			physicalDataChartPanel.setWeightChart(new WeightChart("Courbe de poids", physicalDataChartPanel.getCurrentMonth(), physicalDataChartPanel.getCurrentYear(), user));	

			physicalDataChartPanel.getCurrentWeightChartPanel().removeAll();
			physicalDataChartPanel.getCurrentWeightChartPanel().add(physicalDataChartPanel.getWeightChart().showWeightPanel());
			System.out.println("nb error = "+physicalDataChartPanel.getWeightChart().getNbError());
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
			if (physicalDataChartPanel.getCurrentMonth()==1){
				physicalDataChartPanel.setCurrentMonth(12);
				physicalDataChartPanel.setCurrentYear(physicalDataChartPanel.getCurrentYear()-1);
			}else
				physicalDataChartPanel.setCurrentMonth(physicalDataChartPanel.getCurrentMonth()-1);

			System.out.println("Mois précédent : "+physicalDataChartPanel.getCurrentMonth()+"/"+physicalDataChartPanel.getCurrentYear());

			physicalDataChartPanel.setWaistSizeChart(new WaistSizeChart("Courbe de tour de taille", physicalDataChartPanel.getCurrentMonth(), physicalDataChartPanel.getCurrentYear(), user));	
			physicalDataChartPanel.getCurrentWaistSizeChartPanel().removeAll();
			physicalDataChartPanel.getCurrentWaistSizeChartPanel().add(physicalDataChartPanel.getWaistSizeChart().showWaistSizePanel());
			physicalDataChartPanel.getWaistSizeMainBox().repaint();
			physicalDataChartPanel.repaint();
		}
	}

	class nextMonthWaistSizeAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (physicalDataChartPanel.getCurrentMonth()==12){
				physicalDataChartPanel.setCurrentMonth(1);
				physicalDataChartPanel.setCurrentYear(physicalDataChartPanel.getCurrentYear()+1);
			}else
				physicalDataChartPanel.setCurrentMonth(physicalDataChartPanel.getCurrentMonth()+1);
			System.out.println("Mois suivant : "+physicalDataChartPanel.getCurrentMonth()+"/"+physicalDataChartPanel.getCurrentYear());
			physicalDataChartPanel.setWaistSizeChart(new WaistSizeChart("Courbe de tour de taille", physicalDataChartPanel.getCurrentMonth(), physicalDataChartPanel.getCurrentYear(), user));	

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
			if (physicalDataChartPanel.getCurrentMonth()==1){
				physicalDataChartPanel.setCurrentMonth(12);
				physicalDataChartPanel.setCurrentYear(physicalDataChartPanel.getCurrentYear()-1);
			}else
				physicalDataChartPanel.setCurrentMonth(physicalDataChartPanel.getCurrentMonth()-1);

			System.out.println("Mois précédent : "+physicalDataChartPanel.getCurrentMonth()+"/"+physicalDataChartPanel.getCurrentYear());

			physicalDataChartPanel.setHipSizeChart(new HipSizeChart("Courbe de tour de taille", physicalDataChartPanel.getCurrentMonth(), physicalDataChartPanel.getCurrentYear(), user));	
			physicalDataChartPanel.getCurrentHipSizeChartPanel().removeAll();
			physicalDataChartPanel.getCurrentHipSizeChartPanel().add(physicalDataChartPanel.getHipSizeChart().showHipSizePanel());
			physicalDataChartPanel.getHipSizeMainBox().repaint();
			physicalDataChartPanel.repaint();
		}
	}

	class nextMonthHipSizeAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (physicalDataChartPanel.getCurrentMonth()==12){
				physicalDataChartPanel.setCurrentMonth(1);
				physicalDataChartPanel.setCurrentYear(physicalDataChartPanel.getCurrentYear()+1);
			}else
				physicalDataChartPanel.setCurrentMonth(physicalDataChartPanel.getCurrentMonth()+1);
			System.out.println("Mois suivant : "+physicalDataChartPanel.getCurrentMonth()+"/"+physicalDataChartPanel.getCurrentYear());
			physicalDataChartPanel.setHipSizeChart(new HipSizeChart("Courbe du tour de hanche", physicalDataChartPanel.getCurrentMonth(), physicalDataChartPanel.getCurrentYear(), user));	

			physicalDataChartPanel.getCurrentHipSizeChartPanel().removeAll();
			physicalDataChartPanel.getCurrentHipSizeChartPanel().add(physicalDataChartPanel.getHipSizeChart().showHipSizePanel());
			System.out.println("nb error = "+physicalDataChartPanel.getHipSizeChart().getNbError());
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
			if (performanceChartPanel.getCurrentMonth()==1){
				performanceChartPanel.setCurrentMonth(12);
				performanceChartPanel.setCurrentYear(performanceChartPanel.getCurrentYear()-1);
			}else
				performanceChartPanel.setCurrentMonth(performanceChartPanel.getCurrentMonth()-1);

			System.out.println("Mois précédent : "+performanceChartPanel.getCurrentMonth()+"/"+performanceChartPanel.getCurrentYear());

			performanceChartPanel.setJoggingPerfChart(new JoggingPerformancesChart("Performances jogging", performanceChartPanel.getCurrentMonth(), performanceChartPanel.getCurrentYear(), user));	
			performanceChartPanel.getCurrentJoggingPerfChartPanel().removeAll();
			performanceChartPanel.getCurrentJoggingPerfChartPanel().add(performanceChartPanel.getJoggingPerfChart().showJoggingPerfPanel());
			performanceChartPanel.getJoggingPerfMainBox().repaint();
			performanceChartPanel.repaint();
		}
	}

	class nextMonthJoggingPerfAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (performanceChartPanel.getCurrentMonth()==12){
				performanceChartPanel.setCurrentMonth(1);
				performanceChartPanel.setCurrentYear(performanceChartPanel.getCurrentYear()+1);
			}else
				performanceChartPanel.setCurrentMonth(performanceChartPanel.getCurrentMonth()+1);
			System.out.println("Mois suivant : "+performanceChartPanel.getCurrentMonth()+"/"+performanceChartPanel.getCurrentYear());
			performanceChartPanel.setJoggingPerfChart(new JoggingPerformancesChart("Performances jogging", performanceChartPanel.getCurrentMonth(), performanceChartPanel.getCurrentYear(), user));	

			performanceChartPanel.getCurrentJoggingPerfChartPanel().removeAll();
			performanceChartPanel.getCurrentJoggingPerfChartPanel().add(performanceChartPanel.getJoggingPerfChart().showJoggingPerfPanel());
			System.out.println("nb error = "+performanceChartPanel.getJoggingPerfChart().getNbError());
			if (performanceChartPanel.getJoggingPerfChart().getNbError()==2)
				performanceChartPanel.getNextMonthJoggingPerfButton().setVisible(false);
			else
				performanceChartPanel.getNextMonthJoggingPerfButton().setVisible(true);

			performanceChartPanel.getJoggingPerfMainBox().repaint();
			performanceChartPanel.repaint();
		}
	}

}
