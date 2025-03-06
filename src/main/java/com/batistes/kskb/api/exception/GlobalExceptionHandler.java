package com.batistes.kskb.api.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@ControllerAdvice // Indica que esta clase manejará excepciones globalmente en los controladores
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NoResourceFoundException.class) // Maneja NoResourceFoundException
    public ResponseEntity<?> NoResourceFoundException(NoResourceFoundException exception, WebRequest request) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("timestamp", new Date());
        errorDetails.put("message", exception.getMessage());
        errorDetails.put("details", request.getDescription(false));

        logger.error("NoResourceFoundException", exception);

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND); // Devuelve 404 Not Found
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class) // Maneja HttpRequestMethodNotSupportedException
    public ResponseEntity<?> HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception, WebRequest request) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("timestamp", new Date());
        errorDetails.put("message", exception.getMessage());
        errorDetails.put("details", request.getDescription(false));

        logger.error("HttpRequestMethodNotSupportedException", exception);

        return new ResponseEntity<>(errorDetails, HttpStatus.METHOD_NOT_ALLOWED); // Devuelve 405 Method Not Allowed
    }

    @ExceptionHandler(IllegalArgumentException.class) // Maneja IllegalArgumentException
    public ResponseEntity<?> illegalArgumentException(IllegalArgumentException exception, WebRequest request) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("timestamp", new Date());
        errorDetails.put("message", exception.getMessage());
        errorDetails.put("details", request.getDescription(false));

        logger.error("IllegalArgumentException", exception);

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST); // Devuelve 400 Bad Request
    }

    @ExceptionHandler(Exception.class) // Maneja cualquier Exception NO manejada específicamente
    public ResponseEntity<?> runtimeExceptionHandler(Exception ex, WebRequest request) throws Exception {
    if (ex instanceof AuthenticationException) {
        throw ex; // Re-throw the AuthenticationException to let Spring Security handle it.
    } else {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("timestamp", new Date());
        errorDetails.put("message", "Internal Server Error"); 
        errorDetails.put("details", request.getDescription(false));
        
        logger.error("Exception (non-Authentication): ", ex);

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR); // Devuelve 500 Internal Server Error
    }
}

}