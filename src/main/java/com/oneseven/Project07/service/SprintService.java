package com.oneseven.Project07.service;

import com.oneseven.Project07.entity.Sprint;
import com.oneseven.Project07.exception.SprintNotFoundException;
import com.oneseven.Project07.repository.SprintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SprintService {

    private final SprintRepository repository;

    @Autowired
    public SprintService(SprintRepository repository) {
        this.repository = repository;
    }

    public Sprint getSprint(Integer id) throws SprintNotFoundException {
        Optional<Sprint> sprint = repository.findById(id);
        if(sprint.isPresent()){
            return sprint.get();
        } else {
            throw new SprintNotFoundException("Sprint with id " +id+ " not found.");
        }
    }

    public String addNewSprint(String name){
        Sprint sprint = new Sprint();
        sprint.setSprintName(name);
        repository.save(sprint);
        return "Sprint vytvořen";
    }

    public void save(Sprint sprint){
        repository.save(sprint);
    }

    public Iterable<Sprint> getAllSprints() {
        return repository.findAll();
    }
}
