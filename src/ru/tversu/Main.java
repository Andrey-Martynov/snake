package ru.tversu;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import ru.tversu.game.PlayingField;
import ru.tversu.game.Snake;

/**
 * @author Martynov Andrey
 * 20.01.2020
 */
public class Main extends Application {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 500;
    private GraphicsContext gc;
    private DirectionOfMovement direction = DirectionOfMovement.RIGHT;
    private final Snake snake = new Snake();
    private final PlayingField playingField = PlayingField.getInstance();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        scene.addEventHandler(KeyEvent.KEY_PRESSED, this::moveSnake);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), event -> game()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        primaryStage.show();
    }

    private void game() {
        if (snake.isLive() && playingField.hasFreeCells()) {
            playingField.createFood();
            moveSnake();
            drawPlayingField();
        }
    }

    void drawPlayingField() {
        gc.clearRect(0, 0, WIDTH, HEIGHT);
        for (int x = 0; x < playingField.getWIDTH(); x++) {
            for (int y = 0; y < playingField.getHEIGHT(); y++) {
                switch (playingField.get(x, y)) {
                    case FOOD:
                        drawFood(x, y);
                        break;
                    case SNAKE:
                        drawSnakeBody(x, y);
                        break;
                }
            }
        }
    }

    private void drawSnakeBody(int x, int y) {
        gc.setFill(Color.RED);
        gc.fillRect(x * 8, y * 8, 8, 8);
    }

    private void drawFood(int x, int y) {
        gc.setFill(Color.BLACK);
        gc.fillOval(x * 8, y * 8, 8, 8);
    }

    private void moveSnake() {
        switch (direction) {
            case LEFT:
                snake.moveLeft();
                break;
            case RIGHT:
                snake.moveRight();
                break;
            case UP:
                snake.moveUp();
                break;
            case DOWN:
                snake.moveDown();
                break;
        }
    }

    private void moveSnake(KeyEvent key) {
        switch (key.getCode()) {
            case LEFT:
                snake.moveLeft();
                direction = DirectionOfMovement.LEFT;
                break;
            case RIGHT:
                snake.moveRight();
                direction = DirectionOfMovement.RIGHT;
                break;
            case UP:
                snake.moveUp();
                direction = DirectionOfMovement.UP;
                break;
            case DOWN:
                snake.moveDown();
                direction = DirectionOfMovement.DOWN;
                break;
        }
    }

    private enum DirectionOfMovement {
        LEFT, RIGHT, UP, DOWN
    }
}
