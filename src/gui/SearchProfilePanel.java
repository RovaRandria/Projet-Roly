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

/**
 * Panel that show the form to search another user
 * @author Angelique Nguyen & Rova Randrianantoanina
 * @version 1.0
 */
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

	private Box mainBox = Box.createVerticalBox();

	/**
	 * Constructor
	 * @param user : the user logged in
	 */
	public SearchProfilePanel(User user) {
		this.retrievedUser = user;
		init();
		initStyle();
	}
	
	/**
	 * Method that reinitialize the main panel
	 */
	public void repaintPanel(){
		init();
		initStyle();
	}
	
	/**
	 * Method that initialize the components on the panel
	 */
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

	/**
	 * Method that initialize the style of the components
	 */
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


