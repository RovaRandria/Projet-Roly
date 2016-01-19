package test;

import org.hibernate.Session;

import utils.DataUtility;

public class TestProfile {

	public static void main(String[] args) {
		DataInit.createTables();

		Session session = DBConnection.getSession();
		//Test persist.
		session.beginTransaction();
		Profile profile = new Profile("Dupont", "Paul", DataUtility.createDate(1, 6, 2015), Gender.MALE, DataUtility.createDate(1, 6, 1986), 75);
		session.persist(profile);
		session.getTransaction().commit();

		//Test flush.
		session.beginTransaction();
		Profile retrievedProfile = (Profile) session.get(Profile.class, "1");
		System.out.println(retrievedProfile.toString());
		retrievedProfile.setFirstName("Luc");
		session.flush();
		session.getTransaction().commit();
		
		//Test merge.
		session.beginTransaction();
		Profile retrievedProfile2 = (Profile) session.get(Profile.class, "1");
		System.out.println(retrievedProfile.toString());
		session.getTransaction().commit();
		session.close();
		retrievedProfile2.setFirstName("Thierry");
		session = DBConnection.getSession();
		session.beginTransaction();
		session.merge(retrievedProfile2);
		session.getTransaction().commit();
		
		/*
		//Test delete
		session.clear();
		session.beginTransaction();
		session.delete(retrievedProfile);
		session.getTransaction().commit();
		*/
	}
}
