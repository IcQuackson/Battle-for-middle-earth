package main_classes;

import java.io.Serializable;

public abstract class GameCharacter implements Comparable<GameCharacter>, Serializable {

    public static final String TEAM_BEASTS = "Bestas";

    public static final String TEAM_HEROES = "Heróis";

    private String name;

    private int healthPoints;

    private int armor;

    private CharacterType type;

    public GameCharacter(CharacterType type){
        this.name = "";
        this.healthPoints = 0;
        this.armor = 0;
        this.type = type;
    }

    public GameCharacter(String name, int healthPoints, int armor, CharacterType type) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.armor = armor;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public CharacterType getType() {
        return type;
    }

    public void setType(CharacterType type) {
        this.type = type;
    }

    public void decreaseHealthPoints(int value) {
        this.healthPoints -= value;
        this.healthPoints = setZeroIfNegative(this.healthPoints);
    }

    /*
    *   
    */
    public abstract void attack(GameCharacter enemyCharacter);

    public boolean isDead() {
        return this.healthPoints == 0;
    }

    public static int setZeroIfNegative(int value) {
        if (value < 0) {
            return 0;
        }
        else {
            return value;
        }
    }

    @Override
    public int compareTo(GameCharacter character) {

        if (this.getHealthPoints() > character.getHealthPoints()) {
            return 1;
        }
        
        if (this.getName().equals(character.getName()) && this.getArmor() == character.getArmor()
            && this.getHealthPoints() == character.getHealthPoints()
            && this.getType().equals(character.getType())) {
                return 0;
        }
        
        else {
            return -1;
        }
    }

    @Override
    public String toString() {

        return String.format("%s  tipo: %s  vida: %d  armadura: %d", this.getName(),
                                                                    this.getType().toString(),
                                                                    this.getHealthPoints(),
                                                                    this.getArmor());
    }
}
