package com.agileengine.financial.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {

  @ExceptionHandler(BadRequestException.class)
  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  public ResponseEntity<ErrorResponse> badRequestException(BadRequestException badRequestException) {
    return  ResponseEntity.status(badRequestException.getHttpStatus().value())
        .body(new ErrorResponse(badRequestException.getError(),badRequestException.getMessage(),
            badRequestException.getHttpStatus()));
  }


  @ExceptionHandler(DataNotFoundException.class)
  @ResponseStatus(code = HttpStatus.NOT_FOUND)
  public ResponseEntity<ErrorResponse> badRequestException(DataNotFoundException dataNotFoundException) {
    return  ResponseEntity.status(dataNotFoundException.getHttpStatus().value())
        .body(new ErrorResponse(dataNotFoundException.getError(),dataNotFoundException.getMessage(),
            dataNotFoundException.getHttpStatus()));
  }

  @ExceptionHandler(LockedException.class)
  @ResponseStatus(code = HttpStatus.NOT_FOUND)
  public ResponseEntity<ErrorResponse> badRequestException(LockedException lockedException) {
    return  ResponseEntity.status(lockedException.getHttpStatus().value())
        .body(new ErrorResponse(lockedException.getError(),lockedException.getMessage(),
            lockedException.getHttpStatus()));
  }

}
