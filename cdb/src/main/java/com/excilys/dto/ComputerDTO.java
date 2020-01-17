package com.excilys.dto;

public class ComputerDTO {
	
	private long idComputer;
	private String name;
	private String introducedDate;
	private String discontinuedDate;
	private CompanyDTO companyDTO;

	
	private ComputerDTO(ComputerDTOBuilder builder) {
		this.idComputer = builder.builderComputerDTOId;
		this.name = builder.builderComputerDTOName;
		this.introducedDate = builder.builderComputerDTOIntroducedDate;
		this.discontinuedDate = builder.builderComputerDTODiscontinuedDate;
		this.companyDTO = builder.builderComputerDTOCompanyDTO;
	}
	
	public static class ComputerDTOBuilder {
		
		private long builderComputerDTOId;
		private String builderComputerDTOName;
		private String builderComputerDTOIntroducedDate;
		private String builderComputerDTODiscontinuedDate;
		private CompanyDTO builderComputerDTOCompanyDTO;
		
		public ComputerDTOBuilder() {};
		
    	public ComputerDTOBuilder buildComputerDTOId(long idComputer) {
    		this.builderComputerDTOId = idComputer;
    		return this;
    	}
    	public ComputerDTOBuilder buildComputertDTOName(String name) {
    		this.builderComputerDTOName = name;
    		return this;
    	}
    	public ComputerDTOBuilder buildComputerDTOIntroducedDate(String introducedDate) {
    		this.builderComputerDTOIntroducedDate = introducedDate;
    		return this;
    	}
    	public ComputerDTOBuilder buildComputerDTODiscontinuedDate(String discontinuedDate) {
    		this.builderComputerDTODiscontinuedDate = discontinuedDate;
    		return this;
    	}
    	public ComputerDTOBuilder buildComputerDTOCompany(CompanyDTO companyDTO) {
    		this.builderComputerDTOCompanyDTO = companyDTO;
    		return this;
    	}
    	
    	public ComputerDTO build() {
    		return new ComputerDTO(this);
    	}
	}

	public long getIdComputer() {
		return idComputer;
	}

	public void setIdComputer(long idComputer) {
		this.idComputer = idComputer;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getIntroducedDate() {
		return introducedDate;
	}


	public void setIntroducedDate(String introducedDate) {
		this.introducedDate = introducedDate;
	}


	public String getDiscontinuedDate() {
		return discontinuedDate;
	}


	public void setDiscontinuedDate(String discontinuedDate) {
		this.discontinuedDate = discontinuedDate;
	}


	public CompanyDTO getCompanyDTO() {
		return companyDTO;
	}


	public void setCompanyDTO(CompanyDTO companyDTO) {
		this.companyDTO = companyDTO;
	}


	public String toString() {
		return "Computer [idComputerDTO=" + idComputer + ", nameComputerDTO=" + name + ", introducedDateComputer="
				+ introducedDate + ", discontinuedDateComputerDTO=" + discontinuedDate
				+", companyDTO=" + companyDTO + "]";
	}
}
