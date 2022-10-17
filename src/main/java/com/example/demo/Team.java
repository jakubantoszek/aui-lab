package com.example.demo;

public class Team {
    private String name;
    private String league;
    private int points;

    public Team(String name, String league, int points){
        this.name = name;
        this.league = league;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
