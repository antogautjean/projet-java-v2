package org.antogautjean.Controller;

import org.antogautjean.model.ProductionLine;

import java.util.HashMap;

public class FactoryController {
    private HashMap<String, ProductionLine> productionLines = new HashMap<>();

    public FactoryController(HashMap<String, ProductionLine> productionLines) {
        this.productionLines = productionLines;
    }

    public HashMap<String, ProductionLine> getProductionLines() {
        return this.productionLines;
    }

    public ProductionLine getProductionLine(String code) {
        return this.productionLines.get(code);
    }

    @Override
    public String toString() {
        String output = "";
        for (HashMap.Entry<String, ProductionLine> pl : this.productionLines.entrySet()) {
            output += pl.getValue().toString() + "\n";
        }
        return output;
    }
}
