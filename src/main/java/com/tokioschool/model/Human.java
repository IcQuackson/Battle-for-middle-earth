package com.tokioschool.model;

import static com.tokioschool.model.CharacterType.HUMAN;

public class Human extends Hero
{
    public Human()
    {
        super("", HUMAN, 0, 0);
    }

    public Human(String name, int healthPoints, int armor)
    {
        super(name, HUMAN, healthPoints, armor);
    }

    @Override
    protected GameUnit clone()
    {
        return new Human(getName(), getHealthPoints(), getArmor());
    }
}
