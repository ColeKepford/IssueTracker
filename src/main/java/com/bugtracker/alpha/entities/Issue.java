package com.bugtracker.alpha.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="issue")
public class Issue {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long issue_id;
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
  private LocalDateTime date_created;
  
  @DateTimeFormat(pattern = "YYYY-MM-DD hh:mm:ss")
  private LocalDateTime date_resolved;

  @ManyToMany(fetch = FetchType.LAZY, 
    cascade =  {CascadeType.ALL}
  )
  @JoinTable(name = "issue_assigned_users",
      joinColumns = @JoinColumn(name = "issue", nullable = false, updatable = false),
      inverseJoinColumns = @JoinColumn(name = "user", nullable = false, updatable = false))
  private Set<User> assignedUsers = new HashSet<>();


  public Issue() {
  }

  public Issue(String title, String description, String severity, Company company, String type, String state, User creator, LocalDateTime date_created/*, User ... assignedUsers*/)  {
    this.title = title;
    this.description = description;
    this.severity = severity;
    this.company = company;
    this.type = type;
    this.state = state;
    this.creator = creator;
    this.date_created = date_created;
    /*this.assignedUsers = Stream.of(assignedUsers).collect(Collectors.toSet());
    this.assignedUsers.forEach(x -> x.getIssues().add(this));*/
  }

  public long getIssue_id() {
    return Long.valueOf(this.issue_id);
  }

  public void setIssue_id(long issue_id) {
    this.issue_id = Long.valueOf(issue_id);
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
    this.company.setCompany_id(company.getCompany_id());
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

  public String getState() {
    return this.state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public Set<User> getUsers() {
    return this.assignedUsers;
  }


  public LocalDateTime getDate_created() {
    return this.date_created;
  }

  public void setDate_created(LocalDateTime date_created) {
    this.date_created = date_created;
  }

  public LocalDateTime getDate_resolved() {
    return this.date_resolved;
  }

  public void setDate_resolved(LocalDateTime date_resolved) {
    this.date_resolved = date_resolved;
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
  }

  @Override
  public String toString() {
    return "{" +
      " issue_id='" + getIssue_id() + "'" +
      ", title='" + getTitle() + "'" +
      ", description='" + getDescription() + "'" +
      ", severity='" + getSeverity() + "'" +
      ", company='" + getCompany() + "'" +
      ", type='" + getType() + "'" +
      ", state='" + getState() + "'" +
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
        return issue_id == issue.issue_id && Objects.equals(title, issue.title) && Objects.equals(description, issue.description) && Objects.equals(severity, issue.severity) && Objects.equals(company, issue.company) && Objects.equals(type, issue.type) && Objects.equals(state, issue.state) && Objects.equals(creator, issue.creator) && Objects.equals(date_created, issue.date_created) && Objects.equals(date_resolved, issue.date_resolved) && Objects.equals(assignedUsers, issue.assignedUsers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(issue_id, title, description, severity, company, type, state, creator, date_created, date_resolved);
  }

}
