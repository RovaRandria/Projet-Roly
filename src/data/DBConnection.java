package data;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
/**
 * This class links the different data classes to the database
 * @author Rova
 *
 */
public class DBConnection {
	private static SessionFactory sessionFactory;
	private static AnnotationConfiguration config;

	/**
	 * This class create the config of the database from the file "connection.cfg.xml" and create the link beetween the different tables
	 * @return
	 */
	public static AnnotationConfiguration getConfig() {
		if (config == null) {
			config = new AnnotationConfiguration();
			config.addAnnotatedClass(Sport.class);
			config.addAnnotatedClass(Practice.class);
			config.addAnnotatedClass(Exercise.class);
			config.addAnnotatedClass(Profile.class);
			config.addAnnotatedClass(Practice.class);
			config.addAnnotatedClass(User.class);
			config.addAnnotatedClass(PhysicalData.class);
			config.addAnnotatedClass(User.class);
			
			String packageName = DBConnection.class.getPackage().getName();
			config.configure(packageName + "/connection.cfg.xml");
		}
		return config;
	}

	/**
	 * Create a session to access the database
	 * @return
	 */
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				AnnotationConfiguration config = getConfig();
				sessionFactory = config.buildSessionFactory();
			} catch (Throwable ex) {
				System.err.println("Initial SessionFactory creation failed." + ex);
				throw new ExceptionInInitializerError(ex);
			}
		}
		return sessionFactory;
	}

	public static Session getSession() {
		return getSessionFactory().openSession();
	}
}
