package com.oneseven.Project07.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="Sprint")
public class Sprint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sprintId;

    //@Column(name="Název", length = 45, nullable = false, unique = false)
    private String sprintName;

    //@Column(name = "Datum začátku", length = 30, nullable = true, unique = false)
    private Date sprintStartDate;

    //@Column(name = "Datum konce", length = 30, nullable = true, unique = false)
    private Date sprintEndDate;

    public Integer getSprintId() {
        return sprintId;
    }

    public void setSprintId(Integer sprintId) {
        this.sprintId = sprintId;
    }

    public String getSprintName() {
        return sprintName;
    }

    public void setSprintName(String sprintName) {
        this.sprintName = sprintName;
    }

    public Date getSprintStartDate() {
        return sprintStartDate;
    }

    public void setSprintStartDate(Date sprintStartDate) {
        this.sprintStartDate = sprintStartDate;
    }

    public Date getSprintEndDate() {
        return sprintEndDate;
    }

    public void setSprintEndDate(Date sprintEndDate) {
        this.sprintEndDate = sprintEndDate;
    }
}
