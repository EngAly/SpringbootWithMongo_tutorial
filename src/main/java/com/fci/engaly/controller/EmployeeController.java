package com.fci.engaly.controller;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fci.engaly.model.Employee;
import com.fci.engaly.service.EmployeeService;

/**
 * this class have all CRUD queries on mongo database you can test each one by
 * one from its getmapping:value write url for each service and you can find the
 * result there method not supported in form method as delete,put and other so
 * that we use post man to test all this methods example: to test getAllEmps()
 * method type in your browser the following url
 * http://127.0.0.1:8080/parent(class value)/method value
 * http://127.0.0.1:8080/emp/getall
 * 
 * @author engaly
 *
 */
@RestController
@RequestMapping(value = "/emp")
public class EmployeeController {

	@Autowired
	EmployeeService serv;
	// @Autowired
	// MongoTemplate mongo;

	
	@GetMapping("/tes")
	public Map<String, Object> endPointExample() {
	    Map<String, Object> rtn = new LinkedHashMap<>();
	    rtn.put("name", "aly");
	    return rtn;

	}
	
	
	@GetMapping(value = "/getall")
	public Collection<Employee> getAllEmps() {
		return serv.getAllEmployees();
	}

	@GetMapping(value = "/getEmpsOrderByName")
	public Collection<Employee> getAllOrderByname() {
		return serv.getAllEmployeesOrderByName();
	}

	@GetMapping(value = "/getbyid?{employee-id}")
	public Optional<Employee> getById(@PathVariable(value = "employee-id") String id) {
		return serv.findEmployeeById(id);
	}

	@GetMapping(value = "/getby/{name}")
	public List<Employee> getEmpsByName(@PathVariable("name") String name) {
		return serv.findEmployeeByNameRegex(name);
	}

	@GetMapping(value = "/getby")
	public List<Employee> getEmpsBySalaryOrName(Employee emp) {
		try {
			if (emp.getSalary().trim().matches("[0-9]+")) {
				return serv.findEmployeeBySalary(emp.getSalary());
			}
			return serv.findEmployeeByName(emp.getName());
		} catch (Exception e) {
			return serv.findEmployeeByName(emp.getName());
		}

	}

	@PostMapping(value = "/create")
	public String create(Employee emp) {
		// bottom line if you use postman so that you will pass emp in body
		// public String create(@RequestBody List<Employee> emp) {
		return serv.createEmployee(emp);

	}

	/**
	 * method to update employee data via its id note that test this method on
	 * postman because form not support put in method example:- {"id":
	 * "5e36988b1edff2af8d806562","name": "test name","designation":
	 * "tstot","salary": "1000"}
	 * 
	 * @param e
	 * @return
	 */
	@PutMapping(value = "/update")
	public String update(@RequestBody Employee e) {
		// public String update(@PathVariable(value = "employee-id") String id,
		// @RequestBody Employee e) {
		// if you use post method can call it from form html use bottom header
		// public String update(Employee e) {
		// e.setId(id);
		serv.updateEmployee(e);
		return "Employee record for employee-id= " + e.getId() + " updated.";
	}

	/**
	 * Method to delete employee by id. test on postman because form method not
	 * support delete request use this
	 * url:http://127.0.0.1:8080/emp/delete/<<id>>
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete/{employee-id}")
	public String delete(@PathVariable(value = "employee-id") String id) {
		serv.deleteEmployeeById(id);
		return "Employee record for employee-id= " + id + " deleted.";
	}

	/**
	 * Method to delete all employees from the database.<br>
	 * please be warn to use this method it will erase all Documents in
	 * Collection
	 * 
	 * @return: successful erase all Documents
	 */
	@DeleteMapping(value = "/deleteall")
	public String deleteAll() {
		serv.deleteAllEmployees();
		return "All employee records deleted.";
	}

	// @GetMapping("/dd")
	// public String isStored(Object data) {
	// // public List<Employee> isStored(Object data) {
	// // Employee emp = (Employee) data;
	// try {
	// Query query = new Query();
	// query.addCriteria(Criteria.where("name").is("aly ahmed mohamed"));
	// List<Employee> ee = mongo.find(query, Employee.class);
	// return ee.size());
	// } catch (Exception e) {
	// return "name not found";
	// }
	//
	// }

}
