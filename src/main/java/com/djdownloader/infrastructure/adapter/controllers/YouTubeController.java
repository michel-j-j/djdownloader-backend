package com.djdownloader.infrastructure.adapter.controllers;

import com.djdownloader.domain.model.Metadata;
import com.djdownloader.domain.port.out.Downloader;
import com.djdownloader.infrastructure.adapter.helper.ExplodeURL;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class YouTubeController {

  private final Downloader downloader;

  public YouTubeController(Downloader downloader) {
    this.downloader = downloader;
  }

  @PostMapping("/youtube")
  public ResponseEntity<Resource> urlDownloader(@RequestBody Map<String, Object> content) {
    try {
      String format = (String) content.get("format");
      String audioQuality = "10"; // 0 (mejor) a 10 (peor)
      String url = ExplodeURL.extractVideoId((String) content.get("url"));
      String fileName = downloader.executeCommand(url, format, audioQuality);

      Metadata metadata = ExplodeURL.getMetadata(url);

      System.out.println(metadata.toString());
      System.out.println(fileName);

      String artist = metadata.getArtist();
      String duration = String.valueOf(metadata.getDuration());

      return downloader.downloadFile(fileName)
                       .map(resource -> ResponseEntity.ok()
                                                      .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                                                      .header("X-Original-Title", fileName.replace("." + format, ""))
                                                      .header("X-Artist", artist) // Puedes obtener esto de los metadatos del video
                                                      .header("X-Duration", duration) // Puedes obtener esto de los metadatos del video
                                                      .body(resource))
                       .orElse(ResponseEntity.notFound().build());
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.internalServerError().build();
    }
  }



}
