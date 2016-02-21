package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.hibernate.Session;

import utils.DataUtility;
import utils.DateNumbersList;

import data.DBConnection;
import data.Gender;
import data.Profile;
import data.User;

public class InfoManagerPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private User user;
	
	private InfoManagerPanel instance = this;
	
	private JButton updateInfoButton = new JButton("Modifier");
	
	private JLabel passwordLabel = new JLabel("Mot de passe : ");
	private JLabel firstNameLabel = new JLabel("Prénom : ");
	private JLabel lastNameLabel = new JLabel("Nom : ");
	private JLabel genderLabel = new JLabel("Sexe : ");
	private JLabel birthdayLabel = new JLabel("Date de naissance : ");
	private JLabel userInfoLabel = new JLabel();
	
	private JPasswordField  passwordField = new JPasswordField(10);
	private JTextField  firstNameTextField  = new JTextField(10);
	private JTextField  lastNameTextField  = new JTextField(10);
	
	private ButtonGroup genderButtonGroup = new ButtonGroup();
	private JRadioButton maleRadioButton = new JRadioButton("Homme");
	private JRadioButton femaleRadioButton = new JRadioButton("Femme");
	
	private JComboBox birthdayDayComboBox = new JComboBox(DateNumbersList.day().toArray());
	private JComboBox birthdayMonthComboBox = new JComboBox(DateNumbersList.month().toArray());
	private JComboBox birthdayYearComboBox = new JComboBox(DateNumbersList.year().toArray());
	
	
	public InfoManagerPanel() {
	}
	
	public InfoManagerPanel(User user) {
		this.user = user;
		initStyle();
		init();
		initActions();
	}
	
	public void init() {
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
		firstNameTextField.setText(user.getProfile().getFirstName());
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
		genderButtonGroup.add(maleRadioButton);
		genderButtonGroup.add(femaleRadioButton);
		frameConstraints.gridx = 1;
		frameConstraints.gridy = 3;
		this.add(maleRadioButton, frameConstraints);
		frameConstraints.gridx = 2;
		frameConstraints.gridy = 3;
		this.add(femaleRadioButton, frameConstraints);
		if(user.getProfile().getGender() != null) {
			if(user.getProfile().getGender().equals(Gender.Femme))
				femaleRadioButton.setSelected(true);
			else
				maleRadioButton.setSelected(true);
		}
		frameConstraints.gridx = 0;
		frameConstraints.gridy = 4;
		this.add(birthdayLabel, frameConstraints);
		frameConstraints.gridx = 1;
		frameConstraints.gridy = 4;
		Calendar cal = Calendar.getInstance();
		if(user.getProfile().getBirthdate() != null) {
			cal.setTime(user.getProfile().getBirthdate());
			birthdayDayComboBox.setSelectedItem(cal.get(Calendar.DAY_OF_MONTH));
			birthdayMonthComboBox.setSelectedItem(cal.get(Calendar.MONTH)+1);
			birthdayYearComboBox.setSelectedItem(cal.get(Calendar.YEAR));
		}
		this.add(birthdayDayComboBox, frameConstraints);
		frameConstraints.gridx = 2;
		frameConstraints.gridy = 4;
		this.add(birthdayMonthComboBox, frameConstraints);
		frameConstraints.gridx = 3;
		frameConstraints.gridy = 4;
		this.add(birthdayYearComboBox, frameConstraints);
		frameConstraints.gridx = 1;
		frameConstraints.gridy = 5;
		this.add(updateInfoButton, frameConstraints);
		frameConstraints.gridx = 0;
		frameConstraints.gridy = 6;
		this.add(userInfoLabel, frameConstraints);
		
	}
	
	public void initStyle() {
		//Font & Backgrounds Settings
	}
		
	public void initActions() {		
		updateInfoButton.addActionListener(new UpdateInfo());
	}
	
	class UpdateInfo implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
		Session session = DBConnection.getSession();
		System.out.println("BEGIN TRANSACTION");
		session.beginTransaction();
		Profile retrievedProfile = (Profile) session.get(Profile.class, user.getProfile().getId());
			
		if (String.valueOf(passwordField.getPassword()).isEmpty()||(String.valueOf(passwordField.getPassword()).length()<6)){
				userInfoLabel.setText("Veuillez saisir un mot de passe de 6 caractères minimum.");
		}
		
		else user.setPassword(String.valueOf(passwordField.getPassword()));
		
		retrievedProfile.setFirstName(firstNameTextField.getText());
		
		retrievedProfile.setLastName(lastNameTextField.getText());
		
		if(maleRadioButton.isSelected())
			retrievedProfile.setGender(Gender.Homme);
		else if (femaleRadioButton.isSelected())
			retrievedProfile.setGender(Gender.Femme);
		
		retrievedProfile.setBirthdate(DataUtility.createDate((Integer)birthdayDayComboBox.getSelectedItem(), (Integer)birthdayMonthComboBox.getSelectedItem(), (Integer)birthdayYearComboBox.getSelectedItem()));
		JOptionPane.showMessageDialog(instance, "Vos informations ont bien été mises à jour !", "Informations à jour", JOptionPane.INFORMATION_MESSAGE);
		session.merge(retrievedProfile);
		
		session.getTransaction().commit();
		instance.repaint();
		}
	}	
	
}
