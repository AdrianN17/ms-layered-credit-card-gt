package com.bank.credit_card.exceptions;

import com.bank.credit_card.generic.service.exception.ServiceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ControllerAdvice
public class GlobalControllAdvice {

    @ExceptionHandler({ServiceException.class,
            CustomNotFound.class, CustomBadRequest.class})
    public final ResponseEntity<?> handleException(Exception ex, WebRequest request) {

        HttpHeaders headers = new HttpHeaders();
        if (ex instanceof CustomBadRequest se) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            return handleServiceBadRequestException(se, headers, status, request);
        } else if (ex instanceof CustomNotFound cnf) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            return handleNotFoundException(cnf, headers, status, request);
        } else if (ex instanceof ServiceException se) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            return handleServiceBadRequestException(se, headers, status, request);
        } else {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleExceptionInternal(ex, null, headers, status, request);
        }
    }

    protected ResponseEntity<?> handleServiceBadRequestException(CustomBadRequest cbr, HttpHeaders headers,
                                                                 HttpStatus status, WebRequest request) {
        cbr.getBindingResult().getFieldErrors();
        List<Map<String, String>> fErrors = cbr.getBindingResult().getFieldErrors().stream().map(err -> {
            Map<String, String> error = new HashMap<>();
            error.put(err.getField(), err.getDefaultMessage());
            return error;
        }).toList();


        return handleExceptionInternal(cbr, fErrors, headers, status, request);

    }

    protected ResponseEntity<?> handleNotFoundException(CustomNotFound cnf, HttpHeaders headers,
                                                        HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(cnf.getMessage());
        Map<String, Object> body = new HashMap<>();
        body.put("errors", errors);
        return handleExceptionInternal(cnf, body, headers, status, request);
    }


    protected ResponseEntity<?> handleServiceBadRequestException(ServiceException uce, HttpHeaders headers,
                                                                 HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(uce.getMessage());
        Map<String, Object> body = new HashMap<>();
        body.put("errors", errors);
        return handleExceptionInternal(uce, body, headers, status, request);
    }


    protected ResponseEntity<?> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                        HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        return new ResponseEntity<>(body, headers, status);
    }

}
