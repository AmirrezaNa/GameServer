package controller.gameController;

import java.util.Timer;
import java.util.TimerTask;

public class ShopController {

    public static void TimerForDismay(GameController gameController) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                gameController.ball.ballDismay = false;
            }
        };
        timer.schedule(task, 10000);
    }

    public static void TimerForSlumber(GameController gameController) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                gameController.ball.ballSlumber = false;
            }
        };
        timer.schedule(task, 10000);
    }
    public static void TimerForSlaughter(GameController gameController) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                gameController.ball.ballSlaughter = false;
            }
        };
        timer.schedule(task, 120000);
    }


}
