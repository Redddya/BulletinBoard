package org.finalproject.service;

import org.finalproject.domain.Announcement;

import java.time.LocalDateTime;
import java.util.List;

public interface AnnouncementService extends CRUDService<Announcement> {
    void deleteAllByAuthorId(int id);
    void deleteAllByRubricId(int id);
    List<Announcement> showByRubricId(int id);
    List<Announcement> showByAuthorId(int id);
    List<Announcement> showByKeyword(String keyWord);
    List<Announcement> showByDate(LocalDateTime date);
}
