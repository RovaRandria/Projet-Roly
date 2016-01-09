package test;
import org.hibernate.Session;

//import utils.DataUtility;

public class TestExerciseSport {

	public static void main(String[] args) {
		System.out.println("CREATE TABLE");

		DataInit.createTables();
		System.out.println("DBCONNECTION");

		Session session = DBConnection.getSession();
		System.out.println("BEGIN TRANSACTION");

		//Test persist.
		session.beginTransaction();
//		Exercise exercise = new Exercise("1", "lskdf", "ldvk", "isdhf");
//		session.persist(exercise);
//		session.getTransaction().commit();
		Sport sport = new Sport("Esca");
		session.persist(sport);
		session.getTransaction().commit();

		//Test flush.
//		session.beginTransaction();
//		Sport retrievedSport = (Sport) session.get(Sport.class, "1");
//		System.out.println(retrievedSport.toString());
//		retrievedSport.setName("Escalade");
//		session.flush();
//		session.getTransaction().commit();
		
		//Test merge.
		session.beginTransaction();
		Sport retrievedSport = (Sport) session.get(Sport.class, "1");
		System.out.println(retrievedSport.toString());
		session.getTransaction().commit();
		session.close();
		retrievedSport.setName("Escalade");
		session = DBConnection.getSession();
		session.beginTransaction();
		session.merge(retrievedSport);
		session.getTransaction().commit();
		
		
		//Test delete
		/*session.clear();
		session.beginTransaction();*/
//		session.delete(retrievedPerson);
//		session.getTransaction().commit();
	}
}
