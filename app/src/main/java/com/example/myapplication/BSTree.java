package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class BSTree {
    Property property;
    BSTree left, right;
    List<Property> result;
    String resultStr;

    BSTree(Property property) {
        this.property = property;
        this.left = new BSTree();
        this.right = new BSTree();
        this.result = new ArrayList<>();
        resultStr = "";
    }

    BSTree() {
        this.property = new Property();
        this.result = new ArrayList<>();
        resultStr = "";
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
//        Map<String, List<Requirement>> conditions = new Parser(t).parseExp();
        List<Requirement> requirements = new Parser(t).parseExp();

        for(Requirement r:requirements){
            if(!checkAttribute(r)){
                return false;
            }
        }

//        for (String s : conditions.keySet()) {
//            List<Requirement> requires = conditions.get(s);
//            for (Requirement r : requires) {
//                if (!checkAttribute(s, r)) return false;
//                System.out.println(conditions.toString());
//            }
//        }
        return true;
    }

    public boolean checkAttribute(Requirement r){
        String currentVal = this.property.getAttribute(r.attribute);
        String desiredVal = r.value;

                if (r.condition == Condition.GREATER) {
//            System.out.println(attributeVal+value);
            return Double.parseDouble(currentVal) > Double.parseDouble(desiredVal);
        }

        if (r.condition == Condition.LESS) {
//            System.out.println(attributeVal+value);
            return Double.parseDouble(currentVal) < Double.parseDouble(desiredVal);
        }

        if (r.condition == Condition.EQUAL) {
            return currentVal.equals(desiredVal);
        }


        return true;
    }

//    private boolean checkAttribute( Requirement r) {
////        String attributeVal = this.property.getAttribute(s);
//        Condition c = r.condition; // > < =
//        String value = r.value;
//
//        if (c == Condition.GREATER) {
////            System.out.println(attributeVal+value);
//            return Double.parseDouble(attributeVal) > Double.parseDouble(value);
//        }
//
//        if (c == Condition.LESS) {
////            System.out.println(attributeVal+value);
//            return Double.parseDouble(attributeVal) < Double.parseDouble(value);
//        }
//
//        if (c == Condition.EQUAL) {
//            return attributeVal.equals(value);
//        }
//
//        return false;
//    }

    @Override
    public String toString() {
        return "BSTree{" +
                "property = " + property.id +
                ", left = " + left +
                ", right = " + right +
                '}';
    }
}
