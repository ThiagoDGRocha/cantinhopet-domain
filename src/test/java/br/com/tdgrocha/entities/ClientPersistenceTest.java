package br.com.tdgrocha.entities;

import br.com.tdgrocha.repositories.ClientRepository;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;

@QuarkusTest
public class ClientPersistenceTest {

    @Inject
    ClientRepository repository;

    @Test
    @Transactional
    public void testPersistence() throws Exception {
        Client client = new Client(null, "test");
        repository.persist(client);
        Client result = repository.findByName("test");
        System.out.println(result.getId() + " <-> " + result.getNome());
    }

}
