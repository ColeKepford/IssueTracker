package com.bugtracker.alpha.roles;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="company_roles")
public class Role {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int role_id;
  private int company_id;
  private String name;

  public Role() {

  }

  public Role(int company_id, String name) {
    this.company_id = company_id;
    this.name = name;
  }

  public int getRole_id() {
    return this.role_id;
  }

  public void setRole_id(int role_id) {
    this.role_id = role_id;
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
