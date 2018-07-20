package ru.geekbrains.java1.lesson2;

import java.util.Arrays;
import java.util.Random;

public class Main {
    // Task 6
    public static boolean checkBalance(int[] arr) {
        int arrSum = Arrays.stream(arr).sum();
        if( (arrSum % 2) == 0 ) {
            int arrHalfSum = arrSum / 2;
            int sum = 0;
            for(int i = 0; i < arr.length-1; i++) {
                sum += arr[i];
                if(sum == arrHalfSum) {
                    String arrS = Arrays.toString(Arrays.copyOfRange(arr, 0, i+1)).replace("]", " || ") +
                            Arrays.toString(Arrays.copyOfRange(arr, i+1, arr.length)).replace("[", "");

                    System.out.println(arrS);
                    return true;
                }
            }
        }
        return false;
    }

    // Task 7
    public static void arrayShift(int[] arr, int n) {
        n %= arr.length;
        if((n == 0) || (n == arr.length)) {
            return;
        }

        int idx = 0;
        int pos = 0;
        int newPos;
        int tmp = arr[0];
        int tmp1;
        for(int cnt = 0; cnt < arr.length; cnt++) {
            newPos = pos + n;
            if(newPos < 0) {
                newPos += arr.length;
            }
            if(newPos >= arr.length) {
                newPos -= arr.length;
            }

            tmp1 = arr[newPos];
            arr[newPos] = tmp;
            tmp = tmp1;
            if(newPos == idx) {
                idx++;
                pos = idx;
                tmp = arr[pos];
            } else {
                pos = newPos;
            }
        }
    }

    // *****************************************************************************************************************

    public static void main(String[] args) {
        // 1. Задать целочисленный массив, состоящий из элементов 0 и 1.
        // Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ]. С помощью цикла и условия заменить 0 на 1, 1 на 0;
        {
            System.out.println("\n********************************* Task 1 ******************************************");
            int[] arr = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
            System.out.println("Array:   " + Arrays.toString(arr));
            for (int i = 0; i < arr.length; i++) {
                arr[i] = (arr[i] == 0) ? 1 : 0;
            }
            System.out.println("Reverse: " + Arrays.toString(arr));
        }

        // 2. Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;
        {
            System.out.println("\n********************************* Task 2 ******************************************");
            int[] arr = new int[8];
            for (int i = 0; i < 8; i++) {
                arr[i] = i * 3;
            }
            System.out.println("Array:   " + Arrays.toString(arr));
        }

        // 3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2;
        {
            System.out.println("\n********************************* Task 3 ******************************************");
            int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
            System.out.println("Array:    " + Arrays.toString(arr));
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] < 6) {
                    arr[i] *= 2;
                }
            }
            System.out.println("Array *2: " + Arrays.toString(arr));
        }

        // 4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое),
        // и с помощью цикла(-ов) заполнить его диагональные элементы единицами;
        {
            System.out.println("\n********************************* Task 4 ******************************************");
            int[][] arr = new int[5][5];
            for (int i = 0; i < arr.length; i++) {
                    arr[i][i] = 1;
            }

            for(int[] item: arr) {
                for(int item1: item) {
                    System.out.print(item1 + "  ");
                }
                System.out.println();
            }
        }

        // 5. ** Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета);
        {
            System.out.println("\n********************************* Task 5 ******************************************");
            Random rnd = new Random();
            int[] arr = new int[10];
            for(int i = 0; i < arr.length; i++) {
                arr[i] = rnd.nextInt(50);
            }

            int minIndex = 0;
            for(int i = 0; i < arr.length; i++) {
                if(arr[i] < arr[minIndex]) {
                    minIndex = i;
                }
            }

            int maxIndex = 0;
            for(int i = 0; i < arr.length; i++) {
                if(arr[i] > arr[maxIndex]) {
                    maxIndex = i;
                }
            }

            // Еще как вариант
            //int min = Arrays.stream(arr).min().getAsInt();
            //int max = Arrays.stream(arr).max().getAsInt();

            System.out.println("Array: " + Arrays.toString(arr));
            System.out.println("Min Value: " + arr[minIndex] + ". Min Index: " + minIndex + ".\n" +
                    "Max Value: " + arr[maxIndex] + ". Max Index: " + maxIndex + '.');
        }

        // 6. ** Написать метод, в который передается не пустой одномерный целочисленный массив,
        // метод должен вернуть true если в массиве есть место, в котором сумма левой и правой части массива равны.
        // Примеры: checkBalance([1, 1, 1, || 2, 1]) → true, checkBalance ([2, 1, 1, 2, 1]) → false,
        // checkBalance ([10, || 10]) → true, граница показана символами ||, эти символы в массив не входят.
        {
            System.out.println("\n********************************* Task 6 ******************************************");

            int[] arr = {1, 11, 3, 10, 5};
            System.out.println("Array 1: " + Arrays.toString(arr));
            System.out.println("Center " + (checkBalance(arr) ? "" : "not ") + "found");

            System.out.println();
            int[] arr2 = {1, 5, 3, 1, 5};
            System.out.println("Array 2: " + Arrays.toString(arr2));
            System.out.println("Center " + (checkBalance(arr2) ? "" : "not ") + "found");
        }


        // 7. **** Написать метод, которому на вход подается одномерный массив и число n
        // (может быть положительным, или отрицательным), при этом метод должен сместить все элементы массива
        // на n позиций. Для усложнения задачи нельзя пользоваться вспомогательными массивами.
        {
            System.out.println("\n********************************* Task 7 ******************************************");
            int arr[] = new int[5];
            for(int i = -10; i <= 10; i++) {
                Arrays.setAll(arr, (index) -> index);
                arrayShift(arr, i);
                System.out.println("Array 1, shift " + i + " " + Arrays.toString(arr));
            }

            System.out.println();
            int arr2[] = new int[20];
            for(int i = -10; i <= 10; i++) {
                Arrays.setAll(arr2, (index) -> index);
                arrayShift(arr2, i);
                System.out.println("Array 2, shift " + i + " " + Arrays.toString(arr2));
            }
        }
    }
}
