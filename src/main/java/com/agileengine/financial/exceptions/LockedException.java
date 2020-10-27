package com.agileengine.financial.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class LockedException extends RuntimeException{

  private String error;
  private String message;
  private HttpStatus httpStatus;

  public LockedException(String error, String message,
      HttpStatus httpStatus) {
    this.error = error;
    this.message = message;
    this.httpStatus = httpStatus;
  }
}
