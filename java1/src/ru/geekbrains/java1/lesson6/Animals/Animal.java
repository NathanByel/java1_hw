package ru.geekbrains.java1.lesson6.Animals;

public abstract class Animal {
    private String type;
    private String name;
    private int    age;
    private float  maxRunDistance;
    private float  maxJumpHeight;
    private float  maxSwimDistance;

    // Private methods *************************************************************************************************
    // Получить случайное число с округлением до 1 знака после точки.
    private double getRandomRound1(float min, float max) {
        return Math.round((min + Math.random() * (max-min)) * 10f) / 10f;
    }

    // Constructors ****************************************************************************************************
    public Animal(String type, String name, int age, float maxRunDistance, float maxJumpHeight, float maxSwimDistance) {
        if( (type == null) || (type.length() == 0) ) {
            throw new IllegalArgumentException("Type can't be null or empty.");
        }

        if( (name == null) || (name.length() == 0) ) {
            throw new IllegalArgumentException("Name can't be null or empty.");
        }

        if(age < 1) {
            throw new IllegalArgumentException("Age can't be less that one.");
        }

        this.type = type;
        this.name = name;
        this.age  = age;
        this.maxRunDistance  = maxRunDistance;
        this.maxJumpHeight   = maxJumpHeight;
        this.maxSwimDistance = maxSwimDistance;
    }

    public Animal(String type, String name, int age, float maxRunDistance, float maxJumpHeight, float maxSwimDistance, int percent) {
        this(type, name, age, maxRunDistance, maxJumpHeight, maxSwimDistance);

        if (percent > 100) {
            percent = 100;
        }

        float min, max;
        min = this.maxRunDistance - this.maxRunDistance / 100 * percent;
        max = this.maxRunDistance + this.maxRunDistance / 100 * percent;
        this.maxRunDistance = (float)this.getRandomRound1(min, max);

        min = this.maxJumpHeight - this.maxJumpHeight / 100 * percent;
        max = this.maxJumpHeight + this.maxJumpHeight / 100 * percent;
        this.maxJumpHeight = (float)this.getRandomRound1(min, max);

        min = this.maxSwimDistance - this.maxSwimDistance / 100 * percent;
        max = this.maxSwimDistance + this.maxSwimDistance / 100 * percent;
        this.maxSwimDistance = (float)this.getRandomRound1(min, max);
    }

    // Public methods **************************************************************************************************
    public void printInfo() {
        System.out.println(
                "Животное:     " + this.type            + "\n"  +
                "Имя:          " + this.name            + "\n"  +
                "Возраст:      " + this.age             + "\n"  +
                "Макс. бег:    " + this.maxRunDistance  + "м\n" +
                "Макс. прыжок: " + this.maxJumpHeight   + "м\n" +
                "Макс. заплыв: " + this.maxSwimDistance + "м"
        );
    }

    public boolean run(float distance) {
        boolean result = (distance <= this.maxRunDistance);
        System.out.println(this.type + ", " + this.name + " -> Попытка бега на " + distance + "м: " + result);
        return result;
    }

    public boolean jump(float height) {
        boolean result = (height <= this.maxJumpHeight);
        System.out.println(this.type + ", " + this.name + " -> Попытка прыжка на " + height + "м: " + result);
        return result;
    }

    public boolean swim(float distance) {
        boolean result = (distance <= this.maxSwimDistance);
        System.out.println(this.type + ", " + this.name + " -> Попытка заплыва на " + distance + "м: " + result);
        return result;
    }
}
