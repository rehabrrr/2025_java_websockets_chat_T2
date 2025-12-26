package com.example.webchat.exceptions;

import com.example.webchat.model.dto.common.ExceptionDescription;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.PrintWriter;
import java.io.StringWriter;

@Slf4j
@RestControllerAdvice
public class ServiceExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ExceptionDescription> handleUnknownException(HttpServletRequest request, Exception e) {
        return new ResponseEntity<>(
                new ExceptionDescription(getServiceName(request), "{reserved-for-future-use =)}", e.getMessage(), getStackTrace(e)),
                HttpStatus.BAD_REQUEST
        );
    }

    private static String getStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

    private static String getServiceName(HttpServletRequest request) {
        return request.getMethod() + " " + request.getServletPath();
    }
}