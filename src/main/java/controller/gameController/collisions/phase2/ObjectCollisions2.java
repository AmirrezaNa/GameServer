package controller.gameController.collisions.phase2;

import controller.data.SoundEffects;
import controller.gameController.Constants;
import controller.gameController.GameController;
import controller.gameController.Impact;
import controller.gameController.collisions.phase1.ObjectCollision;
import model.ClientModel;
import model.entity.BallAngle;
import model.entity.BallModel;
import model.entity.BulletModel;
import model.entity.enemy.AllEnemies;

public class ObjectCollisions2 {


    public static void checkCollisionsPhase2(GameController gameController, ClientModel client) {
        checkBulletCollisions(gameController);
        checkBallCollisions(gameController, client);
        checkBallAngleCollisions(gameController);
        checkEnemyCollisions(gameController);


    }

    public static void checkBulletCollisions(GameController gameController) {
        // bullets collisions
        checkCollisionBallEnemyBullet(gameController);
        checkBulletOmenoctCollision(gameController);
        checkBulletNecropickCollision(gameController);
        checkBulletWyrmCollision(gameController);
        checkBulletArchmireCollision(gameController);
        checkBulletBarricados1Collision(gameController);
        checkBulletBarricados2Collision(gameController);
        checkBulletBlackOrbCollision(gameController);

    }

    public static void checkBallCollisions(GameController gameController, ClientModel client) {
        //ball collisions
        checkCollisionBallOmenoct(gameController, client);
        checkCollisionBallNecropick(gameController, client);
        checkCollisionBallArchmire(gameController, client);
        checkCollisionBallArchmirePoints(gameController);
        checkCollisionBallWyrm(gameController, client);
        checkCollisionBallBarricados1(gameController);
        checkCollisionBallBarricados2(gameController);
        checkCollisionBallBlackOrb(gameController, client);
        ObjectCollision.checkCollisionBallCollectible(gameController, client);
    }

    public static void checkBallAngleCollisions(GameController gameController) {
        //ball-angle collisions
        checkCollisionBallAngleNecropick(gameController);
        checkCollisionBallAngleOmenoct(gameController);
    }

    public static void checkEnemyCollisions(GameController gameController) {
        //enemies collisions
        checkCollisionNecropickOmenoct(gameController);
        checkCollisionWyrmOmenoct(gameController);
        checkCollisionBarricados1Omenoct(gameController);
        checkCollisionBarricados2Omenoct(gameController);
        checkCollisionNecropickWyrm(gameController);
        checkCollisionBarricados1Necropick(gameController);
        checkCollisionBarricados2Necropick(gameController);
        checkCollisionBarricados1Wyrm(gameController);
        checkCollisionBarricados2Wyrm(gameController);
    }


    // =========================     bullets collisions    ==========================


    public static void checkCollisionBallEnemyBullet(GameController gameController) {
        double xMin2 = gameController.ball.x - BallModel.ballRadius;
        double xMax2 = gameController.ball.x + BallModel.ballRadius;
        double yMin2 = gameController.ball.y - BallModel.ballRadius;
        double yMax2 = gameController.ball.y + BallModel.ballRadius;
        int size = Math.min(gameController.enemyBullets.size(), 20);

        if (!gameController.enemyBullets.isEmpty()) {
            for (int j = 0; j < size; j++) {
                if (gameController.enemyBullets.get(j).bulletHealth > 0) {

                    double xMin1 = gameController.enemyBullets.get(j).x;
                    double xMax1 = gameController.enemyBullets.get(j).x + BulletModel.bulletSize;
                    double yMin1 = gameController.enemyBullets.get(j).y;
                    double yMax1 = gameController.enemyBullets.get(j).y + BulletModel.bulletSize;


                    if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                            || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                            || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                            || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                        gameController.enemyBullets.get(j).bulletHealth = 0;
                        if (gameController.settings.level == 1) {
                            gameController.ball.HP -= 4;
                        }
                        if (gameController.settings.level == 2) {
                            gameController.ball.HP -= 5;
                        }
                        if (gameController.settings.level == 3) {
                            gameController.ball.HP -= 6;
                        }

                        SoundEffects.playSound(Constants.HURT_SOUND_PATH);
                        Impact.turnOnImpact(gameController.enemyBullets.get(j).x + ((double) BulletModel.bulletSize / 2),
                                gameController.enemyBullets.get(j).y + ((double) BulletModel.bulletSize / 2),
                                gameController.ball.x + ((double) BallModel.ballRadius / 2),
                                gameController.ball.y + ((double) BallModel.ballRadius / 2), gameController);

                    }
                }
            }
        }
    }


    public static void checkBulletOmenoctCollision(GameController gameController) {
        if (!gameController.bullets.isEmpty()) {
            int size = Math.min(gameController.bullets.size(), 15);
            for (int j = 0; j < size; j++) {
                if (gameController.bullets.get(j).bulletHealth > 0) {

                    double xMin1 = gameController.bullets.get(j).x;
                    double xMax1 = gameController.bullets.get(j).x + BulletModel.bulletSize;
                    double yMin1 = gameController.bullets.get(j).y;
                    double yMax1 = gameController.bullets.get(j).y + BulletModel.bulletSize;
                    for (int k = 0; k < gameController.omenoctEnemies.size(); k++) {
                        if (gameController.omenoctEnemies.get(k).enemyHealth > 0) {

                            double xMin2 = gameController.omenoctEnemies.get(k).x - AllEnemies.OmenoctModel.distanceToCenter;
                            double xMax2 = gameController.omenoctEnemies.get(k).x + AllEnemies.OmenoctModel.distanceToCenter;
                            double yMin2 = gameController.omenoctEnemies.get(k).y;
                            double yMax2 = gameController.omenoctEnemies.get(k).y + (2* AllEnemies.OmenoctModel.distanceToCenter);

                            if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                                gameController.bullets.get(j).bulletHealth = 0;
                                if (gameController.bulletAres) {
                                    gameController.omenoctEnemies.get(k).enemyHealth -= 7;
                                }
                                if (!gameController.bulletAres) {
                                    gameController.omenoctEnemies.get(k).enemyHealth -= 5;
                                }
                                if (gameController.ball.ballChiron) {
                                    gameController.ball.HP += 3;
                                }
                                if (BulletModel.bulletSlaughter) {
                                    gameController.omenoctEnemies.get(k).enemyHealth -= 50;
                                    BulletModel.bulletSlaughter = false;
                                }
                                SoundEffects.playSound(Constants.HURT_SOUND_PATH);
                                if (gameController.omenoctEnemies.get(k).enemyHealth <= 0) {
                                    SoundEffects.playSound(Constants.HIT_SOUND_PATH);
                                    gameController.newCollectible(gameController.omenoctEnemies.get(k).x, gameController.omenoctEnemies.get(k).y, 4);
                                    gameController.newCollectible(gameController.omenoctEnemies.get(k).x+2, gameController.omenoctEnemies.get(k).y+1, 4);
                                    gameController.newCollectible(gameController.omenoctEnemies.get(k).x+4, gameController.omenoctEnemies.get(k).y-1, 4);
                                    gameController.newCollectible(gameController.omenoctEnemies.get(k).x+5, gameController.omenoctEnemies.get(k).y, 4);
                                    gameController.newCollectible(gameController.omenoctEnemies.get(k).x, gameController.omenoctEnemies.get(k).y+2, 4);
                                    gameController.newCollectible(gameController.omenoctEnemies.get(k).x+1, gameController.omenoctEnemies.get(k).y+3, 4);
                                    gameController.newCollectible(gameController.omenoctEnemies.get(k).x+2, gameController.omenoctEnemies.get(k).y+4, 4);
                                    gameController.newCollectible(gameController.omenoctEnemies.get(k).x, gameController.omenoctEnemies.get(k).y+3, 4);

                                }
                                Impact.turnOnImpact(gameController.bullets.get(j).x + ((double) BulletModel.bulletSize / 2),
                                        gameController.bullets.get(j).y + ((double) BulletModel.bulletSize / 2),
                                        gameController.omenoctEnemies.get(k).x + ((double) AllEnemies.OmenoctModel.omenoctSize),
                                        gameController.omenoctEnemies.get(k).y + ((double) AllEnemies.OmenoctModel.omenoctSize), gameController);

                            }
                        }
                    }
                }
            }
        }
    }


    public static void checkBulletNecropickCollision(GameController gameController) {
        if (!gameController.bullets.isEmpty()) {
            int size = Math.min(gameController.bullets.size(), 15);
            for (int j = 0; j < size; j++) {
                if (gameController.bullets.get(j).bulletHealth > 0) {

                    double xMin1 = gameController.bullets.get(j).x;
                    double xMax1 = gameController.bullets.get(j).x + BulletModel.bulletSize;
                    double yMin1 = gameController.bullets.get(j).y;
                    double yMax1 = gameController.bullets.get(j).y + BulletModel.bulletSize;
                    for (int k = 0; k < gameController.necropickEnemies.size(); k++) {
                        if (gameController.necropickEnemies.get(k).enemyHealth > 0) {

                            double xMin2 = gameController.necropickEnemies.get(k).x;
                            double xMax2 = gameController.necropickEnemies.get(k).x + AllEnemies.NecropickModel.necropickSize;
                            double yMin2 = gameController.necropickEnemies.get(k).y;
                            double yMax2 = gameController.necropickEnemies.get(k).y + AllEnemies.NecropickModel.necropickSize;

                            if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                                gameController.bullets.get(j).bulletHealth = 0;
                                if (gameController.bulletAres) {
                                    gameController.necropickEnemies.get(k).enemyHealth -= 7;
                                }
                                if (!gameController.bulletAres) {
                                    gameController.necropickEnemies.get(k).enemyHealth -= 5;
                                }
                                if (gameController.ball.ballChiron) {
                                    gameController.ball.HP += 3;
                                }
                                if (BulletModel.bulletSlaughter) {
                                    gameController.necropickEnemies.get(k).enemyHealth -= 50;
                                    BulletModel.bulletSlaughter = false;
                                }
                                SoundEffects.playSound(Constants.HURT_SOUND_PATH);
                                if (gameController.necropickEnemies.get(k).enemyHealth <= 0) {
                                    SoundEffects.playSound(Constants.HIT_SOUND_PATH);
                                    gameController.newCollectible(gameController.necropickEnemies.get(k).x, gameController.necropickEnemies.get(k).y, 2);
                                    gameController.newCollectible(gameController.necropickEnemies.get(k).x+1, gameController.necropickEnemies.get(k).y-1, 2);
                                    gameController.newCollectible(gameController.necropickEnemies.get(k).x+1, gameController.necropickEnemies.get(k).y-1, 2);
                                    gameController.newCollectible(gameController.necropickEnemies.get(k).x+2, gameController.necropickEnemies.get(k).y, 2);
                                }
                                Impact.turnOnImpact(gameController.bullets.get(j).x + ((double) BulletModel.bulletSize / 2),
                                        gameController.bullets.get(j).y + ((double) BulletModel.bulletSize / 2),
                                        gameController.necropickEnemies.get(k).x + ((double) AllEnemies.NecropickModel.necropickSize),
                                        gameController.necropickEnemies.get(k).y + ((double) AllEnemies.NecropickModel.necropickSize), gameController);

                            }
                        }
                    }
                }
            }
        }
    }


    public static void checkBulletArchmireCollision(GameController gameController) {
        if (!gameController.bullets.isEmpty()) {
            int size = Math.min(gameController.bullets.size(), 15);
            for (int j = 0; j < size; j++) {
                if (gameController.bullets.get(j).bulletHealth > 0) {

                    double xMin1 = gameController.bullets.get(j).x;
                    double xMax1 = gameController.bullets.get(j).x + BulletModel.bulletSize;
                    double yMin1 = gameController.bullets.get(j).y;
                    double yMax1 = gameController.bullets.get(j).y + BulletModel.bulletSize;
                    for (int k = 0; k < gameController.archmireEnemies.size(); k++) {
                        if (gameController.archmireEnemies.get(k).enemyHealth > 0) {

                            double xMin2 = gameController.archmireEnemies.get(k).x;
                            double xMax2 = gameController.archmireEnemies.get(k).x + AllEnemies.ArchmireModel.archmireSize;
                            double yMin2 = gameController.archmireEnemies.get(k).y;
                            double yMax2 = gameController.archmireEnemies.get(k).y + AllEnemies.ArchmireModel.archmireSize;

                            if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                                gameController.bullets.get(j).bulletHealth = 0;
                                if (gameController.bulletAres) {
                                    gameController.archmireEnemies.get(k).enemyHealth -= 7;
                                }
                                if (!gameController.bulletAres) {
                                    gameController.archmireEnemies.get(k).enemyHealth -= 5;
                                }
                                if (gameController.ball.ballChiron) {
                                    gameController.ball.HP += 3;
                                }
                                if (BulletModel.bulletSlaughter) {
                                    gameController.archmireEnemies.get(k).enemyHealth -= 50;
                                    BulletModel.bulletSlaughter = false;
                                }
                                SoundEffects.playSound(Constants.HURT_SOUND_PATH);
                                if (gameController.archmireEnemies.get(k).enemyHealth <= 0) {
                                    SoundEffects.playSound(Constants.HIT_SOUND_PATH);
                                    gameController.newCollectible(gameController.archmireEnemies.get(k).x, gameController.archmireEnemies.get(k).y, 6);
                                    gameController.newCollectible(gameController.archmireEnemies.get(k).x+1, gameController.archmireEnemies.get(k).y+1, 6);
                                    gameController.newCollectible(gameController.archmireEnemies.get(k).x+2, gameController.archmireEnemies.get(k).y+1, 6);
                                    gameController.newCollectible(gameController.archmireEnemies.get(k).x+2, gameController.archmireEnemies.get(k).y+2, 6);
                                    gameController.newCollectible(gameController.archmireEnemies.get(k).x, gameController.archmireEnemies.get(k).y+2, 6);
                                }


                            }
                        }
                    }
                }
            }
        }
    }


    public static void checkBulletWyrmCollision(GameController gameController) {
        if (!gameController.bullets.isEmpty()) {
            int size = Math.min(gameController.bullets.size(), 15);
            for (int j = 0; j < size; j++) {
                if (gameController.bullets.get(j).bulletHealth > 0) {

                    double xMin1 = gameController.bullets.get(j).x;
                    double xMax1 = gameController.bullets.get(j).x + BulletModel.bulletSize;
                    double yMin1 = gameController.bullets.get(j).y;
                    double yMax1 = gameController.bullets.get(j).y + BulletModel.bulletSize;
                    for (int k = 0; k < gameController.wyrmEnemies.size(); k++) {
                        if (gameController.wyrmEnemies.get(k).enemyHealth > 0) {

                            double xMin2 = gameController.wyrmEnemies.get(k).x;
                            double xMax2 = gameController.wyrmEnemies.get(k).x + AllEnemies.WyrmModel.wyrmSize;
                            double yMin2 = gameController.wyrmEnemies.get(k).y;
                            double yMax2 = gameController.wyrmEnemies.get(k).y + AllEnemies.WyrmModel.wyrmSize;

                            if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                                gameController.bullets.get(j).bulletHealth = 0;
                                if (gameController.bulletAres) {
                                    gameController.wyrmEnemies.get(k).enemyHealth -= 7;
                                }
                                if (!gameController.bulletAres) {
                                    gameController.wyrmEnemies.get(k).enemyHealth -= 5;
                                }
                                if (gameController.ball.ballChiron) {
                                    gameController.ball.HP += 3;
                                }
                                if (BulletModel.bulletSlaughter) {
                                    gameController.wyrmEnemies.get(k).enemyHealth -= 50;
                                    BulletModel.bulletSlaughter = false;
                                }
                                SoundEffects.playSound(Constants.HURT_SOUND_PATH);
                                if (gameController.wyrmEnemies.get(k).enemyHealth <= 0) {
                                    SoundEffects.playSound(Constants.HIT_SOUND_PATH);
                                    gameController.newCollectible(gameController.wyrmEnemies.get(k).x, gameController.wyrmEnemies.get(k).y, 8);
                                    gameController.newCollectible(gameController.wyrmEnemies.get(k).x+4, gameController.wyrmEnemies.get(k).y, 8);
                                }
                                Impact.turnOnImpact(gameController.bullets.get(j).x + ((double) BulletModel.bulletSize / 2),
                                        gameController.bullets.get(j).y + ((double) BulletModel.bulletSize / 2),
                                        gameController.wyrmEnemies.get(k).x + ((double) AllEnemies.WyrmModel.wyrmSize / 2),
                                        gameController.wyrmEnemies.get(k).y + ((double) AllEnemies.WyrmModel.wyrmSize / 2), gameController);

                            }
                        }
                    }
                }
            }
        }
    }


    public static void checkBulletBarricados1Collision(GameController gameController) {
        if (!gameController.bullets.isEmpty()) {
            int size = Math.min(gameController.bullets.size(), 15);
            for (int j = 0; j < size; j++) {
                if (gameController.bullets.get(j).bulletHealth > 0) {

                    double xMin1 = gameController.bullets.get(j).x;
                    double xMax1 = gameController.bullets.get(j).x + BulletModel.bulletSize;
                    double yMin1 = gameController.bullets.get(j).y;
                    double yMax1 = gameController.bullets.get(j).y + BulletModel.bulletSize;
                    for (int k = 0; k < gameController.barricadosEnemies1.size(); k++) {
                        if (gameController.barricadosEnemies1.get(k).enemyTimer > 0) {

                            double xMin2 = gameController.barricadosEnemies1.get(k).x;
                            double xMax2 = gameController.barricadosEnemies1.get(k).x + AllEnemies.BarricadosModel1.barricadosSize;
                            double yMin2 = gameController.barricadosEnemies1.get(k).y;
                            double yMax2 = gameController.barricadosEnemies1.get(k).y + AllEnemies.BarricadosModel1.barricadosSize;

                            if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                                gameController.bullets.get(j).bulletHealth = 0;

                                Impact.turnOnImpact(gameController.bullets.get(j).x + ((double) BulletModel.bulletSize / 2),
                                        gameController.bullets.get(j).y + ((double) BulletModel.bulletSize / 2),
                                        gameController.barricadosEnemies1.get(k).x + ((double) AllEnemies.BarricadosModel1.barricadosSize / 2),
                                        gameController.barricadosEnemies1.get(k).y + ((double) AllEnemies.BarricadosModel1.barricadosSize / 2), gameController);

                            }
                        }
                    }
                }
            }
        }
    }


    public static void checkBulletBarricados2Collision(GameController gameController) {
        if (!gameController.bullets.isEmpty()) {
            int size = Math.min(gameController.bullets.size(), 15);
            for (int j = 0; j < size; j++) {
                if (gameController.bullets.get(j).bulletHealth > 0) {

                    double xMin1 = gameController.bullets.get(j).x;
                    double xMax1 = gameController.bullets.get(j).x + BulletModel.bulletSize;
                    double yMin1 = gameController.bullets.get(j).y;
                    double yMax1 = gameController.bullets.get(j).y + BulletModel.bulletSize;
                    for (int k = 0; k < gameController.barricadosEnemies2.size(); k++) {
                        if (gameController.barricadosEnemies2.get(k).enemyTimer > 0) {

                            double xMin2 = gameController.barricadosEnemies2.get(k).x;
                            double xMax2 = gameController.barricadosEnemies2.get(k).x + AllEnemies.BarricadosModel2.barricadosSize;
                            double yMin2 = gameController.barricadosEnemies2.get(k).y;
                            double yMax2 = gameController.barricadosEnemies2.get(k).y + AllEnemies.BarricadosModel2.barricadosSize;

                            if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                                gameController.bullets.get(j).bulletHealth = 0;

                                Impact.turnOnImpact(gameController.bullets.get(j).x + ((double) BulletModel.bulletSize / 2),
                                        gameController.bullets.get(j).y + ((double) BulletModel.bulletSize / 2),
                                        gameController.barricadosEnemies2.get(k).x + ((double) AllEnemies.BarricadosModel2.barricadosSize / 2),
                                        gameController.barricadosEnemies2.get(k).y + ((double) AllEnemies.BarricadosModel2.barricadosSize / 2), gameController);

                            }
                        }
                    }
                }
            }
        }
    }


    public static void checkBulletBlackOrbCollision(GameController gameController) {
        if (!gameController.bullets.isEmpty()) {
            int size = Math.min(gameController.bullets.size(), 15);
            for (int j = 0; j < size; j++) {
                if (gameController.bullets.get(j).bulletHealth > 0) {

                    double xMin1 = gameController.bullets.get(j).x;
                    double xMax1 = gameController.bullets.get(j).x + BulletModel.bulletSize;
                    double yMin1 = gameController.bullets.get(j).y;
                    double yMax1 = gameController.bullets.get(j).y + BulletModel.bulletSize;
                    for (int k = 0; k < gameController.blackOrbEnemies.size(); k++) {
                        if (gameController.blackOrbEnemies.get(k).enemyHealth > 0) {

                            double xMin2 = gameController.blackOrbEnemies.get(k).x;
                            double xMax2 = gameController.blackOrbEnemies.get(k).x + AllEnemies.BlackOrbModel.blackOrbSize;
                            double yMin2 = gameController.blackOrbEnemies.get(k).y;
                            double yMax2 = gameController.blackOrbEnemies.get(k).y + AllEnemies.BlackOrbModel.blackOrbSize;

                            if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                                gameController.bullets.get(j).bulletHealth = 0;
                                if (gameController.bulletAres) {
                                    gameController.blackOrbEnemies.get(k).enemyHealth -= 7;
                                }
                                if (!gameController.bulletAres) {
                                    gameController.blackOrbEnemies.get(k).enemyHealth -= 5;
                                }
                                if (gameController.ball.ballChiron) {
                                    gameController.ball.HP += 3;
                                }
                                if (BulletModel.bulletSlaughter) {
                                    gameController.blackOrbEnemies.get(k).enemyHealth -= 50;
                                    BulletModel.bulletSlaughter = false;
                                }
                                SoundEffects.playSound(Constants.HURT_SOUND_PATH);
                                if (gameController.blackOrbEnemies.get(k).enemyHealth <= 0) {
                                    SoundEffects.playSound(Constants.HIT_SOUND_PATH);
                                    gameController.newCollectible(gameController.blackOrbEnemies.get(k).x, gameController.blackOrbEnemies.get(k).y, 30);
                                    gameController.newCollectible(gameController.blackOrbEnemies.get(k).x+2, gameController.blackOrbEnemies.get(k).y-2, 30);
                                    gameController.newCollectible(gameController.blackOrbEnemies.get(k).x+4, gameController.blackOrbEnemies.get(k).y, 30);
                                    gameController.newCollectible(gameController.blackOrbEnemies.get(k).x+4, gameController.blackOrbEnemies.get(k).y+3, 30);
                                    gameController.newCollectible(gameController.blackOrbEnemies.get(k).x, gameController.blackOrbEnemies.get(k).y+2, 30);
                                }


                                Impact.turnOnImpact(gameController.bullets.get(j).x + ((double) BulletModel.bulletSize / 2),
                                        gameController.bullets.get(j).y + ((double) BulletModel.bulletSize / 2),
                                        gameController.blackOrbEnemies.get(k).x + ((double) AllEnemies.BlackOrbModel.blackOrbSize / 2),
                                        gameController.blackOrbEnemies.get(k).y + ((double) AllEnemies.BlackOrbModel.blackOrbSize / 2), gameController);

                            }
                        }
                    }
                }
            }
        }
    }


    // =========================     ball collisions    ==========================


    public static void checkCollisionBallOmenoct(GameController gameController, ClientModel client) {
        double xMin1 = gameController.ball.x - BallModel.ballRadius;
        double xMax1 = gameController.ball.x + BallModel.ballRadius;
        double yMin1 = gameController.ball.y - BallModel.ballRadius;
        double yMax1 = gameController.ball.y + BallModel.ballRadius;
        for (int k = 0; k < gameController.omenoctEnemies.size(); k++) {
            if (gameController.omenoctEnemies.get(k).enemyHealth > 0) {

                double xMin2 = gameController.omenoctEnemies.get(k).x - AllEnemies.OmenoctModel.distanceToCenter;
                double xMax2 = gameController.omenoctEnemies.get(k).x + AllEnemies.OmenoctModel.distanceToCenter;
                double yMin2 = gameController.omenoctEnemies.get(k).y;
                double yMax2 = gameController.omenoctEnemies.get(k).y + (2* AllEnemies.OmenoctModel.distanceToCenter);

                if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                        || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {
                    if (gameController.settings.level == 1) {
                        gameController.ball.HP -= 6;
                    }
                    if (gameController.settings.level == 2) {
                        gameController.ball.HP -= 8;
                    }
                    if (gameController.settings.level == 3) {
                        gameController.ball.HP -= 10;
                    }
                    if (client.player.isWritOfAstrape()) {
                        gameController.omenoctEnemies.get(k).enemyHealth -= 2;
                        if (gameController.ball.ballChiron) {
                            gameController.ball.HP += 3;
                        }
                    }
                    if (gameController.ball.ballCerberus) {
                        gameController.omenoctEnemies.get(k).enemyHealth -= 10;
                        gameController.ball.ballCerberus = false;
                        if (gameController.ball.ballChiron) {
                            gameController.ball.HP += 3;
                        }
                    }
                    if (gameController.omenoctEnemies.get(k).enemyHealth <= 0) {
                        SoundEffects.playSound(Constants.HIT_SOUND_PATH);
                        gameController.newCollectible(gameController.omenoctEnemies.get(k).x, gameController.omenoctEnemies.get(k).y, 4);
                        gameController.newCollectible(gameController.omenoctEnemies.get(k).x+2, gameController.omenoctEnemies.get(k).y+1, 4);
                        gameController.newCollectible(gameController.omenoctEnemies.get(k).x+4, gameController.omenoctEnemies.get(k).y-1, 4);
                        gameController.newCollectible(gameController.omenoctEnemies.get(k).x+5, gameController.omenoctEnemies.get(k).y, 4);
                        gameController.newCollectible(gameController.omenoctEnemies.get(k).x, gameController.omenoctEnemies.get(k).y+2, 4);
                        gameController.newCollectible(gameController.omenoctEnemies.get(k).x+1, gameController.omenoctEnemies.get(k).y+3, 4);
                        gameController.newCollectible(gameController.omenoctEnemies.get(k).x+2, gameController.omenoctEnemies.get(k).y+4, 4);
                        gameController.newCollectible(gameController.omenoctEnemies.get(k).x, gameController.omenoctEnemies.get(k).y+3, 4);

                    }

                    SoundEffects.playSound(Constants.HURT_SOUND_PATH);

                    Impact.turnOnImpact(gameController.ball.x,
                            gameController.ball.y,
                            gameController.omenoctEnemies.get(k).x,
                            gameController.omenoctEnemies.get(k).y, gameController);

                }
            }
        }
    }


    public static void checkCollisionBallNecropick(GameController gameController, ClientModel client) {
        double xMin1 = gameController.ball.x - BallModel.ballRadius;
        double xMax1 = gameController.ball.x + BallModel.ballRadius;
        double yMin1 = gameController.ball.y - BallModel.ballRadius;
        double yMax1 = gameController.ball.y + BallModel.ballRadius;
        for (int k = 0; k < gameController.necropickEnemies.size(); k++) {
            if (gameController.necropickEnemies.get(k).enemyHealth > 0 && !gameController.necropickEnemies.get(k).hide) {

                double xMin2 = gameController.necropickEnemies.get(k).x;
                double xMax2 = gameController.necropickEnemies.get(k).x + AllEnemies.NecropickModel.necropickSize;
                double yMin2 = gameController.necropickEnemies.get(k).y;
                double yMax2 = gameController.necropickEnemies.get(k).y + AllEnemies.NecropickModel.necropickSize;

                if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                        || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                    if (client.player.isWritOfAstrape()) {
                        gameController.necropickEnemies.get(k).enemyHealth -= 2;
                        if (gameController.ball.ballChiron) {
                            gameController.ball.HP += 3;
                        }
                    }
                    if (gameController.ball.ballCerberus) {
                        gameController.necropickEnemies.get(k).enemyHealth -= 10;
                        gameController.ball.ballCerberus = false;
                        if (gameController.ball.ballChiron) {
                            gameController.ball.HP += 3;
                        }
                    }
                    if (gameController.necropickEnemies.get(k).enemyHealth <= 0) {
                        SoundEffects.playSound(Constants.HIT_SOUND_PATH);
                        gameController.newCollectible(gameController.necropickEnemies.get(k).x, gameController.necropickEnemies.get(k).y, 2);
                        gameController.newCollectible(gameController.necropickEnemies.get(k).x+1, gameController.necropickEnemies.get(k).y-1, 2);
                        gameController.newCollectible(gameController.necropickEnemies.get(k).x+1, gameController.necropickEnemies.get(k).y-1, 2);
                        gameController.newCollectible(gameController.necropickEnemies.get(k).x+2, gameController.necropickEnemies.get(k).y, 2);
                    }

                    Impact.turnOnImpact(gameController.ball.x,
                            gameController.ball.y,
                            gameController.necropickEnemies.get(k).x,
                            gameController.necropickEnemies.get(k).y, gameController);

                }
            }
        }
    }


    public static void checkCollisionBallArchmire(GameController gameController, ClientModel client) {
        double xMin1 = gameController.ball.x - BallModel.ballRadius;
        double xMax1 = gameController.ball.x + BallModel.ballRadius;
        double yMin1 = gameController.ball.y - BallModel.ballRadius;
        double yMax1 = gameController.ball.y + BallModel.ballRadius;
        for (int k = 0; k < gameController.archmireEnemies.size(); k++) {
            if (gameController.archmireEnemies.get(k).enemyHealth > 0) {

                double xMin2 = gameController.archmireEnemies.get(k).x;
                double xMax2 = gameController.archmireEnemies.get(k).x + AllEnemies.ArchmireModel.archmireSize;
                double yMin2 = gameController.archmireEnemies.get(k).y;
                double yMax2 = gameController.archmireEnemies.get(k).y + AllEnemies.ArchmireModel.archmireSize;

                if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                        || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                    if (client.player.isWritOfAstrape()) {
                        gameController.archmireEnemies.get(k).enemyHealth -= 2;
                        if (gameController.ball.ballChiron) {
                            gameController.ball.HP += 3;
                        }
                    }
                    if (gameController.ball.ballCerberus) {
                        gameController.archmireEnemies.get(k).enemyHealth -= 10;
                        gameController.ball.ballCerberus = false;
                        if (gameController.ball.ballChiron) {
                            gameController.ball.HP += 3;
                        }
                    }
                    if (gameController.archmireEnemies.get(k).enemyHealth <= 0) {
                        SoundEffects.playSound(Constants.HIT_SOUND_PATH);
                        gameController.newCollectible(gameController.archmireEnemies.get(k).x, gameController.archmireEnemies.get(k).y, 6);
                        gameController.newCollectible(gameController.archmireEnemies.get(k).x+1, gameController.archmireEnemies.get(k).y+1, 6);
                        gameController.newCollectible(gameController.archmireEnemies.get(k).x+2, gameController.archmireEnemies.get(k).y+1, 6);
                        gameController.newCollectible(gameController.archmireEnemies.get(k).x+2, gameController.archmireEnemies.get(k).y+2, 6);
                        gameController.newCollectible(gameController.archmireEnemies.get(k).x, gameController.archmireEnemies.get(k).y+2, 6);
                    }
                    gameController.ball.ballInArchmire = true;

                } else {
                    gameController.ball.ballInArchmire = false;
                }
            }
        }
    }


    public static void checkCollisionBallArchmirePoints(GameController gameController) {
        if (!gameController.archmirePoints.isEmpty()) {
            double xMin1 = gameController.ball.x - BallModel.ballRadius;
            double xMax1 = gameController.ball.x + BallModel.ballRadius;
            double yMin1 = gameController.ball.y - BallModel.ballRadius;
            double yMax1 = gameController.ball.y + BallModel.ballRadius;
            int size;
            if (gameController.archmirePoints.size() > 20) {
                size = 20;
            } else {
                size = gameController.archmirePoints.size();
            }
            for (int k = 0; k < size; k++) {
                if (gameController.archmirePoints.get(k).archmirePointTimer > 0) {

                    double xMin2 = gameController.archmirePoints.get(k).x;
                    double xMax2 = gameController.archmirePoints.get(k).x + AllEnemies.ArchmireModel.archmireSize;
                    double yMin2 = gameController.archmirePoints.get(k).y;
                    double yMax2 = gameController.archmirePoints.get(k).y + AllEnemies.ArchmireModel.archmireSize;

                    if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                            || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                            || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                            || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                        gameController.ball.ballInArchmireTrace = true;

                    } else {
                        gameController.ball.ballInArchmireTrace = false;
                    }
                }
            }
        }
    }

    public static void checkCollisionBallWyrm(GameController gameController, ClientModel client) {
        double xMin1 = gameController.ball.x - BallModel.ballRadius;
        double xMax1 = gameController.ball.x + BallModel.ballRadius;
        double yMin1 = gameController.ball.y - BallModel.ballRadius;
        double yMax1 = gameController.ball.y + BallModel.ballRadius;
        for (int k = 0; k < gameController.wyrmEnemies.size(); k++) {
            if (gameController.wyrmEnemies.get(k).enemyHealth > 0) {

                double xMin2 = gameController.wyrmEnemies.get(k).x;
                double xMax2 = gameController.wyrmEnemies.get(k).x + AllEnemies.WyrmModel.wyrmSize;
                double yMin2 = gameController.wyrmEnemies.get(k).y;
                double yMax2 = gameController.wyrmEnemies.get(k).y + AllEnemies.WyrmModel.wyrmSize;

                if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                        || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                    if (client.player.isWritOfAstrape()) {
                        gameController.wyrmEnemies.get(k).enemyHealth -= 2;
                        if (gameController.ball.ballChiron) {
                            gameController.ball.HP += 3;
                        }
                    }
                    if (gameController.ball.ballCerberus) {
                        gameController.wyrmEnemies.get(k).enemyHealth -= 10;
                        if (gameController.ball.ballChiron) {
                            gameController.ball.HP += 3;
                        }
                        gameController.ball.ballCerberus = false;
                    }
                    if (gameController.wyrmEnemies.get(k).enemyHealth <= 0) {
                        SoundEffects.playSound(Constants.HIT_SOUND_PATH);
                        gameController.newCollectible(gameController.wyrmEnemies.get(k).x, gameController.wyrmEnemies.get(k).y, 8);
                        gameController.newCollectible(gameController.wyrmEnemies.get(k).x+4, gameController.wyrmEnemies.get(k).y, 8);
                    }

                    Impact.turnOnImpact(gameController.ball.x,
                            gameController.ball.y,
                            gameController.wyrmEnemies.get(k).x + ((double) AllEnemies.WyrmModel.wyrmSize / 2),
                            gameController.wyrmEnemies.get(k).y + ((double) AllEnemies.WyrmModel.wyrmSize / 2), gameController);


                }
            }
        }
    }

    public static void checkCollisionBallBarricados1(GameController gameController) {
        double xMin1 = gameController.ball.x - BallModel.ballRadius;
        double xMax1 = gameController.ball.x + BallModel.ballRadius;
        double yMin1 = gameController.ball.y - BallModel.ballRadius;
        double yMax1 = gameController.ball.y + BallModel.ballRadius;
        if (!gameController.barricadosEnemies1.isEmpty()) {
            for (int k = 0; k < gameController.barricadosEnemies1.size(); k++) {
                if (gameController.barricadosEnemies1.get(k).enemyTimer > 0) {

                    double xMin2 = gameController.barricadosEnemies1.get(k).x;
                    double xMax2 = gameController.barricadosEnemies1.get(k).x + AllEnemies.BarricadosModel1.barricadosSize;
                    double yMin2 = gameController.barricadosEnemies1.get(k).y;
                    double yMax2 = gameController.barricadosEnemies1.get(k).y + AllEnemies.BarricadosModel1.barricadosSize;

                    if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                            || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                            || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                            || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                        Impact.turnOnImpact(gameController.ball.x,
                                gameController.ball.y,
                                gameController.barricadosEnemies1.get(k).x + ((double) AllEnemies.BarricadosModel1.barricadosSize / 2),
                                gameController.barricadosEnemies1.get(k).y + ((double) AllEnemies.BarricadosModel1.barricadosSize / 2), gameController);


                    }
                }
            }
        }
    }


    public static void checkCollisionBallBarricados2(GameController gameController) {
        double xMin1 = gameController.ball.x - BallModel.ballRadius;
        double xMax1 = gameController.ball.x + BallModel.ballRadius;
        double yMin1 = gameController.ball.y - BallModel.ballRadius;
        double yMax1 = gameController.ball.y + BallModel.ballRadius;
        for (int k = 0; k < gameController.barricadosEnemies2.size(); k++) {
            if (gameController.barricadosEnemies2.get(k).enemyTimer > 0) {

                double xMin2 = gameController.barricadosEnemies2.get(k).x;
                double xMax2 = gameController.barricadosEnemies2.get(k).x + AllEnemies.BarricadosModel2.barricadosSize;
                double yMin2 = gameController.barricadosEnemies2.get(k).y;
                double yMax2 = gameController.barricadosEnemies2.get(k).y + AllEnemies.BarricadosModel2.barricadosSize;

                if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                        || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                    Impact.turnOnImpact(gameController.ball.x,
                            gameController.ball.y,
                            gameController.barricadosEnemies2.get(k).x + ((double) AllEnemies.BarricadosModel2.barricadosSize / 2),
                            gameController.barricadosEnemies2.get(k).y + ((double) AllEnemies.BarricadosModel2.barricadosSize / 2), gameController);


                }
            }
        }
    }


    public static void checkCollisionBallBlackOrb(GameController gameController, ClientModel client) {
        double xMin1 = gameController.ball.x - BallModel.ballRadius;
        double xMax1 = gameController.ball.x + BallModel.ballRadius;
        double yMin1 = gameController.ball.y - BallModel.ballRadius;
        double yMax1 = gameController.ball.y + BallModel.ballRadius;
        for (int k = 0; k < gameController.blackOrbEnemies.size(); k++) {
            if (gameController.blackOrbEnemies.get(k).enemyHealth > 0) {

                double xMin2 = gameController.blackOrbEnemies.get(k).x;
                double xMax2 = gameController.blackOrbEnemies.get(k).x + AllEnemies.BlackOrbModel.blackOrbSize;
                double yMin2 = gameController.blackOrbEnemies.get(k).y;
                double yMax2 = gameController.blackOrbEnemies.get(k).y + AllEnemies.BlackOrbModel.blackOrbSize;

                if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                        || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                    if (client.player.isWritOfAstrape()) {
                        gameController.blackOrbEnemies.get(k).enemyHealth -= 2;
                        if (gameController.ball.ballChiron) {
                            gameController.ball.HP += 3;
                        }
                    }
                    if (gameController.ball.ballCerberus) {
                        gameController.blackOrbEnemies.get(k).enemyHealth -= 10;
                        gameController.ball.ballCerberus = false;
                        if (gameController.ball.ballChiron) {
                            gameController.ball.HP += 3;
                        }
                    }
                    if (gameController.blackOrbEnemies.get(k).enemyHealth <= 0) {
                        SoundEffects.playSound(Constants.HIT_SOUND_PATH);
                        gameController.newCollectible(gameController.blackOrbEnemies.get(k).x, gameController.blackOrbEnemies.get(k).y, 30);
                        gameController.newCollectible(gameController.blackOrbEnemies.get(k).x+2, gameController.blackOrbEnemies.get(k).y-2, 30);
                        gameController.newCollectible(gameController.blackOrbEnemies.get(k).x+4, gameController.blackOrbEnemies.get(k).y, 30);
                        gameController.newCollectible(gameController.blackOrbEnemies.get(k).x+4, gameController.blackOrbEnemies.get(k).y+3, 30);
                        gameController.newCollectible(gameController.blackOrbEnemies.get(k).x, gameController.blackOrbEnemies.get(k).y+2, 30);
                    }

                    gameController.ball.ballInBlackOrb = true;

                } else {
                    gameController.ball.ballInBlackOrb = false;
                }
            }
        }
    }


    // =========================     ball-angle collisions    ==========================


    public static void checkCollisionBallAngleOmenoct(GameController gameController) {
        if (gameController.ballAngle.angleExists) {

            double xMin1 = gameController.ballAngle.x - BallAngle.ballAngleRadius;
            double xMax1 = gameController.ballAngle.x + BallAngle.ballAngleRadius;
            double yMin1 = gameController.ballAngle.y - BallAngle.ballAngleRadius;
            double yMax1 = gameController.ballAngle.y + BallAngle.ballAngleRadius;
            for (int k = 0; k < gameController.omenoctEnemies.size(); k++) {
                if (gameController.omenoctEnemies.get(k).enemyHealth > 0) {

                    double xMin2 = gameController.omenoctEnemies.get(k).xAngles[6];
                    double xMax2 = gameController.omenoctEnemies.get(k).xAngles[2];
                    double yMin2 = gameController.omenoctEnemies.get(k).yAngles[0];
                    double yMax2 = gameController.omenoctEnemies.get(k).yAngles[4];

                    if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                            || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                            || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                            || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {


                        gameController.omenoctEnemies.get(k).enemyHealth -= 10;
                        if (gameController.ball.ballChiron) {
                            gameController.ball.HP += 3;
                        }
                        SoundEffects.playSound(Constants.HURT_SOUND_PATH);
                        if (gameController.omenoctEnemies.get(k).enemyHealth <= 0) {
                            SoundEffects.playSound(Constants.HIT_SOUND_PATH);
                            gameController.newCollectible(gameController.omenoctEnemies.get(k).x, gameController.omenoctEnemies.get(k).y, 4);
                            gameController.newCollectible(gameController.omenoctEnemies.get(k).x+2, gameController.omenoctEnemies.get(k).y+1, 4);
                            gameController.newCollectible(gameController.omenoctEnemies.get(k).x+4, gameController.omenoctEnemies.get(k).y-1, 4);
                            gameController.newCollectible(gameController.omenoctEnemies.get(k).x+5, gameController.omenoctEnemies.get(k).y, 4);
                            gameController.newCollectible(gameController.omenoctEnemies.get(k).x, gameController.omenoctEnemies.get(k).y+2, 4);
                            gameController.newCollectible(gameController.omenoctEnemies.get(k).x+1, gameController.omenoctEnemies.get(k).y+3, 4);
                            gameController.newCollectible(gameController.omenoctEnemies.get(k).x+2, gameController.omenoctEnemies.get(k).y+4, 4);
                            gameController.newCollectible(gameController.omenoctEnemies.get(k).x, gameController.omenoctEnemies.get(k).y+3, 4);

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


    public static void checkCollisionBallAngleNecropick(GameController gameController) {
        if (gameController.ballAngle.angleExists) {

            double xMin1 = gameController.ballAngle.x - BallAngle.ballAngleRadius;
            double xMax1 = gameController.ballAngle.x + BallAngle.ballAngleRadius;
            double yMin1 = gameController.ballAngle.y - BallAngle.ballAngleRadius;
            double yMax1 = gameController.ballAngle.y + BallAngle.ballAngleRadius;
            for (int k = 0; k < gameController.necropickEnemies.size(); k++) {
                if (gameController.necropickEnemies.get(k).enemyHealth > 0 && !gameController.necropickEnemies.get(k).hide) {

                    double xMin2 = gameController.necropickEnemies.get(k).xAngles[0];
                    double xMax2 = gameController.necropickEnemies.get(k).xAngles[1];
                    double yMin2 = gameController.necropickEnemies.get(k).yAngles[0];
                    double yMax2 = gameController.necropickEnemies.get(k).yAngles[1];

                    if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                            || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                            || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                            || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {


                        gameController.necropickEnemies.get(k).enemyHealth -= 10;
                        if (gameController.ball.ballChiron) {
                            gameController.ball.HP += 3;
                        }
                        SoundEffects.playSound(Constants.HURT_SOUND_PATH);
                        if (gameController.necropickEnemies.get(k).enemyHealth <= 0) {
                            SoundEffects.playSound(Constants.HIT_SOUND_PATH);
                            gameController.newCollectible(gameController.necropickEnemies.get(k).x, gameController.necropickEnemies.get(k).y, 2);
                            gameController.newCollectible(gameController.necropickEnemies.get(k).x+1, gameController.necropickEnemies.get(k).y-1, 2);
                            gameController.newCollectible(gameController.necropickEnemies.get(k).x+1, gameController.necropickEnemies.get(k).y-1, 2);
                            gameController.newCollectible(gameController.necropickEnemies.get(k).x+2, gameController.necropickEnemies.get(k).y, 2);
                        }
                        Impact.turnOnImpact(gameController.ball.x,
                                gameController.ball.y,
                                gameController.necropickEnemies.get(k).x + ((double) AllEnemies.NecropickModel.necropickSize / 2),
                                gameController.necropickEnemies.get(k).y + ((double) AllEnemies.NecropickModel.necropickSize / 2), gameController);

                    }
                }

            }
        }
    }


    // =========================     enemies collisions    ==========================


    public static void checkCollisionWyrmOmenoct(GameController gameController) {
        if (!gameController.wyrmEnemies.isEmpty()) {
            for (int i = 0; i < gameController.wyrmEnemies.size(); i++) {
                if (gameController.wyrmEnemies.get(i).enemyHealth > 0) {

                    double xMin1 = gameController.wyrmEnemies.get(i).x;
                    double xMax1 = gameController.wyrmEnemies.get(i).x + AllEnemies.WyrmModel.wyrmSize;
                    double yMin1 = gameController.wyrmEnemies.get(i).y;
                    double yMax1 = gameController.wyrmEnemies.get(i).y + AllEnemies.WyrmModel.wyrmSize;

                    for (int k = 0; k < gameController.omenoctEnemies.size(); k++) {
                        if (gameController.omenoctEnemies.get(k).enemyHealth > 0) {

                            double xMin2 = gameController.omenoctEnemies.get(k).xAngles[6];
                            double xMax2 = gameController.omenoctEnemies.get(k).xAngles[2];
                            double yMin2 = gameController.omenoctEnemies.get(k).yAngles[0];
                            double yMax2 = gameController.omenoctEnemies.get(k).yAngles[4];

                            if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                                Impact.turnOnImpact(gameController.wyrmEnemies.get(i).x + ((double) AllEnemies.WyrmModel.wyrmSize / 2),
                                        gameController.wyrmEnemies.get(i).y + ((double) AllEnemies.WyrmModel.wyrmSize / 2),
                                        gameController.omenoctEnemies.get(k).x,
                                        gameController.omenoctEnemies.get(k).y + AllEnemies.OmenoctModel.distanceToCenter, gameController);
                            }
                        }
                    }
                }
            }
        }
    }


    public static void checkCollisionNecropickOmenoct(GameController gameController) {
        for (int i = 0; i < gameController.necropickEnemies.size(); i++) {
            if (gameController.necropickEnemies.get(i).enemyHealth > 0 && !gameController.necropickEnemies.get(i).hide) {

                double xMin1 = gameController.necropickEnemies.get(i).xAngles[0];
                double xMax1 = gameController.necropickEnemies.get(i).xAngles[1];
                double yMin1 = gameController.necropickEnemies.get(i).yAngles[0];
                double yMax1 = gameController.necropickEnemies.get(i).yAngles[1];

                for (int k = 0; k < gameController.omenoctEnemies.size(); k++) {
                    if (gameController.omenoctEnemies.get(k).enemyHealth > 0) {

                        double xMin2 = gameController.omenoctEnemies.get(k).xAngles[6];
                        double xMax2 = gameController.omenoctEnemies.get(k).xAngles[2];
                        double yMin2 = gameController.omenoctEnemies.get(k).yAngles[0];
                        double yMax2 = gameController.omenoctEnemies.get(k).yAngles[4];

                        if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                                || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                            Impact.turnOnImpact(gameController.necropickEnemies.get(i).x + ((double) AllEnemies.NecropickModel.necropickSize / 2),
                                    gameController.necropickEnemies.get(i).y + ((double) AllEnemies.NecropickModel.necropickSize / 2),
                                    gameController.omenoctEnemies.get(k).x,
                                    gameController.omenoctEnemies.get(k).y + AllEnemies.OmenoctModel.distanceToCenter, gameController);
                        }
                    }
                }
            }
        }
    }


    public static void checkCollisionBarricados1Omenoct(GameController gameController) {
        if (!gameController.barricadosEnemies1.isEmpty()) {
            for (int i = 0; i < gameController.barricadosEnemies1.size(); i++) {
                if (gameController.barricadosEnemies1.get(i).enemyTimer > 0) {

                    double xMin1 = gameController.barricadosEnemies1.get(i).x;
                    double xMax1 = gameController.barricadosEnemies1.get(i).x + AllEnemies.BarricadosModel1.barricadosSize;
                    double yMin1 = gameController.barricadosEnemies1.get(i).y;
                    double yMax1 = gameController.barricadosEnemies1.get(i).y + AllEnemies.BarricadosModel1.barricadosSize;

                    for (int k = 0; k < gameController.omenoctEnemies.size(); k++) {
                        if (gameController.omenoctEnemies.get(k).enemyHealth > 0) {

                            double xMin2 = gameController.omenoctEnemies.get(k).xAngles[6];
                            double xMax2 = gameController.omenoctEnemies.get(k).xAngles[2];
                            double yMin2 = gameController.omenoctEnemies.get(k).yAngles[0];
                            double yMax2 = gameController.omenoctEnemies.get(k).yAngles[4];

                            if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                                Impact.turnOnImpact(gameController.barricadosEnemies1.get(i).x + ((double) AllEnemies.BarricadosModel1.barricadosSize / 2),
                                        gameController.barricadosEnemies1.get(i).y + ((double) AllEnemies.BarricadosModel1.barricadosSize / 2),
                                        gameController.omenoctEnemies.get(k).x,
                                        gameController.omenoctEnemies.get(k).y + AllEnemies.OmenoctModel.distanceToCenter, gameController);
                            }
                        }
                    }
                }
            }
        }
    }

    public static void checkCollisionBarricados2Omenoct(GameController gameController) {
        if (!gameController.barricadosEnemies2.isEmpty()) {
            for (int i = 0; i < gameController.barricadosEnemies2.size(); i++) {
                if (gameController.barricadosEnemies2.get(i).enemyTimer > 0) {

                    double xMin1 = gameController.barricadosEnemies2.get(i).x;
                    double xMax1 = gameController.barricadosEnemies2.get(i).x + AllEnemies.BarricadosModel2.barricadosSize;
                    double yMin1 = gameController.barricadosEnemies2.get(i).y;
                    double yMax1 = gameController.barricadosEnemies2.get(i).y + AllEnemies.BarricadosModel2.barricadosSize;

                    for (int k = 0; k < gameController.omenoctEnemies.size(); k++) {
                        if (gameController.omenoctEnemies.get(k).enemyHealth > 0) {

                            double xMin2 = gameController.omenoctEnemies.get(k).xAngles[6];
                            double xMax2 = gameController.omenoctEnemies.get(k).xAngles[2];
                            double yMin2 = gameController.omenoctEnemies.get(k).yAngles[0];
                            double yMax2 = gameController.omenoctEnemies.get(k).yAngles[4];

                            if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                                Impact.turnOnImpact(gameController.barricadosEnemies2.get(i).x + ((double) AllEnemies.BarricadosModel2.barricadosSize / 2),
                                        gameController.barricadosEnemies2.get(i).y + ((double) AllEnemies.BarricadosModel2.barricadosSize / 2),
                                        gameController.omenoctEnemies.get(k).x,
                                        gameController.omenoctEnemies.get(k).y + AllEnemies.OmenoctModel.distanceToCenter, gameController);
                            }
                        }
                    }
                }
            }
        }
    }


    public static void checkCollisionNecropickWyrm(GameController gameController) {
        if (!gameController.necropickEnemies.isEmpty()) {
            for (int i = 0; i < gameController.necropickEnemies.size(); i++) {
                if (gameController.necropickEnemies.get(i).enemyHealth > 0 && !gameController.necropickEnemies.get(i).hide) {

                    double xMin1 = gameController.necropickEnemies.get(i).xAngles[0];
                    double xMax1 = gameController.necropickEnemies.get(i).xAngles[1];
                    double yMin1 = gameController.necropickEnemies.get(i).yAngles[0];
                    double yMax1 = gameController.necropickEnemies.get(i).yAngles[1];

                    if (!gameController.wyrmEnemies.isEmpty()) {
                        for (int j = 0; j < gameController.wyrmEnemies.size(); j++) {
                            if (gameController.wyrmEnemies.get(j).enemyHealth > 0) {

                                double xMin2 = gameController.wyrmEnemies.get(j).x;
                                double xMax2 = gameController.wyrmEnemies.get(j).x + AllEnemies.WyrmModel.wyrmSize;
                                double yMin2 = gameController.wyrmEnemies.get(j).y;
                                double yMax2 = gameController.wyrmEnemies.get(j).y + AllEnemies.WyrmModel.wyrmSize;

                                if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                        || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                                    Impact.turnOnImpact(gameController.necropickEnemies.get(j).x + ((double) AllEnemies.NecropickModel.necropickSize / 2),
                                            gameController.necropickEnemies.get(j).y + ((double) AllEnemies.NecropickModel.necropickSize / 2),
                                            gameController.wyrmEnemies.get(j).x,
                                            gameController.wyrmEnemies.get(j).y + (double) AllEnemies.WyrmModel.wyrmSize / 2, gameController);
                                }
                            }
                        }
                    }
                }
            }

        }
    }


    public static void checkCollisionBarricados1Necropick(GameController gameController) {
        if (!gameController.barricadosEnemies1.isEmpty()) {
            for (int i = 0; i < gameController.barricadosEnemies1.size(); i++) {
                if (gameController.barricadosEnemies1.get(i).enemyTimer > 0) {

                    double xMin1 = gameController.barricadosEnemies1.get(i).x;
                    double xMax1 = gameController.barricadosEnemies1.get(i).x + AllEnemies.BarricadosModel1.barricadosSize;
                    double yMin1 = gameController.barricadosEnemies1.get(i).y;
                    double yMax1 = gameController.barricadosEnemies1.get(i).y + AllEnemies.BarricadosModel1.barricadosSize;

                    if (!gameController.necropickEnemies.isEmpty()) {
                        for (int j = 0; j < gameController.necropickEnemies.size(); j++) {
                            if (gameController.necropickEnemies.get(j).enemyHealth > 0 && !gameController.necropickEnemies.get(j).hide) {

                                double xMin2 = gameController.necropickEnemies.get(j).xAngles[0];
                                double xMax2 = gameController.necropickEnemies.get(j).xAngles[1];
                                double yMin2 = gameController.necropickEnemies.get(j).yAngles[0];
                                double yMax2 = gameController.necropickEnemies.get(j).yAngles[1];

                                if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                        || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                                    Impact.turnOnImpact(gameController.barricadosEnemies1.get(j).x + ((double) AllEnemies.BarricadosModel1.barricadosSize / 2),
                                            gameController.barricadosEnemies1.get(j).y + ((double) AllEnemies.BarricadosModel1.barricadosSize / 2),
                                            gameController.necropickEnemies.get(j).x + (double) AllEnemies.NecropickModel.necropickSize / 2,
                                            gameController.necropickEnemies.get(j).y + (double) AllEnemies.NecropickModel.necropickSize / 2, gameController);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void checkCollisionBarricados2Necropick(GameController gameController) {
        if (!gameController.barricadosEnemies2.isEmpty()) {
            for (int i = 0; i < gameController.barricadosEnemies2.size(); i++) {
                if (gameController.barricadosEnemies2.get(i).enemyTimer > 0) {

                    double xMin1 = gameController.barricadosEnemies2.get(i).x;
                    double xMax1 = gameController.barricadosEnemies2.get(i).x + AllEnemies.BarricadosModel2.barricadosSize;
                    double yMin1 = gameController.barricadosEnemies2.get(i).y;
                    double yMax1 = gameController.barricadosEnemies2.get(i).y + AllEnemies.BarricadosModel2.barricadosSize;

                    if (!gameController.necropickEnemies.isEmpty()) {
                        for (int j = 0; j < gameController.necropickEnemies.size(); j++) {
                            if (gameController.necropickEnemies.get(j).enemyHealth > 0 && !gameController.necropickEnemies.get(j).hide) {

                                double xMin2 = gameController.necropickEnemies.get(j).xAngles[0];
                                double xMax2 = gameController.necropickEnemies.get(j).xAngles[1];
                                double yMin2 = gameController.necropickEnemies.get(j).yAngles[0];
                                double yMax2 = gameController.necropickEnemies.get(j).yAngles[1];

                                if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                        || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                                    Impact.turnOnImpact(gameController.barricadosEnemies2.get(j).x + ((double) AllEnemies.BarricadosModel2.barricadosSize / 2),
                                            gameController.barricadosEnemies2.get(j).y + ((double) AllEnemies.BarricadosModel2.barricadosSize / 2),
                                            gameController.necropickEnemies.get(j).x + (double) AllEnemies.NecropickModel.necropickSize / 2,
                                            gameController.necropickEnemies.get(j).y + (double) AllEnemies.NecropickModel.necropickSize / 2, gameController);
                                }
                            }
                        }
                    }
                }
            }
        }

    }


    public static void checkCollisionBarricados2Wyrm(GameController gameController) {
        if (!gameController.barricadosEnemies2.isEmpty()) {
            for (int i = 0; i < gameController.barricadosEnemies2.size(); i++) {
                if (gameController.barricadosEnemies2.get(i).enemyTimer > 0) {

                    double xMin1 = gameController.barricadosEnemies2.get(i).x;
                    double xMax1 = gameController.barricadosEnemies2.get(i).x + AllEnemies.BarricadosModel2.barricadosSize;
                    double yMin1 = gameController.barricadosEnemies2.get(i).y;
                    double yMax1 = gameController.barricadosEnemies2.get(i).y + AllEnemies.BarricadosModel2.barricadosSize;

                    if (!gameController.wyrmEnemies.isEmpty()) {
                        for (int j = 0; j < gameController.wyrmEnemies.size(); j++) {
                            if (gameController.wyrmEnemies.get(j).enemyHealth > 0) {

                                double xMin2 = gameController.wyrmEnemies.get(j).x;
                                double xMax2 = gameController.wyrmEnemies.get(j).x + AllEnemies.WyrmModel.wyrmSize;
                                double yMin2 = gameController.wyrmEnemies.get(j).y;
                                double yMax2 = gameController.wyrmEnemies.get(j).y + AllEnemies.WyrmModel.wyrmSize;

                                if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                        || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                                    Impact.turnOnImpact(gameController.barricadosEnemies2.get(i).x + ((double) AllEnemies.BarricadosModel2.barricadosSize / 2),
                                            gameController.barricadosEnemies2.get(i).y + ((double) AllEnemies.BarricadosModel2.barricadosSize / 2),
                                            gameController.wyrmEnemies.get(j).x + (double) AllEnemies.WyrmModel.wyrmSize / 2,
                                            gameController.wyrmEnemies.get(j).y + (double) AllEnemies.WyrmModel.wyrmSize / 2, gameController);
                                }
                            }
                        }
                    }
                }
            }
        }

    }



    public static void checkCollisionBarricados1Wyrm(GameController gameController) {
        if (!gameController.barricadosEnemies1.isEmpty()) {
            for (int i = 0; i < gameController.barricadosEnemies1.size(); i++) {
                if (gameController.barricadosEnemies1.get(i).enemyTimer > 0) {

                    double xMin1 = gameController.barricadosEnemies1.get(i).x;
                    double xMax1 = gameController.barricadosEnemies1.get(i).x + AllEnemies.BarricadosModel1.barricadosSize;
                    double yMin1 = gameController.barricadosEnemies1.get(i).y;
                    double yMax1 = gameController.barricadosEnemies1.get(i).y + AllEnemies.BarricadosModel1.barricadosSize;

                    if (!gameController.wyrmEnemies.isEmpty()) {
                        for (int j = 0; j < gameController.wyrmEnemies.size(); j++) {
                            if (gameController.wyrmEnemies.get(j).enemyHealth > 0) {

                                double xMin2 = gameController.wyrmEnemies.get(j).x;
                                double xMax2 = gameController.wyrmEnemies.get(j).x + AllEnemies.WyrmModel.wyrmSize;
                                double yMin2 = gameController.wyrmEnemies.get(j).y;
                                double yMax2 = gameController.wyrmEnemies.get(j).y + AllEnemies.WyrmModel.wyrmSize;

                                if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                        || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                                    Impact.turnOnImpact(gameController.barricadosEnemies1.get(i).x + ((double) AllEnemies.BarricadosModel1.barricadosSize / 2),
                                            gameController.barricadosEnemies1.get(i).y + ((double) AllEnemies.BarricadosModel1.barricadosSize / 2),
                                            gameController.wyrmEnemies.get(j).x + (double) AllEnemies.WyrmModel.wyrmSize / 2,
                                            gameController.wyrmEnemies.get(j).y + (double) AllEnemies.WyrmModel.wyrmSize / 2, gameController);
                                }
                            }
                        }
                    }
                }
            }
        }

    }
}

