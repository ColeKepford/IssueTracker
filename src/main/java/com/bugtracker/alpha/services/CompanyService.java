package com.bugtracker.alpha.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bugtracker.alpha.controllers.LoggingController;
import com.bugtracker.alpha.entities.Company;
import com.bugtracker.alpha.repositories.CompanyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
  
  private final CompanyRepository companyRepository;
  private final LoggingController logs;

  @Autowired
  public CompanyService(CompanyRepository companyRepository, LoggingController logs) {
    this.companyRepository = companyRepository;
    this.logs  = logs;
  }

  public List<Company> getAllCompanies() {
    logs.info("Retrieved all companies");

    Iterable<Company> iterable = companyRepository.findAll();
    ArrayList<Company> companies = new ArrayList<>();
    iterable.forEach(companies::add);
    return companies;
  }

  public Company getCompanyById(long id) {
    Optional<Company> optional = companyRepository.findById(id);
    if(!optional.isPresent()) {
      logs.info("Company with id: "+id+" doesnt exsist.");
      return null;
    }
    else{
      logs.info("Company retrieved.");
      return optional.get();
    }
  }

  public List<Company> getByCompanyName(String name) {
    Optional<List<Company>> companyOptional = companyRepository.findByCompanyName(name);
    if(companyOptional.isPresent()) {
      logs.info("Companies with given name retrieved."); 
      return companyOptional.get();
    }
    else {
      logs.info("Companies with given name don't exist");
        return new ArrayList<>();
    }
  }

  public void addNewCompany(Company company) {
    Optional<Company> companyOptional = companyRepository.findById(company.getCompanyId());
    if(companyOptional.isPresent()) {
      logs.info("Company already exists.");
    }
    else {
      companyRepository.save(company);
      logs.info("Company Succesfully Added");
    }
  }

  public void updateCompany(Company company) {
    Optional<Company> companyOptional = companyRepository.findById(company.getCompanyId());
    if(companyOptional.isPresent()) {
      companyRepository.updateCompany(company.getName(), company.getCompanyId());
    }
    else {
      logs.error("Unable to update Company");
    }
  }

  public void deleteCompany(Company company) {
    Optional<Company> companyOptional = companyRepository.findById(company.getCompanyId());
    if(companyOptional.isPresent()) {
      companyRepository.delete(company);
    }
    else {
      logs.error("Unable to delete Company");
    }
  }
}
