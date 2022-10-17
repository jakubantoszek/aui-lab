package com.example.demo;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
public class TeamRepository implements ObjectRepository<Team> {

    private Map<String, Team> repository;

    public TeamRepository(){
        repository = new HashMap<String, Team>();
    }

    @Override
    public void add(Team t) {
        repository.put(t.getName(), t);
    }

    public Team getByKey(String name) {
        return repository.get(name);
    }

    @Override
    public ArrayList<Team> getAllObjects() {
        return new ArrayList<Team>(repository.values());
    }

    public void delete(String name) {
        repository.remove(name);
    }
}
