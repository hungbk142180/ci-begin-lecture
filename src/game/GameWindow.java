package game;

import base.event.KeyEventPress;
import base.Settings;
//import tklibs.SpriteUtils;

import javax.swing.*;
//import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
//import java.awt.image.BufferedImage;

public class GameWindow extends JFrame {
    GameCanvas canvas;

    public GameWindow() {
        //setup
        this.setSize(Settings.SCREEN_WITDH, Settings.SCREEN_HEIGHT);
        this.setResizable(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setupEvenListenner();

        //init game
        this.canvas = new GameCanvas();
        this.add(canvas);
        this.setVisible(true);
    }

    private void setupEvenListenner() {
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_W) {
                    KeyEventPress.isUpPress = true;
                }
                if(e.getKeyCode() == KeyEvent.VK_S) {
                    KeyEventPress.isDownPress = true;
                }
                if(e.getKeyCode() == KeyEvent.VK_A) {
                    KeyEventPress.isLeftPress = true;
                }
                if(e.getKeyCode() == KeyEvent.VK_D) {
                    KeyEventPress.isRightPress = true;
                }
                if(e.getKeyCode() == KeyEvent.VK_SPACE) {
                    KeyEventPress.isFirePress = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_W) {
                    KeyEventPress.isUpPress = false;
                }
                if(e.getKeyCode() == KeyEvent.VK_S) {
                    KeyEventPress.isDownPress = false;
                }
                if(e.getKeyCode() == KeyEvent.VK_A) {
                    KeyEventPress.isLeftPress = false;
                }
                if(e.getKeyCode() == KeyEvent.VK_D) {
                    KeyEventPress.isRightPress = false;
                }
                if(e.getKeyCode() == KeyEvent.VK_SPACE) {
                    KeyEventPress.isFirePress = false;
                }
            }
        });
    }

    public void gameLoop() {
        long delay = 1000/60;
        long lastTime = 0;
        while (true) {
            long currentTime = System.currentTimeMillis();
            if(currentTime - lastTime > delay) {
                canvas.run(); //runAll()
//                this.repaint(); //renderAll()
                //JFrame.repaint()
                canvas.render(); //render all to backBuffer
                this.repaint(); //render backBuffer to game
                lastTime = currentTime;
            }
        }
    }
}
