package model.entity.enemy;

import javax.swing.*;
import java.awt.*;

public class AllEnemies {
    public static class WyrmModel extends EnemyModel {

        public double dx;
        public double dy;
        public double ax;
        public double ay;
        public int enemyHealth;
        public static double enemySpeed = 0.3;
        public static double enemyAcceleration;
        public static int wyrmSize = 50;
        public static String imageIcon = "E:\\java projects\\ApProjectPhase1\\src\\controller\\data\\store\\apIcons\\wyrm.png";
        public static Image image = new ImageIcon(imageIcon).getImage();


        public WyrmModel(double x, double y) {
            super(x, y);
            this.dx = 0;
            this.dy = 0;
            this.ax = 0;
            this.ay = 0;
            enemyAcceleration = 3;
            this.enemyHealth = 12;

        }
    }

    public static class OmenoctModel extends EnemyModel {


        public double dx;
        public double dy;
        public double ax;
        public double ay;
        public int enemyHealth;
        public static double enemySpeed = 0.3;
        public static double enemyAcceleration;
        public double[] xAngles;
        public double[] yAngles;
        public static int omenoctSize = 20;
        public static int distanceToCenter = (int) ((omenoctSize / 2) / Math.sin(Math.PI / 8));
        public double angle;
        public double dAngle;
        public static String imageIconAdress = "E:\\java projects\\ApProjectPhase1\\src\\controller\\data\\store\\apIcons\\omenoct.png";
        public static Image image = new ImageIcon(imageIconAdress).getImage();


        // the position of omenoct is based on the top angle

        public OmenoctModel(double x, double y) {
            super(x, y);
            xAngles = new double[]{x, (x + (omenoctSize * Math.cos(Math.PI / 8)))
                    , (x + (distanceToCenter * Math.cos(Math.PI / 8))), (x + (omenoctSize * Math.cos(Math.PI / 8)))
                    , x, (x - (omenoctSize * Math.cos(Math.PI / 8)))
                    , (x - (distanceToCenter * Math.cos(Math.PI / 8))), (x - (omenoctSize * Math.cos(Math.PI / 8)))};
            yAngles = new double[]{y, (y + (omenoctSize * Math.sin(Math.PI / 8)))
                    , (y + distanceToCenter - (distanceToCenter * Math.sin(Math.PI / 8))), (y + distanceToCenter + (omenoctSize * Math.sin(Math.PI / 8)))
                    , y + (2 * distanceToCenter), (y + distanceToCenter + (omenoctSize * Math.sin(Math.PI / 8)))
                    , (y + distanceToCenter - (distanceToCenter * Math.sin(Math.PI / 8))), (y + (omenoctSize * Math.sin(Math.PI / 8)))};

            this.dx = 0;
            this.dy = 0;
            this.ax = 0;
            this.ay = 0;
            enemyAcceleration = 3;
            this.enemyHealth = 20;
            this.angle = 0;
            this.dAngle = 0;
        }
    }

    public static class NecropickModel extends EnemyModel {

        public double dx;
        public double dy;
        public double ax;
        public double ay;
        public int enemyHealth;
        public static double enemySpeed = 0.3;
        public static double enemyAcceleration;
        public double[] xAngles;
        public double[] yAngles;
        public static int necropickSize = 30;
        public static String imageIcon = "E:\\java projects\\ApProjectPhase1\\src\\controller\\data\\store\\apIcons\\necropick.png";
        public static Image image = new ImageIcon(imageIcon).getImage();
        public int hidingTime;
        public boolean hide;
        public boolean allowShot;
        public boolean necropickAlert;

        public NecropickModel(double x, double y) {
            super(x, y);
            xAngles = new double[]{x, (x + necropickSize), (x + necropickSize), x};
            yAngles = new double[]{y, y, (y + necropickSize), (y + necropickSize)};
            this.dx = 0;
            this.dy = 0;
            this.ax = 0;
            this.ay = 0;
            enemyAcceleration = 3;
            this.enemyHealth = 10;
            this.hidingTime = 0;
            this.hide = false;
            this.allowShot = false;
            this.necropickAlert = false;
        }
    }

    public static class EnemyModel2 extends EnemyModel {

        public double dx;
        public double dy;
        public double ax;
        public double ay;
        public int enemyHealth;
        public static double enemySpeed = 0.3;
        public static double enemyAcceleration;
        public double[] xAngles;
        public double[] yAngles;
        public static int enemy2Size = 30;
        public double angle;
        public double dAngle;

        public EnemyModel2(double x, double y) {
            super(x, y);
            xAngles = new double[]{x, (x + enemy2Size), (x + ((double) enemy2Size / 2))};
            yAngles = new double[]{y, y, (y + (enemy2Size))};
            this.dx = 0;
            this.dy = 0;
            this.ax = 0;
            this.ay = 0;
            enemyAcceleration = 3;
            this.enemyHealth = 15;
            this.angle = 0;
            this.dAngle = 0;
        }
    }

    public static class EnemyModel1 extends EnemyModel {

        public double dx;
        public double dy;
        public double ax;
        public double ay;
        public int enemyHealth;
        public static double enemySpeed = 0.3;
        public static double enemyAcceleration;
        public double[] xAngles;
        public double[] yAngles;
        public static int enemy1Size = 30;
        public double angle;
        public double dAngle;
        public boolean dash;

        public EnemyModel1(double x, double y) {
            super(x, y);
            xAngles = new double[]{x, (x + enemy1Size), (x + enemy1Size), x};
            yAngles = new double[]{y, y, (y + enemy1Size), (y + enemy1Size)};
            this.dx = 0;
            this.dy = 0;
            this.ax = 0;
            this.ay = 0;
            this.dash = false;
            enemyAcceleration = 3;
            this.enemyHealth = 10;
            this.angle = 0;
            this.dAngle = 0;
        }

    }

    public static class BlackOrbModel extends EnemyModel {

        public double dx;
        public double dy;
        public double ax;
        public double ay;
        public int enemyHealth;
        public static double enemySpeed = 0.3;
        public static double enemyAcceleration;
        public static int blackOrbSize = 30;

        public boolean draw1;
        public boolean draw2;
        public boolean draw3;
        public boolean draw4;
        public boolean draw5;
        public int blackOrbTimer;


        public BlackOrbModel(double x, double y) {
            super(x, y);
            this.dx = 0;
            this.dy = 0;
            this.ax = 0;
            this.ay = 0;
            enemyAcceleration = 3;
            this.enemyHealth = 150;
            this.draw1 = false;
            this.draw2 = false;
            this.draw3 = false;
            this.draw4 = false;
            this.draw5 = false;
            blackOrbTimer = 25;

        }
    }

    public static class BarricadosModel2 extends EnemyModel {

        public double dx;
        public double dy;
        public double ax;
        public double ay;
        public int enemyTimer;
        public static double enemySpeed = 0.3;
        public static double enemyAcceleration;
        public static int barricadosSize = 60;
        public static String imageIcon = "E:\\java projects\\ApProjectPhase1\\src\\controller\\data\\store\\apIcons\\barricados.png";
        public static Image image = new ImageIcon(imageIcon).getImage();

        public BarricadosModel2(double x, double y) {
            super(x, y);
            this.dx = 0;
            this.dy = 0;
            this.ax = 0;
            this.ay = 0;
            enemyAcceleration = 3;
            this.enemyTimer = 120;
        }
    }

    public static class BarricadosModel1 extends EnemyModel {

        public double dx;
        public double dy;
        public double ax;
        public double ay;
        public int enemyTimer;
        public static double enemySpeed = 0.3;
        public static double enemyAcceleration;
        public static int barricadosSize = 60;
        public static String imageIcon = "E:\\java projects\\ApProjectPhase1\\src\\controller\\data\\store\\apIcons\\barricados.png";
        public static Image image = new ImageIcon(imageIcon).getImage();

        public BarricadosModel1(double x, double y) {
            super(x, y);
            this.dx = 0;
            this.dy = 0;
            this.ax = 0;
            this.ay = 0;
            enemyAcceleration = 3;
            this.enemyTimer = 120;
        }
    }

    public static class ArchmirePoints extends EnemyModel {
        public int archmirePointTimer;
        public static double radius = 20;

        public ArchmirePoints(double x, double y) {
            super(x, y);
            this.archmirePointTimer = 5;
        }
    }

    public static class ArchmireModel extends EnemyModel {

        public double dx;
        public double dy;
        public double ax;
        public double ay;
        public int enemyHealth;
        public static double enemySpeed = 0.3;
        public static double enemyAcceleration;
        public static int archmireSize = 40;
        public static String imageIcon = "E:\\java projects\\ApProjectPhase1\\src\\controller\\data\\store\\apIcons\\archmire.png";
        public static Image image = new ImageIcon(imageIcon).getImage();

        public ArchmireModel(double x, double y) {
            super(x, y);
            this.dx = 0;
            this.dy = 0;
            this.ax = 0;
            this.ay = 0;
            enemyAcceleration = 3;
            this.enemyHealth = 30;
        }
    }
}
