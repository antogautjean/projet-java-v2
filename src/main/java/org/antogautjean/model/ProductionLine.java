package org.antogautjean.model;

import java.util.HashMap;

import org.antogautjean.App;
import org.antogautjean.Controller.StockController;

public class ProductionLine {
    // attributs CSV
    private String code;
    private String name;
    private HashMap<String, Integer> inputs = new HashMap<>();
    private HashMap<String, Integer> outputs = new HashMap<>();

    // attributs CSV non utilisés dans la version de base
    private Integer time;
    private Integer staffAmountNOTqualified;
    private Integer staffAmountQualified;

    // attributs supplémentaires
      private Integer verificationOrder;
    private Integer activationLevel;
    private StockController stockController;

    public ProductionLine(StockController stockController, String code, String name, HashMap<String, Integer> inputs,
            HashMap<String, Integer> outputs, Integer time, Integer staffAmountNOTqualified, Integer staffAmountQualified,
            Integer verificationOrder) {
        this.code = code;
        this.name = name;
        this.inputs = inputs;
        this.outputs = outputs;
        this.time = time;
        this.staffAmountNOTqualified = staffAmountNOTqualified;
        this.staffAmountQualified = staffAmountQualified;
        this.verificationOrder = verificationOrder;

        this.activationLevel = 0;
    }

    // Getters standards
    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public Integer getTime() {
        return this.time;
    }

    public Integer getStaffAmountNOTqualified() {
        return this.staffAmountNOTqualified;
    }

    public Integer getStaffAmountQualified() {
        return this.staffAmountQualified;
    }

    public Integer getVerificationOrder() {
        return this.verificationOrder;
    }

    public Integer getActivationLevel() {
        return this.activationLevel;
    }

    // Setters

    // Autres fonctions
    public HashMap<String, Integer> getMissingIngredients() {
        // TODO
        return this.inputs;
    }

    public HashMap<String, Integer> getOutputQuantity() {
        HashMap<String, Integer> outputQuantity = new HashMap<>();
        // https://stackoverflow.com/a/9009709/5736301
        for (HashMap.Entry<String, Integer> entry : this.outputs.entrySet()) {
            outputQuantity.put(entry.getKey(), entry.getValue() * this.activationLevel);
        }
        return outputQuantity;
    }

    public HashMap<String, Integer> getQuantityDemanded() {
        HashMap<String, Integer> orderedQuantity = new HashMap<>();
        for (HashMap.Entry<String, Integer> entry : this.outputs.entrySet()) {
            orderedQuantity.put(entry.getKey(), this.stockController.getProduct(entry.getKey()).getDemand());
        }
        return orderedQuantity;
    }

    @Override
    public String toString() {
        return this.code + " " + this.name;
    }
}
