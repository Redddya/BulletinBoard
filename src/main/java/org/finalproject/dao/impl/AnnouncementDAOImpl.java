package org.finalproject.dao.impl;

import org.finalproject.dao.AnnouncementDAO;
import org.finalproject.domain.Announcement;
import org.finalproject.domain.MatchingAd;
import org.finalproject.service.MatchingAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional
public class AnnouncementDAOImpl implements AnnouncementDAO {

    @PersistenceContext
    private EntityManager em;


    @Override
    public void save(Announcement announcement) {
        em.persist(announcement);
    }

    @Override
    public void update(Announcement announcement) {
        Announcement announcement1 = em.merge(announcement);
        em.persist(announcement1);
    }

    @Override
    public void deleteById(int id) {
        Query query = em.
                createQuery("DELETE Announcement a WHERE a.id = :an_id");
        query.setParameter("an_id", id);
        query.executeUpdate();
    }

    @Override
    public List<Announcement> findAll() {
        TypedQuery<Announcement> query = em.createQuery("FROM Announcement", Announcement.class);
        return query.getResultList();
    }

    @Override
    public Announcement findById(int id) {
        return em.find(Announcement.class, id);
    }

    @Override
    public void deleteAllByAuthorId(int id) {
        Query query = em.createQuery("DELETE Announcement a WHERE " +
                " a.author.id = :au_id");
        query.setParameter("au_id", id);
        query.executeUpdate();
    }

    @Override
    public void deleteAllByRubricId(int id) {
        Query query = em.createQuery("DELETE Announcement a WHERE " +
                " a.rubric.id = :ru_id");
        query.setParameter("ru_id", id);
        query.executeUpdate();
    }

    @Override
    public List<Announcement> showByRubricId(int id) {
        TypedQuery<Announcement> query = em
                .createQuery("FROM Announcement a WHERE a.rubric.id = :ru_id", Announcement.class)
                .setParameter("ru_id", id);
        return query.getResultList();
    }

    @Override
    public List<Announcement> showByAuthorId(int id) {
        TypedQuery<Announcement> query = em
                .createQuery("FROM Announcement a WHERE a.author.id = :au_id", Announcement.class);
        query.setParameter("au_id", id);
        return  query.getResultList();
    }

    @Override
    public List<Announcement> showByDate(LocalDateTime date) {
        TypedQuery<Announcement> query = em
                .createQuery("FROM Announcement WHERE publication_date = :an_date", Announcement.class);
        query.setParameter("an_date", date);
        return query.getResultList();
    }

    @Override
    public List<Announcement> showByKeyWord(String keyWord) {
        TypedQuery<Announcement> query = em
                .createQuery("FROM Announcement a WHERE announcement_text LIKE CONCAT('%', :key_w, '%')",
                        Announcement.class);
        query.setParameter("key_w", keyWord);
        return query.getResultList();
    }
}
