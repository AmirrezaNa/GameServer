package controller.gameLoop.phase2.BossFight;

import controller.data.DataManager;
import controller.data.SoundEffects;
import controller.gameController.Constants;
import controller.gameController.GameRestart;
import model.ClientModel;
import model.entity.BallModel;
import model.entity.enemy.boss.SmileyModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class FinalBossFrame  {
    FinalBossPanel gamePanel;

    static Toolkit toolkit = Toolkit.getDefaultToolkit();
    static Dimension screenSize = toolkit.getScreenSize();
    public static int x = 0;
    public static int y = 0;
    public static int width = screenSize.width;
    public static int height = screenSize.height;

    public FinalBossFrame(ClientModel client) {


        gamePanel = new FinalBossPanel(client);
        Thread thread = new Thread(gamePanel);
        thread.start();

        check(client);
    }




    public void check(ClientModel client) {
        Timer timer1 = new Timer(100, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    checkGameOver(client);
                    checkWinner(client);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        timer1.start();

    }

    public void checkGameOver(ClientModel client) throws IOException {
        if (client.gameController.ball != null) {
            if (client.gameController.ball.HP <= 0) {
                client.gameController.pause = true;
                GameRestart.restartGame(client);

                if (DataManager.checkPlayerExists(client.player.getName())) {
                    DataManager.updatePlayerData(client.player);
                } else {
                    DataManager.createPlayerData(client.player);
                }
                SoundEffects.playSound(Constants.END_SOUND_PATH);

            }
        }
    }

    public void checkWinner(ClientModel client) {
        if (client.gameController.ball != null) {
            boolean playerHasWonFinalBoss = client.gameController.smiley.enemyHealth <= 0;
            if (playerHasWonFinalBoss) {
                SmileyModel.imageIcon = "E:\\java projects\\ApProjectPhase1\\src\\controller\\data\\store\\apIcons\\dead.png";
                displayWin(client);
            }

        }
    }


    Timer timer1;
    boolean isAnimationComplete = false;

    public void displayWin(ClientModel client) {
        FinalBossPanel.finalBossOver = true;
        timer1 = new Timer(100, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isAnimationComplete) {
                    if (width >= 0) {
                        // reduce width gradually
                        x += 1;
                        BallModel.ballRadius++;
                        width -= 2;
                    }
                    if (height >= 0) {
                        // reduce height gradually
                        BallModel.ballRadius++;
                        y += 1;
                        height -= 2;

                    }
                    if (width <= 0 && height <= 0) {
                        isAnimationComplete = true;
                        ((Timer) e.getSource()).stop();
                        displayWinnerWindow(client);
                    }
                }
            }
        });
        timer1.start();
    }


    public void displayWinnerWindow(ClientModel client) {
        if (client.gameController.ball != null) {

            try {
                if (DataManager.checkPlayerExists(client.player.getName())) {
                    DataManager.updatePlayerData(client.player);
                } else {
                    DataManager.createPlayerData(client.player);
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            SoundEffects.playSound(Constants.WINNER_SOUND_PATH);

        }
    }
}
