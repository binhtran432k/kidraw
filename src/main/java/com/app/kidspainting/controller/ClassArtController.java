package com.app.kidspainting.controller;


import java.util.List;
import java.util.Optional;

import com.app.kidspainting.entity.Class;
import com.app.kidspainting.repository.ClassArtRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClassArtController {
    @Autowired
    private ClassArtRepository classArtRepository;

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/api/v1/classArt")
    public List<Class> listAll() {
        List<Class> classArtAll = classArtRepository.findAll();
        return classArtAll;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/api/v1/classArt/{id}")
    public Class findById(@PathVariable Long id){
        Optional<Class> classArt = classArtRepository.findById(id);
        return classArt.get();
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/api/v1/classArt")
    public Class addClassArt(@RequestBody Class NewClassArt){
        classArtRepository.save(NewClassArt);
        return NewClassArt;
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(value = "/api/v1/classArt/{id}")
    public void deleteClassArt(@PathVariable Long id) {
        classArtRepository.deleteById(id);
    }

    @CrossOrigin(origins = "*")
    @PutMapping(value = "/api/v1/classArt/{id}")
    public void updateClassArt(@PathVariable Long id, @RequestBody Class NewClassArt){
        Class classArt = classArtRepository.getById(id);
        classArt.setName(NewClassArt.getName());
        classArtRepository.save(classArt);
    }
}
