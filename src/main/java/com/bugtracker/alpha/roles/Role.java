package com.bugtracker.alpha.roles;

import javax.persistence.*;

import com.bugtracker.alpha.companies.Company;

@Entity
@Table(name="role")
public class Role {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int role_id;
  @JoinColumn(name = "company", referencedColumnName = "company_id")
  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  private Company company;
  private String name;

  public Role() {

  }

  public Role(Company company, String name) {
    this.company = company;
    this.name = name;
  }

  public int getRole_id() {
    return this.role_id;
  }

  public void setRole_id(int role_id) {
    this.role_id = role_id;
  }

  public Company getCompany() {
    return this.company;
  }

  public void setCompany_id(Company company) {
    this.company = company;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
