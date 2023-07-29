package org.finalproject.util.exception;

import org.finalproject.util.exception.custom.AnnouncementException;
import org.finalproject.util.exception.custom.AuthorException;
import org.finalproject.util.exception.custom.MatchingAdException;
import org.finalproject.util.exception.custom.RubricException;
import org.finalproject.util.exception.custom.error_response.AnnouncementErrorResponse;
import org.finalproject.util.exception.custom.error_response.AuthorErrorResponse;
import org.finalproject.util.exception.custom.error_response.MatchingAdErrorResponse;
import org.finalproject.util.exception.custom.error_response.RubricErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdBoardExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<AnnouncementErrorResponse> announcementExceptionHandler
            (AnnouncementException e) {
        AnnouncementErrorResponse response =
                new AnnouncementErrorResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler
    public ResponseEntity<AuthorErrorResponse> authorExceptionHandler
            (AuthorException e){
        AuthorErrorResponse response =
                new AuthorErrorResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler
    public ResponseEntity<MatchingAdErrorResponse> matchingAdExceptionHandler
            (MatchingAdException e){
        MatchingAdErrorResponse response =
                new MatchingAdErrorResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
    }
    @ExceptionHandler
    public ResponseEntity<RubricErrorResponse> rubricExceptionHandler
            (RubricException e){
        RubricErrorResponse response =
                new RubricErrorResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
    }
}
