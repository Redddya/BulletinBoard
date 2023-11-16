package org.finalproject.repository;

import org.finalproject.domain.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional
public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {
    void deleteAllByAuthorId(int id);
    void deleteAllByRubricId(int id);
    @Query("SELECT a FROM Announcement a WHERE a.rubric.id = :id")
    List<Announcement> showByRubricId(@Param("id") int id);
    @Query("SELECT a FROM Announcement a WHERE a.author.id = :id")
    List<Announcement> showByAuthorId(@Param("id") int id);
    @Query("select a from Announcement a where a.text like %:keyword")
    List<Announcement> showByKeyword(@Param("keyword") String keyword);
    @Query("SELECT a FROM Announcement a WHERE a.publicationDate = :date")
    List<Announcement> showByPublicationDate(@Param("date") LocalDateTime date);
    @Query("DELETE Announcement a WHERE a.isActive = false")
    void deleteInactive();
}
