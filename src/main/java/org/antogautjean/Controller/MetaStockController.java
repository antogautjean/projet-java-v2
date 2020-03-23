package org.antogautjean.Controller;

import org.antogautjean.model.Product;

/**
 * MetaStockController
 */
public class MetaStockController {
    // TODO: transformer realStock en IMMUTABLE
    StockController realStock;
    StockController simulationStock;

    public MetaStockController(StockController stock) {
        this.realStock = stock;
        this.simulationStock = stock.clone();
    }

    public Product getRealProduct(String code) {
        return this.realStock.getProduct(code);
    }

    public Product getProductAfterCalculation(String code) {
        return this.simulationStock.getProduct(code);
    }
}