package test;

import org.hibernate.Session;

public class Login {
	
	private User currentUser;
	
	private boolean coState;
	
	public Login() {
		//TABLES CREATION
		DataInit.createTables();
	}
	
	public Login (boolean coState) {
		//TABLES CREATION
		DataInit.createTables();
		this.coState = coState;
	}
	
	public User disconnect() {
		currentUser = null;
		coState = false;
		return null;
	}
	
	public int connect(String pseudo, String password, User retrievedUser) {
		System.out.println("DBCONNECTION");
		Session session = DBConnection.getSession();
		
		System.out.println("BEGIN TRANSACTION");
		session.beginTransaction();
		
		if(retrievedUser != null) {
			if(retrievedUser.getPassword().equals(password)) {
				System.out.println(retrievedUser.getPseudo() + " est bien connecté !");
				session.getTransaction().commit();			
				session.close();
				currentUser = retrievedUser;
				coState = true;
				return 1;
			}
			else {
				System.out.println("Mot de passe incorrect !");
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

	public User register(String pseudo, String password) {
		User newUser;
		//Test persist.
		Session session = DBConnection.getSession();
		session.beginTransaction();
		User retrievedUser = (User) session.get(User.class, pseudo);
		if(retrievedUser != null) { 
			System.out.println("Ce pseudo est déjà utilisé.");
			newUser = null;
		}
		else {
				newUser = new User(pseudo, password);
				session.persist(newUser);
				session.getTransaction().commit();
				System.out.println("L'utilisateur " + pseudo +" a bien été créé.");
		}
		return newUser;
	}
}
