package org.example.Person;

import org.example.coffe.Coffee;

public class Person {

    private String name;

    private Coffee coffee;

    public Person(String name, Coffee coffee) {
        this.coffee = coffee;
        this.name = name;
    }

    public Coffee getCoffee() {
        return coffee;
    }

    public String getName() {
        return name;
    }
}
