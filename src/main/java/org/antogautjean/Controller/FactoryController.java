package org.antogautjean.Controller;

import org.antogautjean.model.ControllerFromFileInterface;
import org.antogautjean.model.FileImporter;
import org.antogautjean.model.ProductionLine;
import org.antogautjean.view.components.SpinnerCell;
import org.antogautjean.view.components.TableRowFormatInterface;

import java.io.IOException;
import java.util.HashMap;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class FactoryController implements TableRowFormatInterface, ControllerFromFileInterface {
    private HashMap<String, ProductionLine> productionLines = new HashMap<>();
    private StockController stock;
    protected boolean fileImportFailed = false;

    @Override
    public void setIfFileImportFailed(boolean b) {
        this.fileImportFailed = b;
    }

    @Override
    public boolean getIfFileImportFailed() {
        return this.fileImportFailed;
    }

    public void setStockController(StockController stockCtrl) {
        this.stock = stockCtrl;
    }

    public void setProductionLines(HashMap<String, ProductionLine> productionLines) {
        this.productionLines = productionLines;

        for (ProductionLine pl : this.productionLines.values()) {
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

    @Override
    public Object[][] getTableLineFormat() {
        int verifOrder = 0;
        Object[][] output = new Object[productionLines.size()][7]; // 7 = amount of columns
        for (String key : productionLines.keySet()) {
            ProductionLine line = productionLines.get(key);

            String lineState = "";
            Integer outputQty = 0;
            for (Integer qty : line.getOutputQuantity().values()) {
                outputQty += qty;
            }
            Integer outputQtyDemanded = 0;
            for (Integer qty : line.getQuantityDemanded().values()) {
                outputQtyDemanded += qty;
            }
            String percent = "";
            if (outputQty >= outputQtyDemanded) {
                percent = "100,00%";
            } else if (outputQtyDemanded == 0) {
                percent = "NA";
            } else {
                Double tmp = outputQty * 100. / outputQtyDemanded;
                percent = String.format("%.2f", tmp) + "%";
                percent = (tmp < 10. ? "00" : "0") + percent;
            }
            switch (line.getState()) {
                case IMPOSSIBLE:
                    lineState = "<html>Production impossible<br>(il manque des ressources)</html>";
                    break;
                case POSSIBLE:
                    lineState = "Production possible";
                    break;
                default: // NONE
                    lineState = "Aucune production";
            }
            String ratioQuantiteProduiteDemandee = percent + " (" + outputQty + " / " + outputQtyDemanded + ")";

            output[verifOrder] = new Object[] {
                    new SpinnerCell(new JSpinner(new SpinnerNumberModel((verifOrder + 1), 1, Integer.MAX_VALUE, 1))),
                    line.getCode(), line.getName(), String.join("\n", line.getOutputList()),
                    new SpinnerCell(
                            new JSpinner(new SpinnerNumberModel(line.getActivationLevel().intValue(), 0, 9, 1))),
                    lineState, ratioQuantiteProduiteDemandee };
            verifOrder++;
        }
        return output;
    }

    @Override
    public void refreshFromFile() throws IOException {
        FileImporter.fileToFactory(this, this.stock);
    }
}
