package org.antogautjean.controller;

import java.util.HashMap;

import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import org.antogautjean.model.Product;
import org.antogautjean.model.ProductionLine;
import org.antogautjean.model.ProductionLineState;
import org.antogautjean.view.HomeView;
import org.antogautjean.view.components.spinnercell.QuantityToBuySpinnerCell;
import org.antogautjean.view.components.table.TableRowFormatInterface;

/**
 * MetaStockController est une classe qui permet de simuler le stock
 */
public class StockMetaController implements TableRowFormatInterface {
    protected StockController realStock;
    protected StockController simulationStock;
    protected FactoryController factoryCtrl;

    /**
     * Constructeur
     * 
     * @param stockToClone correspond au stock réel qu'on va ensuite cloner
     */
    public StockMetaController(StockController stockToClone, FactoryController factoryCtrl) {
        this.realStock = stockToClone;
        this.simulationStock = stockToClone.clone();

        this.factoryCtrl = factoryCtrl;
    }

    /**
     * Permet d'obtenir le produit réel stocké dans le meta controller
     * 
     * @param code du produit à récupérer
     * @return le produit en question
     */
    public Product getRealProduct(String code) {
        return this.realStock.getProduct(code);
    }

    /**
     * Permet d'obtenir le produit après simulation des chaines de production
     * 
     * @param code du produit à récupérer
     * @return le produit en question
     */
    public Product getProductAfterCalculation(String code) {
        return this.simulationStock.getProduct(code);
    }

    /**
     * Simule le fonctionnement de l'usine en appliquant les changements sur
     * "simulationStock"
     */
    protected void simulateProduction() {
        for (ProductionLine prodLine : this.factoryCtrl.getProductionLines().values()) {
            HashMap<String, Integer> inputNeeded = prodLine.getInputNeeds();
            for (String productCode : inputNeeded.keySet()) {
                Product currProduct = this.simulationStock.getProduct(productCode);
                if (currProduct.getQuantity() >= inputNeeded.get(productCode)) {
                    // si les besoins de la ligne de production peuvent êtres satisfait
                    currProduct.setQuantity(currProduct.getQuantity() - inputNeeded.get(productCode));
                    prodLine.setState(ProductionLineState.POSSIBLE);
                } else {
                    // sinon, production impossible
                    prodLine.setState(ProductionLineState.IMPOSSIBLE);
                }
            }
        }
    }

    @Override
    public Object[][] getTableLineFormat(HomeView parentComponent) {
        // avant tout, lancer une simulation
        simulateProduction();
        
        HashMap<String, Product> realStockHashMap = this.realStock.getStock();
        Object[][] output = new Object[realStockHashMap.size()][7]; // 7 = amount of columns
        int productIndex = 0;
        for (String key : realStockHashMap.keySet()) {
            Product product = realStockHashMap.get(key);

            String prevision = product.getBuyPrice() == null ? "N/A"
                    : (product.getBuyPrice() * product.getQuantityToBuy()) + " €";

            SpinnerModel quantity2buy_spinnerModel = new SpinnerNumberModel(product.getQuantityToBuy().intValue(), 0, 9,
                    1);

            output[productIndex] = new Object[] { product.getCode(), product.getName(), product.getQuantity(),
                    new QuantityToBuySpinnerCell(new JSpinner(quantity2buy_spinnerModel), product.getCode(), this,
                            parentComponent),
                    prevision, product.getQuantity() + product.getQuantityToBuy(),
                    this.simulationStock.getProduct(product.getCode()).getQuantity() };
            productIndex++;
        }

        return output;
    }

    @Override
    public String toString() {
        return realStock.toString() + "\n\n" + simulationStock.toString();
    }
}