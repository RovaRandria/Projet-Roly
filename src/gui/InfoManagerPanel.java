package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.hibernate.Session;

import utils.DateNumbersList;

import data.DBConnection;
import data.Gender;
import data.User;

public class InfoManagerPanel extends JPanel {
	private User user;
	
	private InfoManagerPanel instance = this;
	
	private JButton updateInfoButton = new JButton("Modifier");
	
	private JLabel passwordLabel = new JLabel("Mot de passe :");
	private JLabel firstNameLabel = new JLabel("Prénom :");
	private JLabel lastNameLabel = new JLabel("Nom :");
	private JLabel genderLabel = new JLabel("Sexe :");
	private JLabel birthdayLabel = new JLabel("Date de naissance :");
	
	private JPasswordField  passwordField = new JPasswordField(25);
	private JTextField  firstNameTextField  = new JTextField(25);
	private JTextField  lastNameTextField  = new JTextField(25);
	private JRadioButton maleRadioButton = new JRadioButton("Homme");
	private JRadioButton femaleRadioButton = new JRadioButton("Femme");
	private JComboBox birthdayDayComboBox = new JComboBox(DateNumbersList.day().toArray());
	private JComboBox birthdayMonthComboBox = new JComboBox(DateNumbersList.month().toArray());
	private JComboBox birthdayYearComboBox = new JComboBox(DateNumbersList.year().toArray());
	
	public InfoManagerPanel(User user) {
		this.user = user;
		initStyle();
		init();
		initActions();
	}
	
	public void init() {
		Session session = DBConnection.getSession();
		session.beginTransaction();
		this.setLayout(new GridBagLayout());
		GridBagConstraints frameConstraints = new GridBagConstraints();
		frameConstraints.gridx = 0;
		frameConstraints.gridy = 0;
		this.add(passwordLabel, frameConstraints);
		frameConstraints.gridx = 1;
		frameConstraints.gridy = 0;
		passwordField.setText(user.getPassword());
		this.add(passwordField, frameConstraints);
		frameConstraints.gridx = 0;
		frameConstraints.gridy = 1;
		this.add(firstNameLabel, frameConstraints);
		frameConstraints.gridx = 1;
		frameConstraints.gridy = 1;
		/*firstNameTextField.setText(user.getProfile().getFirstName());
		this.add(firstNameTextField, frameConstraints);
		frameConstraints.gridx = 0;
		frameConstraints.gridy = 2;
		this.add(lastNameLabel, frameConstraints);
		frameConstraints.gridx = 1;
		frameConstraints.gridy = 2;
		lastNameTextField.setText(user.getProfile().getLastName());
		this.add(lastNameTextField, frameConstraints);
		frameConstraints.gridx = 0;
		frameConstraints.gridy = 3;
		this.add(genderLabel, frameConstraints);
		frameConstraints.gridx = 1;
		frameConstraints.gridy = 3;
		this.add(maleRadioButton, frameConstraints);
		this.add(femaleRadioButton, frameConstraints);
		if(user.getProfile().getGender().equals(Gender.FEMALE))
			femaleRadioButton.setSelected(true);
		else
			maleRadioButton.setSelected(true);
		frameConstraints.gridx = 0;
		frameConstraints.gridy = 4;
		this.add(birthdayLabel);
		frameConstraints.gridx = 1;
		frameConstraints.gridy = 4;
		this.add(birthdayDayComboBox);
		this.add(birthdayMonthComboBox);
		this.add(birthdayYearComboBox);*/
	}
	
	public void initStyle() {
		//Font & Backgrounds Settings
	}
		
	public void initActions() {		
		updateInfoButton.addActionListener(new UpdateInfo());
	}
	
	private class UpdateInfo implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
		instance.repaint();
		}
	}	
	
}
