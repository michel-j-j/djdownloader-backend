package com.djdownloader.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class YouTubeController {
  @GetMapping("/youtube/")
  public String hello() {
    return "Hello, World!";
  }
}