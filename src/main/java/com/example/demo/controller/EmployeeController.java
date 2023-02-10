package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
class EmployeeController {
  @Autowired
  private EmployeeService employeeService;
  
  @GetMapping
  List<Employee> all() {
    return employeeService.all();
  }

  @PostMapping
  Employee add(@RequestBody Employee employee) {
    return employeeService.add(employee);
  }

  @GetMapping("/{id}")
  Employee get(@PathVariable Long id) {
    return employeeService.get(id);
  }

  @PutMapping("/{id}")
  Employee modify(@RequestBody Employee newEmployee, @PathVariable Long id) {
    return employeeService.modify(newEmployee, id);
  }

  @DeleteMapping("/{id}")
  void delete(@PathVariable Long id) {
    employeeService.delete(id);
  }
}
