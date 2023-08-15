package com.tokioschool.model;

/**
 * The GameUnit class is an abstract class that represents a unit in the game, with properties such as
 * name, type, health points, and armor.
 */
public abstract class GameUnit
{
    public static final String TEAM_HEROES = "Heróis";
    public static final String TEAM_BEASTS = "Bestas";
    private String name;
    private CharacterType type;
    private int healthPoints;
    private int armor;

    protected GameUnit(String name, CharacterType type, int healthPoints, int armor)
    {
        this.name = name;
        this.type = type;
        this.healthPoints = healthPoints;
        this.armor = armor;
    }

    /**
	 * The getName() function returns the name of an object as a string.
	 * 
	 * @return The method is returning the value of the variable "name".
	 */
	public String getName()
    {
        return name;
    }

    /**
	 * The function sets the name of an object.
	 * 
	 * @param name The name parameter is a String that represents the name to be set.
	 */
	protected void setName(String name)
    {
        this.name = name;
    }

    /**
	 * The function returns the type of the character.
	 * 
	 * @return The method is returning the value of the variable "type" of type CharacterType.
	 */
	public CharacterType getType()
    {
        return this.type;
    }

    /**
	 * The function returns the value of the healthPoints variable.
	 * 
	 * @return The method is returning the value of the variable "healthPoints".
	 */
	public int getHealthPoints()
    {
        return healthPoints;
    }

    /**
	 * The function sets the value of the healthPoints variable.
	 * 
	 * @param healthPoints The healthPoints parameter is an integer value that represents the health
	 * points of an object.
	 */
	protected void setHealthPoints(int healthPoints)
    {
        this.healthPoints = healthPoints;
    }

    /**
	 * The function returns the value of the "armor" variable.
	 * 
	 * @return The method is returning the value of the "armor" variable.
	 */
	public int getArmor()
    {
        return this.armor;
    }

    /**
	 * The function sets the value of the "armor" variable.
	 * 
	 * @param armor The "armor" parameter is an integer that represents the amount of armor to be set.
	 */
	protected void setArmor(int armor)
    {
        this.armor = armor;
    }

    /**
	 * The function calculates the damage inflicted on an enemy game unit and subtracts the enemy's
	 * armor value from it before reducing the enemy's health points.
	 * 
	 * @param enemy The "enemy" parameter is a GameUnit object representing the enemy that is being
	 * attacked.
	 * @return The method is returning the damage inflicted on the enemy unit.
	 */
	protected int attack(GameUnit enemy)
    {
        int damage;
        int enemyArmor;

        damage = calculateDamage(enemy);
        enemyArmor = calculateEnemyArmor(enemy);
        if (damage > enemyArmor)
            enemy.setHealthPoints(enemy.getHealthPoints() - (damage - enemyArmor));
        return damage;
    }

    /**
	 * The function calculates the damage inflicted on an enemy game unit by rolling the attack power.
	 * 
	 * @param enemy The parameter "enemy" is of type GameUnit, which represents an enemy game unit.
	 * @return The method is returning the result of the rollAttackPower() method.
	 */
	protected int calculateDamage(GameUnit enemy)
    {
        return rollAttackPower();
    }

    /**
	 * The function getMaxAttackPower returns the maximum attack power of a game unit.
	 * 
	 * @param unit The "unit" parameter represents a game unit, which is an object or entity in a game.
	 * @return An integer value representing the maximum attack power of the game unit.
	 */
	abstract protected int getMaxAttackPower(GameUnit unit);

    /**
	 * The function calculates and returns the armor value of an enemy game unit.
	 * 
	 * @param enemy The parameter "enemy" is of type GameUnit, which represents an enemy unit in the
	 * game.
	 * @return The armor value of the enemy GameUnit.
	 */
	protected int calculateEnemyArmor(GameUnit enemy)
    {
        return enemy.getArmor();
    }

    /**
	 * The function "rollAttackPower" is an abstract method that returns an integer representing the
	 * attack power.
	 * 
	 * @return An integer value representing the attack power.
	 */
	protected abstract int rollAttackPower();

    /**
	 * The above function is an abstract method that returns a clone of a GameUnit object.
	 * 
	 * @return The method is returning a clone of the GameUnit object.
	 */
	protected abstract GameUnit clone();

    @Override
    public String toString() {
        return String.format("nome:%s  tipo:%s vida:%d armadura:%d", this.getName(),
                this.getType().toString(),
                this.getHealthPoints(),
                this.getArmor());
    }
}
