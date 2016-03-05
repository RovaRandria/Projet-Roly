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
		Sport climbing = new Sport("Escalade", "Escalade quoi");
		Sport jogging = new Sport("Jogging", "Jogging quoi");
		Sport cycling = new Sport("Vélo", "Du vélo quoi");
		Sport ski = new Sport("Ski", "Ski quoi");
		Sport bodybuilding = new Sport("Musculation", "Muscu quoi");
		session.persist(climbing);
		session.persist(jogging);
		session.persist(cycling);
		session.persist(ski);
		session.persist(bodybuilding);
		session.getTransaction().commit();
	}
	
	public static void insertExercises() {
		Session session = DBConnection.getSession();
		session.beginTransaction();
		Exercise yellowClimbingRoute = new Exercise("Voie jaune", "Très facile");
		Exercise orangeClimbingRoute = new Exercise("Voie orange", "Facile");
		Exercise blueClimbingRoute = new Exercise("Voie bleue", "Moyen");
		Exercise redClimbingRoute = new Exercise("Voie rouge", "Difficile");
		Exercise whiteClimbingRoute = new Exercise("Voie blanche", "Très difficile");
		Exercise blackClimbingRoute = new Exercise("Voie noire", "Exertrêmement difficile");
		Exercise greenClimbingRoute = new Exercise("Voie verte", "Impossible");
		Exercise greenTrack = new Exercise("Piste verte", "Piste facile");
		Exercise blueTrack = new Exercise("Piste bleue", "Piste moyenne");
		Exercise redTrack = new Exercise("Piste rouge", "Piste difficile");
		Exercise blackTrack = new Exercise("Piste noire", "Piste très difficile");
		Exercise pushup = new Exercise("Pompes", "série de 20 pompes", AreaOfEffect.PECTORALS);
		Exercise situp = new Exercise("Abdominaux", "série de 20 abdominaux", AreaOfEffect.ABDOMINALS);
		Exercise pullup = new Exercise("Tractions", "série de 20 tractions", AreaOfEffect.ARMS);
		Exercise dips = new Exercise("Dips", "série de 20 dips", AreaOfEffect.BACK);
		Exercise squat = new Exercise("Squats", "série de 20 squat", AreaOfEffect.THIGHS);
		Exercise benchPress = new Exercise("Développés-couchés", "série de 20 développés-couchés", AreaOfEffect.PECTORALS);
		session.persist(yellowClimbingRoute);
		session.persist(orangeClimbingRoute);
		session.persist(blueClimbingRoute);
		session.persist(redClimbingRoute);
		session.persist(whiteClimbingRoute);
		session.persist(blackClimbingRoute);
		session.persist(greenClimbingRoute);
		session.persist(greenTrack);
		session.persist(blueTrack);
		session.persist(redTrack);
		session.persist(blackTrack);
		session.persist(pushup);
		session.persist(situp);
		session.persist(pullup);
		session.persist(dips);
		session.persist(squat);
		session.persist(benchPress);
		session.getTransaction().commit();
	}
}
