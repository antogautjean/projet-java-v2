package org.antogautjean.controller;

import org.antogautjean.model.Product;

/**
 * MetaStockController
 */
public class StockMetaController {
    // TODO: transformer realStock en IMMUTABLE
    StockController realStock;
    StockController simulationStock;

    public StockMetaController(StockController stock) {
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