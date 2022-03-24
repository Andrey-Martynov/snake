package ru.tversu.game;

import com.sun.javafx.UnmodifiableArrayList;

/**
 * @author Martynov Andrey
 * 20.01.2020
 */
public class Coordinate {
    private int x;
    private int y;

    public Coordinate() {
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Coordinate getUp() {
        return new Coordinate(x, y - 1);
    }

    public Coordinate getDown() {
        return new Coordinate(x, y + 1);

    }

    public Coordinate getLeft() {
        return new Coordinate(x - 1, y);
    }

    public Coordinate getRight() {
        return new Coordinate(x + 1, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coordinate that = (Coordinate) o;
        return x == that.x && y == that.y;
    }
}