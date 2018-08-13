package ru.geekbrains.java1.lesson8;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {
    private Image img;
    private int width, height, x, y;
    private boolean flipX = false;
    private boolean border = false;


    public ImagePanel(Image img, int x, int y) {
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setOpaque(false);
        setLayout(null);

        this.x = x;
        this.y = y;
        width = img.getWidth(null);
        height = img.getHeight(null);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(flipX) {
            g.drawImage(img, x, y, x+width, y+height, img.getWidth(null),
                    0, 0, img.getHeight(null), null);
        } else {
            g.drawImage(img, x, y, width, height, this);
        }

        if (border) {
            g.setColor(Color.BLUE);
            g.drawRect(x, y, width, height);
        }
    }

    public void setImage(Image img) {
        this.img = img;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setFlipX(boolean flipX) {
        this.flipX = flipX;
    }

    public void setBorder(boolean border) {
        this.border = border;
    }
}
