package controller.gameController.collisions.bossFight;

import controller.data.SoundEffects;
import controller.gameController.Constants;
import controller.gameController.GameController;
import controller.gameController.Impact;
import controller.gameController.collisions.phase2.ObjectCollisions2;
import model.entity.BallModel;
import model.entity.BulletModel;
import model.entity.enemy.AllEnemies;
import model.entity.enemy.boss.LeftHandModel;
import model.entity.enemy.boss.RightHandModel;

public class ObjectCollisionBossFight {

    public static void checkCollisionsPhase2(GameController gameController) {
        checkBulletCollisions(gameController);
        checkBallCollisions(gameController);



    }

    public static void checkBulletCollisions(GameController gameController) {
        // bullets collisions
        ObjectCollisions2.checkCollisionBallEnemyBullet(gameController);
        if (gameController.rightHand.rightHandExists) {
            checkCollisionBulletRightHand(gameController);
        }
        if (gameController.leftHand.leftHandExists) {
            checkCollisionBulletLeftHand(gameController);
        }
        checkCollisionBulletSmiley(gameController);

    }

    public static void checkBallCollisions(GameController gameController) {
        //ball collisions
        if (gameController.rightHand.rightHandExists) {
            checkCollisionBallRightHand(gameController);
        }
        if (gameController.leftHand.leftHandExists) {
            checkCollisionBallLeftHand(gameController);
        }
        ObjectCollisions2.checkCollisionBallArchmirePoints(gameController);


    }




    // ========================   bullet collisions     ==============================



    public static void checkCollisionBulletRightHand(GameController gameController) {

        double xMin2 = gameController.rightHand.x;
        double xMax2 = gameController.rightHand.x + (double) RightHandModel.rightHandSize /2;
        double yMin2 = gameController.rightHand.y - (double) RightHandModel.rightHandSize /2;
        double yMax2 = gameController.rightHand.y + (double) RightHandModel.rightHandSize /2;
        int size = Math.min(gameController.bullets.size(), 20);

        if (!gameController.bullets.isEmpty()) {
            for (int j = 0; j < size; j++) {
                if (gameController.bullets.get(j).bulletHealth > 0) {

                    double xMin1 = gameController.bullets.get(j).x;
                    double xMax1 = gameController.bullets.get(j).x + BulletModel.bulletSize;
                    double yMin1 = gameController.bullets.get(j).y;
                    double yMax1 = gameController.bullets.get(j).y + BulletModel.bulletSize;


                    if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                            || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                            || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                            || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                        gameController.bullets.get(j).bulletHealth = 0;

                        if (gameController.smiley.projectileAttack || gameController.smiley.powerPunchAttack) {
                            if (gameController.bulletAres) {
                                gameController.rightHand.enemyHealth -= 7;
                            }
                            if (!gameController.bulletAres) {
                                gameController.rightHand.enemyHealth -= 5;
                            }
                            if (gameController.ball.ballChiron) {
                                gameController.ball.HP += 3;
                            }
                            if (BulletModel.bulletSlaughter) {
                                gameController.rightHand.enemyHealth -= 50;
                                BulletModel.bulletSlaughter = false;
                            }
                        }

                        SoundEffects.playSound(Constants.HURT_SOUND_PATH);
                        Impact.turnOnImpact(gameController.bullets.get(j).x + ((double) BulletModel.bulletSize / 2),
                                gameController.bullets.get(j).y + ((double) BulletModel.bulletSize / 2),
                                gameController.rightHand.x + ((double) RightHandModel.rightHandSize / 2),
                                gameController.rightHand.y + ((double) RightHandModel.rightHandSize / 2), gameController);

                    }
                }
            }
        }
    }



    public static void checkCollisionBulletLeftHand(GameController gameController) {

        double xMin2 = gameController.leftHand.x - (double) LeftHandModel.leftHandSize /2;
        double xMax2 = gameController.leftHand.x;
        double yMin2 = gameController.leftHand.y - (double) LeftHandModel.leftHandSize /2;
        double yMax2 = gameController.leftHand.y + (double) LeftHandModel.leftHandSize /2;
        int size = Math.min(gameController.bullets.size(), 20);

        if (!gameController.bullets.isEmpty()) {
            for (int j = 0; j < size; j++) {
                if (gameController.bullets.get(j).bulletHealth > 0) {

                    double xMin1 = gameController.bullets.get(j).x;
                    double xMax1 = gameController.bullets.get(j).x + BulletModel.bulletSize;
                    double yMin1 = gameController.bullets.get(j).y;
                    double yMax1 = gameController.bullets.get(j).y + BulletModel.bulletSize;


                    if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                            || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                            || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                            || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                        gameController.bullets.get(j).bulletHealth = 0;

                        if (gameController.smiley.projectileAttack || gameController.smiley.powerPunchAttack) {
                            if (gameController.bulletAres) {
                                gameController.leftHand.enemyHealth -= 7;
                            }
                            if (!gameController.bulletAres) {
                                gameController.leftHand.enemyHealth -= 5;
                            }
                            if (gameController.ball.ballChiron) {
                                gameController.ball.HP += 3;
                            }
                            if (BulletModel.bulletSlaughter) {
                                gameController.leftHand.enemyHealth -= 50;
                                BulletModel.bulletSlaughter = false;
                            }
                        }

                        SoundEffects.playSound(Constants.HURT_SOUND_PATH);
                        Impact.turnOnImpact(gameController.bullets.get(j).x + ((double) BulletModel.bulletSize / 2),
                                gameController.bullets.get(j).y + ((double) BulletModel.bulletSize / 2),
                                gameController.leftHand.x + ((double) LeftHandModel.leftHandSize / 2),
                                gameController.leftHand.y + ((double) LeftHandModel.leftHandSize / 2), gameController);

                    }
                }
            }
        }
    }


    public static void checkCollisionBulletSmiley(GameController gameController) {

        double x = gameController.smiley.x;
        double y = gameController.smiley.y;
        int size = Math.min(gameController.bullets.size(), 20);

        if (!gameController.bullets.isEmpty()) {
            for (int j = 0; j < size; j++) {
                if (gameController.bullets.get(j).bulletHealth > 0) {

                    double x1 = gameController.bullets.get(j).x;
                    double y1 = gameController.bullets.get(j).y;

                    double distance = Math.pow((x-x1), 2) + Math.pow((y-y1), 2);
                    if (distance <= 40000) {

                        gameController.bullets.get(j).bulletHealth = 0;

                        if (gameController.smiley.squeezeAttack || gameController.smiley.vomitAttack || gameController.smiley.powerPunchAttack
                                || gameController.smiley.rapidFireAttack || gameController.smiley.slapAttack) {
                            if (gameController.bulletAres) {
                                gameController.smiley.enemyHealth -= 7;
                            }
                            if (!gameController.bulletAres) {
                                gameController.smiley.enemyHealth -= 5;
                            }
                            if (gameController.ball.ballChiron) {
                                gameController.ball.HP += 3;
                            }
                            if (BulletModel.bulletSlaughter) {
                                gameController.smiley.enemyHealth -= 50;
                                BulletModel.bulletSlaughter = false;
                            }
                        }

                        SoundEffects.playSound(Constants.HURT_SOUND_PATH);
                        Impact.turnOnImpact(gameController.bullets.get(j).x + ((double) BulletModel.bulletSize / 2),
                                gameController.bullets.get(j).y + ((double) BulletModel.bulletSize / 2),
                                gameController.smiley.x, gameController.smiley.y, gameController);

                    }
                }
            }
        }
    }









    // ========================    ball collisions     ======================================


    public static void checkCollisionBallRightHand(GameController gameController) {
        double xMin1 = gameController.ball.x - BallModel.ballRadius;
        double xMax1 = gameController.ball.x + BallModel.ballRadius;
        double yMin1 = gameController.ball.y - BallModel.ballRadius;
        double yMax1 = gameController.ball.y + BallModel.ballRadius;
        double xMin2 = gameController.rightHand.x;
        double xMax2 = gameController.rightHand.x + RightHandModel.rightHandSize;
        double yMin2 = gameController.rightHand.y - RightHandModel.rightHandSize;
        double yMax2 = gameController.rightHand.y + AllEnemies.BlackOrbModel.blackOrbSize;

        if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

//            if (EnterNamePage.player.isWritOfAstrape()) {
//                gameController.rightHand.enemyHealth -= 2;
//            }
            if (gameController.ball.ballCerberus) {
                gameController.rightHand.enemyHealth -= 10;
                gameController.ball.ballCerberus = false;
            }
            if (gameController.ball.ballChiron) {
                gameController.ball.HP += 3;
            }

            Impact.turnOnImpact(gameController.ball.x,
                    gameController.ball.y,
                    gameController.rightHand.x + ((double) RightHandModel.rightHandSize / 2),
                    gameController.rightHand.y + ((double) RightHandModel.rightHandSize / 2), gameController);

        }
    }


    public static void checkCollisionBallLeftHand(GameController gameController) {
        double xMin1 = gameController.ball.x - BallModel.ballRadius;
        double xMax1 = gameController.ball.x + BallModel.ballRadius;
        double yMin1 = gameController.ball.y - BallModel.ballRadius;
        double yMax1 = gameController.ball.y + BallModel.ballRadius;
        double xMin2 = gameController.leftHand.x - LeftHandModel.leftHandSize;
        double xMax2 = gameController.leftHand.x;
        double yMin2 = gameController.leftHand.y - LeftHandModel.leftHandSize;
        double yMax2 = gameController.leftHand.y + LeftHandModel.leftHandSize;

        if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

//            if (EnterNamePage.player.isWritOfAstrape()) {
//                gameController.leftHand.enemyHealth -= 2;
//            }
            if (gameController.ball.ballCerberus) {
                gameController.leftHand.enemyHealth -= 10;
                gameController.ball.ballCerberus = false;
            }
            if (gameController.ball.ballChiron) {
                gameController.ball.HP += 3;
            }

            Impact.turnOnImpact(gameController.ball.x,
                    gameController.ball.y,
                    gameController.leftHand.x - ((double) LeftHandModel.leftHandSize / 2),
                    gameController.leftHand.y + ((double) LeftHandModel.leftHandSize / 2), gameController);

        }
    }
}
