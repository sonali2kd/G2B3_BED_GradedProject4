package com.greatlearning.emra.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greatlearning.emra.entity.Employee;
import com.greatlearning.emra.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/list")
	public String listEmployees(Model model) {
		
		List<Employee> employees = employeeService.listAll();
		
		model.addAttribute("employees", employees);
		
		return "list-employees";
		
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		Employee theEmployee = new Employee();
		
		theModel.addAttribute("Employee", theEmployee);
		
		return "employee-form";
		
	}
	
	@PostMapping("/save")
	public String saveEmployee(
			@RequestParam("id") int id,
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("email") String email
			) {
		
		System.out.println(id);
		Employee theEmployee = null;
		
		if(id!=0)
		{
			
		}
		else {
			theEmployee = new Employee(firstName, lastName, email);
		}
		
		employeeService.add(theEmployee);
		
		return "redirect:/employees/list";
		
	}
	
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel) {
		
		Employee theEmployee = employeeService.findById(theId);
		
		theModel.addAttribute("Employee", theEmployee);
		
		return "employee-form";
		
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId) {
		
		employeeService.deleteById(theId);
		
		return "redirect:/employees/list";
		
	}
	
	@RequestMapping(value = "/403")
	public ModelAndView accessDenied(Principal user) {
		
		ModelAndView model = new ModelAndView();
		
		if(user != null) {
			
			model.addObject("msg", "Hi" + user.getName() 
			+ ", you do not have permission to access this page!");
			
		}else {
			
			model.addObject("msg", 
			"You do not have permission to access this page!");
			
		}
		
		model.setViewName("403");
		
		return model;
		
	}
	
}
