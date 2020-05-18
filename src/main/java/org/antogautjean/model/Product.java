package org.antogautjean.model;

public class Product {
    private String code;
    private String name;
    private Integer quantity;
    private Unit unit;
    private Double buyPrice;
    private Double sellPrice;
    private Integer demand;

    private Integer quantityToBuy;

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

    public Product(String code, String name, int quantity, Unit unit){
        this(code, name, quantity,  unit, null, null, 0);
    }

    public Integer getQuantityToBuy() {
        return this.quantityToBuy;
    }

    public void setQuantityToBuy(Integer quantityToBuy) {
        this.quantityToBuy = quantityToBuy;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public Unit getUnit() {
        return unit;
    }

    public Double getBuyPrice() {
        return buyPrice;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public Integer getDemand() {
        return demand;
    }

    public void setBuyPrice(Double buyPrice){
        this.buyPrice = buyPrice;
    }

    public void setSellPrice(Double sellPrice){
        this.sellPrice = sellPrice;
    }

    public void setDemand(Integer demand){
        this.demand = demand;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public Product clone() {
        Product p = new Product(this.code, this.name, this.quantity, this.unit, this.buyPrice, this.sellPrice, this.demand);
        p.setQuantityToBuy(this.getQuantityToBuy());
        return p;
    }
}
