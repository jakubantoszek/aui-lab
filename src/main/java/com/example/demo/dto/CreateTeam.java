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
public class CreateTeam {
    private String name;
    private String league;
    private int points;

    public static Function<CreateTeam, Team> dtoToEntity() {
        return request -> Team.builder()
                .name(request.getName())
                .league(request.getLeague())
                .points(request.getPoints())
                .build();
    }
}