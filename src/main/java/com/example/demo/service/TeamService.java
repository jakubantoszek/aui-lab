package com.example.demo.service;

import com.example.demo.entities.Team;
import com.example.demo.repo.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class TeamService {
    private TeamRepository repository;

    @Autowired
    public TeamService(TeamRepository repository){
        this.repository = repository;
    }

    public void add(Team t) {
        repository.add(t);
    }

    public Optional<Team> findByKey(String name) {
        return repository.findByKey(name);
    }

    public ArrayList<Team> findAll() {
        return repository.findAll();
    }

    public void delete(String name) {
        repository.delete(name);
    }
}
