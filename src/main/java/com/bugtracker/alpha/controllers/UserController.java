package com.bugtracker.alpha.controllers;

import java.util.List;

import com.bugtracker.alpha.entities.Issue;
import com.bugtracker.alpha.entities.User;
import com.bugtracker.alpha.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins="http://localhost:3000")
public class UserController {
  private final UserService userService;

  @Autowired
    public UserController(UserService userService) {
      this.userService = userService;
    }

    @GetMapping("/allUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getUser/{id}")
    public User getUser(@PathVariable("id")Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping("/useremail/{email}")
    public List<User> getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping("/lastName/{name}")
    public List<User> getUsersByLastName(@PathVariable String name) {
      return userService.getUsersByLastName(name);
    }

    @GetMapping("/getAllIssues/{id}")
    public List<Issue> getUsersIssues(@PathVariable int id) {
      return userService.getAllIssues(id);
    }

    @PostMapping(path="/registerUser", consumes = "application/json", produces = "application/json")
    public void addNewUser(@RequestBody User user) {
        userService.addNewUser(user);
    }

    @PutMapping(path="/updateUser")
    public void updateNewUser(@RequestBody User user) {
      userService.updateuser(user);
    }

    @PutMapping(path="/deleteUser")
    public void deleteUser(@RequestBody User user) {
      userService.deleteuser(user);
    }

    @GetMapping("/login/{email}&{password}")
    public User logon(@PathVariable("email") String email,
                      @PathVariable("password") String password) {
        return userService.credentials(email, password);
    }
}
