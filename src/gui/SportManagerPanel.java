package gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.hibernate.Session;

import utils.AvailableSports;
import data.DBConnection;
import data.User;

public class SportManagerPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private User user;
		
	private JComboBox sportComboBox;
	
	private JButton addSportButton = new JButton("Ajouter");	
	private JButton removeSportButton = new JButton("Supprimer");
	private JButton showPracticePanelButton = new JButton("Détails");
	private JButton showPerfChartButton = new JButton("Voir mes performances");
	private JButton backHomeButton = new JButton("Retour au profil");
	
//	private JScrollPane practicesJScrollPane = new JScrollPane();
//	
//	private JTextArea practicesTextArea = new JTextArea();
	
	private JLabel titleLabel = new JLabel("Mes activités sportives");
	private JLabel sportsLabel = new JLabel();
	
	private JPanel addDeletePanel = new JPanel();
	
	private static final Font TITLE_FONT = new Font("Arial", Font.ITALIC|Font.BOLD, 15);
	
	public SportManagerPanel() {
	}
	
	public SportManagerPanel(User user) {
		this.user = user;
		initStyle();
		init();
		initActions();
	}
	
	public void repaintPanel(){
		initStyle();
		init();
		initActions();
	}
	
	public void init() {		
		Session session = DBConnection.getSession();
		session.beginTransaction();
		user = (User) session.get(User.class, user.getPseudo());
		sportsLabel = new JLabel(user.getProfile().displaySport());
		sportComboBox = new JComboBox(AvailableSports.getSportsListString().toArray());
		
		addDeletePanel.add(addSportButton);
		addDeletePanel.add(removeSportButton);
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints frameConstraints = new GridBagConstraints();
		
		frameConstraints.insets = new Insets(5, 0, 40, 0);
		frameConstraints.gridwidth = 2;
		frameConstraints.gridx = 0; 
		frameConstraints.gridy = 0; 
		add(titleLabel, frameConstraints);
		
		frameConstraints.anchor = GridBagConstraints.WEST;
		frameConstraints.fill = GridBagConstraints.NONE;
		frameConstraints.insets = new Insets(20, 0, 5, 20);
		frameConstraints.gridwidth = 1;
		frameConstraints.gridy = 1; 
		add(new JLabel("Sport(s) pratiqué(s) : "), frameConstraints);
		frameConstraints.gridy = 3; 
		add(new JLabel("Gérer mes sports : "), frameConstraints);
		frameConstraints.gridy = 5; 
		add(new JLabel("Dernière séance : "), frameConstraints);
		

		frameConstraints.gridx = 1;
		frameConstraints.gridy = 1; 
		frameConstraints.insets = new Insets(20, 20, 5, 0);
		add(sportsLabel, frameConstraints);
		frameConstraints.gridy = GridBagConstraints.RELATIVE; 
		frameConstraints.insets = new Insets(5, 20, 20, 0);
		add(showPerfChartButton, frameConstraints);
		frameConstraints.insets = new Insets(20, 20, 5, 0);
		add(sportComboBox, frameConstraints);
		frameConstraints.insets = new Insets(5, 20, 20, 0);
		add(addDeletePanel, frameConstraints);
		
		frameConstraints.insets = new Insets(20, 20, 5, 0);
		frameConstraints.gridy = GridBagConstraints.RELATIVE; 
		for(int i = 0; i < user.getProfile().getPracticesList().size(); i++) {
			add(new JLabel(user.getProfile().getPracticesList().get(i).toString()), frameConstraints);
		}
		frameConstraints.insets = new Insets(10, 20, 10, 0);
		add(showPracticePanelButton, frameConstraints);

		frameConstraints.gridx = 0;
		frameConstraints.gridwidth = 2;
		frameConstraints.anchor = GridBagConstraints.CENTER;
		frameConstraints.fill = GridBagConstraints.CENTER;
		frameConstraints.insets = new Insets(30, 0, 5, 0);
		add(backHomeButton, frameConstraints);


//		practicesTextArea.setEditable(false);
//		practicesJScrollPane = new JScrollPane(practicesTextArea);
//
//		practicesJScrollPane.setMinimumSize(new Dimension(500,100));
//
//		frameConstraints.gridx = 0; 
//		frameConstraints.gridy = 1; 
//		this.add(practicesJScrollPane, frameConstraints);
		session.getTransaction().commit();
	}
	
	public void initStyle() {
		titleLabel.setFont(TITLE_FONT);
	}
		
	public void initActions() {		

	}

	public JComboBox getSportComboBox() {
		return sportComboBox;
	}

	public void setSportComboBox(JComboBox sportComboBox) {
		this.sportComboBox = sportComboBox;
	}

	public JButton getAddSportButton() {
		return addSportButton;
	}

	public void setAddSportButton(JButton addSportButton) {
		this.addSportButton = addSportButton;
	}

	public JButton getRemoveSportButton() {
		return removeSportButton;
	}

	public void setRemoveSportButton(JButton removeSportButton) {
		this.removeSportButton = removeSportButton;
	}

	public JButton getShowPracticePanelButton() {
		return showPracticePanelButton;
	}

	public void setShowPracticePanelButton(JButton showPracticePanelButton) {
		this.showPracticePanelButton = showPracticePanelButton;
	}

	public JButton getShowPerfChartButton() {
		return showPerfChartButton;
	}

	public void setShowPerfChartButton(JButton showPerfChartButton) {
		this.showPerfChartButton = showPerfChartButton;
	}

	public JButton getBackHomeButton() {
		return backHomeButton;
	}

	public void setBackHomeButton(JButton backHomeButton) {
		this.backHomeButton = backHomeButton;
	}

	public JLabel getSportsLabel() {
		return sportsLabel;
	}

	public void setSportsLabel(JLabel sportsLabel) {
		this.sportsLabel = sportsLabel;
	}

	
}
