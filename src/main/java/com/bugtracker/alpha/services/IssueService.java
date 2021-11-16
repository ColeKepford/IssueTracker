package com.bugtracker.alpha.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bugtracker.alpha.entities.Issue;
import com.bugtracker.alpha.entities.User;
import com.bugtracker.alpha.repositories.IssueRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssueService {
  private final IssueRepository issueRepository;

  @Autowired
  public IssueService(IssueRepository issueRepository) {
    this.issueRepository = issueRepository;
  }

  public Issue getIssue(long id) {
    Optional<Issue> optionalIssue = issueRepository.findById(id);
    if(!optionalIssue.isPresent()) {
      return null;
    }
    else {
      return optionalIssue.get();
    }
  }

  public List<Issue> getAllIssues() {
    Iterable<Issue> iterIssues = issueRepository.findAll();
    ArrayList<Issue> issues = new ArrayList<>();

    iterIssues.forEach(issues::add);
    return issues;
  }

  public List<User> getAllAssignedusers(long id) {
    Optional<List<User>> optionalUsers = issueRepository.findAllAssignedUsers(id);
    if(!optionalUsers.isPresent()) {
      return new ArrayList<>();
    }
    else {
      return optionalUsers.get();
    }
  }

  public List<Issue> findByCompany(long id) {
    Optional<List<Issue>> optionalIssues = issueRepository.findByCompany(id);
    if(!optionalIssues.isPresent()) {
      return new ArrayList<>();
    }
    else {
      return optionalIssues.get();
    }
  }

  public List<Issue> findByTitle(String title) {
    Optional<List<Issue>> optionalIssues = issueRepository.findByTitle(title);
    if(!optionalIssues.isPresent()) {
      return new ArrayList<>();
    }
    else {
      return optionalIssues.get();
    }
  }

  public List<Issue> findByTitleForUser(String title, long id) {
    Optional<List<Issue>> optionalIssues = issueRepository.findByTitleForUser(title, id);
    if(!optionalIssues.isPresent()) {
      return new ArrayList<>();
    }
    else {
      return optionalIssues.get();
    }
  }

  public List<Issue> findBySeverity(String severity) {
    Optional<List<Issue>> optionalIssues = issueRepository.findBySeverity(severity);
    if(!optionalIssues.isPresent()) {
      return new ArrayList<>();
    }
    else {
      return optionalIssues.get();
    }
  }

  public List<Issue> findBySeverityForUser(String severity, long id) {
    Optional<List<Issue>> optionalIssues = issueRepository.findBySeverityForUser(severity, id);
    if(!optionalIssues.isPresent()) {
      return new ArrayList<>();
    }
    else {
      return optionalIssues.get();
    }
  }

  public List<Issue> findByState (String state) {
    Optional<List<Issue>> optionalIssues = issueRepository.findByState(state);
    if(!optionalIssues.isPresent()) {
      return new ArrayList<>();
    }
    else {
      return optionalIssues.get();
    }
  }

  public List<Issue> findByStateForUser(String state, long id) {
    Optional<List<Issue>> optionalIssues = issueRepository.findByStateForUser(state, id);
    if(!optionalIssues.isPresent()) {
      return new ArrayList<>();
    }
    else {
      return optionalIssues.get();
    }
  }

  public void addNewIssue(Issue issue) {
    Optional<Issue> issueOptional = issueRepository.findById(issue.getIssueId());
    if(!issueOptional.isPresent()) {
      issueRepository.save(issue);
      issueRepository.assignUserToIssue(issue.getIssueId(), issue.getCreator().getUserId());
    }
    else {
      //todo logging
    }
  }

  public void updateIssue(Issue issue) {
    Optional<Issue> issueOptional = issueRepository.findById(issue.getIssueId());
    if(issueOptional.isPresent()) {
      issueRepository.updateIssue(issue.getTitle(), issue.getDescription(), issue.getSeverity(), issue.getCompany(), issue.getType(), issue.getState(), issue.getCreator(), issue.getDateCreated(), issue.getDateResolved(), issue.getIssueId());
    }
    else {
      //todo logging
    }
  }

  public void assignUserToIssue(long issueId, long userId) {
    issueRepository.assignUserToIssue(issueId, userId);
  }

  public void deleteIssue(Issue issue) {
    Optional<Issue> issueOptional = issueRepository.findById(issue.getIssueId());
    if(issueOptional.isPresent()) {
      issueRepository.delete(issue);
    }
    else {
      //todo logging
    }
  }
}
