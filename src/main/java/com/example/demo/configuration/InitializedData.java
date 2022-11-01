package com.example.demo.configuration;

import com.example.demo.entities.Player;
import com.example.demo.entities.Team;
import com.example.demo.service.PlayerService;
import com.example.demo.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitializedData {
    private final PlayerService playerService;

    private final TeamService teamService;

    @Autowired
    public InitializedData(PlayerService ps, TeamService ts){
        this.playerService = ps;
        this.teamService = ts;
    }

    @PostConstruct
    private synchronized void init() {
        Team barcelona = Team.builder()
                .name("FC Barcelona")
                .league("La Liga")
                .points(25)
                .build();

        Team realMadrid = Team.builder()
                .name("Real Madryt")
                .league("La Liga")
                .points(28)
                .build();

        Team manCity = Team.builder()
                .name("Manchester City FC")
                .league("Premier League")
                .points(31)
                .build();

        Team leicester = Team.builder()
                .name("Leicester City")
                .league("Premier League")
                .points(6)
                .build();

        teamService.add(barcelona);
        teamService.add(realMadrid);
        teamService.add(manCity);
        teamService.add(leicester);

        Player lewandowski = Player.builder()
                .id(1)
                .name("Robert Lewandowski")
                .team(barcelona)
                .appearances(20)
                .averageRating(7.53)
                .build();

        Player benzema = Player.builder()
                .id(2)
                .name("Karim Benzema")
                .team(realMadrid)
                .appearances(12)
                .averageRating(7.25)
                .build();

        Player haaland = Player.builder()
                .id(3)
                .name("Erling Haaland")
                .team(manCity)
                .appearances(23)
                .averageRating(8.97)
                .build();

        Player vardy = Player.builder()
                .id(4)
                .name("Jamie Vardy")
                .team(leicester)
                .appearances(7)
                .averageRating(4.32)
                .build();

        playerService.add(lewandowski);
        playerService.add(benzema);
        playerService.add(haaland);
        playerService.add(vardy);
    }
}
