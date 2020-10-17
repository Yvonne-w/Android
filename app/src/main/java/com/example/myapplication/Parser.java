package com.example.myapplication;

import java.util.*;

public class Parser {
//    public Map<String, List<Requirement>> requirements;
    public List<Requirement> requirements;
    Tokenizer _tokenizer;

    public Parser(Tokenizer tokenizer) {
        _tokenizer = tokenizer;
//        requirements = new HashMap<String, List<Requirement>>();
        requirements = new ArrayList<Requirement>();
    }

    public List<Requirement> parseExp(){
        while (_tokenizer.hasNext()) {
            StringBuilder key = new StringBuilder();
            while (_tokenizer.current().type()== Token.Type.CHAR) {
                key.append(_tokenizer.current().token());
                _tokenizer.next();
            }

            Requirement r = new Requirement();
            r.attribute = String.valueOf(key);

            if (_tokenizer.current().type() == Token.Type.GREATER) {
                r.condition = Condition.valueOf(_tokenizer.current().token());
                _tokenizer.next();
            }
            else if (_tokenizer.current().type() == Token.Type.LESS) {
                r.condition = Condition.valueOf(_tokenizer.current().token());
                _tokenizer.next();
            }
            else if (_tokenizer.current().type() == Token.Type.EQUAL) {
                r.condition = Condition.valueOf(_tokenizer.current().token());
                _tokenizer.next();
            }

            StringBuilder v = new StringBuilder();
            while (_tokenizer.hasNext() && _tokenizer.current().type() != Token.Type.SEP) {
                v.append(_tokenizer.current().token());
                _tokenizer.next();
            }
            r.value = String.valueOf(v);


            requirements.add(r);

            if (_tokenizer.hasNext() && _tokenizer.current().type() == Token.Type.SEP) {
                _tokenizer.next();
                continue;
            }
        }
        return requirements;
    }


    @Override
    public String toString() {
        return "Parser{" +
                "requirements=" + requirements +
                ", _tokenizer=" + _tokenizer +
                '}';
    }
}
