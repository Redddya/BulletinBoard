package org.finalproject.controller;


import org.finalproject.domain.Announcement;
import org.finalproject.service.AnnouncementService;
import org.finalproject.util.exception.AdBoardExceptionHandler;
import org.finalproject.util.exception.custom.AnnouncementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/announcements")
public class AnnouncementController {
    private final AnnouncementService announcementService;
    private final AdBoardExceptionHandler adBoardExceptionHandler;

    public AnnouncementController(AnnouncementService announcementService, AdBoardExceptionHandler adBoardExceptionHandler) {
        this.announcementService = announcementService;
        this.adBoardExceptionHandler = adBoardExceptionHandler;
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

    @GetMapping("/author/{id}")
    public List<Announcement> getAllAnnouncementsByAuthorId(@PathVariable("id") int authorId) {
        return announcementService.showByAuthorId(authorId);
    }

    @GetMapping("/keyWord/{keyWord}")//change on parameter
    public List<Announcement> getAllAnnouncementsByKeyWord(@PathVariable String keyWord) {
        return announcementService.showByKeyWord(keyWord);
    }

    @PostMapping("/new")
    public ResponseEntity<HttpStatus> create(
            @RequestBody @Valid Announcement announcement, BindingResult bindingResult) {
        hasErrors(bindingResult);
        announcementService.save(announcement);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable int id,
                                             @RequestBody @Valid Announcement announcement, BindingResult bindingResult) {
        hasErrors(bindingResult);
        announcement.setId(id);
        announcementService.update(announcement);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {
        announcementService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}/rubric")
    public ResponseEntity<HttpStatus> deleteByRubricId(@PathVariable("id") int rubricId) {
        announcementService.deleteAllByRubricId(rubricId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}/author")
    public ResponseEntity<HttpStatus> deleteByAuthorId(@PathVariable("id") int authorId) {
        announcementService.deleteAllByRubricId(authorId);
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
