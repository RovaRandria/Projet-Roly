package gui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.hibernate.Session;
import org.jfree.ui.RefineryUtilities;

import data.DBConnection;
import data.User;



public class SearchProfileGUI extends JFrame{
	private SearchProfileGUI instance = this;
	private static final long serialVersionUID = 1L;
	private ProfilePanel profilePanel;
	private JPanel searchPanel = new JPanel();
	private JPanel resultPanel = new JPanel();

	private JTextField searchTextField = new JTextField(10);
	private JButton searchButton = new JButton("Rechercher");
	private JButton addToFriendsButton = new JButton("Ajouter en ami");
	private JLabel friendsLabel = new JLabel("Amis avec vous");
	private JLabel resultLabel = new JLabel("");

	private JButton showPhysicDataChartButton = new JButton("Voir évolution physique");
	private JButton showJoggingPerfChartButton = new JButton("Voir évolution jogging");

	private User user;
	private User retrievedUser;

	private Box mainBox = Box.createVerticalBox();

	public SearchProfileGUI(User user) {
		super("Rechercher un sportif");
		this.user = user;
		initStyle();
		init();
		initActions();
	}
	public void init() {

		searchPanel.add(searchTextField);
		searchPanel.add(searchButton);

		mainBox.add(searchPanel);
		mainBox.add(resultPanel);

		RefineryUtilities.centerFrameOnScreen(this);
		this.add(mainBox);
		this.setSize(500, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);

	}

	public void initStyle(){

	}

	public void initActions(){
		searchButton.addActionListener(new searchAction());
		addToFriendsButton.addActionListener(new addToFriendsAction());
		showPhysicDataChartButton.addActionListener(new showChartAction());
		showJoggingPerfChartButton.addActionListener(new showJoggingPerfChartAction());
	}

	class searchAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			resultPanel.removeAll();
			Session session = DBConnection.getSession();
			session.beginTransaction();
			retrievedUser = (User) session.get(User.class, searchTextField.getText());
			session.getTransaction().commit();
			if (retrievedUser==null){
				resultLabel.setText("Aucun utilisateur trouvé.");
				resultPanel.add(resultLabel);
			}
			else{
				profilePanel = new ProfilePanel(retrievedUser, false);
				Boolean isFriend = false;
				for (int i = 0; i < user.getProfile().getFriends().size(); i++) {
					if(user.getProfile().getFriends().get(i).getId().equals(retrievedUser.getProfile().getId())) {
						isFriend = true;
					}										
				}
				if(isFriend) {
					resultPanel.add(friendsLabel);
					resultPanel.add(showJoggingPerfChartButton);
					resultPanel.add(showPhysicDataChartButton);
				}					

				else
					resultPanel.add(addToFriendsButton);
				resultPanel.add(profilePanel);

				init();
			}
		}
	}
	class addToFriendsAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Session session = DBConnection.getSession();
			session.beginTransaction();
			/*if(user.getFriends() == null) {
				ArrayList<Profile> friendsList = new ArrayList<Profile>();
				friendsList.add(retrievedUser.getProfile());
				user.setFriends(friendsList);
			}
			else*/
			user.getProfile().getFriends().add(retrievedUser.getProfile());
			session.merge(user);
			session.getTransaction().commit();
			JOptionPane.showMessageDialog(instance, retrievedUser.getPseudo() + " a bien été ajoué en ami !", "Ami ajouté", JOptionPane.INFORMATION_MESSAGE);
			resultPanel.removeAll();
			resultPanel.add(friendsLabel);
			resultPanel.add(showJoggingPerfChartButton);
			resultPanel.add(showPhysicDataChartButton);
			profilePanel = new ProfilePanel(retrievedUser, false);
			resultPanel.add(profilePanel);

		}
	}

	public static void main(String[] args) {
		//DataInit.createTables();
		Session session = DBConnection.getSession();
		session.beginTransaction();
		User user = (User) session.get(User.class, "roro");
		session.getTransaction().commit();
		new SearchProfileGUI(user);
	}

	class showChartAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new ShowChartsPanel(retrievedUser, 0);
		}
	}

	class showJoggingPerfChartAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new ShowChartsPanel(retrievedUser, 1);
		}
	}
}


