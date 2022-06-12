package com.oneseven.Project07.service;

import com.oneseven.Project07.entity.Team;
import com.oneseven.Project07.exception.TeamNotFoundException;
import com.oneseven.Project07.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeamService {

    private final TeamRepository repository;

    @Autowired
    public  TeamService (TeamRepository repository) {
        this.repository = repository;
    }

    public Team getTeam(Integer id) throws TeamNotFoundException{
        Optional<Team> team = repository.findById(id);
        if(team.isPresent()){
            return team.get();
        } else {
            throw new TeamNotFoundException("Team with id " +id+ " not found.");
        }
    }

    public String addNewTeam(String name, Integer capacity) {
        Team team = new Team();
        team.setTeamName(name);
        team.setTeamCapacity(capacity);
        repository.save(team);
        return "Tým vytvořen";
    }

    public void save(Team team){
        repository.save(team);
    }

    public Iterable<Team> getAllTeams() {
        return repository.findAll();
    }
}
