package controller.gameController.objectController;

import model.entity.Collectible;

import java.util.Timer;
import java.util.TimerTask;

public class CollectibleController {


    public static void countDownCollectible(int seconds, Collectible collectible1) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int remainingSeconds = seconds;

            @Override
            public void run() {
                if (remainingSeconds > 0) {
                    remainingSeconds--;
                } else {
                    collectible1.collectibleHealth = 0;
                    timer.cancel();
                }
            }
        }, 0, 1000);
    }
}

