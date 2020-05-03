package org.antogautjean.Controller;

import org.antogautjean.model.ProductionLine;
import org.antogautjean.view.SpinnerCell;
import org.antogautjean.view.TableLinesFormatInterface;

import java.util.HashMap;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class FactoryController implements TableLinesFormatInterface {
    private HashMap<String, ProductionLine> productionLines = new HashMap<>();
    private StockController stock;

    public FactoryController(HashMap<String, ProductionLine> productionLines, StockController stock) {
        this.productionLines = productionLines;
        this.stock = stock;

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
                    lineState = "<html><span color='red'>⚠ Production impossible</span><br>(il manque des ressources)</html>";
                    break;
                case POSSIBLE:
                    lineState = "👍 Production possible";
                    break;
                default: // NONE
                    lineState = "Aucune production";
            }
            String ratioQuantiteProduiteDemandee = percent + " (" + outputQty + " / " + outputQtyDemanded + ")";

            output[verifOrder] = new Object[] {
                new SpinnerCell(new JSpinner(new SpinnerNumberModel(
                    (verifOrder + 1), 1, Integer.MAX_VALUE, 1
                ))),
                line.getCode(),
                line.getName(),
                String.join("\n", line.getOutputList()),
                new SpinnerCell(new JSpinner(new SpinnerNumberModel(
                    line.getActivationLevel().intValue(), 0, 9, 1
                ))),
                lineState,
                ratioQuantiteProduiteDemandee
            };
            verifOrder++;
        }
        return output;
    }
}
