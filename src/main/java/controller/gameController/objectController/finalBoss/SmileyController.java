package controller.gameController.objectController.finalBoss;

import controller.gameController.GameController;
import controller.gameController.SmileyAttacksController;

public class SmileyController {


    public static void updateSmiley(GameController gameController) {
        if (gameController.smiley != null) {
            if (gameController.smiley.y < 100) {
                gameController.smiley.y += gameController.smiley.dy;
            } else {
                if (gameController.smiley.dx == 0) {
                    gameController.smiley.dx = 1;
                }
                if (gameController.smiley.dx > 0) {
                    if (gameController.smiley.x > 750) {
                        gameController.smiley.dx = -gameController.smiley.dx;
                    }
                }
                if (gameController.smiley.dx < 0) {
                    if (gameController.smiley.x < 600) {
                        gameController.smiley.dx = -gameController.smiley.dx;
                    }
                }
                gameController.smiley.x += gameController.smiley.dx;
            }
            if (gameController.smiley.enemyHealth < 100 && !gameController.smiley.punchExists) {
                gameController.smiley.punchExists = true;
                gameController.newPunch(550, 0);
                SmileyAttacksController.startPowerPunchAttack(gameController);
            }
        }
    }

}

