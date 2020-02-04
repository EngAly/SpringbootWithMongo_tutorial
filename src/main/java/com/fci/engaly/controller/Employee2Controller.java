package com.fci.engaly.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * class to view html pages and you can send variable to this html pages
 */
@RequestMapping(value = "/c2v") // controller to view
@Controller
public class Employee2Controller {

	// @Autowired
	// EmployeeService serv;

	@RequestMapping(value = "/passVar")
	public String sendJson(Model model) {
		// you can pass object to View
		// model.addAttribute("json", serv.getAllEmployees());
		model.addAttribute("summary", "details about employees data");
		model.addAttribute("time", getReportDate());

		// organizeEmps => html page that controller will view it.
		return "organizeEmps";
	}

	/**
	 * get the time that report shown in it
	 * 
	 * @return: report time
	 */
	private String getReportDate() {
		String pattern = "dd-MM-yyyy HH:mm:ss";
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		String date = dateFormat.format(new Date());
		return date;
	}

}
