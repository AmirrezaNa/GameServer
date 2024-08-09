package controller.gameController.objectController.enemies;

import controller.gameController.GameController;
import model.entity.enemy.normalAndMiniBoss.NecropickModel;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class NecropickController {


    public static void update(GameController gameController) {
        if (!gameController.necropickEnemies.isEmpty()) {
            for (int i = 0; i < gameController.necropickEnemies.size(); i++) {
                if (gameController.necropickEnemies.get(i).enemyHealth > 0) {
                    if (gameController.necropickEnemies.get(i).hidingTime <= 4) {
                        gameController.necropickEnemies.get(i).hide = true;
                    }
                    else if (gameController.necropickEnemies.get(i).hidingTime <= 12) {
                        gameController.necropickEnemies.get(i).hide = false;

                    }
                    if (gameController.necropickEnemies.get(i).hidingTime >= 2 && gameController.necropickEnemies.get(i).hidingTime <= 4) {
                        gameController.necropickEnemies.get(i).necropickAlert = true;
                    }
                    else {
                        gameController.necropickEnemies.get(i).necropickAlert = false;
                    }
                }
            }
        }
    }

    public static void setNecropickHidingTime(GameController gameController) {

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (!gameController.phase2Over) {
                    if (!gameController.necropickEnemies.isEmpty()) {
                        for (int i = 0; i < gameController.necropickEnemies.size(); i++) {
                            if (gameController.necropickEnemies.get(i).enemyHealth > 0 && !gameController.pause) {
                                gameController.necropickEnemies.get(i).hidingTime++;
                                if (gameController.necropickEnemies.get(i).hidingTime == 6) {
                                    shotNecropickBullets(gameController.necropickEnemies.get(i), gameController);
                                }
                                if (gameController.necropickEnemies.get(i).hidingTime == 12) {
                                    gameController.necropickEnemies.get(i).x = gameController.ball.x - 200;
                                    gameController.necropickEnemies.get(i).y = gameController.ball.y;
                                    gameController.necropickEnemies.get(i).hidingTime = 0;
                                }
                            }
                        }
                    }
                }
//                if (stopWave) {
//                    timer.cancel();
//                }
            }

        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }


    public static void shotNecropickBullets(NecropickModel necropick, GameController gameController) {
        for (int i = 0; i < 8; i++) {
            Point point = new Point();
            point.setLocation(necropick.x + ((double) NecropickModel.necropickSize / 2), necropick.y + ((double) NecropickModel.necropickSize / 2));
            Point goal = new Point();
            goal.setLocation(gameController.ball.x, gameController.ball.y + 30 + ((Math.pow(-1,i) * i * 100)));
            gameController.newNecropickBullet(point, goal);
        }
    }


}
