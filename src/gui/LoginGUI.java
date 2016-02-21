package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.hibernate.Session;

import data.DBConnection;
import data.Login;
import data.User;

	
public class LoginGUI extends JFrame {
	public static void main(String[] args) {
		//DataInit.createTables();
		new LoginGUI("Pass'Sport");
	}

	private static final long serialVersionUID = 1L;
	private Login login;
	private LoginGUI instance = this;
	
	private InfoManagerPanel infoManagerPanel = new InfoManagerPanel();
	private SportManagerPanel sportManagerPanel = new SportManagerPanel();
	private PhysicalDataPanel physicalDataPanel = new PhysicalDataPanel();
	
	private ProfilePanel profilePanel = new ProfilePanel();
	private JPanel homePanel = new JPanel();
	private JPanel connectionPanel = new JPanel();
	private JPanel userInfoPanel = new JPanel();
	
	private JLabel homeLabel = new JLabel("Bienvenu sur Pass'Sport, le réseau social des sportifs !");
	private JLabel pseudoLabel = new JLabel("Pseudo :");
	private JLabel passwordLabel = new JLabel("Mot de passe :");
	private JLabel registrationLabel = new JLabel("Pour s'inscrire :");
	private JLabel userInfoLabel = new JLabel("Utilisateur connecté : ");
	
	private JTextField pseudoTextField = new JTextField(10);
	private JTextField passwordTextField = new JPasswordField(10);
	
	private JButton backButton = new JButton("Retour");
	private JButton connectionButton = new JButton("Connexion");
	private JButton newRegistrationButton = new JButton("Inscription");
	private JButton registrationButton = new JButton("Inscription");
	private JButton searchProfileButton = new JButton("Rechercher un utilisateur");

	private JButton updateInfoButton = new JButton("Modifier ses informations");
	private JButton updatePhysicalDataButton = new JButton("Modifier ses données physiques");
	private JButton displayProfileButton = new JButton("Afficher le profil");
	private JButton sportManagerButton = new JButton("Mes activités");
	private JButton disconnectionButton = new JButton("Déconnexion");
	
	public LoginGUI(String title) {
		super(title);
		login = new  Login(false);
		initStyle();
		init();
		initActions();

	}
	public void init() {
		this.setPreferredSize(new Dimension(800, 300));
		this.setLayout(new GridBagLayout());
		GridBagConstraints frameConstraints = new GridBagConstraints();

		frameConstraints.gridx = 0;
		frameConstraints.gridy = 0;
		this.add(backButton, frameConstraints);
		backButton.setVisible(false);
		frameConstraints.gridx = 1;
		frameConstraints.gridy = 0;
		homePanel.add(homeLabel, frameConstraints);
		homePanel.add(registrationLabel);
		homePanel.add(newRegistrationButton);
		this.add(homePanel, frameConstraints);
		
		frameConstraints.gridx = 1;
		frameConstraints.gridy = 1;		
		this.add(sportManagerPanel, frameConstraints);
		
		connectionPanel.setLayout(new GridBagLayout());
		GridBagConstraints frameConstraints2 = new GridBagConstraints();
		frameConstraints2.gridx = 1;
		frameConstraints2.gridy = 1;
		connectionPanel.add(pseudoLabel, frameConstraints2);
		frameConstraints2.gridx = 1;
		frameConstraints2.gridy = 2;
		pseudoTextField.setText("");
		passwordTextField.setText("");
		connectionPanel.add(pseudoTextField, frameConstraints2);
		frameConstraints2.gridx = 1;
		frameConstraints2.gridy = 3;
		connectionPanel.add(passwordLabel, frameConstraints2);
		frameConstraints2.gridx = 1;
		frameConstraints2.gridy = 4;
		connectionPanel.add(passwordTextField, frameConstraints2);
		frameConstraints2.gridx = 1;
		frameConstraints2.gridy = 6;
		connectionPanel.add(connectionButton, frameConstraints2);
		connectionPanel.add(registrationButton, frameConstraints2);
		connectionPanel.add(disconnectionButton, frameConstraints2);
		registrationButton.setVisible(false);
		frameConstraints.gridx = 1;
		frameConstraints.gridy = 2;	
		this.add(connectionPanel, frameConstraints);
		
		frameConstraints.gridx = 1;
		frameConstraints.gridy = 3;
		userInfoPanel.add(userInfoLabel);
		this.add(userInfoPanel, frameConstraints);

		if (!login.isCoState()) {
			disconnectionButton.setVisible(false);
			updateInfoButton.setVisible(false);
			updatePhysicalDataButton.setVisible(false);
			displayProfileButton.setVisible(false);
			sportManagerButton.setVisible(false);
			profilePanel.setVisible(false);
			infoManagerPanel.setVisible(false);
			physicalDataPanel.setVisible(false);
			sportManagerPanel.setVisible(false);
			userInfoLabel.setText("");
		}
		else {
			connectionPanel.removeAll();
			backButton.setText("Retour au profil");
			homePanel.remove(registrationLabel);
			homePanel.remove(newRegistrationButton);
			connectionPanel.add(disconnectionButton);
			connectionPanel.add(updateInfoButton);
			connectionPanel.add(updatePhysicalDataButton);
			connectionPanel.add(sportManagerButton);
			disconnectionButton.setVisible(true);
			updateInfoButton.setVisible(true);
			updatePhysicalDataButton.setVisible(true);
			sportManagerButton.setVisible(true);
			searchProfileButton.setVisible(true);
			this.remove(infoManagerPanel);
			this.remove(physicalDataPanel);
			this.remove(sportManagerPanel);
			this.remove(profilePanel);

			Session session = DBConnection.getSession();
			session.beginTransaction();
			User currentUser = (User) session.get(User.class, login.getCurrentUser().getPseudo());
			profilePanel = new ProfilePanel(currentUser);
			frameConstraints.gridx = 1;
			frameConstraints.gridy = 1;		
			this.add(profilePanel, frameConstraints);
			profilePanel.setVisible(true);
			infoManagerPanel.setVisible(true);
			physicalDataPanel.setVisible(true);
			sportManagerPanel.setVisible(true);
			session.getTransaction().commit();
			this.add(searchProfileButton);
		}

		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setResizable(false);
	}
	
	public void initStyle() {
	//Font & Backgrounds Settings
	}
	
	public void initActions() {		
		//connectionButton.addActionListener(new ConnectionAction(pseudoTextField.getText(), passwordTextField.getText()));
		connectionButton.addActionListener(new ConnectionAction());
		disconnectionButton.addActionListener(new DisconnectionAction());
		newRegistrationButton.addActionListener(new NewRegistrationAction());
		registrationButton.addActionListener(new RegistrationAction());
		backButton.addActionListener(new BackHomeAction());
		//displayProfileButton.addActionListener(new DisplayProfileAction());
		updateInfoButton.addActionListener(new UpdateInfoAction());
		updatePhysicalDataButton.addActionListener(new UpdatePhysicalDataAction());
		sportManagerButton.addActionListener(new SportManagerAction());
		searchProfileButton.addActionListener(new searchAction());
	}
	
	class BackHomeAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			searchProfileButton.setVisible(true);
			init();
			instance.repaint();
		}
	}
	
	class ConnectionAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Session session = DBConnection.getSession();
			System.out.println("BEGIN TRANSACTION");
			session.beginTransaction();
			User retrievedUser = (User) session.get(User.class, pseudoTextField.getText());
			switch(login.connect(pseudoTextField.getText(), passwordTextField.getText(), retrievedUser, session)) {
				case 0 :
					JOptionPane.showMessageDialog(instance, "Cet utilisateur n'existe pas !", "Echec de connexion", JOptionPane.ERROR_MESSAGE);
					userInfoLabel.setText("Cet utilisateur n'existe pas !");
					instance.repaint();
					session.getTransaction().commit();
					session.close();
					break;
				case 1 :
					session.getTransaction().commit();
					login.setCurrentUser(retrievedUser);
					login.setCoState(true);
					JOptionPane.showMessageDialog(instance, "Connexion réussie ! Bienvenu " + login.getCurrentUser().getPseudo() + " !", "Connexion réussie", JOptionPane.INFORMATION_MESSAGE);
					userInfoLabel.setText("Utilisateur connecté : " + login.getCurrentUser().getPseudo());
					init();
					instance.pack();
					instance.repaint();
					break;
				case 2 :
					JOptionPane.showMessageDialog(instance, "Mot de passe incorrect !", "Echec de connexion", JOptionPane.ERROR_MESSAGE);
					userInfoLabel.setText("Mot de passe incorrect !");
					instance.repaint();
					session.getTransaction().commit();
					session.close();
					break;
			}
		}
	}
	
	class DisconnectionAction implements ActionListener {
		private User user;
		@Override
		public void actionPerformed(ActionEvent e) {
			Session session = DBConnection.getSession();
			user = login.getCurrentUser();
			login.disconnect();
			init();
			JOptionPane.showMessageDialog(instance, this.user.getPseudo() + " a bien été déconnecté !", "Déconnexion réussie", JOptionPane.INFORMATION_MESSAGE);
			userInfoLabel.setText(this.user.getPseudo() + " a bien été déconnecté !");
			instance.repaint();
			session.close();
		}
	}
	
	class NewRegistrationAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			connectionPanel.remove(connectionButton);
			homePanel.remove(registrationLabel);
			homePanel.remove(newRegistrationButton);
			backButton.setVisible(true);
			userInfoLabel.setText("6 caractères minimum.");
			pseudoTextField.setText("");
			passwordTextField.setText("");
			registrationButton.setVisible(true);
			searchProfileButton.setVisible(false);
			instance.repaint();
			
		}
	}
	
	class RegistrationAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (pseudoTextField.getText().isEmpty()) {
				userInfoLabel.setText("Veuillez saisir un pseudo !");
			}
			else if (passwordTextField.getText().isEmpty()||(passwordTextField.getText().length()<6)) {
				userInfoLabel.setText("Veuillez saisir un mot de passe de 6 caractères minimum.");
			}
			else {
				User newUser = login.register(pseudoTextField.getText(), passwordTextField.getText());
				if (newUser != null) {
				init();
				JOptionPane.showMessageDialog(instance, "L'utilisateur " + newUser.getPseudo() + " a bien été créé ! Vous pouvez maintenant vous connecter !", "Inscription réussie", JOptionPane.INFORMATION_MESSAGE);
				userInfoLabel.setText("L'utilisateur " + newUser.getPseudo() + " a bien été créé ! Vous pouvez maintenant vous connecter !");
				instance.repaint();
				}
				else {
					userInfoLabel.setText("Ce pseudo existe déjà !");
					pseudoTextField.setText("");
					passwordTextField.setText("");
					instance.repaint();
				}
			}
		}
	}
	
	class UpdateInfoAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			homePanel.removeAll();
			instance.remove(connectionPanel);
			instance.remove(userInfoPanel);
			instance.remove(profilePanel);
			User user = instance.getLogin().getCurrentUser();
			infoManagerPanel = new InfoManagerPanel(user);
			instance.add(infoManagerPanel);
			instance.add(backButton);
			backButton.setVisible(true);
			infoManagerPanel.setVisible(true);
			searchProfileButton.setVisible(false);
			pack();
			instance.repaint();
			
		}
	}
	
	class UpdatePhysicalDataAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			homePanel.removeAll();
			instance.remove(connectionPanel);
			instance.remove(userInfoPanel);
			instance.remove(profilePanel);
			User user = instance.getLogin().getCurrentUser();
			physicalDataPanel = new PhysicalDataPanel(user);
			instance.add(physicalDataPanel);
			instance.add(backButton);
			backButton.setVisible(true);
			infoManagerPanel.setVisible(true);
			searchProfileButton.setVisible(false);
			pack();
			instance.repaint();
			
		}
	}
	
	class SportManagerAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			homePanel.removeAll();
			instance.remove(connectionPanel);
			instance.remove(userInfoPanel);
			instance.remove(profilePanel);
			User user = instance.getLogin().getCurrentUser();
			sportManagerPanel = new SportManagerPanel(user);
			instance.add(sportManagerPanel);
			instance.add(backButton);
			backButton.setVisible(true);
			sportManagerPanel.setVisible(true);
			searchProfileButton.setVisible(false);
			pack();
			instance.repaint();
			
		}
	}
	
	class searchAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			new SearchProfileGUI();
		}
	}

	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	public LoginGUI getInstance() {
		return instance;
	}
	public void setInstance(LoginGUI instance) {
		this.instance = instance;
	}
	
	
		//TABLES CREATION
		//DataInit.createTables();
		//login.register("Lily", "lilybg");
}
