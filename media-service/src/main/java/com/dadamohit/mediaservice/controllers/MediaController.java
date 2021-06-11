package com.dadamohit.mediaservice.controllers;

import com.dadamohit.servicecommon.UserMedia;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/media")
public class MediaController {

  @GetMapping(path = "/user/{id}")
  public UserMedia getUserMedia(@PathVariable Long id){

  }
}
