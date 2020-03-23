package org.antogautjean.Controller;

import org.antogautjean.model.ProductionLine;

import java.util.HashMap;

public class FactoryController {
    private HashMap<String, ProductionLine> productionLines = new HashMap<>();
    private StockController stock;

    public FactoryController(HashMap<String, ProductionLine> productionLines, StockController stock) {
        this.productionLines = productionLines;
        this.stock = stock;
        
        for(ProductionLine pl : this.productionLines.values()) {
            pl.setFactory(this);
        }
    }

    public HashMap<String, ProductionLine> getProductionLines() {
        return this.productionLines;
    }

    public ProductionLine getProductionLine(String code) {
        return this.productionLines.get(code);
    }

    public StockController getStockController() {
        return this.stock;
    }

    @Override
    public String toString() {
        String output = "";
        for (ProductionLine pl : this.productionLines.values()) {
            output += pl + "\n";
        }
        return output;
    }
}
