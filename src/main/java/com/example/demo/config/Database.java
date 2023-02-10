package com.example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@Configuration
public class Database {
  private static final Logger log = LoggerFactory.getLogger(Database.class);

  @Bean
  CommandLineRunner initDatabase(EmployeeService employeeService) {
		return args -> {
			employeeService.add(new Employee("Bilbo Baggins", "burglar"));
			employeeService.add(new Employee("Frodo Baggins", "thief"));
		};
  }
}
