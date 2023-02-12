package com.greatlearning.emra.service;

import java.util.List;

import com.greatlearning.emra.entity.Employee;

public interface EmployeeService {

	public List<Employee> listAll();

	public void add(Employee theEmployee);

	public Employee findById(int theId);

	public void deleteById(int theId);

	void save(Employee employee);

}
