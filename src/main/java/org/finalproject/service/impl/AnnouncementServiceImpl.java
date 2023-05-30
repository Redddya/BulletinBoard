package org.finalproject.service.impl;

import org.finalproject.dao.AnnouncementDAO;
import org.finalproject.dao.CRUDDao;
import org.finalproject.dao.impl.AnnouncementDAOImpl;
import org.finalproject.domain.Announcement;
import org.finalproject.service.AnnouncementService;

import java.time.LocalDateTime;
import java.util.List;

public class AnnouncementServiceImpl implements AnnouncementService {
    private AnnouncementDAO dao;
    public AnnouncementServiceImpl(){ //???????????????????????
        dao = new AnnouncementDAOImpl();
    }

    @Override
    public void save(Announcement announcement) {
        dao.save(announcement);
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
        return dao.showByRubricId(id);
    }

    @Override
    public List<Announcement> showByAuthorId(int id) {
        return dao.showByAuthorId(id);
    }

    @Override
    public List<Announcement> showByKeyWord(String keyWord) {
        return dao.showByKeyWord(keyWord);
    }

    @Override
    public List<Announcement> showByDate(LocalDateTime date) {
        return dao.showByDate(date);
    }

    @Override
    public void deleteById(int id) {
        dao.deleteById(id);
    }

    @Override
    public List<Announcement> findAll() {
        return dao.findAll();
    }
}
