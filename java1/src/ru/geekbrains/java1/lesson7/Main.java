package ru.geekbrains.java1.lesson7;

import ru.geekbrains.java1.lesson6.Animals.NameGenerator;

import java.util.Random;

/*
    1. Расширить задачу про котов и тарелки с едой
    2. Сделать так, чтобы в тарелке с едой не могло получиться отрицательного количества еды
       (например, в миске 10 еды, а кот пытается покушать 15-20)
    3. Каждому коту нужно добавить поле сытость (когда создаем котов, они голодны).
       Если коту удалось покушать (хватило еды), сытость = true
    4. Считаем, что если коту мало еды в тарелке, то он ее просто не трогает, то есть не может быть наполовину сыт
       (это сделано для упрощения логики программы)
    5. Создать массив котов и тарелку с едой, попросить всех котов покушать из этой тарелки и потом
       вывести информацию о сытости котов в консоль
    6. Добавить в тарелку метод, с помощью которого можно было бы добавлять еду в тарелку
*/
public class Main {
    public static void main(String[] args) {
        Random rand = new Random();
        NameGenerator nameGen = new NameGenerator();

        Plate plate = new Plate(
                100,
                100,
                20,
                true );

        Cat cats[] = new Cat[3];
        for(int i = 0; i < cats.length; i++) {
            cats[i] = new Cat(
                    nameGen.getRandomName(),
                    40 + rand.nextInt(20),
                    1 + rand.nextInt(5),
                    plate );
        }

        while(true) {
            plate.update();

            for (int i = 0; i < cats.length; i++) {
                cats[i].update();
            }
        }
    }
}
