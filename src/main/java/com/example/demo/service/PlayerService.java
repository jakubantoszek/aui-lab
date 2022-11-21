package com.example.demo.service;

import com.example.demo.entities.Player;
import com.example.demo.repo.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    private PlayerRepository repository;

    @Autowired
    public PlayerService(PlayerRepository repository){
        this.repository = repository;
    }

    public void add(Player p) {
        repository.save(p);
    }

    public Optional<Player> findByKey(Integer id) {
        return repository.findById(id);
    }

    public List<Player> findAll() {
        return repository.findAll();
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
