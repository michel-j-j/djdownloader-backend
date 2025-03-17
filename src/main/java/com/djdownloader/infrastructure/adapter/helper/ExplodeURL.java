package com.djdownloader.infrastructure.adapter.helper;

import com.djdownloader.domain.model.Metadata;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class ExplodeURL {
  public static String extractVideoId(String url) {
    String[] parts = url.split("\\?");
    if (parts.length > 1) {
      String[] params = parts[1].split("&");
      for (String param : params) {
        if (param.startsWith("v=")) {
          return param.substring(2);
        }
      }
    }
    return null; // Retorna null si no encuentra el parámetro v=
  }
  public static Metadata getMetadata(String videoId) {
    try {
      File yt_dlp = new File("yt-dlp");
      List<String> metadataCommand = Arrays.asList(
          yt_dlp.getAbsolutePath(),
          "--dump-json",
          "https://music.youtube.com/watch?v=" + videoId
      );

      ProcessBuilder pb = new ProcessBuilder(metadataCommand);
      pb.redirectErrorStream(true);
      Process process = pb.start();

      StringBuilder jsonOutput = new StringBuilder();
      try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
        String line;
        while ((line = reader.readLine()) != null) {
          jsonOutput.append(line);
        }
      }
      process.waitFor();

      ObjectMapper mapper = new ObjectMapper();
      JsonNode rootNode = mapper.readTree(jsonOutput.toString());

      // Dependiendo de la metadata, el campo del artista podría llamarse "artist" o estar en otro lado
      String artist = rootNode.has("artist") ? rootNode.get("artist").asText() : "Desconocido";
      int duration = rootNode.has("duration") ? rootNode.get("duration").asInt() : 0;

      return new Metadata(artist, duration);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
