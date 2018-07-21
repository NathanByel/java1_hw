package ru.geekbrains.java1.lesson1;

public class Main {
    public static void main(String[] args) {
        // Task 2
        // Создать переменные всех пройденных типов данных, и инициализировать их значения.
        byte    varTypeByte      = 10;
        short   varTypeShort     = -10000;
        int     varTypeInt       = 2000000;
        long    varTypeLong      = 30000000000L;
        float   varTypeFloat     = 12.5F;
        double  varTypeDouble    = -10.5;
        char    varTypeChar      = 'A';
        boolean varTypeBool      = true;

        System.out.println("Task 2");
        System.out.println(
                "varTypeByte = "    + varTypeByte   + '\n' +
                "varTypeShort = "   + varTypeShort  + '\n' +
                "varTypeInt = "     + varTypeInt    + '\n' +
                "varTypeLong = "    + varTypeLong   + '\n' +
                "varTypeFloat = "   + varTypeFloat  + '\n' +
                "varTypeDouble = "  + varTypeDouble + '\n' +
                "varTypeChar = "    + varTypeChar   + '\n' +
                "varTypeBool = "    + varTypeBool );

        // Task 3
        System.out.println("\nTask 3");
        System.out.println( "calcFunc result = " + calcFunc(10, 5, 50, 10) );

        // Task 4
        System.out.println("\nTask 4");
        System.out.println( "Numbers 1, 5: " + checkNumbers(1, 5) );
        System.out.println( "Numbers 5, 5: " + checkNumbers(5, 5) );
        System.out.println( "Numbers 5, 10: " + checkNumbers(5, 10) );
        System.out.println( "Numbers 10, 10: " + checkNumbers(10, 10) );
        System.out.println( "Numbers 10, 15: " + checkNumbers(10, 15) );

        // Task 5
        System.out.println("\nTask 5");
        checkPositive(-5);
        checkPositive(0);
        checkPositive(5);

        // Task 6
        System.out.println("\nTask 6");
        System.out.println("Number -1: " + checkNegative(-1));
        System.out.println("Number  5: " + checkNegative(5));

        // Task 7
        System.out.println("\nTask 7");
        printHello("Маша");
        printHello("Саша");

        // Task 8
        System.out.println("\nTask 8");
        System.out.println("Year 2000: " + isLeapYear(2000)); // true
        System.out.println("Year 2004: " + isLeapYear(2004)); // true
        System.out.println("Year 2008: " + isLeapYear(2008)); // true
        System.out.println("Year 2011: " + isLeapYear(2011)); // false
        System.out.println("Year 2100: " + isLeapYear(2100)); // false
        System.out.println("Year 2400: " + isLeapYear(2400)); // true
        System.out.println("Year 2501: " + isLeapYear(2501)); // false
    }

    // Task 3
    // Написать метод вычисляющий выражение a * (b + (c / d)) и возвращающий результат,
    // где a, b, c, d – входные параметры этого метода;
    public static long calcFunc(int a, int b, int c, int d) {
        if(d == 0) {
            System.out.println("На ноль делить нельзя!");
            return 0;
        }
        return a * (b + (c / d));
    }

    // Task 4
    // Написать метод, принимающий на вход два числа, и проверяющий что их сумма лежит в пределах от 10 до 20(включительно),
    // если да – вернуть true, в противном случае – false;
    public static boolean checkNumbers(int num1, int num2) {
        int sum = num1 + num2;
        return sum >= 10 && sum <= 20;
    }

    // Task 5
    // Написать метод, которому в качестве параметра передается целое число, метод должен напечатать в консоль
    // положительное ли число передали, или отрицательное; Замечание: ноль считаем положительным числом.
    public static void checkPositive(int num) {
        System.out.print("Number " + num + " is ");
        if(num >= 0) {
            System.out.println("positive");
        } else {
            System.out.println("negative");
        }
    }

    // Task 6
    // Написать метод, которому в качестве параметра передается целое число,
    // метод должен вернуть true, если число отрицательное.
    public static boolean checkNegative(int num) {
        return num < 0;
    }

    // Task 7
    // 7. Написать метод, которому в качестве параметра передается строка, обозначающая имя,
    // метод должен вывести в консоль сообщение «Привет, указанное_имя!»
    public static void printHello(String name) {
        System.out.println("Привет, " + name + '!');
    }

    // Task 8
    // Написать метод, который определяет является ли год високосным, и выводит сообщение в консоль.
    // Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.
    public static boolean isLeapYear(int year) {
        // Вариант 1
        if( (year % 4) == 0 ) {
            if( (year % 100) != 0 || (year % 400) == 0) {
                return true;
            }
        }
        return false;

        // Вариант 2
        // return (year % 4) == 0 && ((year % 100) != 0 || (year % 400 == 0));
    }
}
