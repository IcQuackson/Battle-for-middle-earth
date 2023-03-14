package model;

public abstract class Character
{
    public static final String TEAM_HEROES = "Heróis";
    public static final String TEAM_BEASTS = "Bestas";
    private String name;
    private CharacterType type;
    private int healthPoints;
    private int armor;

    protected Character(String name, CharacterType type, int healthPoints, int armor)
    {
        this.name = name;
        this.type = type;
        this.healthPoints = healthPoints;
        this.armor = armor;
    }

    protected String getName()
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

    protected int getHealthPoints()
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

    protected int attack(Character enemy)
    {
        int damage;
        int enemyArmor;

        damage = calculateDamage(enemy);
        enemyArmor = calculateEnemyArmor(enemy);
        if (damage > enemyArmor)
            enemy.setHealthPoints(enemy.getHealthPoints() - (damage - enemyArmor));
        return damage;
    }

    protected int calculateDamage(Character enemy)
    {
        return rollAttackPower();
    }

    protected int calculateEnemyArmor(Character enemy)
    {
        return enemy.getArmor();
    }

    protected abstract int rollAttackPower();

    protected abstract Character clone();

    @Override
    public String toString() {
        return String.format("nome:%s  tipo:%s vida:%d armadura:%d", this.getName(),
                this.getType().toString(),
                this.getHealthPoints(),
                this.getArmor());
    }
}
