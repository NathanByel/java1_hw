package ru.geekbrains.java1.lesson3;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static String getText(int num) {
        if(num < 10 || num > 20) {
            num %= 10;
            if (num == 1) {
                return "-ой попытки";
            } else if (num >= 2 && num <= 4) {
                return "-х попыток";
            }
        }
        return "-и попыток";
    }

    public static void main(String[] args) {
        /*  Создать массив из слов String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado",
            "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut",
            "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};

            При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя, сравнивает его с
            загаданным словом и сообщает правильно ли ответил пользователь. Если слово не угадано, компьютер
            показывает буквы которые стоят на своих местах.

            apple – загаданное
            apricot - ответ игрока
            ap############# (15 символов, чтобы пользователь не мог узнать длину слова)

            Для сравнения двух слов посимвольно, можно пользоваться:
            String str = "apple";
            str.charAt(0); - метод, вернет char, который стоит в слове str на первой позиции

            Играем до тех пор, пока игрок не отгадает слово.
            Используем только маленькие буквы. */
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado",
                "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut",
                "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};

        Random rnd = new Random();
        while(true) {
            String thinkWord = words[rnd.nextInt(words.length)];
            System.out.println("Игра угадайка v1.0\n" +
                "Компьютер загадал слово, а Вы должны отгадать.\n" +
                "Когда надоест - введите 1 для выхода.");
            //System.out.println(thinkWord); // Debug

            int attempts = 0;
            char[] arrResult = new char[15];
            Arrays.fill(arrResult, '#');
            Scanner scanner = new Scanner(System.in);
            while(true) {
                attempts++;
                System.out.println("Итак, Ваш ответ: ");
                String answer = scanner.nextLine().toLowerCase();
                if(answer.equals("1")) {
                    return;
                }

                int cmpCnt = 0;
                for(int i = 0; (i < answer.length()) && (i < thinkWord.length()); i++) {
                    if(answer.charAt(i) == thinkWord.charAt(i)) {
                        arrResult[i] = answer.charAt(i);
                        cmpCnt++;
                    }
                }

                if(answer.length() == thinkWord.length() && thinkWord.length() == cmpCnt) {
                    System.out.println("Поздравляем!\nВы угадали с " + attempts + getText(attempts) + "!");
                    break;
                }

                System.out.println(arrResult);
                System.out.println(((cmpCnt > 0)? "Вы на верном пути!" : "Даже не близко...") + "\nПопробуйте еще.\n");
            }

            System.out.println("\nЕще разок?\nНаберите \"да\" или что угодно для выхода.");
            if(!scanner.nextLine().toLowerCase().equals("да")) {
                break;
            }
            System.out.println();
        }
    }
}
