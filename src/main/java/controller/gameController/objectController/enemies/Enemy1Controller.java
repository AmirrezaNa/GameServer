package controller.gameController.objectController.enemies;

import controller.gameController.GameController;
import controller.gameController.Rotation;
import model.entity.enemy.AllEnemies;

public class Enemy1Controller {



    public static void setDirectionForEnemy1(GameController gameController) {
        if (!gameController.enemies1.isEmpty()) {
            for (int i = 0; i < gameController.enemies1.size(); i++) {
                if (gameController.enemies1.get(i).enemyHealth > 0) {
                    gameController.enemies1.get(i).dx = -((gameController.enemies1.get(i).x - gameController.ball.x) / Math.sqrt(Math.pow((gameController.enemies1.get(i).x - gameController.ball.x), 2) + Math.pow((gameController.enemies1.get(i).y - gameController.ball.y), 2))) * AllEnemies.EnemyModel1.enemySpeed;
                    if (gameController.ball.y < gameController.enemies1.get(i).y) {
                        gameController.enemies1.get(i).dy = -Math.sqrt(Math.pow(AllEnemies.EnemyModel1.enemySpeed, 2) - Math.pow(gameController.enemies1.get(i).dx, 2));
                    } else {
                        gameController.enemies1.get(i).dy = Math.sqrt(Math.pow(AllEnemies.EnemyModel1.enemySpeed, 2) - Math.pow(gameController.enemies1.get(i).dx, 2));
                    }
                    if (gameController.ball.ballDismay) {
                        gameController.enemies1.get(i).dx = -gameController.enemies1.get(i).dx;
                        gameController.enemies1.get(i).dy = -gameController.enemies1.get(i).dy;
                    }
                }
            }
        }
    }


    public static void updateEnemy1(GameController gameController) {
        setDirectionForEnemy1(gameController);
        if (!gameController.enemies1.isEmpty()) {
            for (int i = 0; i < gameController.enemies1.size(); i++) {
                if (gameController.enemies1.get(i).enemyHealth > 0) {
                    if (gameController.enemies1.get(i).dash) {
                        gameController.enemies1.get(i).x += 2 * (gameController.enemies1.get(i).dx + gameController.enemies1.get(i).ax);
                        gameController.enemies1.get(i).y += 2 * (gameController.enemies1.get(i).dy + gameController.enemies1.get(i).ay);
                    }
                    if (!gameController.enemies1.get(i).dash) {
                        gameController.enemies1.get(i).x += gameController.enemies1.get(i).dx + gameController.enemies1.get(i).ax;
                        gameController.enemies1.get(i).y += gameController.enemies1.get(i).dy + gameController.enemies1.get(i).ay;
                    }

                    if (gameController.enemies1.get(i).ax != 0) {
                        if (gameController.enemies1.get(i).ax > 0) {
                            gameController.enemies1.get(i).ax -= 0.05;
                        } else {
                            gameController.enemies1.get(i).ax += 0.05;
                        }
                    }
                    if (gameController.enemies1.get(i).ay != 0) {
                        if (gameController.enemies1.get(i).ay > 0) {
                            gameController.enemies1.get(i).ay -= 0.05;
                        } else {
                            gameController.enemies1.get(i).ay += 0.05;
                        }
                    }
                    Rotation.enemy1Rotation(gameController);
                }
            }
        }

    }
}

