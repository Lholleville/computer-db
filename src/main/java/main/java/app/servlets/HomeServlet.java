package main.java.app.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.app.model.Computer;
import main.java.app.service.ComputerService;


public class HomeServlet extends HttpServlet {

	private ComputerService computerService = ComputerService.getInstance();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long nbComputers = computerService.getNbComputers();
		
		ArrayList<Computer> computers = computerService.findAllComputers();
		
		request.setAttribute("nbComputers", nbComputers);
		request.setAttribute("computers", computers);
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
	}
}
