package com.djdownloader.domain.model;

public class Metadata {
  private String artist;
  private int duration;

  public Metadata(String artist, int duration) {
    this.artist = artist;
    this.duration = duration;
  }

  @Override
  public String toString() {
    return "Metadata{" +
        "artist='" + artist + '\'' +
        ", duration=" + duration +
        '}';
  }

  public int getDuration() {
    return duration;
  }

  public String getArtist() {
    return artist;
  }

}