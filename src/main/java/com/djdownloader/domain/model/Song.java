package com.djdownloader.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Song {
  private UUID id;
  private String fileName;
  private String url;
  private LocalDateTime downloadDate;

  public Song(UUID id, String fileName, String url, LocalDateTime downloadDate) {
    this.id = id;
    this.fileName = fileName;
    this.url = url;
    this.downloadDate = downloadDate;
  }

  // Getters y setters

  public UUID getId() {
    return id;
  }

  public String getFileName() {
    return fileName;
  }

  public String getUrl() {
    return url;
  }

  public LocalDateTime getDownloadDate() {
    return downloadDate;
  }
}
