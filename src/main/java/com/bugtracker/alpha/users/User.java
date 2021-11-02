package com.bugtracker.alpha.users;
import java.time.LocalDate;

import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import com.bugtracker.alpha.companies.*;
import com.bugtracker.alpha.roles.*;

@Entity
@Table(name="user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int user_id;
  private String email;
  private String password;
  private String first_name;
  private String last_name;
  @JoinColumn(name = "company", referencedColumnName = "company_id")
  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  private Company company;
  private String address;
  private String postal_code;
  private String phone_num;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private String created_time;
  @JoinColumn(name = "role", referencedColumnName = "role_id")
  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  private Role role;

  public User() {

  }

  public User(String email, String password, String first_name, String last_name, Company company, String address, String postal_code, String phone_num, String created_time, Role role) {
    this.email = email;
    this.password = password;
    this.first_name = first_name;
    this.last_name = last_name;
    this.company = company;
    this.address = address;
    this.postal_code = postal_code;
    this.phone_num = phone_num;
    this.created_time = created_time;
    this.role = role;
  } 

  public int getUser_id() {
    return this.user_id;
  }

  public void setUser_id(int user_id) {
    this.user_id = user_id;
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

  public String getFirst_name() {
    return this.first_name;
  }

  public void setFirst_name(String first_name) {
    this.first_name = first_name;
  }

  public String getLast_name() {
    return this.last_name;
  }

  public void setLast_name(String last_name) {
    this.last_name = last_name;
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

  public String getPostal_code() {
    return this.postal_code;
  }

  public void setPostal_code(String postal_code) {
    this.postal_code = postal_code;
  }

  public String getPhone_num() {
    return this.phone_num;
  }

  public void setPhone_num(String phone_num) {
    this.phone_num = phone_num;
  }

  public String getCreated_time() {
    return this.created_time;
  }

  public void setCreated_time(String created_time) {
    this.created_time = created_time;
  }

  public Role getRole() {
    return this.role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  @Override
  public String toString() {
    return "{" +
      " user_id='" + getUser_id() + "'" +
      ", email='" + getEmail() + "'" +
      ", password='" + getPassword() + "'" +
      ", first_name='" + getFirst_name() + "'" +
      ", last_name='" + getLast_name() + "'" +
      ", company='" + getCompany() + "'" +
      ", address='" + getAddress() + "'" +
      ", postal_code='" + getPostal_code() + "'" +
      ", phone_num='" + getPhone_num() + "'" +
      ", created_time='" + getCreated_time() + "'" +
      ", role='" + getRole() + "'" +
      "}";
  }

}
