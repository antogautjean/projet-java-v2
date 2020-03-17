package org.antogautjean.model;

import org.antogautjean.model.Product;

import java.util.HashMap;

public class Stock {

    private String stockName;

    private HashMap<String, Product> stock = new HashMap<>(1);

    public Stock(String stockName){
        this.stockName = stockName;
    }

    public String getStockName(){
        return this.stockName;
    }

    public boolean addProduct(Product product){
        //tester si le produit existe deja dans la base de donnée
        try {
            this.stock.put(product.getCode(), product);
            return true;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteProduct(String code){
        try {
            this.stock.remove(code);
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteProduct(Product product){

        return this.deleteProduct(product.getCode());

    }

    public Product getProduct(String code){
        try {
            return this.stock.get(code);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean load(String csvFilePath){
        return false;
    }

    public boolean export(String csvFilePath){
        return false;
    }

    public String toString(){
        return "Il y a " + this.stock.size() + " produits en stock (valeur <sum> €)." ;
    }
}
