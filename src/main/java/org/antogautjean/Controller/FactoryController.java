package org.antogautjean.Controller;

import org.antogautjean.model.ProductionLine;

import java.util.HashMap;

public class FactoryController {
    private HashMap<String, ProductionLine> productionLines;

    public HashMap<String,ProductionLine> getProductionLines() {
        return this.productionLines;
    }

    public ProductionLine getProductionLine(String code) {
        return this.productionLines.get(code);
    }
}
