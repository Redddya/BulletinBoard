package org.finalproject;

import org.finalproject.domain.*;
import org.finalproject.service.AnnouncementService;
import org.finalproject.service.CRUDService;
import org.finalproject.service.MatchingAdService;
import org.finalproject.service.impl.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        MatchingAdService service = new MatchingAdServiceImpl();
        AnnouncementService announcementService = new AnnouncementServiceImpl();


        System.out.println(service.filter(announcementService.findById(79)));
    }
}
