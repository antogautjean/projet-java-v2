package org.antogautjean.controller;

import java.util.HashMap;

import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import org.antogautjean.model.Product;
import org.antogautjean.view.HomeView;
import org.antogautjean.view.components.spinnercell.QuantityToBuySpinnerCell;
import org.antogautjean.view.components.table.TableRowFormatInterface;

/**
 * MetaStockController est une classe qui permet de simuler le stock
 */
public class StockMetaController implements TableRowFormatInterface {
    StockController realStock;
    StockController simulationStock;

    /**
     * Constructeur
     * @param stockToClone correspond au stock réel qu'on va ensuite cloner
     */
    public StockMetaController(StockController stockToClone) {
        this.realStock = stockToClone;
        this.simulationStock = stockToClone.clone();
    }

    /**
     * Permet d'obtenir le produit réel stocké dans le meta controller
     * @param code du produit à récupérer
     * @return le produit en question
     */
    public Product getRealProduct(String code) {
        return this.realStock.getProduct(code);
    }

    /**
     * Permet d'obtenir le produit après simulation des chaines de production
     * @param code du produit à récupérer
     * @return le produit en question
     */
    public Product getProductAfterCalculation(String code) {
        return this.simulationStock.getProduct(code);
    }

    @Override
    public Object[][] getTableLineFormat(HomeView parentComponent) {
        HashMap<String, Product> realStockHashMap = this.realStock.getStock();
        Object[][] output = new Object[realStockHashMap.size()][7]; // 7 = amount of columns
        int productIndex = 0;
        for (String key : realStockHashMap.keySet()) {
            Product product = realStockHashMap.get(key);

            String prevision = product.getBuyPrice() == null ? "N/A"
                    : (product.getBuyPrice() * product.getDemand()) + "";

            SpinnerModel quantity2buy_spinnerModel = new SpinnerNumberModel(product.getQuantityToBuy().intValue(), 0, 9,
                    1);

            output[productIndex] = new Object[] { product.getCode(), product.getName(), product.getQuantity(),
                    new QuantityToBuySpinnerCell(new JSpinner(quantity2buy_spinnerModel), product.getCode(), this,
                            parentComponent),
                    prevision, product.getQuantity() + product.getQuantityToBuy(),
                    -1 };
            productIndex++;
        }

        return output;
    }

    @Override
    public String toString() {
        return realStock.toString() + "\n\n" + simulationStock.toString();
    }
}