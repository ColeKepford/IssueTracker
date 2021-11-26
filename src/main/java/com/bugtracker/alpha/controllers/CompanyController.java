package com.bugtracker.alpha.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.bugtracker.alpha.dtos.CompanyDto;
import com.bugtracker.alpha.dtos.DtoConverter;
import com.bugtracker.alpha.entities.Company;
import com.bugtracker.alpha.services.CompanyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Companies")
@CrossOrigin(origins="http://localhost:3000")
public class CompanyController {
  private final CompanyService companyService;
  private DtoConverter dtoConverter;

  @Autowired
    public CompanyController(CompanyService companyService, DtoConverter dtoConverter) {
      this.companyService = companyService;
      this.dtoConverter = dtoConverter;
    }

    @GetMapping("/allCompanies")
    public List<Company> getAllCompanies() {
      return companyService.getAllCompanies();
    }

    @GetMapping("/id/{id}")
    public Company getCompany(@PathVariable("id")long id) {
      return companyService.getCompanyById(id);
    }

    @GetMapping("/companyName/{name}")
    public List<Company> getCompanyByName(@PathVariable String name) {
      return companyService.getByCompanyName(name);
    }

    @PostMapping(path="/registerCompany", consumes = "application/json", produces = "application/json")
    public void addNewCompany(@RequestBody Company company) {
      companyService.addNewCompany(company);
    }

    @PutMapping(path="/deleteCompany")
    public void deleteCompany(@RequestBody Company company) {
      companyService.deleteCompany(company);
    }

    @PutMapping(path="/updateCompany")
    public void updateCompany(@RequestBody Company company) {
      companyService.updateCompany(company);
    }
}
