package model;

import java.util.Random;

public abstract class Beast extends Character
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
}
