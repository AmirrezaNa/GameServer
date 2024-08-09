package controller.gameLoop.phase2.normalAndMiniBossEnemies;

import controller.gameController.FrameOfObject;
import controller.gameController.GameController;
import controller.gameController.WaveController;
import controller.gameController.collisions.phase2.FrameCollisions2;
import controller.gameController.collisions.phase2.ObjectCollisions2;
import controller.gameController.objectController.BallAngleController;
import controller.gameController.objectController.BallController;
import controller.gameController.objectController.BallDirectionController;
import controller.gameController.objectController.BulletController;
import controller.gameController.objectController.enemies.*;
import model.ClientModel;
import model.entity.BallAngle;
import model.entity.BallDirection;
import model.entity.BallModel;


import java.awt.*;

public class GamePanel2 implements Runnable {

    ClientModel client;
    public GameInternalFrame gameInternalFrame;

    public static int mainFrame;//shows the frame of the ball

    public GamePanel2(ClientModel client) {
        this.client = client;

        this.gameInternalFrame = new GameInternalFrame(client, client.gameController);

//        ball = GameController.ball;
        BallController.getBallIntoFrame2(client.gameController);
        BallController.checkIfBallInArchmire(client.gameController);
        BallController.checkIfBallInArchmireTrace(client.gameController);
        BallController.checkIfBallInBlackOrb(client.gameController);
        client.gameController.createBallDirection();
        client.gameController.createBallAngle();
        WaveController.setTimerForOmenoct(client.gameController);
        WaveController.setTimerForNecropick(client.gameController);
        WaveController.setTimerForArchmire(client.gameController);
        WaveController.setTimerForWyrm(client.gameController);
        WaveController.setTimerForBarricados1(client.gameController);
        WaveController.setTimerForBarricados2(client.gameController);
        WaveController.setTimerForBlackOrb(client.gameController);


    }

    @Override
    public void run() {

        while (!this.client.gameController.phase2Over) {


            update(this.client.gameController, this.client);


            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }


    public void update(GameController gameController, ClientModel client) {
        if (!gameController.pause) {
            mainFrame = FrameOfObject.getFrameOfBall(gameController);

            BallController.updateTheBall(gameController);
            BallDirectionController.updateBallDirectionPanel2(gameController);
            BallAngleController.updateBallAngle(gameController);

            BulletController.updateBullet(gameController);
            if (!gameController.ball.ballSlumber) {
                BulletController.updateEnemyBullet(gameController);

                OmenoctController.updateOmenoct(gameController);
                NecropickController.update(gameController);
                ArchmireController.updateArchmire(gameController);
                WyrmController.updateWyrm(gameController);
                BarricadosController1.updateBarricados1(gameController);
                BarricadosController2.updateBarricados2(gameController);
                BlackOrbController.updateBlackOrb(gameController);

            }

            FrameOfObject.FrameOfBullet(gameController);
            FrameCollisions2.checkFramesCollisions2(gameController, client);
            ObjectCollisions2.checkCollisionsPhase2(gameController, client);

        }
    }
}
