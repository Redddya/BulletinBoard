package org.finalproject.dao.impl;

import org.finalproject.dao.CRUDDao;
import org.finalproject.domain.Adress;
import org.finalproject.domain.Announcement;
import org.finalproject.domain.Rubric;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class AdressDAOImpl implements CRUDDao<Adress> {
    @Override
    public void save(Adress entity) {//id == 0
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(entity);
        transaction.commit();
    }

    @Override
    public void update(Adress adress) {//id != 0
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query = entityManager.createQuery(
                "UPDATE Adress ad " +
                        "SET ad.country = :ad_country, " +
                        "ad.city = :ad_city, " +
                        "ad.street = :ad_street, " +
                        "ad.house = :ad_house");
        query.setParameter("ad_country", adress.getCountry());
        query.setParameter("ad_city", adress.getCity());
        query.setParameter("ad_street", adress.getStreet());
        query.setParameter("ad_house", adress.getHouse());
        query.executeUpdate();
        transaction.commit();
    }

    @Override
    public void deleteById(int id) {
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query = entityManager.createQuery("DELETE Adress a WHERE a.id = :ad_id");
        query.setParameter("ad_id", id);
        transaction.commit();
    }

    @Override
    public List<Adress> findAll() {
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        TypedQuery<Adress> query = entityManager.createQuery("FROM Rubric r", Adress.class);
        List<Adress> adresses = query.getResultList();
        transaction.commit();
        return adresses;
    }

    @Override
    public Adress findById(int id) {
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Adress adress = entityManager.find(Adress.class, id);
        transaction.commit();
        return adress;
    }
}
