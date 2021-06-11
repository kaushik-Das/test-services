package com.dadamohit.userservice.controllers;

import com.dadamohit.userservice.models.User;
import com.dadamohit.userservice.repositories.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")
public class UserController {

  @Autowired
  private final UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping()
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @GetMapping(path = "/{id}")
  public User getUserById(@PathVariable Long id) throws Exception {
    final Optional<User> user = userRepository.findById(id);
    if (!user.isPresent()) {
      throw new Exception("User Not found");
    }
    final User response = user.get();
    return response;
  }

  @PostMapping(path = "/")
  public void addUser(@RequestBody User user) {
    userRepository.save(user);
  }

  @PutMapping(path = "/{id}")
  public void updateUser(@RequestBody User user, @PathVariable Long id){
    user.setUserId(id);
    userRepository.save(user);
  }

  @DeleteMapping(path = "/{id}")
  public void deleteUser(@PathVariable Long id){
    userRepository.deleteById(id);
  }
}
