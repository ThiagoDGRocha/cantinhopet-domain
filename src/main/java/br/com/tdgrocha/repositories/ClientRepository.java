package br.com.tdgrocha.repositories;

import br.com.tdgrocha.entities.Client;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@ApplicationScoped
public class ClientRepository {

    private EntityManager em;

    public ClientRepository(EntityManager entityManager) {
        em = entityManager;
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

    public List<Client> findAll() throws Exception {
        try {
            return em.createQuery("FROM " + Client.class.getName()).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    public Client findByName(String name) throws Exception {
        try {
            return em.createQuery("select c from Client c where c.nome = '" + name + "'", Client.class).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }
}
