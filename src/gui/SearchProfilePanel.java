package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import data.User;


public class SearchProfilePanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private ProfilePanel profilePanel;
	private JPanel searchPanel = new JPanel();
	private JPanel resultPanel;

	private JTextField searchTextField = new JTextField(10);
	private JButton searchButton = new JButton("Rechercher");
	private JButton backHomeButton = new JButton("Retour au profil");

	private JLabel titleLabel = new JLabel("Recherche de sportifs");
	private JLabel subtitleLabel = new JLabel("Entrez le pseudo de l'utilisateur que vous recherchez");
	private JLabel resultLabel;
	private User retrievedUser;
	private static final Font TITLE_FONT = new Font("Arial", Font.ITALIC|Font.BOLD, 20);

//	private JButton showPhysicDataChartButton = new JButton("Voir évolution physique");
//	private JButton showJoggingPerfChartButton = new JButton("Voir évolution jogging");
//	private JButton addToFriendsButton = new JButton("Ajouter en ami");
//	private JLabel friendsLabel = new JLabel("Amis avec vous");
//	private User user;

	private Box mainBox = Box.createVerticalBox();

	public SearchProfilePanel(User user) {
		this.retrievedUser = user;
		init();
		initStyle();
		initActions();
	}
	
	public void repaintPanel(){
		init();
		initStyle();
		initActions();
	}
	public void init() {
		searchPanel.add(searchTextField);
		searchPanel.add(searchButton);
		
		if (resultPanel==null)
			resultPanel = new JPanel();
		if (resultLabel==null)
			resultLabel = new JLabel("");	

		setLayout(new GridBagLayout());
		GridBagConstraints frameConstraints = new GridBagConstraints();

		frameConstraints.insets = new Insets(5, 0, 40, 0);
		frameConstraints.gridx = 0;
		frameConstraints.gridy = 0;
		add(titleLabel, frameConstraints);

		frameConstraints.insets = new Insets(5, 0, 5, 0);
		frameConstraints.gridy = GridBagConstraints.RELATIVE;
		add(subtitleLabel, frameConstraints);
		add(searchPanel, frameConstraints);
		add(resultPanel, frameConstraints);
		add(backHomeButton, frameConstraints);

	}

	public void initStyle(){
		titleLabel.setFont(TITLE_FONT);
		resultLabel.setForeground(new Color(255, 0, 0));

		titleLabel.setOpaque(false);
		subtitleLabel.setOpaque(false);
		searchPanel.setOpaque(false);
		if (resultPanel!=null)
			resultPanel.setOpaque(false);
		searchButton.setOpaque(false);
		if (resultLabel==null)
			resultLabel.setOpaque(false);
		this.setOpaque(false);
	}

	public void initActions(){
//		addToFriendsButton.addActionListener(new addToFriendsAction());
//		showPhysicDataChartButton.addActionListener(new showChartAction());
//		showJoggingPerfChartButton.addActionListener(new showJoggingPerfChartAction());
	}

	
	

//	class addToFriendsAction implements ActionListener {
//		public void actionPerformed(ActionEvent e) {
//			Session session = DBConnection.getSession();
//			session.beginTransaction();
//			/*if(user.getFriends() == null) {
//				ArrayList<Profile> friendsList = new ArrayList<Profile>();
//				friendsList.add(retrievedUser.getProfile());
//				user.setFriends(friendsList);
//			}
//			else*/
//			user.getProfile().getFriends().add(retrievedUser.getProfile());
//			session.merge(user);
//			session.getTransaction().commit();
//			JOptionPane.showMessageDialog(instance, retrievedUser.getPseudo() + " a bien été ajoué en ami !", "Ami ajouté", JOptionPane.INFORMATION_MESSAGE);
//			resultPanel.removeAll();
//			resultPanel.add(friendsLabel);
//			resultPanel.add(showJoggingPerfChartButton);
//			resultPanel.add(showPhysicDataChartButton);
//			profilePanel = new ProfilePanel(retrievedUser, false);
//			resultPanel.add(profilePanel);
//
//		}
//	}
//
//	class showChartAction implements ActionListener {
//		public void actionPerformed(ActionEvent e) {
//			new PhysicalDataChartPanel(retrievedUser);
//		}
//	}
//
//	class showJoggingPerfChartAction implements ActionListener {
//		public void actionPerformed(ActionEvent e) {
//			new PerformanceChartPanel(retrievedUser);
//		}
//	}

	public JTextField getSearchTextField() {
		return searchTextField;
	}
	public void setSearchTextField(JTextField searchTextField) {
		this.searchTextField = searchTextField;
	}
	public JButton getSearchButton() {
		return searchButton;
	}
	public void setSearchButton(JButton searchButton) {
		this.searchButton = searchButton;
	}
	public ProfilePanel getProfilePanel() {
		return profilePanel;
	}
	public void setProfilePanel(ProfilePanel profilePanel) {
		this.profilePanel = profilePanel;
	}
	public JPanel getSearchPanel() {
		return searchPanel;
	}
	public void setSearchPanel(JPanel searchPanel) {
		this.searchPanel = searchPanel;
	}
	public JPanel getResultPanel() {
		return resultPanel;
	}
	public void setResultPanel(JPanel resultPanel) {
		this.resultPanel = resultPanel;
	}
	public JLabel getResultLabel() {
		return resultLabel;
	}
	public void setResultLabel(JLabel resultLabel) {
		this.resultLabel = resultLabel;
	}
	public User getRetrievedUser() {
		return retrievedUser;
	}
	public void setRetrievedUser(User retrievedUser) {
		this.retrievedUser = retrievedUser;
	}
	public Box getMainBox() {
		return mainBox;
	}
	public void setMainBox(Box mainBox) {
		this.mainBox = mainBox;
	}

	public JButton getBackHomeButton() {
		return backHomeButton;
	}

	public void setBackHomeButton(JButton backHomeButton) {
		this.backHomeButton = backHomeButton;
	}
	
}


