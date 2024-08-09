package model.entity.enemy.boss;

import model.entity.enemy.EnemyModel;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class SmileyModel extends EnemyModel {

    public double dx;
    public double dy;
    public int enemyHealth;
    public static double enemySpeed = 0.3;
    public static int smileySize = 400;
    public boolean punchExists;
    public static String imageIcon = "E:\\java projects\\ApProjectPhase1\\src\\controller\\data\\store\\apIcons\\smiley.png";
    public static Image image = new ImageIcon(imageIcon).getImage();
    public boolean squeezeAttack;
    public boolean projectileAttack;
    public boolean vomitAttack;
    public boolean powerPunchAttack;
    public boolean quakeAttack;
    public boolean rapidFireAttack;
    public boolean slapAttack;
    public boolean annihilatorAttack;


    public SmileyModel(double x, double y) {
        super(x, y);
        this.dx = 0;
        this.dy = 1;
        this.enemyHealth = 300;
        this.punchExists = false;
        this.squeezeAttack = false;
        this.projectileAttack = false;
        this.vomitAttack = false;
        this.powerPunchAttack = false;
        this.quakeAttack = false;
        this.rapidFireAttack = false;
        this.slapAttack = false;
        this.annihilatorAttack = false;

    }
}

