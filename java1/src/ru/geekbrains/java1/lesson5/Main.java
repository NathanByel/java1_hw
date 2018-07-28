package ru.geekbrains.java1.lesson5;

/*
    Создать класс "Сотрудник" с полями: ФИО, должность, email, телефон, зарплата, возраст;
    Конструктор класса должен заполнять эти поля при создании объекта;
    Внутри класса «Сотрудник» написать метод, который выводит информацию об объекте в консоль;
    Создать массив из 5 сотрудников
    Пример:
    Person[] persArray = new Person[5]; // Вначале объявляем массив объектов
    persArray[0] = new Person("Ivanov Ivan", "Engineer", "ivivan@mailbox.com", "892312312", 30000, 30); // потом для каждой ячейки массива задаем объект
    persArray[1] = new Person(...);
    ...
    persArray[4] = new Person(...);

    С помощью цикла вывести информацию только о сотрудниках старше 40 лет;
*/

public class Main {
    public static void main(String[] args) {
        Worker[] workersArray = new Worker[5];

        workersArray[0] = new Worker(
                "Самойлов Максим Леонидович",
                "Курьер",
                "samleo@gmail.com",
                "89121232323",
                30000,
                22 );

        workersArray[1] = new Worker(
                "Коломейцев Семен Игоревич",
                "Программист C#",
                "sem@mail.ru",
                "+79995552233",
                110000,
                42 );

        workersArray[2] = new Worker(
                "Прокопчук Мария Федоровна",
                "Бухгалтер",
                "pr@bank.ru",
                "85550003535",
                70000,
                30 );

        workersArray[3] = new Worker(
                "Вознесенская Василиса Георгиевна",
                "WEB разработчик",
                "vasiliska@gg.com",
                "81111111111",
                150000,
                24 );

        workersArray[4] = new Worker(
                "Гладышев Николай Васильевич",
                "Уборщик",
                "glad@clean.com",
                "+79885553535",
                20000,
                48 );

        System.out.println("Сотрудники старше 40 лет:");
        for( Worker worker: workersArray) {
            if(worker.getAge() > 40) {
                worker.printInfo();
                System.out.println();
            }
        }
    }
}
