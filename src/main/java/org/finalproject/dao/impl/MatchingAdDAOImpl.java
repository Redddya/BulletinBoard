package org.finalproject.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.finalproject.dao.MatchingAdDAO;
import org.finalproject.domain.Announcement;
import org.finalproject.domain.MatchingAd;
import org.finalproject.repository.MatchingAdRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class MatchingAdDAOImpl implements MatchingAdDAO {
    private final MatchingAdRepository matchingAdRepository;
    @PersistenceContext
    private EntityManager em;

    public MatchingAdDAOImpl(MatchingAdRepository matchingAdRepository) {
        this.matchingAdRepository = matchingAdRepository;
    }

    @Override
    public List<MatchingAd> filter(Announcement announcement) {
        return matchingAdRepository.findAllByAdvertisementParams(announcement.getName(), announcement.getPrice());
    }

    @Override
    public void save(MatchingAd entity) {
        matchingAdRepository.save(entity);
    }

    @Override
    public void update(MatchingAd entity) {
        matchingAdRepository.save(entity);
    }

    @Override
    public void deleteById(int id) {
        matchingAdRepository.deleteById(id);
    }

    @Override
    public List<MatchingAd> findAll() {
        return matchingAdRepository.findAll();
    }

    @Override
    public Optional<MatchingAd> findById(int id) {
        return matchingAdRepository.findById(id);
    }
}
