package ru.geekbrains.java1.lesson6.Animals;

public class Animal {
    private String  name;
    private int     age;
    private float   maxRun;
    private float   maxJump;
    private float   maxSwim;

    // Получить случайное число с округлением до 1 знака после точки.
    private double getRandomRound1(float min, float max) {
        return Math.round((min + Math.random() * (max-min)) * 10f) / 10f;
    }

    private boolean checkAction(float value, float max){
        if(value <= max) {
            System.out.println("true");
            return true;
        } else {
            System.out.println("false");
            return false;
        }
    }

    public Animal(String name, int age, float maxRun, float maxJump, float maxSwim) {
        this.name = name;
        this.age = age;
        this.maxRun = maxRun;
        this.maxJump = maxJump;
        this.maxSwim = maxSwim;
    }

    public Animal(String name, int age, float maxRun, float maxJump, float maxSwim, int percent) {
        this.name = name;
        this.age = age;
        this.maxRun = maxRun;
        this.maxJump = maxJump;
        this.maxSwim = maxSwim;

        float min, max;
        if (percent > 100) {
            percent = 100;
        }

        min = this.maxRun - this.maxRun / 100 * percent;
        max = this.maxRun + this.maxRun / 100 * percent;
        this.maxRun = (float)this.getRandomRound1(min, max);

        min = this.maxJump - this.maxJump / 100 * percent;
        max = this.maxJump + this.maxJump / 100 * percent;
        this.maxJump = (float)this.getRandomRound1(min, max);

        min = this.maxSwim - this.maxSwim / 100 * percent;
        max = this.maxSwim + this.maxSwim / 100 * percent;
        this.maxSwim = (float)this.getRandomRound1(min, max);
    }

    public void printInfo() {
        System.out.println(
                "Имя:          " + this.name    + "\n" +
                "Возраст:      " + this.age     + "\n" +
                "Макс. бег:    " + this.maxRun  + "м\n" +
                "Макс. прыжок: " + this.maxJump + "м\n" +
                "Макс. заплыв: " + this.maxSwim + "м"
        );
    }

    public boolean run(float distance){
        System.out.print("-> Попытка бега на " + distance + "м: ");
        return checkAction(distance, this.maxRun);
    }

    public boolean jump(float height) {
        System.out.print("-> Попытка прыжка на " + height + "м: ");
        return checkAction(height, this.maxJump);
    }

    public boolean swim(float distance) {
        System.out.print("-> Попытка заплыва на " + distance + "м: ");
        return checkAction(distance, this.maxSwim);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
