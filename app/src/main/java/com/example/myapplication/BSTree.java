package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class BSTree {
    Property property;
    BSTree left, right;
    List<Property> result;
    String resultStr;
    int mark;
    PriorityQueue pq = new PriorityQueue();

    BSTree(Property property) {
        this.property = property;
        this.left = new BSTree();
        this.right = new BSTree();
        this.result = new ArrayList<>();
        resultStr = "";
        this.mark = 0;
    }

    BSTree() {
        this.property = new Property();
        this.result = new ArrayList<>();
        resultStr = "";
        this.mark = 0;
    }

    boolean add(Property element) {
        if (this.property == null || this.property.id == 0) {
            this.property = element;
            return true;
        }

        if (element.compareTo(property) < 0) {
            if (left == null) {
                left = new BSTree(element);
                return true;
            }
            return left.add(element); //recursively find the location of the element until there is a null.
        } else if (element.compareTo(property) > 0) {
            if (right == null) {
                right = new BSTree(element);
                return true;
            }
            return right.add(element);
        } else {
            return false;
        }
    }

    public void inOrderTraverse(BSTree x, String exp) {
        if (x != null) {
            inOrderTraverse(x.left, exp);
            if (x.property.id != 0) {
                if (x.checkConditions(exp)) {
                    result.add(x.property);
                    resultStr += x.property.toString();
                }
            }
            inOrderTraverse(x.right, exp);
        }
    }

    private boolean checkConditions(String exp) {
        Tokenizer t = new Tokenizer(exp);
        List<Requirement> requirements = new Parser(t).parseExp();

        for (Requirement r : requirements) {
            if (!checkAttribute(r)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkAttribute(Requirement r) {
        String currentVal = this.property.getAttribute(r.attribute);
        String desiredVal = r.value;

        if (r.condition == Condition.GREATER) {
            if (Double.parseDouble(currentVal) > Double.parseDouble(desiredVal)) {
                mark += 20;
            }
            return Double.parseDouble(currentVal) > Double.parseDouble(desiredVal);
        } else if (r.condition == Condition.LESS) {
            if (Double.parseDouble(currentVal) < Double.parseDouble(desiredVal)) {
                mark += 20;
            }
            return Double.parseDouble(currentVal) < Double.parseDouble(desiredVal);
        } else if (r.condition == Condition.EQUAL||r.condition ==Condition.UNKNOWN) {
            if (currentVal.equals(desiredVal)) {
                mark += 20;
            }
            return currentVal.equals(desiredVal);
        }


        return true;
    }

    @Override
    public String toString() {
        return "BSTree{" +
                "property = " + property.id +
                ", left = " + left +
                ", right = " + right +
                '}';
    }
}
