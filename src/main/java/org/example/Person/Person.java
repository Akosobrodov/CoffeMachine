package org.example.Person;

import org.example.coffe.Coffee;

public class Person {

    private String name;

    private Coffee coffee;

    private int amount;

    public Person(String name, Coffee coffee, int amount) {
        this.coffee = coffee;
        this.name = name;
        this.amount = amount;
    }

    public Coffee getCoffee() {
        return coffee;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }
}
