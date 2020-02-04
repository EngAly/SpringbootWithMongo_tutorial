package com.fci.engaly.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.Query;

import com.fci.engaly.model.Employee;

/**
 * write all method that you want to made it api for all mongo db manipulation
 * you can put all methods that will wrapping all DAO(data access object) that
 * operate with and benefits all mongo database queries.
 *
 */
public interface EmployeeService {

	@Query("{ 'name' : ?0}")
	/**
	 * Method to fetch all employees from the db.
	 * 
	 * @return
	 */
	public Collection<Employee> getAllEmployees();

	/**
	 * method to get all employees order by name note you can change order by to
	 * any other field as salary when you contract with this method you can
	 * change field of sorting
	 * 
	 * @return
	 */
	public Collection<Employee> getAllEmployeesOrderByName();

	/**
	 * Method to save employees in the db. you will pass name and designation
	 * from index page and will pass to mongo repositoy method
	 * 
	 * @param emp
	 * @return
	 */
	public String createEmployee(Employee emp);

	/**
	 * Method to fetch employee by id.
	 * 
	 * @param id
	 *            for specific employee
	 * @return:- employee record
	 */
	public Optional<Employee> findEmployeeById(String id);

	/**
	 * Method to fetch employee by any field in employee details.
	 * 
	 * @param any
	 *            field: name, salary and any other filed for specific employee
	 * @return:- employee record
	 */
	public List<Employee> findEmployeeByName(String name);

	public List<Employee> findEmployeeBySalary(String salary);

	/**
	 * method to get all employees with defined regex in method so that you can
	 * defined pattern that you will call employees with it
	 * 
	 * @param name:name
	 *            or part of it all is equal
	 * @return:- get all employees with defined pattern
	 */
	public List<Employee> findEmployeeByNameRegex(String name);

	/**
	 * Method to update employee by id. will pass emp details to update it with
	 * new content
	 * 
	 * @param id
	 * @param emp
	 * @return
	 */
	public void updateEmployee(Employee emp);

	public void deleteEmployeeById(String id);

	public void deleteAllEmployees();

}
