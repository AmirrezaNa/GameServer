package controller.gameController.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Timer;
import java.util.TimerTask;

public class MouseInputListener implements MouseListener, MouseMotionListener {

    public static double x = 0;
    public static double y = 0;

    @Override
    public void mouseClicked(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mousePressed(MouseEvent e) {
//        x = e.getX();
//        y = e.getY();
//        if (client.gameController.empowerBullet) {
//            Timer timer = new Timer();
//            final int[] counter = {0};
//            TimerTask task = new TimerTask() {
//                @Override
//                public void run() {
//                    client.gameController.newBullet(e.getPoint());
//                    counter[0]++;
//                    if (counter[0] == 3) {
//                        timer.cancel();
//                    }
//                }
//            };
//            timer.scheduleAtFixedRate(task, 0, 50);
//
//        }
//        else {
//            client.gameController.newBullet(e.getPoint());
//        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }
}

