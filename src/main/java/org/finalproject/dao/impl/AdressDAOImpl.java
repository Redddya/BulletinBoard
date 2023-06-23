package org.finalproject.dao.impl;

import org.finalproject.dao.CRUDDao;
import org.finalproject.domain.Adress;
import org.finalproject.domain.Announcement;
import org.finalproject.domain.Rubric;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;
@Transactional
@Repository
public class AdressDAOImpl implements CRUDDao<Adress> {
 @PersistenceContext
 private EntityManager em;

    @Override
    public void save(Adress entity) {//id == 0
        em.persist(entity);
    }

    @Override
    public void update(Adress adress) {//id != 0
        Adress adress1 = em.merge(adress);
        em.persist(adress1);
        }

    @Override
    public void deleteById(int id) {
        Query query = em.createQuery("DELETE Adress a WHERE a.id = :ad_id");
        query.setParameter("ad_id", id);
    }

    @Override
    public List<Adress> findAll() {
        TypedQuery<Adress> query = em.createQuery("FROM Rubric r", Adress.class);
        return query.getResultList();
    }

    @Override
    public Adress findById(int id) {
        return em.find(Adress.class, id);
    }
}
