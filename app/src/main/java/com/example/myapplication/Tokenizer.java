package com.example.myapplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tokenizer {

    private String buffer;
    private Token currentToken;
    public int tokenLen;

    public Tokenizer(String text) {
        buffer = text;
        next();
        tokenLen = 0;

    }

    public void next() {
        buffer = buffer.trim();

        if (buffer.isEmpty()) {
            currentToken = null;
            return;
        }

        char firstChar = buffer.charAt(0);
        if (firstChar == ';') {
            currentToken = new Token(";", Token.Type.SEP);
            tokenLen = 1;
        }
        if (firstChar == '>') {
            currentToken = new Token("GREATER", Token.Type.GREATER);
            tokenLen = 1;
        }
        if (firstChar == '<') {
            currentToken = new Token("LESS", Token.Type.LESS);
            tokenLen = 1;
        }
        if (firstChar == '=') {
            currentToken = new Token("EQUAL", Token.Type.EQUAL);
            tokenLen = 1;
        }
        if (firstChar == '[') {
            currentToken = new Token("[", Token.Type.LSBRA);
            tokenLen = 1;
        }
        if (firstChar == ']') {
            currentToken = new Token("]", Token.Type.RSBRA);
            tokenLen = 1;
        }
        if (Character.isDigit(firstChar)) {
            String s = String.valueOf(firstChar);
            int i = 1;
            while (i < buffer.length() && Character.isDigit(buffer.charAt(i))) {
                s = s + buffer.charAt(i);
                i++;
            }
            currentToken = new Token(s, Token.Type.INT);
            tokenLen = currentToken.token().length();
        }
        if (Character.isLetter(firstChar)) {
            currentToken = new Token(String.valueOf(firstChar), Token.Type.CHAR);
            tokenLen = 1;
        }

        // Remove the extracted token from buffer
//        int tokenLen = currentToken.token().length();
        buffer = buffer.substring(tokenLen);
    }

    public Token current() {
        return currentToken;
    }

    public boolean hasNext() {
        return currentToken != null;
    }

}
