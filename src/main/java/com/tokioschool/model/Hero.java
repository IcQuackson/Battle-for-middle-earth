package com.tokioschool.model;

import java.util.Random;

/**
 * The Hero class is an abstract class that extends the GameUnit class and provides methods for rolling
 * attack power, calculating enemy armor, and getting the maximum attack power.
 */
public abstract class Hero extends GameUnit
{
    protected Hero(String name, CharacterType type, int healthPoints, int armor)
    {
        super(name, type, healthPoints, armor);
    }

    
    /**
	 * The function "rollAttackPower" returns the maximum value between two randomly generated
	 * integers.
	 * 
	 * @return The method is returning the maximum value between damage1 and damage2.
	 */
	@Override
	protected int rollAttackPower() {
        int damage1;
        int damage2;
        Random random;

        random = new Random();
        damage1 = random.nextInt(101);
        damage2 = random.nextInt(101);

        return Math.max(damage1, damage2);
    }

    /**
	 * The function calculates and returns the armor value of a given enemy game unit.
	 * 
	 * @param enemy The parameter "enemy" is of type GameUnit, which represents an enemy unit in the
	 * game.
	 * @return The armor value of the enemy GameUnit.
	 */
	public int calculateEnemyArmor(GameUnit enemy) {
        return enemy.getArmor();
    }


    /**
	 * The getMaxAttackPower function returns the maximum attack power for a given GameUnit.
	 * 
	 * @param unit The parameter "unit" is of type GameUnit, which is likely a class representing a
	 * game character or unit.
	 * @return The method is returning an integer value of 100.
	 */
	protected int getMaxAttackPower(GameUnit unit) {
        return 100;
    }
}
