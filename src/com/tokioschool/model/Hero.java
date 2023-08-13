package com.tokioschool.model;

import java.util.Random;

public abstract class Hero extends GameUnit
{
    protected Hero(String name, CharacterType type, int healthPoints, int armor)
    {
        super(name, type, healthPoints, armor);
    }

    @Override
    protected int rollAttackPower() {
        int damage1;
        int damage2;
        Random random;

        random = new Random();
        damage1 = random.nextInt(101);
        damage2 = random.nextInt(101);

        return Math.max(damage1, damage2);
    }

    public int calculateEnemyArmor(GameUnit enemy) {
        return enemy.getArmor();
    }


    protected int getMaxAttackPower(GameUnit unit) {
        return 100;
    }
}
