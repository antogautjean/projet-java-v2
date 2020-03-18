package org.antogautjean.Controller;

import org.antogautjean.model.FileImporter;
import org.antogautjean.model.Product;
import org.antogautjean.view.App;

import java.io.IOException;
import java.util.HashMap;

public class StockController {

    private String stockName;

    private HashMap<String, Product> stock = new HashMap<>(1);

    public StockController(String stockName) throws IOException {

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

    public void loadProducts(String productFilePath, String priceFilePath) throws IOException {

        new FileImporter(
                "./src/main/java/org/antogautjean/data/elements.csv",
                "./src/main/java/org/antogautjean/data/prix.csv",
                this);
    }

    public boolean export(String csvFilePath){
        return false;
    }

    public String toString(){
        return "Il y a " + this.stock.size() + " produits en stock (valeur <sum> €)." ;
    }
}
