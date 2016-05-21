package hu.popcornmaci.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EFMManager {
	private static EntityManager em;
	private static EntityManagerFactory emf;

	private EFMManager() {

	}

	public static EntityManagerFactory getFactory() {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory("JPA");
		}
		return emf;
	}

	public static EntityManager getManager() {
		if (em == null) {
			em = getFactory().createEntityManager();
		}
		return em;
	}

	public static void close() {
		if (em != null) {
			em.close();
		}
		if (emf != null) {
			emf.close();
		}
	}
}
