package org.finalproject.dao.impl;

import org.finalproject.dao.MatchingAdDAO;
import org.finalproject.domain.Announcement;
import org.finalproject.domain.MatchingAd;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

public class MatchingAdDAOImpl implements MatchingAdDAO {

    @Override
    public List<MatchingAd> filter(Announcement announcement) {
        List<MatchingAd> allMatchingAds = findAll();
        BigDecimal price = announcement.getPrice();
        return allMatchingAds.stream().filter(matchingAd ->
                price.compareTo(matchingAd.getPriceFrom()) >= 0
                        && price.compareTo(matchingAd.getPriceTo()) <= 0
                            && announcement.getRubric().getName().equals(matchingAd.getRubric().getName())).toList();
    }

    @Override
    public int sendMassages(List<MatchingAd> matchingAds, Announcement announcement) {
        int countOfMessage = 0;
        for (MatchingAd match: matchingAds) {
            System.out.println("Hi, " + match.getAuthor().getName() +
                    "! I have a new purchase for you by your subscription on " + match.getRubric().getName() + " rubric. " +
                    " So, my purchase is " + announcement.getName().toUpperCase() +
                    "\n" + announcement.getText() +
                    " \n With price " + announcement.getPrice() +
                    " and author " + announcement.getAuthor().getName() + " with e-mail: "
                            + announcement.getAuthor().getEmail().getEmail() +
                    "\n We are waiting for you on our website!");
            countOfMessage++;
        }
    return countOfMessage;
    }

    @Override
    public void save(MatchingAd entity) {
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(entity);
        transaction.commit();
    }

    @Override
    public void update(MatchingAd entity) {
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        MatchingAd matchingAd = entityManager.merge(entity);
        entityManager.persist(matchingAd);
        transaction.commit();
    }


    @Override
    public void deleteById(int id) {
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query = entityManager.
                createQuery("DELETE MatchingAd ma WHERE ma.id = :ma_id");
        query.setParameter("ma_id", id);
        query.executeUpdate();
        transaction.commit();
    }

    @Override
    public List<MatchingAd> findAll() {
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        TypedQuery<MatchingAd> query = entityManager.createQuery("FROM MatchingAd", MatchingAd.class);
        List<MatchingAd> matchingAds = query.getResultList();
        transaction.commit();
        return matchingAds;
    }

    @Override
    public MatchingAd findById(int id) {
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        MatchingAd matchingAd = entityManager.find(MatchingAd.class, id);
        transaction.commit();
        return matchingAd;
    }
}
