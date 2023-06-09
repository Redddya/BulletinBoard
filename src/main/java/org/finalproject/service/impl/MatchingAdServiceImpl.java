package org.finalproject.service.impl;

import org.finalproject.dao.MatchingAdDAO;
import org.finalproject.dao.impl.AuthorDAOImpl;
import org.finalproject.dao.impl.MatchingAdDAOImpl;
import org.finalproject.domain.Announcement;
import org.finalproject.domain.MatchingAd;
import org.finalproject.service.MatchingAdService;

import java.util.List;

public class MatchingAdServiceImpl implements MatchingAdService {

    private MatchingAdDAO dao;
    public MatchingAdServiceImpl() {
        dao = new MatchingAdDAOImpl();
    }

    @Override
    public List<MatchingAd> filter(Announcement announcement) {
        return dao.filter(announcement);
    }

    @Override
    public int sendMassages(List<MatchingAd> matchingAds, Announcement announcement) {
        return dao.sendMassages(matchingAds, announcement);
    }

    @Override
    public void save(MatchingAd entity) {
        dao.save(entity);
    }

    @Override
    public void update(MatchingAd entity) {
        dao.update(entity);
    }

    @Override
    public void deleteById(int id) {
        dao.deleteById(id);
    }

    @Override
    public List findAll() {
        return dao.findAll();
    }
}
