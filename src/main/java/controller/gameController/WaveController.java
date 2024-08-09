package controller.gameController;

import controller.gameController.objectController.enemies.NecropickController;
import model.ClientModel;
import model.entity.enemy.AllEnemies;

import java.util.Timer;
import java.util.TimerTask;

public class WaveController {



    // ========================== creating wave1 enemies ================================


    public static AllEnemies.EnemyModel1 setTimerForEnemy1(ClientModel client) {   // this method creates an enemy every 5 seconds

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (client.gameController.enemies2.size() + client.gameController.enemies1.size() <= 10) {
                    client.gameController.newEnemy1(client);
                } else {
                    timer.cancel();
                    client.gameController.wave++;
                    setTimerForEnemy1wave2(client);
                }
                if (client.gameController.stopWave) {
                    timer.cancel();
                }

            }
        };
        timer.scheduleAtFixedRate(task, 10000, 5000);

        return client.gameController.enemy1;
    }

    public static AllEnemies.EnemyModel2 setTimerForEnemy2(ClientModel client) {   // this method creates an enemy every 5 seconds

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (client.gameController.enemies1.size() + client.gameController.enemies2.size() <= 10) {
                    client.gameController.newEnemy2(client);
                } else {
                    timer.cancel();
                    setTimerForEnemy2wave2(client);
                }
                if (client.gameController.stopWave) {
                    timer.cancel();
                }
            }

        };
        timer.scheduleAtFixedRate(task, 12500, 5000);

        return client.gameController.enemy2;
    }


    // =========================== creating wave2 enemies =======================================

    public static AllEnemies.EnemyModel1 setTimerForEnemy1wave2(ClientModel client) {   // this method creates an enemy every 5 seconds

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (client.gameController.enemies2.size() + client.gameController.enemies1.size() <= 25) {
                    client.gameFrame.countDown = false;
                    client.gameController.newEnemy1(client);
                } else {
                    timer.cancel();
                    client.gameController.wave++;
                    setTimerForEnemy1wave3(client);
                }
                if (client.gameController.stopWave) {
                    timer.cancel();
                }

            }
        };
        timer.scheduleAtFixedRate(task, 15000, 5000);

        return client.gameController.enemy1;
    }

    public static AllEnemies.EnemyModel2 setTimerForEnemy2wave2(ClientModel client) {   // this method creates an enemy every 5 seconds

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (client.gameController.enemies2.size() + client.gameController.enemies1.size() <= 25) {
                    client.gameFrame.countDown = false;
                    client.gameController.newEnemy2(client);
                } else {
                    timer.cancel();
                    setTimerForEnemy2wave3(client);
                }
                if (client.gameController.stopWave) {
                    timer.cancel();
                }
            }

        };
        timer.scheduleAtFixedRate(task, 17500, 5000);

        return client.gameController.enemy2;
    }

    // =========================== creating wave3 enemies =======================================

    public static AllEnemies.EnemyModel1 setTimerForEnemy1wave3(ClientModel client) {   // this method creates an enemy every 5 seconds

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (client.gameController.enemies1.size() + client.gameController.enemies2.size() <= 45) {
                    client.gameFrame.countDown = false;
                    client.gameController.newEnemy1(client);
                }
                if (client.gameController.stopWave) {
                    timer.cancel();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 15000, 5000);

        return client.gameController.enemy1;
    }

    public static AllEnemies.EnemyModel2 setTimerForEnemy2wave3(ClientModel client) {   // this method creates an enemy every 5 seconds

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (client.gameController.enemies1.size() + client.gameController.enemies2.size() <= 45) {
                    client.gameFrame.countDown = false;
                    client.gameController.newEnemy2(client);
                }
                if (client.gameController.stopWave) {
                    timer.cancel();
                }
            }

        };
        timer.scheduleAtFixedRate(task, 17500, 5000);

        return client.gameController.enemy2;
    }



    // =================       creating wave 4 and 5 enemies     =========================================


    public static AllEnemies.OmenoctModel setTimerForOmenoct(GameController gameController) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (!gameController.phase2Over && !gameController.pause) {
                    if (gameController.omenoctEnemies.isEmpty()) {
                        gameController.newOmenoct();
                    }
                    if (!gameController.omenoctEnemies.isEmpty()) {
                        boolean createNewOne = true;
                        for (int i = 0; i < gameController.omenoctEnemies.size(); i++) {
                            if (gameController.omenoctEnemies.get(i).enemyHealth > 0) {
                                createNewOne = false;
                                break;
                            }
                        }
                        if (createNewOne) {
                            gameController.newOmenoct();
                        }

                    }
                }
//                if (stopWave) {
//                    timer.cancel();
//                }
            }

        };
        timer.scheduleAtFixedRate(task, 500, 5000);

        return gameController.omenoct;
    }


    public static AllEnemies.NecropickModel setTimerForNecropick(GameController gameController) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (!gameController.phase2Over && !gameController.pause) {

                    if (gameController.necropickEnemies.isEmpty()) {
                        gameController.newNecropick();
                        NecropickController.setNecropickHidingTime(gameController);
                    }
                    if (!gameController.necropickEnemies.isEmpty()) {
                        boolean createNewOne = true;
                        for (int i = 0; i < gameController.necropickEnemies.size(); i++) {
                            if (gameController.necropickEnemies.get(i).enemyHealth > 0) {
                                createNewOne = false;
                                break;
                            }
                        }
                        if (createNewOne) {
                            gameController.newNecropick();
                            NecropickController.setNecropickHidingTime(gameController);
                        }

                    }
                }
//                if (stopWave) {
//                    timer.cancel();
//                }
            }

        };
        timer.scheduleAtFixedRate(task, 500, 5000);

        return gameController.necropick;
    }

    public static AllEnemies.ArchmireModel setTimerForArchmire(GameController gameController) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (!gameController.phase2Over && !gameController.pause) {

                    gameController.newArchmire();
                }
//                if (stopWave) {
//                    timer.cancel();
//                }
            }

        };
        timer.scheduleAtFixedRate(task, 500, 20000);

        return gameController.archmire;
    }

    public static AllEnemies.WyrmModel setTimerForWyrm(GameController gameController) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (!gameController.phase2Over && !gameController.pause) {

                    if (gameController.wyrmEnemies.isEmpty()) {
                        gameController.newWyrm();
                    }
                    if (!gameController.wyrmEnemies.isEmpty()) {
                        boolean createNewOne = true;
                        for (int i = 0; i < gameController.wyrmEnemies.size(); i++) {
                            if (gameController.wyrmEnemies.get(i).enemyHealth > 0) {
                                createNewOne = false;
                                break;
                            }
                        }
                        if (createNewOne) {
                            gameController.newWyrm();
                        }

                    }
                }
//                if (stopWave) {
//                    timer.cancel();
//                }
            }

        };
        timer.scheduleAtFixedRate(task, 5000, 5000);

        return gameController.wyrm;
    }


    public static AllEnemies.BarricadosModel1 setTimerForBarricados1(GameController gameController) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (!gameController.phase2Over && !gameController.pause) {

                    if (gameController.barricadosEnemies1.isEmpty()) {
                        gameController.newBarricados1();
                    }
                    if (!gameController.barricadosEnemies1.isEmpty()) {
                        boolean createNewOne = true;
                        for (int i = 0; i < gameController.barricadosEnemies1.size(); i++) {
                            if (gameController.barricadosEnemies1.get(i).enemyTimer > 0) {
                                createNewOne = false;
                                break;
                            }
                        }
                        if (createNewOne) {
                            gameController.newBarricados1();
                        }

                    }
                }
//                if (stopWave) {
//                    timer.cancel();
//                }
            }

        };
        timer.scheduleAtFixedRate(task, 5000, 180000);

        return gameController.barricados1;
    }


    public static AllEnemies.BarricadosModel2 setTimerForBarricados2(GameController gameController) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (!gameController.phase2Over && !gameController.pause) {

                    if (gameController.barricadosEnemies2.isEmpty()) {
                        gameController.newBarricados2();
                    }
                    if (!gameController.barricadosEnemies2.isEmpty()) {
                        boolean createNewOne = true;
                        for (int i = 0; i < gameController.barricadosEnemies2.size(); i++) {
                            if (gameController.barricadosEnemies2.get(i).enemyTimer > 0) {
                                createNewOne = false;
                                break;
                            }
                        }
                        if (createNewOne) {
                            gameController.newBarricados2();
                        }
                    }
                }
//                if (stopWave) {
//                    timer.cancel();
//                }
            }

        };
        timer.scheduleAtFixedRate(task, 15000, 180000);

        return gameController.barricados2;
    }


    public static AllEnemies.BlackOrbModel setTimerForBlackOrb(GameController gameController) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (!gameController.phase2Over && !gameController.pause) {

                    if (gameController.blackOrbEnemies.isEmpty()) {
                        gameController.newBlackOrb();
                    }
                    if (!gameController.blackOrbEnemies.isEmpty()) {
                        boolean createNewOne = true;
                        for (int i = 0; i < gameController.blackOrbEnemies.size(); i++) {
                            if (gameController.blackOrbEnemies.get(i).enemyHealth > 0) {
                                createNewOne = false;
                                break;
                            }
                        }
                        if (createNewOne) {
                            gameController.newBlackOrb();
                        }

                    }
                }
//                if (stopWave) {
//                    timer.cancel();
//                }
            }

        };
        timer.scheduleAtFixedRate(task, 1000, 5000);

        return gameController.blackOrb;
    }

}

