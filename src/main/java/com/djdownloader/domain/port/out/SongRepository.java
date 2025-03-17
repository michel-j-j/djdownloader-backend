package com.djdownloader.domain.port.out;

import com.djdownloader.domain.model.Song;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SongRepository {
      Song save(Song song);
      Optional<Song> findById(UUID id);
      List<Song> findAll();
}
