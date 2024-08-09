package controller.gameController.objectController;

import controller.gameController.GameController;
import controller.gameController.listener.MouseInputListener;
import model.entity.BallModel;

public class BallAngleController {


    public static void updateBallAngle(GameController gameController) {
        if (gameController.ballAngle.angleExists) {

            double x1 = gameController.ball.x;
            double y1 = gameController.ball.y;
            double x2 = 0;
            double y2 = 0;
            double deltaX = x2 - x1;
            double deltaY = y2 - y1;

            // Calculate the angle in radians
            gameController.ballAngle.angle = Math.atan2(deltaY, deltaX);
            gameController.ballAngle.x = gameController.ball.x + (BallModel.ballRadius * Math.cos(gameController.ballAngle.angle));
            gameController.ballAngle.y = gameController.ball.y + (BallModel.ballRadius * Math.sin(gameController.ballAngle.angle));
        }
    }
}
