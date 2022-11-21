package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entities.Team;
import com.example.demo.service.PlayerService;
import com.example.demo.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("api/teams")
public class TeamController {
    private final TeamService teamService;
    private final PlayerService playerService;

    @Autowired
    public TeamController(TeamService teamService, PlayerService playerService) {
        this.teamService = teamService;
        this.playerService = playerService;
    }

    @GetMapping
    public ResponseEntity<GetTeams> getTeams(){
        List<Team> teams = teamService.findAll();
        Function<Collection<Team>, GetTeams> mapper = GetTeams.entityToDto();
        GetTeams response = mapper.apply(teams);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{name}")
    public ResponseEntity<GetTeam> getTeam(@PathVariable("name") String name){
        return teamService.findByName(name)
                .map(result -> ResponseEntity.ok(GetTeam.entityToDto().apply(result)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{name}/players")
    public ResponseEntity<GetPlayersFromTeam> getPlayersFromTeam(@PathVariable("name") String name){
        return teamService.findByName(name)
                .map(result -> ResponseEntity.ok(GetPlayersFromTeam.entityToDto().apply(result)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createTeam(@RequestBody CreateTeam request, UriComponentsBuilder builder){
        Team team = CreateTeam.dtoToEntity().apply(request);
        team = teamService.add(team);
        return ResponseEntity.created(builder.pathSegment("api", "teams", "{name}")
                .buildAndExpand(team.getName()).toUri()).build();
    }

    @DeleteMapping("{name}")
    public ResponseEntity<Void> deleteTeam(@PathVariable("name") String name){
        Optional<Team> team = teamService.findByName(name);
        if(team.isPresent()){
            teamService.delete(team.get().getName());
            return ResponseEntity.accepted().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{name}")
    public ResponseEntity<Void> updateTeam(@RequestBody UpdateTeam request, @PathVariable("name") String name){
        Optional<Team> team = teamService.findByName(name);
        if(team.isPresent()){
            UpdateTeam.dtoToEntity().apply(team.get(), request);
            teamService.update(team.get());
            return ResponseEntity.accepted().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
