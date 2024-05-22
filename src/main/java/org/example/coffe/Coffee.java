package org.example.coffe;

public class Coffee {
    private final CoffeeType type;

    public Coffee(CoffeeType type) {
        this.type = type;
    }

    public String getCoffeeName() {
        return type.getName();
    }

    public int getCoffeeAmount() {
        return type.getCoffeeAmount();
    }

    public int getWaterAmount() {
        return type.getWaterAmount();
    }

    public int getMilkAmount() {
        return type.getMilkAmount();
    }

    public CoffeeType getType() {
        return type;
    }
}
