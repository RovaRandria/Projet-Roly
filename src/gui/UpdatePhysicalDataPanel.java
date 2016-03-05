
package gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import data.PhysicalData;
import data.User;

public class UpdatePhysicalDataPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private User user;

	private JButton updatePhysicalDataButton = new JButton("Ajouter");
	private JButton backHomeButton = new JButton("Retour au profil");

	private JLabel titleLabel = new JLabel("Renseigner de nouvelles données physiques");
	private JLabel weightLabel = new JLabel("Poids : ");
	private JLabel waistSizeLabel = new JLabel("Tour de taille : ");
	private JLabel hipSizeLabel = new JLabel("Tour de hanche : ");

	private JTextField  weightField = new JTextField(5);
	private JTextField  waistSizeField  = new JTextField(5);
	private JTextField   hipSizeField  = new JTextField(5);
	
	private static final Font TITLE_FONT = new Font("Arial", Font.ITALIC|Font.BOLD, 15);

	public UpdatePhysicalDataPanel() {
	}

	public UpdatePhysicalDataPanel(User user) {
		this.user = user;
		initStyle();
		init();
		initActions();
	}

	public void init() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints frameConstraints = new GridBagConstraints();
		
		frameConstraints.insets = new Insets(5, 0, 10, 0);
		frameConstraints.gridx = 0;
		frameConstraints.gridy = 0;
		this.add(titleLabel, frameConstraints);
		
		frameConstraints.gridy = GridBagConstraints.RELATIVE;
		frameConstraints.insets = new Insets(2, 10, 2, 10);
		this.add(weightLabel, frameConstraints);
		this.add(weightField, frameConstraints);
		this.add(waistSizeLabel, frameConstraints);
		this.add(waistSizeField, frameConstraints);
		this.add(hipSizeLabel, frameConstraints);
		this.add(hipSizeField, frameConstraints);
		frameConstraints.insets = new Insets(10, 0, 5, 0);
		this.add(updatePhysicalDataButton, frameConstraints);
		
		frameConstraints.gridx = 1;
		frameConstraints.gridy = 4;
		frameConstraints.anchor = GridBagConstraints.LINE_END;
		frameConstraints.fill = GridBagConstraints.NONE;
		frameConstraints.insets = new Insets(0, 10, 0, 2);
		this.add(backHomeButton, frameConstraints);

		
		List<PhysicalData> physicalDataList = user.getProfile().getPhysicalDataList();
		if (physicalDataList.size()>0){
			PhysicalData latestPhysicalData = user.getProfile().getPhysicalDataList().get(user.getProfile().getPhysicalDataList().size()-1);
			weightField.setText(Float.toString(latestPhysicalData.getWeight()));
			waistSizeField.setText(Float.toString(latestPhysicalData.getWaistSize()));
			hipSizeField.setText(Float.toString(latestPhysicalData.getHipSize()));
		}
	}

	public void initStyle() {
		titleLabel.setFont(TITLE_FONT);
		updatePhysicalDataButton.setOpaque(false);
		backHomeButton.setOpaque(false);
		titleLabel.setOpaque(false);
		weightLabel.setOpaque(false);
		waistSizeLabel.setOpaque(false);
		hipSizeLabel.setOpaque(false);
		this.setOpaque(false);
	}

	public void initActions() {		

	}

	public JButton getUpdatePhysicalDataButton() {
		return updatePhysicalDataButton;
	}

	public void setUpdatePhysicalDataButton(JButton updatePhysicalDataButton) {
		this.updatePhysicalDataButton = updatePhysicalDataButton;
	}

	public JTextField getWeightField() {
		return weightField;
	}

	public void setWeightField(JTextField weightField) {
		this.weightField = weightField;
	}

	public JTextField getWaistSizeField() {
		return waistSizeField;
	}

	public void setWaistSizeField(JTextField waistSizeField) {
		this.waistSizeField = waistSizeField;
	}

	public JTextField getHipSizeField() {
		return hipSizeField;
	}

	public void setHipSizeField(JTextField hipSizeField) {
		this.hipSizeField = hipSizeField;
	}

	public JButton getBackHomeButton() {
		return backHomeButton;
	}

	public void setBackHomeButton(JButton backHomeButton) {
		this.backHomeButton = backHomeButton;
	}





}
