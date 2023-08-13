package com.tokioschool.model;

import static com.tokioschool.model.CharacterType.ORC;

public class Orc extends Beast
{
    public Orc()
    {
        super("", ORC, 0, 0);
    }

    public Orc(String name, int healthPoints, int armor)
    {
        super(name, ORC, healthPoints, armor);
    }

    @Override
    public int calculateEnemyArmor(GameUnit enemy)
    {
        return (int) (enemy.getArmor() - enemy.getArmor() * 0.1);
    }

    @Override
    protected int getMaxAttackPower(GameUnit enemy) {
        return (int) (enemy.getArmor() - enemy.getArmor() * 0.1);
    }

    @Override
    protected GameUnit clone()
    {
        return new Orc(getName(), getHealthPoints(), getArmor());
    }
}
