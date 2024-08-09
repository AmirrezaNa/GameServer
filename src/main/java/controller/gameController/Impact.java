package controller.gameController;

import model.entity.BallModel;
import model.entity.enemy.normalAndMiniBoss.*;

public class Impact {
    public static void turnOnImpact(double x1, double y1, double x2, double y2, GameController gameController) {
        double xImpactPoint = (x1 + x2) / 2;
        double yImpactPoint = (y1 + y2) / 2;

        if (!gameController.phase1over) {
            impactEnemy1(xImpactPoint, yImpactPoint, gameController);
            impactEnemy2(xImpactPoint, yImpactPoint, gameController);
        } else if (!gameController.phase2Over) {
            impactOmenoct(xImpactPoint, yImpactPoint, gameController);
            impactNecropick(xImpactPoint, yImpactPoint, gameController);
            impactBarricados1(xImpactPoint, yImpactPoint, gameController);
            impactBarricados2(xImpactPoint, yImpactPoint, gameController);
        }
        impactBall(xImpactPoint, yImpactPoint, gameController);
    }


    public static void impactEnemy1(double xImpactPoint, double yImpactPoint, GameController gameController) {
        for (EnemyModel1 enemy1 : gameController.enemies1) {
            if (enemy1.enemyHealth > 0) {
                if (Math.pow((Math.abs(enemy1.x) - Math.abs(xImpactPoint)), 2) + Math.pow((Math.abs(enemy1.y) - Math.abs(yImpactPoint)), 2) < 8100) {
                    enemy1.ax = ((enemy1.x - xImpactPoint) / Math.sqrt(Math.pow((enemy1.x - xImpactPoint), 2) + Math.pow((enemy1.y - yImpactPoint), 2))) * EnemyModel1.enemyAcceleration;
                    if (yImpactPoint < enemy1.y) {
                        enemy1.ay = Math.sqrt(Math.pow(EnemyModel1.enemyAcceleration, 2) - Math.pow(enemy1.ax, 2));
                    } else {
                        enemy1.ay = -Math.sqrt(Math.pow(EnemyModel1.enemyAcceleration, 2) - Math.pow(enemy1.ax, 2));
                    }
                }
            }
        }
    }

    public static void impactEnemy2(double xImpactPoint, double yImpactPoint, GameController gameController) {
        for (EnemyModel2 enemy2 : gameController.enemies2) {
            if (enemy2.enemyHealth > 0) {
                if (Math.pow((Math.abs(enemy2.x) - Math.abs(xImpactPoint)), 2) + Math.pow((Math.abs(enemy2.y) - Math.abs(yImpactPoint)), 2) < 8100) {
                    enemy2.ax = ((enemy2.x - xImpactPoint) / Math.sqrt(Math.pow((enemy2.x - xImpactPoint), 2) + Math.pow((enemy2.y - yImpactPoint), 2))) * EnemyModel1.enemyAcceleration;
                    if (yImpactPoint < enemy2.y) {
                        enemy2.ay = Math.sqrt(Math.pow(EnemyModel1.enemyAcceleration, 2) - Math.pow(enemy2.ax, 2));
                    } else {
                        enemy2.ay = -Math.sqrt(Math.pow(EnemyModel1.enemyAcceleration, 2) - Math.pow(enemy2.ax, 2));
                    }
                }
            }
        }
    }

    public static void impactOmenoct(double xImpactPoint, double yImpactPoint, GameController gameController) {
        for (OmenoctModel omenoct : gameController.omenoctEnemies) {
            if (omenoct.enemyHealth > 0) {
                if (Math.pow((Math.abs(omenoct.x) - Math.abs(xImpactPoint)), 2) + Math.pow((Math.abs(omenoct.y) - Math.abs(yImpactPoint)), 2) < 8100) {
                    omenoct.ax = ((omenoct.x - xImpactPoint) / Math.sqrt(Math.pow((omenoct.x - xImpactPoint), 2) + Math.pow((omenoct.y - yImpactPoint), 2))) * EnemyModel1.enemyAcceleration;
                    if (yImpactPoint < omenoct.y) {
                        omenoct.ay = Math.sqrt(Math.pow(OmenoctModel.enemyAcceleration, 2) - Math.pow(omenoct.ax, 2));
                    } else {
                        omenoct.ay = -Math.sqrt(Math.pow(OmenoctModel.enemyAcceleration, 2) - Math.pow(omenoct.ax, 2));
                    }
                }
            }
        }
    }


    public static void impactNecropick(double xImpactPoint, double yImpactPoint, GameController gameController) {
        for (NecropickModel necropick : gameController.necropickEnemies) {
            if (necropick.enemyHealth > 0 && !necropick.hide) {
                if (Math.pow((Math.abs(necropick.x) - Math.abs(xImpactPoint)), 2) + Math.pow((Math.abs(necropick.y) - Math.abs(yImpactPoint)), 2) < 8100) {
                    necropick.ax = ((necropick.x - xImpactPoint) / Math.sqrt(Math.pow((necropick.x - xImpactPoint), 2) + Math.pow((necropick.y - yImpactPoint), 2))) * EnemyModel1.enemyAcceleration;
                    if (yImpactPoint < necropick.y) {
                        necropick.ay = Math.sqrt(Math.pow(NecropickModel.enemyAcceleration, 2) - Math.pow(necropick.ax, 2));
                    } else {
                        necropick.ay = -Math.sqrt(Math.pow(NecropickModel.enemyAcceleration, 2) - Math.pow(necropick.ax, 2));
                    }
                }
            }
        }
    }

    public static void impactBarricados1(double xImpactPoint, double yImpactPoint, GameController gameController) {
        for (BarricadosModel1 barricados : gameController.barricadosEnemies1) {
            if (barricados.enemyTimer > 0) {
                if (Math.pow((Math.abs(barricados.x) - Math.abs(xImpactPoint)), 2) + Math.pow((Math.abs(barricados.y) - Math.abs(yImpactPoint)), 2) < 8100) {
                    barricados.ax = ((barricados.x - xImpactPoint) / Math.sqrt(Math.pow((barricados.x - xImpactPoint), 2) + Math.pow((barricados.y - yImpactPoint), 2))) * EnemyModel1.enemyAcceleration;
                    if (yImpactPoint < barricados.y) {
                        barricados.ay = Math.sqrt(Math.pow(NecropickModel.enemyAcceleration, 2) - Math.pow(barricados.ax, 2));
                    } else {
                        barricados.ay = -Math.sqrt(Math.pow(NecropickModel.enemyAcceleration, 2) - Math.pow(barricados.ax, 2));
                    }
                }
            }
        }
    }


    public static void impactBarricados2(double xImpactPoint, double yImpactPoint, GameController gameController) {
        for (BarricadosModel2 barricados : gameController.barricadosEnemies2) {
            if (barricados.enemyTimer > 0) {
                if (Math.pow((Math.abs(barricados.x) - Math.abs(xImpactPoint)), 2) + Math.pow((Math.abs(barricados.y) - Math.abs(yImpactPoint)), 2) < 8100) {
                    barricados.ax = ((barricados.x - xImpactPoint) / Math.sqrt(Math.pow((barricados.x - xImpactPoint), 2) + Math.pow((barricados.y - yImpactPoint), 2))) * EnemyModel1.enemyAcceleration;
                    if (yImpactPoint < barricados.y) {
                        barricados.ay = Math.sqrt(Math.pow(NecropickModel.enemyAcceleration, 2) - Math.pow(barricados.ax, 2));
                    } else {
                        barricados.ay = -Math.sqrt(Math.pow(NecropickModel.enemyAcceleration, 2) - Math.pow(barricados.ax, 2));
                    }
                }
            }
        }
    }


    public static void impactBall(double xImpactPoint, double yImpactPoint, GameController gameController) {
        double xBallCenter = gameController.ball.x;
        double yBallCenter = gameController.ball.y;
        if (Math.pow((Math.abs(xBallCenter) - Math.abs(xImpactPoint)), 2) +
                Math.pow((Math.abs(yBallCenter) - Math.abs(yImpactPoint)), 2) < 8100) {

            gameController.ball.ax = ((xBallCenter - xImpactPoint) / Math.sqrt(Math.pow((xBallCenter - xImpactPoint), 2) + Math.pow((yBallCenter - yImpactPoint), 2))) * BallModel.ballAcceleration;
            if (yImpactPoint < yBallCenter) {
                gameController.ball.ay = Math.sqrt(Math.pow(BallModel.ballAcceleration, 2) - Math.pow(gameController.ball.ax, 2));
            } else {
                gameController.ball.ay = -Math.sqrt(Math.pow(BallModel.ballAcceleration, 2) - Math.pow(gameController.ball.ax, 2));
            }

        }
    }

    public static void banishImpact(double x1, double y1, double x2, double y2, GameController gameController) {
        double xImpactPoint = (x1 + x2) / 2;
        double yImpactPoint = (y1 + y2) / 2;
        if (gameController.Banish > 0) {
            for (EnemyModel1 enemy1 : gameController.enemies1) {
                if (enemy1.enemyHealth > 0) {
                    if (Math.pow((Math.abs(enemy1.x) - Math.abs(xImpactPoint)), 2) + Math.pow((Math.abs(enemy1.y) - Math.abs(yImpactPoint)), 2) < 10000) {
                        enemy1.ax = 2 * ((enemy1.x - xImpactPoint) / Math.sqrt(Math.pow((enemy1.x - xImpactPoint), 2) + Math.pow((enemy1.y - yImpactPoint), 2))) * EnemyModel1.enemyAcceleration;
                        if (yImpactPoint < enemy1.y) {
                            enemy1.ay = 2 * Math.sqrt(Math.pow(EnemyModel1.enemyAcceleration, 2) - Math.pow(enemy1.ax, 2));
                        } else {
                            enemy1.ay = -2 * Math.sqrt(Math.pow(EnemyModel1.enemyAcceleration, 2) - Math.pow(enemy1.ax, 2));
                        }
                    }
                }
            }

            for (EnemyModel2 enemy2 : gameController.enemies2) {
                if (enemy2.enemyHealth > 0) {
                    if (Math.pow((Math.abs(enemy2.x) - Math.abs(xImpactPoint)), 2) + Math.pow((Math.abs(enemy2.y) - Math.abs(yImpactPoint)), 2) < 10000) {
                        enemy2.ax = 2 * ((enemy2.x - xImpactPoint) / Math.sqrt(Math.pow((enemy2.x - xImpactPoint), 2) + Math.pow((enemy2.y - yImpactPoint), 2))) * EnemyModel1.enemyAcceleration;
                        if (yImpactPoint < enemy2.y) {
                            enemy2.ay = 2 * Math.sqrt(Math.pow(EnemyModel1.enemyAcceleration, 2) - Math.pow(enemy2.ax, 2));
                        } else {
                            enemy2.ay = -2 * Math.sqrt(Math.pow(EnemyModel1.enemyAcceleration, 2) - Math.pow(enemy2.ax, 2));
                        }
                    }
                }
            }
            gameController.Banish--;
        }


    }


    public static void impactQuakeAttack(GameController gameController) {
        double xImpactPoint = gameController.epsilonFrame.epsilonFrame.x + ((double) gameController.epsilonFrame.epsilonFrame.width /2);
        double yImpactPoint = gameController.epsilonFrame.epsilonFrame.y;
        double xBallCenter = gameController.ball.x;
        double yBallCenter = gameController.ball.y;
        gameController.ball.ax = ((xBallCenter - xImpactPoint) / Math.sqrt(Math.pow((xBallCenter - xImpactPoint), 2) + Math.pow((yBallCenter - yImpactPoint), 2))) * BallModel.ballAcceleration;
        if (yImpactPoint < yBallCenter) {
            gameController.ball.ay =  Math.sqrt(Math.pow(BallModel.ballAcceleration, 2) - Math.pow(gameController.ball.ax, 2));
        } else {
            gameController.ball.ay = - Math.sqrt(Math.pow(BallModel.ballAcceleration, 2) - Math.pow(gameController.ball.ax, 2));
        }
    }

}
