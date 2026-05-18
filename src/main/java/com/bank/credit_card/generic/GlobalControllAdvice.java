package com.bank.credit_card.generic;

import com.bank.credit_card.generic.exception.*;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalControllAdvice {

    // ── Validación automática de @Valid @RequestBody (BAD REQUEST → 400) ─────

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, WebRequest request) {

        List<Map<String, String>> fieldErrors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(fe -> {
                    Map<String, String> error = new HashMap<>();
                    error.put("field", fe.getField());
                    error.put("message", fe.getDefaultMessage());
                    error.put("rejectedValue",
                            fe.getRejectedValue() != null ? fe.getRejectedValue().toString() : "null");
                    return error;
                })
                .toList();

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
        body.put("code", "VALIDATION_ERROR");
        body.put("message", "Request validation failed");
        body.put("fieldErrors", fieldErrors);
        body.put("path", request.getDescription(false).replace("uri=", ""));

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    // ── @Validated en interfaz/controller con @Min/@Max en objetos anidados ──
    // Cuando @Validated está activo a nivel de clase, el MethodValidationInterceptor
    // lanza ConstraintViolationException en lugar de dejar que BindingResult la capture.

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, Object>> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest request) {

        List<Map<String, String>> fieldErrors = ex.getConstraintViolations()
                .stream()
                .map(cv -> {
                    Map<String, String> error = new HashMap<>();
                    // Extraer solo el nombre del campo (último segmento del propertyPath)
                    String path = cv.getPropertyPath().toString();
                    String field = path.contains(".")
                            ? path.substring(path.lastIndexOf('.') + 1)
                            : path;
                    error.put("field", field);
                    error.put("message", cv.getMessage());
                    error.put("rejectedValue",
                            cv.getInvalidValue() != null ? cv.getInvalidValue().toString() : "null");
                    return error;
                })
                .toList();

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
        body.put("code", "VALIDATION_ERROR");
        body.put("message", "Request validation failed");
        body.put("fieldErrors", fieldErrors);
        body.put("path", request.getDescription(false).replace("uri=", ""));

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    // ── Validación manual con BindingResult (BAD REQUEST → 400) ──────────────

    @ExceptionHandler(RequestValidationException.class)
    public ResponseEntity<Map<String, Object>> handleRequestValidationException(
            RequestValidationException ex, WebRequest request) {

        List<Map<String, String>> fieldErrors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(fe -> {
                    Map<String, String> error = new HashMap<>();
                    error.put("field", fe.getField());
                    error.put("message", fe.getDefaultMessage());
                    error.put("rejectedValue",
                            fe.getRejectedValue() != null ? fe.getRejectedValue().toString() : "null");
                    return error;
                })
                .toList();

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
        body.put("code", "VALIDATION_ERROR");
        body.put("message", "Request validation failed");
        body.put("fieldErrors", fieldErrors);
        body.put("path", request.getDescription(false).replace("uri=", ""));

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    // ── BAD REQUEST (400) ─────────────────────────────────────────────────────

    @ExceptionHandler({
            BadRequestException.class,
            NullPointerException.class,
            IllegalArgumentException.class
    })
    public ResponseEntity<Map<String, Object>> handleBadRequestException(
            RuntimeException ex, WebRequest request) {
        String message = ex.getMessage() != null ? ex.getMessage() : "Bad request";
        return buildResponse(HttpStatus.BAD_REQUEST, "BAD_REQUEST", message, request);
    }

    // ── UNAUTHORIZED (401) ────────────────────────────────────────────────────

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Map<String, Object>> handleUnauthorizedException(
            UnauthorizedException ex, WebRequest request) {
        return buildResponse(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED", ex.getMessage(), request);
    }

    // ── UNPROCESSABLE ENTITY (422) ────────────────────────────────────────────

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<Map<String, Object>> handleUnprocessableEntityException(
            UnprocessableEntityException ex, WebRequest request) {
        return buildResponse(HttpStatus.valueOf(422), "UNPROCESSABLE_ENTITY", ex.getMessage(), request);
    }

    // ── INTERNAL SERVER ERROR (500) ───────────────────────────────────────────

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<Map<String, Object>> handleInternalServerErrorException(
            InternalServerErrorException ex, WebRequest request) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", ex.getMessage(), request);
    }

    // ── BAD GATEWAY (502) ─────────────────────────────────────────────────────

    @ExceptionHandler(BadGatewayException.class)
    public ResponseEntity<Map<String, Object>> handleBadGatewayException(
            BadGatewayException ex, WebRequest request) {
        return buildResponse(HttpStatus.BAD_GATEWAY, "BAD_GATEWAY", ex.getMessage(), request);
    }


    // ── Fallback general (500) ────────────────────────────────────────────────

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(
            Exception ex, WebRequest request) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_ERROR",
                "An unexpected error occurred", request);
    }

    // ── Helper ────────────────────────────────────────────────────────────────

    private ResponseEntity<Map<String, Object>> buildResponse(
            HttpStatus status, String errorCode, String message, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("code", errorCode);
        body.put("message", message != null ? message : "No message available");
        body.put("path", request.getDescription(false).replace("uri=", ""));

        return new ResponseEntity<>(body, status);
    }
}
