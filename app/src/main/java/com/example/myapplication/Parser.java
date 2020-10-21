package com.example.myapplication;

import java.util.*;

public class Parser {
    public List<Requirement> requirements;
    Tokenizer _tokenizer;

    public Parser(Tokenizer tokenizer) {
        _tokenizer = tokenizer;
        requirements = new ArrayList<>();
    }

    public List<Requirement> parseExp() {
        requirements = new ArrayList<>();

        while (_tokenizer.hasNext()) {
            Requirement r = new Requirement();
            //match to attribute
            StringBuilder key = new StringBuilder();
            if(_tokenizer.current().type()==Token.Type.NOT){
                _tokenizer.next();
                while(_tokenizer.current().type()!=Token.Type.SEP||_tokenizer.hasNext()){
                    key.append(_tokenizer.current().token());
                    _tokenizer.next();
                }
            }
            System.out.println(key);
            while (_tokenizer.hasNext() && _tokenizer.current().type() == Token.Type.CHAR) {
                key.append(_tokenizer.current().token());
                _tokenizer.next();
            }

            if (key.length() == 0) {
                key.append("price");
            }
            r.attribute = String.valueOf(key);

            //match to condition
            if (_tokenizer.hasNext()) {
                if (_tokenizer.current().type() == Token.Type.GREATER) {
                    r.condition = Condition.valueOf(_tokenizer.current().token());
                    _tokenizer.next();
                } else if (_tokenizer.current().type() == Token.Type.LESS) {
                    r.condition = Condition.valueOf(_tokenizer.current().token());
                    _tokenizer.next();
                } else if (_tokenizer.current().type() == Token.Type.EQUAL) {
                    r.condition = Condition.valueOf(_tokenizer.current().token());
                    _tokenizer.next();
                }  else if (_tokenizer.current().type() == Token.Type.NOT) {
                    r.condition = Condition.valueOf(_tokenizer.current().token());

                }else {
                    r.condition = Condition.UNKNOWN;
                    _tokenizer.next();
                }
            } else {
                r.condition = Condition.UNKNOWN;
                r.value = String.valueOf(key);
                requirements.add(r);
                return requirements;
            }

            //compare value
            StringBuilder v = new StringBuilder();
            while (_tokenizer.hasNext() && _tokenizer.current().type() != Token.Type.SEP) {
                v.append(_tokenizer.current().token());
                _tokenizer.next();
            }
            r.value = String.valueOf(v);

            if (v.length() == 0) {
                r.value = String.valueOf(key);
            }

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
