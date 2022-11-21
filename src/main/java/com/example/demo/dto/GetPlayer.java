package com.example.demo.dto;

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
public class GetPlayer {
    private Integer id;
    private String name;
    private int appearances;
    private double averageRating;
    private String team;

    public static Function<Player, GetPlayer> dtoToEntity() {
        return request -> GetPlayer.builder()
                .id(request.getId())
                .name(request.getName())
                .appearances(request.getAppearances())
                .averageRating(request.getAverageRating())
                .team(request.getTeam().getName())
                .build();
    }
}