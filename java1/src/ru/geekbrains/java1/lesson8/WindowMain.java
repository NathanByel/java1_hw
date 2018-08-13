package ru.geekbrains.java1.lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowMain extends JFrame {
    public WindowMain() {
        setTitle("Коты");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 600);
        //setUndecorated(true);
        getContentPane().setBackground(Color.BLACK);

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new  JMenu("Файл");
        JMenuItem exitItem = new JMenuItem("Выход");
        fileMenu.add(exitItem);

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        setVisible(true);
    }
}
