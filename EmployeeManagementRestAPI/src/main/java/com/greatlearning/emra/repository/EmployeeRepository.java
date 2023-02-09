package com.greatlearning.emra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.emra.entity.Employee;

public interface EmployeeRepository extends 
JpaRepository<Employee, Integer>{
	
	void deleteById(long id);

}
