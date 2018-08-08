package ru.geekbrains.java1.lesson6.Animals;

public class Dog extends Animal {
    private static final float MAX_RUN_DISTANCE  = 500f;
    private static final float MAX_JUMP_HEIGHT   = 0.5f;
    private static final float MAX_SWIM_DISTANCE = 10f;
    private static final int   RANGE_PERCENT     = 10;

    // Constructors ****************************************************************************************************
    public Dog(String name, int age) {
        super("Собака", name, age, MAX_RUN_DISTANCE, MAX_JUMP_HEIGHT, MAX_SWIM_DISTANCE, RANGE_PERCENT);
    }
}
