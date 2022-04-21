package com.simplilearn.webservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	@ResponseBody
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String indexMapping() {
		return "Welcome, to spring boot developement, Server is up and running !";
	}
	
	@ResponseBody
	@RequestMapping(value="/hello", method=RequestMethod.GET)
	public String helloMapping() {
		return "Hello, everyone ! welcome to Webservice learning !";
	}
	
	@ResponseBody
	@RequestMapping(value="/hi", method=RequestMethod.GET)
	public String hiMapping(@RequestParam(value="name") String name) {
		return "Hi "+name+" , ! welcome to Webservice learning !";
	}
	
	@ResponseBody
	@RequestMapping(value="/user/{id}", method=RequestMethod.GET)
	public String userMapping(@PathVariable(value="id") int id) {
		return "<h1>Hi User ," + id+"</h1>";
	}
}
