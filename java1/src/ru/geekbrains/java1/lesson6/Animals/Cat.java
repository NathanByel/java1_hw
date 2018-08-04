package ru.geekbrains.java1.lesson6.Animals;

public class Cat extends Animal {
    private static final float MAX_RUN = 200f;
    private static final float MAX_JUMP = 2f;
    private static final float MAX_SWIM = 0;
    private static final int RANGE_PERCENT = 20;

    public Cat(String name, int age) {
        super(name, age, MAX_RUN, MAX_JUMP, MAX_SWIM, RANGE_PERCENT);
    }

    @Override
    public void printInfo() {
        System.out.println("Кот");
        super.printInfo();
    }
}
