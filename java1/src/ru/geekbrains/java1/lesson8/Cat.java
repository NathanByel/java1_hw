package ru.geekbrains.java1.lesson8;

import javax.swing.*;
import java.awt.*;

public class Cat {
    public static final int CAT_STATE_IDLE = 0;
    public static final int CAT_STATE_WALK = 1;
    private static boolean imgLoaded = false;
    private static Image[] animIdle = new Image[10];
    private static Image[] animWalk = new Image[10];

    private JFrame frame;
    ImagePanel imagePanel;
    private Image currentFrame;
    private Image[] currentAnimation;
    private float sizeScale = 0.2f;
    private float stateTime = 0f;
    private float frameDuration = 0.1f;
    private float currentX,currentY, newX, newY;
    private int currentState = CAT_STATE_IDLE;
    private boolean frameFlip = false;
    private boolean selected = false;

    public Cat(JFrame frame, int x, int y) {
        this.frame = frame;
        currentX = newX = x;
        currentY = newY = y;

        // Загружаем ресурсы только один раз, при создании самого первого объекта.
        if(!imgLoaded) {
            imgLoaded = true;
            for (int i = 0; i < 10; i++) {
                animIdle[i] = new ImageIcon("assets/Idle (" + (i+1) + ").png").getImage();
            }

            for (int i = 0; i < 10; i++) {
                animWalk[i] = new ImageIcon("assets/Walk (" + (i+1) + ").png").getImage();
            }
        }

        imagePanel = new ImagePanel(animIdle[0], (int)currentX, (int)currentY);
        currentFrame = animIdle[0];
        currentAnimation = animIdle;
        imagePanel.setSize((int)(currentFrame.getWidth(null) * sizeScale),
                (int)(currentFrame.getHeight(null) * sizeScale));
        frame.add(imagePanel);
        frame.revalidate();
        //frame.repaint();
    }

    private void update(float dt) {
        switch (currentState) {
            case CAT_STATE_IDLE:
                currentAnimation = animIdle;
                break;

            case CAT_STATE_WALK:
                currentAnimation = animWalk;
                if((currentX != newX) || (currentY != newY)) {
                    float move = 70f * dt;

                    if (currentX < newX) {
                        currentX += move;

                        if(currentX > newX) {
                            currentX = newX;
                        }
                        frameFlip = false;
                    } else if(currentX > newX) {
                        currentX -= move;
                        if(currentX < newX) {
                            currentX = newX;
                        }
                        frameFlip = true;
                    }

                    if (currentY < newY) {
                        currentY += move;
                        if(currentY > newY) {
                            currentY = newY;
                        }
                    } else if(currentY > newY) {
                        currentY -= move;
                        if(currentY < newY) {
                            currentY = newY;
                        }
                    }
                } else {
                    currentState = CAT_STATE_IDLE;
                }
                break;
        }

        stateTime += dt;
        int frameNumber = (int)(stateTime / frameDuration);
        frameNumber = frameNumber % currentAnimation.length;
        currentFrame = currentAnimation[frameNumber];
    }

    public void render(float dt) {
        update(dt);
        imagePanel.setFlipX(frameFlip);
        imagePanel.setSize((int)(currentFrame.getWidth(null) * sizeScale),
                (int)(currentFrame.getHeight(null) * sizeScale));
        imagePanel.setPos((int)currentX, (int)currentY);
        imagePanel.setImage(currentFrame);
        frame.repaint();
    }

    public void goTo(int x, int y) {
        this.newX = x;
        this.newY = y;
        currentState = CAT_STATE_WALK;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        imagePanel.setBorder(selected);
    }

    public int getCurrentState() {
        return currentState;
    }
}
