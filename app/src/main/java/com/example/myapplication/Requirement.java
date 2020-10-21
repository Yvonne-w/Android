package com.example.myapplication;

public class Requirement {
    public String attribute;
    public Condition condition;
    public String value;

    public Requirement() {
        this.attribute = "";
        this.condition = Condition.UNKNOWN;
        this.value = "";
    }

    @Override
    public String toString() {
        return "Requirement{" +
                "attribute='" + attribute + '\'' +
                ", condition=" + condition +
                ", value='" + value + '\'' +
                '}';
    }
}

enum Condition {
    GREATER, LESS, EQUAL, UNKNOWN
}


