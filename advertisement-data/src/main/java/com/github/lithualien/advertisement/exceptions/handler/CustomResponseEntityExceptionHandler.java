package com.github.lithualien.advertisement.exceptions.handler;

import com.github.lithualien.advertisement.exceptions.ResourceNotFoundException;
import com.github.lithualien.advertisement.models.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleGlobalExceptions(Exception exception, WebRequest webRequest) {
        return new ResponseEntity<>(getExceptionResponse(exception.getMessage(), webRequest.getDescription(false)), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleResourceNotFoundExceptions(Exception exception, WebRequest webRequest) {
        return new ResponseEntity<>(getExceptionResponse(exception.getMessage(), webRequest.getDescription(false)), HttpStatus.BAD_REQUEST);
    }

    private ExceptionResponse getExceptionResponse(String message, String description) {
        return new ExceptionResponse(
                LocalDateTime.now(),
                message,
                description
        );
    }
}
