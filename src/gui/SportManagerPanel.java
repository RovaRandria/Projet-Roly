package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

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
	private JButton addPracticeButton = new JButton("Ajouter une séance");
	
	private JTextArea practicesTextArea = new JTextArea();
	
	private JLabel addSportLabel = new JLabel("Ajouter/supprimer sport : ");
	private JLabel sportsLabel = new JLabel();
	private JLabel practicesLabel = new JLabel();
	
	public SportManagerPanel() {
	}
	
	public SportManagerPanel(User user) {
		this.user = user;
		initStyle();
		init();
		initActions();
	}
	
	public void init() {
		Session session = DBConnection.getSession();
		session.beginTransaction();
		user = (User) session.get(User.class, user.getPseudo());
		this.setLayout(new GridBagLayout());
		GridBagConstraints frameConstraints = new GridBagConstraints();
		frameConstraints.gridx = 0; 
		frameConstraints.gridy = 0; 
		this.add(sportsLabel, frameConstraints);
		sportsLabel.setText("Vous pratiquez : " + user.getProfile().displaySport());
		frameConstraints.gridx = 0; 
		frameConstraints.gridy = 1; 
		this.add(addSportLabel, frameConstraints);
		frameConstraints.gridx = 1; 
		frameConstraints.gridy = 1; 
		this.add(sportComboBox, frameConstraints);
		frameConstraints.gridx = 2; 
		frameConstraints.gridy = 1; 
		this.add(addSportButton, frameConstraints);
		frameConstraints.gridx = 3; 
		frameConstraints.gridy = 1; 
		this.add(removeSportButton, frameConstraints);
		frameConstraints.gridx = 0; 
		frameConstraints.gridy = 2; 
		this.add(practicesLabel, frameConstraints);
		practicesLabel.setText("Vos dernières séances : ");
		frameConstraints.gridx = 1; 
		frameConstraints.gridy = 2; 
		this.add(addPracticeButton, frameConstraints);
		frameConstraints.gridx = 0; 
		frameConstraints.gridy = 3; 
		for(int i = 0; i < user.getProfile().getPracticesList().size(); i++) {
			practicesTextArea.append(user.getProfile().getPracticesList().get(i).toString()+"\n");
		}
		this.add(practicesTextArea, frameConstraints);
		session.getTransaction().commit();
	}
	
	public void initStyle() {
		//Font & Backgrounds Settings
	}
		
	public void initActions() {		
		addSportButton.addActionListener(new addSportAction());
		removeSportButton.addActionListener(new removeSportAction());
		addPracticeButton.addActionListener(new addPracticeAction());
	}
	
	private class addSportAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
		Session session = DBConnection.getSession();
		session.beginTransaction();
		Profile profile = (Profile) session.get(Profile.class, user.getProfile().getId());			  
		Sport sport = new Sport(sportComboBox.getSelectedItem().toString());
		if(!profile.getSportsList().contains(sport)) {
			profile.getSportsList().add(sport);			  
			JOptionPane.showMessageDialog(instance, "Le sport " + sport.getName() + " a bien été ajouté !", "Sport ajouté", JOptionPane.INFORMATION_MESSAGE);
			session.merge(profile);
		}
		else
			JOptionPane.showMessageDialog(instance, "Vous avez déjà indiqué " + sport.getName() + " !", "Erreur", JOptionPane.ERROR_MESSAGE);
		session.getTransaction().commit();
		init();
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
					profile.getSportsList().remove(profile.getSportsList().get(i));
					JOptionPane.showMessageDialog(instance, "Le sport " + sport.getName() + " a bien été retiré !", "Sport retiré", JOptionPane.INFORMATION_MESSAGE);
					session.merge(profile);
				}
				else
					JOptionPane.showMessageDialog(instance, "Vous ne pratiquez pas " + sport.getName() + " !", "Erreur", JOptionPane.ERROR_MESSAGE);
									
			}
			session.getTransaction().commit();
			init();
			instance.repaint();
		}
	}
	

	private class addPracticeAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			PracticeManagerFrame practiceManager = new PracticeManagerFrame(user);
		}
	}
}
