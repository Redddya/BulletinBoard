package org.finalproject.controller;

import jakarta.validation.Valid;
import org.finalproject.domain.MatchingAd;
import org.finalproject.service.MatchingAdService;
import org.finalproject.util.exception.custom.MatchingAdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matching-ads")
public class MatchingAdController {
    MatchingAdService matchingAdService;
    public MatchingAdController(MatchingAdService matchingAdService){
        this.matchingAdService = matchingAdService;
    }
    @Secured(value = "USER")
    @GetMapping("/{id}")
    public MatchingAd getMatchingAd(@PathVariable int id){
        MatchingAd byId = matchingAdService.findById(id);
        System.out.println();
        return byId;
    }
    @Secured(value = "ADMIN")
    @GetMapping
    public List<MatchingAd> getAllMatchingAds(){
        return matchingAdService.findAll();
    }
    @PostMapping("/new")
    public ResponseEntity<HttpStatus> create(
            @RequestBody @Valid MatchingAd matchingAd, BindingResult bindingResult){
        hasErrors(bindingResult);
        matchingAdService.save(matchingAd);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @Secured(value = "USER")
    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable int id,
                                             @RequestBody @Valid MatchingAd matchingAd, BindingResult bindingResult){
        hasErrors(bindingResult);
        matchingAd.setId(id);
        matchingAdService.update(matchingAd);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @Secured(value = "ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id){
        matchingAdService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    private void hasErrors(BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            StringBuilder errorMessage = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error: errors){
                errorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage());
            }
            throw new MatchingAdException(errorMessage.toString());
        }
    }
}
