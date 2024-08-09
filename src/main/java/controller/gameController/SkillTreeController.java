package controller.gameController;

import model.ClientModel;

import java.util.Timer;
import java.util.TimerTask;

public class SkillTreeController {

    public static void turnOnWritOfProteus(GameController gameController, ClientModel client) {
        if (client.player.isWritOfProteus()) {
            if (!gameController.pause) {
                gameController.ballAngle.angleExists = true;
                Timer timer = new Timer();
                int[] countDownEmpower = {300};
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        if (countDownEmpower[0] > 0) {
                            countDownEmpower[0]--;
                            client.player.setWritOfProteus(false);
                        } else {
                            gameController.ballAngle.angleExists = false;
                            client.player.setWritOfProteus(true);
                            timer.cancel();
                        }
                    }
                };
                timer.scheduleAtFixedRate(task, 0, 1000);
            }
        }
    }


    public static void turnOnWritOfAceso(GameController gameController, ClientModel client) {
        if (client.player.isWritOfAceso()) {
            if (!gameController.pause) {
                Timer timer = new Timer();
                int[] countDownEmpower = {300};
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        if (countDownEmpower[0] > 0) {
                            countDownEmpower[0]--;
                            gameController.ball.HP++;
                            client.player.setWritOfProteus(false);
                        } else {
                            client.player.setWritOfProteus(true);
                            timer.cancel();
                        }
                    }
                };
                timer.scheduleAtFixedRate(task, 0, 1000);
            }
        }
    }

    public static void turnOnWritOfAres(GameController gameController, ClientModel client) {
        if (client.player.isWritOfAres()) {
            if (!gameController.pause) {
                gameController.bulletAres = true;
                Timer timer = new Timer();
                int[] countDownEmpower = {300};
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        if (countDownEmpower[0] > 0) {
                            countDownEmpower[0]--;
                            client.player.setWritOfAres(false);
                        } else {
                            gameController.bulletAres = false;
                            client.player.setWritOfAres(true);
                            timer.cancel();
                        }
                    }
                };
                timer.scheduleAtFixedRate(task, 0, 1000);
            }
        }
    }


    public static void turnOnWritOfCerberus(GameController gameController, ClientModel client) {

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (client.player.isWritOfCerberus()) {
                    if (!gameController.pause) {
                        gameController.ball.ballCerberus = true;
                    }
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 15000);

    }


}