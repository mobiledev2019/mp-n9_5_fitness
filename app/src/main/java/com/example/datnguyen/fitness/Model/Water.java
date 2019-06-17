package com.example.datnguyen.fitness.Model;

import java.io.Serializable;

public class Water implements Serializable {
    private int id;
    private int waterNumber;
    private String inputDay;
    private int waterMl;

    public Water() {
    }

    public Water(int id, String inputDay, int waterNumber, int waterMl) {
        this.id = id;
        this.waterNumber = waterNumber;
        this.inputDay = inputDay;
        this.waterMl = waterMl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWaterNumber() {
        return waterNumber;
    }

    public void setWaterNumber(int waterNumber) {
        this.waterNumber = waterNumber;
    }

    public String getInputDay() {
        return inputDay;
    }

    public void setInputDay(String inputDay) {
        this.inputDay = inputDay;
    }

    public int getWaterMl() {
        return waterMl;
    }

    public void setWaterMl(int waterMl) {
        this.waterMl = waterMl;
    }

    @Override
    public String toString() {
        return "Water{" +
                "id='" + id + '\'' +
                ", waterNumber=" + waterNumber +
                ", inputDay=" + inputDay +
                ", waterMl=" + waterMl +
                '}';
    }
}
