package com.devsuperior.dsmeta.controllers.handlers;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.dsmeta.common.dto.CustomError;
import com.devsuperior.dsmeta.common.exceptions.DateParseException;

@ControllerAdvice
public class ControllerExceptionHandler {
  
  @ExceptionHandler(DateParseException.class)
  public ResponseEntity<CustomError> DateParseException(DateParseException e, HttpServletRequest request){
    HttpStatus status = HttpStatus.BAD_REQUEST;
    
    String path = request.getRequestURI();
    String queryString = request.getQueryString();
    
    if (queryString != null && !queryString.isEmpty()) {
      path = path + "?" + queryString;
    }
    
    CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), path);
    return ResponseEntity.status(status).body(err);
  }
}
