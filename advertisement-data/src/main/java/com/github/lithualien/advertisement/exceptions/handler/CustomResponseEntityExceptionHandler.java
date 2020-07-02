package com.github.lithualien.advertisement.exceptions.handler;

import com.github.lithualien.advertisement.exceptions.NotContentCreatorException;
import com.github.lithualien.advertisement.exceptions.ResourceAlreadyExistsException;
import com.github.lithualien.advertisement.exceptions.ResourceNotFoundException;
import com.github.lithualien.advertisement.models.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler( { Exception.class } )
    public final ResponseEntity<ExceptionResponse> handleInternalServerErrorExceptions(Exception exception, WebRequest webRequest) {
        return new ResponseEntity<>(getExceptionResponse(exception.getMessage(), webRequest.getDescription(false)), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler( { ResourceNotFoundException.class, ResourceAlreadyExistsException.class } )
    public final ResponseEntity<ExceptionResponse> handleBadRequestExceptions(Exception exception, WebRequest webRequest) {
        return new ResponseEntity<>(getExceptionResponse(exception.getMessage(), webRequest.getDescription(false)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler( { NotContentCreatorException.class } )
    public final ResponseEntity<ExceptionResponse> handleUnauthorizedExceptions(Exception exception, WebRequest webRequest) {
        return new ResponseEntity<>(getExceptionResponse(exception.getMessage(), webRequest.getDescription(false)), HttpStatus.UNAUTHORIZED);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler( { MethodArgumentNotValidException.class } )
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    private ExceptionResponse getExceptionResponse(String message, String description) {
        return new ExceptionResponse(
                LocalDateTime.now(),
                message,
                description
        );
    }
}
