package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import utils.DataUtility;

import data.Gender;
import data.User;

/**
 * Panel that show the form to update user's informations
 * @author Angelique Nguyen & Rova Randrianantoanina
 * @version 1.0
 */
public class InfoManagerPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private User user;

	private JButton updateInfoButton = new JButton("Modifier");
	private JButton backButton = new JButton("Retour au profil");

	private JLabel titleLabel = new JLabel("Modifier mes informations");
	private JLabel pseudoLabel = new JLabel("");
	private JLabel errorLabel = new JLabel(" ");

	private JPasswordField  passwordField = new JPasswordField(15);
	private JTextField  firstNameTextField  = new JTextField(15);
	private JTextField  lastNameTextField  = new JTextField(15);

	private ButtonGroup genderButtonGroup = new ButtonGroup();
	private JRadioButton maleRadioButton = new JRadioButton("Homme");
	private JRadioButton femaleRadioButton = new JRadioButton("Femme");

	private ButtonGroup privacyButtonGroup = new ButtonGroup();
	private JRadioButton privateRadioButton = new JRadioButton("Privé");
	private JRadioButton publicRadioButton = new JRadioButton("Public");
	
	private JComboBox birthdayDayComboBox = new JComboBox(DataUtility.day().toArray());
	private JComboBox birthdayMonthComboBox = new JComboBox(DataUtility.month().toArray());
	private JComboBox birthdayYearComboBox = new JComboBox(DataUtility.year().toArray());

	private JPanel datePanel = new JPanel();
	private JPanel genderRadioButtonPanel = new JPanel();
	private JPanel privacyRadioButtonPanel = new JPanel();

	private static final Font TITLE_FONT = new Font("Arial", Font.ITALIC|Font.BOLD, 15);

	public InfoManagerPanel() {
	}

	/**
	 * Constructor
	 * @param user : user who wants to update their informations
	 * @see User
	 */
	public InfoManagerPanel(User user) {
		this.user = user;
		initStyle();
		init();
	}

	/**
	 * Method that initialize the components on the panel
	 */
	public void init() {
		pseudoLabel = new JLabel(user.getPseudo());

		if(user.getProfile().getGender() != null) {
			if(user.getProfile().getGender().equals(Gender.Femme))
				femaleRadioButton.setSelected(true);
			else
				maleRadioButton.setSelected(true);
		}
		if(user.getProfile().getPrivacy()==0) 
			publicRadioButton.setSelected(true);
		else
			privateRadioButton.setSelected(true);
		
		passwordField.setText(user.getPassword());
		firstNameTextField.setText(user.getProfile().getFirstName());
		lastNameTextField.setText(user.getProfile().getLastName());
		genderButtonGroup.add(maleRadioButton);
		genderButtonGroup.add(femaleRadioButton);
		genderRadioButtonPanel.add(maleRadioButton);
		genderRadioButtonPanel.add(femaleRadioButton);

		privacyButtonGroup.add(privateRadioButton);
		privacyButtonGroup.add(publicRadioButton);
		privacyRadioButtonPanel.add(privateRadioButton);
		privacyRadioButtonPanel.add(publicRadioButton);
		
		Calendar cal = Calendar.getInstance();
		if(user.getProfile().getBirthdate() != null) {
			cal.setTime(user.getProfile().getBirthdate());
			birthdayDayComboBox.setSelectedItem(cal.get(Calendar.DAY_OF_MONTH));
			birthdayMonthComboBox.setSelectedItem(cal.get(Calendar.MONTH)+1);
			birthdayYearComboBox.setSelectedItem(cal.get(Calendar.YEAR));
		}
		else{
			birthdayDayComboBox.setSelectedItem(cal.get(Calendar.DAY_OF_MONTH));
			birthdayMonthComboBox.setSelectedItem(cal.get(Calendar.MONTH)+1);
			birthdayYearComboBox.setSelectedItem(cal.get(Calendar.YEAR));
		}
		datePanel.add(birthdayDayComboBox);
		datePanel.add(birthdayMonthComboBox);
		datePanel.add(birthdayYearComboBox);


		this.setLayout(new GridBagLayout());
		GridBagConstraints frameConstraints = new GridBagConstraints();

		frameConstraints.insets = new Insets(5, 0, 40, 0);
		frameConstraints.gridwidth = 2;
		frameConstraints.gridx = 0;
		frameConstraints.gridy = 0;
		this.add(titleLabel, frameConstraints);

		frameConstraints.insets = new Insets(5, 10, 5, 30);
		frameConstraints.gridwidth = 1;
		frameConstraints.gridx = 0;
		frameConstraints.gridy = 1;
		frameConstraints.anchor = GridBagConstraints.WEST;
		frameConstraints.fill = GridBagConstraints.NONE;
		this.add(new JLabel("Pseudo : "), frameConstraints);
		frameConstraints.gridy = GridBagConstraints.RELATIVE;
		this.add(new JLabel("Mot de passe : "), frameConstraints);
		this.add(new JLabel("Prénom : "), frameConstraints);
		this.add(new JLabel("Nom : "), frameConstraints);
		this.add(new JLabel("Sexe : "), frameConstraints);
		this.add(new JLabel("Date de naissance : "), frameConstraints);
		this.add(new JLabel("Confidentialité : "), frameConstraints);


		frameConstraints.anchor = GridBagConstraints.CENTER;
		frameConstraints.fill = GridBagConstraints.CENTER;
		frameConstraints.gridx = 1;
		frameConstraints.gridy = 1;
		this.add(pseudoLabel, frameConstraints);
		frameConstraints.gridy = GridBagConstraints.RELATIVE;
		this.add(passwordField, frameConstraints);
		this.add(firstNameTextField, frameConstraints);
		this.add(lastNameTextField, frameConstraints);
		this.add(genderRadioButtonPanel, frameConstraints);
		this.add(datePanel, frameConstraints);
		this.add(privacyRadioButtonPanel, frameConstraints);


		frameConstraints.gridwidth = 2;
		frameConstraints.gridx = 0;
		frameConstraints.gridy = GridBagConstraints.RELATIVE;
		frameConstraints.insets = new Insets(20, 0, 5, 0);
		this.add(updateInfoButton, frameConstraints);
		this.add(errorLabel, frameConstraints);
		frameConstraints.insets = new Insets(40, 0, 5, 0);
		this.add(backButton, frameConstraints);

	}

	/**
	 * Method that initialize the style of the components
	 */
	public void initStyle() {
		titleLabel.setFont(TITLE_FONT);
		errorLabel.setForeground(new Color(255, 0, 0));

		titleLabel.setFont(TITLE_FONT);
		errorLabel.setForeground(new Color(255, 0, 0));
		
		updateInfoButton.setOpaque(false);
		backButton.setOpaque(false);
		titleLabel.setOpaque(false);
		pseudoLabel.setOpaque(false);
		errorLabel.setOpaque(false);
		maleRadioButton.setOpaque(false);
		femaleRadioButton.setOpaque(false);
		privateRadioButton.setOpaque(false);
		publicRadioButton.setOpaque(false);
		birthdayDayComboBox.setOpaque(false);
		birthdayMonthComboBox.setOpaque(false);
		birthdayYearComboBox.setOpaque(false);
		datePanel.setOpaque(false);
		genderRadioButtonPanel.setOpaque(false);
		privacyRadioButtonPanel.setOpaque(false);
		this.setOpaque(false);
	}

	public JButton getUpdateInfoButton() {
		return updateInfoButton;
	}

	public void setUpdateInfoButton(JButton updateInfoButton) {
		this.updateInfoButton = updateInfoButton;
	}

	public JButton getBackButton() {
		return backButton;
	}

	public void setBackButton(JButton backButton) {
		this.backButton = backButton;
	}

	public JLabel getErrorLabel() {
		return errorLabel;
	}

	public void setErrorLabel(JLabel errorLabel) {
		this.errorLabel = errorLabel;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}

	public JTextField getFirstNameTextField() {
		return firstNameTextField;
	}

	public void setFirstNameTextField(JTextField firstNameTextField) {
		this.firstNameTextField = firstNameTextField;
	}

	public JTextField getLastNameTextField() {
		return lastNameTextField;
	}

	public void setLastNameTextField(JTextField lastNameTextField) {
		this.lastNameTextField = lastNameTextField;
	}

	public ButtonGroup getGenderButtonGroup() {
		return genderButtonGroup;
	}

	public void setGenderButtonGroup(ButtonGroup genderButtonGroup) {
		this.genderButtonGroup = genderButtonGroup;
	}

	public JRadioButton getMaleRadioButton() {
		return maleRadioButton;
	}

	public void setMaleRadioButton(JRadioButton maleRadioButton) {
		this.maleRadioButton = maleRadioButton;
	}

	public JRadioButton getFemaleRadioButton() {
		return femaleRadioButton;
	}

	public void setFemaleRadioButton(JRadioButton femaleRadioButton) {
		this.femaleRadioButton = femaleRadioButton;
	}

	public JComboBox getBirthdayDayComboBox() {
		return birthdayDayComboBox;
	}

	public void setBirthdayDayComboBox(JComboBox birthdayDayComboBox) {
		this.birthdayDayComboBox = birthdayDayComboBox;
	}

	public JComboBox getBirthdayMonthComboBox() {
		return birthdayMonthComboBox;
	}

	public void setBirthdayMonthComboBox(JComboBox birthdayMonthComboBox) {
		this.birthdayMonthComboBox = birthdayMonthComboBox;
	}

	public JComboBox getBirthdayYearComboBox() {
		return birthdayYearComboBox;
	}

	public void setBirthdayYearComboBox(JComboBox birthdayYearComboBox) {
		this.birthdayYearComboBox = birthdayYearComboBox;
	}

	public ButtonGroup getPrivacyButtonGroup() {
		return privacyButtonGroup;
	}

	public void setPrivacyButtonGroup(ButtonGroup privacyButtonGroup) {
		this.privacyButtonGroup = privacyButtonGroup;
	}

	public JRadioButton getPrivateRadioButton() {
		return privateRadioButton;
	}

	public void setPrivateRadioButton(JRadioButton privateRadioButton) {
		this.privateRadioButton = privateRadioButton;
	}

	public JRadioButton getPublicRadioButton() {
		return publicRadioButton;
	}

	public void setPublicRadioButton(JRadioButton publicRadioButton) {
		this.publicRadioButton = publicRadioButton;
	}

	public JPanel getPrivacyRadioButtonPanel() {
		return privacyRadioButtonPanel;
	}

	public void setPrivacyRadioButtonPanel(JPanel privacyRadioButtonPanel) {
		this.privacyRadioButtonPanel = privacyRadioButtonPanel;
	}



}
