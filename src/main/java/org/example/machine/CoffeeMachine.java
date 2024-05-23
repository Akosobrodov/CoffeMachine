package org.example.machine;

import org.example.Person.Person;
import org.example.coffe.CoffeeType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class CoffeeMachine {

    private static final int maxCoffeeAmount = 1000;

    private static final int maxMilkAmount = 1000;

    private static final int maxWaterAmount = 1000;

    private static final int maxMadeCups = 10;

    private Scanner console = new Scanner(System.in);

    private boolean stateMachine;

    private int coffeeAmount;

    private int milkAmount;

    private int waterAmount;

    private int madeCups;

    private int logCountLatte;

    private int logCountEspresso;

    private List<String> logList;

    private List<Person> personList = new ArrayList<>();

    CoffeeMachine(int coffeeAmount, int milkAmount, int waterAmount, int madeCups) {
        this.waterAmount = waterAmount;
        this.coffeeAmount = coffeeAmount;
        this.milkAmount = milkAmount;
        this.madeCups = madeCups;
        this.logList = new ArrayList<>();
        this.logCountLatte = 0;
        this.logCountEspresso = 0;
        this.stateMachine = false;
    }

    public int getMadeCups() {
        return madeCups;
    }

    public int getCoffeeAmount() {
        return coffeeAmount;
    }

    public int getMilkAmount() {
        return milkAmount;
    }

    public int getWaterAmount() {
        return waterAmount;
    }

    public boolean getStateMachine() {
        return stateMachine;
    }

    public void setWaterAmount(int waterAmount) {
        this.waterAmount = waterAmount;
    }

    public void setMilkAmount(int milkAmount) {
        this.milkAmount = milkAmount;
    }

    public void setCoffeeAmount(int coffeeAmount) {
        this.coffeeAmount = coffeeAmount;
    }

    private void log(String message) {
        logList.add(message);
    }

    public void turnOn() {
        this.stateMachine = true;
        System.out.println("Кофемашина включена");
    }

    public void turnOff() {
        this.stateMachine = false;
        System.out.println("Кофемашина выключена");
    }

    public void makePersonCoffee(CoffeeType coffee, int amount) {
        if (this.coffeeAmount >= amount * coffee.getCoffeeAmount() &&
                this.milkAmount >= amount * coffee.getMilkAmount() &&
                this.waterAmount >= amount * coffee.getWaterAmount()) {
            if (this.madeCups < maxMadeCups) {
                setWaterAmount(this.waterAmount - amount * coffee.getWaterAmount());
                setCoffeeAmount(this.coffeeAmount - amount * coffee.getCoffeeAmount());
                setMilkAmount(this.milkAmount - amount * coffee.getMilkAmount());
                System.out.println("Кофе готов");
                this.madeCups += amount;
                if (Objects.equals(coffee.getName(), "Латте")) {
                    logCountLatte += amount;
                } else {
                    logCountEspresso += amount;
                }

            } else {
                System.out.println("Максимальное количество чашек достигнуто");
            }
        } else {
            System.out.println("Недостаточно ингредиентов");
        }
    }

    public void makeCoffee(int amount) {
        if (!getStateMachine()) {
            System.out.println("Машина выключена");
            return;
        }
        System.out.println("Выберите кофе:" +
                "\n1.Латте" +
                "\n2.Еспрессо");
        switch (console.nextLine()) {
            case "1" -> {
                makePersonCoffee(CoffeeType.LATTE, amount);
            }
            case "2" -> {
                makePersonCoffee(CoffeeType.ESPRESSO, amount);
            }
            default -> System.out.println("Такого кофе нет");
        }
    }

    public void makeCoffeeFromProfile() {
        if (!getStateMachine()) {
            System.out.println("Машина выключена");
            return;
        }
        for (int i = 0; i < personList.size(); i++) {
            System.out.println(personList.get(i).getName());
        }
        System.out.println("Введите имя профиля");
        String name = console.nextLine();
        for (Person person : personList) {
            if (Objects.equals(person.getName(), name)) {
                makePersonCoffee(person.getCoffeeLatte(), person.getAmountLatte());
                makePersonCoffee(person.getCoffeeEspresso(), person.getAmountEspresso());
            } else {
                System.out.println("Профиль не найден");
            }
        }
    }

    public void addWater() {
        System.out.println("Введите кол-во воды");
        try {
            int waterAmount = Integer.parseInt(console.nextLine());
            if (this.waterAmount + waterAmount <= maxWaterAmount && waterAmount > 0) {
                setWaterAmount(this.waterAmount + waterAmount);
                System.out.println("Вода успешно добавлена");
                log("Добавлена вода " + waterAmount + " миллилитров");
            } else {
                System.out.println("Проверьте ингредиенты и измените значение");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка ввода");
        }
    }

    public void addMilk() {
        System.out.println("Введите кол-во молока");
        try {
            int milkAmount = Integer.parseInt(console.nextLine());
            if (this.milkAmount + milkAmount <= maxMilkAmount && milkAmount > 0) {
                setMilkAmount(this.milkAmount + milkAmount);
                System.out.println("Молоко успешно добавлено");
                log("Добавлено молоко " + milkAmount + " миллилитров");
            } else {
                System.out.println("Проверьте ингредиенты и измените значение");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка ввода");
        }
    }

    public void addCoffee() {
        System.out.println("Введите кол-во кофе");
        try {
            int coffeeAmount = Integer.parseInt(console.nextLine());
            if (this.coffeeAmount + coffeeAmount <= maxCoffeeAmount && coffeeAmount > 0) {
                setCoffeeAmount(this.coffeeAmount + coffeeAmount);
                System.out.println("Кофе успешно добавлено");
                log("Добавлено кофе " + coffeeAmount + " грамм");
            } else {
                System.out.println("Проверьте ингредиенты и измените значение");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка ввода");
        }
    }

    public void addPerson() {
        System.out.println("Введите имя профиля");
        String personName = console.nextLine();
        boolean canAdd = personList.stream().noneMatch(person -> person.getName().equals(personName));
        if (canAdd) {
            System.out.println("Выберите кол-во латте в профиле");
            int amountLatte = Integer.parseInt(console.nextLine());
            System.out.println("Введите кол-во Еспрессо");
            int amountEspresso = Integer.parseInt(console.nextLine());
            if (amountLatte > 0 & amountEspresso > 0) {
                personList.add(new Person(personName, CoffeeType.LATTE, amountLatte,
                        CoffeeType.ESPRESSO, amountEspresso));
                System.out.println("Профиль добавлен");
                log("Добавлен профиль " + personName);
            } else {
                System.out.println("Не верное значение порций");
            }
        } else {
            System.out.println("Такой профиль уже имеется");
        }
    }

    public void showLog() {
        System.out.println("Лог приготовленных напитков:");
        System.out.println("Приготовлено латте:" + logCountLatte);
        System.out.println("Приготовлено еспрессо:" + logCountEspresso + "\n");

        for (String logEntry : logList) {
            System.out.println(logEntry);
        }
    }

    public void showRecipe() {
        System.out.println("Выберите кофе:" +
                "\n1.Латте" +
                "\n2.Еспрессо");
        switch (console.nextLine()) {
            case "1" -> {
                CoffeeType latte = CoffeeType.LATTE;
                System.out.println("Кофе: " + latte.getCoffeeAmount() + " грамм" +
                        "\nМолоко: " + latte.getMilkAmount() + " миллилитров" +
                        "\nВода: " + latte.getWaterAmount() + " миллилитров");
                log("Выведен рецепт");
            }
            case "2" -> {
                CoffeeType espresso = CoffeeType.ESPRESSO;
                System.out.println("Кофе: " + espresso.getCoffeeAmount() + " грамм" +
                        "\nМолоко: " + espresso.getMilkAmount() + " миллилитров" +
                        "\nВода: " + espresso.getWaterAmount() + " миллилитров");
                log("Выведен рецепт");
            }
            default -> System.out.println("Такого кофе нет");
        }
    }

    public void showMachineIngredient() {
        System.out.println("Кофе: " + getCoffeeAmount() + " грамм");
        System.out.println("Вода: " + getWaterAmount() + " миллилитров");
        System.out.println("Молоко: " + getMilkAmount() + " миллилитров");
        log("Выведены ресурсы кофемашины");
    }

    public void cleanMachine() {
        if (this.madeCups > 0) {
            this.madeCups = 0;
            System.out.println("Машина очищена");
            log("Очищена машина");
        } else {
            System.out.println("Машина уже чистая");
        }
    }

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        CoffeeMachine coffeeMachine = new CoffeeMachine(1000, 1000, 1000, 0);

        System.out.println("Кофемашина. Выберите команды, которые желаете выполнить:" +
                "\n1.Включить" +
                "\n2.Выключить" +
                "\n3.Сделать кофе" +
                "\n4.Сделать 3 кофе" +
                "\n5.Выбрать кол-во кофе" +
                "\n6.Добавить профиль" +
                "\n7.Проверить ингредиенты" +
                "\n8.Почистить кофемашину" +
                "\n9.Добавить воду" +
                "\n10.Добавить молоко" +
                "\n11.Добавить кофе" +
                "\n12.Проверка чистоты" +
                "\n13.Приготовить кофе из профиля" +
                "\n14.Показать рецепт кофе" +
                "\n15.Показать лог приготовленных напитков");

        while (true) {
            switch (console.nextLine()) {
                case "1" -> coffeeMachine.turnOn();
                case "2" -> coffeeMachine.turnOff();
                case "3" -> coffeeMachine.makeCoffee(1);
                case "4" -> coffeeMachine.makeCoffee(3);
                case "5" -> {
                    System.out.println("Введите кол-во порций");
                    int amount = console.nextInt();
                    console.nextLine();
                    if (amount > 0) {
                        coffeeMachine.makeCoffee(amount);
                    }
                }
                case "6" -> coffeeMachine.addPerson();
                case "7" -> coffeeMachine.showMachineIngredient();
                case "8" -> coffeeMachine.cleanMachine();
                case "9" -> coffeeMachine.addWater();
                case "10" -> coffeeMachine.addMilk();
                case "11" -> coffeeMachine.addCoffee();
                case "12" -> System.out.println("Машина загрязнена на " + coffeeMachine.getMadeCups());
                case "13" -> coffeeMachine.makeCoffeeFromProfile();
                case "14" -> coffeeMachine.showRecipe();
                case "15" -> coffeeMachine.showLog();
                default -> System.out.println("Нет такой команды");
            }
        }
    }
}
