package controller.gameLoop.phase2.BossFight;

import controller.gameController.SmileyAttacksController;
import controller.gameController.collisions.bossFight.FrameCollisionBossFight;
import controller.gameController.collisions.bossFight.ObjectCollisionBossFight;
import controller.gameController.objectController.BallAngleController;
import controller.gameController.objectController.BallController;
import controller.gameController.objectController.BallDirectionController;
import controller.gameController.objectController.BulletController;
import controller.gameController.objectController.finalBoss.LeftHandController;
import controller.gameController.objectController.finalBoss.PunchController;
import controller.gameController.objectController.finalBoss.RightHandController;
import controller.gameController.objectController.finalBoss.SmileyController;
import model.ClientModel;
import model.entity.BallAngle;
import model.entity.BallDirection;
import model.entity.BallModel;
import model.entity.enemy.boss.LeftHandModel;
import model.entity.enemy.boss.PunchModel;
import model.entity.enemy.boss.RightHandModel;
import model.entity.enemy.boss.SmileyModel;

import javax.swing.*;
import java.awt.*;

public class FinalBossPanel implements Runnable {


    ClientModel client;
    public static boolean finalBossOver;


    public FinalBossPanel(ClientModel client) {
        this.client = client;
        client.gameController.phase2Over = true;
        finalBossOver = false;

        client.gameController.epsilonFrame = new EpsilonFrame(client);
        BallController.getBallIntoFinalFrame(client.gameController);
        client.gameController.createBallDirection();
        client.gameController.createBallAngle();
        client.gameController.newSmiley(550, 0);
        client.gameController.newRightHand(client.gameFrame2.width, (double) client.gameFrame2.height / 2);
        client.gameController.newLeftHand(0, (double) client.gameFrame2.height / 2);


        SmileyAttacksController.startSmileyAttacks(client.gameController);

    }


    @Override
    public void run() {

        while (!finalBossOver) {


            update(this.client);



            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }


    public void update(ClientModel client) {
        if (!client.gameController.pause) {

            FrameCollisionBossFight.checkBossFightFrameCollisions(client.gameController);

            BallController.updateTheBall(client.gameController);
            BallDirectionController.updateBallDirectionFinalBoss(client.gameController);
            BallAngleController.updateBallAngle(client.gameController);

            BulletController.updateBullet(client.gameController);
            if (!client.gameController.ball.ballSlumber) {
                BulletController.updateEnemyBullet(client.gameController);

                SmileyController.updateSmiley(client.gameController);
                RightHandController.updateRightHand(client.gameController);
                LeftHandController.updateLeftHand(client.gameController);
                PunchController.updatePunch(client.gameController);
            }

            ObjectCollisionBossFight.checkCollisionsPhase2(client.gameController);
            FrameCollisionBossFight.checkBossFightFrameCollisions(client.gameController);


        }
    }
}