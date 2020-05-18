package org.antogautjean.controller;

import java.util.HashMap;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import org.antogautjean.model.FileImporter;
import org.antogautjean.model.ProductionLine;
import org.antogautjean.view.components.ActivationLevelSpinnerCell;
import org.antogautjean.view.components.QuantityToBuySpinnerCell;
import org.antogautjean.view.components.table.TableRowFormatInterface;

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
        return !this.fileImportFailed;
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
        StringBuilder output = new StringBuilder();
        for (ProductionLine pl : this.productionLines.values()) {
            output.append(pl).append("\n");
        }
        return output.toString();
    }

    @Override
    public Object[][] getTableLineFormat() {
        int linesOrder = 0;
        Object[][] output = new Object[productionLines.size()][7]; // 7 = amount of columns
        for (String key : productionLines.keySet()) {
            ProductionLine line = productionLines.get(key);

            String lineState;
            Integer outputQty = 0;
            for (Integer qty : line.getOutputQuantity().values()) {
                outputQty += qty;
            }
            Integer outputQtyDemanded = 0;
            for (Integer qty : line.getQuantityDemanded().values()) {
                outputQtyDemanded += qty;
            }
            String percent;
            if (outputQty >= outputQtyDemanded) {
                percent = "100,00%";
            } else if (outputQtyDemanded == 0) {
                percent = "NA";
            } else {
                double tmp = outputQty * 100. / outputQtyDemanded;
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

            //MetaControllerInterface metaLines = (MetaControllerInterface) new FactoryMetaController();//TODO: mettre this.factory

            output[linesOrder] = new Object[] {
                    new ActivationLevelSpinnerCell(new JSpinner(new SpinnerNumberModel((linesOrder + 1), 1, Integer.MAX_VALUE, 1)), line.getCode()),
                    line.getCode(),
                    line.getName(),
                    String.join("\n", line.getOutputList()),
                    new ActivationLevelSpinnerCell(new JSpinner(new SpinnerNumberModel(line.getActivationLevel().intValue(), 0, 9, 1)), line.getCode()),
                    lineState,
                    ratioQuantiteProduiteDemandee };
            linesOrder++;

            /*
            output[verifOrder] = new Object[] {
                    new SpinnerCell(new JSpinner(new SpinnerNumberModel((verifOrder + 1), 1, Integer.MAX_VALUE, 1)), verifOrder, this.getClass().getName()),
                    line.getCode(),
                    line.getName(),
                    String.join("\n", line.getOutputList()),
                    new SpinnerCell(new JSpinner(new SpinnerNumberModel(line.getActivationLevel().intValue(), 0, 9, 1)), verifOrder,this.getClass().getName()),
                    lineState,
                    ratioQuantiteProduiteDemandee };
            verifOrder++;
            verifOrder++;
             */
        }
        return output;
    }

    @Override
    public void refreshFromFile() {
        FileImporter.fileToFactory(this, this.stock);
    }
}
