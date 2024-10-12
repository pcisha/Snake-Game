package com.snakes.www;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/*
@author prachi.shah
@date 10-11-2024
 */
public class SnakeGameController extends JFrame implements ActionListener {
    private final SnakeGameModel model;
    private final SnakeGameView view;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SnakeGameController game = new SnakeGameController();
            game.setVisible(true);
        });
    }

    public SnakeGameController() {
        model = new SnakeGameModel();
        view = new SnakeGameView(model);

        add(view);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Snake Game");

        Timer timer = new Timer(150, this); // Timer to move the snake periodically
        timer.start();

        addKeyListener(new KeyAdapter() {   // Key listener to control snake direction
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        model.setDirection('U');
                        break;
                    case KeyEvent.VK_DOWN:
                        model.setDirection('D');
                        break;
                    case KeyEvent.VK_LEFT:
                        model.setDirection('L');
                        break;
                    case KeyEvent.VK_RIGHT:
                        model.setDirection('R');
                        break;
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (model.isGameRunning()) {
            model.move();
        }
        view.repaint();
    }
}
