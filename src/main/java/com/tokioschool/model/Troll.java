package com.tokioschool.model;

import static com.tokioschool.model.CharacterType.TROLL;

public class Troll extends Beast
{
    public Troll()
    {
        super("", TROLL, 0, 0);
    }

    public Troll(String name, int healthPoints, int armor)
    {
        super(name, TROLL, healthPoints, armor);
    }

    @Override
    protected GameUnit clone()
    {
        return new Troll(getName(), getHealthPoints(), getArmor());
    }
}
