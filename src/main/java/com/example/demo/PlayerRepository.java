package com.example.demo;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
public class PlayerRepository implements ObjectRepository<Player> {

    private Map<Integer, Player> repository;

    public PlayerRepository(){
        repository = new HashMap<Integer, Player>();
    }

    @Override
    public void add(Player p) {
        repository.put(p.getAppearances(), p);
    }

    public Player getByKey(Integer id) {
        return repository.get(id);
    }

    @Override
    public ArrayList<Player> getAllObjects() {
        return new ArrayList<Player>(repository.values());
    }

    public void delete(Integer id) {
        repository.remove(id);
    }
}
