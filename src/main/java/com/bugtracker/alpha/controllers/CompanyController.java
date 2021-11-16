package com.bugtracker.alpha.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.bugtracker.alpha.dtos.CompanyDto;
import com.bugtracker.alpha.entities.Company;
import com.bugtracker.alpha.services.CompanyService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companies")
@CrossOrigin(origins="http://localhost:3000")
public class CompanyController {
  private final CompanyService companyService;
  private ModelMapper modelMapper;

  @Autowired
    public CompanyController(CompanyService companyService, ModelMapper modelMapper) {
      this.companyService = companyService;
      this.modelMapper = modelMapper;
    }

    @GetMapping("/allCompanies")
    public List<CompanyDto> getAllCompanies() {
      List<Company> companies = companyService.getAllCompanies();
      return companies.stream()
      .map(this::convertToDto)
      .collect(Collectors.toList());
    }

    @GetMapping("/id/{id}")
    public CompanyDto getCompany(@PathVariable("id")long id) {
      return convertToDto(companyService.getCompanyById(id));
    }

    @GetMapping("/companyName/{name}")
    public List<CompanyDto> getCompanyByName(@PathVariable String name) {
      List<Company> companies = companyService.getByCompanyName(name);
      return companies.stream()
      .map(this::convertToDto)
      .collect(Collectors.toList());
    }

    @PostMapping(path="/registerCompany", consumes = "application/json", produces = "application/json")
    public void addNewCompany(@RequestBody CompanyDto company) {
      companyService.addNewCompany(convertToEntity(company));
    }

    @PutMapping(path="/deleteCompany")
    public void deleteCompany(@RequestBody CompanyDto company) {
      companyService.deleteCompany(convertToEntity(company));
    }

    @PutMapping(path="/updateCompany")
    public void updateCompany(@RequestBody CompanyDto company) {
      companyService.updateCompany(convertToEntity(company));
    }

    private CompanyDto convertToDto(Company company) {
      return new CompanyDto(company);
    }

    private Company convertToEntity(CompanyDto companyDto) {
      return new Company(companyDto.getCompanyId(), companyDto.getName());
    }
}
