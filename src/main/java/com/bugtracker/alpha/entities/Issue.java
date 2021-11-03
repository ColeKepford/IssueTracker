package com.bugtracker.alpha.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = "assignedUsers")


@Entity
@Table(name="issue")
public class Issue {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int issue_id;
  private String title;
  private String description;
  private String severity;
  @JoinColumn(name = "company", referencedColumnName = "company_id")
  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  private Company company;
  private String type;
  private String state;
  @DateTimeFormat(pattern = "YYYY-MM-DD hh:mm:ss")
  private LocalDateTime date_created;
  @DateTimeFormat(pattern = "YYYY-MM-DD hh:mm:ss")
  private LocalDateTime date_resolved;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "issue_assigned_users",
      joinColumns = @JoinColumn(name = "issue", referencedColumnName = "issue_id"),
      inverseJoinColumns = @JoinColumn(name = "user", referencedColumnName = "user_id"))
  private Set<User> assignedUsers;


  public Issue() {
  }

  public Issue(String title, String description, String severity, Company company, String type, String state, LocalDateTime date_created, User... assignedUsers) {
    this.title = title;
    this.description = description;
    this.severity = severity;
    this.company = company;
    this.type = type;
    this.state = state;
    this.date_created = date_created;
    this.assignedUsers = Stream.of(assignedUsers).collect(Collectors.toSet());
    this.assignedUsers.forEach(x -> x.getIssues().add(this));
  }


  public int getIssue_id() {
    return this.issue_id;
  }

  public void setIssue_id(int issue_id) {
    this.issue_id = issue_id;
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

  public Company getCompany() {
    return this.company;
  }

  public void setCompany(Company company) {
    this.company = company;
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


}
