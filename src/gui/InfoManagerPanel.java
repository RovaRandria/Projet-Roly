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

import utils.DateNumbersList;

import data.Gender;
import data.User;

public class InfoManagerPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private User user;
		
	private JButton updateInfoButton = new JButton("Modifier");
	private JButton backButton = new JButton("Retour au profil");
	
	private JLabel titleLabel = new JLabel("Modifier mes informations");
	private JLabel pseudoLabel;
	private JLabel errorLabel = new JLabel(" ");
	
	private JPasswordField  passwordField = new JPasswordField(15);
	private JTextField  firstNameTextField  = new JTextField(15);
	private JTextField  lastNameTextField  = new JTextField(15);
	
	private ButtonGroup genderButtonGroup = new ButtonGroup();
	private JRadioButton maleRadioButton = new JRadioButton("Homme");
	private JRadioButton femaleRadioButton = new JRadioButton("Femme");
	
	private JComboBox birthdayDayComboBox = new JComboBox(DateNumbersList.day().toArray());
	private JComboBox birthdayMonthComboBox = new JComboBox(DateNumbersList.month().toArray());
	private JComboBox birthdayYearComboBox = new JComboBox(DateNumbersList.year().toArray());
	
	private JPanel datePanel = new JPanel();
	private JPanel radioButtonPanel = new JPanel();
	
	private static final Font TITLE_FONT = new Font("Arial", Font.ITALIC|Font.BOLD, 15);
	
	public InfoManagerPanel() {
	}
	
	public InfoManagerPanel(User user) {
		this.user = user;
		initStyle();
		init();
		initActions();
	}
	
	public void init() {
		pseudoLabel = new JLabel(user.getPseudo());
	
		if(user.getProfile().getGender() != null) {
			if(user.getProfile().getGender().equals(Gender.Femme))
				femaleRadioButton.setSelected(true);
			else
				maleRadioButton.setSelected(true);
		}
		passwordField.setText(user.getPassword());
		firstNameTextField.setText(user.getProfile().getFirstName());
		lastNameTextField.setText(user.getProfile().getLastName());
		genderButtonGroup.add(maleRadioButton);
		genderButtonGroup.add(femaleRadioButton);
		radioButtonPanel.add(maleRadioButton);
		radioButtonPanel.add(femaleRadioButton);
		
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

		
		frameConstraints.anchor = GridBagConstraints.CENTER;
		frameConstraints.fill = GridBagConstraints.CENTER;
		frameConstraints.gridx = 1;
		frameConstraints.gridy = 1;
		this.add(pseudoLabel, frameConstraints);
		frameConstraints.gridy = GridBagConstraints.RELATIVE;
		this.add(passwordField, frameConstraints);
		this.add(firstNameTextField, frameConstraints);
		this.add(lastNameTextField, frameConstraints);
		this.add(radioButtonPanel, frameConstraints);
		this.add(datePanel, frameConstraints);


		frameConstraints.gridwidth = 2;
		frameConstraints.gridx = 0;
		frameConstraints.gridy = GridBagConstraints.RELATIVE;
		frameConstraints.insets = new Insets(20, 0, 5, 0);
		this.add(updateInfoButton, frameConstraints);
		this.add(errorLabel, frameConstraints);
		frameConstraints.insets = new Insets(40, 0, 5, 0);
		this.add(backButton, frameConstraints);
		
	}
	
	public void initStyle() {
		titleLabel.setFont(TITLE_FONT);
		errorLabel.setForeground(new Color(255, 0, 0));
	}
		
	public void initActions() {		

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
	

	
}
