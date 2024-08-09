package controller.gameController.collisions.phase1;

import controller.data.SoundEffects;
import controller.gameController.Constants;
import controller.gameController.GameController;
import controller.gameController.Impact;
import model.ClientModel;
import model.entity.BallAngle;
import model.entity.BallModel;
import model.entity.BulletModel;
import model.entity.Collectible;

public class ObjectCollision {


    // ===========  Here is where we are checking the intersections of different types of objects ===============


    public static void checkObjectsCollisions(GameController gameController, ClientModel client) {
        checkCollisionEnemy1Enemy2(gameController);
        checkCollisionEnemy1Enemy1(gameController);
        checkCollisionEnemy2Enemy2(gameController);

        checkCollisionBallEnemy1(gameController, client);
        checkCollisionBallEnemy2(gameController, client);
        checkCollisionBallCollectible(gameController, client);

        checkCollisionBulletEnemy1(gameController);
        checkCollisionBulletEnemy2(gameController);


        checkCollisionBallAngleEnemy1(gameController);
        checkCollisionBallAngleEnemy2(gameController);

    }


    // ===========  Here is where we are checking the intersections of different types of objects ===============


    // a method for checking intersections between enemy1 and enemy2

    public static void checkCollisionEnemy1Enemy2(GameController gameController) {
        if (!gameController.enemies1.isEmpty()) {
            for (int k = 0; k < gameController.enemies1.size(); k++) {
                if (gameController.enemies1.get(k).enemyHealth > 0) {

                    double xMin1 = gameController.enemies1.get(k).xAngles[0];
                    double xMax1 = gameController.enemies1.get(k).xAngles[0];
                    double yMin1 = gameController.enemies1.get(k).yAngles[0];
                    double yMax1 = gameController.enemies1.get(k).yAngles[0];
                    for (int i = 0; i < 4; i++) {
                        if (gameController.enemies1.get(k).xAngles[i] < xMin1) {
                            xMin1 = gameController.enemies1.get(k).xAngles[i];
                        }
                        if (gameController.enemies1.get(k).xAngles[i] > xMax1) {
                            xMax1 = gameController.enemies1.get(k).xAngles[i];
                        }
                        if (gameController.enemies1.get(k).yAngles[i] < yMin1) {
                            yMin1 = gameController.enemies1.get(k).yAngles[i];
                        }
                        if (gameController.enemies1.get(k).yAngles[i] > yMax1) {
                            yMax1 = gameController.enemies1.get(k).yAngles[i];
                        }
                    }
                    for (int j = 0; j < gameController.enemies2.size(); j++) {
                        if (gameController.enemies2.get(j).enemyHealth > 0) {

                            double xMin2 = gameController.enemies2.get(j).xAngles[0];
                            double xMax2 = gameController.enemies2.get(j).xAngles[0];
                            double yMin2 = gameController.enemies2.get(j).yAngles[0];
                            double yMax2 = gameController.enemies2.get(j).yAngles[0];
                            for (int i = 0; i < 3; i++) {
                                if (gameController.enemies2.get(j).xAngles[i] < xMin2) {
                                    xMin2 = gameController.enemies2.get(j).xAngles[i];
                                }
                                if (gameController.enemies2.get(j).xAngles[i] > xMax2) {
                                    xMax2 = gameController.enemies2.get(j).xAngles[i];
                                }
                                if (gameController.enemies2.get(j).yAngles[i] < yMin2) {
                                    yMin2 = gameController.enemies2.get(j).yAngles[i];
                                }
                                if (gameController.enemies2.get(j).yAngles[i] > yMax2) {
                                    yMax2 = gameController.enemies2.get(j).yAngles[i];
                                }
                            }

                            if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {
                                gameController.enemies1.get(k).dash = false;
                                Impact.turnOnImpact(gameController.enemies1.get(k).x,
                                        gameController.enemies1.get(k).y,
                                        gameController.enemies2.get(j).x,
                                        gameController.enemies2.get(j).y, gameController);
                            }
                        }
                    }
                }
            }
        }
    }


    // ==========================================================================================


    // this method is for checking the intersections between enemies of type1 with each other
    public static void checkCollisionEnemy1Enemy1(GameController gameController) {
        for (int i = 0; i < gameController.enemies1.size() - 1; i++) {
            if (gameController.enemies1.get(i).enemyHealth > 0) {

                double xMin1 = gameController.enemies1.get(i).xAngles[0];
                double xMax1 = gameController.enemies1.get(i).xAngles[0];
                double yMin1 = gameController.enemies1.get(i).yAngles[0];
                double yMax1 = gameController.enemies1.get(i).yAngles[0];
                for (int k = 0; k < 4; k++) {
                    if (xMin1 > gameController.enemies1.get(i).xAngles[k]) {
                        xMin1 = gameController.enemies1.get(i).xAngles[k];
                    }
                    if (xMax1 < gameController.enemies1.get(i).xAngles[k]) {
                        xMax1 = gameController.enemies1.get(i).xAngles[k];
                    }
                    if (yMin1 > gameController.enemies1.get(i).yAngles[k]) {
                        yMin1 = gameController.enemies1.get(i).yAngles[k];
                    }
                    if (yMax1 < gameController.enemies1.get(i).yAngles[k]) {
                        yMax1 = gameController.enemies1.get(i).yAngles[k];
                    }
                }
                for (int j = i + 1; j < gameController.enemies1.size(); j++) {
                    if (gameController.enemies1.get(j).enemyHealth > 0) {

                        double xMin2 = gameController.enemies1.get(j).xAngles[0];
                        double xMax2 = gameController.enemies1.get(j).xAngles[0];
                        double yMin2 = gameController.enemies1.get(j).yAngles[0];
                        double yMax2 = gameController.enemies1.get(j).yAngles[0];
                        for (int k = 0; k < 4; k++) {
                            if (xMin2 > gameController.enemies1.get(j).xAngles[k]) {
                                xMin2 = gameController.enemies1.get(j).xAngles[k];
                            }
                            if (xMax2 < gameController.enemies1.get(j).xAngles[k]) {
                                xMax2 = gameController.enemies1.get(j).xAngles[k];
                            }
                            if (yMin2 > gameController.enemies1.get(j).yAngles[k]) {
                                yMin2 = gameController.enemies1.get(j).yAngles[k];
                            }
                            if (yMax2 < gameController.enemies1.get(j).yAngles[k]) {
                                yMax2 = gameController.enemies1.get(j).yAngles[k];
                            }
                        }

                        if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                                || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                            gameController.enemies1.get(i).dash = false;
                            gameController.enemies1.get(j).dash = false;
                            Impact.turnOnImpact(gameController.enemies1.get(i).x,
                                    gameController.enemies1.get(i).y,
                                    gameController.enemies1.get(j).x,
                                    gameController.enemies1.get(j).y, gameController);

                        }
                    }
                }
            }
        }
    }

    // this method is for checking the intersections between enemies of type2 with each other

    public static void checkCollisionEnemy2Enemy2(GameController gameController) {
        for (int i = 0; i < gameController.enemies2.size() - 1; i++) {
            if (gameController.enemies2.get(i).enemyHealth > 0) {

                double xMin1 = gameController.enemies2.get(i).xAngles[0];
                double xMax1 = gameController.enemies2.get(i).xAngles[0];
                double yMin1 = gameController.enemies2.get(i).yAngles[0];
                double yMax1 = gameController.enemies2.get(i).yAngles[0];
                for (int k = 0; k < 3; k++) {
                    if (xMin1 > gameController.enemies2.get(i).xAngles[k]) {
                        xMin1 = gameController.enemies2.get(i).xAngles[k];
                    }
                    if (xMax1 < gameController.enemies2.get(i).xAngles[k]) {
                        xMax1 = gameController.enemies2.get(i).xAngles[k];
                    }
                    if (yMin1 > gameController.enemies2.get(i).yAngles[k]) {
                        yMin1 = gameController.enemies2.get(i).yAngles[k];
                    }
                    if (yMax1 < gameController.enemies2.get(i).yAngles[k]) {
                        yMax1 = gameController.enemies2.get(i).yAngles[k];
                    }
                }
                for (int j = i + 1; j < gameController.enemies2.size(); j++) {
                    if (gameController.enemies2.get(j).enemyHealth > 0) {

                        double xMin2 = gameController.enemies2.get(j).xAngles[0];
                        double xMax2 = gameController.enemies2.get(j).xAngles[0];
                        double yMin2 = gameController.enemies2.get(j).yAngles[0];
                        double yMax2 = gameController.enemies2.get(j).yAngles[0];
                        for (int k = 0; k < 3; k++) {
                            if (xMin2 > gameController.enemies2.get(j).xAngles[k]) {
                                xMin2 = gameController.enemies2.get(j).xAngles[k];
                            }
                            if (xMax2 < gameController.enemies2.get(j).xAngles[k]) {
                                xMax2 = gameController.enemies2.get(j).xAngles[k];
                            }
                            if (yMin2 > gameController.enemies2.get(j).yAngles[k]) {
                                yMin2 = gameController.enemies2.get(j).yAngles[k];
                            }
                            if (yMax2 < gameController.enemies2.get(j).yAngles[k]) {
                                yMax2 = gameController.enemies2.get(j).yAngles[k];
                            }
                        }

                        if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                                || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                            Impact.turnOnImpact(gameController.enemies2.get(i).x,
                                    gameController.enemies2.get(i).y,
                                    gameController.enemies2.get(j).x,
                                    gameController.enemies2.get(j).y, gameController);

                        }
                    }
                }
            }
        }
    }


    // a method for checking intersections between enemy1 and the ball

    public static void checkCollisionBallEnemy1(GameController gameController, ClientModel client) {
        double xMin1 = gameController.ball.x - BallModel.ballRadius;
        double xMax1 = gameController.ball.x + BallModel.ballRadius;
        double yMin1 = gameController.ball.y - BallModel.ballRadius;
        double yMax1 = gameController.ball.y + BallModel.ballRadius;
        for (int k = 0; k < gameController.enemies1.size(); k++) {
            if (gameController.enemies1.get(k).enemyHealth > 0) {

                double xMin2 = gameController.enemies1.get(k).xAngles[0];
                double xMax2 = gameController.enemies1.get(k).xAngles[0];
                double yMin2 = gameController.enemies1.get(k).yAngles[0];
                double yMax2 = gameController.enemies1.get(k).yAngles[0];
                for (int i = 0; i < 4; i++) {
                    if (gameController.enemies1.get(k).xAngles[i] < xMin2) {
                        xMin2 = gameController.enemies1.get(k).xAngles[i];
                    }
                    if (gameController.enemies1.get(k).xAngles[i] > xMax2) {
                        xMax2 = gameController.enemies1.get(k).xAngles[i];
                    }
                    if (gameController.enemies1.get(k).yAngles[i] < yMin2) {
                        yMin2 = gameController.enemies1.get(k).yAngles[i];
                    }
                    if (gameController.enemies1.get(k).yAngles[i] > yMax2) {
                        yMax2 = gameController.enemies1.get(k).yAngles[i];
                    }
                }

                if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                        || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {
                    boolean angleCollided = false;
                    for (int i = 0; i < 4; i++) {
                        if ((xMin1 <= gameController.enemies1.get(k).xAngles[i]) &&
                                (xMax1 >= gameController.enemies1.get(k).xAngles[i]) &&
                                (yMin1 <= gameController.enemies1.get(k).yAngles[i]) &&
                                (yMax1 >= gameController.enemies1.get(k).yAngles[i])) {
                            angleCollided = true;
                        }
                    }
                    if (angleCollided) {
                        if (gameController.settings.level == 1) {
                            gameController.ball.HP -= 4;
                        }
                        if (gameController.settings.level == 2) {
                            gameController.ball.HP -= 6;
                        }
                        if (gameController.settings.level == 3) {
                            gameController.ball.HP -= 8;
                        }

                        SoundEffects.playSound(Constants.HURT_SOUND_PATH);
                    }
                    if (client.player.isWritOfAstrape()) {
                        gameController.enemies1.get(k).enemyHealth -= 2;
                    }
                    if (gameController.ball.ballCerberus) {
                        gameController.enemies1.get(k).enemyHealth -= 10;
                        gameController.ball.ballCerberus = false;
                    }

                    gameController.enemies1.get(k).dash = false;
                    gameController.enemies1.get(k).dAngle = Math.PI;
                    Impact.turnOnImpact(gameController.ball.x,
                            gameController.ball.y,
                            gameController.enemies1.get(k).x,
                            gameController.enemies1.get(k).y, gameController);

                }
            }
        }
    }


    // a method for checking intersections between enemy2 and the ball

    public static void checkCollisionBallEnemy2(GameController gameController, ClientModel client) {
        double xMin1 = gameController.ball.x - BallModel.ballRadius;
        double xMax1 = gameController.ball.x + BallModel.ballRadius;
        double yMin1 = gameController.ball.y - BallModel.ballRadius;
        double yMax1 = gameController.ball.y + BallModel.ballRadius;
        for (int k = 0; k < gameController.enemies2.size(); k++) {
            if (gameController.enemies2.get(k).enemyHealth > 0) {

                double xMin2 = gameController.enemies2.get(k).xAngles[0];
                double xMax2 = gameController.enemies2.get(k).xAngles[0];
                double yMin2 = gameController.enemies2.get(k).yAngles[0];
                double yMax2 = gameController.enemies2.get(k).yAngles[0];
                for (int i = 0; i < 3; i++) {
                    if (gameController.enemies2.get(k).xAngles[i] < xMin2) {
                        xMin2 = gameController.enemies2.get(k).xAngles[i];
                    }
                    if (gameController.enemies2.get(k).xAngles[i] > xMax2) {
                        xMax2 = gameController.enemies2.get(k).xAngles[i];
                    }
                    if (gameController.enemies2.get(k).yAngles[i] < yMin2) {
                        yMin2 = gameController.enemies2.get(k).yAngles[i];
                    }
                    if (gameController.enemies2.get(k).yAngles[i] > yMax2) {
                        yMax2 = gameController.enemies2.get(k).yAngles[i];
                    }
                }

                if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                        || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                    boolean angleCollided = false;
                    for (int i = 0; i < 3; i++) {
                        if ((xMin1 <= gameController.enemies2.get(k).xAngles[i]) &&
                                (xMax1 >= gameController.enemies2.get(k).xAngles[i]) &&
                                (yMin1 <= gameController.enemies2.get(k).yAngles[i]) &&
                                (yMax1 >= gameController.enemies2.get(k).yAngles[i])) {
                            angleCollided = true;
                        }
                    }
                    if (angleCollided) {
                        if (gameController.settings.level == 1) {
                            gameController.ball.HP -= 8;
                        }
                        if (gameController.settings.level == 2) {
                            gameController.ball.HP -= 10;
                        }
                        if (gameController.settings.level == 2) {
                            gameController.ball.HP -= 12;
                        }
                        SoundEffects.playSound(Constants.HURT_SOUND_PATH);
                    }
                    if (client.player.isWritOfAstrape()) {
                        gameController.enemies2.get(k).enemyHealth -= 2;
                    }
                    if (gameController.ball.ballCerberus) {
                        gameController.enemies2.get(k).enemyHealth -= 10;
                        gameController.ball.ballCerberus = false;
                    }

                    gameController.enemies2.get(k).dAngle = Math.PI;
                    Impact.turnOnImpact(gameController.ball.x,
                            gameController.ball.y,
                            gameController.enemies2.get(k).x,
                            gameController.enemies2.get(k).y, gameController);

                }
            }

        }
    }

    // =================================================================================

    public static void checkCollisionBulletEnemy1(GameController gameController) {
        if (!gameController.bullets.isEmpty()) {
            for (int j = 0; j < gameController.bullets.size(); j++) {
                if (gameController.bullets.get(j).bulletHealth > 0) {

                    double xMin1 = gameController.bullets.get(j).x;
                    double xMax1 = gameController.bullets.get(j).x + BulletModel.bulletSize;
                    double yMin1 = gameController.bullets.get(j).y;
                    double yMax1 = gameController.bullets.get(j).y + BulletModel.bulletSize;
                    for (int k = 0; k < gameController.enemies1.size(); k++) {
                        if (gameController.enemies1.get(k).enemyHealth > 0) {

                            double xMin2 = gameController.enemies1.get(k).xAngles[0];
                            double xMax2 = gameController.enemies1.get(k).xAngles[0];
                            double yMin2 = gameController.enemies1.get(k).yAngles[0];
                            double yMax2 = gameController.enemies1.get(k).yAngles[0];
                            for (int i = 0; i < 3; i++) {
                                if (gameController.enemies1.get(k).xAngles[i] < xMin2) {
                                    xMin2 = gameController.enemies1.get(k).xAngles[i];
                                }
                                if (gameController.enemies1.get(k).xAngles[i] > xMax2) {
                                    xMax2 = gameController.enemies1.get(k).xAngles[i];
                                }
                                if (gameController.enemies1.get(k).yAngles[i] < yMin2) {
                                    yMin2 = gameController.enemies1.get(k).yAngles[i];
                                }
                                if (gameController.enemies1.get(k).yAngles[i] > yMax2) {
                                    yMax2 = gameController.enemies1.get(k).yAngles[i];
                                }
                            }

                            if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                                gameController.bullets.get(j).bulletHealth = 0;
                                if (gameController.bulletAres) {
                                    gameController.enemies1.get(k).enemyHealth -= 7;
                                }
                                if (!gameController.bulletAres) {
                                    gameController.enemies1.get(k).enemyHealth -= 5;
                                }
                                if (gameController.ball.ballChiron) {
                                    gameController.ball.HP += 3;
                                }
                                if (BulletModel.bulletSlaughter) {
                                    gameController.enemies1.get(k).enemyHealth -= 50;
                                    BulletModel.bulletSlaughter = false;
                                }
                                SoundEffects.playSound(Constants.HURT_SOUND_PATH);
                                if (gameController.enemies1.get(k).enemyHealth <= 0) {
                                    SoundEffects.playSound(Constants.HIT_SOUND_PATH);
                                    gameController.newCollectible(gameController.enemies1.get(k).x, gameController.enemies1.get(k).y, 5);
                                }
                                Impact.turnOnImpact(gameController.bullets.get(j).x + ((double) BulletModel.bulletSize / 2),
                                        gameController.bullets.get(j).y + ((double) BulletModel.bulletSize / 2),
                                        gameController.enemies1.get(k).x + ((double) gameController.enemies1.get(k).enemy1Size / 2),
                                        gameController.enemies1.get(k).y + ((double) gameController.enemies1.get(k).enemy1Size / 2), gameController);

                            }
                        }
                    }
                }
            }
        }
    }

    public static void checkCollisionBulletEnemy2(GameController gameController) {
        if (!gameController.bullets.isEmpty()) {
            for (int j = 0; j < gameController.bullets.size(); j++) {
                if (gameController.bullets.get(j).bulletHealth > 0) {
                    double xMin1 = gameController.bullets.get(j).x;
                    double xMax1 = gameController.bullets.get(j).x + BulletModel.bulletSize;
                    double yMin1 = gameController.bullets.get(j).y;
                    double yMax1 = gameController.bullets.get(j).y + BulletModel.bulletSize;
                    for (int k = 0; k < gameController.enemies2.size(); k++) {
                        if (gameController.enemies2.get(k).enemyHealth > 0) {

                            double xMin2 = gameController.enemies2.get(k).xAngles[0];
                            double xMax2 = gameController.enemies2.get(k).xAngles[0];
                            double yMin2 = gameController.enemies2.get(k).yAngles[0];
                            double yMax2 = gameController.enemies2.get(k).yAngles[0];
                            for (int i = 0; i < 3; i++) {
                                if (gameController.enemies2.get(k).xAngles[i] < xMin2) {
                                    xMin2 = gameController.enemies2.get(k).xAngles[i];
                                }
                                if (gameController.enemies2.get(k).xAngles[i] > xMax2) {
                                    xMax2 = gameController.enemies2.get(k).xAngles[i];
                                }
                                if (gameController.enemies2.get(k).yAngles[i] < yMin2) {
                                    yMin2 = gameController.enemies2.get(k).yAngles[i];
                                }
                                if (gameController.enemies2.get(k).yAngles[i] > yMax2) {
                                    yMax2 = gameController.enemies2.get(k).yAngles[i];
                                }
                            }

                            if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                                gameController.bullets.get(j).bulletHealth = 0;
                                if (gameController.bulletAres) {
                                    gameController.enemies2.get(k).enemyHealth -= 7;
                                }
                                if (!gameController.bulletAres) {
                                    gameController.enemies2.get(k).enemyHealth -= 5;
                                }
                                if (gameController.ball.ballChiron) {
                                    gameController.ball.HP += 3;
                                }
                                if (BulletModel.bulletSlaughter) {
                                    gameController.enemies2.get(k).enemyHealth -= 50;
                                    BulletModel.bulletSlaughter = false;
                                }
                                SoundEffects.playSound(Constants.HURT_SOUND_PATH);
                                if (gameController.enemies2.get(k).enemyHealth <= 0) {
                                    SoundEffects.playSound(Constants.HIT_SOUND_PATH);
                                    gameController.newCollectible(gameController.enemies2.get(k).x, gameController.enemies2.get(k).y, 5);
                                    gameController.newCollectible(gameController.enemies2.get(k).x + Collectible.collectibleSize, gameController.enemies2.get(k).y + Collectible.collectibleSize, 5);
                                }
                                Impact.turnOnImpact(gameController.bullets.get(j).x + ((double) BulletModel.bulletSize / 2),
                                        gameController.bullets.get(j).y + ((double) BulletModel.bulletSize / 2),
                                        gameController.enemies2.get(k).x + ((double) gameController.enemies2.get(k).enemy2Size / 2),
                                        gameController.enemies2.get(k).y + ((double) gameController.enemies2.get(k).enemy2Size / 2), gameController);

                            }
                        }
                    }
                }
            }
        }
    }

    // ===============================================================================


    public static void checkCollisionBallCollectible(GameController gameController, ClientModel client) {
        double xMin1 = gameController.ball.x - BallModel.ballRadius;
        double xMax1 = gameController.ball.x + BallModel.ballRadius;
        double yMin1 = gameController.ball.y - BallModel.ballRadius;
        double yMax1 = gameController.ball.y + BallModel.ballRadius;
        if (gameController.settings.level == 1) {
            xMin1 = gameController.ball.x - ((double) (BallModel.ballRadius * 3) / 2);
            xMax1 = gameController.ball.x + ((double) (BallModel.ballRadius * 3) / 2);
            yMin1 = gameController.ball.y - ((double) (BallModel.ballRadius * 3) / 2);
            yMax1 = gameController.ball.y + ((double) (BallModel.ballRadius * 3) / 2);
        }

        if (!gameController.collectibles.isEmpty()) {
            for (int i = 0; i < gameController.collectibles.size(); i++) {
                if (gameController.collectibles.get(i).collectibleHealth > 0) {
                    double xMin2 = gameController.collectibles.get(i).x;
                    double xMax2 = gameController.collectibles.get(i).x + Collectible.collectibleSize;
                    double yMin2 = gameController.collectibles.get(i).y;
                    double yMax2 = gameController.collectibles.get(i).y + Collectible.collectibleSize;

                    if (gameController.settings.level == 1) {
                        xMin2 = gameController.collectibles.get(i).x - (Collectible.collectibleSize / 2);
                        xMax2 = gameController.collectibles.get(i).x + (Collectible.collectibleSize * 3 / 2);
                        yMin2 = gameController.collectibles.get(i).y - (Collectible.collectibleSize / 2);
                        yMax2 = gameController.collectibles.get(i).y + (Collectible.collectibleSize * 3 / 2);
                    }

                    if (((xMin2 >= xMin1 && xMin2 <= xMax1) && (yMin2 >= yMin1 && yMin2 <= yMax1))
                            || ((xMin2 <= xMin1 && xMax2 >= xMin1) && (yMin2 >= yMin1 && yMin2 <= yMax1))
                            || ((xMin2 >= xMin1 && xMin2 <= xMax1) && (yMax2 >= yMin1 && yMax2 <= yMax1))
                            || ((xMin2 <= xMin1 && xMax2 >= xMin1) && (yMax2 >= yMin1 && yMax2 <= yMax1))) {
                        gameController.collectibles.get(i).collectibleHealth = 0;
                        client.player.setXP(client.player.getXP() + gameController.collectibles.get(i).xp);

                    }
                }

            }
        }
    }


    // ===============================================================================


    // a method for checking intersections between enemy1 and the ballAngle

    public static void checkCollisionBallAngleEnemy1(GameController gameController) {
        if (gameController.ballAngle.angleExists) {

            double xMin1 = gameController.ballAngle.x - BallAngle.ballAngleRadius;
            double xMax1 = gameController.ballAngle.x + BallAngle.ballAngleRadius;
            double yMin1 = gameController.ballAngle.y - BallAngle.ballAngleRadius;
            double yMax1 = gameController.ballAngle.y + BallAngle.ballAngleRadius;
            for (int k = 0; k < gameController.enemies1.size(); k++) {
                if (gameController.enemies1.get(k).enemyHealth > 0) {

                    double xMin2 = gameController.enemies1.get(k).xAngles[0];
                    double xMax2 = gameController.enemies1.get(k).xAngles[0];
                    double yMin2 = gameController.enemies1.get(k).yAngles[0];
                    double yMax2 = gameController.enemies1.get(k).yAngles[0];
                    for (int i = 0; i < 4; i++) {
                        if (gameController.enemies1.get(k).xAngles[i] < xMin2) {
                            xMin2 = gameController.enemies1.get(k).xAngles[i];
                        }
                        if (gameController.enemies1.get(k).xAngles[i] > xMax2) {
                            xMax2 = gameController.enemies1.get(k).xAngles[i];
                        }
                        if (gameController.enemies1.get(k).yAngles[i] < yMin2) {
                            yMin2 = gameController.enemies1.get(k).yAngles[i];
                        }
                        if (gameController.enemies1.get(k).yAngles[i] > yMax2) {
                            yMax2 = gameController.enemies1.get(k).yAngles[i];
                        }
                    }

                    if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                            || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                            || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                            || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {


                        gameController.enemies1.get(k).dash = false;
                        gameController.enemies1.get(k).dAngle = Math.PI;
                        gameController.enemies1.get(k).enemyHealth -= 10;
                        if (gameController.ball.ballChiron) {
                            gameController.ball.HP += 3;
                        }
                        SoundEffects.playSound(Constants.HURT_SOUND_PATH);
                        if (gameController.enemies1.get(k).enemyHealth <= 0) {
                            SoundEffects.playSound(Constants.HIT_SOUND_PATH);
                        }
                        Impact.turnOnImpact(gameController.ball.x,
                                gameController.ball.y,
                                gameController.enemies1.get(k).x,
                                gameController.enemies1.get(k).y, gameController);

                    }
                }
            }
        }
    }


    // a method for checking intersections between enemy2 and the ball

    public static void checkCollisionBallAngleEnemy2(GameController gameController) {
        if (gameController.ballAngle.angleExists) {

            double xMin1 = gameController.ballAngle.x - BallAngle.ballAngleRadius;
            double xMax1 = gameController.ballAngle.x + BallAngle.ballAngleRadius;
            double yMin1 = gameController.ballAngle.y - BallAngle.ballAngleRadius;
            double yMax1 = gameController.ballAngle.y + BallAngle.ballAngleRadius;
            for (int k = 0; k < gameController.enemies2.size(); k++) {
                if (gameController.enemies2.get(k).enemyHealth > 0) {

                    double xMin2 = gameController.enemies2.get(k).xAngles[0];
                    double xMax2 = gameController.enemies2.get(k).xAngles[0];
                    double yMin2 = gameController.enemies2.get(k).yAngles[0];
                    double yMax2 = gameController.enemies2.get(k).yAngles[0];
                    for (int i = 0; i < 3; i++) {
                        if (gameController.enemies2.get(k).xAngles[i] < xMin2) {
                            xMin2 = gameController.enemies2.get(k).xAngles[i];
                        }
                        if (gameController.enemies2.get(k).xAngles[i] > xMax2) {
                            xMax2 = gameController.enemies2.get(k).xAngles[i];
                        }
                        if (gameController.enemies2.get(k).yAngles[i] < yMin2) {
                            yMin2 = gameController.enemies2.get(k).yAngles[i];
                        }
                        if (gameController.enemies2.get(k).yAngles[i] > yMax2) {
                            yMax2 = gameController.enemies2.get(k).yAngles[i];
                        }
                    }

                    if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                            || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                            || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                            || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {


                        gameController.enemies2.get(k).dAngle = Math.PI;
                        gameController.enemies2.get(k).enemyHealth -= 10;
                        if (gameController.ball.ballChiron) {
                            gameController.ball.HP += 3;
                        }
                        SoundEffects.playSound(Constants.HURT_SOUND_PATH);
                        if (gameController.enemies2.get(k).enemyHealth <= 0) {
                            SoundEffects.playSound(Constants.HIT_SOUND_PATH);
                        }
                        Impact.turnOnImpact(gameController.ball.x,
                                gameController.ball.y,
                                gameController.enemies2.get(k).x,
                                gameController.enemies2.get(k).y, gameController);

                    }
                }

            }
        }
    }
}
