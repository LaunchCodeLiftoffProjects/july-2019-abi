package org.launchcode.liftoff.forms;


import org.launchcode.liftoff.model.Player;
import org.launchcode.liftoff.models.Roster;

public class AddRosterPlayerForm {

    private int rosterId;

    private int playerId;

    private Iterable<Player> players;

    private Roster roster;

    public AddRosterPlayerForm() { }

    public AddRosterPlayerForm(Iterable<Roster> cheeses, Roster roster){
        this.players=players;
        this.roster=roster;
    }

    public int getRosterId() {
        return rosterId;
    }

    public void setRosterId(int rosterId) {
        this.rosterId = rosterId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public Roster getRoster() {
        return roster;
    }

    public void setRoster(Roster roster) {
        this.roster = roster;
    }

    public Iterable<Player> getPlayers() {
        return players;
    }
}

