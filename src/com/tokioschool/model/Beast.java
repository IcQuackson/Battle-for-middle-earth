package com.tokioschool.model;

import java.util.Random;

public abstract class Beast extends GameUnit
{
    protected Beast(String name, CharacterType type, int healthPoints, int armor)
    {
        super(name, type, healthPoints, armor);
    }

    @Override
    protected int rollAttackPower()
    {
        int damage;
        Random random;

        random = new Random();
        damage = random.nextInt(91);
        return damage;
    }

    public int calculateEnemyArmor(GameUnit enemy) {
        return enemy.getArmor();
    }

    protected int getMaxAttackPower(GameUnit unit) {
        return 90;
    }


}
