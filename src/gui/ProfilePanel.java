package gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.PhysicalData;
import data.User;


public class ProfilePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private User user;
	private JPanel physicalDataPanel = new JPanel();
	private boolean isMine;

	private JButton updateInfoButton = new JButton();
	private JButton showPhysicalDataButton = new JButton();
	private JButton sportManagerButton = new JButton();
	private JButton disconnectionButton = new JButton();	
	
	private JLabel homeLabel = new JLabel();
	private JLabel pseudoLabel = new JLabel();
	private JLabel registrationDateLabel = new JLabel();
	private JLabel firstNameLabel = new JLabel();
	private JLabel lastNameLabel = new JLabel();
	private JLabel birthdateLabel = new JLabel();
	private JLabel genderLabel = new JLabel();
	private JLabel sportsLabel = new JLabel();
	private JLabel physicalDataDateLabel = new JLabel();
	private JLabel physicalDataWeightLabel = new JLabel();
	private JLabel physicalDataHipLabel = new JLabel();
	private JLabel physicalDataWaistLabel = new JLabel();
	
	private static final Font TITLE_FONT = new Font("Arial", Font.ITALIC|Font.BOLD, 20);

	public ProfilePanel(User user, boolean isMine) {
		this.user = user;
		this.isMine = isMine;
		init();
		initStyle();
		initActions();
	}
	
	
	private void init() {
		if (isMine){
			pseudoLabel = new JLabel(user.getPseudo());
			homeLabel = new JLabel("Mon profil");
			registrationDateLabel = new JLabel(user.getProfile().getRegistrationDate().toString());
			firstNameLabel = new JLabel(user.getProfile().getFirstName());
			lastNameLabel = new JLabel(user.getProfile().getLastName());
			
			if(user.getProfile().getBirthdate() != null)
				birthdateLabel = new JLabel(user.getProfile().getBirthdate().toString());
			else
				birthdateLabel = new JLabel("Non renseigné");
			
			if(user.getProfile().getGender() != null)
				genderLabel = new JLabel(user.getProfile().getGender().toString());
			else
				genderLabel = new JLabel("Non renseigné");
			
			sportsLabel.setText(user.getProfile().displaySport());
			
			List<PhysicalData> physicalDataList = user.getProfile().getPhysicalDataList();
			if (physicalDataList.size()==0){
				physicalDataDateLabel = new JLabel("Non renseigné");	
			}
			else{
				PhysicalData latestPhysicalData = user.getProfile().getPhysicalDataList().get(user.getProfile().getPhysicalDataList().size()-1);
				physicalDataDateLabel = new JLabel("Dernière mesure prise le "+latestPhysicalData.getMeasureDate());	
				physicalDataWeightLabel = new JLabel("Poids : "+latestPhysicalData.getWeight());
				physicalDataHipLabel = new JLabel("Tour de hanche : "+latestPhysicalData.getHipSize());
				physicalDataWaistLabel = new JLabel("Tour de taille : "+latestPhysicalData.getWaistSize());
			}
			updateInfoButton = new JButton("Modifier mes informations");
			showPhysicalDataButton = new JButton("Détails");
			sportManagerButton = new JButton("Voir mes activités sportives");
			disconnectionButton = new JButton("Déconnexion");	
			
			
			setLayout(new GridBagLayout());
			GridBagConstraints frameConstraints = new GridBagConstraints();
	
			frameConstraints.insets = new Insets(5, 0, 40, 0);
			frameConstraints.gridwidth = 2;
			frameConstraints.gridx = 0;
			frameConstraints.gridy = 0;
			add(homeLabel, frameConstraints);
	
			frameConstraints.insets = new Insets(5, 10, 5, 50);
			frameConstraints.gridwidth = 1;
			frameConstraints.anchor = GridBagConstraints.WEST;
			frameConstraints.fill = GridBagConstraints.NONE;
			frameConstraints.gridy = GridBagConstraints.RELATIVE;
		
			add(new JLabel("Pseudo : "), frameConstraints);
			add(new JLabel("Inscription le : "), frameConstraints);
			add(new JLabel("Nom : "), frameConstraints);
			add(new JLabel("Prénom : "), frameConstraints);
			add(new JLabel("Date de naissance : "), frameConstraints);
			add(new JLabel("Genre : "), frameConstraints);
			add(new JLabel("Sports pratiqués : "), frameConstraints);
			
			if (physicalDataList.size()!=0)
				frameConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
			add(new JLabel("Données physiques : "), frameConstraints);
			
			
			frameConstraints.anchor = GridBagConstraints.CENTER;
			frameConstraints.fill = GridBagConstraints.CENTER;
			frameConstraints.gridx = 1;
			frameConstraints.gridy = 1;
			add(pseudoLabel, frameConstraints);
			frameConstraints.gridy = GridBagConstraints.RELATIVE;
			add(registrationDateLabel, frameConstraints);
			add(lastNameLabel, frameConstraints);
			add(firstNameLabel, frameConstraints);
			add(birthdateLabel, frameConstraints);
			add(genderLabel, frameConstraints);
			add(sportsLabel, frameConstraints);
			frameConstraints.insets = new Insets(5, 10, 3, 50);
			add(physicalDataDateLabel, frameConstraints);
			
			if (physicalDataList.size()!=0){
				frameConstraints.insets = new Insets(3, 10, 3, 50);
				add(physicalDataWeightLabel, frameConstraints);
				add(physicalDataHipLabel, frameConstraints);
				add(physicalDataWaistLabel, frameConstraints);
				add(showPhysicalDataButton, frameConstraints);
			}
			add(showPhysicalDataButton, frameConstraints);

			frameConstraints.insets = new Insets(30, 0, 3, 0);
			frameConstraints.gridwidth = 2;
			frameConstraints.gridx = 0;
			add(updateInfoButton, frameConstraints);
			frameConstraints.insets = new Insets(3, 0, 3, 0);
			add(sportManagerButton, frameConstraints);
			frameConstraints.insets = new Insets(40, 0, 5, 0);
			add(disconnectionButton, frameConstraints);
		}
	}
	
	private void initStyle() {
		homeLabel.setFont(TITLE_FONT);
		physicalDataPanel.setOpaque(false);
		updateInfoButton.setOpaque(false);
		showPhysicalDataButton.setOpaque(false);
		sportManagerButton.setOpaque(false);
		disconnectionButton.setOpaque(false);
		homeLabel.setOpaque(false);
		pseudoLabel.setOpaque(false);
		registrationDateLabel.setOpaque(false);
		firstNameLabel.setOpaque(false);
		lastNameLabel.setOpaque(false);
		birthdateLabel.setOpaque(false);
		genderLabel.setOpaque(false);
		sportsLabel.setOpaque(false);
		physicalDataDateLabel.setOpaque(false);
		physicalDataWeightLabel.setOpaque(false);
		physicalDataHipLabel.setOpaque(false);		
		physicalDataWaistLabel.setOpaque(false);
		this.setOpaque(false);
	}
	
	public void initActions() {		

	}


	public JPanel getPhysicalDataPanel() {
		return physicalDataPanel;
	}


	public void setPhysicalDataPanel(JPanel physicalDataPanel) {
		this.physicalDataPanel = physicalDataPanel;
	}


	public boolean isMine() {
		return isMine;
	}


	public void setMine(boolean isMine) {
		this.isMine = isMine;
	}


	public JButton getUpdateInfoButton() {
		return updateInfoButton;
	}


	public void setUpdateInfoButton(JButton updateInfoButton) {
		this.updateInfoButton = updateInfoButton;
	}


	public JButton getShowPhysicalDataButton() {
		return showPhysicalDataButton;
	}


	public void setShowPhysicalDataButton(JButton showPhysicalDataButton) {
		this.showPhysicalDataButton = showPhysicalDataButton;
	}


	public JButton getSportManagerButton() {
		return sportManagerButton;
	}


	public void setSportManagerButton(JButton sportManagerButton) {
		this.sportManagerButton = sportManagerButton;
	}


	public JButton getDisconnectionButton() {
		return disconnectionButton;
	}


	public void setDisconnectionButton(JButton disconnectionButton) {
		this.disconnectionButton = disconnectionButton;
	}

}
