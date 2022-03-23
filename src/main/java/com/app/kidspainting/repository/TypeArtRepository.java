package com.app.kidspainting.repository;

import java.util.Optional;

import com.app.kidspainting.entity.ArtType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeArtRepository extends JpaRepository <ArtType, Long> {
    Optional<ArtType> findByName(String name);
}
