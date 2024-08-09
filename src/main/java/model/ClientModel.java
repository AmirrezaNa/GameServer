package model;

import controller.gameController.GameController;
import controller.gameController.listener.Inputs;
import controller.gameController.listener.KeyInputListener;
import controller.gameController.listener.MouseInputListener;
import controller.gameLoop.phase1.GameFrame;
import controller.gameLoop.phase2.BossFight.FinalBossFrame;
import controller.gameLoop.phase2.BossFight.FinalBossPanel;
import controller.gameLoop.phase2.normalAndMiniBossEnemies.GameFrame2;

public class ClientModel {
    public GameController gameController;
    public Inputs inputs;
    public GameFrame gameFrame;
    public GameFrame2 gameFrame2;
    public FinalBossFrame finalBossFrame;
    public Player player;

    public ClientModel() {
        this.player = new Player();
        this.gameController = new GameController();
        this.inputs = new Inputs();
        this.gameFrame = null;
        this.gameFrame2 = null;
        this.finalBossFrame = null;
//        MouseInputListener.getMousePoint(this.gameController, this.inputs);
//        KeyInputListener.getKeyPoint(this);
    }

}
