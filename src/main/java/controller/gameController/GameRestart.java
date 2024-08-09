package controller.gameController;

import model.ClientModel;
import model.entity.BallModel;

public class GameRestart {


    // restarting the game ================================================================

    public static void restartGame(ClientModel client) {
        client.gameController.numberOfBullets = client.gameController.bullets.size();
        client.gameController.bullets.clear();
        client.gameController.enemies2.clear();
        client.gameController.enemies1.clear();
        client.gameController.bullets.clear();
        client.gameController.collectibles.clear();
        client.gameController.ball.HP = 100;
        BallModel.ballRadius = 20;
        client.gameFrame.count = 0;
        client.gameFrame.x = 300;
        client.gameFrame.y = 50;
        if (client.gameFrame2 != null) {
            client.gameFrame2.width = 600;
            client.gameFrame.height = 600;
        }
        client.gameController.stopWave = true;
//        GameFrame.thread.interrupt();
    }
}
