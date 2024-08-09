package controller.gameController.collisions.phase1;

import controller.gameController.GameController;
import controller.gameController.Impact;
import controller.gameLoop.phase1.GameFrame;
import model.entity.BallModel;
import model.entity.BulletModel;

public class FrameCollision {


    public static void checkFrameCollisions(GameController gameController, GameFrame gameFrame) {

        checkBulletHitFrame(gameController, gameFrame);
        checkBallCollisionToFrame(gameController, gameFrame);
        checkEnemy1CollisionToFrame(gameController, gameFrame);
        checkEnemy2CollisionToFrame(gameController, gameFrame);

    }


    public static void checkBulletHitFrame(GameController gameController, GameFrame gameFrame) {
        if (!gameController.bullets.isEmpty()) {
            for (int i = 0; i < gameController.bullets.size(); i++) {
                if (gameController.bullets.get(i).bulletHealth > 0) {

                    if (gameController.bullets.get(i).x > gameFrame.width) {
                        gameController.bullets.get(i).dx = 0;
                        gameController.bullets.get(i).dy = 0;
                        gameController.bullets.get(i).bulletHealth = 0;
                        if (!gameFrame.countDown) {
                            gameFrame.width += 20;
                            gameFrame.x += 5;
                            Impact.turnOnImpact(gameController.bullets.get(i).x + ((double) BulletModel.bulletSize / 2),
                                    gameController.bullets.get(i).y + ((double) BulletModel.bulletSize / 2),
                                    gameController.bullets.get(i).x + ((double) BulletModel.bulletSize / 2),
                                    gameController.bullets.get(i).y + ((double) BulletModel.bulletSize / 2), gameController);
                        }

                    } else if (gameController.bullets.get(i).y > gameFrame.height) {
                        gameController.bullets.get(i).dx = 0;
                        gameController.bullets.get(i).dy = 0;
                        gameController.bullets.get(i).bulletHealth = 0;
                        if (!gameFrame.countDown) {
                            gameFrame.height += 20;
                            gameFrame.y += 5;
                            Impact.turnOnImpact(gameController.bullets.get(i).x + ((double) BulletModel.bulletSize / 2),
                                    gameController.bullets.get(i).y + ((double) BulletModel.bulletSize / 2),
                                    gameController.bullets.get(i).x + ((double) BulletModel.bulletSize / 2),
                                    gameController.bullets.get(i).y + ((double) BulletModel.bulletSize / 2), gameController);
                        }

                    } else if (gameController.bullets.get(i).x < 0) {
                        gameController.bullets.get(i).dx = 0;
                        gameController.bullets.get(i).dy = 0;
                        gameController.bullets.get(i).bulletHealth = 0;
                        if (!gameFrame.countDown) {
                            gameFrame.x -= 20;
                            gameFrame.width += 10;
                            Impact.turnOnImpact(gameController.bullets.get(i).x + ((double) BulletModel.bulletSize / 2),
                                    gameController.bullets.get(i).y + ((double) BulletModel.bulletSize / 2),
                                    gameController.bullets.get(i).x + ((double) BulletModel.bulletSize / 2),
                                    gameController.bullets.get(i).y + ((double) BulletModel.bulletSize / 2), gameController);
                        }

                    } else if (gameController.bullets.get(i).y < 0) {
                        gameController.bullets.get(i).dx = 0;
                        gameController.bullets.get(i).dy = 0;
                        gameController.bullets.get(i).bulletHealth = 0;
                        if (!gameFrame.countDown) {
                            gameFrame.y -= 20;
                            gameFrame.height += 10;
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

    public static void checkBallCollisionToFrame(GameController gameController, GameFrame gameFrame) {
        if (gameController.ball.x + BallModel.ballRadius >= gameFrame.width) {
            Impact.turnOnImpact(gameController.ball.x + BallModel.ballRadius,
                    gameController.ball.y,
                    gameController.ball.x + BallModel.ballRadius,
                    gameController.ball.y, gameController);
        }
        if (gameController.ball.x - BallModel.ballRadius <= 0) {
            Impact.turnOnImpact(gameController.ball.x - BallModel.ballRadius,
                    gameController.ball.y,
                    gameController.ball.x - BallModel.ballRadius,
                    gameController.ball.y, gameController);
        }
        if (gameController.ball.y + BallModel.ballRadius >= gameFrame.height) {
            Impact.turnOnImpact(gameController.ball.x,
                    gameController.ball.y + BallModel.ballRadius,
                    gameController.ball.x,
                    gameController.ball.y + BallModel.ballRadius, gameController);
        }
        if (gameController.ball.y - BallModel.ballRadius <= 0) {
            Impact.turnOnImpact(gameController.ball.x,
                    gameController.ball.y - BallModel.ballRadius,
                    gameController.ball.x,
                    gameController.ball.y - BallModel.ballRadius, gameController);
        }
    }


    public static void checkEnemy1CollisionToFrame(GameController gameController, GameFrame gameFrame) {
        if (!gameController.enemies1.isEmpty()) {
            for (int i = 0; i < gameController.enemies1.size(); i++) {
                if (gameController.enemies1.get(i).enemyHealth > 0) {

                    double xMin1 = gameController.enemies1.get(i).xAngles[0];
                    double xMax1 = gameController.enemies1.get(i).xAngles[0];
                    double yMin1 = gameController.enemies1.get(i).yAngles[0];
                    double yMax1 = gameController.enemies1.get(i).yAngles[0];
                    for (int j = 0; j < 4; j++) {
                        if (gameController.enemies1.get(i).xAngles[j] < xMin1) {
                            xMin1 = gameController.enemies1.get(i).xAngles[j];
                        }
                        if (gameController.enemies1.get(i).xAngles[j] > xMax1) {
                            xMax1 = gameController.enemies1.get(i).xAngles[j];
                        }
                        if (gameController.enemies1.get(i).yAngles[j] < yMin1) {
                            yMin1 = gameController.enemies1.get(i).yAngles[j];
                        }
                        if (gameController.enemies1.get(i).yAngles[j] > yMax1) {
                            yMax1 = gameController.enemies1.get(i).yAngles[j];
                        }
                    }
                    if (xMax1 >= gameFrame.width) {
                        Impact.turnOnImpact(gameController.enemies1.get(i).x + gameController.enemies1.get(i).enemy1Size,
                                gameController.enemies1.get(i).y,
                                gameController.enemies1.get(i).x + gameController.enemies1.get(i).enemy1Size,
                                gameController.enemies1.get(i).y, gameController);
                    }
                    if (xMin1 <= 0) {
                        Impact.turnOnImpact(gameController.enemies1.get(i).x - gameController.enemies1.get(i).enemy1Size,
                                gameController.enemies1.get(i).y,
                                gameController.enemies1.get(i).x - gameController.enemies1.get(i).enemy1Size,
                                gameController.enemies1.get(i).y, gameController);
                    }
                    if (yMax1 >= gameFrame.height) {
                        Impact.turnOnImpact(gameController.enemies1.get(i).x,
                                gameController.enemies1.get(i).y + gameController.enemies1.get(i).enemy1Size,
                                gameController.enemies1.get(i).x,
                                gameController.enemies1.get(i).y + gameController.enemies1.get(i).enemy1Size, gameController);
                    }
                    if (yMin1 <= 0) {
                        Impact.turnOnImpact(gameController.enemies1.get(i).x,
                                gameController.enemies1.get(i).y - gameController.enemies1.get(i).enemy1Size,
                                gameController.enemies1.get(i).x,
                                gameController.enemies1.get(i).y - gameController.enemies1.get(i).enemy1Size, gameController);
                    }
                }
            }
        }
    }


    public static void checkEnemy2CollisionToFrame(GameController gameController, GameFrame gameFrame) {
        if (!gameController.enemies2.isEmpty()) {
            for (int k = 0; k < gameController.enemies2.size(); k++) {
                if (gameController.enemies2.get(k).enemyHealth > 0) {

                    double xMin2 = gameController.enemies2.get(k).xAngles[0];
                    double xMax2 = gameController.enemies2.get(k).xAngles[0];
                    double yMin2 = gameController.enemies2.get(k).yAngles[0];
                    double yMax2 = gameController.enemies2.get(k).yAngles[0];
                    for (int i = 0; i < 3; i++) {
                        if (gameController.enemies2.get(k).xAngles[i] < xMin2) {
                            xMin2 = gameController.enemies2.get(k).xAngles[i];
                        }
                        if (gameController.enemies2.get(k).xAngles[i] > xMax2) {
                            xMax2 = gameController.enemies2.get(k).xAngles[i];
                        }
                        if (gameController.enemies2.get(k).yAngles[i] < yMin2) {
                            yMin2 = gameController.enemies2.get(k).yAngles[i];
                        }
                        if (gameController.enemies2.get(k).yAngles[i] > yMax2) {
                            yMax2 = gameController.enemies2.get(k).yAngles[i];
                        }
                    }
                    if (xMax2 >= gameFrame.width) {
                        Impact.turnOnImpact(gameController.enemies2.get(k).x + gameController.enemies2.get(k).enemy2Size,
                                gameController.enemies2.get(k).y,
                                gameController.enemies2.get(k).x + gameController.enemies2.get(k).enemy2Size,
                                gameController.enemies2.get(k).y, gameController);
                    }
                    if (xMin2 <= 0) {
                        Impact.turnOnImpact(gameController.enemies2.get(k).x - gameController.enemies2.get(k).enemy2Size,
                                gameController.enemies2.get(k).y,
                                gameController.enemies2.get(k).x - gameController.enemies2.get(k).enemy2Size,
                                gameController.enemies2.get(k).y, gameController);
                    }
                    if (yMax2 >= gameFrame.height) {
                        Impact.turnOnImpact(gameController.enemies2.get(k).x,
                                gameController.enemies2.get(k).y + gameController.enemies2.get(k).enemy2Size,
                                gameController.enemies2.get(k).x,
                                gameController.enemies2.get(k).y + gameController.enemies2.get(k).enemy2Size, gameController);
                    }
                    if (yMin2 <= 0) {
                        Impact.turnOnImpact(gameController.enemies2.get(k).x,
                                gameController.enemies2.get(k).y - gameController.enemies2.get(k).enemy2Size,
                                gameController.enemies2.get(k).x,
                                gameController.enemies2.get(k).y - gameController.enemies2.get(k).enemy2Size, gameController);
                    }
                }
            }
        }
    }


}

