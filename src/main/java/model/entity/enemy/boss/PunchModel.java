package model.entity.enemy.boss;

import model.entity.enemy.EnemyModel;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class PunchModel extends EnemyModel {

    public double dx;
    public double dy;
    public static double enemySpeed = 0.3;
    public static double enemyAcceleration;
    public static int punchSize = 100;
    public static String imageIcon = "E:\\java projects\\ApProjectPhase1\\src\\controller\\data\\store\\apIcons\\punch.png";
    public static Image image = new ImageIcon(imageIcon).getImage();


    public PunchModel(double x, double y) {
        super(x, y);
        this.dx = 0;
        this.dy = 1;
        enemyAcceleration = 3;
    }
}

