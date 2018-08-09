package ru.geekbrains.java1.lesson7;

public class Cat {
    private String name;
    private int hungerSpeed;
    private int fullness = 1;
    private int maxFullness;
    private long timer = 0;
    private Plate plate = null;

    public Cat(String name, int maxFullness, int hungerSpeed)
    {
        this.name = name;
        this.maxFullness = maxFullness;
        this.hungerSpeed = hungerSpeed;
    }

    public Cat(String name, int maxFullness, int hungerSpeed, Plate plate)
    {
        this(name, maxFullness, hungerSpeed);
        this.plate = plate;
    }

    public void setPlate(Plate plate) {
        this.plate = plate;
    }

    private void tryToEat() {
        if (plate != null) {
            if (plate.takeFood(maxFullness)) {
                System.out.println(this.name + " поел!");
                fullness = maxFullness;
            } else {
                System.out.printf("%s не смог поесть, мало еды. Нужно: %d, в тарелке есть: %d\n",
                        this.name, maxFullness, plate.getTotalFood());
            }
        } else {
            System.out.println(this.name + " не смог поесть, нет тарелки!");
        }
    }

    public void update() {
        if(System.currentTimeMillis() > timer + 1000) {
            timer = System.currentTimeMillis();

            if(fullness > 0) {
                int df = fullness - hungerSpeed;
                if(df > 0) {
                    fullness = df;
                    System.out.println(this.name + " гуляет... Сытость: " + fullness);
                } else {
                    fullness = 0;
                    System.out.println(this.name + " проголодался!");
                    tryToEat();
                }
            } else {
                tryToEat();
            }
            System.out.println();
        }
    }
}
