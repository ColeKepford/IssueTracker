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
    logs.userRetrievedSuccessfully("Retrieved all companies");

    Iterable<Company> iterable = companyRepository.findAll();
    ArrayList<Company> companies = new ArrayList<>();
    iterable.forEach(companies::add);
    return companies;
  }

  public Company getCompanyById(long id) {
    Optional<Company> optional = companyRepository.findById(id);
    if(!optional.isPresent()) {
      logs.userDoesntExist("Company with id: "+id+" doesnt exsist");
      return null;
    }
    else{
      logs.userRetrievedSuccessfully("Company with id: "+id+" was retrieved");
      return optional.get();
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
        return new ArrayList<>();
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
