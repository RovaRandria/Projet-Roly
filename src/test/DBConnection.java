package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class DBConnection {
	private static SessionFactory sessionFactory;
	private static AnnotationConfiguration config;

	public static AnnotationConfiguration getConfig() {
		if (config == null) {
			config = new AnnotationConfiguration();
			config.addAnnotatedClass(Sport.class);
			config.addAnnotatedClass(Exercise.class);
			config.addAnnotatedClass(Practice.class);
			config.addAnnotatedClass(Exercise.class);
			config.addAnnotatedClass(Profile.class);
			config.addAnnotatedClass(PhysicalData.class);
			config.addAnnotatedClass(User.class);
			
			String packageName = DBConnection.class.getPackage().getName();
			config.configure(packageName + "/connection.cfg.xml");
		}
		return config;
	}

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
