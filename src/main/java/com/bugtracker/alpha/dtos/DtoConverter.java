package com.bugtracker.alpha.dtos;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.bugtracker.alpha.entities.*;

public class DtoConverter {
  public CompanyDto companyToDto(Company company) {
    return new CompanyDto(company);
  }

  public Company companyDtoToEntity(CompanyDto dto) {
    return new Company(dto.getCompanyId(), dto.getName());
  }

  public IssueDto issueToDto(Issue issue) {
    return new IssueDto(issue);
  }

  public Issue issueDtoToEntity(IssueDto dto) {
    return new Issue(dto.getIssueId(), dto.getTitle(), dto.getDescription(), dto.getSeverity(), companyDtoToEntity(dto.getCompanyDto()), dto.getType(),  dto.getState(), userDtoToEntity(dto.getCreatorDto()), dto.getDateCreated(), dto.getDateResolved(), userDtosToEntities(dto.getAssignedUsers()));
  }

  public Set<IssueDto> issuesToDtos(Set<Issue> issues) {
    Iterator<Issue> iter = issues.iterator();
    Set<IssueDto> dtos = new HashSet<>();
    while(iter.hasNext()) {
      dtos.add(new IssueDto(iter.next()));
    }
    return dtos;
  }

  public Set<Issue> issueDtosToEntities(Set<IssueDto> issueDtos) {
    Iterator<IssueDto> iter = issueDtos.iterator();
    Set<Issue> issues = new HashSet<>();
    while(iter.hasNext()) {
      issues.add(issueDtoToEntity(iter.next()));
    }
    return issues;
  }

  public RoleDto roleToDto(Role role) {
    return new RoleDto(role);
  }

  public Role roleDtoToEntity(RoleDto dto) {

    return new Role(dto.getId(), dto.getName());
  }

  public UserDto userToDto(User user) {
    return new UserDto(user);
  }

  public User userDtoToEntity(UserDto dto) {
    return new User(dto.getUserId(), dto.getEmail(), dto.getPassword(), dto.getFirstName(), dto.getLastName(), companyDtoToEntity(dto.getCompanyDto()), dto.getAddress(), dto.getPostalCode(), dto.getProvince(), dto.getCountry(), dto.getPhoneNum(), dto.getCreatedTime(), roleDtoToEntity(dto.getRoleDto()), issueDtosToEntities(dto.getIssuesDto()));
  }

  public Set<UserDto> usersToDtos(Set<User> users) {
    Iterator<User> iter = users.iterator();
    Set<UserDto> dtos = new HashSet<>();
    while(iter.hasNext()) {
      dtos.add(userToDto(iter.next()));
    }
    return dtos;
  }

  public Set<User> userDtosToEntities(Set<UserDto> dtos) {
    Iterator<UserDto> iter = dtos.iterator();
    Set<User> users = new HashSet<>();
    while(iter.hasNext()) {
      users.add(userDtoToEntity(iter.next()));
    }
    return users;
  }

  
}
