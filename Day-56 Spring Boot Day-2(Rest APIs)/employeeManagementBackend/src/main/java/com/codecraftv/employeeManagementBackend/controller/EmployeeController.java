package com.codecraftv.employeeManagementBackend.controller;

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

import com.codecraftv.employeeManagementBackend.model.Employee;
import com.codecraftv.employeeManagementBackend.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    // You can define the api end points here
    // Before that create a bean of EmployeeService here

    @Autowired
    private EmployeeService empService; // spring injects the bean here

    @GetMapping
    public List<Employee> getAllEmployees() {
        return empService.getAllEmployees();
    }

    // Get by id
    @GetMapping("/{id}")
    public Employee getById(@PathVariable Long id) {
        return empService.getEmployeeById(id);
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee emp) {
        return empService.saveEmployee(emp);
    }

    @PutMapping("/{id}")
    public Employee updateEmp(@PathVariable Long id, @RequestBody Employee updated_emp) {
        return empService.modifyEmployee(id, updated_emp); // this updates the employee based on id field
    }

    @DeleteMapping("/{id}")
    public boolean deleteEmp(@PathVariable Long id) {
        return empService.removeEmployeeById(id);
    }

}
