package org.finalproject.dao.impl;

import org.finalproject.dao.MatchingAdDAO;
import org.finalproject.domain.Announcement;
import org.finalproject.domain.MatchingAd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Transactional
@Repository
public class MatchingAdDAOImpl implements MatchingAdDAO {
    @PersistenceContext
    private EntityManager em;
    @Override
    public List<MatchingAd> filter(Announcement announcement) {
        TypedQuery<MatchingAd> query = em.
                createQuery(
                        "SELECT ma " +
                                "FROM MatchingAd ma " +
                                "WHERE :an_pr BETWEEN ma.priceFrom AND ma.priceTo AND " +
                                ":an_name LIKE CONCAT('%', ma.title, '%')", MatchingAd.class
        );
        query.setParameter("an_pr", announcement.getPrice());
        query.setParameter("an_name", announcement.getName());
        return query.getResultList();
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
        em.persist(entity);
    }

    @Override
    public void update(MatchingAd entity) {
        MatchingAd matchingAd = em.merge(entity);
        em.persist(matchingAd);
        }


    @Override
    public void deleteById(int id) {
        Query query = em.
                createQuery("DELETE MatchingAd ma WHERE ma.id = :ma_id");
        query.setParameter("ma_id", id);
        query.executeUpdate();
    }

    @Override
    public List<MatchingAd> findAll() {
        TypedQuery<MatchingAd> query = em.createQuery("FROM MatchingAd", MatchingAd.class);
        return query.getResultList();
    }

    @Override
    public MatchingAd findById(int id) {
        return em.find(MatchingAd.class, id);
    }
}
