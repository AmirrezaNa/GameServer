package controller.gameController;

import controller.data.SoundEffects;
import controller.gameController.objectController.CollectibleController;
import controller.gameController.objectController.enemies.*;
import controller.gameLoop.phase2.BossFight.EpsilonFrame;
import controller.gameLoop.phase2.normalAndMiniBossEnemies.CreateFrames;
import model.ClientModel;
import model.entity.*;
import model.entity.enemy.boss.LeftHandModel;
import model.entity.enemy.boss.PunchModel;
import model.entity.enemy.boss.RightHandModel;
import model.entity.enemy.boss.SmileyModel;
import model.entity.enemy.normalAndMiniBoss.*;
import view.SettingsPanel;

import java.awt.*;
import java.io.Serializable;
import java.util.*;

public class GameController implements Serializable {

    private static final long serialVersionUID = 1L;
    public SmileyModel smiley;
    public RightHandModel rightHand;
    public LeftHandModel leftHand;
    public PunchModel punch;

    public BallModel ball;
    public BallDirection ballDirection;
    public BallAngle ballAngle;

    BulletModel bullet;
    EnemyModel1 enemy1;
    EnemyModel2 enemy2;
    ArchmireModel archmire;
    BarricadosModel1 barricados1;
    BarricadosModel2 barricados2;
    BlackOrbModel blackOrb;
    OmenoctModel omenoct;
    WyrmModel wyrm;
    NecropickModel necropick;
    Collectible collectible;
    public ArrayList<BulletModel> bullets;
    public ArrayList<BulletModel> enemyBullets;
    public ArrayList<EnemyModel1> enemies1;
    public ArrayList<EnemyModel2> enemies2;
    public ArrayList<ArchmireModel> archmireEnemies;
    public ArrayList<ArchmirePoints> archmirePoints;
    public ArrayList<BarricadosModel1> barricadosEnemies1;
    public ArrayList<BlackOrbModel> blackOrbEnemies;
    public ArrayList<BarricadosModel2> barricadosEnemies2;
    public ArrayList<OmenoctModel> omenoctEnemies;
    public ArrayList<WyrmModel> wyrmEnemies;
    public ArrayList<NecropickModel> necropickEnemies;
    public ArrayList<Collectible> collectibles;
    public int wave;
    public int Banish;
    public int Empower;
    public boolean bulletAres;
    public boolean gameOver;
    public boolean pause;

    public int elapsedTime;
    public int numberOfBullets;
    public boolean phase1over;
    public boolean stopWave;
    public boolean phase2Over;
    public boolean finalBossOver;
    public boolean empowerBullet;
    public EpsilonFrame epsilonFrame;
    public SettingsPanel settings;
    public CreateFrames[] createdFrames;
    public boolean framesCreated;
    public Set<Integer> collidedFrames;
    public boolean ballBetweenFrames;
    public GameController() {
        gameTime(this);
        this.settings = new SettingsPanel();

        this.smiley = null;
        this.rightHand = null;
        this.leftHand = null;
        this.punch = null;
        this.ball = null;
        this.ballDirection = null;
        this.ballAngle = null;
        this.bullet = null;
        this.enemy1 = null;
        this.enemy2 = null;
        this.archmire = null;
        this.barricados1 = null;
        this.barricados2 = null;
        this.blackOrb = null;
        this.omenoct = null;
        this.wyrm = null;
        this.necropick = null;
        this.collectible = null;


        this.epsilonFrame = null;
        this.createdFrames = null;
        this.framesCreated = false;
        this.collidedFrames = new HashSet<>();
        this.ballBetweenFrames = false;

        this.bullets = new ArrayList<>();
        this.enemyBullets = new ArrayList<>();
        this.enemies1 = new ArrayList<>();
        this.enemies2 = new ArrayList<>();
        this.archmireEnemies = new ArrayList<>();
        this.archmirePoints = new ArrayList<>();
        this.barricadosEnemies1 = new ArrayList<>();
        this.blackOrbEnemies = new ArrayList<>();
        this.barricadosEnemies2 = new ArrayList<>();
        this.omenoctEnemies = new ArrayList<>();
        this.wyrmEnemies = new ArrayList<>();
        this.necropickEnemies = new ArrayList<>();
        this.collectibles = new ArrayList<>();

        this.wave = 1;
        this.Banish = 0;
        this.Empower = 0;
        this.bulletAres = false;
        this.empowerBullet = false;
        this.elapsedTime = 0;
        this.numberOfBullets = 0;
        this.phase1over = false;
        this.phase2Over = false;
        this.finalBossOver = false;
        this.gameOver = false;
        this.pause = false;
        this.stopWave = false;
    }





    // ===============================================================================


    // creating and updating the ball ================================================

    public synchronized void newBall(ClientModel client) {
        this.ball = BallModel.getInstance((double) (client.gameFrame.width / 2) - 20, (double) (client.gameFrame.height / 2) - 20);
        this.ball.ballChiron = client.player.isWritOfChiron();
    }


    public void createBallDirection() {
        this.ballDirection = new BallDirection(ball.x, ball.y);
    }

    public void createBallAngle() {
        this.ballAngle = new BallAngle(ball.x, ball.y);
    }



    // creating the bullets ================================================




    public BulletModel newBullet(Point point) {
        if ((!this.gameOver || !this.finalBossOver) && !this.pause) {
            this.bullet = new BulletModel(this.ball.x, this.ball.y);
            this.bullet.bulletFrame = FrameOfObject.getFrameOfBall(this);
            this.bullet.dx = ((point.x - (this.ball.x)) / Math.sqrt(Math.pow((point.x - (this.ball.x)), 2) + Math.pow((point.y - (this.ball.y)), 2))) * BulletModel.bulletSpeed;
            if (this.bullet.y < point.y) {
                this.bullet.dy = Math.sqrt(Math.pow(BulletModel.bulletSpeed, 2) - Math.pow(this.bullet.dx, 2));
            } else {
                this.bullet.dy = -(Math.sqrt(Math.pow(BulletModel.bulletSpeed, 2) - Math.pow(this.bullet.dx, 2)));
            }
            this.bullets.add(0, this.bullet);
            return this.bullet;
        }
        return null;
    }

    public BulletModel newOmenoctBullet(Point point) {
        if ((!this.gameOver || !this.finalBossOver)  && !this.pause && !this.ball.ballSlumber) {
            BulletModel.bulletSpeed = 3;
            this.bullet = new BulletModel(point.x, point.y);
            this.bullet.bulletFrame = FrameOfObject.getFrameOfBall(this);
            this.bullet.dx = -((point.x - (this.ball.x + 20)) / Math.sqrt(Math.pow((point.x - (this.ball.x + 20)), 2) + Math.pow((point.y - (this.ball.y + 20)), 2))) * BulletModel.bulletSpeed;
            if (this.ball.y > point.y) {
                this.bullet.dy = Math.sqrt(Math.pow(BulletModel.bulletSpeed, 2) - Math.pow(this.bullet.dx, 2));
            } else {
                this.bullet.dy = -(Math.sqrt(Math.pow(BulletModel.bulletSpeed, 2) - Math.pow(this.bullet.dx, 2)));
            }
            this.enemyBullets.add(0, this.bullet);
            BulletModel.bulletSpeed = 15;
            return this.bullet;
        }
        return null;
    }

    public BulletModel newNecropickBullet(Point point, Point goal) {
        if ((!this.gameOver || !this.finalBossOver)  && !this.pause && !this.ball.ballSlumber) {
            BulletModel.bulletSpeed = 3;
            this.bullet = new BulletModel(point.x, point.y);
            this.bullet.bulletFrame = FrameOfObject.getFrameOfBall(this);
            this.bullet.dx = -((point.x - (goal.x)) / Math.sqrt(Math.pow((point.x - (goal.x)), 2) + Math.pow((point.y - (goal.y)), 2))) * BulletModel.bulletSpeed;
            if (goal.y > point.y) {
                this.bullet.dy = Math.sqrt(Math.pow(BulletModel.bulletSpeed, 2) - Math.pow(this.bullet.dx, 2));
            } else {
                this.bullet.dy = -(Math.sqrt(Math.pow(BulletModel.bulletSpeed, 2) - Math.pow(this.bullet.dx, 2)));
            }
            this.enemyBullets.add(0, this.bullet);
            BulletModel.bulletSpeed = 15;
            return this.bullet;
        }
        return null;
    }



    // creating the enemies ================================================

    public void newEnemy1(ClientModel client) {
        if (!this.phase1over && !this.pause && !this.ball.ballSlumber) {
            SoundEffects.playSound(Constants.ENEMY_ENTER_SOUND_PATH);
            if (this.enemies1.size() % 2 == 0) {
                this.enemy1 = new EnemyModel1(50, (double) client.gameFrame.height / 2);
                this.enemies1.add(0, this.enemy1);
            } else {
                this.enemy1 = new EnemyModel1((double) client.gameFrame.width / 2, 50);
                this.enemy1.dash = true;
                this.enemies1.add(0, this.enemy1);
            }
        }
    }

    public void newEnemy2(ClientModel client) {
        if (!this.phase1over && !this.pause && !this.ball.ballSlumber) {
            SoundEffects.playSound(Constants.ENEMY_ENTER_SOUND_PATH);
            if (this.enemies2.size() % 2 == 0) {
                this.enemy2 = new EnemyModel2((double) client.gameFrame.width - 60, (double) client.gameFrame.height / 2);
                this.enemies2.add(0, this.enemy2);
            } else {
                this.enemy2 = new EnemyModel2(((double) client.gameFrame.width / 2), client.gameFrame.height - 60);
                this.enemies2.add(0, this.enemy2);
            }

        }
    }


    public void newArchmire() {
        if (!this.pause && !this.ball.ballSlumber)
            for (int i = 1; i < 3; i++) {
                this.archmire = new ArchmireModel(createdFrames[i].x + (double) (createdFrames[i].width / 2),
                        createdFrames[i].y + (double) (createdFrames[i].height / 2));
                ArchmireController.setTrace(this.archmire, this);
                this.archmireEnemies.add(this.archmire);
            }
        SoundEffects.playSound(Constants.ENEMY_ENTER_SOUND_PATH);

    }

    public void newBarricados1() {
        if (!this.pause && !this.ball.ballSlumber) {
            SoundEffects.playSound(Constants.ENEMY_ENTER_SOUND_PATH);
            int x = createdFrames[2].x + (createdFrames[2].width/2);
            int y = createdFrames[2].y + createdFrames[2].height - (BarricadosModel1.barricadosSize/2);
            this.barricados1 = new BarricadosModel1(x, y);
            this.barricadosEnemies1.add(this.barricados1);
            BarricadosController1.setTimerForBarricados1(this.barricados1, this);
        }
    }

    public void newBarricados2() {
        if (!this.pause && !this.ball.ballSlumber) {
            SoundEffects.playSound(Constants.ENEMY_ENTER_SOUND_PATH);
            int x = createdFrames[1].x + (createdFrames[1].width/2);
            int y = createdFrames[1].y + createdFrames[1].height - (BarricadosModel2.barricadosSize/2);
            this.barricados2 = new BarricadosModel2(x, y);
            this.barricadosEnemies2.add(this.barricados2);
            BarricadosController2.setTimerForBarricados2(this.barricados2, this);
        }
    }


    public void newBlackOrb() {
        if (!this.pause && !this.ball.ballSlumber) {
            SoundEffects.playSound(Constants.ENEMY_ENTER_SOUND_PATH);
            int x = createdFrames[3].x + (createdFrames[3].width/2);
            int y = createdFrames[3].y + (createdFrames[3].height/2);
            this.blackOrb = new BlackOrbModel(x, y);
            this.blackOrbEnemies.add(this.blackOrb);
            BlackOrbController.setTimerForCreatingBlackOrb(this.blackOrb, this);
        }
    }


    public void newOmenoct() {
        if (!this.pause && !this.ball.ballSlumber) {
            SoundEffects.playSound(Constants.ENEMY_ENTER_SOUND_PATH);
            int x = createdFrames[FrameOfObject.getFrameOfBall(this)].x + createdFrames[FrameOfObject.getFrameOfBall(this)].width - (ArchmireModel.archmireSize / 2);
            int y = createdFrames[FrameOfObject.getFrameOfBall(this)].y + createdFrames[FrameOfObject.getFrameOfBall(this)].height/2;
            this.omenoct = new OmenoctModel(x, y);
            this.omenoctEnemies.add(this.omenoct);
            if (this.omenoctEnemies.size() == 1) {
                OmenoctController.shotBullet(this);
            }
        }
    }


    public void newWyrm() {
        if (!this.pause && !this.ball.ballSlumber) {
            SoundEffects.playSound(Constants.ENEMY_ENTER_SOUND_PATH);
            int x = createdFrames[3].x + (createdFrames[3].width/2);
            int y = createdFrames[3].y + createdFrames[3].height - (WyrmModel.wyrmSize/2);
            this.wyrm = new WyrmModel(x, y);
            this.wyrmEnemies.add(this.wyrm);
            if (wyrmEnemies.size() == 1) {
                WyrmController.shotBullet(this);
            }
        }
    }

    public void newNecropick() {
        if (!this.pause && !this.ball.ballSlumber) {
            SoundEffects.playSound(Constants.ENEMY_ENTER_SOUND_PATH);
            this.necropick = new NecropickModel((int) (ball.x - 200), (int) ball.y);
            this.necropickEnemies.add(this.necropick);
        }
    }


    // this part is for collectibles ====================================================

    public void newCollectible(double x, double y, int xp) {
        this.collectible = new Collectible(x, y, xp);
        this.collectibles.add(this.collectible);
        CollectibleController.countDownCollectible(10, this.collectible);//collectible will disappear in 10 seconds
    }



    // this part is for boss-fight ======================================================

    public void newSmiley(double x, double y) {
        smiley = new SmileyModel(x, y);
    }

    public void newRightHand(double x, double y) {
        rightHand = new RightHandModel(x, y);
    }

    public void newLeftHand(double x, double y) {
        this.leftHand = new LeftHandModel(x, y);
    }

    public void newPunch(double x, double y) {
        this.punch = new PunchModel(x, y);
    }


    public void gameTime(GameController gameController) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (!gameController.pause) {
                    gameController.elapsedTime++;
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }


}
