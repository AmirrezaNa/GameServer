package controller.gameController.objectController.finalBoss;

import controller.gameController.GameController;

public class RightHandController {

    public static void updateRightHand(GameController gameController) {
        if (gameController.rightHand != null) {

            if (gameController.smiley.squeezeAttack) {
                if ((gameController.rightHand.x > (gameController.epsilonFrame.epsilonFrame.x + gameController.epsilonFrame.epsilonFrame.width))) {
                    gameController.rightHand.x += gameController.rightHand.dx;
                }
                else {
                    gameController.rightHand.x -= gameController.rightHand.dx;
                }

            } else if (gameController.rightHand.x > 1000) {
                gameController.rightHand.x += gameController.rightHand.dx;
            } else if (gameController.rightHand.x < 1000) {
                gameController.rightHand.x -= gameController.rightHand.dx;
            } else {
                if (gameController.rightHand.dy > 0) {
                    if (gameController.rightHand.y > 500) {
                        gameController.rightHand.dy = -gameController.rightHand.dy;
                    }
                }
                if (gameController.rightHand.dy < 0) {
                    if (gameController.rightHand.y < 200) {
                        gameController.rightHand.dy = -gameController.rightHand.dy;
                    }
                }
                gameController.rightHand.y += gameController.rightHand.dy;
            }
        }
    }


}

