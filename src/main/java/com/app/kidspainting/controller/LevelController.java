package com.app.kidspainting.controller;

import java.util.List;
import java.util.Optional;

import com.app.kidspainting.entity.ArtLevel;
import com.app.kidspainting.repository.LevelRepository;

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
public class LevelController {

    @Autowired
    private LevelRepository levelRepository;

    //Lấy tất cả mức độ
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/api/v1/level")
    public List<ArtLevel> listAll() {
        List<ArtLevel> levelAll = levelRepository.findAll();
        return levelAll;
    }

    //Lấy mức độ dựa trên id
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/api/v1/level/id/{id}")
    public ArtLevel getLevel(@PathVariable Long id){
        Optional<ArtLevel> myLevel = levelRepository.findById(id);
        return myLevel.get();
    }

    //Lấy mức độ dựa trên tên
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/api/v1/level/{name}")
    public ArtLevel findByName(@PathVariable String name){
        Optional<ArtLevel> myLevel = levelRepository.findByName(name);
        return myLevel.get();
    }

    //Thêm mức độ vẽ
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/api/v1/level")
    public ArtLevel addLevel(@RequestBody ArtLevel NewLevel){
        levelRepository.save(NewLevel);
        return NewLevel;
    }

    //Xóa mức độ
    @CrossOrigin(origins = "*")
    @DeleteMapping(value = "/api/v1/level/{id}")
    public void deleteLevel(@PathVariable Long id) {
        levelRepository.deleteById(id);
    }

    //Chỉnh sửa mức độ
    @CrossOrigin(origins = "*")
    @PutMapping(value = "/api/v1/level/{id}")
    public void updateLevel(@PathVariable Long id, @RequestBody ArtLevel NewLevel){
        ArtLevel myLevel = levelRepository.getById(id);
        myLevel.setName(NewLevel.getName());
        levelRepository.save(myLevel);
    }
}
