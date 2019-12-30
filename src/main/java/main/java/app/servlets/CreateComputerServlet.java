package main.java.app.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.java.app.model.Company;
import main.java.app.service.CompanyService;
import main.java.app.service.ComputerService;
import main.java.app.validator.Validator;
import main.java.app.validator.modeltovalidate.ComputerValidate;

public class CreateComputerServlet extends HttpServlet {
	
	private CompanyService companyService = CompanyService.getInstance();
	private ComputerService computerService = ComputerService.getInstance();

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		setSelectOpt(request);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		setSelectOpt(request);
	
		ComputerValidate computer = new ComputerValidate(
				request.getParameter("computerName"), 
				request.getParameter("introduced"), 
				request.getParameter("discontinued"), 
				request.getParameter("companyId")
				);
		
		if(Validator.validateComputer(computer)) {
			ComputerService.createComputer(
					computer.getName(), 
					computer.getIntroduced(), 
					computer.getDiscontinued(), 
					computer.getCompanyId()
					);
		}else {
			request.setAttribute("error", "We could not create the computer");
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward( request, response );
	}
	
	private void setSelectOpt(HttpServletRequest request) {
		ArrayList<Company> companies = companyService.findAllCompanies();
		request.setAttribute("companies", companies);
	}
	
}
