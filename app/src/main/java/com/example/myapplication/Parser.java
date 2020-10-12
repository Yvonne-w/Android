package com.example.myapplication;

import java.util.*;

public class Parser {
    public Map<String, List<Requirement>> requirements;
    Tokenizer _tokenizer;

    public Parser(Tokenizer tokenizer) {
        _tokenizer = tokenizer;
        requirements = new HashMap<String, List<Requirement>>();
    }

    public Map<String, List<Requirement>> parseExp() {
        while (_tokenizer.hasNext()) {
            StringBuilder key = new StringBuilder();
            while (_tokenizer.current().type()== Token.Type.CHAR) {
                key.append(_tokenizer.current().token());
                _tokenizer.next();
            }
//            System.out.println(key);

            Requirement r = new Requirement();
            if (_tokenizer.current().type() == Token.Type.GREATER) {
                r.condition = Condition.valueOf(_tokenizer.current().token());
                _tokenizer.next();
            }
            if (_tokenizer.current().type() == Token.Type.LESS) {
                r.condition = Condition.valueOf(_tokenizer.current().token());
                _tokenizer.next();
            }
            if (_tokenizer.current().type() == Token.Type.EQUAL) {
                r.condition = Condition.valueOf(_tokenizer.current().token());
                _tokenizer.next();
            }
//            System.out.println(r.condition.toString());

            StringBuilder v = new StringBuilder();
            while (_tokenizer.hasNext() && _tokenizer.current().type() != Token.Type.SEP) {
                v.append(_tokenizer.current().token());
//            System.out.println("v "+v);
                _tokenizer.next();
            }
//            System.out.println(v.toString());

            r.value = String.valueOf(v);

            if (!requirements.containsKey(String.valueOf(key))) {
                List<Requirement> result = new ArrayList<>();
                result.add(r);
                requirements.put(String.valueOf(key),result);
            }else{
                requirements.get(String.valueOf(key)).add(r);
            }

            if (_tokenizer.hasNext() && _tokenizer.current().type() == Token.Type.SEP) {
                _tokenizer.next();
                continue;
            }
        }

//        System.out.println(requirements.keySet().toString()+"check here");
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
