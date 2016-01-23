package test;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.hibernate.Session;

	
public class LoginGUI extends JFrame {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LoginGUI loginGUI = new LoginGUI("Pass'Sport");
	}
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Login login;
	private LoginGUI instance = this;
	
	private JPanel homePanel = new JPanel();
	private JPanel connectionPanel = new JPanel();
	private JPanel userInfoPanel = new JPanel();
	
	private JLabel homeLabel = new JLabel("Bienvenu sur Pass'Sport, le réseau social des sportfis !");
	private JLabel pseudoLabel = new JLabel("Pseudo :");
	private JLabel passwordLabel = new JLabel("Mot de passe :");
	private JLabel registrationLabel = new JLabel("Pour s'inscrire :");
	private JLabel userInfoLabel = new JLabel("Utilisateur connecté : ");
	
	private JTextField pseudoTextField = new JTextField(25);
	private JTextField passwordTextField = new JPasswordField(25);
	
	private JButton backButton = new JButton("Retour");
	private JButton connectionButton = new JButton("Connexion");
	private JButton newRegistrationButton = new JButton("Inscription");
	private JButton registrationButton = new JButton("Inscription");
	private JButton disconnectionButton = new JButton("Déconnexion");
	
	public LoginGUI(String title) {
		super(title);
		login = new  Login(false);
		initStyle();
		init();
		initActions();

	}
	private void init() {
		/*homePanel.setPreferredSize(new Dimension(800, 200));
		connectionPanel.setPreferredSize(new Dimension(800, 400));
		userInfoPanel.setPreferredSize(new Dimension(800, 200));*/
		this.setLayout(new GridBagLayout());
		GridBagConstraints frameConstraints = new GridBagConstraints();
		
		frameConstraints.gridx = 1;
		frameConstraints.gridy = 0;
		homePanel.add(homeLabel, frameConstraints);
		
		frameConstraints.gridx = 1;
		frameConstraints.gridy = 0;
		homePanel.add(registrationLabel);
		homePanel.add(newRegistrationButton);
		homePanel.add(backButton);
		backButton.setVisible(false);
		this.add(homePanel, frameConstraints);

		frameConstraints.gridx = 1;
		frameConstraints.gridy = 1;

		if (!login.isCoState()) 
			disconnectionButton.setVisible(false);
		
		connectionPanel.setLayout(new GridBagLayout());
		GridBagConstraints frameConstraints2 = new GridBagConstraints();
		frameConstraints2.gridx = 1;
		frameConstraints2.gridy = 1;
		connectionPanel.add(pseudoLabel, frameConstraints2);
		frameConstraints2.gridx = 1;
		frameConstraints2.gridy = 2;
		pseudoTextField.setPreferredSize(new Dimension(50,20));
		passwordTextField.setPreferredSize(new Dimension(50,20));
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
		registrationButton.setVisible(false);
		this.add(connectionPanel, frameConstraints);
		
		frameConstraints.gridx = 1;
		frameConstraints.gridy = 2;
		userInfoPanel.add(userInfoLabel);
		this.add(userInfoPanel, frameConstraints);

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
		//connectionButton.addActionListener(new ConnectionAction(pseudoTextField.getText(), passwordTextField.getText()));
		connectionButton.addActionListener(new ConnectionAction());
		disconnectionButton.addActionListener(new DisconnectionAction());
		newRegistrationButton.addActionListener(new NewRegistrationAction());
		registrationButton.addActionListener(new RegistrationAction());
		backButton.addActionListener(new BackHomeAction());
	}
	
	private class BackHomeAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			init();
			instance.repaint();
		}
	}
	
	private class ConnectionAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			Session session = DBConnection.getSession();
			System.out.println("BEGIN TRANSACTION");
			session.beginTransaction();
			User retrievedUser = (User) session.get(User.class, pseudoTextField.getText());
			switch(login.connect(pseudoTextField.getText(), passwordTextField.getText(), retrievedUser, session)) {
				case 0 :
					userInfoLabel.setText("Ce pseudo existe déjà !");
					instance.repaint();
				case 1 :
					login.setCurrentUser(retrievedUser);
					userInfoLabel.setText(login.getCurrentUser().getPseudo() + " est bien connecté !");
					connectionPanel.removeAll();
					homePanel.remove(registrationLabel);
					homePanel.remove(newRegistrationButton);
					connectionPanel.add(disconnectionButton);
					disconnectionButton.setVisible(true);
					instance.repaint();
				break;
				case 2 :
					userInfoLabel.setText("Mot de passe incorrect !");
					instance.repaint();
					break;
			}
		}
	}
	
	private class DisconnectionAction implements ActionListener {
		private User user;
		@Override
		public void actionPerformed(ActionEvent e) {
			user = login.getCurrentUser();
			login.disconnect();
			init();
			userInfoLabel.setText(this.user.getPseudo() + " a bien été déconnecté !");
			instance.repaint();
		}
	}
	
	private class NewRegistrationAction implements ActionListener {
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
			instance.repaint();
			
		}
	}
	
	private class RegistrationAction implements ActionListener {
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
		//TABLES CREATION
		//DataInit.createTables();
		//login.register("Lily", "lilybg");
}
