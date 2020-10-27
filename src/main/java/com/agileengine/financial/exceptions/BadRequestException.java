package com.agileengine.financial.exceptions;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BadRequestException extends RuntimeException {

  private String error;
  private String message;
  private HttpStatus httpStatus;

  public BadRequestException(String error, String message,
      HttpStatus httpStatus) {
    this.error = error;
    this.message = message;
    this.httpStatus = httpStatus;
  }
}
