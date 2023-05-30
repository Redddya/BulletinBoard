package org.finalproject.dao.impl;

import org.finalproject.dao.AnnouncementDAO;
import org.finalproject.dao.MatchingAdDAO;
import org.finalproject.domain.Announcement;
import org.finalproject.domain.MatchingAd;
import org.finalproject.service.MatchingAdService;
import org.finalproject.service.impl.MatchingAdServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;

public class AnnouncementDAOImpl implements AnnouncementDAO {
    private MatchingAdService service = new MatchingAdServiceImpl();
    @Override
    public void save(Announcement announcement) {
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(announcement);
        transaction.commit();
        List<MatchingAd> matchingAds = service.filter(announcement);
        service.sendMassages(matchingAds, announcement);
    }

    @Override
    public void update(Announcement announcement) {
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Announcement announcement1 = entityManager.merge(announcement);
        entityManager.persist(announcement1);
        transaction.commit();
    }

    @Override
    public void deleteById(int id) {
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query = entityManager.
                createQuery("DELETE Announcement a WHERE a.id = :an_id");
        query.setParameter("an_id", id);
        transaction.commit();
    }

    @Override
    public List<Announcement> findAll() {
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        TypedQuery<Announcement> query = entityManager.createQuery("FROM Announcement", Announcement.class);
        List<Announcement> announcements = query.getResultList();
        transaction.commit();
        return announcements;
    }

    @Override
    public Announcement findById(int id) {
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Announcement announcement = entityManager.find(Announcement.class, id);
        transaction.commit();
        return announcement;
    }

    @Override
    public void deleteAllByAuthorId(int id) {
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query = entityManager.createQuery("DELETE Announcement a WHERE " +
                " a.author.id = :au_id");
        query.setParameter("au_id", id);
        query.executeUpdate();
        transaction.commit();
    }

    @Override
    public void deleteAllByRubricId(int id) {
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query = entityManager.createQuery("DELETE Announcement a WHERE " +
                " a.rubric.id = :ru_id");
        query.setParameter("ru_id", id);
        query.executeUpdate();
        transaction.commit();
    }

    @Override
    public List<Announcement> showByRubricId(int id) {
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        TypedQuery<Announcement> query = entityManager
                .createQuery("FROM Announcement a WHERE a.rubric.id = :ru_id", Announcement.class)
                .setParameter("ru_id", id);
        List<Announcement> resultList = query.getResultList();
        transaction.commit();
        return resultList;
    }

    @Override
    public List<Announcement> showByAuthorId(int id) {
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        TypedQuery<Announcement> query = entityManager
                .createQuery("FROM Announcement a WHERE a.author.id = :au_id", Announcement.class);
        query.setParameter("au_id", id);
        List<Announcement> resultList = query.getResultList();
        transaction.commit();
        return resultList;
    }

    @Override
    public List<Announcement> showByKeyWord(String keyWord) {
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        TypedQuery<Announcement> query = entityManager
                .createQuery("FROM Announcement a WHERE announcement_text LIKE CONCAT('%', :key_w, '%')",
                        Announcement.class);
        query.setParameter("key_w", keyWord);
        List<Announcement> resultList = query.getResultList();
        transaction.commit();
        return resultList;
    }

    @Override
    public List<Announcement> showByDate(LocalDateTime date) {
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        TypedQuery<Announcement> query = entityManager
                .createQuery("FROM Announcement WHERE publication_date = :an_date", Announcement.class);
        query.setParameter("an_date", date);
        List<Announcement> resultList = query.getResultList();
        transaction.commit();
        return resultList;
    }
}
