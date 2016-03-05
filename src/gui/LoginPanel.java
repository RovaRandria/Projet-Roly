package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

	
public class LoginPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JLabel homeLabel = new JLabel("Bienvenu sur Pass'Sport, le réseau social des sportifs !");
	private JLabel pseudoLabel = new JLabel("Pseudo :");
	private JLabel passwordLabel = new JLabel("Mot de passe :");
	private JLabel errorLabel = new JLabel(" ");
	private JLabel registrationLabel = new JLabel("Vous n'avez pas encore de compte ? ");
	
	private JTextField pseudoTextField = new JTextField(10);
	private JTextField passwordTextField = new JPasswordField(10);
	
	private JButton connectionButton = new JButton("Se connecter");
	private JButton registrationButton = new JButton("S'inscrire");

	private Box mainBox = Box.createVerticalBox();
	
	private static final Font TITLE_FONT = new Font("Arial", Font.ITALIC|Font.BOLD, 15);

	public LoginPanel() {
		initStyle();
		init();
		initActions();
		
	}
	
	public void init() {
		setLayout(new GridBagLayout());
		GridBagConstraints frameConstraints = new GridBagConstraints();
		
		frameConstraints.insets = new Insets(5, 0, 40, 0);
		frameConstraints.gridx = 0;
		frameConstraints.gridy = 0;
		add(homeLabel, frameConstraints);
		
		frameConstraints.gridy = GridBagConstraints.RELATIVE;
		frameConstraints.insets = new Insets(5, 0, 5, 0);
		add(pseudoLabel, frameConstraints);
		add(pseudoTextField, frameConstraints);
		add(passwordLabel, frameConstraints);
		add(passwordTextField, frameConstraints);
		add(connectionButton, frameConstraints);
		add(errorLabel, frameConstraints);
		
		frameConstraints.insets = new Insets(30, 0, 5, 0);
		add(registrationLabel, frameConstraints);
		frameConstraints.insets = new Insets(5, 0, 5, 0);
		add(registrationButton, frameConstraints);
		

	}
	
	public void initStyle() {
		homeLabel.setFont(TITLE_FONT);
		errorLabel.setForeground(new Color(255, 0, 0));
		
		homeLabel.setOpaque(false);
		pseudoLabel.setOpaque(false);
		passwordLabel.setOpaque(false);
		errorLabel.setOpaque(false);
		registrationLabel.setOpaque(false);
		connectionButton.setOpaque(false);
		registrationButton.setOpaque(false);
		this.setOpaque(false);
	}
	
	public void initActions() {		

	}

	public JButton getConnectionButton() {
		return connectionButton;
	}

	public void setConnectionButton(JButton connectionButton) {
		this.connectionButton = connectionButton;
	}

	public JTextField getPseudoTextField() {
		return pseudoTextField;
	}

	public void setPseudoTextField(JTextField pseudoTextField) {
		this.pseudoTextField = pseudoTextField;
	}

	public JTextField getPasswordTextField() {
		return passwordTextField;
	}

	public void setPasswordTextField(JTextField passwordTextField) {
		this.passwordTextField = passwordTextField;
	}

	public JButton getRegistrationButton() {
		return registrationButton;
	}

	public void setRegistrationButton(JButton registrationButton) {
		this.registrationButton = registrationButton;
	}

	public JLabel getErrorLabel() {
		return errorLabel;
	}

	public Box getMainBox() {
		return mainBox;
	}

	public void setMainBox(Box mainBox) {
		this.mainBox = mainBox;
	}


}
