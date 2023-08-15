package com.tokioschool.model;

import static com.tokioschool.model.CharacterType.ORC;

/**
 * The Orc class is a subclass of the Beast class and overrides methods for calculating enemy armor,
 * maximum attack power, and cloning.
 */
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

    /**
	 * The function calculates the enemy's armor by subtracting 10% of the enemy's armor from the
	 * original armor value.
	 * 
	 * @param enemy The parameter "enemy" is of type GameUnit, which represents an enemy unit in a
	 * game.
	 * @return The method is returning an integer value, which is the enemy's armor after reducing it
	 * by 10%.
	 */
	@Override
	public int calculateEnemyArmor(GameUnit enemy)
    {
        return (int) (enemy.getArmor() - enemy.getArmor() * 0.1);
    }
    
    /**
	 * The getMaxAttackPower function calculates the maximum attack power against an enemy by
	 * subtracting 10% of the enemy's armor from their total armor.
	 * 
	 * @param enemy The "enemy" parameter is of type GameUnit, which represents an enemy game unit.
	 * @return The maximum attack power of the enemy unit.
	 */
	@Override
	protected int getMaxAttackPower(GameUnit enemy) {
        return (int) (enemy.getArmor() - enemy.getArmor() * 0.1);
    }
    
    /**
	 * The function returns a new instance of the Orc class with the same name, health points, and
	 * armor as the original instance.
	 * 
	 * @return The method is returning a new instance of the Orc class, which is a subclass of
	 * GameUnit.
	 */
	@Override
	protected GameUnit clone()
    {
        return new Orc(getName(), getHealthPoints(), getArmor());
    }
}
