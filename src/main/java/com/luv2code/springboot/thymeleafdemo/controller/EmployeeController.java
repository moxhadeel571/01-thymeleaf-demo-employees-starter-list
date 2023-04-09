package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	// load employee data


	private EmployeeService employeeService;

	public EmployeeController(EmployeeService theEmployeeservice) {
		 employeeService = theEmployeeservice;
	}

	// add mapping for "/list"

	@GetMapping("/list")
	public String Employees(Model theModel) {
// get the employees from db
		List<Employee> theEmployees = employeeService.findAll();
// add to the spring model
		theModel.addAttribute("employees", theEmployees);
		return "employees/list-employees";
	}
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId,Model theModel){
		Employee theEmployee= employeeService.findById(theId);
		theModel.addAttribute("employee",theEmployee);
		return "employees/employee-form";
	}
	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId")int theId){
		employeeService.deleteById(theId);
		return "redirect:/employees/list";
	}
 	@GetMapping ("/showFormForAdd")
	public String showFormForAdd (Model theModel) {
// create model attribute to bind form data
		Employee theEmployee = new Employee();
		theModel.addAttribute(  "employee", theEmployee);
		return "employees/employee-form";
	}
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
		employeeService.save(theEmployee);
		return "redirect:/employees/list";
	}
//	@PutMapping("/update")
//	public String updateEmployee(@ModelAttribute("employee")Employee theEmployee){
//		employeeService.
//	}

}









