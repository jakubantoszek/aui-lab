package com.example.demo.service;

import com.example.demo.entities.Player;
import com.example.demo.entities.Team;
import com.example.demo.repo.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    private TeamRepository repository;

    @Autowired
    public TeamService(TeamRepository repository){
        this.repository = repository;
    }

    @Transactional
    public Team add(Team t) {
        return repository.save(t);
    }

    public Optional<Team> findByName(String name) {
        return repository.findById(name);
    }

    public List<Team> findAll() {
        return repository.findAll();
    }

    public void update(Team team) {
        repository.save(team);
    }

    public void delete(String name) {
        repository.deleteById(name);
    }
}
