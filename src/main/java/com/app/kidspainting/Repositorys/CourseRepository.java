package com.app.kidspainting.Repositorys;

import java.util.Optional;

import com.app.kidspainting.Utils.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository <Course, Integer>{
    Optional<Course> findById(Integer id);
}
