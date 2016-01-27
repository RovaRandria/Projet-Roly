package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.hibernate.Session;

import utils.AvailableSports;
import data.DBConnection;
import data.Profile;
import data.Sport;
import data.User;

public class SportManagerPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private User user;
	
	private SportManagerPanel instance = this;

	private JComboBox sportComboBox = new JComboBox(AvailableSports.getSportsListString().toArray());
	
	private JButton addSportButton = new JButton("Ajouter");
	
	private JButton removeSportButton = new JButton("Retirer");
	
	private JLabel addSportLabel = new JLabel("Ajouter/supprimer sport : ");
	
	public SportManagerPanel() {
	}
	
	public SportManagerPanel(User user) {
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
		this.add(addSportLabel, frameConstraints);
		frameConstraints.gridx = 1; 
		frameConstraints.gridy = 0; 
		this.add(sportComboBox, frameConstraints);
		frameConstraints.gridx = 2; 
		frameConstraints.gridy = 0; 
		this.add(addSportButton, frameConstraints);
		frameConstraints.gridx = 3; 
		frameConstraints.gridy = 0; 
		this.add(removeSportButton, frameConstraints);
	}
	
	public void initStyle() {
		//Font & Backgrounds Settings
	}
		
	public void initActions() {		
		addSportButton.addActionListener(new addSportAction());
		removeSportButton.addActionListener(new removeSportAction());
	}
	
	private class addSportAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
		Session session = DBConnection.getSession();
		session.beginTransaction();
		Profile profile = (Profile) session.get(Profile.class, user.getProfile().getId());			  
		Sport sport = new Sport(sportComboBox.getSelectedItem().toString());
		profile.getSportsList().add(sport);			  
		session.merge(profile);
		session.getTransaction().commit();
		instance.repaint();
		}
	}
	
	private class removeSportAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Session session = DBConnection.getSession();
			session.beginTransaction();
			Profile profile = (Profile) session.get(Profile.class, user.getProfile().getId());			  
			Sport sport = new Sport(sportComboBox.getSelectedItem().toString());
			for (int i = 0; i < profile.getSportsList().size(); i++) {
				if(profile.getSportsList().get(i).getName().equals(sport.getName())) {
					System.out.println("aaaaaaaazfaz");
					profile.getSportsList().remove(profile.getSportsList().get(i));	
					System.out.println("aazdzzaz");
					session.merge(profile);
					System.out.println("azfaz");
				}
				System.out.println(i);
			}
			session.getTransaction().commit();
			instance.repaint();
		}
	}
}
