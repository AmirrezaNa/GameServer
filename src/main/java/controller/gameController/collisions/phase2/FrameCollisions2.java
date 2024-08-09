package controller.gameController.collisions.phase2;

import controller.gameController.FrameOfObject;
import controller.gameController.GameController;
import controller.gameController.Impact;
import controller.gameController.objectController.BallController;
import controller.gameController.objectController.BulletController;
import controller.gameController.objectController.enemies.BarricadosController2;
import model.ClientModel;
import model.entity.BallModel;
import model.entity.BulletModel;

public class FrameCollisions2 {

    public static void checkFramesCollisions2(GameController gameController, ClientModel client) {
        FrameOfObject.getCollidedFrames(gameController);
        checkBulletHitFrames(gameController);
        checkBallCollisionToFrames2(gameController);
        checkEnemyBulletOut(gameController, client);
    }

    public static boolean frameCollided(int k, GameController gameController) {//this method checks if a frame has a collision with another frame
        int numOfFramesCollided = 0;
        for (int i = 0; i < gameController.createdFrames.length; i++) {
            int x = gameController.createdFrames[i].x;
            int y = gameController.createdFrames[i].y;
            int width = gameController.createdFrames[i].width;
            int height = gameController.createdFrames[i].height;
            if ((gameController.ball.x >= (x + BallModel.ballRadius)) && (gameController.ball.x <= (x + width - BallModel.ballRadius))
                    && (gameController.ball.y > (y + BallModel.ballRadius)) && (gameController.ball.y < (y + height - BallModel.ballRadius))) {
                numOfFramesCollided++;
            }
        }
        return numOfFramesCollided >= 2;
    }


    public static void checkBulletHitFrames(GameController gameController) {
        if (!gameController.bullets.isEmpty()) {
            for (int i = 0; i < gameController.bullets.size(); i++) {
                if (gameController.bullets.get(i).bulletHealth > 0) {
                    int k;
                    if (!gameController.collidedFrames.isEmpty()) {
                        k = gameController.bullets.get(i).bulletFrame;
                    } else {
                        k = FrameOfObject.getFrameOfBall(gameController);
                    }
                    if (k != -1) {

                        if (gameController.bullets.get(i).x > gameController.createdFrames[k].x + gameController.createdFrames[k].width) {
                            if (!BulletController.isBulletInAFrame(gameController.bullets.get(i), gameController)) {
                                gameController.bullets.get(i).dx = 0;
                                gameController.bullets.get(i).dy = 0;
                                gameController.bullets.get(i).bulletHealth = 0;
                                if (!BarricadosController2.isBarricados2InFrame(k, gameController)) {

                                    gameController.createdFrames[k].width += 20;
                                    gameController.createdFrames[k].x += 5;
                                }
                                Impact.turnOnImpact(gameController.bullets.get(i).x + ((double) BulletModel.bulletSize / 2),
                                        gameController.bullets.get(i).y + ((double) BulletModel.bulletSize / 2),
                                        gameController.bullets.get(i).x + ((double) BulletModel.bulletSize / 2),
                                        gameController.bullets.get(i).y + ((double) BulletModel.bulletSize / 2), gameController);
                            }

                        } else if (gameController.bullets.get(i).y > gameController.createdFrames[k].y + gameController.createdFrames[k].height) {
                            if (!BulletController.isBulletInAFrame(gameController.bullets.get(i), gameController)) {
                                gameController.bullets.get(i).dx = 0;
                                gameController.bullets.get(i).dy = 0;
                                gameController.bullets.get(i).bulletHealth = 0;
                                if (!BarricadosController2.isBarricados2InFrame(k, gameController)) {
                                    gameController.createdFrames[k].height += 20;
                                    gameController.createdFrames[k].y += 5;
                                }
                                Impact.turnOnImpact(gameController.bullets.get(i).x + ((double) BulletModel.bulletSize / 2),
                                        gameController.bullets.get(i).y + ((double) BulletModel.bulletSize / 2),
                                        gameController.bullets.get(i).x + ((double) BulletModel.bulletSize / 2),
                                        gameController.bullets.get(i).y + ((double) BulletModel.bulletSize / 2), gameController);
                            }

                        } else if (gameController.bullets.get(i).x < gameController.createdFrames[k].x) {
                            if (!BulletController.isBulletInAFrame(gameController.bullets.get(i), gameController)) {
                                gameController.bullets.get(i).dx = 0;
                                gameController.bullets.get(i).dy = 0;
                                gameController.bullets.get(i).bulletHealth = 0;
                                if (!BarricadosController2.isBarricados2InFrame(k, gameController)) {

                                    gameController.createdFrames[k].x -= 20;
                                    gameController.createdFrames[k].width += 10;
                                }
                                Impact.turnOnImpact(gameController.bullets.get(i).x + ((double) BulletModel.bulletSize / 2),
                                        gameController.bullets.get(i).y + ((double) BulletModel.bulletSize / 2),
                                        gameController.bullets.get(i).x + ((double) BulletModel.bulletSize / 2),
                                        gameController.bullets.get(i).y + ((double) BulletModel.bulletSize / 2), gameController);
                            }

                        } else if (gameController.bullets.get(i).y < gameController.createdFrames[k].y) {
                            if (!BulletController.isBulletInAFrame(gameController.bullets.get(i), gameController)) {
                                gameController.bullets.get(i).dx = 0;
                                gameController.bullets.get(i).dy = 0;
                                gameController.bullets.get(i).bulletHealth = 0;
                                if (!BarricadosController2.isBarricados2InFrame(k, gameController)) {

                                    gameController.createdFrames[k].y -= 20;
                                    gameController.createdFrames[k].height += 10;
                                }
                                Impact.turnOnImpact(gameController.bullets.get(i).x + ((double) BulletModel.bulletSize / 2),
                                        gameController.bullets.get(i).y + ((double) BulletModel.bulletSize / 2),
                                        gameController.bullets.get(i).x + ((double) BulletModel.bulletSize / 2),
                                        gameController.bullets.get(i).y + ((double) BulletModel.bulletSize / 2), gameController);
                            }

                        }
                    }

                }
            }
        }
    }


    public static void checkBallCollisionToFrames2(GameController gameController) {
        int k = FrameOfObject.getFrameOfBall(gameController);
        if (k != -1) {
            if (gameController.ball.x + BallModel.ballRadius >= gameController.createdFrames[k].x + gameController.createdFrames[k].width) {
                ballCanMoveOn(gameController);
                if (!gameController.ballBetweenFrames && !BallController.checkIfBallIsInFrame(gameController)) {
                    Impact.turnOnImpact(gameController.ball.x + BallModel.ballRadius,
                            gameController.ball.y,
                            gameController.ball.x + BallModel.ballRadius,
                            gameController.ball.y, gameController);
                }
            }
            if (gameController.ball.x - BallModel.ballRadius <= gameController.createdFrames[k].x) {
                ballCanMoveOn(gameController);
                if (!gameController.ballBetweenFrames && !BallController.checkIfBallIsInFrame(gameController)) {
                    Impact.turnOnImpact(gameController.ball.x - BallModel.ballRadius,
                            gameController.ball.y,
                            gameController.ball.x - BallModel.ballRadius,
                            gameController.ball.y, gameController);
                }
            }
            if (gameController.ball.y + BallModel.ballRadius >= gameController.createdFrames[k].y + gameController.createdFrames[k].height) {
                ballCanMoveOn(gameController);
                if (!gameController.ballBetweenFrames && !BallController.checkIfBallIsInFrame(gameController)) {
                    Impact.turnOnImpact(gameController.ball.x,
                            gameController.ball.y + BallModel.ballRadius,
                            gameController.ball.x,
                            gameController.ball.y + BallModel.ballRadius, gameController);
                }
            }
            if (gameController.ball.y - BallModel.ballRadius <= gameController.createdFrames[k].y) {
                ballCanMoveOn(gameController);
                if (!gameController.ballBetweenFrames && !BallController.checkIfBallIsInFrame(gameController)) {
                    Impact.turnOnImpact(gameController.ball.x,
                            gameController.ball.y - BallModel.ballRadius,
                            gameController.ball.x,
                            gameController.ball.y - BallModel.ballRadius, gameController);
                }
            }
        }
    }

    public static void ballCanMoveOn(GameController gameController) {
        int currentFrame = FrameOfObject.getFrameOfBall(gameController);

        for (int i = 0; i < gameController.createdFrames.length; i++) {
            if (i == currentFrame) {
                continue;
            }
            if (gameController.ball.x >= gameController.createdFrames[i].x && (gameController.ball.x <= (gameController.createdFrames[i].x + gameController.createdFrames[i].width))
                    && ((Math.abs(gameController.ball.y - (gameController.createdFrames[i].y)) <= BallModel.ballRadius)
                    || (Math.abs(gameController.ball.y - (gameController.createdFrames[i].y + gameController.createdFrames[i].height)) <= BallModel.ballRadius))) {

                gameController.ballBetweenFrames = true;
                break;

            } else if (gameController.ball.y >= gameController.createdFrames[i].y && (gameController.ball.y <= (gameController.createdFrames[i].y + gameController.createdFrames[i].height))
                    && ((Math.abs(gameController.ball.x - (gameController.createdFrames[i].x)) <= BallModel.ballRadius)
                    || (Math.abs(gameController.ball.x - (gameController.createdFrames[i].x + gameController.createdFrames[i].width)) <= BallModel.ballRadius))) {
                gameController.ballBetweenFrames = true;
                break;
            } else {
                gameController.ballBetweenFrames = false;
            }
        }
    }


    public static void checkEnemyBulletOut(GameController gameController, ClientModel client) {
        if (!gameController.enemyBullets.isEmpty()) {
            for (int i = 0; i < gameController.enemyBullets.size(); i++) {
                if (gameController.enemyBullets.get(i).x < 0 || gameController.enemyBullets.get(i).x > client.gameFrame2.width
                        || gameController.enemyBullets.get(i).y < 0 || gameController.enemyBullets.get(i).y > client.gameFrame2.height) {
                    gameController.enemyBullets.get(i).bulletHealth = 0;
                }
            }
        }
    }
}


