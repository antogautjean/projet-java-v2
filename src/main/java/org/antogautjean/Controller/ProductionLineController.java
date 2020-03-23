package org.antogautjean.Controller;

import org.antogautjean.App;
import org.antogautjean.model.ProductionLine;

import java.util.HashMap;

public class ProductionLineController {
    private HashMap<Integer, ProductionLine> productionLines;
    private App parentController;

    public ProductionLineController(App parentController) {
        this.parentController = parentController;
    }

    public HashMap<Integer,ProductionLine> getProductionLines() {
        return this.productionLines;
    }
}
