package com.oneseven.Project07.controller;


import com.oneseven.Project07.entity.Team;
import com.oneseven.Project07.exception.TeamNotFoundException;
import com.oneseven.Project07.repository.TeamRepository;
import com.oneseven.Project07.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("")
public class TeamController {

    private final TeamService teamService;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }


    //GET team pomocí id
    @GetMapping(path="/getTeam")
    @ResponseBody
    public Team getTeam(Integer id) throws TeamNotFoundException {
        return teamService.getTeam(id);
    }
    //GET all teams
    @GetMapping(path="/allTeams")
    @ResponseBody
    public Iterable <Team> allTeams() {
        return teamService.getAllTeams();
    }
    //POST přidá tým při zadání názvu a kapacity
    @PostMapping(path="/addTeam")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public String addTeam (@RequestParam String name,
                           @RequestParam Integer capacity) {
        return teamService.addNewTeam(name, capacity);
    }




    //zobrazi vsechny týmy v tabulce
    @RequestMapping("/teamsTab")
    public String teamsTab(Model model) {
        model.addAttribute("teams", teamService.getAllTeams());
        return "teamsScr";
    }
    //Formulář pro vytvoření nového týmu
    @PostMapping("/teamsForm")
    public String teamsForm(@ModelAttribute Team team) {
        teamRepository.save(team);
        return "redirect:/teamsTab";
    }

    //uprava týmu
    @GetMapping(path = "/editTeam")
    public String editTeam(Integer teamId, Model model) throws TeamNotFoundException {
        Team team = teamService.getTeam(teamId);
        model.addAttribute("team", team);
        return "teamUpdate";
    }
    @PostMapping(path = "/editTeam")
    public String editSubmit(@ModelAttribute Team team) {
        teamService.save(team);
        return "redirect:/teamsTab";
    }
    //odstranení týmu
    @PostMapping("/deleteTeam")
    public String deleteTeam(Integer teamId){
        teamRepository.deleteById(teamId);
        return "redirect:/teamsTab";
    }

}
