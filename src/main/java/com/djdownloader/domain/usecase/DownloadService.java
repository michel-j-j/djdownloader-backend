package com.djdownloader.domain.usecase;
import com.djdownloader.domain.model.Song;
import com.djdownloader.domain.port.out.SongRepository;
import com.djdownloader.domain.port.out.Downloader;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class DownloadService {

  private final Downloader downloader;
  private final SongRepository songRepository;

  public DownloadService(Downloader downloader, SongRepository songRepository) {
    this.downloader = downloader;
    this.songRepository = songRepository;
  }

  public void downloadSong(String url, String format, String audioQuality) {
    // Ejecuta la descarga usando el adaptador
    String fileName = downloader.executeCommand(url, format, audioQuality);
    // Construye el objeto Song
    Song song = new Song(UUID.randomUUID(), fileName, url, LocalDateTime.now());
    // Persiste la canción descargada
    songRepository.save(song);
  }
}
