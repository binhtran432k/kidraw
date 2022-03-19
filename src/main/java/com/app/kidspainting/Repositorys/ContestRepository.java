package com.app.kidspainting.Repositorys;

import java.util.Optional;

import com.app.kidspainting.Utils.Contest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContestRepository extends JpaRepository <Contest, Integer> {
    Optional<Contest> findById(Integer id);
}
