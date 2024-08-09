package model.entity.enemy.normalAndMiniBoss;

import model.entity.enemy.EnemyModel;

public class ArchmirePoints extends EnemyModel {
    public int archmirePointTimer;
    public static double radius = 20;

    public ArchmirePoints(double x, double y) {
        super(x, y);
        this.archmirePointTimer = 5;
    }
}
