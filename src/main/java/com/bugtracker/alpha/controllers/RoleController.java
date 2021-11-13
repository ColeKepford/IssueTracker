package com.bugtracker.alpha.controllers;

import java.util.List;

import com.bugtracker.alpha.entities.Role;
import com.bugtracker.alpha.services.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
@CrossOrigin(origins="http://localhost:3000")
public class RoleController {
  private final RoleService roleService;

  @Autowired
  public RoleController(RoleService roleService) {
    this.roleService = roleService;
  }

  @GetMapping("allRoles")
  public List<Role> getAllRoles() {
    return roleService.getAllRoles();
  }
  
  @GetMapping("/id/{id}")
  public Role getRole(@PathVariable("id")long id) {
    return roleService.findRoleById(id);
  }

  @GetMapping("/name/{name}")
  public List<Role> getRolesByName(@PathVariable("name")String name) {
    return roleService.getRolesByName(name);
  }

  @PostMapping(path="addRole", consumes="application/json", produces="application/json")
  public void addRole(@RequestBody Role role) {
    roleService.addRole(role);
  }

  @PutMapping(path="/updateRole")
  public void updateRole(@RequestBody Role role) {
    roleService.updateRole(role);
  }

  @PutMapping(path="/deleteRole")
  public void deleteRole(@RequestBody Role role) {
    roleService.deleteRole(role);
  }
}
