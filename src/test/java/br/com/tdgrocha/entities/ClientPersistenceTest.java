package br.com.tdgrocha.entities;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@QuarkusTest
public class ClientPersistenceTest {

    @Inject
    EntityManager em;

    @Test
    @Transactional
    public void testPersistence() throws Exception {
        Client client = new Client(null, "test");
        em.persist(client);
        Client result = em.createQuery("select c from Client c where c.nome = 'test'", Client.class).getSingleResult();
        System.out.println(result.getId() + " <-> " + result.getNome());
    }

}
