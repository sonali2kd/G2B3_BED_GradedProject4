package com.greatlearning.emra.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.emra.entity.Employee;
import com.greatlearning.emra.repository.EmployeeRepository;
import com.greatlearning.emra.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	EmployeeRepository repository;
	
	@Override
	public List<Employee> listAll() {
		
		return repository.findAll();
		
	}
	
	@Override
	public void save(Employee employee) {
		
		repository.save(employee);
		
	}

	@Override
	public void add(Employee theEmployee) {
		
		repository.save(theEmployee);
		
	}

	@Override
	public Employee findById(int theId) {
		
		return repository.findById(theId).get();
		
	}

	@Override
	public void deleteById(int theId) {
		
		repository.deleteById(theId);
		
	}
	
}
