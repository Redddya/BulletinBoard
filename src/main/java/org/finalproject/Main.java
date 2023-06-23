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
        AuthorServiceImpl authorService = context.getBean(AuthorServiceImpl.class);
        RubricServiceImpl rubricService = context.getBean(RubricServiceImpl.class);
        String email = "c@srrd.d1";
        String notPhone = "Not phone";
        Author author = Author.builder()
                .email(Email.builder().email(email).build()).build();
        Rubric rubric = Rubric.builder().name(notPhone).build();
        authorService.save(author);
        rubricService.save(rubric);
        Announcement announcement1 =Announcement.builder()
                .name("Announcement 1")
                .publicationDate(LocalDateTime.now())
                .text("This is announcement 1.")
                .price(new BigDecimal("9.99"))
                .author(author)
                .rubric(rubric)
                .build();
        announcementService.save(announcement1);
        System.out.println(announcementService.findById(79));
    }
}
