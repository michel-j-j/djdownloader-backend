package com.djdownloader.infrastructure.adapter.downloader;

import com.djdownloader.domain.port.out.Downloader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class ProcessBuilderDownloader implements Downloader {
  private static final String DOWNLOAD_DIR = "src/main/resources/downloads/";

  @Override
  public String executeCommand(String command, String format, String audioQuality) {
    StringBuilder sb = new StringBuilder();
    File yt_dlp = new File("yt-dlp"); // Ruta absoluta
    String fileName = null;
    String outputFilePath = DOWNLOAD_DIR + "%(title)s.%(ext)s";

    List<String> comandos = Arrays.asList(
        yt_dlp.getAbsolutePath(),
        "-o", outputFilePath,
        "https://music.youtube.com/watch?v=" + command,
        "-x",
        "--audio-quality", audioQuality,
        "--audio-format", format
    );

    try {
      java.lang.ProcessBuilder processBuilder = new java.lang.ProcessBuilder(comandos);
      processBuilder.redirectErrorStream(true);
      Process process = processBuilder.start();

      // Leer la salida del proceso
      try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
        String line;
        while ((line = reader.readLine()) != null) {
          System.out.println(line);
          // Espera y chequeo del código de proceso se podría mejorar para no detenerse en cada línea
          process.waitFor();
          // Buscar la línea con el nombre del archivo final
          if (line.contains("[Merger] Merging formats into"))
            fileName = extractFileName(line);
          else if (line.contains("[download]") && line.contains("has already been downloaded"))
            fileName = extractFileName(line);
          else if (line.contains("[ExtractAudio] Destination:"))
            fileName = extractFileName(line);
        }
      }
      process.waitFor();
      return fileName;
    } catch (Exception e) {
      e.printStackTrace();
      return "Error: " + e.getMessage();
    }
  }

  @Override
  public Optional<Resource> downloadFile(String fileName) {
    try {
      Path filePath = Paths.get(DOWNLOAD_DIR).resolve(fileName).normalize();
      Resource resource = new UrlResource(filePath.toUri());
      if (!resource.exists()) {
        return Optional.empty();
      }
      return Optional.of(resource);
    } catch (Exception e) {
      e.printStackTrace();
      return Optional.empty();
    }
  }

  @Override
  public String extractFileName(String line) {
    if (line.contains("[Merger] Merging formats into")) {
      int start = line.indexOf("\"") + 1;
      int end = line.lastIndexOf("\"");
      if (start > 0 && end > start) {
        String fullPath = line.substring(start, end);
        return new File(fullPath).getName();
      }
    } else if (line.contains("[download]") && line.contains("has already been downloaded")) {
      int start = line.indexOf("[download]") + "[download]".length();
      int end = line.indexOf("has already been downloaded");
      if (start >= 0 && end > start) {
        String fullPath = line.substring(start, end).trim();
        return new File(fullPath).getName();
      }
    } else if (line.contains("[ExtractAudio] Destination:")) {
      int index = line.indexOf("Destination:") + "Destination:".length();
      String fullPath = line.substring(index).trim();
      return new File(fullPath).getName();
    }
    return null;
  }
}
