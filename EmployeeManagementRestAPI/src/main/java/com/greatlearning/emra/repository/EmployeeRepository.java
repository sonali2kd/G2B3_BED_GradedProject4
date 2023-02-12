package com.greatlearning.emra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.emra.entity.Employee;

public interface EmployeeRepository extends 
JpaRepository<Employee, Integer>{
	
	void deleteById(long id);
	
	List<Employee> findByIdOrderByFirstNameAsc(String firstName);
	
}
