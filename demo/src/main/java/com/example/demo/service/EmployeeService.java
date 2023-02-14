package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.EmployeeException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {
  @Autowired
  private EmployeeRepository employeeRepository;

  public List<Employee> all() {
    return employeeRepository.findAll();
  }

  public Employee add(Employee employee) {
    return employeeRepository.save(employee);
  }

  public Employee get(Long id) {
    return employeeRepository.findById(id)
      .orElseThrow(() -> new EmployeeException.NotFound(id));
  }

  public Employee modify (Employee newEmployee, Long id) {
    return employeeRepository.findById(id).map(employee -> {
      employee.setName(newEmployee.getName());
      employee.setRole(newEmployee.getRole());
      return employeeRepository.save(employee);
    }).orElseThrow(() -> new EmployeeException.NotFound(id));
    // .orElseGet(() -> {
    //   employee.setId(id);
    //   return employeeRepository.save(employee);
    // });
  }

  public void delete (Long id) {
    employeeRepository.deleteById(id);
  }
}
