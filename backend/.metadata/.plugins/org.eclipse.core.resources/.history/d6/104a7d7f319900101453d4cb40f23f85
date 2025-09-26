package com.digitalwallet.backend;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Global exception handler so frontend receives JSON messages instead of HTML stacktraces.
 * Add this file to the same package root as your application or under an @Component-scan'd package.
 */
@RestControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> validationError(MethodArgumentNotValidException ex) {
    String msg = ex.getBindingResult().getFieldErrors().stream()
      .map(e -> e.getField() + " " + e.getDefaultMessage())
      .collect(Collectors.joining(", "));
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", msg));
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<?> badRequest(IllegalArgumentException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", ex.getMessage()));
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<?> typeMismatch(MethodArgumentTypeMismatchException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "invalid argument"));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> generic(Exception ex) {
    // print stacktrace to server log for debugging
    ex.printStackTrace();
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
      .body(Map.of("message", ex.getMessage() == null ? "server error" : "server error: " + ex.getMessage()));
  }
}
