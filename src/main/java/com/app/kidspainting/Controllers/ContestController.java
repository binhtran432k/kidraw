package com.app.kidspainting.Controllers;


import java.util.List;
import java.util.Optional;

import com.app.kidspainting.Repositorys.ContestRepository;
import com.app.kidspainting.Repositorys.LevelRepository;
import com.app.kidspainting.Repositorys.TypeArtRepository;
import com.app.kidspainting.Utils.Contest;
import com.app.kidspainting.Utils.Level;
import com.app.kidspainting.Utils.TypeArt;

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
    public Contest findById(@PathVariable Integer id){
        Optional<Contest> contest = contestRepository.findById(id);
        return contest.get();
    }

    // Lấy kiểu vẽ trong cuộc thi bằng id
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/api/v1/contest/type/{id}")
    public String getTypeOfContest(@PathVariable Integer id){
        Optional<Contest> contest = contestRepository.findById(id);      
        return contest.get().getMyType().getTypeName();
    }
    
    //lấy mức độ của cuộc thi bằng id
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/api/v1/contest/level/{id}")
    public String getLevelOfContest(@PathVariable Integer id){
        Optional<Contest> contest = contestRepository.findById(id);      
        return contest.get().getMyLevel().getLevelName();
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/api/v1/contest/{typeArtName}/{levelArtName}")
    public Contest addContest(@RequestBody Contest NewContest, @PathVariable String typeArtName, @PathVariable String levelArtName){
        Optional<TypeArt> typeArt = typeArtRepository.findByTypeName(typeArtName);
        NewContest.setMyType(typeArt.get());
        Optional<Level> level = levelRepository.findByLevelName(levelArtName);
        NewContest.setMyLevel(level.get());
        contestRepository.save(NewContest);
        return NewContest;
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(value = "/api/v1/contest/{id}")
    public void deleteContest(@PathVariable Integer id) {
        contestRepository.deleteById(id);
    }

    @CrossOrigin(origins = "*")
    @PutMapping(value = "/api/v1/contest/{typeArtName}/{levelArtName}/{id}")
    public void updateContest(@PathVariable Integer id, @PathVariable String typeArtName, @PathVariable String levelArtName, @RequestBody Contest NewContest){
        Contest contest = contestRepository.getById(id);
        Optional<TypeArt> typeArt = typeArtRepository.findByTypeName(typeArtName);
        NewContest.setMyType(typeArt.get());
        Optional<Level> level = levelRepository.findByLevelName(levelArtName);
        NewContest.setMyLevel(level.get());

        contest.setContestName(NewContest.getContestName());
        contest.setContestBody(NewContest.getContestBody());
        contest.setContestStatus(NewContest.getContestStatus());
        contest.setMaxContestParticipant(NewContest.getMaxContestParticipant());
        contest.setMyLevel(NewContest.getMyLevel());
        contest.setMyType(NewContest.getMyType());
        contest.setStartTime(NewContest.getStartTime());
        contest.setEndTime(NewContest.getEndTime());
        contestRepository.save(contest);
    }
}
