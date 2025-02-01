package com.djdownloader.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProccessBuilder {

  public static String ejecutarComando(String comando) {
    StringBuilder sb = new StringBuilder();
    File yt_dlp = new File("yt-dlp"); // Ruta absoluta

    List<String> comandos = new ArrayList<>();
    comandos.add(yt_dlp.getAbsolutePath());  // Ruta del ejecutable
    comandos.add("https://music.youtube.com/watch?v=" + comando); // URL

    try {
      ProcessBuilder processBuilder = new ProcessBuilder(comandos);
      processBuilder.redirectErrorStream(true);
      Process process = processBuilder.start();

      // Leer la salida del proceso
      try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
        String linea;
        while ((linea = reader.readLine()) != null) {
          System.out.println(linea);
        }
      }

      // Esperar a que termine el proceso
      int exitCode = process.waitFor();
      return "Proceso finalizado con código: " + exitCode;
    } catch (Exception e) {
      e.printStackTrace();
      return "Error: " + e.getMessage();
    }
  }

}
