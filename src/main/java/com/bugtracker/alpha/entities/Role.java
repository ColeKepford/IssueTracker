package com.bugtracker.alpha.entities;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="role")
public class Role {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "role_id")
  private long roleId;
  private String name;

  public Role() {

  }

  public Role(String name) {
    this.name = name;
  }

  public Role(long roleId, String name) {
    this.roleId = roleId;
    this.name = name;
  }

  public long getRoleId() {
    return this.roleId;
  }

  public void setRoleId(int roleId) {
    this.roleId = roleId;
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
      " role_id='" + getRoleId() + "'" +
      ", name='" + getName() + "'" +
      "}";
  }
}
