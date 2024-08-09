package controller.gameController.objectController.enemies;

import controller.gameController.GameController;
import model.entity.enemy.normalAndMiniBoss.ArchmireModel;
import model.entity.enemy.normalAndMiniBoss.ArchmirePoints;

import java.util.Timer;
import java.util.TimerTask;

public class ArchmireController {

    public static void setDirectionForArchmire(GameController gameController) {
        if (!gameController.archmireEnemies.isEmpty()) {
            for (int i = 0; i < gameController.archmireEnemies.size(); i++) {
                if (gameController.archmireEnemies.get(i).enemyHealth > 0) {
                    gameController.archmireEnemies.get(i).dx = -((gameController.archmireEnemies.get(i).x - gameController.ball.x) / Math.sqrt(Math.pow((gameController.archmireEnemies.get(i).x - gameController.ball.x), 2) + Math.pow((gameController.archmireEnemies.get(i).y - gameController.ball.y), 2))) * ArchmireModel.enemySpeed;
                    if (gameController.ball.y < gameController.archmireEnemies.get(i).y) {
                        gameController.archmireEnemies.get(i).dy = -Math.sqrt(Math.pow(ArchmireModel.enemySpeed, 2) - Math.pow(gameController.archmireEnemies.get(i).dx, 2));
                    } else {
                        gameController.archmireEnemies.get(i).dy = Math.sqrt(Math.pow(ArchmireModel.enemySpeed, 2) - Math.pow(gameController.archmireEnemies.get(i).dx, 2));
                    }
                }
            }
        }
    }

    public static void updateArchmire(GameController gameController) {
        setDirectionForArchmire(gameController);
        if (!gameController.archmireEnemies.isEmpty()) {
            for (int i = 0; i < gameController.archmireEnemies.size(); i++) {
                if (gameController.archmireEnemies.get(i).enemyHealth > 0) {
                    gameController.archmireEnemies.get(i).x += gameController.archmireEnemies.get(i).dx + gameController.archmireEnemies.get(i).ax;
                    gameController.archmireEnemies.get(i).y += gameController.archmireEnemies.get(i).dy + gameController.archmireEnemies.get(i).ay;

                    if (gameController.archmireEnemies.get(i).ax != 0) {
                        if (gameController.archmireEnemies.get(i).ax > 0) {
                            gameController.archmireEnemies.get(i).ax -= 0.05;
                        } else {
                            gameController.archmireEnemies.get(i).ax += 0.05;
                        }
                    }
                    if (gameController.archmireEnemies.get(i).ay != 0) {
                        if (gameController.archmireEnemies.get(i).ay > 0) {
                            gameController.archmireEnemies.get(i).ay -= 0.05;
                        } else {
                            gameController.archmireEnemies.get(i).ay += 0.05;
                        }
                    }
                }
            }
        }

    }

    public static void setTrace(ArchmireModel archmire, GameController gameController) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (archmire.enemyHealth > 0  && !gameController.pause) {
                    ArchmirePoints archmirePoint = new ArchmirePoints(archmire.x + ((double) ArchmireModel.archmireSize / 2), archmire.y + ((double) ArchmireModel.archmireSize / 2));
                    gameController.archmirePoints.add(0, archmirePoint);
                    setTimerForPoint(archmirePoint, gameController);
                }
//                if (stopWave) {
//                    timer.cancel();
//                }
            }

        };
        timer.scheduleAtFixedRate(task, 0, 500);
    }


    public static void setTimerForPoint(ArchmirePoints archmirePoint, GameController gameController) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (!gameController.pause) {
                    archmirePoint.archmirePointTimer--;
                }

//                if (stopWave) {
//                    timer.cancel();
//                }
            }

        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }


}

