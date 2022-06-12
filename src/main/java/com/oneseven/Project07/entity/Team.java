package com.oneseven.Project07.entity;

import javax.persistence.*;

@Entity
@Table(name = "Team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teamId;

    private String teamName;
    private Integer teamCapacity;
    private Integer remTeamCapacity;

    public Team() {

    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getTeamCapacity() {
        return teamCapacity;
    }

    public void setTeamCapacity(Integer teamCapacity) {
        this.teamCapacity = teamCapacity;
    }

    public Integer getRemTeamCapacity() {
        return remTeamCapacity;
    }

    public void setRemTeamCapacity(Integer remTeamCapacity) {
        this.remTeamCapacity = remTeamCapacity;
    }
}
