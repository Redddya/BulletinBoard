package org.finalproject.controller;


import jakarta.validation.Valid;
import org.finalproject.domain.Announcement;
import org.finalproject.service.AnnouncementService;
import org.finalproject.util.exception.custom.AnnouncementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/announcements")
public class AnnouncementController {
    private final AnnouncementService announcementService;


    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @GetMapping("/{id}")
    public Announcement getAnnouncement(@PathVariable int id) {
        return announcementService.findById(id);
    }

    @GetMapping
    public List<Announcement> getAllAnnouncements() {
        return announcementService.findAll();
    }

    @GetMapping("/rubric/{id}")
    public List<Announcement> getAllAnnouncementsByRubricId(@PathVariable("id") int rubricId) {
        return announcementService.showByRubricId(rubricId);
    }
    @Secured(value = "USER")
    @GetMapping("/author/{id}")
    public List<Announcement> getAllAnnouncementsByAuthorId(@PathVariable("id") int authorId) {
        return announcementService.showByAuthorId(authorId);
    }

    @GetMapping("/keyword/{keyword}")//change on parameter
    public List<Announcement> getAllAnnouncementsByKeyword(@PathVariable String keyword) {
        return announcementService.showByKeyword(keyword);
    }
    @Secured(value = "USER")
    @PostMapping("/new")
    public ResponseEntity<HttpStatus> create(
            @RequestBody @Valid Announcement announcement, BindingResult bindingResult) {
        hasErrors(bindingResult);
        announcementService.save(announcement);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @Secured(value = "USER")
    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable int id,
                                             @RequestBody @Valid Announcement announcement, BindingResult bindingResult) {
        hasErrors(bindingResult);
        announcement.setId(id);
        announcementService.update(announcement);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @Secured(value = "USER")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {
        announcementService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @Secured(value = "ADMIN")
    @DeleteMapping("/{id}/rubric")
    public ResponseEntity<HttpStatus> deleteByRubricId(@PathVariable("id") int rubricId) {
        announcementService.deleteAllByRubricId(rubricId);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @Secured(value = "ADMIN")
    @DeleteMapping("/{id}/author")
    public ResponseEntity<HttpStatus> deleteByAuthorId(@PathVariable("id") int authorId) {
        announcementService.deleteAllByAuthorId(authorId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private void hasErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage());
            }
            throw new AnnouncementException(errorMessage.toString());
        }
    }
}
