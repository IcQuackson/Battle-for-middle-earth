package com.tokioschool.model;

import java.util.Random;

public abstract class Beast extends GameUnit
{
    /**
     * The Beast function is a constructor that takes in the name, type, healthPoints and armor of the Beast.
     *
     * @param String name Set the name of the beast
     * @param CharacterType type Determine the type of character
     * @param int healthPoints Set the healthpoints of the beast
     * @param int armor Set the armor value of the beast
     *
     */
    protected Beast(String name, CharacterType type, int healthPoints, int armor)
    {
        super(name, type, healthPoints, armor);
    }

    /**
     * The rollAttackPower function is used to determine the damage that a character will do when they attack.
     * The function uses a random number generator to generate an integer between 0 and 90, which is then added
     * to the base attack power of 10. This value is returned by the function and stored in an int variable in
     * another class, where it can be used for calculations involving damage done or health lost.
     *
     * @return A random number between 0 and 90
     */
    @Override
    protected int rollAttackPower()
    {
        int damage;
        Random random;

        random = new Random();
        damage = random.nextInt(91);
        return damage;
    }

    /**
     * The calculateEnemyArmor function calculates the enemy's armor.
     *
     * @param GameUnit enemy Get the armor of the enemy
     *
     * @return The enemy's armor
     */
    public int calculateEnemyArmor(GameUnit enemy) {
        return enemy.getArmor();
    }

    /**
     * The getMaxAttackPower function returns the maximum attack power of a unit.
     *
     * @param GameUnit unit Determine the attack power of the unit
     *
     * @return The maximum attack power of the unit
     */
    protected int getMaxAttackPower(GameUnit unit) {
        return 90;
    }


}
