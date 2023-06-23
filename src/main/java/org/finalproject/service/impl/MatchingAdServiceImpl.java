package org.finalproject.service.impl;

import org.finalproject.dao.MatchingAdDAO;
import org.finalproject.dao.impl.AuthorDAOImpl;
import org.finalproject.dao.impl.MatchingAdDAOImpl;
import org.finalproject.domain.Announcement;
import org.finalproject.domain.MatchingAd;
import org.finalproject.service.MatchingAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MatchingAdServiceImpl implements MatchingAdService {
    @Autowired
    private MatchingAdDAO dao;

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

    @Override
    public MatchingAd findById(int id) {
        return dao.findById(id);
    }
}
