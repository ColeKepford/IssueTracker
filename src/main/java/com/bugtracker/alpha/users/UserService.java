package com.bugtracker.alpha.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bugtracker.alpha.logging.LoggingController;

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
      return userRepository.findAll();
  }

  public User getUserById(int id) {
    boolean exists = userRepository.existsById(id);
    if(!exists) {
      logs.userDoesntExist("User with id: "+id+" doesnt exsist");
      return null;
    }
    else{
      logs.userRetrievedSuccessfully("User with id: "+id+" was retrieved");
      return userRepository.getById(id);
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
      logs.userDoesntExist("User with email: " + email + " does not exsist");
      return null;
    }
  }

  public void addNewUser(User user) {
    Optional<User> userOptional = userRepository.findUserByEmail(user.getEmail());
    if(userOptional.isPresent()) {
        logs.userDoesExist("User with email: " + user.getEmail() + " already exsists");
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
        logs.userDoesntExist("User with email: " + email + " does not exsist");
        return null;
    }
  }

  public void updateuser(User user) {
    Optional<User> userOptional = userRepository.findById(user.getUser_id());
    if(userOptional.isPresent()) {
        userRepository.updateUser(user.getEmail(), user.getPassword(), user.getFirst_name(), user.getLast_name(), user.getCompany(), user.getAddress(), user.getPostal_code(), user.getPhone_num(), user.getCreated_time(), user.getRole());
     
        logs.updateSuccessful("User: " + user.getEmail() + " was successfully updated");
    } else {
        logs.unableToUpdateUser("Unable to update user: " + user.getEmail());
    }
  }

  public void deleteuser(User user) {
    Optional<User> userOptional = userRepository.findById(user.getUser_id());
    if(userOptional.isPresent()) {
        userRepository.deleteUser(user.getUser_id());
        logs.userDeletedSuccessfully("user: " + user.getEmail() + " was deleted");
    } else {
        logs.unableToDeleteUser("Unable to delete " + user.getEmail());
    }
  }
}
