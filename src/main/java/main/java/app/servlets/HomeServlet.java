package main.java.app.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.app.comparator.NameComparator;
import main.java.app.model.Computer;
import main.java.app.service.ComputerService;


public class HomeServlet extends HttpServlet {

	private ComputerService computerService = ComputerService.getInstance();
	private int page = 1;
	private int show = 10;
	private int nbComputers = 0;
	private int nbComputersTotal = 0;
	private int nbPages = 0;
	private ArrayList<Computer> computers;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String selection = request.getParameter("selection");
		
		String ids[] = selection.split(",");
		
		for(String id : ids) {
			System.out.println(id);
			computerService.deleteComputer(Long.parseLong(id));
		}
		
		this.doGet(request, response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.nbComputersTotal = computerService.getNbComputers();
		paginate(request.getParameter("page"), request.getParameter("show"));
		this.computers = computerService.findComputerPaginate(this.page, this.show);
		
		if(request.getParameter("search") != null) {
			this.computers = computerService.findComputerByName(request.getParameter("search"));
		}
		
		if(request.getParameter("order") != null) {
			Collections.sort(this.computers, new NameComparator());
		}
		
		this.nbComputers = computers.size();
		request.setAttribute("nbComputers", this.nbComputersTotal);
		request.setAttribute("computers", this.computers);
		request.setAttribute("nbPages", this.nbPages);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp?page="+page+"&show="+show).forward(request, response);
	}
	
	private void paginate(String pageGET, String showGET) {
		
		if(showGET != null && !showGET.isEmpty()) {
			this.show  = Integer.parseInt(showGET);
			this.show = (this.show <= 0) ? 1 : this.show;
			this.nbPages = (int)this.nbComputersTotal / this.show;
		}
		
		if(pageGET != null && !pageGET.isEmpty()) {
			page = Integer.parseInt(pageGET);
			page = page <= 0 ? 1: page;
		}
	}
}
