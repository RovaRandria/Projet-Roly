package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.PhysicalData;
import data.User;


public class ProfilePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private User user;
	
	//private JPanel homePanel = new JPanel();

	private JLabel nameLabel;
	private JLabel birthdateLabel;
	private JLabel genderLabel;
	private JLabel sportsLabel = new JLabel();
	private JLabel physicalDataDateLabel;
	private JLabel physicalDataWeightLabel;
	private JLabel physicalDataHipLabel;
	private JLabel physicalDataWaistLabel;

	private Box physicalDataBox = Box.createVerticalBox();
	
	private JPanel physicalDataPanel = new JPanel();
	
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
		if(user.getProfile().getBirthdate() != null)
			birthdateLabel = new JLabel(user.getProfile().getBirthdate().toString());
		else
			birthdateLabel = new JLabel("Âge inconnu");
		if(user.getProfile().getGender() != null)
			genderLabel = new JLabel(user.getProfile().getGender().toString());
		else
			genderLabel = new JLabel("Sexe inconnu");
		sportsLabel.setText("Sports pratiqués : " + user.getProfile().displaySport());
		
		
		List<PhysicalData> physicalDataList = user.getProfile().getPhysicalDataList();
		if (physicalDataList.size()==0){
			physicalDataDateLabel = new JLabel("Vous n'avez pas encore renseigné vos données physiques.");	
			physicalDataBox.add(physicalDataDateLabel);
			physicalDataPanel.add(physicalDataBox);
		}
		else{
			PhysicalData p = physicalDataList.get(physicalDataList.size()-1);
			physicalDataDateLabel = new JLabel("Dernière mesure prise le "+p.getMeasureDate());	
			physicalDataWeightLabel = new JLabel("Poids : "+p.getWeight());
			physicalDataHipLabel = new JLabel("Tour de hanche : "+p.getHipSize());
			physicalDataWaistLabel = new JLabel("Tour de taille : "+p.getWaistSize());

			physicalDataBox.add(physicalDataDateLabel);
			physicalDataBox.add(physicalDataWeightLabel);
			physicalDataBox.add(physicalDataHipLabel);
			physicalDataBox.add(physicalDataWaistLabel);
			physicalDataPanel.add(physicalDataBox);
		}
		
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
		frameConstraints.gridx = 0;
		frameConstraints.gridy = 3;
		this.add(physicalDataPanel, frameConstraints);
	}
	
	private void initStyle() {
	//Font & Backgrounds Settings
	}
	
	public void initActions() {		

	}
	

}
