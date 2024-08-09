package model.entity.enemy.normalAndMiniBoss;

import model.entity.enemy.EnemyModel;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class ArchmireModel extends EnemyModel {

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
