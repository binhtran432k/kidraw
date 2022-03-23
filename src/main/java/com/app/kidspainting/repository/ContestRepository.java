package com.app.kidspainting.repository;

import com.app.kidspainting.entity.Contest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContestRepository extends JpaRepository <Contest, Long> {
}
