package ru.geekbrains.java1.lesson6.Animals;

public class Cat extends Animal {
    private static final float MAX_RUN_DISTANCE  = 200f;
    private static final float MAX_JUMP_HEIGHT   = 2f;
    private static final float MAX_SWIM_DISTANCE = 0;
    private static final int   RANGE_PERCENT     = 20;

    // Constructors ****************************************************************************************************
    public Cat(String name, int age) {
        super("Кот", name, age, MAX_RUN_DISTANCE, MAX_JUMP_HEIGHT, MAX_SWIM_DISTANCE, RANGE_PERCENT);
    }
}
