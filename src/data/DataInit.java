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
		String climbingDescription = "L'escalade, parfois appelée varappeNote 1 ou grimpe, est une pratique et un sport consistant à se déplacer le long d'une paroi pour atteindre le haut d'un relief ou d'une structure artificielle, par un cheminement appelé voie et avec ou sans aide de matériel. Le terrain de jeu du grimpeur va des blocs de faible hauteur aux parois de plusieurs centaines de mètres en passant par les murs d'escalade. Physiquement, l'escalade est un sport complet sollicitant aussi bien les mains et les bras que les jambes et le tronc ainsi que des aptitudes mentales importantes.";
		String joggingDescription = "Le jogging ou footing (faux anglicisme) est une activité physique consistant à courir à pied une certaine distance à un rythme moyennement soutenu. Le terme jogging est repris de l'anglais, où il désigne le fait de courir à moins de 10 km/h. Le terme joggeur désigne le pratiquant de cette activité.\nLe jogging peut être un exercice physique de loisir, pratiqué dans un but d'amélioration ou entretien de la condition physique (fitness), de l'hygiène de vie et du bien-être. C'est ainsi une activité très populaire, notamment en Allemagne et aux États-Unis.\nLes sportifs réguliers et les athlètes pratiquent le jogging comme exercice d'échauffement, comme entraînement à l'endurance ou comme récupération.";
		Sport climbing = new Sport("Escalade", climbingDescription);
		Sport jogging = new Sport("Jogging", joggingDescription);
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
