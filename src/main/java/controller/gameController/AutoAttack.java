package controller.gameController;

import controller.gameController.objectController.enemies.NecropickController;
import model.ClientModel;
import model.entity.enemy.AllEnemies;
import model.entity.enemy.EnemyModel;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Time;
import java.util.*;

public class AutoAttack {
    public static void autoWaveAttacks(ClientModel client, GameController gameController) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                Class<?>[] classes = EnemyModel.class.getClasses();
                System.out.println(classes.length);

                if (client.gameController.enemies2.size() + client.gameController.enemies1.size() <= 45) {
                    // wave 1, 2, 3 enemies
                    if (!client.gameController.pause) {
                        client.gameController.newEnemy1(client);
                        client.gameController.newEnemy2(client);
                    }
                } else if (gameController.omenoctEnemies.size() + gameController.necropickEnemies.size()
                        + gameController.archmireEnemies.size() + gameController.wyrmEnemies.size()
                        + gameController.blackOrbEnemies.size() + gameController.barricadosEnemies1.size()
                        + gameController.barricadosEnemies2.size() <= 180){
                    // wave 4, 5 enemies
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

                        gameController.newArchmire();


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

                }
            }
        };
        timer.scheduleAtFixedRate(task, 5000, 5000);
    }


    public static void createRandomEnemy(ClientModel client, double x, double y) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Class<?>[] classes = AllEnemies.class.getClasses();

                Random random = new Random();
                int num = random.nextInt(10);


                Class<?> enemy = classes[num];

                Constructor constructor;

                try {
                    constructor = enemy.getConstructor(double.class, double.class);
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }

                EnemyModel enemyModel;
                try {
                    enemyModel = (EnemyModel) constructor.newInstance(x, y);
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }


                if (client.gameController.wave <= 3) {

                    if (enemy.getName().equalsIgnoreCase("enemyModel1")) {
                        client.gameController.enemies1.add((AllEnemies.EnemyModel1) enemyModel);
                    }

                    if (enemy.getName().equalsIgnoreCase("enemyModel2")) {
                        client.gameController.enemies2.add((AllEnemies.EnemyModel2) enemyModel);
                    }
                }

                else if (client.gameController.wave <= 5) {
                    if (enemy.getName().equalsIgnoreCase("wyrmModel")) {
                        client.gameController.wyrmEnemies.add((AllEnemies.WyrmModel) enemyModel);
                    }

                    if (enemy.getName().equalsIgnoreCase("omenoctModel")) {
                        client.gameController.omenoctEnemies.add((AllEnemies.OmenoctModel) enemyModel);
                    }

                    if (enemy.getName().equalsIgnoreCase("necropickModel")) {
                        client.gameController.necropickEnemies.add((AllEnemies.NecropickModel) enemyModel);
                    }

                    if (enemy.getName().equalsIgnoreCase("blackOrb")) {
                        client.gameController.blackOrbEnemies.add((AllEnemies.BlackOrbModel) enemyModel);
                    }

                    if (enemy.getName().equalsIgnoreCase("barricadosModel2")) {
                        client.gameController.barricadosEnemies2.add((AllEnemies.BarricadosModel2) enemyModel);
                    }

                    if (enemy.getName().equalsIgnoreCase("barricadosModel1")) {
                        client.gameController.barricadosEnemies1.add((AllEnemies.BarricadosModel1) enemyModel);
                    }

                    if (enemy.getName().equalsIgnoreCase("ArchmirePoints")) {
                        client.gameController.archmirePoints.add((AllEnemies.ArchmirePoints) enemyModel);
                    }

                    if (enemy.getName().equalsIgnoreCase("ArchmireModel")) {
                        client.gameController.archmireEnemies.add((AllEnemies.ArchmireModel) enemyModel);
                    }
                }
                
            }
        };
        timer.scheduleAtFixedRate(task, 5000, 5000);




    }



}
