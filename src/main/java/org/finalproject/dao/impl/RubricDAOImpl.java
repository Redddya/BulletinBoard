package org.finalproject.dao.impl;

import org.finalproject.dao.AnnouncementDAO;
import org.finalproject.dao.CRUDDao;
import org.finalproject.domain.Rubric;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class RubricDAOImpl implements CRUDDao<Rubric> {

    AnnouncementDAO dao = new AnnouncementDAOImpl();

    @Override
    public void save(Rubric rubric) {
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(rubric);
        transaction.commit();
    }

    @Override
    public void update(Rubric rubric) {
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Rubric rubric1 = entityManager.merge(rubric);

        entityManager.persist(rubric1);

        transaction.commit();
    }

    @Override
    public void deleteById(int id) {
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        dao.deleteAllByRubricId(id);
        Query query = entityManager.createQuery("DELETE Rubric r WHERE r.id = :ru_id");
        query.setParameter("ru_id", id);
        query.executeUpdate();
        transaction.commit();

    }

    @Override
    public List<Rubric> findAll() {
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        TypedQuery<Rubric> query = entityManager.createQuery("From Rubric r", Rubric.class);
        List<Rubric> rubrics = query.getResultList();
        transaction.commit();
        return rubrics;
    }

    @Override
    public Rubric findById(int id) {
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Rubric rubric = entityManager.find(Rubric.class, id);
        transaction.commit();
        return rubric;
    }
}
