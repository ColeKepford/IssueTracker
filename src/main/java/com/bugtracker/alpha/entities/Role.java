package com.bugtracker.alpha.entities;

import javax.persistence.*;

@Entity
@Table(name="role")
public class Role {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int role_id;
  private String name;

  public Role() {

  }

  public Role(String name) {
    this.name = name;
  }

  public int getRole_id() {
    return this.role_id;
  }

  public void setRole_id(int role_id) {
    this.role_id = role_id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
