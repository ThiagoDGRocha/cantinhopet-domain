package br.com.tdgrocha.repositories;

import br.com.tdgrocha.entities.Client;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@ApplicationScoped
public class ClientRepository {

    protected EntityManager em;
    private static ClientRepository instance;

    public ClientRepository() {
        em = getEntityManager();
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("crudHibernatePU");

        if (em == null)
            em = factory.createEntityManager();

        return em;
    }

    public static ClientRepository getInstance() {
        if (instance == null)
            instance = new ClientRepository();

        return instance;
    }

    public List<Client> findAll() throws Exception {
        try {
            return em.createQuery("FROM " + Client.class.getName()).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    public void persist(Client entity) throws Exception {
        try {
            em.persist(entity);
            em.flush();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            throw new Exception(e.getMessage());
        }
    }

    public Client findByName(String test) throws Exception {
        try {
            return em.createQuery("select c from Client c where c.nome = 'test'", Client.class).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }
}
