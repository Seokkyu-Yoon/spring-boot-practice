package com.example.demo.exception;

public interface EmployeeException {
  public class NotFound extends RuntimeException {
    public NotFound(Long id) {
      super("Could not find employee " + id);
    }
  }
}
