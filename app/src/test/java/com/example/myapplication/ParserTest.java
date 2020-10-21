package com.example.myapplication;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ParserTest {
    private static Tokenizer tokenizer;

    private static final String simpleExample = "Belconnen";
    private static final String middleExample = "price < 550";
    private static final String complexExample1 = "suburb = woden; bedroom > 1";
    private static final String complexExample2 = "price > 300; city; allowPet= true";

    @Test(timeout = 1000)
    public void simpleExp1() {
        tokenizer = new Tokenizer(("<220"));
        Parser parser = new Parser((tokenizer));
        List<Requirement> requirements = (parser).parseExp();
        assertEquals(requirements.get(0), new Requirement("price", Condition.LESS, "220"));
        assertEquals("Requirement{attribute='price', condition=LESS, value='220'}", requirements.get(0).toString());
//        System.out.println(requirements.size());
    }

    @Test(timeout=1000)
    public void simpleExp2() {
        tokenizer = new Tokenizer(simpleExample);
        List<Requirement> requirements = (new Parser(tokenizer)).parseExp();
        assertEquals(requirements.get(0).getAttribute(), "Belconnen");
        assertEquals(requirements.get(0).getCondition(), Condition.UNKNOWN);
        assertEquals(requirements.get(0).getValue(), "Belconnen");
//        System.out.println(new Parser(new Tokenizer("price;")).parseExp());
//        System.out.println(new Parser(new Tokenizer(simpleExample)).parseExp().toString());
    }

    @Test(timeout=1000)
    public void middleExp() {
        tokenizer = new Tokenizer((middleExample));
        List<Requirement> requirements = (new Parser(tokenizer)).parseExp();
        assertEquals(requirements.get(0).getAttribute(), "price");
        assertEquals(requirements.get(0).getCondition(), Condition.LESS);
        assertEquals(requirements.get(0).getValue(), "550");
    }

    @Test(timeout = 1000)
    public void complexExp1() {
        tokenizer = new Tokenizer(complexExample1);
        List<Requirement> requirements = (new Parser(tokenizer)).parseExp();
        assertEquals(requirements.get(0), new Requirement("suburb", Condition.EQUAL, "woden"));
        assertEquals(requirements.get(1), new Requirement("bedroom", Condition.GREATER, "1"));
    }

    @Test(timeout = 1000)
    public void complexExp2() {
        tokenizer = new Tokenizer(complexExample2);
        List<Requirement> requirements = (new Parser(tokenizer)).parseExp();
        assertEquals(requirements.get(0), new Requirement("price", Condition.GREATER, "300"));
        assertEquals(requirements.get(1), new Requirement("city", Condition.UNKNOWN, "city"));
        assertEquals(requirements.get(2), new Requirement("allowPet", Condition.EQUAL, "true"));
    }
}
