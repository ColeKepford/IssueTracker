package com.bugtracker.alpha.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="issue")
public class Issue {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "issue_id")
  private long issueId;
  private String title;
  private String description;
  private String severity;

  @JoinColumn(name = "company", referencedColumnName = "company_id")
  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  private Company company;
  
  private String type;
  private String state;

  
  @JoinColumn(name = "creator", referencedColumnName = "user_id")
  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  private User creator;
  
  @DateTimeFormat(pattern = "YYYY-MM-DD hh:mm:ss")
  @Column(name = "date_created")
  private LocalDateTime dateCreated;
  
  @DateTimeFormat(pattern = "YYYY-MM-DD hh:mm:ss")
  @Column(name = "date_resolved")
  private LocalDateTime dateResolved;

  /*@ManyToMany(
    fetch = FetchType.LAZY, 
    cascade = {CascadeType.MERGE, 
      CascadeType.PERSIST}, mappedBy = "issues"
  )
  @JsonIgnore
  private Set<User> assignedUsers = new HashSet<>();*/


  public Issue() {
  }

  public Issue(String title, String description, String severity, Company company, String type, String state, User creator, LocalDateTime dateCreated/*, User ... assignedUsers*/)  {
    this.title = title;
    this.description = description;
    this.severity = severity;
    this.company = company;
    this.type = type;
    this.state = state;
    this.creator = creator;
    this.dateCreated = dateCreated;
    /*this.assignedUsers = Stream.of(assignedUsers).collect(Collectors.toSet());
    this.assignedUsers.forEach(x -> x.getIssues().add(this));*/
  }

  public long getIssueId() {
    return Long.valueOf(this.issueId);
  }

  public void setIssueId(long issueId) {
    this.issueId = Long.valueOf(issueId);
  }

  public String getTitle() {
    return String.valueOf(this.title);
  }

  public void setTitle(String title) {
    this.title = String.valueOf(title);
  }

  public String getDescription() {
    return String.valueOf(this.description);
  }

  public void setDescription(String description) {
    this.description = String.valueOf(description);
  }

  public String getSeverity() {
    return String.valueOf(this.severity);
  }

  public void setSeverity(String severity) {
    this.severity = String.valueOf(severity);
  }

  public Company getCompany() {
    return new Company(this.company.getName());
  }

  public void setCompany(Company company) {
    this.company.setCompanyId(company.getCompanyId());
    this.company.setName(company.getName());
  }

  public String getType() {
    return String.valueOf(this.type);
  }

  public void setType(String type) {
    this.type = type;
  }

  public User getCreator() {
    return this.creator;
  }

  public void setCreator(User creator) {
    this.creator = creator;
  }

  public String getState() {
    return this.state;
  }

  public void setState(String state) {
    this.state = state;
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

  /*public Set<User> getAssignedUsers() {
    return this.assignedUsers;
  }

  public void assignUser(User user) {
    if(!assignedUsers.contains(user)) {
      assignedUsers.add(user);
      user.assignIssue(this);
    }
  }

  public void removeUser(User user) {
    if(assignedUsers.contains(user)) {
      assignedUsers.remove(user);
      user.removeIssue(this);
    }
  }*/


  @Override
  public String toString() {
    return "{" +
      " issueId='" + getIssueId() + "'" +
      ", title='" + getTitle() + "'" +
      ", description='" + getDescription() + "'" +
      ", severity='" + getSeverity() + "'" +
      ", company='" + getCompany() + "'" +
      ", type='" + getType() + "'" +
      ", state='" + getState() + "'" +
      ", creator='" + getCreator().getEmail() + "'" +
      ", dateCreated='" + getDateCreated() + "'" +
      ", dateResolved='" + getDateResolved() + "'" +
      "}";
  }
  

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Issue)) {
            return false;
        }
        Issue issue = (Issue) o;
        return issueId == issue.issueId && Objects.equals(title, issue.title) && Objects.equals(description, issue.description) && Objects.equals(severity, issue.severity) && Objects.equals(company, issue.company) && Objects.equals(type, issue.type) && Objects.equals(state, issue.state) && Objects.equals(creator, issue.creator) && Objects.equals(dateCreated, issue.dateCreated) && Objects.equals(dateResolved, issue.dateResolved);
  }

  @Override
  public int hashCode() {
    return Objects.hash(issueId, title, description, severity, company, type, state, creator, dateCreated, dateResolved);
  }

}
