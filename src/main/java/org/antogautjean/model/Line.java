package org.antogautjean.model;

import java.util.HashMap;

public class Line {

    private String code;

    private String name;

    private HashMap<Product, Integer> input;

    private HashMap<Product, Integer> output;

    private int duration;

    private int esteemedLifetime;

    private int numberOfExecution;

    private Double executionCost;

    public Line(String code, String name, HashMap<Product, Integer> input, HashMap<Product, Integer> output, int duration){

        this.code = code;
        this.name = name;
        this.input = input;
        this.output = output;
        this.duration = duration;
        this.numberOfExecution = 0;
    }

    public boolean execute(){
        //check input exists
        //check output exists
        //input stock - 1
        //output stock + 1
        this.numberOfExecution++;
        return true;
    }

}
