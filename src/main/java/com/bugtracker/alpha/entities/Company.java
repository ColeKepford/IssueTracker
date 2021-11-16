package com.bugtracker.alpha.entities;

import javax.persistence.*;

@Entity
@Table(name="company")
public class Company {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "company_id")
  private long companyId;
  private String name;

  public Company() {

  }
  
  public Company(String name) {
    this.name = name;
  }

  public Company(long id, String name) {
    this.companyId = id;
    this.name = name;
  }

  public long getCompanyId() {
    return this.companyId;
  }

  public void setCompanyId(long companyId) {
    this.companyId = companyId;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "{" +
      " company_id='" + getCompanyId() + "'" +
      ", name='" + getName() + "'" +
      "}";
  }
}
