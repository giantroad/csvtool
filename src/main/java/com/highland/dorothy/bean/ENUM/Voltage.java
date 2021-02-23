package com.highland.dorothy.bean.ENUM;

public enum Voltage {
    one("不满1KV"),
    two("10KV"),
    three("35KV"),
    four("110KV"),
    five("220KV");


    private String name;
    private Voltage(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

}
