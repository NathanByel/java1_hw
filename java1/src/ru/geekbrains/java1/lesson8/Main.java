package ru.geekbrains.java1.lesson8;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random rand = new Random();
        WindowMain windowMain = new WindowMain();

        // Динамическая менюшка для выделения котов
        JMenu catsMenu = new  JMenu("Коты");
        Cat[] cats = new Cat[5];
        for (int i = 0; i < cats.length; i++) {
            cats[i] = new Cat(windowMain, rand.nextInt(400), rand.nextInt(400));
            catsMenu.add("Кот " + (i+1));
            int finalI = i;
            catsMenu.getItem(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cats[finalI].setSelected( !cats[finalI].isSelected() );
                }
            });
        }
        windowMain.getJMenuBar().add(catsMenu);
        windowMain.revalidate();

        long timer = 0;
        long oldTime = System.currentTimeMillis();
        while(true) {
            float dt = (System.currentTimeMillis() - oldTime) / 1000f;
            oldTime = System.currentTimeMillis();

            if(System.currentTimeMillis() > timer + 2000) {
                timer = System.currentTimeMillis();

                for (int i = 0; i < cats.length; i++) {
                    if (cats[i].getCurrentState() == Cat.CAT_STATE_IDLE) {
                        cats[i].goTo(rand.nextInt(400), rand.nextInt(400));
                    }
                }
            }

            for ( Cat cat: cats) {
                cat.render(dt);
            }
        }
    }
}
