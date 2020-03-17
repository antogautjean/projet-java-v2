package org.antogautjean.model;

import java.util.ArrayList;

public class FactoryModel {

    private String factoryName;

    private Stock factoryStock;

    private ArrayList<Line> factoryLine = new ArrayList<>();

    public String getFactoryName(){
        return this.factoryName;
    }

    public Stock getFactoryStock(){
        return this.factoryStock;
    }

    public Line getLine(int lineID){
        try {
            return this.factoryLine.get(lineID);
        }
        catch (Exception e){
            return null;
        }
    }

    public void addLine(Line line){
        this.factoryLine.add(line);
    }

    public void removeLine(int lineID){
        try{
            factoryLine.remove(lineID);
        }
        catch (Exception e){

        }
    }

    public FactoryModel(String factoryName){
        this.factoryName = factoryName;
    }
}