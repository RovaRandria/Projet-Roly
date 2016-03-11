package gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.PhysicalData;
import data.User;

/**
 * Panel that show the profile of a user
 * @author Angelique Nguyen & Rova Randrianantoanina
 * @version 1.0
 */
public class ProfilePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private User user;
	private JPanel physicalDataPanel = new JPanel();
	private boolean isMine;
	private int privacy;

	private JButton updateInfoButton = new JButton();
	private JButton showPhysicalDataButton = new JButton();
	private JButton sportManagerButton = new JButton();
	private JButton disconnectionButton = new JButton();	
	private JButton searchButton = new JButton();	
	private JButton showPerfButton = new JButton();	
	
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
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	
	/**
	 * Constructor
	 * @param user : the user related to the profile
	 * @param isMine : true when the profile is the profile of the user logged in, false otherwise
	 */
	public ProfilePanel(User user, boolean isMine) {
		this.user = user;
		this.isMine = isMine;
		this.privacy = user.getProfile().getPrivacy();
		init();
		initStyle();
	}
	
	public ProfilePanel (){
		
	}
	
	/**
	 * Method that reinitialize the main panel
	 */
	public void repaintPanel(){
		init();
		initStyle();
	}
	
	/**
	 * Method that initialize the components on the panel
	 */
	public void init() {
		if (isMine){
			pseudoLabel = new JLabel(user.getPseudo());
			homeLabel = new JLabel("Mon profil");
			registrationDateLabel = new JLabel(sdf.format(user.getProfile().getRegistrationDate()));
			if (user.getProfile().getFirstName()!=null && !user.getProfile().getFirstName().equals(""))
				firstNameLabel = new JLabel(user.getProfile().getFirstName());
			else
				firstNameLabel = new JLabel("Non renseigné");

			if (user.getProfile().getLastName()!=null && !user.getProfile().getLastName().equals(""))
				lastNameLabel = new JLabel(user.getProfile().getLastName());
			else
				lastNameLabel = new JLabel("Non renseigné");
			
			if(user.getProfile().getBirthdate() != null){
				birthdateLabel = new JLabel(sdf.format(user.getProfile().getBirthdate()));
			}
			else
				birthdateLabel = new JLabel("Non renseigné");
			
			if(user.getProfile().getGender() != null)
				genderLabel = new JLabel(user.getProfile().getGender().toString());
			else
				genderLabel = new JLabel("Non renseigné");
			
			if (user.getProfile().displaySport()!=null && !user.getProfile().displaySport().equals(""))
				sportsLabel.setText(user.getProfile().displaySport());
			else
				sportsLabel.setText("Non renseigné");
			
			List<PhysicalData> physicalDataList = user.getProfile().getPhysicalDataList();
			if (physicalDataList.size()==0){
				physicalDataDateLabel = new JLabel("Non renseigné");	
			}
			else{
				PhysicalData latestPhysicalData = user.getProfile().getPhysicalDataList().get(user.getProfile().getPhysicalDataList().size()-1);
				physicalDataDateLabel = new JLabel("Dernière mesure prise le "+sdf.format(latestPhysicalData.getMeasureDate()));
				physicalDataWeightLabel = new JLabel("Poids : "+latestPhysicalData.getWeight());
				physicalDataHipLabel = new JLabel("Tour de hanche : "+latestPhysicalData.getHipSize());
				physicalDataWaistLabel = new JLabel("Tour de taille : "+latestPhysicalData.getWaistSize());
			}
			updateInfoButton = new JButton("Modifier mes informations");
			showPhysicalDataButton = new JButton("Détails");
			sportManagerButton = new JButton("Voir mes activités sportives");
			searchButton = new JButton("Rechercher un sportif");
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
			add(searchButton, frameConstraints);
			frameConstraints.insets = new Insets(35, 0, 5, 0);
			add(disconnectionButton, frameConstraints);
		}
		else{
			// Public
			if (privacy==0){
				pseudoLabel = new JLabel(user.getPseudo());
				homeLabel = new JLabel("Profil de "+user.getPseudo());
				registrationDateLabel = new JLabel(sdf.format(user.getProfile().getRegistrationDate()));
				if (user.getProfile().getFirstName()!=null && !user.getProfile().getFirstName().equals(""))
					firstNameLabel = new JLabel(user.getProfile().getFirstName());
				else
					firstNameLabel = new JLabel("Non renseigné");

				if (user.getProfile().getLastName()!=null && !user.getProfile().getLastName().equals(""))
					lastNameLabel = new JLabel(user.getProfile().getLastName());
				else
					lastNameLabel = new JLabel("Non renseigné");
				
				if(user.getProfile().getBirthdate() != null){
					birthdateLabel = new JLabel(sdf.format(user.getProfile().getBirthdate()));
				}
				else
					birthdateLabel = new JLabel("Non renseigné");
				
				if(user.getProfile().getGender() != null)
					genderLabel = new JLabel(user.getProfile().getGender().toString());
				else
					genderLabel = new JLabel("Non renseigné");
				
				if (user.getProfile().displaySport()!=null && !user.getProfile().displaySport().equals(""))
					sportsLabel.setText(user.getProfile().displaySport());
				else
					sportsLabel.setText("Non renseigné");
				
				List<PhysicalData> physicalDataList = user.getProfile().getPhysicalDataList();
				if (physicalDataList.size()==0){
					physicalDataDateLabel = new JLabel("Non renseigné");	
				}
				else{
					PhysicalData latestPhysicalData = user.getProfile().getPhysicalDataList().get(user.getProfile().getPhysicalDataList().size()-1);
					physicalDataDateLabel = new JLabel("Dernière mesure prise le "+sdf.format(latestPhysicalData.getMeasureDate()));
					physicalDataWeightLabel = new JLabel("Poids : "+latestPhysicalData.getWeight());
					physicalDataHipLabel = new JLabel("Tour de hanche : "+latestPhysicalData.getHipSize());
					physicalDataWaistLabel = new JLabel("Tour de taille : "+latestPhysicalData.getWaistSize());
				}
				showPerfButton = new JButton("Comparer nos performances");	
				
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
				}

				frameConstraints.insets = new Insets(30, 0, 3, 0);
				frameConstraints.gridwidth = 2;
				frameConstraints.gridx = 0;
				add(showPerfButton, frameConstraints);
			}
			else{
				// privé
				if (privacy==1){
					pseudoLabel = new JLabel(user.getPseudo());
					homeLabel = new JLabel("Profil de "+user.getPseudo());
					registrationDateLabel = new JLabel(sdf.format(user.getProfile().getRegistrationDate()));
		
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
					add(new JLabel("Ce profil est privé."), frameConstraints);
					
					frameConstraints.anchor = GridBagConstraints.CENTER;
					frameConstraints.fill = GridBagConstraints.CENTER;
					frameConstraints.gridx = 1;
					frameConstraints.gridy = 1;
					add(pseudoLabel, frameConstraints);
					frameConstraints.gridy = GridBagConstraints.RELATIVE;
					add(registrationDateLabel, frameConstraints);
				}
			}
		}
	}
	
	/**
	 * Method that initialize the style of the components
	 */
	public void initStyle() {
		homeLabel.setFont(TITLE_FONT);
		physicalDataPanel.setOpaque(false);
		updateInfoButton.setOpaque(false);
		showPhysicalDataButton.setOpaque(false);
		sportManagerButton.setOpaque(false);
		searchButton.setOpaque(false);
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

	public JPanel getPhysicalDataPanel() {
		return physicalDataPanel;
	}


	public JButton getSearchButton() {
		return searchButton;
	}

	public void setSearchButton(JButton searchButton) {
		this.searchButton = searchButton;
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


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

	public JButton getShowPerfButton() {
		return showPerfButton;
	}

	public void setShowPerfButton(JButton showPerfButton) {
		this.showPerfButton = showPerfButton;
	}

}
