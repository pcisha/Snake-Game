package com.snakes.www;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/*
@author prachi.shah
@date 10-11-2024
 */
public class SnakeGameView extends JPanel {
    private final int GRID_SIZE = 20;
    private final SnakeGameModel model;

    public SnakeGameView(SnakeGameModel model) {
        this.model = model;
        setPreferredSize(new Dimension(GRID_SIZE * 30, GRID_SIZE * 30));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) { // Render the game components
        super.paintComponent(g);

        if (model.isGameRunning()) {
            drawFood(g, model.getFood());
            drawSnake(g, model.getSnake());
        } else {
            showGameOver(g);
        }
    }

    private void drawFood(Graphics g, Point food) {
        g.setColor(Color.ORANGE);
        g.fillRect(food.x * GRID_SIZE, food.y * GRID_SIZE, GRID_SIZE, GRID_SIZE);
    }

    private void drawSnake(Graphics g, ArrayList<Point> snake) {
        g.setColor(Color.CYAN);
        for (Point point : snake) {
            g.fillRect(point.x * GRID_SIZE, point.y * GRID_SIZE, GRID_SIZE, GRID_SIZE);
        }
    }

    private void showGameOver(Graphics g) {
        String message = "Game Over!";
        g.setColor(Color.RED);
        g.setFont(new Font("Calibri", Font.BOLD, 35));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString(message, (getWidth() - metrics.stringWidth(message)) / 2, getHeight() / 2);
    }
}
