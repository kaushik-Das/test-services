package com.dadamohit.userservice.controllers;

import com.dadamohit.userservice.models.User;
import com.dadamohit.userservice.repositories.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "/users")
public class UserController {

  @Autowired
  ObjectMapper objectMapper;

  @Autowired
  RestTemplate restTemplate;

  @Value("${mediaservice.url}")
  private String mediaServiceURL;

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

  @PostMapping(path = "/", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
  @Transactional
  public void addUser(@RequestPart("user") User user, @RequestPart("file") MultipartFile file)
      throws IOException {
    final User savedUser = userRepository.save(user);
    HttpEntity<MultiValueMap<String, Object>> requestEntity = createMediaRequest(file);
    ResponseEntity<String> response = null;
    try {
      response = restTemplate.postForEntity(mediaServiceURL, requestEntity, String.class);
    } catch (Exception e){
      System.out.println(e);
    }
    final String mediaId = response.getBody();
    userRepository.addMediaId(savedUser.getUserId(), mediaId);
  }

  @PutMapping(path = "/{id}")
  public void updateUser(@RequestBody User user, @PathVariable Long id) {
    user.setUserId(id);
    userRepository.save(user);
  }

  @DeleteMapping(path = "/{id}")
  public void deleteUser(@PathVariable Long id) {
    userRepository.deleteById(id);
  }

  private HttpEntity<MultiValueMap<String, Object>> createMediaRequest(MultipartFile file)
      throws IOException {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.MULTIPART_FORM_DATA);
    headers.setAccept(Arrays.asList(MediaType.TEXT_PLAIN));
    MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
    headers.setContentLength(file.getSize());
    body.add("file", file.getResource());
    HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
    return requestEntity;
  }
}
