package com.example.demo.dto;

import com.example.demo.entities.Player;
import com.example.demo.entities.Team;
import lombok.*;

import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetPlayersFromTeam {
    @Singular
    List<Player> players;

    public static Function<Team, GetPlayersFromTeam> entityToDto() {
        return teams -> {
            GetPlayersFromTeamBuilder response = GetPlayersFromTeam.builder();
            teams.getSquad().stream()
                    .map(player -> Player.builder()
                            .id(player.getId())
                            .name(player.getName())
                            .build())
                    .forEach(response::player);
            return response.build();
        };
    }
}