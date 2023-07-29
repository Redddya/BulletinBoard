package org.finalproject.service.impl;

import org.finalproject.dao.MatchingAdDAO;
import org.finalproject.domain.Announcement;
import org.finalproject.domain.MatchingAd;
import org.finalproject.service.MatchingAdService;
import org.finalproject.util.exception.custom.MatchingAdException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MatchingAdServiceImpl implements MatchingAdService {
    private final MatchingAdDAO dao;

    public MatchingAdServiceImpl(MatchingAdDAO dao) {
        this.dao = dao;
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
        List<MatchingAd> matchingAds = dao.findAll();
        if (matchingAds.isEmpty())
            throw new MatchingAdException("MatchingAds wasn't found");
        return matchingAds;
    }

    @Override
    public MatchingAd findById(int id) {
        MatchingAd matchingAd = dao.findById(id);
        if(matchingAd == null)
            throw new MatchingAdException("MatchingAds with this id wasn't found");
        return matchingAd;
    }
}
