package org.antogautjean.model;

public enum Unit {

    KG("kg"),
    LITER("litre"),
    BOX("carton");

    private final String unitType;

    Unit(String unit){
        this.unitType = unit;
    }

    @Override
    public String toString() {
        return unitType;
    }

    public static Unit strToUnit(String unit) throws Exception{

            switch (unit){
                case "kg":
                    return KG;
                case "litre":
                    return LITER;
                case "carton":
                    return BOX;
                default:
                    throw new Exception("Unit " + unit + " does not exists");
            }
    }
}