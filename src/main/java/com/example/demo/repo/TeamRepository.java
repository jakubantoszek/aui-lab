package com.example.demo.repo;

import com.example.demo.datastore.DataStore;
import com.example.demo.entities.Team;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public class TeamRepository implements ObjectRepository<Team, String> {

    private DataStore store;

    public TeamRepository(DataStore store){
        this.store = store;
    }

    @Override
    public void add(Team t) {
        store.addTeam(t);
    }

    @Override
    public Optional<Team> findByKey(String name) {
        return store.findTeam(name);
    }

    @Override
    public ArrayList<Team> findAll() {
        return (ArrayList<Team>) store.findAllTeams();
    }

    @Override
    public void delete(String name) {
        store.deleteTeam(name);
    }
}
