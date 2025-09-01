package com.reacitvespring.exception;

public class MovieInfoNotFoundException extends RuntimeException {

  private String message;

  public MovieInfoNotFoundException(String message) {
    super(message);
    this.message = message;
  }
}
