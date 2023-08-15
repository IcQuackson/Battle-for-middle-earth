package com.tokioschool.model;

import static com.tokioschool.model.CharacterType.HOBBIT;
import static com.tokioschool.model.CharacterType.TROLL;

/**
 * The Hobbit class is a subclass of the Hero class and overrides methods to calculate damage, clone
 * the object, and get the maximum attack power.
 */
public class Hobbit extends Hero {
    public Hobbit() {
        super("", HOBBIT, 0, 0);
    }

    public Hobbit(String name, int healthPoints, int armor) {
        super(name, HOBBIT, healthPoints, armor);
    }

    /**
	 * The function calculates the damage inflicted by a game unit, taking into account the attack
	 * power and reducing it by 5 if the target is a troll.
	 * 
	 * @param character The `character` parameter is of type `GameUnit`, which represents a game
	 * character.
	 * @return The method is returning the calculated damage value.
	 */
	@Override
	protected int calculateDamage(GameUnit character) {
        int damage;

        damage = super.rollAttackPower();
        if (character.getType().equals(TROLL))
            damage -= 5;
        return damage;
    }

    
    /**
	 * The function returns a new instance of the Hobbit class with the same name, health points, and
	 * armor as the original instance.
	 * 
	 * @return The method is returning a new instance of the Hobbit class, which is a subclass of the
	 * GameUnit class.
	 */
	@Override
	protected GameUnit clone() {
        return new Hobbit(getName(), getHealthPoints(), getArmor());
    }

    
    /**
	 * The function calculates the maximum attack power of a game unit, taking into account a specific
	 * character type.
	 * 
	 * @param character The `character` parameter is of type `GameUnit`, which represents a game
	 * character.
	 * @return The method is returning the maximum attack power of the character, taking into account
	 * any adjustments based on the character's type.
	 */
	@Override
	protected int getMaxAttackPower(GameUnit character) {
        int damage;

        damage = super.getMaxAttackPower(null);
        if (character.getType().equals(TROLL))
            damage -= 5;
        return damage;
    }
}
