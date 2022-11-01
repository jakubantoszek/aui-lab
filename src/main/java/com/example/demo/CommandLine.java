package com.example.demo;

import com.example.demo.entities.Player;
import com.example.demo.entities.Team;
import com.example.demo.service.PlayerService;
import com.example.demo.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

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
        System.out.println("2. Find player");
        System.out.println("3. Find all players");
        System.out.println("4. Find all players from team");
        System.out.println("5. Delete player");
        System.out.println("6. Add team");
        System.out.println("7. Find team");
        System.out.println("8. Find all teams");
        System.out.println("9. Delete team");
        System.out.println("10. Exit");

        String task = scan.nextLine();

        switch(task){
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
            case "5":
                System.out.println("ID of player to delete:");
                Integer id = Integer.valueOf(scan.nextLine());

                playerService.delete(id);
                System.out.println();
                break;
            case "6":
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

                System.out.println("Team was added correctly");
                break;
            case "7":
                System.out.println("Name : ");
                String teamName = scan.nextLine();

                if(teamService.findByKey(teamName).isPresent())
                {
                    Team value = teamService.findByKey(teamName).get();
                    System.out.println("Name - " + value.getName());
                    System.out.println("League - " + value.getLeague());
                    System.out.println("Points - " + value.getPoints());
                }
                else{
                    System.out.println("Team not found");
                }
                System.out.println();
                break;
            case "8":
                teamService.findAll().forEach(
                        team -> {
                            System.out.println("Name - " + team.getName());
                            System.out.println("League - " + team.getLeague());
                            System.out.println("Points - " + team.getPoints() + "\n");
                        }
                );
                break;
            case "9":
                System.out.println("Name of team to delete:");
                String team_name = scan.nextLine();

                if(playerService.findByTeam(team_name).isEmpty()){
                    System.out.println("Team doesn't exist");
                }
                else{
                    playerService.findByTeam(team_name).forEach(
                            player -> playerService.delete(player.getId())
                    );
                    teamService.delete(team_name);
                }
                System.out.println();
                break;
            case "10":
                exit(0);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + task);
        }
    }
}
