package com.example.myapplication;

public class Requirement {
    public Condition condition;
    public String value;

    public Requirement() {
        this.condition = Condition.UNKNOWN;
        this.value = "";
    }

    @Override
    public String toString() {
        return "com.example.myapplication.Requirement{" +
                "condition " + condition +
                ", value " + value +
                '}';
    }
}

enum Condition {
    //    id, state, type, price, suburb, latitude, longtitude, address, bedroom, bathroom, carspace, postcode, agent, allowpets
    GREATER, LESS, EQUAL, UNKNOWN
}


