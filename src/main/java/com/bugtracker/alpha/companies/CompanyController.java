package com.bugtracker.alpha.companies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Companies")
@CrossOrigin(origins="http://localhost:3000")
public class CompanyController {
  private final CompanyService companyService;

  @Autowired
    public CompanyController(CompanyService companyService) {
      this.companyService = companyService;
    }

    @GetMapping("/allCompanies")
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/id/{id}")
    public Company getCompany(@PathVariable("id")Integer id) {
        return companyService.getCompanyById(id);
    }

    @GetMapping("/Companyname/{name}")
    public List<Company> getCompanyByEmail(@PathVariable String name) {
        return companyService.getByCompanyName(name);
    }

    @PostMapping(path="/registerCompany", consumes = "application/json", produces = "application/json")
    public void addNewCompany(@RequestBody Company company) {
        companyService.addNewCompany(company);
    }
}
