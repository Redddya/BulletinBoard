package org.finalproject.controller;

import jakarta.validation.Valid;
import org.finalproject.domain.Author;
import org.finalproject.security.AuthorDetails;
import org.finalproject.service.AnnouncementService;
import org.finalproject.service.CRUDService;
import org.finalproject.config.JWTUtil;
import org.finalproject.util.exception.custom.AuthorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final CRUDService<Author> authorService;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    public AuthorController(CRUDService<Author> authorService, AnnouncementService announcementService, JWTUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.authorService = authorService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @Secured(value = "USER")
    @GetMapping("/{id}")
    public Author getAuthor(@PathVariable int id) {
        return authorService.findById(id);
    }

    @Secured(value = "ADMIN")
    @GetMapping("/getAllAuthors")
    public List<Author> getAllAuthors() {
        return authorService.findAll();
    }

    @PostMapping("/new")
    public Map<String, String> create(
            @RequestBody Author author, BindingResult bindingResult) {
        hasErrors(bindingResult);
        authorService.save(author);

        String token = jwtUtil.generateJWT(author.getEmail().getEmail());
        return Map.of("jwt-token", token);
    }

    @Secured(value = "USER")
    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable int id,
                                             @RequestBody @Valid Author author, BindingResult bindingResult) {
        hasErrors(bindingResult);
        author.setId(id);
        authorService.update(author);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Secured(value = "USER")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {
        authorService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("login")
    public Map<String, String> login(@RequestBody AuthorDetails authorDetails){
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(authorDetails.getUsername(), authorDetails.getPassword());
        try {
            authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException e){
            return Map.of("message","Incorrect credentials");
        }
        String token = jwtUtil.generateJWT(authorDetails.getUsername());
        return Map.of("jwt-token",token);
    }

    private void hasErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage());
            }
            throw new AuthorException(errorMessage.toString());
        }
    }
}
