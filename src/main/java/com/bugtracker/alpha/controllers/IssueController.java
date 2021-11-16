package com.bugtracker.alpha.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.bugtracker.alpha.dtos.IssueDto;
import com.bugtracker.alpha.entities.Company;
import com.bugtracker.alpha.entities.Issue;
import com.bugtracker.alpha.services.IssueService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/issues")
@CrossOrigin(origins="http://localhost:3000")
public class IssueController {
  private final IssueService issueService;
  private final ModelMapper modelMapper;

  @Autowired
  public IssueController(IssueService issueService, ModelMapper modelMapper) {
    this.issueService = issueService;
    this.modelMapper = modelMapper;
  }

  @GetMapping("/allIssues")
  public List<IssueDto> getAllIssues(){
    List<Issue> issues = issueService.getAllIssues();
    return issues.stream().map(this::convertToDto).collect(Collectors.toList());
  }

  @GetMapping("/getIssue/{id}")
  public IssueDto getIssue(@PathVariable("id")long id) {
    return convertToDto(issueService.getIssue(id));
  }

  @GetMapping("/getByCompany/{id}")
  public List<IssueDto> getIssuesByCompany(@PathVariable("id")long id) {
    List<Issue> issues = issueService.findByCompany(id);
    return issues.stream().map(this::convertToDto).collect(Collectors.toList());
  }

  @GetMapping("/findByTitle/{title}")
  public List<IssueDto> findByTitle(@PathVariable("title")String title) {
    List<Issue> issues = issueService.findByTitle(title);
    return issues.stream().map(this::convertToDto).collect(Collectors.toList());
  }

  @GetMapping("/findByTitleUser/{title}&{id}")
  public List<IssueDto> findByTitleForUser(@PathVariable("title")String title, @PathVariable("id")long id) {
    List<Issue> issues = issueService.findByTitleForUser(title, id);
    return issues.stream().map(this::convertToDto).collect(Collectors.toList());
  }

  @GetMapping("/findBySeverity/{severity}")
  public List<IssueDto> findBySeverity(@PathVariable("severity")String severity) {
    List<Issue> issues = issueService.findBySeverity(severity);
    return issues.stream().map(this::convertToDto).collect(Collectors.toList());
  }

  @GetMapping("/findBySeverity/{severity}&{id}")
  public List<IssueDto> findBySeverity(@PathVariable("severity")String severity, @PathVariable("id")long id) {
    List<Issue> issues = issueService.findBySeverityForUser(severity, id);
    return issues.stream().map(this::convertToDto).collect(Collectors.toList());
  }

  @GetMapping("/findByState/{state}")
  public List<IssueDto> findByState(@PathVariable("state")String state) {
    List<Issue> issues = issueService.findByState(state);
    return issues.stream().map(this::convertToDto).collect(Collectors.toList());
  }

  @GetMapping("/findByState/{state}&{id}")
  public List<IssueDto> findByStateForUser(@PathVariable("state")String state, @PathVariable("id") long id) {
    List<Issue> issues = issueService.findByStateForUser(state, id);
    return issues.stream().map(this::convertToDto).collect(Collectors.toList());
  }

  @PostMapping(path="/newIssue", consumes="application/json", produces="application/json")
  public void addIssue(@RequestBody IssueDto issueDto) {
    issueService.addNewIssue(convertToEntity(issueDto));
  }

  /*@PostMapping(path="/assignIssue/{issue_id}&{user_id}")
  public void assignIssue(@PathVariable("issue_id")long issue_id, @PathVariable("user_id")long user_id) {
    issueService.assignUserToIssue(issue_id, user_id);
  }*/

  @PutMapping(path="/deleteIssue")
  public void deleteIssue(@RequestBody IssueDto issueDto) {
    issueService.deleteIssue(convertToEntity(issueDto));
  }

  @PutMapping(path="/updateIssue")
  public void updateIssue(@RequestBody IssueDto issueDto) {
    issueService.updateIssue(convertToEntity(issueDto));
  }
  
  private IssueDto convertToDto(Issue issue) {
    return new IssueDto(issue);
  }

  private Issue convertToEntity(IssueDto issueDto) {
    Company company = new Company(issueDto.getCompanyDto().getCompanyId(), issueDto.getCompanyDto().getName());
    return new IssueDto(issueDto.getIssueId(), issueDto.getTitle(), issueDto.getDescription(), issueDto.getSeverity(), company, issueDto.getType(), issueDto.getState(),
  }
}
