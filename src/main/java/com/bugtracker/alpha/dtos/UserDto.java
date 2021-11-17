package com.bugtracker.alpha.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Set;

import com.bugtracker.alpha.entities.Issue;
import com.bugtracker.alpha.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.springframework.format.annotation.DateTimeFormat;

public class UserDto implements Serializable{
  
  private long userId;
  private String email;
  private String password;
  private String firstName;
  private String lastName;
  private CompanyDto companyDto;
  private String address;
  private String postalCode;
  private String province;
  private String country;
  private String phoneNum;
  @DateTimeFormat(pattern = "YYYY-MM-DD hh:mm:ss")
  private LocalDateTime createdTime;
  private RoleDto roleDto;
  @JsonManagedReference
  private Set<IssueDto> issuesDto;
  

  public UserDto() {

  }

  public UserDto(User user) {
    this.userId = user.getUserId();
    this.email = user.getEmail();
    this.password = user.getPassword();
    this.firstName = user.getFirstName();
    this.lastName = user.getLastName();
    this.companyDto = new CompanyDto(user.getCompany());
    this.address = user.getAddress();
    this.postalCode = user.getPostalCode();
    this.province = user.getProvince();
    this.country = user.getCountry();
    this.phoneNum = user.getPhoneNum();
    this.createdTime = user.getCreatedTime();
    this.roleDto = new RoleDto(user.getRole());
    /*Iterator<Issue> iterator = user.getIssues().iterator();
    IssueDto newIssueDto;
    while(iterator.hasNext()) {
      newIssueDto = new IssueDto(iterator.next());
      issuesDto.add(newIssueDto);
    }*/
  }

  public long getUserId() {
    return this.userId;
  }

  public void setUserId(long userId) {
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

  public CompanyDto getCompanyDto() {
    return this.companyDto;
  }

  public void setCompanyDto(CompanyDto companyDto) {
    this.companyDto = companyDto;
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

  public LocalDateTime getCreatedTime() {
    return this.createdTime;
  }

  public void setCreatedTime(LocalDateTime createdTime) {
    this.createdTime = createdTime;
  }

  public RoleDto getRoleDto() {
    return this.roleDto;
  }

  public void setRoleDto(RoleDto roleDto) {
    this.roleDto = roleDto;
  }

  public Set<IssueDto> getIssuesDto() {
    return this.issuesDto;
  }

  public void setIssuesDto(Set<IssueDto> issuesDto) {
    this.issuesDto = issuesDto;
  }

  @Override
  public String toString() {
    return "{" +
      " userId='" + getUserId() + "'" +
      ", email='" + getEmail() + "'" +
      ", password='" + getPassword() + "'" +
      ", firstName='" + getFirstName() + "'" +
      ", lastName='" + getLastName() + "'" +
      ", companyDto='" + getCompanyDto() + "'" +
      ", address='" + getAddress() + "'" +
      ", postalCode='" + getPostalCode() + "'" +
      ", country='" + getCountry() + "'" +
      ", phoneNum='" + getPhoneNum() + "'" +
      ", createdTime='" + getCreatedTime() + "'" +
      ", roleDto='" + getRoleDto() + "'" +
      ", issuesDto='" + getIssuesDto() + "'" +
      "}";
  }
}
