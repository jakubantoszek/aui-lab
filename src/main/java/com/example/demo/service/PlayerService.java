package com.example.demo.service;

import com.example.demo.entities.Player;
import com.example.demo.repo.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PlayerService {
    private PlayerRepository repository;

    @Autowired
    public PlayerService(PlayerRepository repository){
        this.repository = repository;
    }

    public void add(Player p) {
        repository.add(p);
    }

    public Optional<Player> findByKey(Integer id) {
        return repository.findByKey(id);
    }

    public ArrayList<Player> findAll() {
        return repository.findAll();
    }

    public void delete(Integer id) {
        repository.delete(id);
    }
}
