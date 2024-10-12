package com.snakes.www;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/*
@author prachi.shah
@date 10-11-2024
 */
public class SnakeGameModel {
    private final int GRID_WIDTH = 30;
    private final int GRID_HEIGHT = 30;
    private ArrayList<Point> snake;
    private Point food;
    private char direction = 'R';
    private boolean gameRunning;

    public SnakeGameModel() {
        initializeGame();     // Initialize the game state
    }

    // Getters for the game view
    public ArrayList<Point> getSnake() {
        return snake;
    }

    public Point getFood() {
        return food;
    }

    public boolean isGameRunning() {
        return gameRunning;
    }

    public void initializeGame() {
        snake = new ArrayList<>();
        snake.add(new Point(5, 5));
        spawnFood();
        direction = 'R';
        gameRunning = true;
    }

    // Spawn food at a random location that does not overlap with the snake
    private void spawnFood() {
        Random rand = new Random();
        do {
            food = new Point(rand.nextInt(GRID_WIDTH), rand.nextInt(GRID_HEIGHT));
        } while (snake.contains(food));
    }

    // Move the snake and check if it eats food or collides
    public void move() {
        if (!gameRunning) return;

        Point head = new Point(snake.get(0));
        switch (direction) {
            case 'U':
                head.y -= 1;
                break;
            case 'D':
                head.y += 1;
                break;
            case 'L':
                head.x -= 1;
                break;
            case 'R':
                head.x += 1;
                break;
        }

        // Check food collision
        if (head.equals(food)) {
            snake.add(0, head);
            spawnFood();
        } else {
            snake.add(0, head);
            snake.remove(snake.size() - 1);
        }

        checkCollision();
    }

    // Check if the snake has collided with itself or the wall
    private void checkCollision() {
        Point head = snake.get(0);

        // Wall collision
        if (head.x < 0 || head.x >= GRID_WIDTH || head.y < 0 || head.y >= GRID_HEIGHT) {
            gameRunning = false;
        }

        // Self-collision
        for (int i = 1; i < snake.size(); i++) {
            if (head.equals(snake.get(i))) {
                gameRunning = false;
                break;
            }
        }
    }

    // Update the direction of the snake
    public void setDirection(char newDirection) {
        if ((direction == 'U' && newDirection != 'D') || (direction == 'D' && newDirection != 'U') || (direction == 'L' && newDirection != 'R') || (direction == 'R' && newDirection != 'L')) {
            direction = newDirection;
        }
    }
}
