package org.antogautjean.model;

import org.antogautjean.Controller.LineController;
import org.antogautjean.Controller.StockController;

import java.util.ArrayList;

public class FactoryModel {

    private String factoryName;

    private StockController factoryStock;

    private ArrayList<LineController> factoryLine = new ArrayList<>();

    public String getFactoryName(){
        return this.factoryName;
    }

    public StockController getFactoryStock(){
        return this.factoryStock;
    }

    public LineController getLine(int lineID){
        try {
            return this.factoryLine.get(lineID);
        }
        catch (Exception e){
            return null;
        }
    }

    public void addLine(LineController line){
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