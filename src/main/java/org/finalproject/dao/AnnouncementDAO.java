package org.finalproject.dao;

import org.finalproject.domain.Announcement;

import java.time.LocalDateTime;
import java.util.List;

public interface AnnouncementDAO extends CRUDDao<Announcement> {
    void deleteAllByAuthorId(int id);
    void deleteAllByRubricId(int id);
    List<Announcement> showByRubricId(int id);
    List<Announcement> showByAuthorId(int id);
    List<Announcement> showByKeyWord(String keyWord);
    List<Announcement> showByDate(LocalDateTime date);

}
