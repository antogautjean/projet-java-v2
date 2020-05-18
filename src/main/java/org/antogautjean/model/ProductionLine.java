package org.antogautjean.model;

import java.util.HashMap;

import org.antogautjean.controller.FactoryController;

public class ProductionLine {
    // attributs CSV
    private String code;
    private String name;
    private HashMap<String, Integer> inputs;
    private HashMap<String, Integer> outputs;

    // attributs CSV non utilisés dans la version de base
    private Integer time;
    private Integer staffAmountNOTqualified;
    private Integer staffAmountQualified;

    // attributs supplémentaires
    private Integer activationLevel;
    private FactoryController factory;

    public ProductionLine(String code, String name, HashMap<String, Integer> inputs, HashMap<String, Integer> outputs,
            Integer time, Integer staffAmountNOTqualified, Integer staffAmountQualified) {
        this.code = code;
        this.name = name;
        this.inputs = inputs;
        this.outputs = outputs;
        this.time = time;
        this.staffAmountNOTqualified = staffAmountNOTqualified;
        this.staffAmountQualified = staffAmountQualified;

        this.activationLevel = 1;
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

    public Integer getActivationLevel() {
        return this.activationLevel;
    }

    public String[] getInputList() {
        String[] output = new String[this.inputs.size()];
        int i = 0;
        for (String str : this.inputs.keySet()) {
            output[i++] = str;
        }
        return output;
    }

    public String[] getOutputList() {
        String[] output = new String[this.outputs.size()];
        int i = 0;
        for (String str : this.outputs.keySet()) {
            output[i++] = str;
        }
        return output;
    }

    // Setter
    public void setFactory(FactoryController factory) {
        this.factory = factory;
    }

    // Autres fonctions
    public HashMap<String, Integer> getMissingIngredients() {
        // TODO
        return this.inputs;
    }

    public HashMap<String, Integer> getInputNeeds() {
        HashMap<String, Integer> outputQuantity = new HashMap<>();
        // https://stackoverflow.com/a/9009709/5736301
        for (HashMap.Entry<String, Integer> entry : this.inputs.entrySet()) {
            outputQuantity.put(entry.getKey(), entry.getValue() * this.activationLevel);
        }
        return outputQuantity;
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
        // récupérer la liste des produits qu'elle peut produire
        HashMap<String, Integer> quantitiesDemanded = new HashMap<>();
        for (String pcode : this.getOutputList()) {
            // récupérer la quantité demandée
            quantitiesDemanded.put(pcode, this.factory.getStockController().getProduct(pcode).getDemand());
        }
        return quantitiesDemanded;
    }

    public ProductionLineState getState() {
        if (this.activationLevel > 0) {
            HashMap<String, Integer> inputNeeds = this.getInputNeeds();
            for (HashMap.Entry<String, Integer> produit : inputNeeds.entrySet()) {
                if (this.factory.getStockController().getProduct(produit.getKey()).getQuantity() < produit.getValue()) {
                    return ProductionLineState.IMPOSSIBLE;
                }
            }
            return ProductionLineState.POSSIBLE;
        } else {
            return ProductionLineState.NONE;
        }
    }

    @Override
    public String toString() {
        return this.code + " " + this.name;
    }
}
