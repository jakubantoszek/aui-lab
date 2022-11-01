package com.example.demo.entities;


import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(callSuper = true)
public class Player {
    private Integer id;
    private String name;
    private int appearances;
    private double averageRating;
    private Team team;
}
