package controller.gameController.objectController;

import controller.gameController.GameController;
import model.entity.BallModel;

import java.util.Timer;
import java.util.TimerTask;

public class BallController {

    public static void updateTheBall(GameController gameController) {
        gameController.ball.x += gameController.ball.ax;
        gameController.ball.y += gameController.ball.ay;
        if (gameController.smiley != null && gameController.smiley.quakeAttack) {
            if (gameController.ball.ax != 0) {
                if (gameController.ball.ax > 0) {
                    gameController.ball.ax -= 0.0005;
                } else {
                    gameController.ball.ax += 0.0005;
                }
            }
            if (gameController.ball.ay != 0) {
                if (gameController.ball.ay > 0) {
                    gameController.ball.ay -= 0.0005;
                } else {
                    gameController.ball.ay += 0.0005;
                }
            }
        }

        else {
            if (gameController.ball.ax != 0) {
                if (gameController.ball.ax > 0) {
                    gameController.ball.ax -= 0.05;
                } else {
                    gameController.ball.ax += 0.05;
                }
            }
            if (gameController.ball.ay != 0) {
                if (gameController.ball.ay > 0) {
                    gameController.ball.ay -= 0.05;
                } else {
                    gameController.ball.ay += 0.05;
                }
            }
        }
    }



    public static void getBallIntoFrame2(GameController gameController) {
        gameController.ball.x = gameController.createdFrames[0].x + ((double) gameController.createdFrames[0].width /2);
        gameController.ball.y = gameController.createdFrames[0].y + ((double) gameController.createdFrames[0].height /2);
    }

    public static void getBallIntoFinalFrame(GameController gameController) {
        gameController.ball.x = gameController.epsilonFrame.epsilonFrame.x + ((double) gameController.epsilonFrame.epsilonFrame.width /2);
        gameController.ball.y = gameController.epsilonFrame.epsilonFrame.y + ((double) gameController.epsilonFrame.epsilonFrame.height /2);
    }


    public static boolean checkIfBallIsInFrame(GameController gameController) {
        for (int i = 0; i < gameController.createdFrames.length; i++) {
            int x = gameController.createdFrames[i].x;
            int y = gameController.createdFrames[i].y;
            int width = gameController.createdFrames[i].width;
            int height = gameController.createdFrames[i].height;
            if ((gameController.ball.x >= (x + BallModel.ballRadius)) && (gameController.ball.x <= (x + width - BallModel.ballRadius))
                    && (gameController.ball.y > (y + BallModel.ballRadius)) && (gameController.ball.y < (y + height - BallModel.ballRadius))) {
                return true;
            }
        }
        return false;
    }

    public static void checkIfBallInArchmire(GameController gameController) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (gameController.ball.ballInArchmire && !gameController.pause) {
                    gameController.ball.HP -= 10;
                }
//                if (stopWave) {
//                    timer.cancel();
//                }
            }

        };
        timer.scheduleAtFixedRate(task, 500, 1000);
    }

    public static void checkIfBallInArchmireTrace(GameController gameController) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (gameController.ball.ballInArchmireTrace && !gameController.pause) {
                    gameController.ball.HP -= 2;
                }
//                if (stopWave) {
//                    timer.cancel();
//                }
            }

        };
        timer.scheduleAtFixedRate(task, 500, 1000);
    }

    public static void checkIfBallInBlackOrb(GameController gameController) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (gameController.ball.ballInBlackOrb && !gameController.pause) {
                    gameController.ball.HP -= 12;
                }
//                if (stopWave) {
//                    timer.cancel();
//                }
            }

        };
        timer.scheduleAtFixedRate(task, 500, 1000);
    }



}