package com.example.demo;

import com.example.demo.entities.Player;
import com.example.demo.entities.Team;
import com.example.demo.service.PlayerService;
import com.example.demo.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Math.max;
import static java.lang.System.exit;

@Component
public class CommandLine implements CommandLineRunner {
    private PlayerService playerService;
    private TeamService teamService;

    @Autowired
    public CommandLine(PlayerService playerService, TeamService teamService){
        this.playerService = playerService;
        this.teamService = teamService;
    }

    @Override
    public void run(String... args) {
        while(true){
            System.out.println("Choose task:");
            chooseTask();
        }
    }

    public void chooseTask(){
        Scanner scan = new Scanner(System.in);
        System.out.println("1. Add player");
        System.out.println("2. Find player by ID");
        System.out.println("3. Find all players");
        System.out.println("4. Delete player");
        System.out.println("5. Add team");
        System.out.println("6. Find team by name");
        System.out.println("7. Find all teams");
        System.out.println("8. Exit");

        String task = scan.nextLine();

        switch(task){
            case "1":
                System.out.println("Set name : ");
                String playerName = scan.nextLine();
                System.out.println("Set team name : ");
                String tName = scan.nextLine();
                Optional<Team> playerTeam = teamService.findByName(tName);

                if(playerTeam.isEmpty()){
                    System.out.println("Team not found\n");
                    break;
                }

                System.out.println("Set appearances : ");
                int appearances = Integer.parseInt(scan.nextLine());
                System.out.println("Set average rating : ");
                double averageRating = Double.parseDouble(scan.nextLine());

                int id = playerService.findAll().stream().max(Comparator.comparing(Player::getId)).get()
                        .getId() + 1;

                playerService.add(Player.builder()
                        .id(id)
                        .name(playerName)
                        .team(playerTeam.get())
                        .appearances(appearances)
                        .averageRating(averageRating)
                        .build());

                System.out.println("Team was added correctly\n");
                break;
            case "2":
                System.out.println("ID : ");
                int playerId = Integer.parseInt(scan.nextLine());

                if(playerService.findByKey(playerId).isPresent())
                {
                    Player value = playerService.findByKey(playerId).get();
                    System.out.println("Name - " + value.getName());
                    System.out.println("Appearances - " + value.getAppearances());
                    System.out.println("Average rating - " + value.getAverageRating());
                    System.out.println("Team - " + value.getTeam().getName());
                    System.out.println("ID - " + value.getId() + "\n");
                }
                else{
                    System.out.println("Team not found\n");
                }
                break;
            case "3":
                if(playerService.findAll().isEmpty())
                    System.out.println("Players not found\n");
                else playerService.findAll().forEach(value -> {
                    System.out.println("Name - " + value.getName());
                    System.out.println("Appearances - " + value.getAppearances());
                    System.out.println("Average rating - " + value.getAverageRating());
                    System.out.println("Team - " + value.getTeam().getName());
                    System.out.println("ID - " + value.getId() + "\n");
                });
                break;
            case "4":
                System.out.println("ID of player to delete:");
                Integer playerToDeleteId = Integer.valueOf(scan.nextLine());

                playerService.delete(playerToDeleteId);
                System.out.println();
                break;
            case "5":
                System.out.println("Set name : ");
                String name = scan.nextLine();
                System.out.println("Set league : ");
                String league = scan.nextLine();
                System.out.println("Set points : ");
                int points = Integer.parseInt(scan.nextLine());

                teamService.add(Team.builder()
                        .name(name)
                        .league(league)
                        .points(points)
                        .build());

                System.out.println("Team was added correctly\n");
                break;
            case "6":
                System.out.println("Name : ");
                String teamName = scan.nextLine();

                if(teamService.findByName(teamName).isPresent())
                {
                    Team value = teamService.findByName(teamName).get();
                    System.out.println("Name - " + value.getName());
                    System.out.println("League - " + value.getLeague());
                    System.out.println("Points - " + value.getPoints());
                }
                else{
                    System.out.println("Team not found\n");
                }
                System.out.println();
                break;
            case "7":
                teamService.findAll().forEach(
                        team -> {
                            System.out.println("Name - " + team.getName());
                            System.out.println("League - " + team.getLeague());
                            System.out.println("Points - " + team.getPoints() + "\n");
                        }
                );
                break;
            case "8":
                exit(0);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + task);
        }
    }
}
