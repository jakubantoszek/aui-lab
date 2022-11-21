package com.example.demo.controller;

import com.example.demo.dto.CreatePlayer;
import com.example.demo.dto.GetPlayer;
import com.example.demo.dto.UpdatePlayer;
import com.example.demo.entities.Player;
import com.example.demo.service.PlayerService;
import com.example.demo.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.GetPlayers;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("api/players")
public class PlayerController {
    private final TeamService teamService;
    private final PlayerService playerService;

    @Autowired
    public PlayerController(TeamService teamService, PlayerService playerService) {
        this.teamService = teamService;
        this.playerService = playerService;
    }

    @GetMapping
    public ResponseEntity<GetPlayers> getPlayers(){
        List<Player> players = playerService.findAll();
        Function<Collection<Player>, GetPlayers> mapper = GetPlayers.entityToDto();
        GetPlayers response = mapper.apply(players);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<GetPlayer> getPlayer(@PathVariable("id") Integer id){
        return playerService.findById(id)
               .map(result -> ResponseEntity.ok(GetPlayer.entityToDto().apply(result)))
               .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createPlayer(@RequestBody CreatePlayer request, UriComponentsBuilder builder){
        Player player = CreatePlayer
                .dtoToEntity(name -> teamService.findByName(name).orElseThrow())
                .apply(request);
        player = playerService.add(player);
        return ResponseEntity.created(builder.pathSegment("api", "players", "{id}")
                .buildAndExpand(player.getId()).toUri()).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable("id") Integer id){
        Optional<Player> player = playerService.findById(id);
        if(player.isPresent()){
            playerService.delete(player.get().getId());
            return ResponseEntity.accepted().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updatePlayer(@RequestBody UpdatePlayer request, @PathVariable("id") Integer id){
        Optional<Player> player = playerService.findById(id);
        if(player.isPresent()){
            UpdatePlayer.dtoToEntity().apply(player.get(), request);
            playerService.update(player.get());
            return ResponseEntity.accepted().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
