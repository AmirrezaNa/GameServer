package controller.gameController.objectController.enemies;

import controller.gameController.GameController;
import model.entity.enemy.AllEnemies;

import java.util.Timer;
import java.util.TimerTask;

public class BlackOrbController {


    public static void setDirectionForBlackOrb(GameController gameController) {
        if (!gameController.blackOrbEnemies.isEmpty()) {
            for (int i = 0; i < gameController.blackOrbEnemies.size(); i++) {
                if (gameController.blackOrbEnemies.get(i).enemyHealth > 0) {
                    gameController.blackOrbEnemies.get(i).dx = -((gameController.blackOrbEnemies.get(i).x - gameController.ball.x) / Math.sqrt(Math.pow((gameController.blackOrbEnemies.get(i).x - gameController.ball.x), 2) + Math.pow((gameController.blackOrbEnemies.get(i).y - gameController.ball.y), 2))) * AllEnemies.BlackOrbModel.enemySpeed;
                    if (gameController.ball.y < gameController.blackOrbEnemies.get(i).y) {
                        gameController.blackOrbEnemies.get(i).dy = -Math.sqrt(Math.pow(AllEnemies.BlackOrbModel.enemySpeed, 2) - Math.pow(gameController.blackOrbEnemies.get(i).dx, 2));
                    } else {
                        gameController.blackOrbEnemies.get(i).dy = Math.sqrt(Math.pow(AllEnemies.BlackOrbModel.enemySpeed, 2) - Math.pow(gameController.blackOrbEnemies.get(i).dx, 2));
                    }
                    if (gameController.ball.ballDismay) {
                        gameController.blackOrbEnemies.get(i).dx = -gameController.blackOrbEnemies.get(i).dx;
                        gameController.blackOrbEnemies.get(i).dy = -gameController.blackOrbEnemies.get(i).dy;
                    }
                }
            }
        }
    }


    public static void updateBlackOrb(GameController gameController) {
        setDirectionForBlackOrb(gameController);
        if (!gameController.blackOrbEnemies.isEmpty()) {
            for (int i = 0; i < gameController.blackOrbEnemies.size(); i++) {
                if (gameController.blackOrbEnemies.get(i).enemyHealth > 0) {
                    if (gameController.blackOrbEnemies.get(i).draw5) {
                        gameController.blackOrbEnemies.get(i).x += gameController.blackOrbEnemies.get(i).dx + gameController.blackOrbEnemies.get(i).ax;
                        gameController.blackOrbEnemies.get(i).y += gameController.blackOrbEnemies.get(i).dy + gameController.blackOrbEnemies.get(i).ay;

                        if (gameController.blackOrbEnemies.get(i).ax != 0) {
                            if (gameController.blackOrbEnemies.get(i).ax > 0) {
                                gameController.blackOrbEnemies.get(i).ax -= 0.05;
                            } else {
                                gameController.blackOrbEnemies.get(i).ax += 0.05;
                            }
                        }
                        if (gameController.blackOrbEnemies.get(i).ay != 0) {
                            if (gameController.blackOrbEnemies.get(i).ay > 0) {
                                gameController.blackOrbEnemies.get(i).ay -= 0.05;
                            } else {
                                gameController.blackOrbEnemies.get(i).ay += 0.05;
                            }
                        }
                    }
                }
            }
        }

    }


    public static void setTimerForCreatingBlackOrb(AllEnemies.BlackOrbModel blackOrb, GameController gameController) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (blackOrb.blackOrbTimer == 20) {
                    blackOrb.draw1 = true;
                }
                if (blackOrb.blackOrbTimer == 15) {
                    blackOrb.draw2 = true;
                }
                if (blackOrb.blackOrbTimer == 10) {
                    blackOrb.draw3 = true;
                }
                if (blackOrb.blackOrbTimer == 5) {
                    blackOrb.draw4 = true;
                }
                if (blackOrb.blackOrbTimer == 0) {
                    blackOrb.draw5 = true;
                }
                if (!gameController.pause) {
                    blackOrb.blackOrbTimer--;
                }

            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }
}
