package com.bugtracker.alpha.entities;

import javax.persistence.*;

@Entity
@Table(name="company")
public class Company {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int company_id;
  private String name;

  public Company() {

  }

  public Company(String name) {
    this.name = name;
  }
  
  public int getCompany_id() {
    return this.company_id;
  }

  public void setCompany_id(int company_id) {
    this.company_id = company_id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
