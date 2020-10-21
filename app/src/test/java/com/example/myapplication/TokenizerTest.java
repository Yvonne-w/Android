package com.example.myapplication;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TokenizerTest {
    @Test
    public void testSingle() {
        // test single tokens of ; > = < and chars, digits
        Tokenizer semicolon = new Tokenizer(";");
        assertEquals(Token.Type.SEP, semicolon.current().type());
        assertEquals(1, semicolon.tokenLen);
//        assertEquals(";", semicolon.getBuffer());

        Tokenizer greater = new Tokenizer(">");
        assertEquals(Token.Type.GREATER, greater.current().type());
        assertEquals(1, greater.tokenLen);

        Tokenizer less = new Tokenizer("<");
        assertEquals(Token.Type.LESS, less.current().type());
        assertEquals(1, less.tokenLen);

        Tokenizer equal = new Tokenizer("=");
        assertEquals(Token.Type.EQUAL, equal.current().type());
        assertEquals(1, equal.tokenLen);

        Tokenizer chars = new Tokenizer("b");
        assertEquals(Token.Type.CHAR, chars.current().type());
        assertEquals(1, chars.tokenLen);

        Tokenizer digits = new Tokenizer("123");
        assertEquals(Token.Type.INT, digits.current().type());
        assertEquals(3, digits.tokenLen);
        System.out.println(digits.current().token());
    }

    @Test
    public void testMultiple() {
        Tokenizer example1 = new Tokenizer("price < 300");
        StringBuilder ex1Tokens = new StringBuilder();
        List<Token.Type> ex1TokenTypes = new ArrayList<>();
        while (example1.hasNext()) {
            ex1Tokens.append(example1.current().token());
            ex1TokenTypes.add(example1.current().type());
            example1.next();
        }
        assertEquals("priceLESS300", ex1Tokens.toString());
        assertEquals(new ArrayList<>(Arrays.asList(Token.Type.CHAR, Token.Type.CHAR, Token.Type.CHAR, Token.Type.CHAR, Token.Type.CHAR, Token.Type.LESS, Token.Type.INT)), ex1TokenTypes);

        Tokenizer example2 = new Tokenizer("p>500; br=3");
        StringBuilder ex2Tokens = new StringBuilder();
        List<Token.Type> ex2TokenTypes = new ArrayList<>();
        while (example2.hasNext()) {
            ex2Tokens.append(example2.current().token());
            ex2TokenTypes.add(example2.current().type());
            example2.next();
        }
        assertEquals("pGREATER500;brEQUAL3", ex2Tokens.toString());
        assertEquals(new ArrayList<>(Arrays.asList(Token.Type.CHAR, Token.Type.GREATER, Token.Type.INT, Token.Type.SEP, Token.Type.CHAR, Token.Type.CHAR, Token.Type.EQUAL, Token.Type.INT)), ex2TokenTypes);


    }
}
