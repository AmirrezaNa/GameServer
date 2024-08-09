package controller.gameController.collisions.bossFight;

import controller.gameController.GameController;
import controller.gameController.Impact;
import model.entity.BallModel;

public class FrameCollisionBossFight {


    public static void checkBossFightFrameCollisions(GameController gameController) {
        checkBulletCollisions(gameController);
        checkBallCollisions(gameController);
    }


    public static void checkBulletCollisions(GameController gameController) {
        // bullets collisions
        checkBulletHitFrame(gameController);

    }

    public static void checkBallCollisions(GameController gameController) {
        //ball collisions
        checkBallCollisionToFrame(gameController);
    }



    // =================================================================================


    public static void checkBulletHitFrame(GameController gameController) {
        if (!gameController.bullets.isEmpty()) {
            int size;
            if (gameController.bullets.size() > 15) {
                size = 15;
            } else {
                size = gameController.bullets.size();
            }
            for (int i = 0; i < size; i++) {
                if (gameController.bullets.get(i).bulletHealth > 0) {

                    if (!gameController.smiley.squeezeAttack) {
                        if (gameController.bullets.get(i).x > (gameController.epsilonFrame.epsilonFrame.x + gameController.epsilonFrame.epsilonFrame.width) &&
                                gameController.bullets.get(i).y > (gameController.epsilonFrame.epsilonFrame.y) && gameController.bullets.get(i).y < (gameController.epsilonFrame.epsilonFrame.y + gameController.epsilonFrame.epsilonFrame.height)) {
                            gameController.bullets.get(i).dx = 0;
                            gameController.bullets.get(i).dy = 0;
                            gameController.bullets.get(i).bulletHealth = 0;
                            gameController.epsilonFrame.epsilonFrame.width += 30;
                            gameController.epsilonFrame.epsilonFrame.x += 3;
                        }

                        else if (gameController.bullets.get(i).x < gameController.epsilonFrame.epsilonFrame.x &&
                                gameController.bullets.get(i).y > (gameController.epsilonFrame.epsilonFrame.y) && gameController.bullets.get(i).y < (gameController.epsilonFrame.epsilonFrame.y + gameController.epsilonFrame.epsilonFrame.height)) {
                            gameController.bullets.get(i).dx = 0;
                            gameController.bullets.get(i).dy = 0;
                            gameController.bullets.get(i).bulletHealth = 0;
                            gameController.epsilonFrame.epsilonFrame.x -= 40;
                            gameController.epsilonFrame.epsilonFrame.width += 30;

                        }

                    }

                }
            }
        }
    }


    public static void checkBallCollisionToFrame(GameController gameController) {
        if ((gameController.ball.x + BallModel.ballRadius) >= (gameController.epsilonFrame.epsilonFrame.x + gameController.epsilonFrame.epsilonFrame.width)) {
            Impact.turnOnImpact(gameController.ball.x + BallModel.ballRadius,
                    gameController.ball.y,
                    gameController.ball.x + BallModel.ballRadius,
                    gameController.ball.y, gameController);
        }
        if (gameController.ball.x - BallModel.ballRadius <= gameController.epsilonFrame.epsilonFrame.x) {
            Impact.turnOnImpact(gameController.ball.x - BallModel.ballRadius,
                    gameController.ball.y,
                    gameController.ball.x - BallModel.ballRadius,
                    gameController.ball.y, gameController);
        }
        if ((gameController.ball.y + BallModel.ballRadius) >= (gameController.epsilonFrame.epsilonFrame.y + gameController.epsilonFrame.epsilonFrame.height)) {
            Impact.turnOnImpact(gameController.ball.x,
                    gameController.ball.y + BallModel.ballRadius,
                    gameController.ball.x,
                    gameController.ball.y + BallModel.ballRadius, gameController);
        }
        if (gameController.ball.y - BallModel.ballRadius <= gameController.epsilonFrame.epsilonFrame.y) {
            Impact.turnOnImpact(gameController.ball.x,
                    gameController.ball.y - BallModel.ballRadius,
                    gameController.ball.x,
                    gameController.ball.y - BallModel.ballRadius, gameController);
        }
    }

}

