package com.tokioschool.model;

import java.util.*;

/**
 * The BoardManager class manages the game board, including adding and deleting heroes and beasts,
 * simulating battles, and providing information about the armies.
 */
public class BoardManager
{
    private final Board board;

    public BoardManager(Board board)
    {
        this.board = board;
    }

    /**
	 * The function "getHeroArmy" returns an ArrayList of GameUnit objects representing the heroes on
	 * the game board.
	 * 
	 * @return The method is returning an ArrayList of GameUnit objects, specifically the heroes on the
	 * board.
	 */
	private ArrayList<GameUnit> getHeroArmy()
    {
        return board.getHeroes();
    }

    /**
	 * The function returns an ArrayList of GameUnit objects representing the beast army on the board.
	 * 
	 * @return The method is returning an ArrayList of GameUnit objects, specifically the beasts on the
	 * board.
	 */
	private ArrayList<GameUnit> getBeastArmy()
    {
        return board.getBeasts();
    }

    /**
	 * The function adds a hero to the game board if it is successfully created.
	 * 
	 * @param name The name of the hero. It is a String value.
	 * @param type The "type" parameter represents the type of hero being added.
	 * @param healthPoints The healthPoints parameter represents the initial health points of the hero.
	 * It is an integer value that determines how much damage the hero can withstand before being
	 * defeated.
	 * @param armor The "armor" parameter represents the amount of armor the hero has. Armor is a type
	 * of protective gear that reduces the amount of damage taken by the hero.
	 * @return The method is returning a boolean value.
	 */
	public boolean addHero(String name, String type, int healthPoints, int armor)
    {
        GameUnit hero = createCharacter(name, type, healthPoints, armor);
        if (hero == null)
                return false;
        board.addHero((Hero) hero);
        return true;
    }

    /**
	 * The function adds a new beast to the game board with the given name, type, health points, and
	 * armor.
	 * 
	 * @param name The name of the beast being added.
	 * @param type The "type" parameter represents the type of the beast.
	 * @param healthPoints The healthPoints parameter represents the initial health points of the
	 * beast. It determines how much damage the beast can take before it is defeated.
	 * @param armor The "armor" parameter represents the armor value of the beast. It is an integer
	 * value that determines the amount of damage the beast can resist or reduce.
	 * @return The method is returning a boolean value.
	 */
	public boolean addBeast(String name, String type, int healthPoints, int armor)
    {
        GameUnit beast = createCharacter(name, type, healthPoints, armor);
        if (beast == null)
            return false;
        board.addBeast((Beast) beast);
        return true;
    }

    /**
	 * The function creates a game character with the given name, type, health points, and armor, and
	 * returns it.
	 * 
	 * @param name The name of the character being created. It is a String value.
	 * @param type The "type" parameter is a String that represents the type of character to create. It
	 * is used to determine the CharacterType enum value for the character.
	 * @param healthPoints The healthPoints parameter represents the initial health points of the
	 * character being created.
	 * @param armor The "armor" parameter represents the amount of armor points that the character has.
	 * Armor points are a form of defense that reduce the amount of damage taken from attacks.
	 * @return The method is returning a GameUnit object.
	 */
	private GameUnit createCharacter(String name, String type, int healthPoints, int armor)
    {
        GameUnit gameUnit;
        CharacterType characterType;

        // Get CharacterType enum from string
        characterType = CharacterType.get(type);
        if (name.isEmpty() || healthPoints < 0 || armor < 0)
            return null;
        // Get character from factory by providing characterType
        gameUnit = CharacterFactory.getCharacter(characterType);
        // Set attributes
        gameUnit.setName(name);
        gameUnit.setHealthPoints(healthPoints);
        gameUnit.setArmor(armor);
        return gameUnit;
    }

    /**
	 * The function returns the hero at a specific index on the game board.
	 * 
	 * @param i The parameter "i" is an integer that represents the index of the hero in the board.
	 * @return The method is returning a GameUnit object, specifically the hero at index i on the
	 * board.
	 */
	private GameUnit getHero(int i)
    {
        return board.getHero(i);
    }

    /**
	 * The function returns a GameUnit object representing a beast at a specified index on the board.
	 * 
	 * @param i The parameter "i" is an integer that represents the index of the beast in the board.
	 * @return The method is returning a GameUnit object, specifically the GameUnit object at index i
	 * in the board's list of beasts.
	 */
	private GameUnit getBeast(int i)
    {
        return board.getBeast(i);
    }

    /**
	 * The function "getHeroDescription" returns a string representation of a hero's description based
	 * on its index in the game board.
	 * 
	 * @param i The parameter "i" is an integer that represents the index of the hero in the board.
	 * @return The method is returning a String representation of the hero object.
	 */
	public String getHeroDescription(int i)
    {
        GameUnit hero;

        hero = board.getHero(i);
        if (hero == null)
            return null;
        return hero.toString();
    }

    /**
	 * The function "getBeastDescription" returns a string representation of a specific beast on the
	 * game board.
	 * 
	 * @param i The parameter "i" is an integer that represents the index of the beast in the board.
	 * @return The method is returning a String representation of the GameUnit object at the specified
	 * index in the board's list of beasts. If the beast at the specified index is null, the method
	 * returns null.
	 */
	public String getBeastDescription(int i)
    {
        GameUnit beast;

        beast = board.getBeast(i);
        if (beast == null)
            return null;
        return beast.toString();
    }

    /**
	 * The function returns an array of hero types from a game unit team.
	 * 
	 * @return The method is returning an array of strings.
	 */
	public String[] getHeroTypes()
    {
        return getTypesFromTeam(GameUnit.TEAM_HEROES);
    }

    /**
	 * The function returns an array of strings representing the types of beasts in a game.
	 * 
	 * @return The method is returning an array of strings.
	 */
	public String[] getBeastTypes()
    {
        return getTypesFromTeam(GameUnit.TEAM_BEASTS);
    }

    /**
	 * The function takes a team name as input and returns an array of strings representing the
	 * character types in that team.
	 * 
	 * @param team A string representing the team name.
	 * @return The method is returning an array of strings.
	 */
	private String[] getTypesFromTeam(String team)
    {
        ArrayList<CharacterType> types = CharacterType.getTypesFromTeam(team);
        if (types == null)
            return null;
        String[] typesArray = new String[types.size()];
        for (int i = 0; i < types.size(); i++)
            typesArray[i] = types.get(i).toString();
        return typesArray;
    }

    /**
	 * The function deletes a hero from the board at a specified index and returns true if successful,
	 * false otherwise.
	 * 
	 * @param index The index parameter represents the position of the hero in the board that you want
	 * to delete.
	 * @return The method is returning a boolean value. If the hero at the specified index is
	 * successfully deleted, the method will return true. If the hero at the specified index does not
	 * exist, the method will return false.
	 */
	public boolean deleteHero(int index)
    {
        if (board.getHero(index) == null)
            return false;
        board.deleteHero(index);
        return true;
    }

    /**
	 * The function deletes a beast from the board at the specified index and returns true if
	 * successful, false otherwise.
	 * 
	 * @param index The index parameter represents the position of the beast in the board that needs to
	 * be deleted.
	 * @return The method is returning a boolean value. If the beast at the specified index is
	 * successfully deleted from the board, the method will return true. Otherwise, it will return
	 * false.
	 */
	public boolean deleteBeast(int index)
    {
        if (board.getBeast(index) == null)
            return false;
        board.deleteBeast(index);
        return true;
    }

    /**
	 * The function ascendHeroIndex takes an index as a parameter and calls the ascendHeroIndex method
	 * of the board object with that index.
	 * 
	 * @param index The index parameter is an integer that represents the position of the hero in the
	 * board.
	 */
	public void ascendHeroIndex(int index)
    {
        board.ascendHeroIndex(index);
    }

    /**
	 * The function descends the hero index on the board.
	 * 
	 * @param index The index parameter is an integer that represents the position of the hero on the
	 * board.
	 */
	public void descendHeroIndex(int index)
    {
        board.descendHeroIndex(index);
    }

    /**
	 * The function "ascendBeastIndex" is used to ascend the index of a beast on the board.
	 * 
	 * @param index The index parameter is an integer that represents the position of the beast in the
	 * board.
	 */
	public void ascendBeastIndex(int index)
    {
        board.ascendBeastIndex(index);
    }

    /**
	 * The function "descendBeastIndex" is used to descend the beast index on the board.
	 * 
	 * @param index The index parameter is an integer that represents the position of the beast in the
	 * board.
	 */
	public void descendBeastIndex(int index)
    {
        board.descendBeastIndex(index);
    }

    /**
	 * The function "deleteDeadCharacters" removes any game units with health points less than or equal
	 * to zero from the hero and beast armies.
	 */
	private void deleteDeadCharacters()
    {
        Iterator<GameUnit> heroesIterator;
        Iterator<GameUnit> beastsIterator;

        heroesIterator = getHeroArmy().iterator();
        beastsIterator = getBeastArmy().iterator();

        while (heroesIterator.hasNext())
        {
            if (heroesIterator.next().getHealthPoints() <= 0)
                heroesIterator.remove();
        }
        while (beastsIterator.hasNext())
        {
            if (beastsIterator.next().getHealthPoints() <= 0)
                beastsIterator.remove();
        }
    }

    /**
	 * The function returns an ArrayList of Strings representing the hero army.
	 * 
	 * @return An ArrayList of Strings is being returned.
	 */
	public ArrayList<String> showHeroArmy()
    {
        return getArmy(getHeroArmy());
    }

    /**
	 * The function "showBeastArmy" returns an ArrayList of Strings representing the beast army.
	 * 
	 * @return An ArrayList of Strings is being returned.
	 */
	public ArrayList<String> showBeastArmy()
    {
        return getArmy(getBeastArmy());
    }

    /**
	 * The function takes an ArrayList of GameUnit objects and returns an ArrayList of their string
	 * representations.
	 * 
	 * @param army An ArrayList of GameUnit objects, representing an army of game units.
	 * @return The method is returning an ArrayList of Strings.
	 */
	private ArrayList<String> getArmy(ArrayList<GameUnit> army)
    {
        ArrayList<String> armyStrings = new ArrayList<>();
        for (GameUnit c : army)
            armyStrings.add(c.toString());
        return armyStrings;
    }

    /**
	 * The function "copyArmy" creates a new ArrayList of GameUnit objects by cloning each object in
	 * the input ArrayList.
	 * 
	 * @param army An ArrayList of GameUnit objects, representing an army of game units.
	 * @return The method is returning a new ArrayList of GameUnit objects.
	 */
	private ArrayList<GameUnit> copyArmy(ArrayList<GameUnit> army)
    {
        ArrayList<GameUnit> newArmy = new ArrayList<>();
        for (GameUnit c : army)
            newArmy.add(c.clone());
        return newArmy;
    }

    /**
	 * The function simulates a battle between two armies and returns a StringBuilder object containing
	 * the battle logs.
	 * 
	 * @return The method is returning a StringBuilder object, which contains the battle logs of the
	 * simulated battle.
	 */
	public StringBuilder simulateBattle()
    {
        int turno = 1;
        boolean hasChanged = true;
        if (getHeroArmy().size() == 0 && getBeastArmy().size() == 0) {
            return null;
        }
        StringBuilder battleLogs = new StringBuilder();
        ArrayList<GameUnit> backUpHeroArmy = copyArmy(getHeroArmy());
        ArrayList<GameUnit> backUpBeastArmy = copyArmy(getBeastArmy());

        while (hasChanged)
        {
            battleLogs.append("Turno " + turno + "\n");
            hasChanged = simulateTurn(battleLogs);
            turno++;
            battleLogs.append("\n");
        }
        // Resets to state before battle
        board.setHeroes(backUpHeroArmy);
        board.setBeasts(backUpBeastArmy);
        return battleLogs;
    }

    /**
	 * The function returns a list of strings representing the hero army in a GUI format.
	 * 
	 * @return The method is returning an ArrayList of strings. Each string in the ArrayList represents
	 * a hero in the game army and contains information about the hero's name, type, health points, and
	 * armor.
	 */
	public ArrayList<String> getHeroArmyGUI() {
        ArrayList<String> armyStrings = new ArrayList<>();
        for (GameUnit unit : this.board.getHeroes())
            armyStrings.add(String.format("%s - %s (%d, %d)", unit.getName(), unit.getType().toString(), unit.getHealthPoints(), unit.getArmor()));
        return armyStrings;
    }

    /**
	 * The function returns an ArrayList of strings representing the beast army in a game, including
	 * the unit's name, type, health points, and armor.
	 * 
	 * @return The method is returning an ArrayList of strings. Each string in the ArrayList represents
	 * a game unit in the beast army. The string format includes the unit's name, type, health points,
	 * and armor.
	 */
	public ArrayList<String> getBeastArmyGUI() {
        ArrayList<String> armyStrings = new ArrayList<>();
        for (GameUnit unit : this.board.getBeasts())
            armyStrings.add(String.format("%s - %s (%d, %d)", unit.getName(), unit.getType().toString(), unit.getHealthPoints(), unit.getArmor()));
        return armyStrings;
    }



    /**
	 * The function `simulateTurn` simulates a turn in a battle between a hero army and a beast army,
	 * updating the battle logs and checking for victory conditions.
	 * 
	 * @param battleLogs A StringBuilder object that stores the battle logs, which are the messages and
	 * information about the battle that occur during each turn.
	 * @return The method returns a boolean value.
	 */
	private boolean simulateTurn(StringBuilder battleLogs)
    {
        int heroArmySize = getHeroArmy().size();
        int beastArmySize = getBeastArmy().size();
        int smallerArmySize;
        int hp;
        int attack;

        // Find smaller army size because only the first nth characters fight (n being the smaller army size)
        smallerArmySize = Math.min(heroArmySize, beastArmySize);

        // For each position the hero strikes the beast first and then the beast strikes back
        for (int i = 0; i < smallerArmySize; i++)
        {
            if (getHeroArmy().size() == 1 && getBeastArmyGUI().size() == 1
                    && isDraw(getHeroArmy().get(0), getBeastArmy().get(0))) {
                battleLogs.append("DRAW\n");
                return false;
            }
            GameUnit hero = getHero(i);
            GameUnit beast = getBeast(i);

            battleLogs.append(String.format("   Luta entre %s (Vida=%d Armadura=%d) e %s (Vida=%d Armadura=%d)\n",
                    hero.getName(), hero.getHealthPoints(), hero.getArmor(),
                    beast.getName(), beast.getHealthPoints(), beast.getArmor()));

            battleLogs.append("      ");
            hp = beast.getHealthPoints();
            attack = hero.attack(beast);
            battleLogs.append(String.format("%s saca %d e tira %d de vida a %s.\n",
                    hero.getName(), attack , hp - beast.getHealthPoints(), beast.getName()));

            battleLogs.append("      ");
            hp = hero.getHealthPoints();
            attack = beast.attack(hero);
            battleLogs.append(String.format("%s saca %d e tira %d de vida a %s.\n",
                    beast.getName(), attack , hp - hero.getHealthPoints(), hero.getName()));

            if (hero.getHealthPoints() <= 0)
            {
                battleLogs.append("   ");
                battleLogs.append("Morre " + hero.getType() + " " + hero.getName() + "!\n");
            }
            else if (beast.getHealthPoints() <= 0)
            {
                battleLogs.append("   ");
                battleLogs.append("Morre " + beast.getType() + " " + beast.getName() + "!\n");
            }
        }
        // Clear dead characters from battle
        deleteDeadCharacters();
        // If one of the armies is dead the battle is over
        if (getBeastArmy().isEmpty())
        {
            battleLogs.append("VITÓRIA DOS HERÓIS!\n");
            return false;
        }
        else if (getHeroArmy().isEmpty())
        {
            battleLogs.append("VITÓRIA DAS BESTAS!\n");
            return false;
        }
        return true;
    }

    /**
	 * The function "isDraw" checks if two game units have equal or lower damage against each other.
	 * 
	 * @param unit1 The first game unit being compared.
	 * @param unit2 The `unit2` parameter is a `GameUnit` object, representing the second game unit in
	 * the comparison.
	 * @return The method is returning a boolean value.
	 */
	private boolean isDraw(GameUnit unit1, GameUnit unit2) {
        return maxDamageVs(unit1, unit2) <= 0 &&  maxDamageVs(unit2, unit1) <= 0;
    }

    /**
	 * The function calculates the maximum damage that an attacker can inflict on a defender based on
	 * their attack power and the defender's armor.
	 * 
	 * @param attacker The attacker parameter represents the game unit that is attacking. It could be a
	 * character, a monster, or any other entity that can perform attacks in the game.
	 * @param defender The "defender" parameter represents the GameUnit that is being attacked. It
	 * could be any object of the GameUnit class that has properties and methods related to defense and
	 * armor.
	 * @return The difference between the attacker's power and the defender's armor.
	 */
	private int maxDamageVs(GameUnit attacker, GameUnit defender) {
        int attackerPower = attacker.getMaxAttackPower(defender);
        int defenderArmor = attacker.calculateEnemyArmor(defender);

        // Check if the attacker's power is greater than the defender's armor
        return attackerPower - defenderArmor;
    }
}
