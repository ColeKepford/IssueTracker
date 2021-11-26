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
    logs.info("Retrieved all users");
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
      logs.info("Retrieved all User's issues.");
      return optionalIssues.get();
    }
  }

  public User getUserById(long id) {
    Optional<User> optional = userRepository.findById(id);
    if(!optional.isPresent()) {
      logs.info("No user found.");
      return null;
    }
    else {
      logs.info("User retrieved.");
      return optional.get();
    }
  }

  public List<User> getUserByEmail(String email) {
    Optional<User> userOptional = userRepository.findUserByEmail(email);
    if(userOptional.isPresent()) { 
      logs.info("User retrieved.");
      List<User> user = new ArrayList<>();
      user.add(userOptional.get());
      return user;
    } 
    else {
      logs.info("User not found.");
      return new ArrayList<>();
    }
  }

  public List<User> getUsersByLastName(String name) {
    Optional<List<User>> usersOptional = userRepository.findUsersByLastName(name);
    if(usersOptional.isPresent()) { 
      logs.info("Users with given last name retrieved.");
      List<User> users = new ArrayList<>();
      users.addAll(usersOptional.get());
      return users;
    } 
    else {
      logs.info("Users with given last name not found.");
      return new ArrayList<>();
    }
  }

  public void addNewUser(User user) {
    Optional<User> userOptional = userRepository.findUserByEmail(user.getEmail());
    if(userOptional.isPresent()) {
        logs.info("That user already exists.");
    }
    userRepository.save(user);
    logs.info("User succesfully added.");
  }

  public User credentials(String email, String password){
    Optional<User> userOptional = userRepository.findUser(email,password);
    if(userOptional.isPresent()){
        logs.info("User with given email retrieved.");
        return userOptional.get();
    }
    else {
        logs.info("User with given email not found.");
        return null;
    }
  }

  public void updateuser(User user) {
    Optional<User> userOptional = userRepository.findById(user.getUserId());
    if(userOptional.isPresent()) {
        userRepository.updateUser(user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getCompany(), user.getAddress(), user.getPostalCode(), user.getProvince(), user.getCountry(), user.getPhoneNum(), user.getCreatedTime(), user.getRole(), user.getUserId());
     
        logs.info("User updated succesfully.");
    } else {
        logs.error("User not updated succesfully.");
    }
  }

  public void deleteuser(User user) {
    Optional<User> userOptional = userRepository.findById(user.getUserId());
    if(userOptional.isPresent()) {
        userRepository.deleteUser(user.getUserId());
        logs.info("User deleted succesfully");
    } else {
        logs.error("Unable to user");
    }
  }
}
