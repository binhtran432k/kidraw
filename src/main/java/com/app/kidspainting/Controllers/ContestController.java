package com.app.kidspainting.Controllers;


import java.util.List;
import java.util.Optional;

import com.app.kidspainting.Repositorys.ContestRepository;
import com.app.kidspainting.Utils.Contest;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(value = "/api/v1/contest")
    public List<Contest> listAll() {
        List<Contest> contestAll = contestRepository.findAll();
        return contestAll;
    }

    @GetMapping(value = "/api/v1/contest/{id}")
    public Contest findById(@PathVariable Integer id){
        Optional<Contest> contest = contestRepository.findById(id);
        return contest.get();
    }

    @PostMapping(value = "/api/v1/contest")
    public Contest addContest(@RequestBody Contest NewContest){
        contestRepository.save(NewContest);
        return NewContest;
    }

    @DeleteMapping(value = "/api/v1/contest/{id}")
    public void deleteContest(@PathVariable Integer id) {
        contestRepository.deleteById(id);
    }

    @PutMapping(value = "/api/v1/contest/{id}")
    public void updateContest(@PathVariable Integer id, @RequestBody Contest NewContest){
        Contest contest = contestRepository.getById(id);
        contest.setContestName(NewContest.getContestName());
        contestRepository.save(contest);
    }
}
