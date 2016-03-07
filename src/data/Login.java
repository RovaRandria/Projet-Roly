package data;

import java.util.Calendar;

import org.hibernate.Session;

import utils.DataUtility;
/**
 * Include the login system methods
 * @author Rova
 *
 */
public class Login {
	
	private User currentUser;
	
	private boolean coState;
	
	private Session session;
	
	public Login (boolean coState) {
		this.coState = coState;
	}
	
	public User disconnect() {
		currentUser = null;
		coState = false;
		return null;
	}
	
	/**
	 * Connect a user
	 * @param pseudo
	 * @param password
	 * @param retrievedUser
	 * @param session
	 * @return
	 */
	public int connect(String pseudo, String password, User retrievedUser, Session session) {		
		if(retrievedUser != null) {
			if(retrievedUser.getPassword().equals(password)) {
				System.out.println("L'utilisateur "+retrievedUser.getPseudo() + " est bien connecté !");
				currentUser = retrievedUser;
				coState = true;
				return 1;
			}
			else {
				System.out.println(	"Mot de passe incorrect !");
				retrievedUser = null;
				return 2;
			}
		}
		else {
			System.out.println("L'utilisateur " + pseudo + " n'existe pas.");
			return 0;
		}		
	}
	
	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public boolean isCoState() {
		return coState;
	}

	public void setCoState(boolean coState) {
		this.coState = coState;
	}
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	/**
	 * Register an new user
	 * @param pseudo
	 * @param password
	 * @return
	 */
	public User register(String pseudo, String password) {
		User newUser;

		Session session = DBConnection.getSession();
		session.beginTransaction();
		//Test persist.
		
		User retrievedUser = (User) session.get(User.class, pseudo);
		if(retrievedUser != null) { 
			System.out.println("Ce pseudo est déjà utilisé.");
			newUser = null;
		}
		else {
				newUser = new User(pseudo, password);
				Calendar calendar = Calendar.getInstance();
				Profile profile = new Profile("", "", DataUtility.createDate(calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.YEAR)),1);
				newUser.setProfile(profile);
				session.persist(newUser);
				session.persist(profile);
				session.getTransaction().commit();
				System.out.println("L'utilisateur " + pseudo +" a bien été créé.");
				session.close();
		}
		return newUser;
	}
}
