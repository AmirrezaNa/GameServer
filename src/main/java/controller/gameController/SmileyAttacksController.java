package controller.gameController;

import model.entity.enemy.boss.LeftHandModel;
import model.entity.enemy.boss.RightHandModel;
import model.entity.enemy.normalAndMiniBoss.ArchmirePoints;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

import static controller.gameController.objectController.enemies.ArchmireController.setTimerForPoint;

public class SmileyAttacksController {

    public static void startSmileyAttacks(GameController gameController) {
        startSqueezeAttack(gameController);
        startProjectileAttack(gameController);
        startVomitAttack(gameController);
        startQuakeAttack(gameController);
        startRapidFireAttack(gameController);
        startSlapAttack(gameController);
    }


    // =======================      Squeeze Attack      ==============================

    public static void startSqueezeAttack(GameController gameController) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (gameController.phase2Over && !gameController.pause) {

                    if (gameController.leftHand.leftHandExists && gameController.rightHand.rightHandExists) {
                        gameController.smiley.squeezeAttack = true;
                        timerForSqueezeAttack(gameController);
                    }
                }
            }
        };
        timer.scheduleAtFixedRate(task, 10000, 120000);
    }

    public static void timerForSqueezeAttack(GameController gameController) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                gameController.smiley.squeezeAttack = false;
            }
        };
        timer.scheduleAtFixedRate(task, 10000, 15000);
    }


    // ==========================================================================


    // =====================     Projectile Attack     =======================================


    public static void startProjectileAttack(GameController gameController) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (gameController.phase2Over && !gameController.pause) {

                    if (gameController.leftHand.leftHandExists && gameController.rightHand.rightHandExists) {
                        gameController.smiley.projectileAttack = true;
                        timerForProjectileAttack(gameController);
                    }
                }
            }
        };
        timer.scheduleAtFixedRate(task, 22000, 120000);
    }

    public static void timerForProjectileAttack(GameController gameController) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (gameController.phase2Over && !gameController.pause) {

                    if (gameController.smiley.projectileAttack) {
                        for (int i = 0; i < 5; i++) {
                            Point point = new Point();
                            point.setLocation(gameController.rightHand.x + ((double) RightHandModel.rightHandSize / 2), gameController.rightHand.y);
                            Point goal = new Point();
                            goal.setLocation(gameController.ball.x, gameController.ball.y + 30 + ((Math.pow(-1, i) * i * 150)));
                            gameController.newNecropickBullet(point, goal);
                        }
                        for (int i = 0; i < 5; i++) {
                            Point point = new Point();
                            point.setLocation(gameController.leftHand.x - ((double) LeftHandModel.leftHandSize / 2), gameController.leftHand.y);
                            Point goal = new Point();
                            goal.setLocation(gameController.ball.x, gameController.ball.y + 30 + ((Math.pow(-1, i) * i * 150)));
                            gameController.newNecropickBullet(point, goal);
                        }
                        gameController.smiley.projectileAttack = false;
                    }
                }

            }
        };
        timer.scheduleAtFixedRate(task, 3000, 15000);
    }





    // ==========================================================================


    // =====================     Vomit Attack     =======================================




    public static void startVomitAttack(GameController gameController) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (gameController.phase2Over && !gameController.pause) {

                    gameController.smiley.vomitAttack = true;
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            Point point = new Point();
                            point.setLocation((gameController.epsilonFrame.epsilonFrame.x + 30 + (i * 100)),
                                    gameController.epsilonFrame.epsilonFrame.y + 30 + (j * 150));
                            ArchmirePoints vomitPoint = new ArchmirePoints(point.x, point.y);
                            gameController.archmirePoints.add(0, vomitPoint);
                            setTimerForPoint(vomitPoint, gameController);
                        }

                    }
                    timerForVomitAttack(gameController);
                }
            }
        };
        timer.scheduleAtFixedRate(task, 30000, 120000);
    }

    public static void timerForVomitAttack(GameController gameController) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                gameController.smiley.vomitAttack = false;
            }
        };
        timer.scheduleAtFixedRate(task, 10000, 15000);
    }





    // ==========================================================================


    // =====================     PowerPunch Attack     =======================================


    public static void startPowerPunchAttack(GameController gameController) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (gameController.phase2Over && !gameController.pause) {

                    if (gameController.smiley.punchExists) {
                        gameController.smiley.powerPunchAttack = true;
                        timerForPowerPunchAttack(gameController);
                        gameController.epsilonFrame.epsilonFrame.y += 50;
                        gameController.epsilonFrame.epsilonFrame.height -= 50;
                    }
                }
            }
        };
        timer.scheduleAtFixedRate(task, 40000, 120000);
    }

    public static void timerForPowerPunchAttack(GameController gameController) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                gameController.smiley.powerPunchAttack = false;
            }
        };
        timer.scheduleAtFixedRate(task, 10000, 15000);
    }






    // ==========================================================================


    // =====================     Quake Attack     =======================================


    public static void startQuakeAttack(GameController gameController) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (gameController.phase2Over && !gameController.pause) {

                    if (gameController.smiley.punchExists) {
                        gameController.smiley.quakeAttack = true;
                        Impact.impactQuakeAttack(gameController);
                        timerForQuakeAttack(gameController);
                    }
                }
            }
        };
        timer.scheduleAtFixedRate(task, 55000, 120000);
    }

    public static void timerForQuakeAttack(GameController gameController) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                gameController.smiley.quakeAttack = false;
            }
        };
        timer.scheduleAtFixedRate(task, 8000, 15000);
    }






    // ==========================================================================


    // =====================     Rapid Fire Attack     =======================================


    public static void startRapidFireAttack(GameController gameController) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (gameController.phase2Over && !gameController.pause) {

                    gameController.smiley.rapidFireAttack = true;
                    shotRapidFireAttackBullets(gameController);
                    timerForRapidFireAttack(gameController);
                }
            }
        };
        timer.scheduleAtFixedRate(task, 65000, 120000);
    }

    public static void shotRapidFireAttackBullets(GameController gameController) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (gameController.phase2Over && !gameController.pause) {

                    if (gameController.smiley.rapidFireAttack) {
                        for (int i = 0; i < 5; i++) {
                            Point point = new Point();
                            point.setLocation(gameController.smiley.x, gameController.smiley.y);
                            Point goal = new Point();
                            goal.setLocation(gameController.ball.x + 10 + ((Math.pow(-1, i) * i * 150)), gameController.ball.y);
                            gameController.newNecropickBullet(point, goal);
                        }
                    }
                }

            }
        };
        timer.scheduleAtFixedRate(task, 0, 3000);
    }


    public static void timerForRapidFireAttack(GameController gameController) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                gameController.smiley.rapidFireAttack = false;
            }
        };
        timer.scheduleAtFixedRate(task, 30000, 15000);
    }





    // ==========================================================================


    // =====================     Slap Attack     =======================================


    public static void startSlapAttack(GameController gameController) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (gameController.phase2Over && !gameController.pause) {

                    if (gameController.smiley.punchExists) {
                        gameController.smiley.slapAttack = true;
                        Impact.impactQuakeAttack(gameController);
                        gameController.ball.HP -= 10;
                        timerForSlapAttack(gameController);
                    }
                }
            }
        };
        timer.scheduleAtFixedRate(task, 100000, 120000);
    }

    public static void timerForSlapAttack(GameController gameController) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                gameController.smiley.slapAttack = false;
            }
        };
        timer.scheduleAtFixedRate(task, 8000, 15000);
    }


}
