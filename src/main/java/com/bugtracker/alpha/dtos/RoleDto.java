package com.bugtracker.alpha.dtos;

import java.io.Serializable;
import java.util.Objects;

import com.bugtracker.alpha.entities.Role;

public class RoleDto implements Serializable{

  private long id;
  private String name;
  
  public RoleDto() {

  }

  public RoleDto(Role role) {
    this.id = role.getRoleId();
    this.name = role.getName();
  }


  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
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
      " id='" + getId() + "'" +
      ", name='" + getName() + "'" +
      "}";
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof RoleDto)) {
            return false;
        }
        RoleDto roleDto = (RoleDto) o;
        return id == roleDto.id && Objects.equals(name, roleDto.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }


}
