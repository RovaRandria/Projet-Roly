package data;

import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class DataInit {

	public static void createTables() {
		AnnotationConfiguration config = DBConnection.getConfig();
		SchemaExport schemaExport = new SchemaExport(config);
		schemaExport.create(true, true);
	}
	
	public static void insertSports() {
		Session session = DBConnection.getSession();
		session.beginTransaction();
		Sport climbing = new Sport("Escalade");
		Sport jogging = new Sport("Jogging");
		session.persist(climbing);
		session.persist(jogging);
		session.getTransaction().commit();
	}
	
	public static void insertExercises() {
		Session session = DBConnection.getSession();
		session.beginTransaction();
		Exercise pushup = new Exercise("Pompes", "20 pompes", AreaOfEffect.PECTORALS);
		Exercise situp = new Exercise("Abdominaux", "20 abdominaux", AreaOfEffect.ABDOMINALS);
		Exercise pullup = new Exercise("Tractions", "20 tractions", AreaOfEffect.ARMS);
		session.persist(pushup);
		session.persist(situp);
		session.persist(pullup);
		session.getTransaction().commit();
	}
}
