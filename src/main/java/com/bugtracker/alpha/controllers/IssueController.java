package com.bugtracker.alpha.controllers;

import java.util.List;

import com.bugtracker.alpha.entities.Issue;
import com.bugtracker.alpha.services.IssueService;

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

  @Autowired
  public IssueController(IssueService issueService) {
    this.issueService = issueService;
  }

  @GetMapping("/allIssues")
  public List<Issue> getAllIssues(){
    return issueService.getAllIssues();
  }

  @GetMapping("/getIssue/{id}")
  public Issue getIssue(@PathVariable("id")Integer id) {
    return issueService.getIssue(id);
  }

  @GetMapping("/getByCompany/{id}")
  public List<Issue> getIssuesByCompany(@PathVariable("id")Integer id) {
    return issueService.findByCompany(id);
  }

  @GetMapping("/findByTitle/{title}")
  public List<Issue> findByTitle(@PathVariable("title")String title) {
    return issueService.findByTitle(title);
  }

  @GetMapping("/findByTitleUser/{title}&{id}")
  public List<Issue> findByTitleForUser(@PathVariable("title")String title, @PathVariable("id")Integer id) {
    return issueService.findByTitleForUser(title, id);
  }

  @GetMapping("/findBySeverity/{severity}")
  public List<Issue> findBySeverity(@PathVariable("severity")String severity) {
    return issueService.findBySeverity(severity);
  }

  @GetMapping("/findBySeverity/{severity}&{id}")
  public List<Issue> findBySeverity(@PathVariable("severity")String severity, @PathVariable("id")Integer id) {
    return issueService.findBySeverityForUser(severity, id);
  }

  @GetMapping("/findByState/{state}")
  public List<Issue> findByState(@PathVariable("state")String state) {
    return issueService.findByState(state);
  }

  @GetMapping("/findByState/{state}&{id}")
  public List<Issue> findByState(@PathVariable("state")String state, @PathVariable("id")Integer id) {
    return issueService.findByStateForUser(state, id);
  }

  @PostMapping(path="/newIssue", consumes="application/json", produces="application/json")
  public void addIssue(@RequestBody Issue issue) {
    issueService.addNewIssue(issue);
  }

  @PutMapping(path="/deleteIssue")
  public void deleteIssue(@RequestBody Issue issue) {
    issueService.deleteIssue(issue);
  }

  @PutMapping(path="/updateIssue")
  public void updateIssue(@RequestBody Issue issue) {
    issueService.updateIssue(issue);
  }
  
}
