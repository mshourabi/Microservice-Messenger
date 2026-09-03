package com.github.mshourabi.distributor.config;


import com.github.mshourabi.client.dto.ErrorDTO;
import com.github.mshourabi.client.exceptions.DomainException;
import com.github.mshourabi.client.exceptions.DuplicateException;
import com.github.mshourabi.client.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpStatus.*;


@RestControllerAdvice
class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /**
     * To detect environment
     * if we run project in developer show more details
     */
    private final Environment environment;

    GlobalExceptionHandler(Environment environment) {
        this.environment = environment;
    }

    /**
     * This for invalid request
     * MethodArgumentNotValidException throws by Spring validations
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        log.error("Validation error", ex);
        var errors = ex.getAllErrors().stream()
                .map(error-> new ErrorDTO(
                        ((FieldError)error).getField(),
                        error.getDefaultMessage()))
                .toList();

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(UNPROCESSABLE_CONTENT, ex.getMessage());
        problemDetail.setTitle("Validation Error");
        problemDetail.setProperty("errors", errors);
        return ResponseEntity.status(UNPROCESSABLE_CONTENT).body(problemDetail);
    }


    /**
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(DomainException.class)
    public ProblemDetail handle(DomainException ex) {
        log.info("Bad request", ex);
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(UNPROCESSABLE_CONTENT, ex.getMessage());
        problemDetail.setTitle("Bad Request");
        problemDetail.setProperty("errors", List.of(ex.getMessage()));
        return problemDetail;
    }

    /**
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handle(ResourceNotFoundException ex) {
        log.error("Resource not found", ex);

        var errors =  new ErrorDTO(ex.getPropertyName(), ex.getMessage());

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("Resource Not Found");
        problemDetail.setProperty("errors", errors);
        return problemDetail;
    }

    /**
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(DuplicateException.class)
    public ProblemDetail handle(DuplicateException ex) {
        log.error("Duplicate error", ex);

        var errors =  new ErrorDTO(ex.getPropertyName(), ex.getMessage());

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(CONFLICT, ex.getMessage());
        problemDetail.setTitle("Duplicate Error");
        problemDetail.setProperty("errors", errors);
        return problemDetail;
    }

    /**
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    ProblemDetail handleUnexpected(Exception ex) {
        log.error("Unexpected exception occurred", ex);

        // Don't expose internal details in production
        String message = "An unexpected error occurred";
        if (isDevelopmentMode()) {
            message = ex.getMessage();
        }

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(INTERNAL_SERVER_ERROR, message);
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    private boolean isDevelopmentMode() {
        List<String> profiles = Arrays.asList(environment.getActiveProfiles());
        return profiles.contains("dev") || profiles.contains("local");
    }
}
