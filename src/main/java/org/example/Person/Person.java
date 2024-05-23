package org.example.Person;

import org.example.coffe.CoffeeType;

public class Person {

    private String name;

    private CoffeeType coffeeLatte;

    private CoffeeType coffeeEspresso;

    private int amountLatte;

    private int amountEspresso;

    public Person(String name, CoffeeType coffeeLatte, int amountLatte,
                  CoffeeType coffeeEspresso, int amountEspresso) {
        this.coffeeLatte = coffeeLatte;
        this.amountLatte = amountLatte;
        this.name = name;
        this.coffeeEspresso = coffeeEspresso;
        this.amountEspresso = amountEspresso;

    }

    public CoffeeType getCoffeeLatte() {
        return coffeeLatte;
    }

    public CoffeeType getCoffeeEspresso() {
        return coffeeEspresso;
    }

    public String getName() {
        return name;
    }

    public int getAmountLatte() {
        return amountLatte;
    }

    public int getAmountEspresso() {
        return amountEspresso;
    }
}
