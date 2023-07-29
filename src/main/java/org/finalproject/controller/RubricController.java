package org.finalproject.controller;

import org.finalproject.domain.Rubric;
import org.finalproject.service.AnnouncementService;
import org.finalproject.service.CRUDService;
import org.finalproject.util.exception.custom.RubricException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rubrics")
public class RubricController {
    private final CRUDService<Rubric> rubricService;
    public RubricController(CRUDService<Rubric> rubricService, AnnouncementService announcementService){
        this.rubricService = rubricService;
    }
    @GetMapping("/{id}")
    public Rubric getRubric(@PathVariable int id){
        return rubricService.findById(id);
    }
    @GetMapping
    public List<Rubric> getAllAuthors(){
        return rubricService.findAll();
    }
    @PostMapping("/new")
    public ResponseEntity<HttpStatus> create(
            @RequestBody @Valid Rubric rubric, BindingResult bindingResult){
        hasErrors(bindingResult);
        rubricService.save(rubric);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable int id,
                                             @RequestBody @Valid Rubric rubric, BindingResult bindingResult){
        hasErrors(bindingResult);
        rubric.setId(id);
        rubricService.update(rubric);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id){
        rubricService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private void hasErrors(BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            StringBuilder errorMessage = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error: errors){
                errorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage());
            }
            throw new RubricException(errorMessage.toString());
        }
    }
}
