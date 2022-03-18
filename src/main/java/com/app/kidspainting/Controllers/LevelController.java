package com.app.kidspainting.Controllers;

import java.util.List;
import java.util.Optional;

import com.app.kidspainting.Repositorys.LevelRepository;
import com.app.kidspainting.Utils.Level;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(value = "/api/v1/level")
    public List<Level> listAll() {
        List<Level> levelAll = levelRepository.findAll();
        return levelAll;
    }

    @GetMapping(value = "/api/v1/level/{id}")
    public Level findById(@PathVariable Integer id){
        Optional<Level> myLevel = levelRepository.findById(id);
        return myLevel.get();
    }

    @PostMapping(value = "/api/v1/level")
    public Level addLevel(@RequestBody Level NewLevel){
        levelRepository.save(NewLevel);
        return NewLevel;
    }

    @DeleteMapping(value = "/api/v1/level/{id}")
    public void deleteLevel(@PathVariable Integer id) {
        levelRepository.deleteById(id);
    }

    @PutMapping(value = "/api/v1/level/{id}")
    public void updateLevel(@PathVariable Integer id, @RequestBody Level NewLevel){
        Level myLevel = levelRepository.getById(id);
        myLevel.setLevelName(NewLevel.getLevelName());
        levelRepository.save(myLevel);
    }
}
