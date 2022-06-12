package com.oneseven.Project07.service;

import com.oneseven.Project07.entity.Status;
import com.oneseven.Project07.entity.Story3;
import com.oneseven.Project07.exception.StoryNotFoundException;
import com.oneseven.Project07.repository.StoryRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class StoryService3 {

    private final StoryRepository repository;

    @Autowired
    public StoryService3(StoryRepository repository) {
        this.repository = repository;
    }

    public Story3 getStory(Integer id) throws StoryNotFoundException {
        Optional<Story3> story = repository.findById(id);
        if(story.isPresent()){
            return story.get();
        } else {
            throw new StoryNotFoundException("Story with id " +id+ " not found.");
        }
    }

    public String addNewStory(String summary, Integer timeE, Integer timeO) {
        Story3 story = new Story3();
        story.setSummary(summary);
        story.setOriginalEstimate(timeE);
        story.setRemainingEstimate(timeO);
        story.setStoryStatus(Status.FREE);
        story.setTeam(null);
        story.setSprint(null);
        repository.save(story);
        return "Story přidáno";
    }

    public void save(Story3 story){
        repository.save(story);
    }

    public Iterable<Story3> getAllStories() {
        return repository.findAll();
    }
}
