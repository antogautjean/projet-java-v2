package org.antogautjean.controller.meta;

import org.antogautjean.controller.FactoryController;
import org.antogautjean.model.ProductionLine;

import java.util.HashMap;

/**
 * MetaStockController
 */
public class FactoryMetaController {
    // TODO: transformer realStock en IMMUTABLE
    FactoryController realFactory;
    FactoryController simulationFactory;

    public FactoryMetaController(FactoryController factory) {
        this.realFactory = factory;
        this.simulationFactory = null; //TODO: Clone
    }

    public HashMap<String, ProductionLine> getRealLine() {
        return this.realFactory.getProductionLines();
    }

    public HashMap<String, ProductionLine> getLineAfterCalculation() {
        return this.simulationFactory.getProductionLines();
    }
}
