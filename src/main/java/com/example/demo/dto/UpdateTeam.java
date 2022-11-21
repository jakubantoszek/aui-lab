package com.example.demo.dto;

import lombok.*;
import com.example.demo.entities.Team;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateTeam {
    private String name;
    private String league;
    private int points;

    public static BiFunction<Team, UpdateTeam, Team> dtoToEntity() {
        return (team, request) -> {
            team.setName(request.getName());
            team.setLeague(request.getLeague());
            team.setPoints(request.getPoints());

            return team;
        };
    }
}