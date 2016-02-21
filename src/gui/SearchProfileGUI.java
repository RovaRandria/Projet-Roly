package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.hibernate.Session;
import org.jfree.ui.RefineryUtilities;

import data.DBConnection;
import data.User;



public class SearchProfileGUI extends JFrame{

	private static final long serialVersionUID = 1L;
	private ProfilePanel profilePanel = new ProfilePanel();
	private JPanel searchPanel = new JPanel();
	private JPanel resultPanel = new JPanel();

	private JTextField searchTextField = new JTextField(10);
	private JButton searchButton = new JButton("Rechercher");
	private JLabel resultLabel = new JLabel("");
	
	private Box mainBox = Box.createVerticalBox();
	
	public SearchProfileGUI() {
		super("Rechercher un sportif");

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
	}
	
	class searchAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			resultPanel.removeAll();
			Session session = DBConnection.getSession();
			session.beginTransaction();
			User user = (User) session.get(User.class, searchTextField.getText());
			session.getTransaction().commit();
			if (user==null){
				resultLabel.setText("Aucun utilisateur trouvé.");
				resultPanel.add(resultLabel);
			}
			else{
				profilePanel = new ProfilePanel(user);
				resultPanel.add(profilePanel);
			}	
			init();
		}
	}
	
	public static void main(String[] args) {
		//DataInit.createTables();
		new SearchProfileGUI();
	}
}
	


