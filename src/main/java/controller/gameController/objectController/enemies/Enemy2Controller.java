package controller.gameController.objectController.enemies;

import controller.gameController.GameController;
import controller.gameController.Rotation;
import model.entity.enemy.normalAndMiniBoss.EnemyModel2;

public class Enemy2Controller {


    public static void setDirectionForEnemy2(GameController gameController) {
        if (!gameController.enemies2.isEmpty()) {
            for (int i = 0; i < gameController.enemies2.size(); i++) {
                if (gameController.enemies2.get(i).enemyHealth > 0) {
                    gameController.enemies2.get(i).dx = -((gameController.enemies2.get(i).x - gameController.ball.x) / Math.sqrt(Math.pow((gameController.enemies2.get(i).x - gameController.ball.x), 2) + Math.pow((gameController.enemies2.get(i).y - gameController.ball.y), 2))) * EnemyModel2.enemySpeed;
                    if (gameController.ball.y < gameController.enemies2.get(i).y) {
                        gameController.enemies2.get(i).dy = -Math.sqrt(Math.pow(EnemyModel2.enemySpeed, 2) - Math.pow(gameController.enemies2.get(i).dx, 2));
                    } else {
                        gameController.enemies2.get(i).dy = Math.sqrt(Math.pow(EnemyModel2.enemySpeed, 2) - Math.pow(gameController.enemies2.get(i).dx, 2));
                    }
                    if (gameController.ball.ballDismay) {
                        gameController.enemies2.get(i).dx = -gameController.enemies2.get(i).dx;
                        gameController.enemies2.get(i).dy = -gameController.enemies2.get(i).dy;
                    }
                }
            }
        }
    }

    public static void updateEnemy2(GameController gameController) {
        setDirectionForEnemy2(gameController);
        if (!gameController.enemies2.isEmpty()) {
            for (int i = 0; i < gameController.enemies2.size(); i++) {
                if (gameController.enemies2.get(i).enemyHealth > 0) {
                    double epsilonDistance = Math.sqrt(Math.pow(Math.abs(gameController.enemies2.get(i).x - gameController.ball.x), 2) + Math.pow(Math.abs(gameController.enemies2.get(i).y - gameController.ball.y), 2));
                    if (epsilonDistance > 100) {
                        gameController.enemies2.get(i).x += 2 * (gameController.enemies2.get(i).dx + gameController.enemies2.get(i).ax);
                        gameController.enemies2.get(i).y += 2 * (gameController.enemies2.get(i).dy + gameController.enemies2.get(i).ay);
                    }
                    if (epsilonDistance <= 100) {
                        gameController.enemies2.get(i).x += gameController.enemies2.get(i).dx + gameController.enemies2.get(i).ax;
                        gameController.enemies2.get(i).y += gameController.enemies2.get(i).dy + gameController.enemies2.get(i).ay;
                    }

                    if (gameController.enemies2.get(i).ax != 0) {
                        if (gameController.enemies2.get(i).ax > 0) {
                            gameController.enemies2.get(i).ax -= 0.05;
                        } else {
                            gameController.enemies2.get(i).ax += 0.05;
                        }
                    }
                    if (gameController.enemies2.get(i).ay != 0) {
                        if (gameController.enemies2.get(i).ay > 0) {
                            gameController.enemies2.get(i).ay -= 0.05;
                        } else {
                            gameController.enemies2.get(i).ay += 0.05;
                        }
                    }
                    Rotation.enemy2Rotation(gameController);
                }
            }
        }

    }
}