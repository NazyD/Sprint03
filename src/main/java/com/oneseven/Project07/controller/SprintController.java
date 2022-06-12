package com.oneseven.Project07.controller;

import com.oneseven.Project07.entity.Sprint;
import com.oneseven.Project07.entity.Story3;
import com.oneseven.Project07.entity.Team;
import com.oneseven.Project07.exception.SprintNotFoundException;
import com.oneseven.Project07.repository.SprintRepository;
import com.oneseven.Project07.service.SprintService;
import com.oneseven.Project07.service.StoryService3;
import com.oneseven.Project07.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("")
public class SprintController {

    private final SprintService sprintService;
    private final StoryService3 storyService;
    private final TeamService teamService;

    @Autowired
    private SprintRepository sprintRepository;

    @Autowired
    public SprintController(SprintService sprintService, StoryService3 storyService, TeamService teamService) {
        this.sprintService = sprintService;
        this.storyService = storyService;
        this.teamService = teamService;
    }


    //POST pridani sprintu pridanim atributu name
    @PostMapping(path="/addSprint")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public String addNewSprint(@RequestParam String name) {
        return sprintService.addNewSprint(name);
    }

    //GET zobrazeni sprintu pomoci atributu id
    @GetMapping(path="/getSprint")
    @ResponseBody
    public Sprint getSprint(Integer id) throws SprintNotFoundException {
        return sprintService.getSprint(id);
    }

    //GET zobrazeni vsech sprintu
    @GetMapping(path="/allSprints")
    @ResponseBody
    public Iterable <Sprint> getAllSprints() {
        return sprintRepository.findAll();
    }




    //Zobrazeni tabulky vsech sprintu s tlacitkem o informacich
    @RequestMapping("/sprintsTab")
    public String sprintsTab(Model model) {
        model.addAttribute("sprints", sprintService.getAllSprints());
        return "sprintsScr";
    }
    //Zobrazeni sprintu a detailnich informaci
    @RequestMapping("/sprintInformation")
    public String sprintInformation(Integer sprintId, Model model) throws SprintNotFoundException {
        Iterable<Story3> allStories = storyService.getAllStories();
        Iterable<Team> allTeams = teamService.getAllTeams();
        List<Team> teams = new ArrayList<>();
        List<Story3> stories = new ArrayList<>();
        List<Integer> remCap = new ArrayList<>();
        for(Story3 story : allStories){
            if(story.getSprint() != null && story.getSprint().getSprintId() == sprintId){
                stories.add(story);
            }

        }
        for(Team team : allTeams){
            for(Story3 story : stories){
                if(teams.contains(team)) {
                } else if(team.getTeamId() == story.getTeam().getTeamId()){
                    teams.add(team);
                }
            }

        }

        for(Team team : teams){
            team.setRemTeamCapacity(team.getTeamCapacity());
            for(Story3 story : stories){
                if(team.getTeamId() == story.getTeam().getTeamId()){
                    team.setRemTeamCapacity(team.getRemTeamCapacity() - story.getOriginalEstimate());
                }
            }
        }
        model.addAttribute("sprintInfo", sprintService.getSprint(sprintId));
        model.addAttribute("stories", stories);
        model.addAttribute("teams", teams);
        return "sprintInformationScr";
    }




    //Zobrazeni tabulky vsech sprintu s upravami sprintu
    @RequestMapping("/sprintsTabEdit")
    public String sprintsTabEdit(Model model) {
        model.addAttribute("sprints", sprintService.getAllSprints());
        return "sprintsScrEdit";
    }

    //Formulář pro vytvoření nového sprintu
    @PostMapping("/sprintForm")
    public String sprintForm(@ModelAttribute Sprint sprint) {
        sprintRepository.save(sprint);
        return "redirect:/sprintsTabEdit";
    }

    //uprava sprintu
    @GetMapping(path = "/editSprint")
    public String editSprint(Integer sprintId, Model model) throws SprintNotFoundException {
        Sprint sprint = sprintService.getSprint(sprintId);
        model.addAttribute("sprint", sprint);
        return "sprintUpdate";
    }
    @PostMapping(path = "/editSprint")
    public String editSubmit(@ModelAttribute Sprint sprint) {
        sprintService.save(sprint);
        return "redirect:/sprintsTabEdit";
    }

    //odstranení sprintu
    @PostMapping("/deleteSprint")
    public String deleteSprint(Integer sprintId){
        sprintRepository.deleteById(sprintId);
        return "redirect:/sprintsTabEdit";
    }

}
