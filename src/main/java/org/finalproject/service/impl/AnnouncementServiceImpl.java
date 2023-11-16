package org.finalproject.service.impl;

import org.finalproject.dao.AnnouncementDAO;
import org.finalproject.domain.Announcement;
import org.finalproject.service.AnnouncementService;
import org.finalproject.service.MatchingAdService;
import org.finalproject.util.exception.custom.AnnouncementException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    private final AnnouncementDAO announcementDAO;
    private final MatchingAdService matchingAdService;

    public AnnouncementServiceImpl(AnnouncementDAO announcementDAO, MatchingAdService matchingAdService) {
        this.announcementDAO = announcementDAO;

        this.matchingAdService = matchingAdService;
    }

    public Announcement findById(int id) {
        Optional<Announcement> foundAnnouncement = announcementDAO.findById(id);
        if (foundAnnouncement.isEmpty())
            throw new AnnouncementException("Announcements with this id wasn't found");
        return foundAnnouncement.get();
    }

    @Override
    public void save(Announcement announcement) {
        announcement.setPublicationDate(LocalDateTime.now());
        announcementDAO.save(announcement);
        if (announcement.isActive())
            matchingAdService.sendMassagesByMatchingAds(announcement);
    }

    @Override
    public void update(Announcement announcement) {
        announcementDAO.save(announcement);
        if (announcement.isActive())
            matchingAdService.sendMassagesByMatchingAds(announcement);
    }

    @Override
    public void deleteAllByAuthorId(int id) {
        announcementDAO.deleteAllByAuthorId(id);
    }

    @Override
    public void deleteAllByRubricId(int id) {
        announcementDAO.deleteAllByRubricId(id);
    }

    @Override
    public List<Announcement> showByRubricId(int id) {
        List<Announcement> announcements = announcementDAO.showByRubricId(id);
        if (announcements.isEmpty())
            throw new AnnouncementException("Announcements with this rubric id wasn't found");
        return announcements;
    }

    @Override
    public List<Announcement> showByAuthorId(int id) {

        List<Announcement> announcements = announcementDAO.showByAuthorId(id);
        if (announcements.isEmpty())
            throw new AnnouncementException("Announcements with this author id wasn't found");
        return announcements;
    }

    @Override
    public List<Announcement> showByKeyword(String keyword) {
        List<Announcement> announcements = announcementDAO.showByKeyword(keyword);
        if (announcements.isEmpty())
            throw new AnnouncementException("Announcements with this key word wasn't found");
        return announcements;
    }

    @Override
    public List<Announcement> showByDate(LocalDateTime date) {
        List<Announcement> announcements = announcementDAO.showByDate(date);
        if (announcements.isEmpty())
            throw new AnnouncementException("Announcements with this date wasn't found");
        return announcements;
    }

    @Override
    public void deleteById(int id) {
        announcementDAO.deleteById(id);
    }

    @Override
    public List<Announcement> findAll() {

        List<Announcement> announcements = announcementDAO.findAll();
        if (announcements.isEmpty())
            throw new AnnouncementException("Any announcements wasn't found");
        return announcements;
    }

    @Scheduled(cron = "0 0 18 * * *")
    public void deleteInActiveAds() {
        announcementDAO.deleteInactive();
    }
}
