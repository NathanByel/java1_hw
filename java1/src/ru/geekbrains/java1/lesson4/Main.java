package ru.geekbrains.java1.lesson4;

import java.util.Random;
import java.util.Scanner;

public class Main {
    // Настройки игры
    private static final int MAP_SIZE         = 8;  // Размер карты
    private static final int WIN_LEN          = 4;  // Элементов в ряд для выигрыша
    private static final int BOMBS            = 10; // Количество ячеек в % с бомбами от общего размера карты
    private static final int EXTRA_TURNS      = 10; // Количество ячеек в % с дополнительными ходами от общего размера карты

    // *****************************************************************************************************************
    private static final char X_ITEM          = 'X';
    private static final char O_ITEM          = 'O';
    private static final char EXTRA_TURN_ITEM = 'T';
    private static final char BOMB_ITEM       = 'B';
    private static final char EMPTY_ITEM      = '*';
    //private static final char WARN_ITEM       = 'W'; // Для отладки

    private static char gameMap[][];
    //private static char warnMap[][];    // Для отладки
    private static Random rand = new Random();
    private static Scanner scanner = new Scanner(System.in);

    // Создаём карту и очищаем её, а также размещаем в ячейках бонусы в виде бомб и дополнительных ходов.
    // Количество бомб и дополнительных ходов указывается в % от общего количества ячеек игрового поля.
    private static boolean makeMap(int size, int winLineLen, int bombs, int extraTurns) {
        if (size < 3) {
            System.out.println("Размер карты не может быть меньше чем 3x3...");
            return false;
        } else if (size > 30) {
            System.out.println("Размер карты не может быть больше чем 30x30...");
            return false;
        } else if (bombs > 50 || extraTurns > 50) {
            System.out.println("Количество ловушек или призов не может быть больше 50%...");
            return false;
        } else if (winLineLen < 3) {
            System.out.println("Количество элементов в ряд для выигрыша не может быть меньше 3х");
            return false;
        } else if (winLineLen > size) {
            System.out.println("Количество элементов в ряд для выигрыша не может быть больше размера карты");
            return false;
        }

        gameMap = new char[size][size];
        //warnMap = new char[size][size];
        int numExtraTurns = (int) ((float) (gameMap.length * gameMap.length) / 100F * (float) extraTurns);
        int numBombs = (int) ((float) (gameMap.length * gameMap.length) / 100F * (float) bombs);
        System.out.println("Размер карты        " + size + "x" + size);
        System.out.println("Нужно собрать линию из " + winLineLen + " элементов для выигрыша.");
        System.out.println();
        System.out.println("Ячеек с бонусами:");
        System.out.println("Дополнительные ходы " + numExtraTurns);
        System.out.println("Бомбы               " + numBombs);

        // Заполняем пустотой в виде "*"
        for (int y = 0; y < gameMap.length; y++) {
            for (int x = 0; x < gameMap.length; x++) {
                gameMap[x][y] = EMPTY_ITEM;
                //warnMap[x][y] = EMPTY_ITEM;
            }
        }

        // Расставляем бонусы
        while (true) {
            int sI = rand.nextInt(gameMap.length);
            int sJ = rand.nextInt(gameMap.length);
            if (gameMap[sI][sJ] == EMPTY_ITEM) {
                if (numExtraTurns > 0) {
                    numExtraTurns--;
                    gameMap[sI][sJ] = EXTRA_TURN_ITEM;
                } else if (numBombs > 0) {
                    numBombs--;
                    gameMap[sI][sJ] = BOMB_ITEM;
                } else {
                    break;
                }
            }
        }
        return true;
    }

    // Вывод карты.
    // Если openAll - false, то бонусы будут скрыты и будут показываться как пустые ячейки.
    private static void printMap(char map[][], boolean openAll) {
        final String spacer = map.length < 9 ? " " : "  ";
        System.out.print("X " + spacer);
        for (int i = 1; i <= map.length; i++) {
            System.out.print(i + (i < 10 ? spacer : " "));
        }
        System.out.println();
        System.out.println("Y");

        for (int y = 0; y < map.length; y++) {
            System.out.print(y + 1 + "  " + (map.length > 9 && y < 9 ? " " : ""));
            for (int x = 0; x < map[y].length; x++) {
                char item = map[x][y];
                // Скрываем бонусы от игрока
                if (item != X_ITEM && item != O_ITEM && !openAll) {
                    item = EMPTY_ITEM;
                }
                System.out.print(item + spacer);
            }
            System.out.println();
        }
    }

    // Проверка строк на выигрышные комбинации
    private static int checkRows(int winLen, char item) {
        int winRows = 0;
        for (int y = 0; y < gameMap.length; y++) {
            int itemCnt = 0;
            for (int x = 0; x < gameMap.length; x++) {
                if (gameMap[x][y] == item) {
                    itemCnt++;
                    if (itemCnt >= winLen) {
                        winRows++;
                        itemCnt = 0;
                    }
                } else {
                    itemCnt = 0;
                }
            }
        }
        return winRows;
    }

    // Проверка столбцов на выигрышные комбинации
    private static int checkColumns(int winLen, char item) {
        int winColumns = 0;
        for (int x = 0; x < gameMap.length; x++) {
            int itemCnt = 0;
            for (int y = 0; y < gameMap.length; y++) {
                if (gameMap[x][y] == item) {
                    itemCnt++;
                    if (itemCnt >= winLen) {
                        winColumns++;
                        itemCnt = 0;
                    }
                } else {
                    itemCnt = 0;
                }
            }
        }
        return winColumns;
    }

    // Проверка диагоналей на выигрышные комбинации
    private static int checkDiagonals(int winLen, char item) {
        int winDiagonals = 0;
        int itemCnt;
        int itemCntR;
        int len = gameMap.length - 1;

        int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
        do {
            //System.out.println("test " + "x1=" + x1 + ", y1=" + y1 + ", x2=" + x2 + ", y2=" + y2);
            // Сканируем ячейки
            int x11 = x2;
            int y22 = y2;
            itemCnt = 0;
            itemCntR = 0;
            while (x11 <= y2) {
                // Диагонали /
                if (gameMap[x11][y22] == item) {
                    itemCnt++;
                    //System.out.println("x11: " + x11 + ", y22: " + y22 + " cnt: " + itemCnt);
                } else {
                    itemCnt = 0;
                }

                // Диагонали \
                if (gameMap[gameMap.length - 1 - x11][y22] == item) {
                    itemCntR++;
                } else {
                    itemCntR = 0;
                }

                //System.out.println("x11=" + x11 + ", y22=" + y22 + ", xx11=" + xx11 + ", yy22=" + yy22);
                if (itemCnt >= winLen) {
                    //System.out.println("diagonal");
                    winDiagonals++;
                    itemCnt = 0;
                }

                if (itemCntR >= winLen) {
                    //System.out.println("diagonal R");
                    winDiagonals++;
                    itemCntR = 0;
                }
                x11++;
                y22--;
            }

            // Следущая диагональ
            if (x1 < len && y2 < len) {
                x1++;
                y2++;
            } else {
                x2++;
                y1++;
            }
        } while (y1 <= len || x2 <= len);

        return winDiagonals;
    }

    // Проверка всей карты на количество выигрышных комбинаций
    private static int checkWin(int winLen, char item) {
        return  checkRows(winLen, item) +
                checkColumns(winLen, item) +
                checkDiagonals(winLen, item);
    }

    // Поиск возможных выигрышных комбинаций
    // Возвращает true - если найдена хоть одна.
    // В coord возвращаются координаты первой ячейки куда надо поставить символ для выигрыша.
    private static boolean checkPossibleWin(int coord[], int winLen, char item) {
        int oldWinCnt = 0;
        int newWinCnt = 0;
        for (int y = 0; y < gameMap.length; y++) {
            for (int x = 0; x < gameMap.length; x++) {
                if ((gameMap[x][y] == EMPTY_ITEM) || (gameMap[x][y] == EXTRA_TURN_ITEM)) {
                    oldWinCnt = checkWin(winLen, item);

                    char oldItem = gameMap[x][y];
                    gameMap[x][y] = item;
                    newWinCnt = checkWin(winLen, item);
                    gameMap[x][y] = oldItem;
                    if(newWinCnt > oldWinCnt) {
                        //warnMap[x][y] = WARN_ITEM;
                        coord[0] = x;
                        coord[1] = y;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // Возвращает false если конец игры
    private static boolean playerTurn(int winLen) {
        int x, y;
        while(true) {
            System.out.print("\nВаш ход(X пробел Y): ");
            String answer[] = scanner.nextLine().split(" ");
            if (answer.length != 2) {
                if (answer[0].toLowerCase().equals("end")) {
                    System.out.println("END");
                    return false;
                }
                System.out.println("Введите 2 числа через пробел, или end для выхода");
                continue;
            }

            try {
                x = Integer.parseInt(answer[0]);
                y = Integer.parseInt(answer[1]);
            } catch (Exception e) {
                System.out.println("Введены не числа, введите заново");
                continue;
            }

            if (x > gameMap.length || y > gameMap.length || x < 1 || y < 1) {
                System.out.println("Введите 2 числа от 1 до " + gameMap.length);
                continue;
            }
            x--;
            y--;

            switch(gameMap[x][y]) {
                case EXTRA_TURN_ITEM:
                    gameMap[x][y] = X_ITEM;
                    if( checkWin(winLen, X_ITEM) > 0 ) {
                        System.out.println("Поздравляем! Вы выиграли!");
                        return false;
                    }
                    printMap(gameMap,false);
                    System.out.println("Поздравляем! Вы получаете бонус, дополнительный ход!");
                    break;

                case EMPTY_ITEM:
                    gameMap[x][y] = X_ITEM;
                    if( checkWin(winLen, X_ITEM) > 0) {
                        System.out.println("Поздравляем! Вы выиграли!");
                        return false;
                    }
                    printMap(gameMap,false);
                    return true;

                case BOMB_ITEM:
                    System.out.println("Ой беда какая... Вы подорвались на бомбе...");
                    System.out.println("Компьютер выиграл!");
                    return false;

                default:
                    printMap(gameMap, false);
                    System.out.println("Клетка уже занята, введите другие числа.");
            }
        }
    }

    // Возвращает false если конец игры
    private static boolean aiTurn(int winLen) {
        int[] coord = new int[2];

        while(true) {
            int x = -1;
            int y = -1;

            if( checkPossibleWin(coord, winLen, O_ITEM) ) {        // Определяем, можем-ли мы выиграть
                x = coord[0];
                y = coord[1];
                //System.out.println("AI WIN");
            } else if( checkPossibleWin(coord, winLen, X_ITEM) ) { // Пытаемся блокировать ходы игрока
                x = coord[0];
                y = coord[1];
                //System.out.println("PLAYER BLOCK");
            } else {                                               // Ищем выигрышные комбинации для AI
                for(int i = winLen; i >= 2; i--) {
                    if( checkPossibleWin(coord, i, O_ITEM) ) {
                        x = coord[0];
                        y = coord[1];
                        //System.out.println("AI COMB: " + i);
                        break;
                    }
                }
            }

            // Если ничего лучше не подобрали - ставим наугад
            if( (x == -1) || (y == -1) ) {
                x = rand.nextInt(gameMap.length);
                y = rand.nextInt(gameMap.length);
                //System.out.println("AI RANDOM");
            }

            System.out.print("\nХод компьютера(X Y): ");
            System.out.println((x+1) + " " + (y+1));

            switch(gameMap[x][y]) {
                case EXTRA_TURN_ITEM:
                    gameMap[x][y] = O_ITEM;
                    if( checkWin(winLen, O_ITEM) > 0) {
                        System.out.println("Компьютер выиграл!");
                        return false;
                    }
                    printMap(gameMap,false);
                    System.out.println("Компьютер получает бонус, дополнительный ход!");
                    break;

                case EMPTY_ITEM:
                    gameMap[x][y] = O_ITEM;
                    if( checkWin(winLen, O_ITEM) > 0) {
                        System.out.println("Компьютер выиграл!");
                        return false;
                    }
                    printMap(gameMap,false);
                    return true;

                case BOMB_ITEM:
                    System.out.println("Компьютер подорвался на бомбе...");
                    System.out.println("Вы выиграли!");
                    return false;

                default:
                    printMap(gameMap,false);
                    System.out.println("Клетка уже занята...");
            }
        }
    }

    // *****************************************************************************************************************
    public static void main(String[] args) {
        System.out.println("***************************** Extra XO v1.0 ******************************");
        System.out.println("Настройки игры задаются в начале класса Main.");
        System.out.println();
        if( !makeMap(MAP_SIZE, WIN_LEN, BOMBS, EXTRA_TURNS) ) {
            System.out.println("Что-то пошло не так...");
            return;
        }
        System.out.println("**************************************************************************");
        System.out.println();

        printMap(gameMap,false);
        while(true) {
            if( !playerTurn(WIN_LEN) ) {
                break;
            }

            if( !aiTurn(WIN_LEN) ) {
                break;
            }
        }
        System.out.println("\nОткрываем всю карту:");
        printMap(gameMap,true);
    }
}

