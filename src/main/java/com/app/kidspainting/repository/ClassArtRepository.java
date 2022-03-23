package com.app.kidspainting.repository;

import com.app.kidspainting.entity.Class;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassArtRepository extends JpaRepository <Class, Long> {
}
