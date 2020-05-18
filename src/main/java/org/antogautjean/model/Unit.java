package org.antogautjean.model;

/**
 * Enumération des différentes unités de mesures
 */
public enum Unit {

    KG("kg"),
    LITER("litre"),
    BOX("carton"),
    UNIT("unite");

    private final String unitType;

    Unit(String unit){
        this.unitType = unit;
    }

    @Override
    public String toString() {
        return unitType;
    }

    /**
     * Permet de transformer un String en Unit
     * @param unit Un string
     * @return L'unité sous la forme Unit
     * @throws Exception L'unité n'existes pas
     */
    public static Unit strToUnit(String unit) throws Exception{

            switch (unit){
                case "kg":
                    return KG;
                case "litre":
                    return LITER;
                case "unite":
                    return BOX;
                case "carton":
                    return UNIT;
                default:
                    throw new Exception("Unit " + unit + " does not exists");
            }
    }
}