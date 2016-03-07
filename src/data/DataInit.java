package data;

import gui.MainGUI;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import utils.DataUtility;
/**
 * This class is used to initialize the database with the data classes mentioned in the class "DBConnection"
 * @author Rova
 *
 */
public class DataInit {

	/**
	 * Create the tables of the database
	 */
	public static void createTables() {
		AnnotationConfiguration config = DBConnection.getConfig();
		SchemaExport schemaExport = new SchemaExport(config);
		schemaExport.create(true, true);
	}
	
	/**
	 * Insert the sport climbing, jogging, cycling, ski, in the database
	 */
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
	
	/**
	 * Insert the different exercises in the database
	 */
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
	
	public static void insertTestValues() {
		Session session = DBConnection.getSession();
		session.beginTransaction();
		User user = new User("py", "misopogon");
		Profile profile = new Profile("", "", DataUtility.createDate(5, 2, 2016),1);
		user.setProfile(profile);
		user.getProfile().setLastName("Pierre");
		user.getProfile().setFirstName("Yvain");
		user.getProfile().setGender(Gender.Homme);
		user.getProfile().setBirthdate(DataUtility.createDate(21, 8, 1981));
		Date measureDate = DataUtility.createDate(10, 2, 2016);
		PhysicalData pd = new PhysicalData(measureDate, 60, 40, 30);
		user.getProfile().getPhysicalDataList().add(pd);
		measureDate = DataUtility.createDate(27, 2, 2016);
		pd = new PhysicalData(measureDate, 58, 39, 38);
		user.getProfile().getPhysicalDataList().add(pd);
		measureDate = DataUtility.createDate(1, 3, 2016);
		pd = new PhysicalData(measureDate, 58, 39, 38);
		user.getProfile().getPhysicalDataList().add(pd);
		measureDate = DataUtility.createDate(3, 3, 2016);
		pd = new PhysicalData(measureDate, 58, 39, 38);
		user.getProfile().getPhysicalDataList().add(pd);
		measureDate = DataUtility.createDate(6, 3, 2016);
		pd = new PhysicalData(measureDate, 60, 40, 41);
		user.getProfile().getPhysicalDataList().add(pd);
		Sport ski = (Sport) session.get(Sport.class, "Ski");
		Sport cycling = (Sport) session.get(Sport.class, "Vélo");
		user.getProfile().getSportsList().add(ski);
		user.getProfile().getSportsList().add(cycling);
		Date date = DataUtility.createDate(27, 2, 2016);
		Practice practice = new Practice(cycling, date, "Cergy", 300.f, 250.f, user.getProfile());
		user.getProfile().getPracticesList().add(practice);
		date = DataUtility.createDate(27, 2, 2016);
		practice = new Practice(cycling, date, "Pontoise", 300.f, 250.f, user.getProfile());
		user.getProfile().getPracticesList().add(practice);
		date = DataUtility.createDate(1, 3, 2016);
		practice = new Practice(cycling, date, "Cergy", 200.f, 180.f, user.getProfile());
		user.getProfile().getPracticesList().add(practice);
		date = DataUtility.createDate(4, 3, 2016);
		practice = new Practice(cycling, date, "Pontoise", 250.f, 220.f, user.getProfile());
		user.getProfile().getPracticesList().add(practice);
		date = DataUtility.createDate(7, 2, 2016);
		practice = new Practice(cycling, date, "Pontoise", 305.f, 300.f, user.getProfile());
		user.getProfile().getPracticesList().add(practice);
		date = DataUtility.createDate(1, 3, 2016);
		List<Exercise> exercisesList = new ArrayList<Exercise>();
		exercisesList.add(new Exercise("Piste rouge", 1));
		exercisesList.add(new Exercise("Piste bleue", 1));
		exercisesList.add(new Exercise("Piste verte", 3));
		practice = new Practice(ski, date, "Annecy", 300.f, exercisesList, user.getProfile());
		user.getProfile().getPracticesList().add(practice);
		date = DataUtility.createDate(4, 3, 2016);
		exercisesList = new ArrayList<Exercise>();
		exercisesList.add(new Exercise("Piste bleue", 1));
		exercisesList.add(new Exercise("Piste noire", 1));
		practice = new Practice(ski, date, "Annecy", 300.f, exercisesList, user.getProfile());
		user.getProfile().getPracticesList().add(practice);		
		session.persist(user);
		session.getTransaction().commit();
	}
	
	public static void main(String[] args) {
		DataInit.createTables();
		DataInit.insertSports();
		DataInit.insertExercises();
		DataInit.insertTestValues();
		new MainGUI("Pass'Sport");
	}
}
