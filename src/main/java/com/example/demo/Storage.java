package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component("sc")
public class Storage {
    ArrayList<Player> playerList = new ArrayList<>();
    ArrayList<Team> teamList = new ArrayList<>();
}

