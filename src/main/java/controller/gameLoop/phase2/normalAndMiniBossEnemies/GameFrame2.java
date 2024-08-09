package controller.gameLoop.phase2.normalAndMiniBossEnemies;

import controller.data.DataManager;
import controller.data.SoundEffects;
import controller.gameController.Constants;
import controller.gameController.GameController;
import controller.gameController.GameRestart;
import controller.gameLoop.phase2.BossFight.FinalBossFrame;
import model.ClientModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GameFrame2{



    GamePanel2 gamePanel;

    static Toolkit toolkit = Toolkit.getDefaultToolkit();
    static Dimension screenSize = toolkit.getScreenSize();
    public int width;
    public int height;

    public GameFrame2(ClientModel client) {
        client.gameController.phase1over = true;
        this.width = screenSize.width;
        this.height = screenSize.height;

        gamePanel = new GamePanel2(client);
        Thread thread = new Thread(gamePanel);
        thread.start();



        check(client.gameController, client);
    }


    public void check(GameController gameController, ClientModel client) {
        Timer timer1 = new Timer(100, new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    checkGameOver(gameController, client);
                    checkWinner(gameController, client);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                if (gameController.phase2Over) {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer1.start();

    }

    public void checkGameOver(GameController gameController, ClientModel client) throws IOException {
        if (gameController.ball != null) {
            if (gameController.ball.HP <= 0) {
                if (!gameController.phase2Over) {
                    gameController.phase2Over = true;
                    GameRestart.restartGame(client);

                    if (DataManager.checkPlayerExists(client.player.getName())) {
                        DataManager.updatePlayerData(client.player);
                    } else {
                        DataManager.createPlayerData(client.player);
                    }
                    SoundEffects.playSound(Constants.END_SOUND_PATH);
//                    GameOverFrame gameOverFrame = new GameOverFrame();
//                    FinalBossFrame finalBossFrame = new FinalBossFrame();
                }
            }
        }
    }

    public void checkWinner(GameController gameController, ClientModel client) {
        if (gameController.ball != null) {
            boolean playerHasWonPhase2 = true;
            if (gameController.omenoctEnemies.size() + gameController.necropickEnemies.size()
                    + gameController.archmireEnemies.size() + gameController.wyrmEnemies.size()
                    + gameController.blackOrbEnemies.size() + gameController.barricadosEnemies1.size()
                    + gameController.barricadosEnemies2.size() < 20){
                playerHasWonPhase2 = false;
            }
            if (gameController.omenoctEnemies.size() + gameController.necropickEnemies.size()
                    + gameController.archmireEnemies.size() + gameController.wyrmEnemies.size()
                    + gameController.blackOrbEnemies.size() + gameController.barricadosEnemies1.size()
                    + gameController.barricadosEnemies2.size() == 10 && gameController.wave == 4){
                gameController.wave++;
            }
            if (gameController.omenoctEnemies.size() + gameController.necropickEnemies.size()
                    + gameController.archmireEnemies.size() + gameController.wyrmEnemies.size()
                    + gameController.blackOrbEnemies.size() + gameController.barricadosEnemies1.size()
                    + gameController.barricadosEnemies2.size() == 20) {
                for (int i = 0; i < gameController.omenoctEnemies.size(); i++) {
                    if (gameController.omenoctEnemies.get(i).enemyHealth > 0) {
                        playerHasWonPhase2 = false;
                        break;
                    }
                }
                for (int j = 0; j < gameController.necropickEnemies.size(); j++) {
                    if (gameController.necropickEnemies.get(j).enemyHealth > 0) {
                        playerHasWonPhase2 = false;
                        break;
                    }
                }
                for (int i = 0; i < gameController.archmireEnemies.size(); i++) {
                    if (gameController.archmireEnemies.get(i).enemyHealth > 0) {
                        playerHasWonPhase2 = false;
                        break;
                    }
                }
                for (int j = 0; j < gameController.wyrmEnemies.size(); j++) {
                    if (gameController.wyrmEnemies.get(j).enemyHealth > 0) {
                        playerHasWonPhase2 = false;
                        break;
                    }
                }
                for (int i = 0; i < gameController.blackOrbEnemies.size(); i++) {
                    if (gameController.blackOrbEnemies.get(i).enemyHealth > 0) {
                        playerHasWonPhase2 = false;
                        break;
                    }
                }
                for (int j = 0; j < gameController.barricadosEnemies1.size(); j++) {
                    if (gameController.barricadosEnemies1.get(j).enemyTimer > 0) {
                        playerHasWonPhase2 = false;
                        break;
                    }
                }
                for (int j = 0; j < gameController.barricadosEnemies2.size(); j++) {
                    if (gameController.barricadosEnemies2.get(j).enemyTimer > 0) {
                        playerHasWonPhase2 = false;
                        break;
                    }
                }
            }
            if (playerHasWonPhase2 && gameController.wave == 5) {
                gameController.wave++;
                displayEndOfPhase2(client);
                client.finalBossFrame = new FinalBossFrame(client);

            }

        }
    }


    public void displayEndOfPhase2(ClientModel client) {
        if (client.gameController.ball != null) {
            int hp = client.gameController.ball.HP;
            GameRestart.restartGame(client);
            client.gameController.ball.HP = hp;
            client.gameController.phase2Over = true;
            client.gameController.pause = false;
        }
    }

}

