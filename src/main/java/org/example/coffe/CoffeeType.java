package org.example.coffe;

public enum CoffeeType {
    LATTE(20, 200, 100, "Латте"),
    ESPRESSO(40, 200, 0, "Еспрессо");

    private final int coffeeAmount;

    private final int waterAmount;

    private final int milkAmount;

    private final String name;

    CoffeeType(int coffeeAmount, int waterAmount, int milkAmount, String name) {
        this.coffeeAmount = coffeeAmount;
        this.waterAmount = waterAmount;
        this.milkAmount = milkAmount;
        this.name = name;
    }

    public int getCoffeeAmount() {
        return coffeeAmount;
    }

    public int getWaterAmount() {
        return waterAmount;
    }

    public int getMilkAmount() {
        return milkAmount;
    }

    public String getName() {
        return name;
    }
}
