package org.finalproject.controller;


import org.finalproject.domain.Author;
import org.finalproject.service.AnnouncementService;
import org.finalproject.service.CRUDService;
import org.finalproject.util.exception.custom.AuthorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final CRUDService<Author> authorService;
    public AuthorController(CRUDService<Author> authorService, AnnouncementService announcementService){
        this.authorService = authorService;
    }

    @GetMapping("/{id}")
    public Author getAuthor(@PathVariable int id){
        return authorService.findById(id);
    }
    @GetMapping
    public List<Author> getAllAuthors(){
        return authorService.findAll();
    }
    @PostMapping("/new")
    public ResponseEntity<HttpStatus> create(
            @RequestBody @Valid Author author, BindingResult bindingResult){
        hasErrors(bindingResult);
        authorService.save(author);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable int id,
                                             @RequestBody @Valid Author author, BindingResult bindingResult){
        hasErrors(bindingResult);
        author.setId(id);
        authorService.update(author);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id){
        authorService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    private void hasErrors(BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            StringBuilder errorMessage = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error: errors){
                errorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage());
            }
            throw new AuthorException(errorMessage.toString());
        }
    }
}
