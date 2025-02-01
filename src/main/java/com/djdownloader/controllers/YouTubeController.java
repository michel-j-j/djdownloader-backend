package com.djdownloader.controllers;

import com.djdownloader.model.ProccessBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class YouTubeController {
  @GetMapping("/youtube/{url}" )
  public String hello(@PathVariable("url") String url) {

    return ProccessBuilder.ejecutarComando(url);
  }
}