package com.excilys.dto;


public class CompanyDTO {
	
	private long idCompany;
	private String nameCompany;
	
	private CompanyDTO(CompanyDTOBuilder builder) {
		this.idCompany = builder.builderCompanyDTOId;
		this.nameCompany = builder.builderCompanyDTOName;
	} 

	public long getIdCompany() {
		return idCompany;
	}
	
	public void setIdCompany(int idCompany) {
		this.idCompany = idCompany;
	}
	
	public String getNameCompany() {
		return nameCompany;
	}
	
	public void setNameCompany(String nameCompany) {
		this.nameCompany = nameCompany;
	}


	public String toString() {
		return "CompanyDTO [idCompanyDTO=" + idCompany + ", nameCompanyDTO=" + nameCompany + "]";
	}

	public static class CompanyDTOBuilder {
    	
    	private long builderCompanyDTOId;
    	private String builderCompanyDTOName;
    	
    	public CompanyDTOBuilder() {};
    	
    	public CompanyDTOBuilder buildCompanyDTOId(long idCompany) {
    		this.builderCompanyDTOId = idCompany;
    		return this;
    	}
    	public CompanyDTOBuilder buildCompanyDTOName(String nameCompany) {
    		this.builderCompanyDTOName = nameCompany;
    		return this;
    	}
    	
    	public CompanyDTO build() {
    		return new CompanyDTO(this);
    	}
    }

}
