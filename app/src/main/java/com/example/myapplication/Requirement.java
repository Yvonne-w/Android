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

    public Requirement(String attribute, Condition condition, String value) {
        this.attribute = attribute;
        this.condition = condition;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Requirement{" +
                "attribute='" + attribute + '\'' +
                ", condition=" + condition +
                ", value='" + value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Requirement)) return false;
        Requirement requirement = (Requirement) o;
        return this.value.equals(requirement.value)  && this.condition.equals(requirement.condition) && this.attribute.equals(requirement.attribute);
    }

    public String getAttribute() {
        return attribute;
    }

    public Condition getCondition() {
        return condition;
    }

    public String getValue() {
        return value;
    }
}

enum Condition {
    GREATER, LESS, EQUAL, UNKNOWN
}


