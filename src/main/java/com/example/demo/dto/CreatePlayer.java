package com.example.demo.dto;

import com.example.demo.entities.Team;
import lombok.*;
import com.example.demo.entities.Player;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreatePlayer {
    private String name;
    private int appearances;
    private double averageRating;
    private String team;

    public static Function<CreatePlayer, Player> dtoToEntity(
            Function<String, Team> teamFunction) {
        return request -> Player.builder()
                .name(request.getName())
                .appearances(request.getAppearances())
                .averageRating(request.getAverageRating())
                .team(teamFunction.apply(request.getTeam()))
                .build();
    }
}