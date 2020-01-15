package main.java.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import main.java.app.service.ComputerService;

@Controller
@RequestMapping("/")
public class ComputerController {
	
	private ComputerService computerService;
	
	public ComputerController(ComputerService computerService) {
		this.computerService = computerService;
	}
	
	@GetMapping("/")
	public String index() {
		return "dashboard";
	}
	
	
}
