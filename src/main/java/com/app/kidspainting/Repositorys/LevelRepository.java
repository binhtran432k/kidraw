package com.app.kidspainting.Repositorys;

import java.util.Optional;

import com.app.kidspainting.Utils.Level;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<Level, Integer> {
    Optional<Level> findById(Integer id);
}
