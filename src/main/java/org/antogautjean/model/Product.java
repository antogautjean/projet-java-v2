package org.antogautjean.model;

/**
 * Classe représentant un produit
 */
public class Product {
    private String code;
    private String name;
    private Integer quantity;
    private Unit unit;
    private Double buyPrice;
    private Double sellPrice;
    private Integer demand;

    private Integer quantityToBuy;

    /**
     * Permet de construire un "Product"
     * @param code le code du produit
     * @param name le nom du produit
     * @param quantity la quantité de produit en stock
     * @param unit l'unité dans laquelle le produit est stocké
     * @param buyPrice le prix d'achat du produit
     * @param sellPrice le prix de vente du produit
     * @param demand la demande
     */
    public Product(String code, String name, int quantity, Unit unit, Double buyPrice, Double sellPrice, Integer demand){
        this.code = code;
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.demand = demand;

        this.quantityToBuy = 0;
    }

    /**
     * Permet de construire un "Product"
     * @param code le code du produit
     * @param name le nom du produit
     * @param quantity la quantité de produit
     * @param unit l'unité dans laquelle est stocké le produit
     */
    public Product(String code, String name, int quantity, Unit unit){
        this(code, name, quantity,  unit, null, null, 0);
    }

    /**
     * Permet d'obtenir la quantité de produit à acheter
     * @return la quantité à acheter
     */
    public Integer getQuantityToBuy() {
        return this.quantityToBuy;
    }

    /**
     * Permet de définir la quatité de produit à acheter
     * @param quantityToBuy La nouvelle quantité
     */
    public void setQuantityToBuy(Integer quantityToBuy) {
        this.quantityToBuy = quantityToBuy;
    }

    /**
     * Permet d'obtenir le code du produit
     * @return Le code du produit
     */
    public String getCode() {
        return code;
    }

    /**
     * Permet d'obtenir le nom du produit
     * @return le nom du produit
     */
    public String getName() {
        return name;
    }

    /**
     * Permet d'obtenir la quantité de produit disponible
     * @return la quantité de produit disponible
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Permet d'obtenir l'unité dans laquelle est stocké le produit
     * @return l'unité
     */
    public Unit getUnit() {
        return unit;
    }

    /**
     * Permet d'obtenir le prix d'achat du produit
     * @return le prix d'achat du produit
     */
    public Double getBuyPrice() {
        return buyPrice;
    }

    /**
     * Permet d'obtenir le prix de vente du produit
     * @return le prix de vente du produit
     */
    public Double getSellPrice() {
        return sellPrice;
    }

    /**
     * Permet d'obtenir la demande de produit
     * @return la demande
     */
    public Integer getDemand() {
        return demand;
    }

    /**
     * Permet de modifier le prix d'achat du produit
     * @param buyPrice Le nouveau prix d'achat
     */
    public void setBuyPrice(Double buyPrice){
        this.buyPrice = buyPrice;
    }

    /**
     * Permet de modifier le prix de vente du produit
     * @param sellPrice Le nouveau prix de vente
     */
    public void setSellPrice(Double sellPrice){
        this.sellPrice = sellPrice;
    }

    /**
     * Permet de modifier la demande du produit
     * @param demand La nouvelle demande
     */
    public void setDemand(Integer demand){
        this.demand = demand;
    }

    /**
     * Permet de modifier le nom du produit
     * @param name Le nouveau nom du produit
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Permet de modifier la quantité de produit disponible en stock
     * @param quantity La nouvelle quantité de produit disponible
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String toString(){

        String description = quantity.toString() + " " + unit + " de " + name + " (" + code + ")";

        if (buyPrice != null) description += " | acheté à " + buyPrice + "€ ";
        if (sellPrice != null) description += " | vendu à " + sellPrice + "€ ";
        if (demand != null) description += "(demande:" + demand + ")";

        return description;
    }

    /**
     * Permet de cloner l'objet Product
     * @return Un objet de type Product similaire
     */
    public Product clone() {
        Product p = new Product(this.code, this.name, this.quantity, this.unit, this.buyPrice, this.sellPrice, this.demand);
        p.setQuantityToBuy(this.getQuantityToBuy());
        return p;
    }
}
