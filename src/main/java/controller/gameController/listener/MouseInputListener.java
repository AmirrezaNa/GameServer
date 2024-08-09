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
        System.out.println(inputs.mousePoint.x + " " + inputs.mousePoint.y);
        if ((inputs.mousePoint != null) && (x != 0) && (y != 0)) {
            if (gameController.empowerBullet) {
                Timer timer = new Timer();
                final int[] counter = {0};
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        Point point  = new Point((int) x, (int) y);
                        gameController.newBullet(point);
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
            x = 0;
            y = 0;
        }


    }

}

