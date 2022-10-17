package com.example.demo;


public class Player {
    private Integer id;
    private String name;
    private int appearances;
    private double averageRating;
    private Team team;

    public Player(Integer id, String name, int appearances, double averageRating, Team team){
        this.id = id;
        this.name = name;
        this.appearances = appearances;
        this.averageRating = averageRating;
        this.team = team;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAppearances() {
        return appearances;
    }

    public void setAppearances(int appearances) {
        this.appearances = appearances;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
