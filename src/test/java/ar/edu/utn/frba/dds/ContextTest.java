package ar.edu.utn.frba.dds;

import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Test;

public class ContextTest  { //extends AbstractPersistenceTest implements WithGlobalEntityManager {

	@Test
	public void contextUp() {
		/*
			TODO: Aca hay que ver bien que testear, porque ahora estoy probando que levante el contexto de la base H2
			Pero como esta hardcodeado el contexto "db" en alguna libreria, esta explotando por todos lados
		 */
		EntityManagerFactory emf;
		EntityManager em;
		emf = Persistence.createEntityManagerFactory("db-test");
		em = emf.createEntityManager();

		assertNotNull(em);
		em.close();
		emf.close();
	}

	@Test
	public void contextUpWithTransaction() throws Exception {
		EntityManagerFactory emf;
		EntityManager em;
		emf = Persistence.createEntityManagerFactory("db-test");
		em = emf.createEntityManager();

		EntityTransaction et;
		et = em.getTransaction();
		assertNotNull(et);
		em.close();
		emf.close();
	}

}