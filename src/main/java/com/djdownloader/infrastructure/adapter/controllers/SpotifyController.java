package com.djdownloader.infrastructure.adapter.controllers;

import com.djdownloader.domain.port.out.Downloader;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SpotifyController {

  private final Downloader downloader;

  public SpotifyController(Downloader downloader) {
    this.downloader = downloader;
  }

  @GetMapping("/spotify/{url}")
  public ResponseEntity<Resource> urlDownloader(@PathVariable("url") String url) {
    String format = "wav";
    String audioQuality = "10"; // 0 (mejor) a 10 (peor)
    String fileName = downloader.executeCommand(url, format, audioQuality);

    return downloader.downloadFile(fileName)
                     .map(resource -> ResponseEntity.ok()
                                                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                                                    .body(resource))
                     .orElse(ResponseEntity.notFound().build());
  }
}
