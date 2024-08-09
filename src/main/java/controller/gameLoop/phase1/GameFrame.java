package controller.gameLoop.phase1;

import controller.data.DataManager;
import controller.data.SoundEffects;
import controller.gameController.Constants;
import controller.gameController.GameRestart;
import controller.gameLoop.phase2.normalAndMiniBossEnemies.GameFrame2;
import model.ClientModel;
import model.entity.BallModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GameFrame{

    public GamePanel gamePanel;
    public int width;
    public int height;
    public int x;
    public int y;
    public Thread thread;
    public int count;
    public boolean countDown;
    public int athena;
    boolean isAnimationComplete;
    public Timer timer;
    public GameFrame(ClientModel client) {

        this.athena = 0;
        this.isAnimationComplete = false;
        this.timer = null;

        this.width = 600;
        this.height = 600;
        this.x = 300;
        this.y = 50;
        this.count = 0;
        this.countDown = true;
        this.gamePanel = null;

//        this.gamePanel = new GamePanel(client.gameController, client);
//
//        thread = new Thread(gamePanel);
//        thread.start();

        countToTenSeconds(client.gameFrame, client);
        check(client.gameFrame, client);

    }





    public void countToTenSeconds(GameFrame gameFrame, ClientModel client) {

        if (this.countDown) {
            Timer timer = new Timer(1000, new ActionListener() { // Timer with 1-second (1000 milliseconds) delay
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (gameFrame != null) {
                        gameFrame.count++;

                        if (gameFrame.count == 10) {
                            gameFrame.countDown = false;
                            changeGameFrameSize(gameFrame, client);
                            ((Timer) e.getSource()).stop(); // Stop the timer after reaching 10 seconds
                        }
                    }

                }
            });

            timer.start(); // Start the timer
        }

    }



    public void changeGameFrameSize(GameFrame gameFrame, ClientModel client) {
        if (!gameFrame.countDown) {

            // this timer reduces the frame size ========================================
            gameFrame.timer = new Timer(100, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        gameFrame.checkGameOver(gameFrame, client);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    if (client.player.isWritOfAthena()) {
                        gameFrame.athena++;
                        if (gameFrame.athena % 5 != 0) {
                            if (gameFrame.width > 300 && !client.gameController.pause && !client.gameController.ball.ballSlumber) {
                                // reduce width gradually
                                gameFrame.x += 1;
                                gameFrame.width -= 2;
                            }
                            if (gameFrame.height > 300 && !client.gameController.pause && !client.gameController.ball.ballSlumber) {
                                // reduce height gradually
                                gameFrame.y += 1;
                                gameFrame.height -= 2;

                            }
                        }

                    } else {

                        if (gameFrame.width > 300 && !client.gameController.pause && !client.gameController.ball.ballSlumber) {
                            // reduce width gradually
                            gameFrame.x += 1;
                            gameFrame.width -= 2;

                        }
                        if (gameFrame.height > 300 && !client.gameController.pause && !client.gameController.ball.ballSlumber) {
                            // reduce height gradually
                            gameFrame.y += 1;
                            gameFrame.height -= 2;

                        }
                    }
                    if (client.gameController.enemies1.size() + client.gameController.enemies2.size() == 10) {
                        gameFrame.countDown = true;
                        SoundEffects.playSound(Constants.CHANGE_WAVE_SOUND_PATH);
                        ((Timer) e.getSource()).stop(); // Stop the timer
                        Timer delayTimer = new Timer(20000, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                ((Timer) e.getSource()).stop(); // Stop the delay timer
                                gameFrame.timer.start(); // Restart the original timer
                            }
                        });
                        delayTimer.setRepeats(false); // Execute the delay timer only once
                        delayTimer.start(); // Start the delay timer

                    }
                    if (client.gameController.enemies1.size() + client.gameController.enemies2.size() == 25) {
                        gameFrame.countDown = true;
                        SoundEffects.playSound(Constants.CHANGE_WAVE_SOUND_PATH);
                        ((Timer) e.getSource()).stop(); // Stop the timer
                        Timer delayTimer2 = new Timer(20000, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                ((Timer) e.getSource()).stop(); // Stop the delay timer
                                gameFrame.timer.start(); // Restart the original timer
                            }
                        });
                        delayTimer2.setRepeats(false); // Execute the delay timer only once
                        delayTimer2.start(); // Start the delay timer
                    }

                }
            });
            gameFrame.timer.start();

            // ==========================================================================
        }

    }


    public void check(GameFrame gameFrame, ClientModel client) {
        Timer timer1 = new Timer(100, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    checkGameOver(gameFrame, client);
                    checkPhase1Over(gameFrame, client);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                if (client.gameController.phase1over) {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer1.start();

    }

    public void checkGameOver(GameFrame gameFrame, ClientModel client) throws IOException {
        if (client.gameController.ball != null) {
            if (client.gameController.ball.HP <= 0) {
                if (!client.gameController.phase1over) {
                    client.gameController.phase1over = true;
                    GameRestart.restartGame(client);
                    if (DataManager.checkPlayerExists(client.player.getName())) {
                        DataManager.updatePlayerData(client.player);
                    } else {
                        DataManager.createPlayerData(client.player);
                    }
                    SoundEffects.playSound(Constants.END_SOUND_PATH);
                    System.out.println("game over!");
//                    GameOverFrame gameOverFrame = new GameOverFrame();
//                    FinalBossFrame finalBossFrame = new FinalBossFrame();
//                    GameFrame2 gameFrame2 = new GameFrame2();
                }
            }
        }
    }

    public void checkPhase1Over(GameFrame gameFrame, ClientModel client) {
        if (client.gameController.ball != null) {
            boolean playerHasPassedPhase1 = true;
            if (client.gameController.enemies1.size() + client.gameController.enemies2.size() < 35) {
                playerHasPassedPhase1 = false;
            }
            if (client.gameController.enemies1.size() + client.gameController.enemies2.size() == 35) {
                for (int i = 0; i < client.gameController.enemies1.size(); i++) {
                    if (client.gameController.enemies1.get(i).enemyHealth > 0) {
                        playerHasPassedPhase1 = false;
                    }
                }
                for (int j = 0; j < client.gameController.enemies2.size(); j++) {
                    if (client.gameController.enemies2.get(j).enemyHealth > 0) {
                        playerHasPassedPhase1 = false;
                    }
                }
            }
            if (playerHasPassedPhase1) {
                displayWin(gameFrame, client);
            }

        }
    }




    public void displayWin(GameFrame gameFrame, ClientModel client) {
        client.gameController.phase1over = true;
        Timer timer1 = new Timer(100, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isAnimationComplete) {
                    if (width >= 0) {
                        // reduce width gradually
                        gameFrame.x += 1;
                        BallModel.ballRadius++;
                        gameFrame.width -= 2;

                    }
                    if (height >= 0) {
                        // reduce height gradually
                        BallModel.ballRadius++;
                        gameFrame.y += 1;
                        gameFrame.height -= 2;
                    }
                    if (width <= 0 && height <= 0) {
                        isAnimationComplete = true;
                        ((Timer) e.getSource()).stop();
                        displayEndOfPhase1(gameFrame, client);
                        client.gameController.wave++;
                        client.gameFrame2 = new GameFrame2(client);
                    }
                }
            }
        });
        timer1.start();
    }


    public void displayEndOfPhase1(GameFrame gameFrame, ClientModel client) {

        if (client.gameController.ball != null) {

            client.gameController.phase1over = true;
            int hp = client.gameController.ball.HP;
            GameRestart.restartGame(client);
            client.gameController.ball.HP = hp;
        }
    }

}


