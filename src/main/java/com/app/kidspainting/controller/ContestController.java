package com.app.kidspainting.controller;


import java.util.List;
import java.util.Optional;

import com.app.kidspainting.entity.ArtLevel;
import com.app.kidspainting.entity.ArtType;
import com.app.kidspainting.entity.Contest;
import com.app.kidspainting.repository.ContestRepository;
import com.app.kidspainting.repository.LevelRepository;
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
public class ContestController {
    @Autowired
    private ContestRepository contestRepository;

    @Autowired 
    private TypeArtRepository typeArtRepository;

    @Autowired
    private LevelRepository levelRepository;

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/api/v1/contest")
    public List<Contest> listAll() {
        List<Contest> contestAll = contestRepository.findAll();
        return contestAll;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/api/v1/contest/{id}")
    public Contest findById(@PathVariable Long id){
        Optional<Contest> contest = contestRepository.findById(id);
        return contest.get();
    }

    // Lấy kiểu vẽ trong cuộc thi bằng id
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/api/v1/contest/type/{id}")
    public String getTypeOfContest(@PathVariable Long id){
        Optional<Contest> contest = contestRepository.findById(id);      
        return contest.get().getType().getName();
    }
    
    //lấy mức độ của cuộc thi bằng id
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/api/v1/contest/level/{id}")
    public String getLevelOfContest(@PathVariable Long id){
        Optional<Contest> contest = contestRepository.findById(id);      
        return contest.get().getLevel().getName();
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/api/v1/contest/{typeArtName}/{levelArtName}")
    public Contest addContest(@RequestBody Contest NewContest, @PathVariable String typeArtName, @PathVariable String levelArtName){
        Optional<ArtType> typeArt = typeArtRepository.findByName(typeArtName);
        NewContest.setType(typeArt.get());
        Optional<ArtLevel> level = levelRepository.findByName(levelArtName);
        NewContest.setLevel(level.get());
        contestRepository.save(NewContest);
        return NewContest;
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(value = "/api/v1/contest/{id}")
    public void deleteContest(@PathVariable Long id) {
        contestRepository.deleteById(id);
    }

    @CrossOrigin(origins = "*")
    @PutMapping(value = "/api/v1/contest/{typeArtName}/{levelArtName}/{id}")
    public void updateContest(@PathVariable Long id, @PathVariable String typeArtName, @PathVariable String levelArtName, @RequestBody Contest NewContest){
        Optional<Contest> contestOpt = contestRepository.findById(id);
        Contest contest = contestOpt.get();
        Optional<ArtType> typeArt = typeArtRepository.findByName(typeArtName);
        NewContest.setType(typeArt.get());
        Optional<ArtLevel> level = levelRepository.findByName(levelArtName);
        NewContest.setLevel(level.get());

        contest.setName(NewContest.getName());
        contest.setBody(NewContest.getBody());
        // contest.setStatus(NewContest.getStatus());
        contest.setMaxParticipant(NewContest.getMaxParticipant());
        // contest.setMyLevel(NewContest.getMyLevel());
        // contest.setMyType(NewContest.getMyType());
        // contest.setStartTime(NewContest.getStartTime());
        // contest.setEndTime(NewContest.getEndTime());
        contestRepository.save(contest);
    }
}
