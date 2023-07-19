package org.finalproject;

import org.finalproject.config.ConfigApp;
import org.finalproject.domain.Announcement;
import org.finalproject.domain.Author;
import org.finalproject.domain.Email;
import org.finalproject.domain.Rubric;
import org.finalproject.service.AnnouncementService;
import org.finalproject.service.CRUDService;
import org.finalproject.service.MatchingAdService;
import org.finalproject.service.impl.AnnouncementServiceImpl;
import org.finalproject.service.impl.AuthorServiceImpl;
import org.finalproject.service.impl.MatchingAdServiceImpl;
import org.finalproject.service.impl.RubricServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigApp.class);

        AnnouncementServiceImpl announcementService = context.getBean(AnnouncementServiceImpl.class);
        announcementService.deleteAllByRubricId(90);
        System.out.println(announcementService.findById(87));
    }
}
