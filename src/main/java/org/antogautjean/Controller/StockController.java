package org.antogautjean.Controller;

import org.antogautjean.model.Product;
import org.antogautjean.view.SpinnerCell;
import org.antogautjean.view.TableLinesFormatInterface;

import java.util.HashMap;

import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class StockController implements TableLinesFormatInterface {
    private HashMap<String, Product> stock = new HashMap<>();

    public boolean addProduct(Product product) {
        // tester si le produit existe deja dans la base de donnée
        try {
            this.stock.put(product.getCode(), product);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteProduct(String code) {
        try {
            this.stock.remove(code);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteProduct(Product product) {

        return this.deleteProduct(product.getCode());

    }

    public Product getProduct(String code) {
        try {
            return this.stock.get(code);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public HashMap<String, Product> getStock() {
        return this.stock;
    }

    public String toString() {
        return "Il y a " + this.stock.size() + " produits en stock (valeur <sum> €)" ;
    }

    public StockController clone() {
        StockController s = new StockController();
        for (Product product : this.stock.values()) {
            s.addProduct(product.clone());
        }
        return s;
    }

    public Double getSellValue() {
        Double output = 0.0;
        for (Product product : this.stock.values()) {
            if (product.getSellPrice() != null) {
                output += product.getSellPrice() * product.getQuantity();
            }
        }
        return output;
    }

    public Double getToBuyValue() {
        Double output = 0.0;
        for (Product product : this.stock.values()) {
            if (product.getBuyPrice() != null) {
                output += product.getBuyPrice() * product.getQuantityToBuy();
            }
        }
        return output;
    }

    @Override
    public Object[][] getTableLineFormat() {
        Object[][] output = new Object[stock.size()][7]; // 7 = amount of columns
        int productIndex = 0;
        for(String key : stock.keySet()) {
            Product product = stock.get(key);
            
            String prevision = product.getBuyPrice() == null ? "N/A"
                    : (product.getBuyPrice() * product.getDemand()) + "";

            SpinnerModel quantity2buy_spinnerModel = new SpinnerNumberModel(product.getQuantityToBuy().intValue(), 0, 9,
                    1);

            output[productIndex] = new Object[] {
                product.getCode(),
                product.getName(),
                product.getQuantity(),
                new SpinnerCell(new JSpinner(quantity2buy_spinnerModel)),
                prevision,
                product.getQuantity() + product.getQuantityToBuy(),
                product.getQuantity() - product.getQuantityToBuy() };
            productIndex++;
        }

        return output;
    }
}
