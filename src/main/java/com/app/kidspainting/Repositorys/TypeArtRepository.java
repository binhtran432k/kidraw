package com.app.kidspainting.Repositorys;

import java.util.Optional;

import com.app.kidspainting.Utils.TypeArt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TypeArtRepository extends JpaRepository <TypeArt, Integer> {
    Optional<TypeArt> findById(Integer id);
}
