package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.demo.exception.EmployeeException;

@ControllerAdvice
public class AdviceController {
  @ResponseBody
  @ExceptionHandler(EmployeeException.NotFound.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String employeeNotFoundHandler (EmployeeException.NotFound exception) {
    return exception.getMessage();
  }
}
