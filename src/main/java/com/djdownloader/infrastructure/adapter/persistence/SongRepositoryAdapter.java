package com.djdownloader.infrastructure.adapter.persistence;

import com.djdownloader.domain.model.Song;
import com.djdownloader.domain.port.out.SongRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class SongRepositoryAdapter implements SongRepository {

  private final SongJpaRepository songJpaRepository;

  public SongRepositoryAdapter(SongJpaRepository songJpaRepository) {
    this.songJpaRepository = songJpaRepository;
  }
  @Override
  public Song save(Song song) {
    SongEntity entity = new SongEntity(
        song.getId(),
        song.getFileName(),
        song.getUrl(),
        song.getDownloadDate()
    );
    SongEntity savedEntity = songJpaRepository.save(entity);
    return toDomain(savedEntity);
  }

  @Override
  public Optional<Song> findById(UUID id) {
    return Optional.empty();
  }

  @Override
  public List<Song> findAll() {
    return List.of();
  }
  private Song toDomain(SongEntity entity) {
    return new Song(entity.getId(), entity.getFileName(), entity.getUrl(), entity.getDownloadDate());
  }
}
