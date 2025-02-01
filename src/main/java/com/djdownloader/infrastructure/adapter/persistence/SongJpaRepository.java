package com.djdownloader.infrastructure.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface SongJpaRepository extends JpaRepository<SongEntity, UUID> {
}