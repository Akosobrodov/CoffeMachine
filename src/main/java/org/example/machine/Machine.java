package org.example.machine;

public class Machine {

    private static boolean stateMachine;

    Machine(boolean state) {
        this.stateMachine = state;
    }

    public void turnOn() {
        this.stateMachine = true;
        System.out.println("Кофемашина включена");
    }

    public void turnOff() {
        this.stateMachine = false;
        System.out.println("Кофемашина выключена");
    }

    public boolean getStateMachine() {
        return stateMachine;
    }
}
