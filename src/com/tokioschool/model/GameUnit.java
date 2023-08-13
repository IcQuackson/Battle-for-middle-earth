package com.tokioschool.model;

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

    public String getName()
    {
        return name;
    }

    protected void setName(String name)
    {
        this.name = name;
    }

    public CharacterType getType()
    {
        return this.type;
    }

    public int getHealthPoints()
    {
        return healthPoints;
    }

    protected void setHealthPoints(int healthPoints)
    {
        this.healthPoints = healthPoints;
    }

    public int getArmor()
    {
        return this.armor;
    }

    protected void setArmor(int armor)
    {
        this.armor = armor;
    }

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

    protected int calculateDamage(GameUnit enemy)
    {
        return rollAttackPower();
    }

    abstract protected int getMaxAttackPower(GameUnit unit);

    protected int calculateEnemyArmor(GameUnit enemy)
    {
        return enemy.getArmor();
    }

    protected abstract int rollAttackPower();

    protected abstract GameUnit clone();

    @Override
    public String toString() {
        return String.format("nome:%s  tipo:%s vida:%d armadura:%d", this.getName(),
                this.getType().toString(),
                this.getHealthPoints(),
                this.getArmor());
    }
}
