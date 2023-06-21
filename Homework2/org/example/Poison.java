package org.example;

import java.util.Random;

public class Poison extends Cell{
    private Random random;
    private Snake snake;
    private Food food;

    public Poison(Snake snake) {
            super(-1, -1, GameSnake.CELL_SIZE, GameSnake.POISON_COLOR);
            random = new Random();
            this.snake = snake;
        }
        public void appear(){
            int x, y;
            do{
                x = random.nextInt(GameSnake.CANVAS_WIDTH);
                y = random.nextInt(GameSnake.CANVAS_HEIGHT);
            } while(snake.isInSnake(x, y) || food.isFood(x, y));
            set(x, y);
        }

        public boolean isPoison(int x, int y) {
            return (getX() == x && getY() == y);
        }

        public void setFood(Food food) {
        this.food = food;
        }
    }
