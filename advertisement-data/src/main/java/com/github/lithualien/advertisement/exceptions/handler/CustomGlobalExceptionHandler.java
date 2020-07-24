package com.github.lithualien.advertisement.exceptions.handler;

import com.github.lithualien.advertisement.exceptions.NotContentCreatorException;
import com.github.lithualien.advertisement.exceptions.ResourceAlreadyExistsException;
import com.github.lithualien.advertisement.exceptions.ResourceNotFoundException;
import com.github.lithualien.advertisement.exceptions.UnsupportedMediaType;
import com.github.lithualien.advertisement.vo.v1.ExceptionVO;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
@RestController
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler( { Exception.class } )
    public final ResponseEntity<ExceptionVO> handleInternalServerErrorExceptions(Exception exception, WebRequest webRequest) {
        return new ResponseEntity<>(getExceptionResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
                webRequest.getDescription(false)), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler( { UnsupportedMediaType.class } )
    public final ResponseEntity<ExceptionVO> handleUnsupportedMediaTypeExceptions(Exception exception, WebRequest webRequest) {
        return new ResponseEntity<>(getExceptionResponse(exception.getMessage(), HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),
                webRequest.getDescription(false)), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler( { ResourceNotFoundException.class, ResourceAlreadyExistsException.class, ConstraintViolationException.class, MethodArgumentTypeMismatchException.class } )
    public final ResponseEntity<ExceptionVO> handleBadRequestExceptions(Exception exception, WebRequest webRequest) {
        return new ResponseEntity<>(getExceptionResponse(exception.getMessage(), HttpStatus.BAD_REQUEST.value(),
                webRequest.getDescription(false)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler( { NotContentCreatorException.class } )
    public final ResponseEntity<ExceptionVO> handleUnauthorizedExceptions(Exception exception, WebRequest webRequest) {
        return new ResponseEntity<>(getExceptionResponse(exception.getMessage(), HttpStatus.UNAUTHORIZED.value(),
                webRequest.getDescription(false)), HttpStatus.UNAUTHORIZED);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();

        body.put("timestamp",LocalDateTime.now());
        body.put("status", status.value());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("errors", errors);
        body.put("path", request.getDescription(false));

        return new ResponseEntity<>(body, headers, status);

    }

    private ExceptionVO getExceptionResponse(String error, Integer status, String description) {
        return new ExceptionVO(
                LocalDateTime.now(),
                status,
                error,
                description
        );
    }
}
