package com.app.kidspainting.repository;

import java.util.Optional;

import com.app.kidspainting.entity.ArtLevel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<ArtLevel, Long> {
    Optional<ArtLevel> findByName(String name);
}
