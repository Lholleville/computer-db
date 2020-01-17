package com.excilys.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.ComputerMapper;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.pagination.Page;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;
import com.excilys.validation.ValidationFront;


@Controller
public class ComputerController {
	
	private ComputerService computerService;

	private  CompanyService companyService;

	private ComputerMapper computerMapper;

	private Page page;
	
	public ComputerController (ComputerService serviceComputer, CompanyService serviceCompany, ComputerMapper computerMapper, Page page) {
		this.computerService = serviceComputer;
		this.companyService = serviceCompany;
		this.computerMapper = computerMapper;
		this.page = page;
	}
	
	@GetMapping("/")
	public String getDashboard( @RequestParam(required = false, defaultValue = "0") int limit,
								@RequestParam(required = false, defaultValue = "1") int pageNumero, 
								@RequestParam(required = false, defaultValue = "") String search, 
								Model model) {

		int offset = 0, currentPage = 1, nbComputer = 0, nbPage = 0;
		currentPage = pageNumero;
		offset = page.calculOffset(currentPage);

		if(limit != 0) {
			page.setLimite(limit);	
		}	
		
		if(search != null) {
			List<Computer> listComputer = computerService.searchComputerByName(page.getLimite(), offset, search);
			nbComputer= listComputer.size();
			nbPage = page.nbPageTotal(nbComputer);
			model.addAttribute("listComputer", listComputer);
			model.addAttribute("search", search);
		} else {
			List<Computer> listComputer = computerService.findAllComputers(page.getLimite(), offset);
			nbComputer = listComputer.size();
			nbPage = page.nbPageTotal(nbComputer);
			model.addAttribute("listComputer", listComputer);
		}
		
		model.addAttribute("nbComputer", nbComputer);
		model.addAttribute("nbPage", nbPage);
		model.addAttribute("currentPage", currentPage);
	
		return "dashboard";
	}
	
	@PostMapping("/")
	public String postDashboard(@RequestParam(required = false, defaultValue = "") String selection) {
		
		String [] checkboxTableau = selection.split(",");
		for(String id : checkboxTableau) {
			Computer computer = computerService.findOneComputer(Long.parseLong(id));
			computerService.deleteOneComputer(computer);
		}
		return "redirect:/";
	}
	
	@GetMapping("/addcomputer")
	public String getAddComputer(Model model) {
		List<Company> listCompany = companyService.findAllCompanies();
		model.addAttribute("listCompany", listCompany);
		return "addComputer";
	}
	
	@PostMapping("/addcomputer")
	public String postAddComputer(@RequestParam(required = false) String computerName, 
								  @RequestParam(required = false, defaultValue = "") String introduced,
								  @RequestParam(required = false, defaultValue = "") String discontinued,
								  @RequestParam(required = false) int companyId,
								  Model model) {

		Boolean ValidationNameIsEmpty = ValidationFront.verificationNameComputerIsEmpty(computerName);	
		if(!ValidationNameIsEmpty) {

			ComputerDTO computerDTO = new ComputerDTO.ComputerDTOBuilder().buildComputertDTOName(computerName)
										.buildComputerDTOIntroducedDate(introduced)
										.buildComputerDTODiscontinuedDate(discontinued)
										.buildComputerDTOCompany(new CompanyDTO.CompanyDTOBuilder().buildCompanyDTOId(companyId).build())
										.build();
			
			computerService.createOneComputer(computerMapper.computerDTOToComputer(computerDTO));
			return "redirect:/";
			
		} else {
			List<Company> listCompany = companyService.findAllCompanies();
			model.addAttribute("listCompany", listCompany);
			model.addAttribute("ValidationNameIsEmpty", ValidationNameIsEmpty);
			return "addComputer";
		}
	}
	
	@GetMapping("/editcomputer")
	public String getEditComputer(@RequestParam(required = false) int computer_id, Model model) {

		Computer computer = computerService.findOneComputer(computer_id);
		List<Company> listCompany = companyService.findAllCompanies();
		model.addAttribute("listCompany", listCompany);
		model.addAttribute("computer", computer);

		return "editComputer";
		
	}

	@PostMapping("/editcomputer")
	public String postEditComputer(@RequestParam(required = false, defaultValue = "") String computerName,
								   @RequestParam(required = false, defaultValue = "") String introduced,
								   @RequestParam(required = false, defaultValue = "") String discontinued,
								   @RequestParam(required = false) int companyId,
								   @RequestParam(required = false) int id) {

		ComputerDTO computerDTO = new ComputerDTO.ComputerDTOBuilder()
				.buildComputerDTOId(id)
				.buildComputertDTOName(computerName)
				.buildComputerDTOIntroducedDate(introduced)
				.buildComputerDTODiscontinuedDate(discontinued)
				.buildComputerDTOCompany(new CompanyDTO.CompanyDTOBuilder()
						.buildCompanyDTOId(companyId)
						.build())
				.build();
		
        computerService.updateOneComputer(computerMapper.computerDTOToComputer(computerDTO)); 
        
        return "redirect:/";
        
	}

}
