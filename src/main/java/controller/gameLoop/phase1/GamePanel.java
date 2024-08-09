package controller.gameLoop.phase1;

import controller.gameController.GameController;
import controller.gameController.WaveController;
import controller.gameController.collisions.phase1.FrameCollision;
import controller.gameController.collisions.phase1.ObjectCollision;
import controller.gameController.objectController.BallAngleController;
import controller.gameController.objectController.BallController;
import controller.gameController.objectController.BallDirectionController;
import controller.gameController.objectController.BulletController;
import controller.gameController.objectController.enemies.Enemy1Controller;
import controller.gameController.objectController.enemies.Enemy2Controller;
import model.ClientModel;

public class GamePanel implements Runnable{

    GameController gameController;
    ClientModel client;

    public GamePanel(GameController gameController, ClientModel client) {
        this.gameController = gameController;
        this.client = client;
        gameController.newBall(client);
        gameController.createBallDirection();
        gameController.createBallAngle();
        WaveController.setTimerForEnemy1(client);
        WaveController.setTimerForEnemy2(client);

    }
    @Override
    public void run() {
        while (!this.gameController.phase1over) {


            update();


            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void update() {
        if (!this.gameController.pause) {
            BallController.updateTheBall(this.gameController);
            BallDirectionController.updateBallDirection(this.gameController);
            BallAngleController.updateBallAngle(this.gameController);
            BulletController.updateBullet(this.gameController);
            FrameCollision.checkFrameCollisions(this.gameController, this.client.gameFrame);
            ObjectCollision.checkObjectsCollisions(this.gameController, this.client);
            if (!this.gameController.ball.ballSlumber) {
                Enemy1Controller.updateEnemy1(this.gameController);
                Enemy2Controller.updateEnemy2(this.gameController);
            }
        }
    }
}
