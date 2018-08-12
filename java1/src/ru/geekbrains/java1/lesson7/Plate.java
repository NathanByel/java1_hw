package ru.geekbrains.java1.lesson7;

public class Plate {
    private int addFoodSpeed;
    private int maxFood;
    private int food;
    private boolean autoAdd;
    private long timer = 0;

    private void checkInValueFood(int food) {
        if(food < 0) {
            throw new IllegalArgumentException("Food can't be < 0");
        }
    }

    public Plate(int maxFood, int foodNum, int addFoodSpeed, boolean autoAdd) {
        checkInValueFood(foodNum);
        if(maxFood <= 0) {
            throw new IllegalArgumentException("maxFood can't be <= 0");
        }

        if(addFoodSpeed <= 0) {
            throw new IllegalArgumentException("addFoodSpeed can't be <= 0");
        }
        this.maxFood = maxFood;
        this.food = foodNum;
        this.addFoodSpeed = addFoodSpeed;
        this.autoAdd = autoAdd;
    }

    public void addFood(int foodNum) {
        checkInValueFood(foodNum);
        food += foodNum;
        System.out.printf("Добавлено еды: %d, Всего в тарелке: %d\n", foodNum, food);
    }

    public boolean takeFood(int foodNum) {
        checkInValueFood(foodNum);
        System.out.printf("Тарелка: Попытка взять %d", foodNum);
        if(foodNum <= food) {
            food -= foodNum;
            System.out.printf(" - Успешно. Осталось в тарелке: %d\n", food);
            return true;
        }
        System.out.printf(" - Нет столько в тарелке! Есть только: %d\n", food);
        return false;
    }

    public int getTotalFood() {
        return food;
    }

    public void setTotalFood(int food) {
        checkInValueFood(food);
        this.food = food;
    }

    public void update() {
        if(System.currentTimeMillis() > timer + 1000) {
            timer = System.currentTimeMillis();

            if (autoAdd && (food < maxFood) ) {
                int fd = food + addFoodSpeed;
                food = (fd < maxFood)? fd: maxFood;
                System.out.printf("Тарелка: Добавлено еды %d, всего %d\n\n", addFoodSpeed, food);
            }
        }
    }
}
