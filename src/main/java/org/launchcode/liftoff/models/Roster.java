package org.launchcode.liftoff.models;

import org.launchcode.liftoff.model.Player;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Roster {
    @Id
    @GeneratedValue
    private int id;


    @NotNull
    @Size(min=3, max=15)
    private String name;

    @ManyToMany
    private List<Player> players;

    public Roster() { }

    public Roster(String name) {this.name=name;}

    public int getId() {return id;}

    public void setId(int id) {this.id=id;}

    public String getName() {return name;}

    public void setName(String name) {this.name=name;}

    public List<Player>getPlayers(){return players;}

    public void addPlayer(Player player) {players.add(player);}
}
