package com.bugtracker.alpha.companies;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bugtracker.alpha.logging.LoggingController;

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
    logs.userRetrievedSuccessfully("Retrieved all companies");
    return companyRepository.findAll();
  }

  public Company getCompanyById(int id) {
    boolean exists = companyRepository.existsById(id);
    if(!exists) {
      logs.userDoesntExist("Company with id: "+id+" doesnt exsist");
      return null;
    }
    else{
      logs.userRetrievedSuccessfully("Company with id: "+id+" was retrieved");
      return companyRepository.getById(id);
    }
  }

  public List<Company> getByCompanyName(String name) {
    Optional<List<Company>> companyOptional = companyRepository.findByCompanyName(name);
    if(companyOptional.isPresent()) {
      logs.companyRetrievedSuccesfully("Companies containing: " + name + " retrieved succesfully"); 
      return companyOptional.get();
    }
    else {
      logs.companyDoesntExist("Companies with name: " + name + "don't exist");
        return new ArrayList<Company>();
    }
  }

  public void addNewCompany(Company company) {
    Optional<Company> companyOptional = companyRepository.findById(company.getCompany_id());
    if(companyOptional.isPresent()) {
      logs.companyDoesExist("Company with id: " + company.getCompany_id() + " already exists");
    }
    else {
      companyRepository.save(company);
      logs.companyAdded("Company: " + company.getName() + " was succesfully added");
    }
  }
}
