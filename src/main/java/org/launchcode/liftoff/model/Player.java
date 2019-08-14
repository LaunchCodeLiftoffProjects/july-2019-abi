package org.launchcode.liftoff.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Table(name = "player")
@Entity
public class Player {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, message="Name may not be empty")
    private String name;

    private String team;

    private String position;

    private Integer passingYards;

    private Integer rushingYards;

    private Integer touchdowns;

    public Player () {}

    public Player (String name) {this.name = name;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name; }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getPassingYards() {
        return passingYards;
    }

    public void setPassingYards(Integer passingYards) {
        this.passingYards = passingYards;
    }

    public Integer getRushingYards() {
        return rushingYards;
    }

    public void setRushingYards(Integer rushingYards) {
        this.rushingYards = rushingYards;
    }

    public Integer getTouchdowns() {
        return touchdowns;
    }

    public void setTouchdowns(Integer touchdowns) {
        this.touchdowns = touchdowns;
    }
}

