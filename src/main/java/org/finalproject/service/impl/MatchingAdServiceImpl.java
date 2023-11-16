package org.finalproject.service.impl;

import org.finalproject.dao.MatchingAdDAO;
import org.finalproject.domain.Announcement;
import org.finalproject.domain.MatchingAd;
import org.finalproject.service.MatchingAdService;
import org.finalproject.util.exception.custom.MatchingAdException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchingAdServiceImpl implements MatchingAdService {
    private final MatchingAdDAO matchingAdDAO;

    public MatchingAdServiceImpl(MatchingAdDAO matchingAdDAO) {
        this.matchingAdDAO = matchingAdDAO;
    }

    @Override
    public void sendMassagesByMatchingAds(Announcement announcement) {
        sendMassages(matchingAdDAO.filter(announcement), announcement);
    }

    private int sendMassages(List<MatchingAd> matchingAds, Announcement announcement) {
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
        matchingAdDAO.save(entity);
    }

    @Override
    public void update(MatchingAd entity) {
        matchingAdDAO.save(entity);
    }

    @Override
    public void deleteById(int id) {
        matchingAdDAO.deleteById(id);
    }

    @Override
    public List findAll() {
        List<MatchingAd> matchingAds = matchingAdDAO.findAll();
        if (matchingAds.isEmpty())
            throw new MatchingAdException("MatchingAds wasn't found");
        return matchingAds;
    }

    @Override
    public MatchingAd findById(int id) {
        Optional<MatchingAd> foundMatchingAd = matchingAdDAO.findById(id);
        if(foundMatchingAd.isEmpty())
            throw new MatchingAdException("MatchingAds with this id wasn't found");
        return foundMatchingAd.get();
    }
}
