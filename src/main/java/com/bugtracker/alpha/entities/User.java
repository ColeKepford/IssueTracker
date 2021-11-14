package com.bugtracker.alpha.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="user_id")
  private long userId;
  private String email;
  private String password;
  @Column(name="first_name")
  private String firstName;
  @Column(name="last_name")
  private String lastName;
  @JoinColumn(name = "company", referencedColumnName = "company_id")
  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  private Company company;
  private String address;
  @Column(name="postal_code")
  private String postalCode;
  private String province;
  private String country;
  @Column(name="phone_num")
  private String phoneNum;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Column(name="created_time")
  private String createdTime;
  @JoinColumn(name = "role", referencedColumnName = "role_id")
  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  private Role role;
  @ManyToMany(fetch = FetchType.EAGER,
    cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    },
    mappedBy = "assignedUsers")
  private Set<Issue> issues = new HashSet<>();

  public User() {

  }

  public User(String email, String password, String firstName, String lastName, Company company, String address,
      String postalCode, String province, String country, String phoneNum, String createdTime, Role role) {
    this.email = email;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.company = company;
    this.address = address;
    this.postalCode = postalCode;
    this.province = province;
    this.country = country;
    this.phoneNum = phoneNum;
    this.createdTime = createdTime;
    this.role = role;
  }

  public long getUserId() {
    return this.userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Company getCompany() {
    return this.company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

  public String getAddress() {
    return this.address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPostalCode() {
    return this.postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getProvince() {
    return this.province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getCountry() {
    return this.country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getPhoneNum() {
    return this.phoneNum;
  }

  public void setPhoneNum(String phoneNum) {
    this.phoneNum = phoneNum;
  }

  public String getCreatedTime() {
    return this.createdTime;
  }

  public void setCreatedTime(String createdTime) {
    this.createdTime = createdTime;
  }

  public Role getRole() {
    return this.role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public Set<Issue> getIssues() {
    return this.issues;
  }

  public void setIssues(Set<Issue> issues) {
    this.issues = issues;
  }

  public void assignIssue(Issue issue) {
    if(!issues.contains(issue)) {
      issues.add(issue);
      issue.assignUser(this);
    }
  }

  public void removeIssue(Issue issue) {
    if(issues.contains(issue)) {
      issues.remove(issue);
      issue.removeUser(this);
    }
  }

  @Override
  public String toString() {
    return "{" +
      " userId='" + getUserId() + "'" +
      ", email='" + getEmail() + "'" +
      ", password='" + getPassword() + "'" +
      ", firstName='" + getFirstName() + "'" +
      ", lastName='" + getLastName() + "'" +
      ", company='" + getCompany() + "'" +
      ", address='" + getAddress() + "'" +
      ", postalCode='" + getPostalCode() + "'" +
      ", province='" + getProvince() + "'" +
      ", country='" + getCountry() + "'" +
      ", phoneNum='" + getPhoneNum() + "'" +
      ", createdTime='" + getCreatedTime() + "'" +
      ", role='" + getRole() + "'" +
      ", issues='" + getIssues() + "'" +
      "}";
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return userId == user.userId && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(company, user.company) && Objects.equals(address, user.address) && Objects.equals(postalCode, user.postalCode) && Objects.equals(province, user.province) && Objects.equals(country, user.country) && Objects.equals(phoneNum, user.phoneNum) && Objects.equals(createdTime, user.createdTime) && Objects.equals(role, user.role) && Objects.equals(issues, user.issues);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, email, password, firstName, lastName, company, address, postalCode, province, country, phoneNum, createdTime, role);
  }
  

}
