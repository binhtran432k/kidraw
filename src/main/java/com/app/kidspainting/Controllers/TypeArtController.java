package com.app.kidspainting.Controllers;

import java.util.List;
import java.util.Optional;

import com.app.kidspainting.Repositorys.TypeArtRepository;
import com.app.kidspainting.Utils.TypeArt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TypeArtController {
    @Autowired
    private TypeArtRepository typeArtRepository;

    @GetMapping(value = "/api/v1/typeArt")
    public List<TypeArt> listAll() {
        List<TypeArt> typeArtAll = typeArtRepository.findAll();
        return typeArtAll;
    }

    @GetMapping(value = "/api/v1/typeArt/{id}")
    public TypeArt findById(@PathVariable Integer id){
        Optional<TypeArt> typeArt = typeArtRepository.findById(id);
        return typeArt.get();
    }

    @PostMapping(value = "/api/v1/typeArt")
    public TypeArt addTypeArt(@RequestBody TypeArt NewTypeArt){
        typeArtRepository.save(NewTypeArt);
        return NewTypeArt;
    }

    @DeleteMapping(value = "/api/v1/typeArt/{id}")
    public void deleteTypeArt(@PathVariable Integer id) {
        typeArtRepository.deleteById(id);
    }

    @PutMapping(value = "/api/v1/typeArt/{id}")
    public void updateTypeArt(@PathVariable Integer id, @RequestBody TypeArt NewTypeArt){
        TypeArt typeArt = typeArtRepository.getById(id);
        typeArt.setTypeName(NewTypeArt.getTypeName());
        typeArtRepository.save(typeArt);
    }
}
