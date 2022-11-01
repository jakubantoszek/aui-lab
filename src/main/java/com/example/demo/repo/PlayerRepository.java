package com.example.demo.repo;

import com.example.demo.datastore.DataStore;
import com.example.demo.entities.Player;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PlayerRepository implements ObjectRepository<Player, Integer> {

    private DataStore store;

    public PlayerRepository(DataStore store){
        this.store = store;
    }

    @Override
    public void add(Player p) {
        store.addPlayer(p);
    }

    @Override
    public Optional<Player> findByKey(Integer id) {
        return store.findPlayer(id);
    }

    public List<Player> findByTeam(String teamName) { return store.findPlayersByTeam(teamName); }

    @Override
    public ArrayList<Player> findAll() {
        return (ArrayList<Player>) store.findAllPlayers();
    }

    @Override
    public void delete(Integer id) {
        store.deletePlayer(id);
    }
}
