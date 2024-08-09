package controller.gameController.objectController.enemies;

import controller.gameController.GameController;
import model.entity.enemy.normalAndMiniBoss.BarricadosModel2;

import java.util.Timer;
import java.util.TimerTask;

public class BarricadosController2 {

    public static void setDirectionForBarricados2(GameController gameController) {
        if (!gameController.barricadosEnemies2.isEmpty()) {
            for (int i = 0; i < gameController.barricadosEnemies2.size(); i++) {
                if (gameController.barricadosEnemies2.get(i).enemyTimer > 0) {
                    gameController.barricadosEnemies2.get(i).dx = -((gameController.barricadosEnemies2.get(i).x - gameController.ball.x) / Math.sqrt(Math.pow((gameController.barricadosEnemies2.get(i).x - gameController.ball.x), 2) + Math.pow((gameController.barricadosEnemies2.get(i).y - gameController.ball.y), 2))) * BarricadosModel2.enemySpeed;
                    if (gameController.ball.y < gameController.barricadosEnemies2.get(i).y) {
                        gameController.barricadosEnemies2.get(i).dy = -Math.sqrt(Math.pow(BarricadosModel2.enemySpeed, 2) - Math.pow(gameController.barricadosEnemies2.get(i).dx, 2));
                    } else {
                        gameController.barricadosEnemies2.get(i).dy = Math.sqrt(Math.pow(BarricadosModel2.enemySpeed, 2) - Math.pow(gameController.barricadosEnemies2.get(i).dx, 2));
                    }
                    if (gameController.ball.ballDismay) {
                        gameController.barricadosEnemies2.get(i).dx = -gameController.barricadosEnemies2.get(i).dx;
                        gameController.barricadosEnemies2.get(i).dy = -gameController.barricadosEnemies2.get(i).dy;
                    }
                }
            }
        }
    }

    public static void updateBarricados2(GameController gameController) {
        setDirectionForBarricados2(gameController);
        if (!gameController.barricadosEnemies2.isEmpty()) {
            for (int i = 0; i < gameController.barricadosEnemies2.size(); i++) {
                if (gameController.barricadosEnemies2.get(i).enemyTimer > 0) {
                    gameController.barricadosEnemies2.get(i).x += gameController.barricadosEnemies2.get(i).dx + gameController.barricadosEnemies2.get(i).ax;
                    gameController.barricadosEnemies2.get(i).y += gameController.barricadosEnemies2.get(i).dy + gameController.barricadosEnemies2.get(i).ay;

                    if (gameController.barricadosEnemies2.get(i).ax != 0) {
                        if (gameController.barricadosEnemies2.get(i).ax > 0) {
                            gameController.barricadosEnemies2.get(i).ax -= 0.05;
                        } else {
                            gameController.barricadosEnemies2.get(i).ax += 0.05;
                        }
                    }
                    if (gameController.barricadosEnemies2.get(i).ay != 0) {
                        if (gameController.barricadosEnemies2.get(i).ay > 0) {
                            gameController.barricadosEnemies2.get(i).ay -= 0.05;
                        } else {
                            gameController.barricadosEnemies2.get(i).ay += 0.05;
                        }
                    }
                }
            }
        }

    }

    public static void setTimerForBarricados2(BarricadosModel2 barricados2, GameController gameController) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (barricados2.enemyTimer > 0 && !gameController.pause) {
                    barricados2.enemyTimer--;
                }
//                if (stopWave) {
//                    timer.cancel();
//                }
            }

        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    public static boolean isBarricados2InFrame(int k, GameController gameController) {
        if (!gameController.barricadosEnemies2.isEmpty()) {
            for (int i = 0; i < gameController.barricadosEnemies2.size(); i++) {
                if (gameController.barricadosEnemies2.get(i).x >= gameController.createdFrames[i].x &&
                        gameController.barricadosEnemies2.get(i).x <= gameController.createdFrames[i].x + gameController.createdFrames[i].width &&
                        gameController.barricadosEnemies2.get(i).y >= gameController.createdFrames[i].y &&
                        gameController.barricadosEnemies2.get(i).y <= gameController.createdFrames[i].y + gameController.createdFrames[i].height) {
                    return true;
                }
            }
        }
        return false;
    }
}
