
package gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utils.DataUtility;

import data.PhysicalData;
import data.User;

/**
 * Panel that show the form to update user's physical data
 * @author Angelique Nguyen & Rova Randrianantoanina
 * @version 1.0
 */
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
	
	private JComboBox dayComboBox = new JComboBox(DataUtility.day().toArray());
	private JComboBox monthComboBox = new JComboBox(DataUtility.month().toArray());
	private JComboBox yearComboBox = new JComboBox(DataUtility.year().toArray());
	
	private JPanel datePanel = new JPanel();
	
	private static final Font TITLE_FONT = new Font("Arial", Font.ITALIC|Font.BOLD, 15);

	public UpdatePhysicalDataPanel() {
	}

	/**
	 * Constructor
	 * @param user : update user's physical data 
	 * @see User
	 */
	public UpdatePhysicalDataPanel(User user) {
		this.user = user;
		initStyle();
		init();
	}

	/**
	 * Method that initialize the components on the panel
	 */
	public void init() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints frameConstraints = new GridBagConstraints();
		
		frameConstraints.insets = new Insets(5, 0, 10, 0);
		frameConstraints.gridx = 0;
		frameConstraints.gridy = 0;
		this.add(titleLabel, frameConstraints);
		
		Calendar cal = Calendar.getInstance();
		dayComboBox.setSelectedItem(cal.get(Calendar.DAY_OF_MONTH));
		monthComboBox.setSelectedItem(cal.get(Calendar.MONTH)+1);
		yearComboBox.setSelectedItem(cal.get(Calendar.YEAR));
		datePanel.add(dayComboBox);
		datePanel.add(monthComboBox);
		datePanel.add(yearComboBox);
		
		frameConstraints.gridy = GridBagConstraints.RELATIVE;
		frameConstraints.insets = new Insets(2, 10, 2, 10);
		add(new JLabel("Date : "), frameConstraints);
		this.add(datePanel, frameConstraints);
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

	/**
	 * Method that initialize the style of the components
	 */
	public void initStyle() {
		titleLabel.setFont(TITLE_FONT);
		updatePhysicalDataButton.setOpaque(false);
		backHomeButton.setOpaque(false);
		titleLabel.setOpaque(false);
		weightLabel.setOpaque(false);
		waistSizeLabel.setOpaque(false);
		hipSizeLabel.setOpaque(false);
		datePanel.setOpaque(false);
		this.setOpaque(false);
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public JLabel getTitleLabel() {
		return titleLabel;
	}

	public void setTitleLabel(JLabel titleLabel) {
		this.titleLabel = titleLabel;
	}

	public JLabel getWeightLabel() {
		return weightLabel;
	}

	public void setWeightLabel(JLabel weightLabel) {
		this.weightLabel = weightLabel;
	}

	public JLabel getWaistSizeLabel() {
		return waistSizeLabel;
	}

	public void setWaistSizeLabel(JLabel waistSizeLabel) {
		this.waistSizeLabel = waistSizeLabel;
	}

	public JLabel getHipSizeLabel() {
		return hipSizeLabel;
	}

	public void setHipSizeLabel(JLabel hipSizeLabel) {
		this.hipSizeLabel = hipSizeLabel;
	}

	public JComboBox getDayComboBox() {
		return dayComboBox;
	}

	public void setDayComboBox(JComboBox dayComboBox) {
		this.dayComboBox = dayComboBox;
	}

	public JComboBox getMonthComboBox() {
		return monthComboBox;
	}

	public void setMonthComboBox(JComboBox monthComboBox) {
		this.monthComboBox = monthComboBox;
	}

	public JComboBox getYearComboBox() {
		return yearComboBox;
	}

	public void setYearComboBox(JComboBox yearComboBox) {
		this.yearComboBox = yearComboBox;
	}

	public JPanel getDatePanel() {
		return datePanel;
	}

	public void setDatePanel(JPanel datePanel) {
		this.datePanel = datePanel;
	}
}
