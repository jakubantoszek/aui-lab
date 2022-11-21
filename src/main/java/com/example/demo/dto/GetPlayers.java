package com.example.demo.dto;

import com.example.demo.entities.Player;
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
public class GetPlayers {
    @Singular
    List<Player> players;

    public static Function<Collection<Player>, GetPlayers> entityToDto() {
        return players -> {
            GetPlayersBuilder response = GetPlayers.builder();
            players.stream()
                    .map(player -> Player.builder()
                                .id(player.getId())
                                .name(player.getName())
                                .build())
                    .forEach(response::player);
            return response.build();
        };
    }
}