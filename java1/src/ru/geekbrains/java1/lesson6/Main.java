package ru.geekbrains.java1.lesson6;

/*
    1. Создать классы Собака и Кот с наследованием от класса Животное.
    2. Животные могут выполнять действия: бежать, плыть, перепрыгивать препятствие. В качестве параметра
       каждому методу передается величина, означающая или длину препятствия (для бега и плавания), или высоту (для прыжков).

    3. У каждого животного есть ограничения на действия
       (бег: кот 200 м., собака 500 м.; прыжок: кот 2 м., собака 0.5 м.; плавание: кот не умеет плавать, собака 10 м.).

    4. При попытке животного выполнить одно из этих действий, оно должно сообщить результат в консоль.
       (Например, dog1.run(150); -> результат: run: true)

    5. * Добавить животным разброс в ограничениях. То есть у одной собаки ограничение на бег может быть 400 м., у другой 600 м.
*/

import ru.geekbrains.java1.lesson6.Animals.Animal;
import ru.geekbrains.java1.lesson6.Animals.Cat;
import ru.geekbrains.java1.lesson6.Animals.Dog;
import ru.geekbrains.java1.lesson6.Animals.NameGenerator;

import java.util.Random;

public class Main {
    // Получить случайное число с округлением до 1 знака после точки.
    private static double getRandomRound1(float min, float max) {
        return Math.round((min + Math.random() * (max-min)) * 10f) / 10f;
    }

    public static void main(String[] args) {
        Random rand = new Random();
        NameGenerator nameGen = new NameGenerator();

        Animal[] animals = new Animal[100];
        for(int i = 0; i < animals.length; i++) {
            if( rand.nextBoolean() ) {
                animals[i] = new Cat(nameGen.getRandomName(), 1 + rand.nextInt(19));
            } else {
                animals[i] = new Dog(nameGen.getRandomName(), 1 + rand.nextInt(19));
            }
        }

        // Пробегаемся по созданным животным. Можно было сделать и в предыдущем цикле, вынес для наглядности.
        for(Animal animal: animals) {
            animal.printInfo();
            animal.run((float)getRandomRound1(100f, 800f));   // 100-800
            animal.jump((float)getRandomRound1(0.1f, 3f));    // 0.1 - 3
            animal.swim((float)getRandomRound1(5f, 15f));     // 5 - 15
            System.out.println();
        }
    }
}
