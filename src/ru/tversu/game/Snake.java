package ru.tversu.game;

import java.util.*;

/**
 * @author Martynov Andrey
 * 20.01.2020
 */
public class Snake {
    private final ArrayList<Coordinate> body = new ArrayList<>();
    private final PlayingField playingField = PlayingField.getInstance();
    private boolean isSnake;

    public Snake() {
        body.add(new Coordinate(1, 1));
        playingField.setSnake(body.get(0));
    }

    public void moveUp() {
        move(body.get(0).getUp());
    }

    public void moveDown() {
        move(body.get(0).getDown());
    }

    public void moveLeft() {
        move(body.get(0).getLeft());
    }

    public void moveRight() {
        move(body.get(0).getRight());
    }

    private void move(Coordinate head) {
        isSnake = playingField.isSnake(head);
        if (playingField.isFood(head)) {
            increaseInSize();
        } else {
            playingField.setEmpty(body.get(body.size() - 1));
        }
        shift();
        body.set(0, head);
        playingField.setSnake(head);
    }

    private void increaseInSize() {
        body.add(null);
    }

    private void shift() {
        Coordinate tmp = body.get(0);
        for (int i = 1; i < body.size(); i++) {
            Coordinate t = body.get(i);
            body.set(i, tmp);
            tmp = t;
        }
    }

    public boolean isLive() {
        return !isSnake && !playingField.isBarrier(body.get(0));
    }
}