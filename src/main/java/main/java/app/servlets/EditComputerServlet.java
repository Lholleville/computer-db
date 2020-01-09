package main.java.app.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.app.model.Company;
import main.java.app.model.Computer;
import main.java.app.service.CompanyService;
import main.java.app.service.ComputerService;
import main.java.app.validator.Validator;
import main.java.app.validator.modeltovalidate.ComputerValidate;

public class EditComputerServlet extends HttpServlet{
	
	private ComputerService computerService = ComputerService.getInstance();
	private CompanyService companyService = CompanyService.getInstance();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		setSelectOpt(request);
		
		Optional<Computer> optComputer = computerService.findComputer(Long.parseLong(request.getParameter("id")));
		Computer computer = null;
		if(optComputer.isPresent()) {
			computer = optComputer.get();
			request.setAttribute("computer", computer);
			this.getServletContext().getRequestDispatcher("/WEB-INF/views/editComputer.jsp").forward(request, response);
		}else {
			//erreur
		}
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		setSelectOpt(request);
		
		ComputerValidate computer = new ComputerValidate(
				request.getParameter("computerName"), 
				request.getParameter("introduced"), 
				request.getParameter("discontinued"), 
				request.getParameter("companyId")
				);
		
		computer.setId(Integer.parseInt(request.getParameter("companyId")));
		if(Validator.validateComputer(computer)) {
			ComputerService.updateComputer(
					computer.getId(),
					computer.getName(), 
					computer.getIntroduced(), 
					computer.getDiscontinued(), 
					computer.getCompanyId()
					);
		}else {
			request.setAttribute("error", "We could not create the computer");
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/editComputer.jsp").forward(request, response);
	}
	
	private void setSelectOpt(HttpServletRequest request) {
		ArrayList<Company> companies = companyService.findAllCompanies();
		request.setAttribute("companies", companies);
	}
}
