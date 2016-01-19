package test;
import org.hibernate.Session;
import utils.DataUtility;

public class TestExercisePractice {

	public static void main(String[] args) {
		System.out.println("CREATE TABLE");

		DataInit.createTables();
		System.out.println("DBCONNECTION");

		Session session = DBConnection.getSession();
		System.out.println("BEGIN TRANSACTION");

		//Test persist.
		session.beginTransaction();
		Sport escalade = new Sport("Escalade");
		Practice practice = new Practice(escalade, DataUtility.createDate(11, 06, 2015), "Cergy" , 120);
		session.persist(practice);
		session.getTransaction().commit();

		//Test flush.
//		session.beginTransaction();
//		Practice retrievedPractice = (Practice) session.get(Practice.class, "1");
//		System.out.println(retrievedPractice.toString());
//		retrievedPractice.setLocation("SOA");
//		session.flush();
//		session.getTransaction().commit();
		
		//Test merge.
		session.beginTransaction();
		Practice retrievedPractice = (Practice) session.get(Practice.class, "1");
		System.out.println(retrievedPractice.toString());
		session.getTransaction().commit();
		session.close();
		retrievedPractice.setDate(DataUtility.createDate(21, 01, 2015));
		session = DBConnection.getSession();
		session.beginTransaction();
		session.merge(retrievedPractice);
		session.getTransaction().commit();
		
		
		//Test delete
		/*session.clear();
		session.beginTransaction();*/
//		session.delete(retrievedPerson);
//		session.getTransaction().commit();
	}
}
