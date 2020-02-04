package com.fci.engaly.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.fci.engaly.dao.Employeedao;
import com.fci.engaly.model.Employee;

@Service
public class EmployeeServiceImp implements EmployeeService {

	@Autowired
	Employeedao dao;
	// this class have more methods about mongoRepository
	@Autowired
	MongoTemplate mongoOperations;

	@Override
	public Collection<Employee> getAllEmployees() {
		return dao.findAll();
	}

	@Override
	public Collection<Employee> getAllEmployeesOrderByName() {
		return dao.findAll(Sort.by(Sort.Direction.ASC, "name"));
	}

	@Override
	public Optional<Employee> findEmployeeById(String id) {
		return dao.findById(id);

	}

	@Override
	public List<Employee> findEmployeeByName(String name) {
		return new Helper().getEmpByName(name);
	}

	@Override
	public List<Employee> findEmployeeBySalary(String salary) {
		return new Helper().getEmpBySalay(salary);
	}

	@Override
	public String createEmployee(Employee emp) {
		if (!(emp.getName().trim().isEmpty() || emp.getDesignation().trim().isEmpty())) {
			if (!new Helper().isDuplicate(emp)) {
				dao.insert(emp);
				return "<h1>Employee records created.</h1>";
			}
			return "<h1>This Employee Name Is Not Available.</h1>";
		}
		return "<h1>fill all fields correctly</h1>";
	}

	@Override
	public void deleteEmployeeById(String id) {
		dao.deleteById(id);
	}

	@Override
	public void updateEmployee(Employee emp) {
		dao.save(emp);
	}

	@Override
	public void deleteAllEmployees() {
		dao.deleteAll();
	}

	@Override
	public List<Employee> findEmployeeByNameRegex(String name) {
		return new Helper().getEmployeeByNameRegex(name);
	}

	class Helper {
		/**
		 * method used to check if defined field value is pre found so that will
		 * preventing employee double name
		 * 
		 * @param data
		 * @return
		 */
		private boolean isDuplicate(Object data) {
			Employee emp = (Employee) data;
			Query query = new Query();
			query.addCriteria(Criteria.where("name").is(emp.getName().trim()));
			List<Employee> ee = mongoOperations.find(query, Employee.class);
			if (ee.size() > 0) {
				return true;
			}
			return false;

		}

		private List<Employee> getEmpByName(String name) {
			Query query = new Query();
			query.addCriteria(Criteria.where("name").is(name));
			List<Employee> emps = mongoOperations.find(query, Employee.class);
			return emps;
		}

		private List<Employee> getEmployeeByNameRegex(String name) {
			Query query = new Query();
			// . any character * with any times
			query.addCriteria(Criteria.where("name").regex(String.format(".*%s.*", name)));
			List<Employee> emps = mongoOperations.find(query, Employee.class);
			return emps;
		}

		private List<Employee> getEmpBySalay(String salary) {
			Query query = new Query();
			query.addCriteria(Criteria.where("salary").is(salary));
			List<Employee> emps = mongoOperations.find(query, Employee.class);
			return emps;
		}

		/**
		 * check if passed id if found or not
		 * 
		 * @param id
		 * @return
		 */
		private boolean checkId(String id) {
			if (dao.existsById(id)) {
				return true;
			}
			return false;
		}
	}
}
