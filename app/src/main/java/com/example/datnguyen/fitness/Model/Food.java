package com.example.datnguyen.fitness.Model;

public class Food {
    private int id;
    private String foodName;
    private int calories;
    private String inputDay;

    public Food() {
    }

    public Food(int id, String foodName, int calories, String inputDay) {
        this.id = id;
        this.foodName = foodName;
        this.calories = calories;
        this.inputDay = inputDay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getInputDay() {
        return inputDay;
    }

    public void setInputDay(String inputDay) {
        this.inputDay = inputDay;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", foodName='" + foodName + '\'' +
                ", calories=" + calories +
                ", inputDay='" + inputDay + '\'' +
                '}';
    }
}
