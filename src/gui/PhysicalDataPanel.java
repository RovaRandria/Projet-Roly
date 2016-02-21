
package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.hibernate.Session;

import utils.DataUtility;

import data.DBConnection;
import data.PhysicalData;
import data.Profile;
import data.User;

public class PhysicalDataPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private User user;

	private PhysicalDataPanel instance = this;

	private JButton updateInfoButton = new JButton("Modifier");

	private JLabel weightLabel = new JLabel("Poids : ");
	private JLabel waistSizeLabel = new JLabel("Tour de taille : ");
	private JLabel hipSizeLabel = new JLabel("Tour de hanche : ");

	private JTextField  weightField = new JTextField(5);
	private JTextField  waistSizeField  = new JTextField(5);
	private JTextField   hipSizeField  = new JTextField(5);


	public PhysicalDataPanel() {
	}

	public PhysicalDataPanel(User user) {
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
		this.add(weightLabel, frameConstraints);
		frameConstraints.gridx = 1;
		frameConstraints.gridy = 0;
		this.add(weightField, frameConstraints);
		frameConstraints.gridx = 0;
		frameConstraints.gridy = 1;
		this.add(waistSizeLabel, frameConstraints);
		frameConstraints.gridx = 1;
		frameConstraints.gridy = 1;
		this.add(waistSizeField, frameConstraints);
		frameConstraints.gridx = 0;
		frameConstraints.gridy = 2;
		this.add(hipSizeLabel, frameConstraints);
		frameConstraints.gridx = 1;
		frameConstraints.gridy = 2;
		this.add(hipSizeField, frameConstraints);
		frameConstraints.gridx = 0;
		frameConstraints.gridy = 3;
		this.add(updateInfoButton, frameConstraints);

		List<PhysicalData> physicalDataList = user.getProfile().getPhysicalDataList();
		if (physicalDataList.size()>0){
			PhysicalData latestPhysicalData = user.getProfile().getPhysicalDataList().get(user.getProfile().getPhysicalDataList().size()-1);
			weightField.setText(Float.toString(latestPhysicalData.getWeight()));
			waistSizeField.setText(Float.toString(latestPhysicalData.getWaistSize()));
			hipSizeField.setText(Float.toString(latestPhysicalData.getHipSize()));
		}
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

			Calendar calendar = Calendar.getInstance();

			PhysicalData p = new PhysicalData();
			p.setWeight(Float.parseFloat(weightField.getText()));
			p.setHipSize(Float.parseFloat(hipSizeField.getText()));
			p.setWaistSize(Float.parseFloat(waistSizeField.getText()));
			p.setMeasureDate(DataUtility.createDate(calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.YEAR)));

			for (int i = 0; i < retrievedProfile.getPhysicalDataList().size(); i++) {
				if(retrievedProfile.getPhysicalDataList().get(i).getMeasureDate().equals(p.getMeasureDate()))
					retrievedProfile.getPhysicalDataList().remove(i);	
			}
			retrievedProfile.getPhysicalDataList().add(p);

			JOptionPane.showMessageDialog(instance, "Vos données physiques ont bien été mises à jour !", "Informations à jour", JOptionPane.INFORMATION_MESSAGE);
			session.merge(retrievedProfile);

			session.getTransaction().commit();
			instance.repaint();
		}
	}	



}
