package com.app.kidspainting.Controllers;


import java.util.List;
import java.util.Optional;

import com.app.kidspainting.Repositorys.ClassArtRepository;
import com.app.kidspainting.Utils.ClassArt;

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
    public List<ClassArt> listAll() {
        List<ClassArt> classArtAll = classArtRepository.findAll();
        return classArtAll;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/api/v1/classArt/{id}")
    public ClassArt findById(@PathVariable Integer id){
        Optional<ClassArt> classArt = classArtRepository.findById(id);
        return classArt.get();
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/api/v1/classArt")
    public ClassArt addClassArt(@RequestBody ClassArt NewClassArt){
        classArtRepository.save(NewClassArt);
        return NewClassArt;
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(value = "/api/v1/classArt/{id}")
    public void deleteClassArt(@PathVariable Integer id) {
        classArtRepository.deleteById(id);
    }

    @CrossOrigin(origins = "*")
    @PutMapping(value = "/api/v1/classArt/{id}")
    public void updateClassArt(@PathVariable Integer id, @RequestBody ClassArt NewClassArt){
        ClassArt classArt = classArtRepository.getById(id);
        classArt.setClassArtName(NewClassArt.getClassArtName());
        classArtRepository.save(classArt);
    }
}
