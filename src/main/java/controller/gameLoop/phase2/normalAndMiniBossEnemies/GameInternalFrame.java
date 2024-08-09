package controller.gameLoop.phase2.normalAndMiniBossEnemies;

import controller.gameController.FrameOfObject;
import controller.gameController.GameController;
import controller.gameController.collisions.phase2.FrameCollisions2;
import model.ClientModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

public class GameInternalFrame {
    public ClientModel client;

    public CreateFrames createFrames0;
    public CreateFrames createFrames1;
    public CreateFrames createFrames2;
    public CreateFrames createFrames3;
    public Timer timer;
    private static final Object lock = new Object();
    static int athena = 0;



    public GameInternalFrame(ClientModel client, GameController gameController) {
        this.client = client;
        gameController.gameOver = false;
        gameController.createdFrames = new CreateFrames[4];
        this.timer = null;
        createInternalFrames(gameController);
        this.createFrames0 = null;
        this.createFrames1 = null;
        this.createFrames2 = null;
        this.createFrames3 = null;
    }

    private void createInternalFrames(GameController gameController) {
        this.createFrames0 = new CreateFrames(450, 100, 400, 300);
        gameController.createdFrames[0] = this.createFrames0;



        this.createFrames1 = new CreateFrames(50, 50, 400, 300);
        gameController.createdFrames[1] = this.createFrames1;


        this.createFrames2 = new CreateFrames(850, 50, 400, 300);
        gameController.createdFrames[2] = this.createFrames2;


        this.createFrames3 = new CreateFrames(450, 400, 400, 300);
        gameController.createdFrames[3] = this.createFrames3;


        new Thread(() -> {
            while (true) {
                synchronized (this.createFrames0) {
                    changeGameFrameSize(0, this.client.gameController, this.client);
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                synchronized (this.createFrames1) {
                    changeGameFrameSize(1, this.client.gameController, this.client);
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                synchronized (this.createFrames2) {
                    changeGameFrameSize(2, this.client.gameController, this.client);
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                synchronized (this.createFrames3) {
                    changeGameFrameSize(3, this.client.gameController, this.client);
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        this.client.gameController.framesCreated = true;
    }



    public void changeGameFrameSize(int i, GameController gameController, ClientModel client) {

        this.timer = new Timer(100, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {


                // this timer reduces the frame size ========================================
                synchronized (lock) {
                    if (!FrameCollisions2.frameCollided(i, gameController) && !gameController.pause && !gameController.ball.ballSlumber) {
                        if (i == FrameOfObject.getFrameOfBall(gameController)) {
                            athena++;
                            if (athena % 5 != 0) {
                                int x = client.gameController.createdFrames[i].x;
                                int y = client.gameController.createdFrames[i].y;
                                int width = client.gameController.createdFrames[i].width;
                                int height = client.gameController.createdFrames[i].height;
                                if (width > 300) {
                                    // reduce width gradually
                                    x++;
                                    width--;
                                    client.gameController.createdFrames[i].x = x;
                                    client.gameController.createdFrames[i].width = width;
                                }
                                if (height > 250) {
                                    // reduce height gradually
                                    y += 1;
                                    height--;
                                    client.gameController.createdFrames[i].y = y;
                                    client.gameController.createdFrames[i].height = height;
                                }
                            }
                        }
                        else {
                            int x = client.gameController.createdFrames[i].x;
                            int y = client.gameController.createdFrames[i].y;
                            int width = client.gameController.createdFrames[i].width;
                            int height = client.gameController.createdFrames[i].height;
                            if (width > 300) {
                                // reduce width gradually
                                x++;
                                width--;
                                client.gameController.createdFrames[i].x = x;
                                client.gameController.createdFrames[i].width = width;
                            }
                            if (height > 250) {
                                // reduce height gradually
                                y += 1;
                                height--;
                                client.gameController.createdFrames[i].y = y;
                                client.gameController.createdFrames[i].height = height;
                            }
                        }
                    }
                }
            }
        });
        timer.start();

        // ==========================================================================

    }
}
