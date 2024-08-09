package controller.gameController.objectController.enemies;

import controller.gameController.GameController;
import model.entity.enemy.normalAndMiniBoss.BarricadosModel1;

import java.util.Timer;
import java.util.TimerTask;

public class BarricadosController1 {

    public static void setDirectionForBarricados1(GameController gameController) {
        if (!gameController.barricadosEnemies1.isEmpty()) {
            for (int i = 0; i < gameController.barricadosEnemies1.size(); i++) {
                if (gameController.barricadosEnemies1.get(i).enemyTimer > 0) {
                    gameController.barricadosEnemies1.get(i).dx = -((gameController.barricadosEnemies1.get(i).x - gameController.ball.x) / Math.sqrt(Math.pow((gameController.barricadosEnemies1.get(i).x - gameController.ball.x), 2) + Math.pow((gameController.barricadosEnemies1.get(i).y - gameController.ball.y), 2))) * BarricadosModel1.enemySpeed;
                    if (gameController.ball.y < gameController.barricadosEnemies1.get(i).y) {
                        gameController.barricadosEnemies1.get(i).dy = -Math.sqrt(Math.pow(BarricadosModel1.enemySpeed, 2) - Math.pow(gameController.barricadosEnemies1.get(i).dx, 2));
                    } else {
                        gameController.barricadosEnemies1.get(i).dy = Math.sqrt(Math.pow(BarricadosModel1.enemySpeed, 2) - Math.pow(gameController.barricadosEnemies1.get(i).dx, 2));
                    }
                    if (gameController.ball.ballDismay) {
                        gameController.barricadosEnemies1.get(i).dx = -gameController.barricadosEnemies1.get(i).dx;
                        gameController.barricadosEnemies1.get(i).dy = -gameController.barricadosEnemies1.get(i).dy;
                    }
                }
            }
        }
    }

    public static void updateBarricados1(GameController gameController) {
        setDirectionForBarricados1(gameController);
        if (!gameController.barricadosEnemies1.isEmpty()) {
            for (int i = 0; i < gameController.barricadosEnemies1.size(); i++) {
                if (gameController.barricadosEnemies1.get(i).enemyTimer > 0) {
                    double epsilonDistance = Math.sqrt(Math.pow(Math.abs(gameController.barricadosEnemies1.get(i).x - gameController.ball.x), 2) + Math.pow(Math.abs(gameController.barricadosEnemies1.get(i).y - gameController.ball.y), 2));
                    if (epsilonDistance > 150) {
                        gameController.barricadosEnemies1.get(i).x += gameController.barricadosEnemies1.get(i).dx + gameController.barricadosEnemies1.get(i).ax;
                        gameController.barricadosEnemies1.get(i).y += gameController.barricadosEnemies1.get(i).dy + gameController.barricadosEnemies1.get(i).ay;
                    }

                    if (gameController.barricadosEnemies1.get(i).ax != 0) {
                        if (gameController.barricadosEnemies1.get(i).ax > 0) {
                            gameController.barricadosEnemies1.get(i).ax -= 0.05;
                        } else {
                            gameController.barricadosEnemies1.get(i).ax += 0.05;
                        }
                    }
                    if (gameController.barricadosEnemies1.get(i).ay != 0) {
                        if (gameController.barricadosEnemies1.get(i).ay > 0) {
                            gameController.barricadosEnemies1.get(i).ay -= 0.05;
                        } else {
                            gameController.barricadosEnemies1.get(i).ay += 0.05;
                        }
                    }
                }
            }
        }

    }


    public static void setTimerForBarricados1(BarricadosModel1 barricados1, GameController gameController) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (barricados1.enemyTimer > 0 && !gameController.pause) {
                    barricados1.enemyTimer--;
                }
//                if (stopWave) {
//                    timer.cancel();
//                }
            }

        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }
}

