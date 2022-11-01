package com.example.demo.datastore;

import com.example.demo.entities.Player;
import com.example.demo.entities.Team;
import org.springframework.stereotype.Component;

import java.util.*;

@Component()
public class DataStore {
    Set<Player> players = new HashSet<>();
    Set<Team> teams = new HashSet<>();

    public synchronized List<Team> findAllTeams() {
        return new ArrayList<>(teams);
    }

    public synchronized Optional<Team> findTeam(String name) {
        return teams.stream()
                .filter(team -> team.getName().equals(name))
                .findFirst();
    }

    public synchronized void addTeam(Team t) throws IllegalArgumentException {
        findTeam(t.getName()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException("Two teams can't have the same name");
                },
               () -> teams.add(t));
    }

    public synchronized void deleteTeam(String name) throws IllegalArgumentException {
        findTeam(name).ifPresentOrElse(
                original -> teams.remove(original),
                () -> {
                    throw new IllegalArgumentException("Team with given name doesn't exist");
                });
    }

    public synchronized List<Player> findAllPlayers() {
        return new ArrayList<>(players);
    }

    public synchronized Optional<Player> findPlayer(Integer id) {
        return players.stream()
                .filter(player -> player.getId().equals(id))
                .findFirst();
    }

    public synchronized void addPlayer(Player p) throws IllegalArgumentException {
        findPlayer(p.getId()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException("Two players can't have the same ID");
                },
                () -> players.add(p));
    }

    public synchronized void deletePlayer(Integer id) throws IllegalArgumentException {
        findPlayer(id).ifPresentOrElse(
                original -> players.remove(original),
                () -> {
                    throw new IllegalArgumentException("Player with given ID doesn't exist");
                });
    }
}

