package org.antogautjean.Controller;
import org.antogautjean.model.Product;

import java.io.IOException;
import java.util.HashMap;

public class StockController {
    private HashMap<String, Product> stock = new HashMap<>();

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

    public HashMap<String, Product> getStock(){
        return this.stock;
    }

    public String toString(){
        return "Il y a " + this.stock.size() + " produits en stock (valeur <sum> €)." ;
    }
}
