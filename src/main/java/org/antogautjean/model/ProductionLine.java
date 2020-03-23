package org.antogautjean.model;

import java.util.HashMap;

import org.antogautjean.App;
import org.antogautjean.Controller.StockController;

public class ProductionLine {
    // attributs CSV
    private String code;
    private String name;
    private HashMap<String, Integer> inputs;
    private HashMap<String, Integer> outputs;

    // attributs CSV non utilisés dans la version de base
    private int time;
    private int staffAmountNOTqualified;
    private int staffAmountQualified;

    // attributs supplémentaires
    private int verificationOrder;
    private int activationLevel;
    private StockController stockController;

    public ProductionLine(StockController stockController, String code, String name, HashMap<String, Integer> inputs, HashMap<String, Integer> outputs,
            int verificationOrder) {
        this.code = code;
        this.name = name;
        this.inputs = inputs;
        this.outputs = outputs;
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

    public int getTime() {
        return this.time;
    }

    public int getStaffAmountNOTqualified() {
        return this.staffAmountNOTqualified;
    }

    public int getStaffAmountQualified() {
        return this.staffAmountQualified;
    }

    public int getVerificationOrder() {
        return this.verificationOrder;
    }

    public int getActivationLevel() {
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

    public HashMap<String, Integer> getOrderedQuantity() {
        HashMap<String, Integer> orderedQuantity = new HashMap<>();
        for (HashMap.Entry<String, Integer> entry : this.outputs.entrySet()) {
            orderedQuantity.put(entry.getKey(), this.stockController.getProduct(entry.getKey()).getOrderedQuantity());
        }
        return orderedQuantity;
    }
}
