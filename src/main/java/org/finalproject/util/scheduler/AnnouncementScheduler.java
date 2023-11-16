package org.finalproject.util.scheduler;

import org.finalproject.domain.Announcement;
import org.finalproject.service.AnnouncementService;
import org.finalproject.service.MatchingAdService;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

public class AnnouncementScheduler {
    MatchingAdService matchingAdService;
    AnnouncementService announcementService;

    public AnnouncementScheduler(MatchingAdService matchingAdService, AnnouncementService announcementService) {
        this.matchingAdService = matchingAdService;
        this.announcementService = announcementService;
    }

    @Scheduled(cron = "0 0 18 * * *")
    private void sendNotificationsAboutMatchingAnnouncements() {
        List<Announcement> announcements = announcementService.findAll();
        if (announcements.isEmpty())
            return;
        for (Announcement announcement : announcements) {
            matchingAdService.sendMassagesByMatchingAds(announcement);
        }
    }
}
