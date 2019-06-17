package com.example.datnguyen.fitness.Model;

public class TempExercise {
    private int id;
    private String name;
    private int check;

    public TempExercise() {
    }

    public TempExercise( String name, int check) {
        this.name = name;
        this.check = check;
    }

    public TempExercise(int id, String name, int check) {
        this.id = id;
        this.name = name;
        this.check = check;
    }

    public int isCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
