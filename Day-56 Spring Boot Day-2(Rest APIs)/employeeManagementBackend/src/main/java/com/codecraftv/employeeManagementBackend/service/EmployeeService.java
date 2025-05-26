package com.codecraftv.employeeManagementBackend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codecraftv.employeeManagementBackend.model.Employee;
import com.codecraftv.employeeManagementBackend.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
public class EmployeeService {
    /*
     * This contains business logic and acts as a middle layer.
     * 
     * Purpose: Do validations, transformations, complex logic here.
     * 
     * Annotation: Use @Service.
     */

    // Have a bean of Repository Layer
    /*
     * So the actual object behind empRepository is a Spring-generated class that
     * implements your interface(EmployeeRepository) and handles all the
     * database logic using JPA and Hibernate.
     * Because Spring injects a concrete bean instance, you can call instance
     * methods on it just like any object.
     */
    @Autowired
    private EmployeeRepository empRepository; // the spring generated class bean gets injected here

    // Write methods here
    public Employee saveEmployee(Employee emp) {
        return empRepository.save(emp); // empRep talks to db saves to db
    }

    // Get all employees
    public List<Employee> getAllEmployees() {
        return empRepository.findAll(); // empRep talks to db and returns all the employees available
    }

    // Get Employee by Id
    public Employee getEmployeeById(Long id) {
        Optional<Employee> emp = empRepository.findById(id); // we will get a particular employee details by Id
        return emp.orElse(null); // If a value is present, returns the value, otherwise returns other.

    }

    
    @Transactional // to acheive atomicity and isolation
    // Now update a employee based on his id
    public Employee modifyEmployee(Long id, Employee updated_emp) {
        Optional<Employee> old_emp = empRepository.findById(id); // first find the employee before u update

        if (old_emp.isPresent()) {
            Employee existing_emp = old_emp.get(); // If a value is present, returns the value, otherwise throws
                                                   // NoSuchElementException.
            existing_emp.setName(updated_emp.getName());
            existing_emp.setDesignation(updated_emp.getDesignation());
            existing_emp.setDepartment(updated_emp.getDepartment());
            existing_emp.setAddress(updated_emp.getAddress());

            // Now save this updated employee to repository
            return empRepository.save(existing_emp);
        } else {
            return null;
        }

    }

    
    @Transactional // to acheive atomicity and isolation
    // Delete employee by id
    public boolean removeEmployeeById(Long id) {
        // First find the employee
        Optional<Employee> emp = empRepository.findById(id);

        if (emp.isPresent()) {
            empRepository.deleteById(id);
            return true;
        }

        else
            return false;

        /*
         * you can also write in this way
         * return empRepository.findById(id).map(emp -> {
         * empRepository.deleteById(id);
         * return true;
         * }).orElse(false);
         */

    }

}
