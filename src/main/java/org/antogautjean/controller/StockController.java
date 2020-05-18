package org.antogautjean.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import org.antogautjean.controller.meta.MetaControllerInterface;
import org.antogautjean.controller.meta.StockMetaController;
import org.antogautjean.model.FileImporter;
import org.antogautjean.model.Product;
import org.antogautjean.view.components.SpinnerCell;
import org.antogautjean.view.components.table.TableRowFormatInterface;

public class StockController implements TableRowFormatInterface, ControllerFromFileInterface {
    private HashMap<String, Product> stock = new HashMap<>();
    protected boolean fileImportFailed = false;

    @Override
    public void setIfFileImportFailed(boolean b) {
        this.fileImportFailed = b;
    }

    @Override
    public boolean getIfFileImportFailed() {
        return !this.fileImportFailed;
    }

    public void addProduct(Product product) {
        // tester si le produit existe deja dans la base de donnée
        try {
            this.stock.put(product.getCode(), product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean deleteProduct(String code) {
        try {
            this.stock.remove(code);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
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
            e.printStackTrace();
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
        double output = 0.0;
        for (Product product : this.stock.values()) {
            if (product.getSellPrice() != null) {
                output += product.getSellPrice() * product.getQuantity();
            }
        }
        return output;
    }

    public Double getToBuyValue() {
        double output = 0.0;
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

            StockMetaController metaStock = new StockMetaController(this); //TODO: passer : this.stock

            output[productIndex] = new Object[] {
                product.getCode(),
                product.getName(),
                product.getQuantity(),
                new SpinnerCell(new JSpinner(quantity2buy_spinnerModel), product.getCode(), this.getClass().getName(), metaStock),
                prevision,
                product.getQuantity() + product.getQuantityToBuy(),
                product.getQuantity() - product.getQuantityToBuy() };
            productIndex++;
        }

        return output;
    }

    @Override
    public void refreshFromFile() throws IOException {
        FileImporter.fileToStock(this);
    }
}
