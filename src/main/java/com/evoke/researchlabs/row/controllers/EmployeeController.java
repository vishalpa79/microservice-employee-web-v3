package com.evoke.researchlabs.row.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.evoke.researchlabs.row.domain.Employee;
import com.evoke.researchlabs.row.repositories.RestServiceCall;

@Controller
public class EmployeeController {

	@Autowired(required = true)
	RestServiceCall restfulClient;

	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("employees", restfulClient.listAllUsers());
		return "employees";
	}

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
	public String showProduct(@PathVariable Integer id, Model model) {
		model.addAttribute("emp", restfulClient.getEmployeeById(id));
		return "employeeshow";
	}

	@RequestMapping("employee/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		model.addAttribute("employee", restfulClient.getEmployeeById(id));
		return "employeeform";
	}

	@RequestMapping("employee/delete/{id}")
	public String delete(@PathVariable Integer id, Model model) {
		restfulClient.deleteEmployeeById(id);
		
		return "redirect:/employees";
		
	}
	@RequestMapping("employee/new")
	public String newProduct(Model model) {
		model.addAttribute("employee", new Employee());
		return "employeeform";
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String saveEmployee(Employee employeeBean, Model model) {
		
		Employee emp = restfulClient.postEntity(employeeBean);
		model.addAttribute("emp", emp);
		return "redirect:/employee/"+emp.getId();

	}

}
