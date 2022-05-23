package com.hasan.laboratuvarraporlama.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GeneralExceptionHandler {


    @ExceptionHandler(ReportNotNullException.class)
    public ResponseEntity<?> reportNotNull(ReportNotNullException reportNotNullException) {
        List<String> details = new ArrayList<>();
        details.add(reportNotNullException.getMessage());

        ErrorResponse errorResponse = new ErrorResponse("Report is null", details);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ReportNotFoundException.class)
    public ResponseEntity<?> reportNotFound(ReportNotFoundException reportNotFoundException) {
        List<String> details = new ArrayList<>();
        details.add(reportNotFoundException.getMessage());

        ErrorResponse errorResponse = new ErrorResponse("Report is not found", details);

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IdentityNumberException.class)
    public ResponseEntity<?> incorrectIdentity(IdentityNumberException identityNumberException) {
        List<String> details = new ArrayList<>();
        details.add(identityNumberException.getMessage());

        ErrorResponse errorResponse = new ErrorResponse("Given identity number is incorrect!", details);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }




}
