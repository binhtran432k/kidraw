package com.app.kidspainting.controller;

import java.util.List;
import java.util.Optional;

import com.app.kidspainting.entity.ArtType;
import com.app.kidspainting.repository.TypeArtRepository;

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
public class TypeArtController {
    @Autowired
    private TypeArtRepository typeArtRepository;

    // Lấy tất cả kiểu vẽ
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/api/v1/typeArt")
    public List<ArtType> listAll() {
        List<ArtType> typeArtAll = typeArtRepository.findAll();
        return typeArtAll;
    }

    //Lấy kiểu vẽ dựa trên id
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/api/v1/typeArt/id/{id}")
    public ArtType getTypeArt(@PathVariable Long id){
        Optional<ArtType> typeArt = typeArtRepository.findById(id);
        return typeArt.get();
    }

    // Lấy kiểu vẽ dữa trên tên
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/api/v1/typeArt/{name}")
    public ArtType getTypeArtName(@PathVariable String name){
        Optional<ArtType> typeArt = typeArtRepository.findByName(name);
        return typeArt.get();
    }

    // Thêm kiểu vẽ
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/api/v1/typeArt")
    public ArtType addTypeArt(@RequestBody ArtType NewTypeArt){
        typeArtRepository.save(NewTypeArt);
        return NewTypeArt;
    }

    //Xóa kiểu vẽ
    @CrossOrigin(origins = "*")
    @DeleteMapping(value = "/api/v1/typeArt/{id}")
    public void deleteTypeArt(@PathVariable Long id) {
        typeArtRepository.deleteById(id);
    }

    // Chỉnh sửa kiểu vẽ
    @CrossOrigin(origins = "*")
    @PutMapping(value = "/api/v1/typeArt/{id}")
    public void updateTypeArt(@PathVariable Long id, @RequestBody ArtType NewTypeArt){
        ArtType typeArt = typeArtRepository.getById(id);
        typeArt.setName(NewTypeArt.getName());
        typeArtRepository.save(typeArt);
    }
}
