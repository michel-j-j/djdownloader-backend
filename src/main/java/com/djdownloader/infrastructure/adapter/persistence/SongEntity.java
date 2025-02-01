package com.djdownloader.infrastructure.adapter.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "songs")
public class SongEntity {
  @Id
  private UUID id;
  private String fileName;
  private String url;
  private LocalDateTime downloadDate;

  public SongEntity() {}

  public SongEntity(UUID id, String fileName, String url, LocalDateTime downloadDate) {
    this.id = id;
    this.fileName = fileName;
    this.url = url;
    this.downloadDate = downloadDate;
  }

  // Getters y setters

  public UUID getId() {
    return id;
  }
  public void setId(UUID id) {
    this.id = id;
  }
  public String getFileName() {
    return fileName;
  }
  public void setFileName(String fileName) {
    this.fileName = fileName;
  }
  public String getUrl() {
    return url;
  }
  public void setUrl(String url) {
    this.url = url;
  }
  public LocalDateTime getDownloadDate() {
    return downloadDate;
  }
  public void setDownloadDate(LocalDateTime downloadDate) {
    this.downloadDate = downloadDate;
  }
}
