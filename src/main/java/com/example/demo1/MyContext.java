package com.example.demo1;

import com.dianping.cat.Cat;

import java.util.HashMap;

public class MyContext implements Cat.Context {

    private HashMap<String, String> hashMap = new HashMap<>();

    @Override
    public void addProperty(String key, String value) {
        hashMap.put(key, value);
    }

    @Override
    public String getProperty(String key) {
        return hashMap.get(key);
    }
}
