package com.bugtracker.alpha.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bugtracker.alpha.controllers.LoggingController;
import com.bugtracker.alpha.entities.Role;
import com.bugtracker.alpha.repositories.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
  private final RoleRepository roleRepository;
  private final LoggingController logs;

  @Autowired
  public RoleService(RoleRepository roleRepository, LoggingController logs) {
    this.roleRepository = roleRepository;
    this.logs = logs;
  }
  
  
  public List<Role> getAllRoles() {
    //todo logging
    Iterable<Role> iterable = roleRepository.findAll();
    ArrayList<Role> roles = new ArrayList<>();
    iterable.forEach(roles::add);
    return roles;
  }

  public Role findRoleById(long id) {
    Optional<Role> optional = roleRepository.findById(id);
    if(!optional.isPresent()) {
      return null;
    }
    else {
      return optional.get();
    } 
  }

  public List<Role> getRolesByName(String name) {
    Optional<List<Role>> rolesOptional = roleRepository.findByName(name);
    if(rolesOptional.isPresent()) {
      //todo logging
      List<Role> roles = new ArrayList<>();
      roles.addAll(rolesOptional.get());
      return roles;
    }
    else {
      //todo logging
      return new ArrayList<>();
    }
  }

  public void addRole(Role role) {
    Optional<Role> roleOptional = roleRepository.findById(role.getRole_id());
    if(roleOptional.isPresent()) {
      //todo logging
    }
    else {
      roleRepository.save(role);
      //todo logging
    }
  }

  
  public void updateRole(Role role) {
    Optional<Role> roleOptional = roleRepository.findById(role.getRole_id());
    if(roleOptional.isPresent()) {
      roleRepository.updateRole(role.getName(), role.getRole_id());
      //todo logging
    }
    else {
      //todo logging
    }
  }
  
  public void deleteRole(Role role) {
    Optional<Role> roleOptional = roleRepository.findById(role.getRole_id());
    if(roleOptional.isPresent()) {
      roleRepository.delete(role);
      //todo logging
    }
    else {
      //todo logging
    }
  }
}
