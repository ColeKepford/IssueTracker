package com.bugtracker.alpha.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bugtracker.alpha.controllers.LoggingController;
import com.bugtracker.alpha.entities.Issue;
import com.bugtracker.alpha.entities.User;
import com.bugtracker.alpha.repositories.UserRepository;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final LoggingController logs;

  @Autowired
  public UserService(UserRepository userRepository, LoggingController logs) {
    this.userRepository = userRepository;
    this.logs = logs;
  }

  public List<User> getAllUsers() {
    logs.userRetrievedSuccessfully("Retrieved all users");
    Iterable<User> iterable = userRepository.findAll();
    ArrayList<User> users = new ArrayList<>();
    iterable.forEach(users::add);
    return users;
  }

  public List<Issue> getAllIssues(long id) {
    Optional<List<Issue>> optionalIssues = userRepository.getAllIssues(id);
    if(!optionalIssues.isPresent()) {
      return new ArrayList<>();
    }
    else {
      return optionalIssues.get();
    }
  }

  public User getUserById(long id) {
    Optional<User> optional = userRepository.findById(id);
    if(!optional.isPresent()) {
      logs.userDoesntExist("User with id: "+id+" doesnt exist");
      return null;
    }
    else {
      logs.userRetrievedSuccessfully("User with id: "+id+" was retrieved");
      return optional.get();
    }
  }

  public List<User> getUserByEmail(String email) {
    Optional<User> userOptional = userRepository.findUserByEmail(email);
    if(userOptional.isPresent()) { 
      logs.userRetrievedSuccessfully("User with email: " + email + " was retrieved");
      List<User> user = new ArrayList<>();
      user.add(userOptional.get());
      return user;
    } 
    else {
      logs.userDoesntExist("User with email: " + email + " does not exist");
      return new ArrayList<>();
    }
  }

  public List<User> getUsersByLastName(String name) {
    Optional<List<User>> usersOptional = userRepository.findUsersByLastName(name);
    if(usersOptional.isPresent()) { 
      logs.userRetrievedSuccessfully("Users with last name: " + name + " were retrieved");
      List<User> users = new ArrayList<>();
      users.addAll(usersOptional.get());
      return users;
    } 
    else {
      logs.userDoesntExist("User with last name: " + name + " do not exist");
      return new ArrayList<>();
    }
  }

  public void addNewUser(User user) {
    Optional<User> userOptional = userRepository.findUserByEmail(user.getEmail());
    if(userOptional.isPresent()) {
        logs.userDoesExist("User with email: " + user.getEmail() + " already exists");
    }
    userRepository.save(user);
    logs.userAddedSuccessfully("User: " + user.getEmail() + " was successfully added");
  }

  public User credentials(String email, String password){
    Optional<User> userOptional = userRepository.findUser(email,password);
    if(userOptional.isPresent()){
        logs.userRetrievedSuccessfully("User with email: " + email + " was retrieved");
        return userOptional.get();
    }
    else {
        logs.userDoesntExist("User with email: " + email + " does not exist");
        return null;
    }
  }

  public void updateuser(User user) {
    Optional<User> userOptional = userRepository.findById(user.getUserId());
    if(userOptional.isPresent()) {
        userRepository.updateUser(user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getCompany(), user.getAddress(), user.getPostalCode(), user.getProvince(), user.getCountry(), user.getPhoneNum(), user.getCreatedTime(), user.getRole(), user.getUserId());
     
        logs.updateSuccessful("User: " + user.getEmail() + " was successfully updated");
    } else {
        logs.unableToUpdateUser("Unable to update user: " + user.getEmail());
    }
  }

  public void deleteuser(User user) {
    Optional<User> userOptional = userRepository.findById(user.getUserId());
    if(userOptional.isPresent()) {
        userRepository.deleteUser(user.getUserId());
        logs.userDeletedSuccessfully("user: " + user.getEmail() + " was deleted");
    } else {
        logs.unableToDeleteUser("Unable to delete " + user.getEmail());
    }
  }
}
