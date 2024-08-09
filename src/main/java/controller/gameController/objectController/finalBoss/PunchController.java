package controller.gameController.objectController.finalBoss;

import controller.gameController.GameController;

public class PunchController {

    public static void updatePunch(GameController gameController) {
        if (gameController.punch != null) {
            if (gameController.smiley.powerPunchAttack || gameController.smiley.quakeAttack || gameController.smiley.slapAttack) {
                if ((gameController.punch.y < gameController.epsilonFrame.epsilonFrame.y)) {
                    gameController.punch.y += gameController.punch.dy;
                }

            } else if (gameController.punch.y < 250) {
                gameController.punch.y += gameController.punch.dy;
            } else {
                gameController.punch.y -= gameController.punch.dy;
            }
        }
    }
}

