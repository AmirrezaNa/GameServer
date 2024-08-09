package model.entity.enemy.boss;

import model.entity.enemy.EnemyModel;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class LeftHandModel extends EnemyModel {

    public double dx;
    public double dy;
    public int enemyHealth;
    public static double enemySpeed = 0.3;
    public static double enemyAcceleration;
    public static int leftHandSize = 150;
    public static String imageIcon = "E:\\java projects\\ApProjectPhase1\\src\\controller\\data\\store\\apIcons\\LeftHand.png";
    public static Image image = new ImageIcon(imageIcon).getImage();
    public boolean leftHandExists;


    public LeftHandModel(double x, double y) {
        super(x, y);
        this.dx = 1;
        this.dy = 1;
        enemyAcceleration = 3;
        this.enemyHealth = 100;
        this.leftHandExists = true;
    }
}

