package com.app.kidspainting.Repositorys;

import java.util.Optional;

import com.app.kidspainting.Utils.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{
    Optional<Student> findByEmail(String mail);
}
