package com.example.datnguyen.fitness.Model;

public class Calories {
    private int id;
    private String day;
    private int calorie;

    public Calories(int id, String day, int calorie) {
        this.id = id;
        this.day = day;
        this.calorie = calorie;
    }

    public Calories(String day, int calorie) {
        this.day = day;
        this.calorie = calorie;
    }

    public Calories() {
    }

    public int getId() {
        return id;
    }

    public String getDay() {
        return day;
    }

    public int getCalorie() {
        return calorie;
    }
}
