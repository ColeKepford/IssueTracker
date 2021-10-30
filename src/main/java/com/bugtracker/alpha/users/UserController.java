package com.bugtracker.alpha.users;

import java.util.List;

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

    @GetMapping("/id/{id}")
    public User getUser(@PathVariable("id")Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping("/useremail/{email}")
    public List<User> getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @PostMapping(path="/registerUser", consumes = "application/json", produces = "application/json")
    public void addNewUser(@RequestBody User user) {
        userService.addNewUser(user);
    }

    @GetMapping("/login/{email}&{password}")
    public User logon(@PathVariable("email") String email,
                      @PathVariable("password") String password) {
        return userService.credentials(email, password);
    }
}
