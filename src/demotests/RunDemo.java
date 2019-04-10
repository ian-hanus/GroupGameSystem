package demotests;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class RunDemo {
    Stage gameStage;
    Scene gameScene;
    Group gameGroup;

    String level = "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0\n" +
         "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0\n" +
         "0,0,0,0,1,1,1,0,0,0,0,0,1,1,0,0,1,1,0,0\n" +
         "1,1,1,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,2\n" +
         "0,0,0,0,0,1,1,0,0,0,0,0,1,0,1,0,0,1,0,2\n" +
         "0,0,0,0,1,0,0,0,1,1,1,0,0,1,0,0,0,0,0,0\n" +
         "0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0\n" +
         "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0";

    double height = 500.0/8;
    double width = 75;

    Shape ball;

    List<Node> movables;
    List<Shape> blocks;
    List<Shape> winBlocks;

    private static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private static final int SPEED = 150;

    boolean movingLeft = false;
    boolean movingRight = false;
    boolean canJump = true;

    public RunDemo() {
         gameStage = new Stage();
         gameStage.setTitle("Bounce");
         gameStage.setWidth(500);
         gameStage.setHeight(500);
         gameStage.setResizable(false);
         gameGroup = new Group();
         gameScene = new Scene(gameGroup);
         gameStage.setScene(gameScene);
         movables = new ArrayList<>();
         blocks = new ArrayList<>();
         winBlocks = new ArrayList<>();

         makeAndPlaceBall();
         makeAndPlaceLevel();

         gameScene.setOnKeyPressed(e -> {
             switch (e.getCode()) {
                 case LEFT:
                     movingLeft = true;
                     break;
                 case RIGHT:
                     movingRight = true;
                     break;
                 case UP:
                     if (canJump) {
                         ballVelocity = -SPEED;
                     }
                     canJump = false;
                     break;
             }
         });

        gameScene.setOnKeyReleased(e -> {
            switch (e.getCode()) {
                case LEFT:
                    movingLeft = false;
                    break;
                case RIGHT:
                    movingRight = false;
                    break;
            }
        });

         var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
         var animation = new Timeline();
         animation.setCycleCount(Timeline.INDEFINITE);
         animation.getKeyFrames().add(frame);
         animation.play();

    }

    double ballVelocity = SPEED;

    private void step(double timeElapsed) {
        boolean onBlockRight = false;
        boolean onBlockLeft = false;
        boolean isOnBlock = false;

        for (Shape n : blocks) {
            double maxBallY = ((Circle)ball).getCenterY() + ((Circle)ball).getRadius();
            double minBallY = ((Circle)ball).getCenterY() - ((Circle)ball).getRadius();
            double minRectY = ((Rectangle) n).getY();
            double maxRectY = ((Rectangle) n).getY() + height;

            double maxBallX = ((Circle)ball).getCenterX() + ((Circle)ball).getRadius();
            double minBallX = ((Circle)ball).getCenterX() - ((Circle)ball).getRadius();
            double minRectX = ((Rectangle) n).getX();
            double maxRectX = ((Rectangle) n).getX() + width;

            if (minBallX <= maxRectX &&
                    maxBallX > maxRectX &&
                    maxBallY <= maxRectY &&
                    minBallY >= minRectY) {
                onBlockRight = true;
            }
            if ( maxBallX  >= minRectX &&
                    minBallX < minRectX &&
                    maxBallY <= maxRectY &&
                    minBallY >= minRectY) {
                onBlockLeft = true;
            }
            if ( maxBallY  >= minRectY &&
                    minBallY < minRectY &&
                    maxBallX >= minRectX &&
                    minBallX <= maxRectX ) {
                isOnBlock = true;
            }
        }

        if (movingLeft && !onBlockRight) {
            for (Node n : movables) {
                if (n instanceof  Rectangle) {
                    ((Rectangle)n).setX(((Rectangle)n).getX() + (SPEED * timeElapsed));
                } else {
                    n.setLayoutX(n.getLayoutX() + (SPEED * timeElapsed));
                }
            }
        }
        if (movingRight && !onBlockLeft) {
            for (Node n : movables) {
                if (n instanceof  Rectangle) {
                    ((Rectangle)n).setX(((Rectangle)n).getX() - (SPEED * timeElapsed));
                } else {
                    n.setLayoutX(n.getLayoutX() - (SPEED * timeElapsed));
                }
            }
        }

        if (ballVelocity < SPEED) {
            ballVelocity += 3;
        } else {
            ballVelocity = SPEED;
            canJump = true;
        }

        if (ballVelocity < 0) {
            ((Circle)ball).setCenterY(((Circle)ball).getCenterY() + ballVelocity * timeElapsed);
        } else {
            if (!isOnBlock) {
                ((Circle) ball).setCenterY(((Circle) ball).getCenterY() + ballVelocity * timeElapsed);
            } else {
                ballVelocity = SPEED;
            }
        }

        boolean won = false;
        for (Shape s : winBlocks) {
            if (ball.intersects(s.getLayoutBounds())) {
                won = true;
            }
        }
        if (won) {
            new Alert(Alert.AlertType.WARNING, "You won!", ButtonType.OK).show();

            gameGroup.getChildren().clear();
            blocks.clear();
            winBlocks.clear();
            movables.clear();
            makeAndPlaceBall();
            makeAndPlaceLevel();
        }

        if (((Circle) ball).getCenterY() + ((Circle) ball).getRadius() > 500) {
            new Alert(Alert.AlertType.WARNING, "Game Over", ButtonType.OK).show();

            gameGroup.getChildren().clear();
            blocks.clear();
            winBlocks.clear();
            movables.clear();
            makeAndPlaceBall();
            makeAndPlaceLevel();
            movingLeft = false; movingRight = false; ballVelocity = SPEED;
        }
    }

     private void makeAndPlaceLevel() {
         int r = 0, c = 0;
         for(String row : level.split("\n")) {
             for (String col : row.split(",")) {
                 if (col.equals("1")) {
                     makeAndPlaceBlock(r, c);
                 } else if (col.equals("2")) {
                     makeAndPlaceWinBlock(r, c);
                 }

                 c++;
             }
             c = 0;
             r++;
         }
     }

     private Rectangle makeBlock(int r, int c, Color color) {
         var block = new Rectangle(width, height);
         block.setFill(color);
         block.setX(250 + (c*width));
         block.setY(r*height);
         return block;
     }

     private void makeAndPlaceBlock(int r, int c) {
         var block = makeBlock(r, c, Color.SLATEGREY);
         movables.add(block); blocks.add(block);
         gameGroup.getChildren().add(block);
     }

    private void makeAndPlaceWinBlock(int r, int c) {
        var block = makeBlock(r, c, Color.RED);
        movables.add(block); winBlocks.add(block);
        gameGroup.getChildren().add(block);
    }

     private void makeAndPlaceBall() {
         ball = new Circle();
         ((Circle) ball).setRadius(10);
         ball.setFill(Color.RED);
         ((Circle) ball).setCenterX(250);
         ((Circle) ball).setCenterY(100);
         gameGroup.getChildren().add(ball);
     }

     public void run () {
         gameStage.show();
     }
}
