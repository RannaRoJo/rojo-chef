package com.rojodev.rojochef;

import java.io.Serializable;

public class Ingredient implements Serializable{

    private static final long serialVersionUID = 2L;

    float amount;
    String measurement;
    String name;

    public Ingredient(float amount, String measurement, String name, long id) {
        this.amount = amount;
        this.measurement = measurement;
        this.name = name;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "amount=" + amount +
                ", measurement='" + measurement + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
