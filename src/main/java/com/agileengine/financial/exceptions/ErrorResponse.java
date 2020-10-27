package com.agileengine.financial.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorResponse {

  private final String error;
  private final String message;
  private final HttpStatus httpStatus;

  public ErrorResponse(String error, String message, HttpStatus httpStatus) {
    this.error = error;
    this.message = message;
    this.httpStatus = httpStatus;
  }
}
