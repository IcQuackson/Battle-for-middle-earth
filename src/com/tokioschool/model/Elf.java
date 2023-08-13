package com.tokioschool.model;

import static com.tokioschool.model.CharacterType.ELF;
import static com.tokioschool.model.CharacterType.ORC;

public class Elf extends Hero
{
    public Elf()
    {
        super("", ELF, 0, 0);
    }

    public Elf(String name, int healthPoints, int armor)
    {
        super(name, ELF, healthPoints, armor);
    }

    public int calculateDamage(GameUnit character)
    {
        int damage;

        damage = super.rollAttackPower();
        if (character.getType().equals(ORC))
            damage += 10;
        return damage;
    }

    @Override
    protected GameUnit clone()
    {
        return new Elf(getName(), getHealthPoints(), getArmor());
    }
}
