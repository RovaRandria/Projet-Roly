package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Panel that show the form to register a new user
 * @author Angelique Nguyen & Rova Randrianantoanina
 * @version 1.0
 */	
public class RegistrationPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private JLabel homeLabel = new JLabel("Rejoignez le réseau social des sportifs !");
	private JLabel pseudoLabel = new JLabel("Pseudo :");
	private JLabel passwordLabel = new JLabel("Mot de passe :");
	private JLabel errorLabel = new JLabel(" ");

	private JTextField pseudoTextField = new JTextField(10);
	private JTextField passwordTextField = new JPasswordField(10);
	
	private JButton submitButton = new JButton("S'inscrire");
	private JButton backButton = new JButton("Retour");
	
	private static final Font TITLE_FONT = new Font("Arial", Font.ITALIC|Font.BOLD, 15);
	
	/**
	 * Constructor
	 */
	public RegistrationPanel() {
		initStyle();
		init();
	}
	
	/**
	 * Method that initialize the components on the panel
	 */
	private void init() {
		GridBagConstraints frameConstraints = new GridBagConstraints();
		setLayout(new GridBagLayout());
	
		frameConstraints.gridx = 0;
		frameConstraints.gridy = 0;
		frameConstraints.insets = new Insets(10, 0, 10, 0);
		add(homeLabel, frameConstraints);
		
		frameConstraints.gridx = 0;
		frameConstraints.gridy = 1;
		frameConstraints.insets = new Insets(5, 0, 5, 0);
		add(pseudoLabel, frameConstraints);
		frameConstraints.gridx = 0;
		frameConstraints.gridy = 2;
		add(pseudoTextField, frameConstraints);
		frameConstraints.gridx = 0;
		frameConstraints.gridy = 3;
		add(passwordLabel, frameConstraints);
		frameConstraints.gridx = 0;
		frameConstraints.gridy = 4;
		add(passwordTextField, frameConstraints);
		frameConstraints.gridx = 0;
		frameConstraints.gridy = 5;
		add(submitButton, frameConstraints);
		frameConstraints.gridx = 0;
		frameConstraints.gridy = 6;
		add(errorLabel, frameConstraints);
		
		frameConstraints.gridx = 0;
		frameConstraints.gridy = 7;
		frameConstraints.insets = new Insets(10, 0, 10, 0);
		add(backButton, frameConstraints);

	}
	
	/**
	 * Method that initialize the style of the components
	 */
	private void initStyle() {
		homeLabel.setFont(TITLE_FONT);
		errorLabel.setForeground(new Color(255, 0, 0));
		
		homeLabel.setOpaque(false);
		pseudoLabel.setOpaque(false);
		passwordLabel.setOpaque(false);
		errorLabel.setOpaque(false);
		submitButton.setOpaque(false);
		backButton.setOpaque(false);
		this.setOpaque(false);
	}
	
	public JLabel getHomeLabel() {
		return homeLabel;
	}

	public void setHomeLabel(JLabel homeLabel) {
		this.homeLabel = homeLabel;
	}

	public JLabel getPseudoLabel() {
		return pseudoLabel;
	}

	public void setPseudoLabel(JLabel pseudoLabel) {
		this.pseudoLabel = pseudoLabel;
	}

	public JLabel getPasswordLabel() {
		return passwordLabel;
	}

	public void setPasswordLabel(JLabel passwordLabel) {
		this.passwordLabel = passwordLabel;
	}

	public JLabel getErrorLabel() {
		return errorLabel;
	}

	public void setErrorLabel(JLabel errorLabel) {
		this.errorLabel = errorLabel;
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

	public JButton getSubmitButton() {
		return submitButton;
	}

	public void setSubmitButton(JButton submitButton) {
		this.submitButton = submitButton;
	}

	public JButton getBackButton() {
		return backButton;
	}

	public void setBackButton(JButton backButton) {
		this.backButton = backButton;
	}

}
