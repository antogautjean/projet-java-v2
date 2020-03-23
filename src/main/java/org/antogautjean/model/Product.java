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

    public boolean setBuyPrice(Double buyPrice){
        this.buyPrice = buyPrice;
        return true;
    }

    public boolean setSellPrice(Double sellPrice){
        this.sellPrice = sellPrice;
        return true;
    }

    public boolean setDemand(Integer demand){
        this.demand = demand;
        return true;
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
}
