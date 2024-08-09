package controller.gameController.objectController;

import controller.gameController.GameController;
import controller.gameController.listener.MouseInputListener;

public class BallDirectionController {


    public static void updateBallDirection(GameController gameController) {

        double x1 = gameController.ball.x;
        double y1 = gameController.ball.y;
        double x2 = MouseInputListener.x;
        double y2 = MouseInputListener.y;
        double deltaX = x2 - x1;
        double deltaY = y2 - y1;

        // Calculate the angle in radians
        gameController.ballDirection.angle = Math.atan2(deltaY, deltaX);
        gameController.ballDirection.x = gameController.ball.x + (10 * Math.cos(gameController.ballDirection.angle));
        gameController.ballDirection.y = gameController.ball.y + (10 * Math.sin(gameController.ballDirection.angle));
    }

    public static void updateBallDirectionPanel2(GameController gameController) {

        double x1 = gameController.ball.x;
        double y1 = gameController.ball.y;
        double x2 = MouseInputListener.x;
        double y2 = MouseInputListener.y;
        double deltaX = x2 - x1;
        double deltaY = y2 - y1;

        // Calculate the angle in radians
        gameController.ballDirection.angle = Math.atan2(deltaY, deltaX);
        gameController.ballDirection.x = gameController.ball.x + (10 * Math.cos(gameController.ballDirection.angle));
        gameController.ballDirection.y = gameController.ball.y + (10 * Math.sin(gameController.ballDirection.angle));
    }

    public static void updateBallDirectionFinalBoss(GameController gameController) {

        double x1 = gameController.ball.x;
        double y1 = gameController.ball.y;
        double x2 = MouseInputListener.x;
        double y2 = MouseInputListener.y;
        double deltaX = x2 - x1;
        double deltaY = y2 - y1;

        // Calculate the angle in radians
        gameController.ballDirection.angle = Math.atan2(deltaY, deltaX);
        gameController.ballDirection.x = gameController.ball.x + (10 * Math.cos(gameController.ballDirection.angle));
        gameController.ballDirection.y = gameController.ball.y + (10 * Math.sin(gameController.ballDirection.angle));
    }
}

