package com.adtimokhin.entity;

public class Player {

    private String name;
    private long score;
    private char windDirection;

    private int id;
    private static int counter=1;

    public Player() {
        this.id = counter++;
    }

    public Player(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public char getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(char windDirection) {
        this.windDirection = windDirection;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
