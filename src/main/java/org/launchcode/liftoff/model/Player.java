package org.launchcode.liftoff.model;

import com.sun.istack.internal.NotNull;


import javax.persistence.*;
import javax.validation.constraints.Size;

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

    @ManyToOne
    private User user;

    public Player () {}

    public Player (String name) {this.name = name}

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
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
