package com.oneseven.Project07.entity;


import javax.persistence.*;

@Entity
@Table(name = "Story3")
public class Story3 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer storyId;

    //@Column(name="Popis", length = 255, nullable = false, unique = false)
    private String summary;

    //@Column(name="Potřebný čas", length = 20, nullable = false, unique = false)
    private Integer originalEstimate;

    //@Column(name="Zbývající čas", length = 20, nullable = false, unique = false)
    private Integer remainingEstimate;

    //@Column(name="Strávený čas", length = 20, nullable = true, unique = false)
    private Integer timeSpent;

    //@Column(name = "Stav story", length = 55, nullable = false, unique = false)
    @Enumerated(EnumType.STRING)
    private Status storyStatus;


    @ManyToOne
    @JoinColumn(name = "teamId", nullable = true)
    private Team team;

    @ManyToOne
    @JoinColumn(name = "sprintId", nullable = true)
    private Sprint sprint;

    public Integer getStoryId() {
        return storyId;
    }

    public void setStoryId(Integer storyId) {
        this.storyId = storyId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getOriginalEstimate() {
        return originalEstimate;
    }

    public void setOriginalEstimate(Integer originalEstimate) {
        this.originalEstimate = originalEstimate;
    }

    public Integer getRemainingEstimate() {
        return remainingEstimate;
    }

    public void setRemainingEstimate(Integer remainingEstimate) {
        this.remainingEstimate = remainingEstimate;
    }

    public Integer getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(Integer timeSpent) {
        this.timeSpent = timeSpent;
    }

    public Status getStoryStatus() {
        return storyStatus;
    }

    public void setStoryStatus(Status storyStatus) {
        this.storyStatus = storyStatus;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }
}
