package org.antogautjean.model;

import org.antogautjean.Observer.Observable;
import org.antogautjean.Observer.Observer;

import java.util.ArrayList;

public class FactoryModel implements Observable {

    private String factoryName;

    private Stock factoryStock;

    private ArrayList<Line> factoryLine = new ArrayList<>();

    private ArrayList<Observer> listObserver = new ArrayList<Observer>();

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

    //Implémentation du pattern observer
    public void addObserver(Observer obs) {
        this.listObserver.add(obs);
    }

    public void notifyObserver(String str) {

        for(Observer obs : listObserver)
            obs.update(str);
    }

    public void removeObserver() {
        listObserver = new ArrayList<>();
    }
}