package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import data.User;


public class ProfilePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private User user;
	
	//private JPanel homePanel = new JPanel();

	private JLabel nameLabel;
	private JLabel birthdateLabel;
	private JLabel genderLabel;
	private JLabel sportsLabel = new JLabel();
	
	public ProfilePanel(User user) {
		this.user = user;
		initStyle();
		init();
		initActions();

	}
	public ProfilePanel() {
	}
	private void init() {		
		nameLabel = new JLabel(user.getProfile().getFirstName()+" "+user.getProfile().getLastName());
		birthdateLabel = new JLabel(user.getProfile().getBirthdate().toString());
		genderLabel = new JLabel(user.getProfile().getGender().toString());
		sportsLabel.setText("Sports pratiqués : " + user.getProfile().displaySport());
		this.setLayout(new GridBagLayout());
		GridBagConstraints frameConstraints = new GridBagConstraints();
		frameConstraints.gridx = 0;
		frameConstraints.gridy = 0;
		this.add(nameLabel, frameConstraints);
		frameConstraints.gridx = 0;
		frameConstraints.gridy = 1;
		this.add(birthdateLabel, frameConstraints);
		frameConstraints.gridx = 1;
		frameConstraints.gridy = 1;
		this.add(genderLabel, frameConstraints);
		frameConstraints.gridx = 0;
		frameConstraints.gridy = 2;
		this.add(sportsLabel, frameConstraints);
	}
	
	private void initStyle() {
	//Font & Backgrounds Settings
	}
	
	public void initActions() {		

	}
	

}
