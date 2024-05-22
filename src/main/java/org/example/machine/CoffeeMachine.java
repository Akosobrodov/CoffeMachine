package org.example.machine;

import org.example.Person.Person;
import org.example.coffe.Coffee;
import org.example.coffe.CoffeeType;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class CoffeeMachine extends Machine {

    private static final int maxCoffeeAmount = 1000;

    private static final int maxMilkAmount = 1000;

    private static final int maxWaterAmount = 1000;

    private static final int maxMadeCups = 10;

    private int coffeeAmount;

    private int milkAmount;

    private int waterAmount;

    private int madeCups;

    private List<String> logList;

    CoffeeMachine(boolean state, int coffeeAmount, int milkAmount, int waterAmount, int madeCups) {
        super(state);
        this.waterAmount = waterAmount;
        this.coffeeAmount = coffeeAmount;
        this.milkAmount = milkAmount;
        this.madeCups = madeCups;
        this.logList = new ArrayList<>();
    }

    public void cleanMachine() {
        if (this.madeCups > 0) {
            this.madeCups = 0;
            System.out.println("Машина очищена");
        } else {
            System.out.println("Машина уже чистая");
        }
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

    public void makeCoffee(CoffeeType coffeeType) {
        Coffee coffee = new Coffee(coffeeType);
        if (getStateMachine()) {
            if (this.coffeeAmount >= coffee.getCoffeeAmount() &&
                    this.milkAmount >= coffee.getMilkAmount() &&
                    this.waterAmount >= coffee.getWaterAmount()) {
                if (this.madeCups < maxMadeCups) {
                    setWaterAmount(this.waterAmount - coffee.getWaterAmount());
                    setCoffeeAmount(this.coffeeAmount - coffee.getCoffeeAmount());
                    setMilkAmount(this.milkAmount - coffee.getMilkAmount());
                    System.out.println("Кофе готов");
                    this.madeCups += 1;
                    log("Приготовлен кофе: " + coffee.getCoffeeName());
                } else {
                    System.out.println("Кофемашина грязная, можно приготовить только после очистки");
                }
            } else {
                System.out.println("Не хватает ингредиентов");
            }
        } else {
            System.out.println("Кофемашина выключена");
        }
    }

    public void makeCoffee(CoffeeType coffeeType, int amount) {
        Coffee coffee = new Coffee(coffeeType);
        if (getStateMachine()) {
            if (this.coffeeAmount >= amount * coffee.getCoffeeAmount() &&
                    this.milkAmount >= amount * coffee.getMilkAmount() &&
                    this.waterAmount >= amount * coffee.getWaterAmount()) {
                if (this.madeCups + amount <= maxMadeCups) {
                    setWaterAmount(this.waterAmount - (amount * coffee.getWaterAmount()));
                    setCoffeeAmount(this.coffeeAmount - (amount * coffee.getCoffeeAmount()));
                    setMilkAmount(this.milkAmount - (amount * coffee.getMilkAmount()));
                    System.out.println("Кофе готов");
                    this.madeCups += amount;
                    log("Приготовлены кофе: " + coffee.getCoffeeName() + " " + amount + " порций");
                } else {
                    System.out.println("Кофемашина грязная, можно приготовить только после очистки");
                }
            } else {
                System.out.println("Не хватает ингредиентов");
            }
        } else {
            System.out.println("Кофемашина выключена");
        }
    }

    public void addWater(int waterAmount) {
        if (this.waterAmount + waterAmount <= maxWaterAmount) {
            setWaterAmount(this.waterAmount + waterAmount);
            System.out.println("Вода успешно добавлена");
        } else {
            System.out.println("Слишком много, измените значение");
        }
    }

    public void addMilk(int milkAmount) {
        if (this.milkAmount + milkAmount <= maxMilkAmount) {
            setMilkAmount(this.milkAmount + milkAmount);
            System.out.println("Молоко успешно добавлено");
        } else {
            System.out.println("Слишком много, измените значение");
        }
    }

    public void addCoffee(int coffeeAmount) {
        if (this.coffeeAmount + coffeeAmount <= maxCoffeeAmount) {
            setCoffeeAmount(this.coffeeAmount + coffeeAmount);
            System.out.println("Кофе успешно добавлено");
        } else {
            System.out.println("Слишком много, измените значение");
        }
    }

    public void showLog() {
        System.out.println("Лог приготовленных напитков:");
        for (String logEntry : logList) {
            System.out.println(logEntry);
        }
    }

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        CoffeeMachine coffeeMachine = new CoffeeMachine(false, 1000, 1000, 1000, 0);

        List<Person> personList = new ArrayList<>();

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
                case "3" -> {
                    System.out.println("Выберите кофе:" +
                            "\n1.Латте" +
                            "\n2.Еспрессо");
                    switch (console.nextLine()) {
                        case "1" -> coffeeMachine.makeCoffee(CoffeeType.LATTE);
                        case "2" -> coffeeMachine.makeCoffee(CoffeeType.ESPRESSO);
                        default -> System.out.println("Такого кофе нет");
                    }
                }
                case "4" -> {
                    System.out.println("Выберите кофе (3 порции):" +
                            "\n1.Латте" +
                            "\n2.Еспрессо");
                    switch (console.nextLine()) {
                        case "1" -> coffeeMachine.makeCoffee(CoffeeType.LATTE, 3);
                        case "2" -> coffeeMachine.makeCoffee(CoffeeType.ESPRESSO, 3);
                        default -> System.out.println("Такого кофе нет");
                    }
                }
                case "5" -> {
                    System.out.println("Выберите кофе:" +
                            "\n1.Латте" +
                            "\n2.Еспрессо");
                    switch (console.nextLine()) {
                        case "1" -> {
                            try {
                                System.out.println("Введите кол-во порций");
                                int amount = console.nextInt();
                                console.nextLine();
                                coffeeMachine.makeCoffee(CoffeeType.LATTE, amount);
                            } catch (InputMismatchException e) {
                                System.out.println("Ошибка ввода");
                            }

                        }
                        case "2" -> {
                            try {
                                System.out.println("Введите кол-во порций");
                                int amount = console.nextInt();
                                console.nextLine();
                                coffeeMachine.makeCoffee(CoffeeType.ESPRESSO, amount);
                            } catch (InputMismatchException e) {
                                System.out.println("Ошибка ввода");
                            }

                        }
                        default -> System.out.println("Такого кофе нет");
                    }
                }
                case "6" -> {
                    System.out.println("Введите имя профиля");
                    String personName = console.nextLine();
                    System.out.println("Выберите кофе:" +
                            "\n1.Латте" +
                            "\n2.Еспрессо");
                    switch (console.nextLine()) {
                        case "1" -> {
                            personList.add(new Person(personName, new Coffee(CoffeeType.LATTE)));
                            System.out.println("Профиль добавлен");
                        }
                        case "2" -> {
                            personList.add(new Person(personName, new Coffee(CoffeeType.ESPRESSO)));
                            System.out.println("Профиль добавлен");
                        }
                        default -> System.out.println("Такого кофе нет");
                    }
                }
                case "7" -> {
                    System.out.println("Кофе: " + coffeeMachine.getCoffeeAmount() + " грамм");
                    System.out.println("Вода: " + coffeeMachine.getWaterAmount() + " миллилитров");
                    System.out.println("Молоко: " + coffeeMachine.getMilkAmount() + " миллилитров");
                }
                case "8" -> coffeeMachine.cleanMachine();
                case "9" -> {
                    try {
                        System.out.println("Введите кол-во воды:");
                        int amount = console.nextInt();
                        console.nextLine();
                        coffeeMachine.addWater(amount);
                    } catch (InputMismatchException e) {
                        System.out.println("Ошибка ввода");
                    }
                }
                case "10" -> {
                    try {
                        System.out.println("Введите кол-во молока:");
                        int amount = console.nextInt();
                        console.nextLine();
                        coffeeMachine.addMilk(amount);
                    } catch (InputMismatchException e) {
                        System.out.println("Ошибка ввода");
                    }
                }
                case "11" -> {
                    try {
                        System.out.println("Введите кол-во кофе:");
                        int amount = console.nextInt();
                        console.nextLine();
                        coffeeMachine.addCoffee(amount);
                    } catch (InputMismatchException e) {
                        System.out.println("Ошибка ввода");
                    }

                }
                case "12" -> {
                    System.out.println("Машина загрязнена на " + coffeeMachine.getMadeCups());
                }
                case "13" -> {
                    System.out.println("Введите имя пользователя :");
                    String name = console.nextLine();
                    for (Person person : personList) {
                        if (Objects.equals(person.getName(), name)) {
                            coffeeMachine.makeCoffee(person.getCoffee().getType());
                        }
                    }
                }
                case "14" -> {
                    System.out.println("Выберите кофе:" +
                            "\n1.Латте" +
                            "\n2.Еспрессо");
                    switch (console.nextLine()) {
                        case "1" -> {
                            CoffeeType latte = CoffeeType.LATTE;
                            System.out.println("Кофе: " + latte.getCoffeeAmount() + " грамм" +
                                    "\nМолоко: " + latte.getMilkAmount() + " миллилитров" +
                                    "\nВода: " + latte.getWaterAmount() + " миллилитров");
                        }
                        case "2" -> {
                            CoffeeType espresso = CoffeeType.ESPRESSO;
                            System.out.println("Кофе: " + espresso.getCoffeeAmount() + " грамм" +
                                    "\nМолоко: " + espresso.getMilkAmount() + " миллилитров" +
                                    "\nВода: " + espresso.getWaterAmount() + " миллилитров");
                        }
                        default -> System.out.println("Такого кофе нет");
                    }
                }
                case "15" -> coffeeMachine.showLog();
                default -> System.out.println("Нет такой команды");
            }
        }
    }
}
