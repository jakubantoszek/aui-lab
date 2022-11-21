package com.example.demo.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(callSuper = true)
@Entity
@Table(name = "teams")
public class Team {

    @Id
    private String name;

    private String league;

    private int points;

    @ToString.Exclude
    @OneToMany(mappedBy = "players", cascade = CascadeType.ALL)
    private List<Player> squad;
}
