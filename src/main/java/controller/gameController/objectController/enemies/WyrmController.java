package controller.gameController.objectController.enemies;

import controller.gameController.GameController;
import model.entity.enemy.AllEnemies;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class WyrmController {

    public static void setDirectionForWyrm(GameController gameController) {
        if (!gameController.wyrmEnemies.isEmpty()) {
            for (int i = 0; i < gameController.wyrmEnemies.size(); i++) {
                if (gameController.wyrmEnemies.get(i).enemyHealth > 0) {
                    gameController.wyrmEnemies.get(i).dx = -((gameController.wyrmEnemies.get(i).x - gameController.ball.x) / Math.sqrt(Math.pow((gameController.wyrmEnemies.get(i).x - gameController.ball.x), 2) + Math.pow((gameController.wyrmEnemies.get(i).y - gameController.ball.y), 2))) * AllEnemies.WyrmModel.enemySpeed;
                    if (gameController.ball.y < gameController.wyrmEnemies.get(i).y) {
                        gameController.wyrmEnemies.get(i).dy = -Math.sqrt(Math.pow(AllEnemies.WyrmModel.enemySpeed, 2) - Math.pow(gameController.wyrmEnemies.get(i).dx, 2));
                    } else {
                        gameController.wyrmEnemies.get(i).dy = Math.sqrt(Math.pow(AllEnemies.WyrmModel.enemySpeed, 2) - Math.pow(gameController.wyrmEnemies.get(i).dx, 2));
                    }
                }
                if (gameController.ball.ballDismay) {
                    gameController.wyrmEnemies.get(i).dx = -gameController.wyrmEnemies.get(i).dx;
                    gameController.wyrmEnemies.get(i).dy = -gameController.wyrmEnemies.get(i).dy;
                }
            }
        }
    }


    public static void updateWyrm(GameController gameController) {
        setDirectionForWyrm(gameController);
        if (!gameController.wyrmEnemies.isEmpty()) {
            for (int i = 0; i < gameController.wyrmEnemies.size(); i++) {
                if (gameController.wyrmEnemies.get(i).enemyHealth > 0) {
                    double epsilonDistance = Math.sqrt(Math.pow(Math.abs((gameController.wyrmEnemies.get(i).x + ((double) AllEnemies.WyrmModel.wyrmSize / 2)) - gameController.ball.x), 2) + Math.pow(Math.abs((gameController.wyrmEnemies.get(i).y + ((double) AllEnemies.WyrmModel.wyrmSize / 2)) - gameController.ball.y), 2));
                    if (epsilonDistance <= 200) {
                        if (gameController.ball.x > (gameController.wyrmEnemies.get(i).x + ((double) AllEnemies.WyrmModel.wyrmSize /2))
                                && gameController.ball.y > (gameController.wyrmEnemies.get(i).y + ((double) AllEnemies.WyrmModel.wyrmSize /2))) {
                            gameController.wyrmEnemies.get(i).x += 5;
                            gameController.wyrmEnemies.get(i).y += 5;
                        }
                        if (gameController.ball.x > (gameController.wyrmEnemies.get(i).x + ((double) AllEnemies.WyrmModel.wyrmSize /2))
                                && gameController.ball.y < (gameController.wyrmEnemies.get(i).y + ((double) AllEnemies.WyrmModel.wyrmSize /2))) {
                            gameController.wyrmEnemies.get(i).x -= 5;
                            gameController.wyrmEnemies.get(i).y += 5;
                        }
                        if (gameController.ball.x < (gameController.wyrmEnemies.get(i).x + ((double) AllEnemies.WyrmModel.wyrmSize /2))
                                && gameController.ball.y > (gameController.wyrmEnemies.get(i).y + ((double) AllEnemies.WyrmModel.wyrmSize /2))) {
                            gameController.wyrmEnemies.get(i).x -= 5;
                            gameController.wyrmEnemies.get(i).y -= 5;
                        }
                        if (gameController.ball.x < (gameController.wyrmEnemies.get(i).x + ((double) AllEnemies.WyrmModel.wyrmSize /2))
                                && gameController.ball.y < (gameController.wyrmEnemies.get(i).y + ((double) AllEnemies.WyrmModel.wyrmSize /2))) {
                            gameController.wyrmEnemies.get(i).x -= 5;
                            gameController.wyrmEnemies.get(i).y -= 5;
                        }
                    }
                    if (epsilonDistance > 200) {
                        gameController.wyrmEnemies.get(i).x += gameController.wyrmEnemies.get(i).dx + gameController.wyrmEnemies.get(i).ax;
                        gameController.wyrmEnemies.get(i).y += gameController.wyrmEnemies.get(i).dy + gameController.wyrmEnemies.get(i).ay;
                    }
                    if (gameController.wyrmEnemies.get(i).ax != 0) {
                        if (gameController.wyrmEnemies.get(i).ax > 0) {
                            gameController.wyrmEnemies.get(i).ax -= 0.05;
                        } else {
                            gameController.wyrmEnemies.get(i).ax += 0.05;
                        }
                    }
                    if (gameController.wyrmEnemies.get(i).ay != 0) {
                        if (gameController.wyrmEnemies.get(i).ay > 0) {
                            gameController.wyrmEnemies.get(i).ay -= 0.05;
                        } else {
                            gameController.wyrmEnemies.get(i).ay += 0.05;
                        }
                    }

                }
            }
        }

    }


    public static void shotBullet(GameController gameController) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (!gameController.phase2Over) {
                    for (int i = 0; i < gameController.wyrmEnemies.size(); i++) {
                        if (gameController.wyrmEnemies.get(i).enemyHealth > 0 && !gameController.pause) {
                            Point point = new Point();
                            point.setLocation(gameController.wyrmEnemies.get(i).x + ((double) AllEnemies.WyrmModel.wyrmSize / 2),
                                    gameController.wyrmEnemies.get(i).y + ((double) AllEnemies.WyrmModel.wyrmSize / 2));
                            gameController.newOmenoctBullet(point);
                        }
                    }
                }
            }

        };
        timer.scheduleAtFixedRate(task, 5000, 5000);
    }

}

