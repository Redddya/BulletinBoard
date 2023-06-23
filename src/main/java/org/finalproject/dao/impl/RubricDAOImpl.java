package org.finalproject.dao.impl;

import org.finalproject.dao.AnnouncementDAO;
import org.finalproject.dao.CRUDDao;
import org.finalproject.domain.Rubric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Repository
@Transactional
public class RubricDAOImpl implements CRUDDao<Rubric> {

    @PersistenceContext
    private EntityManager em;

    //@PersistenceUnit
    //private EntityManagerFactory factory;

    @Autowired
    private AnnouncementDAO dao;

    @Override
    public void save(Rubric rubric) {
        em.persist(rubric);
    }

    @Override
    public void update(Rubric rubric) {

        Rubric rubric1 = em.merge(rubric);

        em.persist(rubric1);
    }

    @Override
    public void deleteById(int id) {
        dao.deleteAllByRubricId(id);
        Query query = em.createQuery("DELETE Rubric r WHERE r.id = :ru_id");
        query.setParameter("ru_id", id);
        query.executeUpdate();
    }

    @Override
    public List<Rubric> findAll() {

        TypedQuery<Rubric> query = em.createQuery("From Rubric r", Rubric.class);
        return query.getResultList();

    }

    @Override
    public Rubric findById(int id) {
        return em.find(Rubric.class, id);
    }
}
