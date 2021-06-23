package com.dadamohit.mediaservice.controllers;

import com.dadamohit.mediaservice.services.MediaService;
import com.dadamohit.servicecommon.models.Media;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "/media")
public class MediaController {

  @Autowired
  private MediaService mediaService;

  @GetMapping(path = "/{id}")
  public Media getMedia(@PathVariable("id") String id) throws IOException {
    return mediaService.getMedia(id);
  }

  @PostMapping()
  public String addMedia(@RequestBody MultipartFile file) throws IOException {
    return mediaService.addMedia(file.getName(), file);

  }

  @DeleteMapping(path = "/{id}")
  public void removeMedia(@PathVariable("id") String id) {
    mediaService.deleteMedia(id);
  }
}
