package controller.gameController.objectController.finalBoss;

import controller.gameController.GameController;

public class LeftHandController {

    public static void updateLeftHand(GameController gameController) {
        if (gameController.leftHand != null) {
            if (gameController.smiley.squeezeAttack) {
                if ((gameController.leftHand.x < gameController.epsilonFrame.epsilonFrame.x)) {
                    gameController.leftHand.x += gameController.leftHand.dx;
                }
                else {
                    gameController.leftHand.x -= gameController.leftHand.dx;
                }

            } else if (gameController.leftHand.x < 400) {
                gameController.leftHand.x += gameController.leftHand.dx;
            } else if (gameController.leftHand.x > 400) {
                gameController.leftHand.x -= gameController.leftHand.dx;
            } else {
                if (gameController.leftHand.dy > 0) {
                    if (gameController.leftHand.y > 500) {
                        gameController.leftHand.dy = -gameController.leftHand.dy;
                    }
                }
                if (gameController.leftHand.dy < 0) {
                    if (gameController.leftHand.y < 200) {
                        gameController.leftHand.dy = -gameController.leftHand.dy;
                    }
                }
                gameController.leftHand.y += gameController.leftHand.dy;
            }
        }
    }

}