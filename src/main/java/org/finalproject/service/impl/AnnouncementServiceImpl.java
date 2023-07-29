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

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    private final AnnouncementDAO dao;
    private final MatchingAdService matchingAdService;

    public AnnouncementServiceImpl(AnnouncementDAO dao, MatchingAdService matchingAdService) {
        this.dao = dao;
        this.matchingAdService = matchingAdService;
    }

    public Announcement findById(int id){
        Announcement announcement = dao.findById(id);
        if(announcement == null)
            throw new AnnouncementException("Announcements with this id wasn't found");
        return announcement;
    }

    @Override
    public void save(Announcement announcement) {
       announcement.setPublicationDate(LocalDateTime.now());
        dao.save(announcement);
        if(announcement.isActive())
            matchingAdService.sendMassages(matchingAdService.filter(announcement), announcement);
    }

    @Override
    public void update(Announcement announcement) {
        dao.update(announcement);
    }

    @Override
    public void deleteAllByAuthorId(int id) {
        dao.deleteAllByAuthorId(id);
    }

    @Override
    public void deleteAllByRubricId(int id) {
        dao.deleteAllByRubricId(id);
    }

    @Override
    public List<Announcement> showByRubricId(int id) {
        List<Announcement> announcements = dao.showByRubricId(id);
        if(announcements.isEmpty())
                throw new AnnouncementException("Announcements with this rubric id wasn't found");
        return announcements;
    }

    @Override
    public List<Announcement> showByAuthorId(int id) {

        List<Announcement> announcements = dao.showByAuthorId(id);
        if(announcements.isEmpty())
            throw new AnnouncementException("Announcements with this author id wasn't found");
        return announcements;
    }

    @Override
    public List<Announcement> showByKeyWord(String keyWord) {
        List<Announcement> announcements = dao.showByKeyWord(keyWord);
        if(announcements.isEmpty())
            throw new AnnouncementException("Announcements with this key word wasn't found");
        return announcements;
    }

    @Override
    public List<Announcement> showByDate(LocalDateTime date) {
        List<Announcement> announcements = dao.showByDate(date);
        if(announcements.isEmpty())
            throw new AnnouncementException("Announcements with this date wasn't found");
        return announcements;
    }

    @Override
    public void deleteById(int id) {
        dao.deleteById(id);
    }

    @Override
    public List<Announcement> findAll() {

        List<Announcement> announcements = dao.findAll();
        if(announcements.isEmpty())
            throw new AnnouncementException("Any announcements wasn't found");
        return announcements;
    }

    @Scheduled(cron = "0 0 18 * * *")
    public void deleteInActiveAds() {
        //TODO
    }
}
