package com.tokioschool.model;

import static com.tokioschool.model.CharacterType.ELF;
import static com.tokioschool.model.CharacterType.ORC;

/**
 * The Elf class is a subclass of the Hero class and represents a character of type Elf.
 */
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

    /**
	 * The function calculates the damage inflicted by a game character, with an additional 10 damage
	 * if the character is of type "ORC".
	 * 
	 * @param character The parameter "character" is of type GameUnit, which represents a game
	 * character.
	 * @return The method is returning the calculated damage value.
	 */
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
