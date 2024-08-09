package controller.gameController.objectController.enemies;

import controller.gameController.FrameOfObject;
import controller.gameController.GameController;
import controller.gameController.Rotation;
import model.entity.enemy.AllEnemies;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class OmenoctController {


    public static void setDirectionForOmenoct(GameController gameController) {
        if (!gameController.omenoctEnemies.isEmpty()) {
            for (int i = 0; i < gameController.omenoctEnemies.size(); i++) {
                if (gameController.omenoctEnemies.get(i).enemyHealth > 0) {
                    if (FrameOfObject.getFrameOfBall(gameController) != -1) {
                        int x = gameController.createdFrames[FrameOfObject.getFrameOfBall(gameController)].x + gameController.createdFrames[FrameOfObject.getFrameOfBall(gameController)].width - (AllEnemies.OmenoctModel.omenoctSize / 2);
//                        int y = createdFrames[FrameOfObject.getFrameOfBall()].y + createdFrames[FrameOfObject.getFrameOfBall()].height / 2;
                        int y = (int) gameController.ball.y;
                        gameController.omenoctEnemies.get(i).dx = -((gameController.omenoctEnemies.get(i).x - x) / Math.sqrt(Math.pow((gameController.omenoctEnemies.get(i).x - x), 2) + Math.pow((gameController.omenoctEnemies.get(i).y - y), 2))) * AllEnemies.OmenoctModel.enemySpeed;
                        if (y < gameController.omenoctEnemies.get(i).y) {
                            gameController.omenoctEnemies.get(i).dy = -Math.sqrt(Math.pow(AllEnemies.OmenoctModel.enemySpeed, 2) - Math.pow(gameController.omenoctEnemies.get(i).dx, 2));
                        } else {
                            gameController.omenoctEnemies.get(i).dy = Math.sqrt(Math.pow(AllEnemies.OmenoctModel.enemySpeed, 2) - Math.pow(gameController.omenoctEnemies.get(i).dx, 2));
                        }
                    }
                }
            }
        }
    }

    public static void setDirectionWhileDismay(GameController gameController) {
        if (!gameController.omenoctEnemies.isEmpty()) {
            for (int i = 0; i < gameController.omenoctEnemies.size(); i++) {
                if (gameController.omenoctEnemies.get(i).enemyHealth > 0) {
                    gameController.omenoctEnemies.get(i).dx = -((gameController.omenoctEnemies.get(i).x - gameController.ball.x) / Math.sqrt(Math.pow((gameController.omenoctEnemies.get(i).x - gameController.ball.x), 2) + Math.pow((gameController.omenoctEnemies.get(i).y - gameController.ball.y), 2))) * AllEnemies.EnemyModel1.enemySpeed;
                    if (gameController.ball.y < gameController.omenoctEnemies.get(i).y) {
                        gameController.omenoctEnemies.get(i).dy = -Math.sqrt(Math.pow(AllEnemies.EnemyModel1.enemySpeed, 2) - Math.pow(gameController.omenoctEnemies.get(i).dx, 2));
                    } else {
                        gameController.omenoctEnemies.get(i).dy = Math.sqrt(Math.pow(AllEnemies.EnemyModel1.enemySpeed, 2) - Math.pow(gameController.omenoctEnemies.get(i).dx, 2));
                    }
                    if (gameController.ball.ballDismay) {
                        gameController.omenoctEnemies.get(i).dx = -gameController.omenoctEnemies.get(i).dx;
                        gameController.omenoctEnemies.get(i).dy = -gameController.omenoctEnemies.get(i).dy;
                    }
                }
            }
        }
    }


    public static void updateOmenoct(GameController gameController) {
        if (gameController.ball.ballDismay) {
            setDirectionWhileDismay(gameController);
        }
        setDirectionForOmenoct(gameController);
        if (!gameController.omenoctEnemies.isEmpty()) {
            for (int i = 0; i < gameController.omenoctEnemies.size(); i++) {
                if (gameController.omenoctEnemies.get(i).enemyHealth > 0) {
                    gameController.omenoctEnemies.get(i).x += gameController.omenoctEnemies.get(i).dx + gameController.omenoctEnemies.get(i).ax;
                    gameController.omenoctEnemies.get(i).y += gameController.omenoctEnemies.get(i).dy + gameController.omenoctEnemies.get(i).ay;

                    if (gameController.omenoctEnemies.get(i).ax != 0) {
                        if (gameController.omenoctEnemies.get(i).ax > 0) {
                            gameController.omenoctEnemies.get(i).ax -= 0.05;
                        } else {
                            gameController.omenoctEnemies.get(i).ax += 0.05;
                        }
                    }
                    if (gameController.omenoctEnemies.get(i).ay != 0) {
                        if (gameController.omenoctEnemies.get(i).ay > 0) {
                            gameController.omenoctEnemies.get(i).ay -= 0.05;
                        } else {
                            gameController.omenoctEnemies.get(i).ay += 0.05;
                        }
                    }
                    Rotation.enemy2Rotation(gameController);
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
                    for (int i = 0; i < gameController.omenoctEnemies.size(); i++) {
                        if (gameController.omenoctEnemies.get(i).enemyHealth > 0 && !gameController.pause) {
                            Point point = new Point();
                            point.setLocation(gameController.omenoctEnemies.get(i).x + AllEnemies.OmenoctModel.distanceToCenter,
                                    gameController.omenoctEnemies.get(i).y + AllEnemies.OmenoctModel.distanceToCenter);
                            gameController.newOmenoctBullet(point);
                        }
                    }
                }
            }

        };
        timer.scheduleAtFixedRate(task, 5000, 5000);
    }

}
