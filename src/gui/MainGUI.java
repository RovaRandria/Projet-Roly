package gui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.hibernate.Session;

import utils.DataUtility;
import chart.HipSizeChart;
import chart.JoggingPerformancesChart;
import chart.WaistSizeChart;
import chart.WeightChart;
import data.DBConnection;
import data.Gender;
import data.Login;
import data.PhysicalData;
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
	private ShowChartsPanel showChartsPanel;
	private JPanel physicalDataPanel;
	
	
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
				loginPanel = new LoginPanel();
				this.add(loginPanel);
			}
			else{
				loginPanel.setVisible(false);
				this.add(registrationPanel);
			}
		}
		else{
			if (user!=null){
				if (infoManagerPanel!=null){
					this.add(infoManagerPanel);
				}
				else{
					if (physicalDataPanel!=null){
						this.add(physicalDataPanel);
					}
					else{
						if (sportManagerPanel!=null){
							this.add(sportManagerPanel);
						}
						else{
							profilePanel = new ProfilePanel(user, true);
							remove(loginPanel);
							this.add(profilePanel);		
						}
					}
				}
			}
		}

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(530, 630);
		setVisible(true);
		//setResizable(false);
	}
	
	public void initStyle() {

	}
	
	public void initActions() {		
		if (loginPanel!=null){
			loginPanel.getConnectionButton().addActionListener(new connectionAction());
			loginPanel.getRegistrationButton().addActionListener(new showRegistrationAction());
		}
		if (registrationPanel!=null){
			registrationPanel.getBackButton().addActionListener(new backHomeAction());
			registrationPanel.getSubmitButton().addActionListener(new registrationAction());
		}
		if (profilePanel!=null){
			profilePanel.getDisconnectionButton().addActionListener(new DisconnectionAction());
			profilePanel.getUpdateInfoButton().addActionListener(new showUpdateInfoAction());
			profilePanel.getSportManagerButton().addActionListener(new showSportManagerAction());
			profilePanel.getShowPhysicalDataButton().addActionListener(new showPhysicalDataAction());
		}
		if (infoManagerPanel!=null){
			infoManagerPanel.getUpdateInfoButton().addActionListener(new updateInfoAction());
			infoManagerPanel.getBackButton().addActionListener(new backHomeAction());
		}
		if (physicalDataPanel!=null){
			showChartsPanel.getPreviousMonthWeightButton().addActionListener(new previousMonthWeightAction());
			showChartsPanel.getNextMonthWeightButton().addActionListener(new nextMonthWeightAction());
			showChartsPanel.getNextMonthWaistSizeButton().addActionListener(new nextMonthWaistSizeAction());
			showChartsPanel.getPreviousMonthWaistSizeButton().addActionListener(new previousMonthWaistSizeAction());
			showChartsPanel.getPreviousMonthHipSizeButton().addActionListener(new previousMonthHipSizeAction());
			showChartsPanel.getNextMonthHipSizeButton().addActionListener(new nextMonthHipSizeAction());
			showChartsPanel.getPreviousMonthJoggingPerfButton().addActionListener(new previousMonthJoggingPerfAction());
			showChartsPanel.getNextMonthJoggingPerfButton().addActionListener(new nextMonthJoggingPerfAction());
			updatePhysicalDataPanel.getUpdatePhysicalDataButton().addActionListener(new UpdatePhysicalDataAction());
			updatePhysicalDataPanel.getBackHomeButton().addActionListener(new backHomeAction());
		}
		if (sportManagerPanel!=null){
			sportManagerPanel.getAddSportButton().addActionListener(new addSportAction());
			sportManagerPanel.getRemoveSportButton().addActionListener(new removeSportAction());
//			showPracticePanelButton.addActionListener(new showPracticePanelAction());
//			showPerfChartButton.addActionListener(new showPerfChartAction());
			sportManagerPanel.getBackHomeButton().addActionListener(new backHomeAction());
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
					JOptionPane.showMessageDialog(instance, "Connexion réussie ! Bienvenue " + login.getCurrentUser().getPseudo() + " !", "Connexion réussie", JOptionPane.INFORMATION_MESSAGE);
					
					loginPanel.getMainBox().repaint();
					loginPanel.repaint();
					profilePanel = new ProfilePanel(user, true);
					repaintFrame();
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
	
	class showRegistrationAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			registrationPanel = new RegistrationPanel();
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
					JOptionPane.showMessageDialog(instance, "L'utilisateur " + user.getPseudo() + " a bien été créé ! Vous pouvez maintenant renseigner vos informations personnelles.", "Inscription réussie", JOptionPane.INFORMATION_MESSAGE);
					profilePanel = new ProfilePanel(user, true);
					repaintFrame();
				}
				else {
					registrationPanel.getErrorLabel().setText("Ce pseudo existe déjà.");
					instance.repaint();
				}
			}
		}
	}
	
	
	class backHomeAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (registrationPanel!=null)
				registrationPanel.setVisible(false);
			if (infoManagerPanel!=null)
				infoManagerPanel.setVisible(false);
			if (physicalDataPanel!=null)
				physicalDataPanel.setVisible(false);
			if (sportManagerPanel!=null)
				sportManagerPanel.setVisible(false);
			
			registrationPanel = null;
			infoManagerPanel = null;
			physicalDataPanel = null;
			sportManagerPanel = null;
			
			repaintFrame();
		}
	}

	class DisconnectionAction implements ActionListener {
		private User user;
		@Override
		public void actionPerformed(ActionEvent e) {
			Session session = DBConnection.getSession();
			user = login.getCurrentUser();
			login.disconnect();
			session.close();
			profilePanel.setVisible(false);
			repaintFrame();
			JOptionPane.showMessageDialog(instance, this.user.getPseudo() + " a bien été déconnecté !", "Déconnexion réussie", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	class showUpdateInfoAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			infoManagerPanel = new InfoManagerPanel(user);
			profilePanel.setVisible(false);
			repaintFrame();
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
	
	class showPhysicalDataAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//physicalDataPanel = new PhysicalDataPanel(user);
			showChartsPanel = new ShowChartsPanel(user, 0);
			updatePhysicalDataPanel = new UpdatePhysicalDataPanel(user);
			profilePanel.setVisible(false);
			
			physicalDataPanel = new JPanel();
			physicalDataPanel.add(showChartsPanel);
			physicalDataPanel.add(updatePhysicalDataPanel);
			repaintFrame();
		}
	}
	
	class showSportManagerAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			sportManagerPanel = new SportManagerPanel(user);
			profilePanel.setVisible(false);
			repaintFrame();
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
			System.out.println("pppppppppppp "+user.getProfile().displaySport());

			sportManagerPanel.setSportsLabel(new JLabel(user.getProfile().displaySport()));
//			sportManagerPanel.repaint();	
//			sportManagerPanel.repaintPanel();
			instance.repaint();
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
System.out.println("pppppppppppp "+user.getProfile().displaySport());
			sportManagerPanel.setSportsLabel(new JLabel(user.getProfile().displaySport()));
//			sportManagerPanel.repaint();
//			sportManagerPanel.repaintPanel();
			instance.repaint();
		}
	}
	

	class addPracticeAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new PracticeManagerFrame(user);
		}
	}
	
	class showChartAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new ShowChartsPanel(user, 0);
		}
	}
	
	class showPerfChartAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new ShowChartsPanel(user, 1);
		}
	}
	
	
	
	
	
	class previousMonthWeightAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("previous -----------------");
			showChartsPanel.getNextMonthWeightButton().setVisible(true);
			if (showChartsPanel.getCurrentMonth()==1){
				showChartsPanel.setCurrentMonth(12);
				showChartsPanel.setCurrentYear(showChartsPanel.getCurrentYear()-1);
			}else
				showChartsPanel.setCurrentMonth(showChartsPanel.getCurrentMonth()-1);

			System.out.println("Mois précédent : "+showChartsPanel.getCurrentMonth()+"/"+showChartsPanel.getCurrentYear());

			showChartsPanel.setWeightChart(new WeightChart("Courbe de poids", showChartsPanel.getCurrentMonth(), showChartsPanel.getCurrentYear(), user));	
			showChartsPanel.getCurrentWeightChartPanel().removeAll();
			showChartsPanel.getCurrentWeightChartPanel().add(showChartsPanel.getWeightChart().showWeightPanel());
			showChartsPanel.getWeightMainBox().repaint();
			showChartsPanel.repaint();
		}
	}

	class nextMonthWeightAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("next -----------------");

			if (showChartsPanel.getCurrentMonth()==12){
				showChartsPanel.setCurrentMonth(1);
				showChartsPanel.setCurrentYear(showChartsPanel.getCurrentYear()+1);
			}else
				showChartsPanel.setCurrentMonth(showChartsPanel.getCurrentMonth()+1);
			System.out.println("Mois suivant : "+showChartsPanel.getCurrentMonth()+"/"+showChartsPanel.getCurrentYear());
			showChartsPanel.setWeightChart(new WeightChart("Courbe de poids", showChartsPanel.getCurrentMonth(), showChartsPanel.getCurrentYear(), user));	

			showChartsPanel.getCurrentWeightChartPanel().removeAll();
			showChartsPanel.getCurrentWeightChartPanel().add(showChartsPanel.getWeightChart().showWeightPanel());
			System.out.println("nb error = "+showChartsPanel.getWeightChart().getNbError());
			if (showChartsPanel.getWeightChart().getNbError()==2)
				showChartsPanel.getNextMonthWeightButton().setVisible(false);
			else
				showChartsPanel.getNextMonthWeightButton().setVisible(true);

			showChartsPanel.getWeightMainBox().repaint();
			showChartsPanel.repaint();
		}
	}

	class previousMonthWaistSizeAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			showChartsPanel.getNextMonthWaistSizeButton().setVisible(true);
			if (showChartsPanel.getCurrentMonth()==1){
				showChartsPanel.setCurrentMonth(12);
				showChartsPanel.setCurrentYear(showChartsPanel.getCurrentYear()-1);
			}else
				showChartsPanel.setCurrentMonth(showChartsPanel.getCurrentMonth()-1);

			System.out.println("Mois précédent : "+showChartsPanel.getCurrentMonth()+"/"+showChartsPanel.getCurrentYear());

			showChartsPanel.setWaistSizeChart(new WaistSizeChart("Courbe de tour de taille", showChartsPanel.getCurrentMonth(), showChartsPanel.getCurrentYear(), user));	
			showChartsPanel.getCurrentWaistSizeChartPanel().removeAll();
			showChartsPanel.getCurrentWaistSizeChartPanel().add(showChartsPanel.getWaistSizeChart().showWaistSizePanel());
			showChartsPanel.getWaistSizeMainBox().repaint();
			showChartsPanel.repaint();
		}
	}

	class nextMonthWaistSizeAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (showChartsPanel.getCurrentMonth()==12){
				showChartsPanel.setCurrentMonth(1);
				showChartsPanel.setCurrentYear(showChartsPanel.getCurrentYear()+1);
			}else
				showChartsPanel.setCurrentMonth(showChartsPanel.getCurrentMonth()+1);
			System.out.println("Mois suivant : "+showChartsPanel.getCurrentMonth()+"/"+showChartsPanel.getCurrentYear());
			showChartsPanel.setWaistSizeChart(new WaistSizeChart("Courbe de tour de taille", showChartsPanel.getCurrentMonth(), showChartsPanel.getCurrentYear(), user));	

			showChartsPanel.getCurrentWaistSizeChartPanel().removeAll();
			showChartsPanel.getCurrentWaistSizeChartPanel().add(showChartsPanel.getWaistSizeChart().showWaistSizePanel());
			System.out.println("nb error = "+showChartsPanel.getWaistSizeChart().getNbError());
			if (showChartsPanel.getWaistSizeChart().getNbError()==2)
				showChartsPanel.getNextMonthWaistSizeButton().setVisible(false);
			else
				showChartsPanel.getNextMonthWaistSizeButton().setVisible(true);

			showChartsPanel.getWaistSizeMainBox().repaint();
			showChartsPanel.repaint();
		}
	}
	
	class previousMonthHipSizeAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			showChartsPanel.getNextMonthHipSizeButton().setVisible(true);
			if (showChartsPanel.getCurrentMonth()==1){
				showChartsPanel.setCurrentMonth(12);
				showChartsPanel.setCurrentYear(showChartsPanel.getCurrentYear()-1);
			}else
				showChartsPanel.setCurrentMonth(showChartsPanel.getCurrentMonth()-1);

			System.out.println("Mois précédent : "+showChartsPanel.getCurrentMonth()+"/"+showChartsPanel.getCurrentYear());

			showChartsPanel.setHipSizeChart(new HipSizeChart("Courbe de tour de taille", showChartsPanel.getCurrentMonth(), showChartsPanel.getCurrentYear(), user));	
			showChartsPanel.getCurrentHipSizeChartPanel().removeAll();
			showChartsPanel.getCurrentHipSizeChartPanel().add(showChartsPanel.getHipSizeChart().showHipSizePanel());
			showChartsPanel.getHipSizeMainBox().repaint();
			showChartsPanel.repaint();
		}
	}

	class nextMonthHipSizeAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (showChartsPanel.getCurrentMonth()==12){
				showChartsPanel.setCurrentMonth(1);
				showChartsPanel.setCurrentYear(showChartsPanel.getCurrentYear()+1);
			}else
				showChartsPanel.setCurrentMonth(showChartsPanel.getCurrentMonth()+1);
			System.out.println("Mois suivant : "+showChartsPanel.getCurrentMonth()+"/"+showChartsPanel.getCurrentYear());
			showChartsPanel.setHipSizeChart(new HipSizeChart("Courbe du tour de hanche", showChartsPanel.getCurrentMonth(), showChartsPanel.getCurrentYear(), user));	

			showChartsPanel.getCurrentHipSizeChartPanel().removeAll();
			showChartsPanel.getCurrentHipSizeChartPanel().add(showChartsPanel.getHipSizeChart().showHipSizePanel());
			System.out.println("nb error = "+showChartsPanel.getHipSizeChart().getNbError());
			if (showChartsPanel.getHipSizeChart().getNbError()==2)
				showChartsPanel.getNextMonthHipSizeButton().setVisible(false);
			else
				showChartsPanel.getNextMonthHipSizeButton().setVisible(true);

			showChartsPanel.getHipSizeMainBox().repaint();
			showChartsPanel.repaint();
		}
	}
	
	class previousMonthJoggingPerfAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			showChartsPanel.getNextMonthJoggingPerfButton().setVisible(true);
			if (showChartsPanel.getCurrentMonth()==1){
				showChartsPanel.setCurrentMonth(12);
				showChartsPanel.setCurrentYear(showChartsPanel.getCurrentYear()-1);
			}else
				showChartsPanel.setCurrentMonth(showChartsPanel.getCurrentMonth()-1);

			System.out.println("Mois précédent : "+showChartsPanel.getCurrentMonth()+"/"+showChartsPanel.getCurrentYear());

			showChartsPanel.setJoggingPerfChart(new JoggingPerformancesChart("Performances jogging", showChartsPanel.getCurrentMonth(), showChartsPanel.getCurrentYear(), user));	
			showChartsPanel.getCurrentJoggingPerfChartPanel().removeAll();
			showChartsPanel.getCurrentJoggingPerfChartPanel().add(showChartsPanel.getJoggingPerfChart().showJoggingPerfPanel());
			showChartsPanel.getJoggingPerfMainBox().repaint();
			showChartsPanel.repaint();
		}
	}
	
	class nextMonthJoggingPerfAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (showChartsPanel.getCurrentMonth()==12){
				showChartsPanel.setCurrentMonth(1);
				showChartsPanel.setCurrentYear(showChartsPanel.getCurrentYear()+1);
			}else
				showChartsPanel.setCurrentMonth(showChartsPanel.getCurrentMonth()+1);
			System.out.println("Mois suivant : "+showChartsPanel.getCurrentMonth()+"/"+showChartsPanel.getCurrentYear());
			showChartsPanel.setJoggingPerfChart(new JoggingPerformancesChart("Performances jogging", showChartsPanel.getCurrentMonth(), showChartsPanel.getCurrentYear(), user));	

			showChartsPanel.getCurrentJoggingPerfChartPanel().removeAll();
			showChartsPanel.getCurrentJoggingPerfChartPanel().add(showChartsPanel.getJoggingPerfChart().showJoggingPerfPanel());
			System.out.println("nb error = "+showChartsPanel.getJoggingPerfChart().getNbError());
			if (showChartsPanel.getJoggingPerfChart().getNbError()==2)
				showChartsPanel.getNextMonthJoggingPerfButton().setVisible(false);
			else
				showChartsPanel.getNextMonthJoggingPerfButton().setVisible(true);

			showChartsPanel.getJoggingPerfMainBox().repaint();
			showChartsPanel.repaint();
		}
	}
	
	class UpdatePhysicalDataAction implements ActionListener {
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
			showChartsPanel.repaint();
			repaintFrame();
		}
	}	
}
