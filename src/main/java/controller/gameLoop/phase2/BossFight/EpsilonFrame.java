package controller.gameLoop.phase2.BossFight;

import controller.gameLoop.phase2.normalAndMiniBossEnemies.CreateFrames;
import model.ClientModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class EpsilonFrame implements Serializable {
    private static final long serialVersionUID = 1L;
    public CreateFrames epsilonFrame;

    public EpsilonFrame(ClientModel client) {
        client.gameController.gameOver = false;

        this.epsilonFrame = new CreateFrames(450, 300, 400, 400);
        changeGameFrameSize(client, this.epsilonFrame);
    }


    static Timer timer;
    int athena = 0;

    public void changeGameFrameSize(ClientModel client, CreateFrames epsilonFrame) {
        // this timer reduces the frame size ========================================
        timer = new Timer(100, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
//                try {
//                    checkGameOver();
//                } catch (IOException ex) {
//                    throw new RuntimeException(ex);
//                }
                if (client.player.isWritOfAthena()) {
                    athena++;
                    if (athena % 5 != 0) {
                        if (!client.gameController.pause && !client.gameController.ball.ballSlumber) {
                            int x = epsilonFrame.x;
                            int y = epsilonFrame.y;
                            int width = epsilonFrame.width;
                            int height = epsilonFrame.height;
                            if (width > 300 && !client.gameController.smiley.squeezeAttack) {
                                // reduce width gradually
                                x += 3;
                                width -= 6;
                                epsilonFrame.x = x;
                                epsilonFrame.width = width;
                            }
                            if (height < 400 && !client.gameController.smiley.squeezeAttack) {
                                y--;
                                height += 2;
                                epsilonFrame.y = y;
                                epsilonFrame.height = height;
                            }
                        }
                    }
                } else {
                    if (!client.gameController.pause && !client.gameController.ball.ballSlumber) {
                        int x = epsilonFrame.x;
                        int y = epsilonFrame.y;
                        int width = epsilonFrame.width;
                        int height = epsilonFrame.height;
                        if (width > 300 && !client.gameController.smiley.squeezeAttack) {
                            // reduce width gradually
                            x += 3;
                            width -= 6;
                            epsilonFrame.x = x;
                            epsilonFrame.width = width;
                        }
                        if (height < 400 && !client.gameController.smiley.squeezeAttack) {
                            y--;
                            height += 2;
                            epsilonFrame.y = y;
                            epsilonFrame.height = height;
                        }
                    }
                }
            }
        });
        timer.start();

        // ==========================================================================

    }
}

