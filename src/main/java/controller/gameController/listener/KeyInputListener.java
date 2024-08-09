package controller.gameController.listener;

import controller.gameController.GameController;
import controller.gameController.Impact;
import controller.gameController.SkillTreeController;
import model.ClientModel;
import model.entity.BallModel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class KeyInputListener implements KeyListener {

    Set<Integer> pressedKeys = new HashSet<>();

    // Default key codes that can be changed based on user input
    public static int upKey = KeyEvent.VK_UP;
    public static int downKey = KeyEvent.VK_DOWN;
    public static int leftKey = KeyEvent.VK_LEFT;
    public static int rightKey = KeyEvent.VK_RIGHT;
    public static int banishKey = KeyEvent.VK_B;
    public static int writOfAresKey = KeyEvent.VK_S;
    public static int writOfCerberus = KeyEvent.VK_C;
    public static int writOfAcesoKey = KeyEvent.VK_O;
    public static int writOfProteusKey = KeyEvent.VK_P;
    public static int writOfEmpusa = KeyEvent.VK_E;
    public static int writOfDolus = KeyEvent.VK_D;


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
//        if (!GameController.pause) {
//            pressedKeys.add(e.getKeyCode());
//
//            if (smiley != null && smiley.quakeAttack && !client.gameController.pause) {
//                handleKeyPressedCombinationDuringQuakeAttack();
//            }
//            else if (!client.gameController.pause){
//                handleKeyPressedCombination();
//            }
//        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys.clear();
    }


    // this method is for checking which keys are being pressed at every moment ================================
    public void handleKeyPressedCombination(ClientModel client) {
        if (pressedKeys.size() == 1) {

            if (pressedKeys.contains(upKey)) {
                if (client.gameController.settings.sense == 1) {
                    client.gameController.ball.y -= client.gameController.ball.dy/2;
                }
                if (client.gameController.settings.sense == 2) {
                    client.gameController.ball.y -= client.gameController.ball.dy;
                }
                if (client.gameController.settings.sense == 3) {
                    client.gameController.ball.y -= 2 * client.gameController.ball.dy;
                }

            }
            else if (pressedKeys.contains(downKey)) {
                if (client.gameController.settings.sense == 1) {
                    client.gameController.ball.y += client.gameController.ball.dy/2;
                }
                if (client.gameController.settings.sense == 2) {
                    client.gameController.ball.y += client.gameController.ball.dy;
                }
                if (client.gameController.settings.sense == 3) {
                    client.gameController.ball.y += 2 * client.gameController.ball.dy;
                }

            }
            else if (pressedKeys.contains(leftKey)) {
                if (client.gameController.settings.sense == 1) {
                    client.gameController.ball.x -= client.gameController.ball.dx/2;
                }
                if (client.gameController.settings.sense == 2) {
                    client.gameController.ball.x -= client.gameController.ball.dx;
                }
                if (client.gameController.settings.sense == 3) {
                    client.gameController.ball.x -= 2 * client.gameController.ball.dx;
                }

            }
            else if (pressedKeys.contains(rightKey)) {
                if (client.gameController.settings.sense == 1) {
                    client.gameController.ball.x += client.gameController.ball.dx/2;
                }
                if (client.gameController.settings.sense == 2) {
                    client.gameController.ball.x += client.gameController.ball.dx;
                }
                if (client.gameController.settings.sense == 3) {
                    client.gameController.ball.x += 2 * client.gameController.ball.dx;
                }

            }
            else if (pressedKeys.contains(banishKey)) {
                Impact.banishImpact(client.gameController.ball.x, client.gameController.ball.y, client.gameController.ball.x, client.gameController.ball.y, client.gameController);
            }
            else if (pressedKeys.contains(writOfProteusKey)) {
                if (client.player.getXP() >= 100) {
                    if (client.player.isWritOfProteus()) {
                        client.player.setXP(client.player.getXP() - 100);
                        SkillTreeController.turnOnWritOfProteus(client.gameController, client);
                    }
                }
            }
            else if (pressedKeys.contains(writOfAresKey)) {
                if (client.player.getXP() >= 100) {
                    if (client.player.isWritOfAres()) {
                        client.player.setXP(client.player.getXP() - 100);
                        SkillTreeController.turnOnWritOfAres(client.gameController, client);
                    }

                }
            }
            else if (pressedKeys.contains(writOfAcesoKey)) {
                if (client.player.getXP() >= 100) {
                    if (client.player.isWritOfAceso()) {
                        client.player.setXP(client.player.getXP() - 100);
                        SkillTreeController.turnOnWritOfAceso(client.gameController, client);
                    }

                }
            }
            else if (pressedKeys.contains(writOfCerberus)) {
                if (client.player.getXP() >= 100) {
                    if (client.player.isWritOfCerberus()) {
                        client.player.setXP(client.player.getXP() - 100);
                        SkillTreeController.turnOnWritOfCerberus(client.gameController, client);
                    }

                }
            }
            else if (pressedKeys.contains(writOfEmpusa)) {
                if (client.player.getXP() >= 100) {
                    client.player.setXP(client.player.getXP() - 100);
                    BallModel.ballRadius = (int) (0.9 * BallModel.ballRadius);
                }
            }
            else if (pressedKeys.contains(writOfDolus)) {
                if (client.player.getXP() >= 100) {
                    client.player.setXP(client.player.getXP() - 100);
                    int i = 0;
                    if (client.player.isWritOfEmpusa()) {
                        i++;
                        BallModel.ballRadius = (int) (0.9 * BallModel.ballRadius);
                    }
                    if (client.player.isWritOfChiron()) {
                        i++;
                        client.gameController.ball.ballChiron = true;
                    }
                    if (client.player.isWritOfCerberus() && i < 2) {
                        SkillTreeController.turnOnWritOfCerberus(client.gameController, client);
                    }
                    if (client.player.isWritOfAres() && i < 2) {
                        SkillTreeController.turnOnWritOfAres(client.gameController, client);
                    }
                }
            }

        } else {
            if (pressedKeys.contains(upKey) && pressedKeys.contains(rightKey)) {
                if (client.gameController.settings.sense == 1) {
                    client.gameController.ball.x += client.gameController.ball.dx/2;
                    client.gameController.ball.y -= client.gameController.ball.dy/2;
                }
                if (client.gameController.settings.sense == 2) {
                    client.gameController.ball.x += client.gameController.ball.dx;
                    client.gameController.ball.y -= client.gameController.ball.dy;
                }
                if (client.gameController.settings.sense == 3) {
                    client.gameController.ball.x += 2 * client.gameController.ball.dx;
                    client.gameController.ball.y -= 2 * client.gameController.ball.dy;
                }

            } else if (pressedKeys.contains(upKey) && pressedKeys.contains(leftKey)) {
                if (client.gameController.settings.sense == 1) {
                    client.gameController.ball.x -= client.gameController.ball.dx/2;
                    client.gameController.ball.y -= client.gameController.ball.dy/2;
                }
                if (client.gameController.settings.sense == 2) {
                    client.gameController.ball.x -= client.gameController.ball.dx;
                    client.gameController.ball.y -= client.gameController.ball.dy;
                }
                if (client.gameController.settings.sense == 3) {
                    client.gameController.ball.x -= 2 * client.gameController.ball.dx;
                    client.gameController.ball.y -= 2 * client.gameController.ball.dy;
                }

            } else if (pressedKeys.contains(downKey) && pressedKeys.contains(leftKey)) {
                if (client.gameController.settings.sense == 1) {
                    client.gameController.ball.x -= client.gameController.ball.dx/2;
                    client.gameController.ball.y += client.gameController.ball.dy/2;
                }
                if (client.gameController.settings.sense == 2) {
                    client.gameController.ball.x -= client.gameController.ball.dx;
                    client.gameController.ball.y += client.gameController.ball.dy;
                }
                if (client.gameController.settings.sense == 3) {
                    client.gameController.ball.x -= 2 * client.gameController.ball.dx;
                    client.gameController.ball.y += 2 * client.gameController.ball.dy;
                }

            } else if (pressedKeys.contains(downKey) && pressedKeys.contains(rightKey)) {
                if (client.gameController.settings.sense == 1) {
                    client.gameController.ball.x += client.gameController.ball.dx/2;
                    client.gameController.ball.y += client.gameController.ball.dy/2;
                }
                if (client.gameController.settings.sense == 2) {
                    client.gameController.ball.x += client.gameController.ball.dx;
                    client.gameController.ball.y += client.gameController.ball.dy;
                }
                if (client.gameController.settings.sense == 3) {
                    client.gameController.ball.x += 2 * client.gameController.ball.dx;
                    client.gameController.ball.y += 2 * client.gameController.ball.dy;
                }

            }
        }

    }





    //===========================       during quake attack      =============================




    public void handleKeyPressedCombinationDuringQuakeAttack(ClientModel client) {
        if (pressedKeys.size() == 1) {

            if (pressedKeys.contains(leftKey)) {
                if (client.gameController.settings.sense == 1) {
                    client.gameController.ball.y -= client.gameController.ball.dy/2;
                }
                if (client.gameController.settings.sense == 2) {
                    client.gameController.ball.y -= client.gameController.ball.dy;
                }
                if (client.gameController.settings.sense == 3) {
                    client.gameController.ball.y -= 2 * client.gameController.ball.dy;
                }

            }
            else if (pressedKeys.contains(rightKey)) {
                if (client.gameController.settings.sense == 1) {
                    client.gameController.ball.y += client.gameController.ball.dy/2;
                }
                if (client.gameController.settings.sense == 2) {
                    client.gameController.ball.y += client.gameController.ball.dy;
                }
                if (client.gameController.settings.sense == 3) {
                    client.gameController.ball.y += 2 * client.gameController.ball.dy;
                }

            }
            else if (pressedKeys.contains(upKey)) {
                if (client.gameController.settings.sense == 1) {
                    client.gameController.ball.x -= client.gameController.ball.dx/2;
                }
                if (client.gameController.settings.sense == 2) {
                    client.gameController.ball.x -= client.gameController.ball.dx;
                }
                if (client.gameController.settings.sense == 3) {
                    client.gameController.ball.x -= 2 * client.gameController.ball.dx;
                }

            }
            else if (pressedKeys.contains(downKey)) {
                if (client.gameController.settings.sense == 1) {
                    client.gameController.ball.x += client.gameController.ball.dx/2;
                }
                if (client.gameController.settings.sense == 2) {
                    client.gameController.ball.x += client.gameController.ball.dx;
                }
                if (client.gameController.settings.sense == 3) {
                    client.gameController.ball.x += 2 * client.gameController.ball.dx;
                }

            }

        } else {
            if (pressedKeys.contains(downKey) && pressedKeys.contains(leftKey)) {
                if (client.gameController.settings.sense == 1) {
                    client.gameController.ball.x += client.gameController.ball.dx/2;
                    client.gameController.ball.y -= client.gameController.ball.dy/2;
                }
                if (client.gameController.settings.sense == 2) {
                    client.gameController.ball.x += client.gameController.ball.dx;
                    client.gameController.ball.y -= client.gameController.ball.dy;
                }
                if (client.gameController.settings.sense == 3) {
                    client.gameController.ball.x += 2 * client.gameController.ball.dx;
                    client.gameController.ball.y -= 2 * client.gameController.ball.dy;
                }

            } else if (pressedKeys.contains(downKey) && pressedKeys.contains(rightKey)) {
                if (client.gameController.settings.sense == 1) {
                    client.gameController.ball.x -= client.gameController.ball.dx/2;
                    client.gameController.ball.y -= client.gameController.ball.dy/2;
                }
                if (client.gameController.settings.sense == 2) {
                    client.gameController.ball.x -= client.gameController.ball.dx;
                    client.gameController.ball.y -= client.gameController.ball.dy;
                }
                if (client.gameController.settings.sense == 3) {
                    client.gameController.ball.x -= 2 * client.gameController.ball.dx;
                    client.gameController.ball.y -= 2 * client.gameController.ball.dy;
                }

            } else if (pressedKeys.contains(upKey) && pressedKeys.contains(rightKey)) {
                if (client.gameController.settings.sense == 1) {
                    client.gameController.ball.x -= client.gameController.ball.dx/2;
                    client.gameController.ball.y += client.gameController.ball.dy/2;
                }
                if (client.gameController.settings.sense == 2) {
                    client.gameController.ball.x -= client.gameController.ball.dx;
                    client.gameController.ball.y += client.gameController.ball.dy;
                }
                if (client.gameController.settings.sense == 3) {
                    client.gameController.ball.x -= 2 * client.gameController.ball.dx;
                    client.gameController.ball.y += 2 * client.gameController.ball.dy;
                }

            } else if (pressedKeys.contains(upKey) && pressedKeys.contains(leftKey)) {
                if (client.gameController.settings.sense == 1) {
                    client.gameController.ball.x += client.gameController.ball.dx/2;
                    client.gameController.ball.y += client.gameController.ball.dy/2;
                }
                if (client.gameController.settings.sense == 2) {
                    client.gameController.ball.x += client.gameController.ball.dx;
                    client.gameController.ball.y += client.gameController.ball.dy;
                }
                if (client.gameController.settings.sense == 3) {
                    client.gameController.ball.x += 2 * client.gameController.ball.dx;
                    client.gameController.ball.y += 2 * client.gameController.ball.dy;
                }

            }
        }

    }
}

