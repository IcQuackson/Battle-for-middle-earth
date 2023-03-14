package model;

import java.util.Random;

public abstract class Hero extends Character
{
    protected Hero(String name, CharacterType type, int healthPoints, int armor)
    {
        super(name, type, healthPoints, armor);
    }

    @Override
    protected int rollAttackPower()
    {
        int damage1;
        int damage2;
        Random random;

        random = new Random();
        damage1 = random.nextInt(101);
        damage2 = random.nextInt(101);

        if (damage1 > damage2)
            return damage1;
        else
            return damage2;
    }
}
