package controller.gameController.objectController;

import controller.gameController.GameController;
import model.entity.BulletModel;

import java.util.Timer;
import java.util.TimerTask;

public class BulletController {


    public static void updateBullet(GameController gameController) {
        if (gameController.Empower == 1) {
            empowerBullet(gameController);
            gameController.Empower--;
        }
        if (!gameController.bullets.isEmpty()) {
            for (int i = 0; i < gameController.bullets.size(); i++) {
                if (gameController.bullets.get(i).bulletHealth > 0) {
                    gameController.bullets.get(i).x += gameController.bullets.get(i).dx;
                    gameController.bullets.get(i).y += gameController.bullets.get(i).dy;
                }
            }
        }
    }

    public static void updateEnemyBullet(GameController gameController) {
        if (!gameController.enemyBullets.isEmpty()) {
            for (int i = 0; i < gameController.enemyBullets.size(); i++) {
                if (gameController.enemyBullets.get(i).bulletHealth > 0) {
                    gameController.enemyBullets.get(i).x += gameController.enemyBullets.get(i).dx;
                    gameController.enemyBullets.get(i).y += gameController.enemyBullets.get(i).dy;
                }
            }
        }
    }


    public static void empowerBullet(GameController gameController) {
        if (!gameController.pause) {
            gameController.empowerBullet = true;
            Timer timer = new Timer();
            int[] countDownEmpower = {10};
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    if (countDownEmpower[0] > 0) {
                        countDownEmpower[0]--;
                    } else {
                        gameController.empowerBullet = false;
                        timer.cancel();
                    }
                }
            };
            timer.scheduleAtFixedRate(task, 0, 1000);
        }
    }


    public static boolean isBulletInAFrame(BulletModel bullet, GameController gameController) {
        for (int i = 0; i < gameController.createdFrames.length; i++) {
            int x = gameController.createdFrames[i].x;
            int y = gameController.createdFrames[i].y;
            int width = gameController.createdFrames[i].width;
            int height = gameController.createdFrames[i].height;
            if ((bullet.x >= x) && (bullet.x <= (x + width - BulletModel.bulletSize))
                    && (bullet.y > y) && (bullet.y < (y + height - BulletModel.bulletSize))) {
                return true;
            }
        }
        return false;
    }
}

