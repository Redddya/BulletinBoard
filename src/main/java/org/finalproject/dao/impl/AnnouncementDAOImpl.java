package org.finalproject.dao.impl;

import org.finalproject.dao.AnnouncementDAO;
import org.finalproject.domain.Announcement;
import org.finalproject.repository.AnnouncementRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class AnnouncementDAOImpl implements AnnouncementDAO {
    final
    AnnouncementRepository announcementRepository;

    public AnnouncementDAOImpl(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    @Override
    public void save(Announcement announcement) {
        announcementRepository.save(announcement);
    }

    @Override
    public void update(Announcement announcement) {
      announcementRepository.save(announcement);
    }

    @Override
    public void deleteById(int id) {
      announcementRepository.deleteById(id);
    }

    @Override
    public List<Announcement> findAll() {
      return announcementRepository.findAll();
    }

    @Override
    public Optional<Announcement> findById(int id) {
       return announcementRepository.findById(id);
    }

    @Override
    public void deleteAllByAuthorId(int id) {
        announcementRepository.deleteAllByAuthorId(id);
    }

    @Override
    public void deleteAllByRubricId(int id) {
      announcementRepository.deleteAllByRubricId(id);
    }

    @Override
    public List<Announcement> showByRubricId(int id) {
        return announcementRepository.showByRubricId(id);
    }

    @Override
    public List<Announcement> showByAuthorId(int id) {
       return announcementRepository.showByAuthorId(id);
    }

    @Override
    public List<Announcement> showByDate(LocalDateTime date) {
        return announcementRepository.showByPublicationDate(date);
    }

    @Override
    public void deleteInactive() {
        announcementRepository.deleteInactive();
    }

    @Override
    public List<Announcement> showByKeyword(String keyWord) {
        return announcementRepository.showByKeyword(keyWord);
    }
}
