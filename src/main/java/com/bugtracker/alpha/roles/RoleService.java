package com.bugtracker.alpha.roles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bugtracker.alpha.companies.Company;
import com.bugtracker.alpha.logging.LoggingController;

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
    return roleRepository.findAll();
  }

  public Role getRoleById(int id) {
    boolean exists = roleRepository.existsById(id);
    if(!exists) {
      //todo logging
      return null;
    }
    else {
      //todo logging
      return roleRepository.getById(id);
    }
  }

  public List<Role> getRolesByCompany(Company company) {
    Optional<List<Role>> rolesOptional = roleRepository.findByCompany(company.getCompany_id());
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

  public void addNewRole(Role role) {
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
