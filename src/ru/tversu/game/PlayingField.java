package ru.tversu.game;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Martynov Andrey
 * 20.01.2020
 */
public class PlayingField {
    private static final PlayingField INSTANCE = new PlayingField();

    private static final int WIDTH = 800 / 8;
    private static final int HEIGHT = 500 / 8;

    private final Cell[][] filed = new Cell[WIDTH][HEIGHT];
    private final ArrayList<Coordinate> freeCells = new ArrayList<>(WIDTH * HEIGHT);
    private final Random r = new Random();
    private boolean food = false;

    private PlayingField() {
        for (int x = 1; x < WIDTH - 1; x++) {
            for (int y = 1; y < HEIGHT - 1; y++) {
                freeCells.add(new Coordinate(x, y));
            }
        }
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++)
                filed[x][y] = Cell.EMPTY;
        }
    }

    public static PlayingField getInstance() {
        return INSTANCE;
    }

    public void createFood() {
        if (!food) {
            int i = Math.abs(r.nextInt()) % freeCells.size();
            set(freeCells.get(i), Cell.FOOD);
            freeCells.remove(i);
            food = true;
        }
    }

    public void setSnake(Coordinate coord) {
        if (get(coord) == Cell.FOOD) {
            food = false;
        }
        set(coord, Cell.SNAKE);
        freeCells.remove(coord);

    }

    public void setEmpty(Coordinate coord) {
        set(coord, Cell.EMPTY);
        freeCells.add(coord);
    }

    public boolean isSnake(Coordinate coord) {
        return get(coord) == Cell.SNAKE;
    }

    public boolean isFood(Coordinate coord) {
        return get(coord) == Cell.FOOD;
    }

    public boolean isBarrier(Coordinate coord) {
        return coord.getX() == 0 || coord.getX() == WIDTH - 1
                || coord.getY() == 0 || coord.getY() == HEIGHT - 1;
    }

    public boolean hasFreeCells() {
        return !freeCells.isEmpty();
    }

    public Cell get(Coordinate coord) {
        return get(coord.getX(), coord.getY());
    }

    public Cell get(int x, int y) {
        return filed[x][y];
    }

    private void set(Coordinate coord, Cell cell) {
        set(coord.getX(), coord.getY(), cell);
    }

    private void set(int x, int y, Cell cell) {
        filed[x][y] = cell;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }
}
