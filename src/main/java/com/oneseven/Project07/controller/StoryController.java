package com.oneseven.Project07.controller;

import com.oneseven.Project07.entity.*;
import com.oneseven.Project07.exception.StoryNotFoundException;
import com.oneseven.Project07.repository.StoryRepository;
import com.oneseven.Project07.service.SprintService;
import com.oneseven.Project07.service.StoryService3;
import com.oneseven.Project07.service.TeamService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("")
public class StoryController {

    private final StoryService3 storyService;
    private final TeamService teamService;
    private final SprintService sprintService;

    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    public StoryController(StoryService3 storyService, TeamService teamService, SprintService sprintService) {
        this.storyService = storyService;
        this.teamService = teamService;
        this.sprintService = sprintService;
    }

    //POST pridani story
    @PostMapping(path="/addStory")
    @ResponseBody
    public String addNewStory (@RequestParam String summary, Integer timeE, Integer timeO) {
        return storyService.addNewStory(summary, timeE, timeO);
    }

    //GET zobrazeni story pomoci id
    @GetMapping(path="/getStory")
    @ResponseBody
    public Story3 getStory(Integer id) throws StoryNotFoundException {
        return storyService.getStory(id);
    }

    //zobrazeni vsech story
    @GetMapping(path="/allStories")
    @ResponseBody
    public Iterable<Story3> getAllStories() {
        return storyRepository.findAll();
    }






    //zobrazeni tabulky stories
    @RequestMapping("/storiesTab")
    public String storiesTab(Model model) {
        model.addAttribute("stories", storyService.getAllStories());
        return "storiesScr";
    }

    //formular pro vytvoreni nove story
    @PostMapping("/storyForm")
    public String storyForm(@ModelAttribute Story3 story3) {
        storyRepository.save(story3);
        return "redirect:/storiesTab";
    }

    //uprava story
    @GetMapping("/editStory")
    public String editStory(Integer storyId, Model model) throws StoryNotFoundException {
        Story3 story = storyService.getStory(storyId);
        Iterable<Team> teams = teamService.getAllTeams();
        Iterable<Sprint> sprints = sprintService.getAllSprints();
        model.addAttribute("story", story);
        model.addAttribute("teams", teams);
        model.addAttribute("sprints", sprints);
        return "storyUpdate";
    }
    @PostMapping("/editStory")
    public String editSubmit(@ModelAttribute Story3 story) {
        storyService.save(story);
        return "redirect:/storiesTabEdit";
    }

    //odstranení story
    @PostMapping("/deleteStory")
    public String deleteStory(Integer storyId){
        storyRepository.deleteById(storyId);
        return "redirect:/storiesTab";
    }






    //zobrazeni tabulky stories s tymy a sprinty
    @RequestMapping("/storiesTabEdit")
    public String storiesTabEdit(Model model) {
        Iterable<Story3> allStories = storyService.getAllStories();
        List<Story3> stories = new ArrayList<>();
        for(Story3 story : allStories) {
            if(story.getTeam() != null){
                stories.add(story);
            }
        }
        model.addAttribute("stories", stories);
        return "storyScrEdit";
    }

    //odstranení story
    @PostMapping("/deleteStory2")
    public String deleteStory2(Integer storyId){
        storyRepository.deleteById(storyId);
        return "redirect:/storiesTabEdit";
    }


}
