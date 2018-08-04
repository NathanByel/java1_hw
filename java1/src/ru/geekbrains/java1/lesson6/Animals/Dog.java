package ru.geekbrains.java1.lesson6.Animals;

public class Dog extends Animal {
    private static final float MAX_RUN = 500f;
    private static final float MAX_JUMP = 0.5f;
    private static final float MAX_SWIM = 10f;
    private static final int RANGE_PERCENT = 10;

    public Dog(String name, int age) {
        super(name, age, MAX_RUN, MAX_JUMP, MAX_SWIM, RANGE_PERCENT);
    }

    @Override
    public void printInfo() {
        System.out.println("Собака");
        super.printInfo();
    }
}
