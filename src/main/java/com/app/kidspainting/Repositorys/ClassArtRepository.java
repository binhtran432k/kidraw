package com.app.kidspainting.Repositorys;

import java.util.Optional;

import com.app.kidspainting.Utils.ClassArt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassArtRepository extends JpaRepository <ClassArt, Integer> {
    Optional<ClassArt> findById(Integer id);
}
