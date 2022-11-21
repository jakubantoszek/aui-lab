package com.example.demo.dto;

import com.example.demo.entities.Team;
import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetTeams {
    @Singular
    List<Team> teams;

    public static Function<Collection<Team>, GetTeams> entityToDto() {
        return teams -> {
            GetTeamsBuilder response = GetTeams.builder();
            teams.stream()
                    .map(team -> Team.builder()
                            .name(team.getName())
                            .build())
                    .forEach(response::team);
            return response.build();
        };
    }
}