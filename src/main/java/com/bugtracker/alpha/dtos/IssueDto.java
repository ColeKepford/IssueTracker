package com.bugtracker.alpha.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

import com.bugtracker.alpha.entities.Issue;
import com.bugtracker.alpha.entities.User;

import org.springframework.format.annotation.DateTimeFormat;

public class IssueDto implements Serializable{
  
  private long issueId;
  private String title;
  private String description;
  private String severity;
  private CompanyDto companyDto;
  private String type;
  private String state;
  private UserDto creatorDto;
  @DateTimeFormat(pattern = "YYYY-MM-DD hh:mm:ss")
  private LocalDateTime dateCreated;
  @DateTimeFormat(pattern = "YYYY-MM-DD hh:mm:ss")
  private LocalDateTime dateResolved;
  private Set<UserDto> assignedUsers;

  public IssueDto() {

  }

  public IssueDto(Issue issue) {
    this.issueId = issue.getIssueId();
    this.title = issue.getTitle();
    this.description = issue.getDescription();
    this.severity = issue.getSeverity();
    this.companyDto = new CompanyDto(issue.getCompany());
    this.type = issue.getType();
    this.state = issue.getState();
    this.creatorDto = new UserDto(issue.getCreator());
    this.dateCreated = issue.getDateCreated();
    this.dateResolved = issue.getDateResolved();

    Iterator<User> iterator = issue.getUsers().iterator();
    UserDto newUser;
    while(iterator.hasNext()) {
      newUser = new UserDto(iterator.next());
      assignedUsers.add(newUser);
    }
  }


  public long getIssueId() {
    return this.issueId;
  }

  public void setIssueId(long issueId) {
    this.issueId = issueId;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getSeverity() {
    return this.severity;
  }

  public void setSeverity(String severity) {
    this.severity = severity;
  }

  public CompanyDto getCompanyDto() {
    return this.companyDto;
  }

  public void setCompanyDto(CompanyDto companyDto) {
    this.companyDto = companyDto;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getState() {
    return this.state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public UserDto getCreatorDto() {
    return this.creatorDto;
  }

  public void setCreatorDto(UserDto creatorDto) {
    this.creatorDto = creatorDto;
  }

  public LocalDateTime getDateCreated() {
    return this.dateCreated;
  }

  public void setDateCreated(LocalDateTime dateCreated) {
    this.dateCreated = dateCreated;
  }

  public LocalDateTime getDateResolved() {
    return this.dateResolved;
  }

  public void setDateResolved(LocalDateTime dateResolved) {
    this.dateResolved = dateResolved;
  }

  public Set<UserDto> getAssignedUsers() {
    return this.assignedUsers;
  }

  public void setAssignedUsers(Set<UserDto> assignedUsers) {
    this.assignedUsers = assignedUsers;
  }

  @Override
  public String toString() {
    return "{" +
      " issueId='" + getIssueId() + "'" +
      ", title='" + getTitle() + "'" +
      ", description='" + getDescription() + "'" +
      ", severity='" + getSeverity() + "'" +
      ", companyDto='" + getCompanyDto().toString() + "'" +
      ", type='" + getType() + "'" +
      ", state='" + getState() + "'" +
      ", creatorDto='" + getCreatorDto().toString() + "'" +
      ", dateCreated='" + getDateCreated() + "'" +
      ", dateResolved='" + getDateResolved() + "'" +
      ", assignedUsers='" + getAssignedUsers().toString() + "'" +
      "}";
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof IssueDto)) {
            return false;
        }
        IssueDto issueDto = (IssueDto) o;
        return issueId == issueDto.issueId && Objects.equals(title, issueDto.title) && Objects.equals(description, issueDto.description) && Objects.equals(severity, issueDto.severity) && Objects.equals(companyDto, issueDto.companyDto) && Objects.equals(type, issueDto.type) && Objects.equals(state, issueDto.state) && Objects.equals(creatorDto, issueDto.creatorDto) && Objects.equals(dateCreated, issueDto.dateCreated) && Objects.equals(dateResolved, issueDto.dateResolved) && Objects.equals(assignedUsers, issueDto.assignedUsers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(issueId, title, description, severity, companyDto, type, state, creatorDto, dateCreated, dateResolved, assignedUsers);
  }


}
