package controller.gameController.listener;

import controller.gameController.GameController;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Timer;
import java.util.TimerTask;

public class MouseInputListener {

    public static double x = 0;
    public static double y = 0;


    public static void getMousePoint(GameController gameController, Inputs inputs) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (inputs.mousePoint != null) {
                    if (gameController.empowerBullet) {
                        Timer timer = new Timer();
                        final int[] counter = {0};
                        TimerTask task = new TimerTask() {
                            @Override
                            public void run() {
                                gameController.newBullet(inputs.mousePoint);
                                counter[0]++;
                                if (counter[0] == 3) {
                                    timer.cancel();
                                }
                            }
                        };
                        timer.scheduleAtFixedRate(task, 0, 5);

                    } else {
                        gameController.newBullet(inputs.mousePoint);
                    }
                }

            }
        };
        timer.scheduleAtFixedRate(task, 0, 5);


    }

}

