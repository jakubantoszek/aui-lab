package com.example.demo.dto;

import lombok.*;
import com.example.demo.entities.Team;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetTeam {
    private String name;
    private String league;
    private int points;

    public static Function<Team, GetTeam> dtoToEntity() {
        return request -> GetTeam.builder()
                .name(request.getName())
                .league(request.getLeague())
                .points(request.getPoints())
                .build();
    }
}