package com.example.demo.dto;

import lombok.*;
import com.example.demo.entities.Player;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdatePlayer {
    private String name;
    private int appearances;
    private double averageRating;

    public static BiFunction<Player, UpdatePlayer, Player> dtoToEntity() {
        return (player, request) -> {
            player.setName(request.getName());
            player.setAppearances(request.getAppearances());

            return player;
        };
    }
}