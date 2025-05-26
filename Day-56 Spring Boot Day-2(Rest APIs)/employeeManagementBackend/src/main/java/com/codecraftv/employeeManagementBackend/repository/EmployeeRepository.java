package com.codecraftv.employeeManagementBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codecraftv.employeeManagementBackend.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> { // JpaRepository<Entity,PrimaryKey> both
                                                                            // should be classes
    // Employee -> This is the entity class we want the repository to manage.
    // Long -> This is the data type of the primary key (ID) field in the Employee class.

    /*
        Why this pattern?

        Spring Data JPA uses generics so that you only have to define:

            The entity type (Product, User, Order, etc.)

            The primary key type (Long, Integer, etc.)

        And then it auto-generates all the basic CRUD operations for you!
     */
}
