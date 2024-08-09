package controller.gameController;

import controller.gameLoop.phase2.normalAndMiniBossEnemies.GamePanel2;

public class FrameOfObject {


    public static void getCollidedFrames(GameController gameController) {
        for (int i = 0; i < gameController.createdFrames.length; i++) {
            if (i == getFrameOfBall(gameController)) {
                continue;
            }
            int k = getFrameOfBall(gameController);
            if (k != -1) {
                int xMini = gameController.createdFrames[i].x + 3;
                int yMini = gameController.createdFrames[i].y + 3;
                int xMaxi = gameController.createdFrames[i].x + gameController.createdFrames[i].width - 3;
                int yMaxi = gameController.createdFrames[i].y + gameController.createdFrames[i].height - 3;


                int xMink = gameController.createdFrames[k].x + 3;
                int yMink = gameController.createdFrames[k].y + 3;
                int xMaxk = gameController.createdFrames[k].x + gameController.createdFrames[k].width - 3;
                int yMaxk = gameController.createdFrames[k].y + gameController.createdFrames[k].height - 3;

                if (((xMini >= xMink && xMini <= xMaxk) || (xMink >= xMini && xMink <= xMaxi))
                        && ((yMini >= yMink && yMini <= yMaxk) || (yMink >= yMini && yMink <= yMaxi))) {
                    gameController.collidedFrames.add(i);
                } else {
                    if (gameController.collidedFrames.contains(i)) {
                        gameController.collidedFrames.remove(i);
                    }
                }
            }
        }

    }



    public static int getFrameOfBall(GameController gameController) {
        if (gameController.framesCreated) {
            for (int i = 0; i < gameController.createdFrames.length; i++) {
                int xMin = gameController.createdFrames[i].x;
                int yMin = gameController.createdFrames[i].y;
                int xMax = gameController.createdFrames[i].x + gameController.createdFrames[i].width;
                int yMax = gameController.createdFrames[i].y + gameController.createdFrames[i].height;
                if (gameController.ball.x > xMin && gameController.ball.x < xMax
                        && gameController.ball.y > yMin && gameController.ball.y < yMax) {
                    return i;
                }
            }
        }
        return -1;
    }


    public static void FrameOfBullet(GameController gameController) {
        for (int j = 0; j < gameController.bullets.size(); j++) {
            for (int i = 0; i < gameController.createdFrames.length; i++) {
                if (gameController.collidedFrames.contains(i)) {
                    int xMin = gameController.createdFrames[i].x;
                    int yMin = gameController.createdFrames[i].y;
                    int xMax = gameController.createdFrames[i].x + gameController.createdFrames[i].width;
                    int yMax = gameController.createdFrames[i].y + gameController.createdFrames[i].height;
                    if (gameController.bullets.get(j).x > xMin && gameController.bullets.get(j).x < xMax
                            && gameController.bullets.get(j).y > yMin && gameController.bullets.get(j).y < yMax) {
                        gameController.bullets.get(j).bulletFrame = i;
                    }
                }

            }
        }
    }
}

