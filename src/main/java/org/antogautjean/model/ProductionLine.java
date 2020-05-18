package org.antogautjean.model;

import java.util.HashMap;

import org.antogautjean.controller.FactoryController;
import org.antogautjean.controller.StockController;

/**
 * Classe représentant une ligne de production
 */
public class ProductionLine {
    // attributs CSV
    protected String code;
    protected String name;
    protected HashMap<String, Integer> inputs;
    protected HashMap<String, Integer> outputs;

    // attributs CSV non utilisés dans la version de base
    protected Integer time;
    protected Integer staffAmountNOTqualified;
    protected Integer staffAmountQualified;

    // attributs supplémentaires
    protected Integer activationLevel;
    protected FactoryController factory;
    protected ProductionLineState state = ProductionLineState.NONE;

    /**
     *
     * @param code Code de la ligne de production
     * @param name Nom de la ligne de production
     * @param inputs Entrées de la ligne de production
     * @param outputs Sorties de la ligne de production
     * @param time Temps que prends la  ligne de prodution pour transformer les inputs en outpouts
     * @param staffAmountNOTqualified Nombre de personnel non qualifié nécessaire
     * @param staffAmountQualified Nombre de personnel qualifié nécessaire
     */
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

    /**
     * Permet d'obtenir le code de la ligne de production
     * @return le code
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Permet d'obtenir le nom de la ligne de production
     * @return le nom
     */
    public String getName() {
        return this.name;
    }

    /**
     * Permet d'obtenir le temps d'execution de la ligne de production
     * @return le temps que prends la chaine de production pour s'executer
     */
    public Integer getTime() {
        return this.time;
    }

    /**
     * Permet d'obtenir le nombre de personnel non qualifié nécessaire à l'execution de la ligne de production
     * @return Le nombre de personnel non qualifié necessaire
     */
    public Integer getStaffAmountNOTqualified() {
        return this.staffAmountNOTqualified;
    }

    /**
     * Permet d'obtenir le nombre de personnel qualifié necessaire à l'execution de la ligne de production
     * @return Le nombre de personnel qualifié necessaire
     */
    public Integer getStaffAmountQualified() {
        return this.staffAmountQualified;
    }

    /**
     * Permet d'obtenir le niveau d'activation de la ligne de production
     * @return le niveau d'activation de la ligne de production
     */
    public Integer getActivationLevel() {
        return this.activationLevel;
    }

    /**
     * Permet d'obtenir la liste des produits nécessaire en entré de la ligne de production
     * @return La liste des produits en entré necessaire
     */
    public String[] getInputList() {
        String[] output = new String[this.inputs.size()];
        int i = 0;
        for (String str : this.inputs.keySet()) {
            output[i++] = str;
        }
        return output;
    }

    /**
     * Permet d'obtenir la liste des produits en sortie de la ligne de production
     * @return La liste des produits en sortie necessaire
     */
    public String[] getOutputList() {
        String[] output = new String[this.outputs.size()];
        int i = 0;
        for (String str : this.outputs.keySet()) {
            output[i++] = str;
        }
        return output;
    }

    // Setter

    /**
     * @param factory
     */
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

    public void setState(ProductionLineState state) {
        this.state = state;
    }

    public ProductionLineState getState() {
        return this.activationLevel > 0 ? this.state : ProductionLineState.NONE;
    }

    @Override
    public String toString() {
        return this.code + " " + this.name;
    }
}
