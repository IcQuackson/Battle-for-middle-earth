package com.tokioschool.model;

import java.util.*;

public class BoardManager
{
    private final Board board;

    public BoardManager(Board board)
    {
        this.board = board;
    }

    private ArrayList<GameUnit> getHeroArmy()
    {
        return board.getHeroes();
    }

    private ArrayList<GameUnit> getBeastArmy()
    {
        return board.getBeasts();
    }

    public boolean addHero(String name, String type, int healthPoints, int armor)
    {
        GameUnit hero = createCharacter(name, type, healthPoints, armor);
        if (hero == null)
                return false;
        board.addHero((Hero) hero);
        return true;
    }

    public boolean addBeast(String name, String type, int healthPoints, int armor)
    {
        GameUnit beast = createCharacter(name, type, healthPoints, armor);
        if (beast == null)
            return false;
        board.addBeast((Beast) beast);
        return true;
    }

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

    private GameUnit getHero(int i)
    {
        return board.getHero(i);
    }

    private GameUnit getBeast(int i)
    {
        return board.getBeast(i);
    }

    public String getHeroDescription(int i)
    {
        GameUnit hero;

        hero = board.getHero(i);
        if (hero == null)
            return null;
        return hero.toString();
    }

    public String getBeastDescription(int i)
    {
        GameUnit beast;

        beast = board.getBeast(i);
        if (beast == null)
            return null;
        return beast.toString();
    }

    public String[] getHeroTypes()
    {
        return getTypesFromTeam(GameUnit.TEAM_HEROES);
    }

    public String[] getBeastTypes()
    {
        return getTypesFromTeam(GameUnit.TEAM_BEASTS);
    }

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

    public boolean deleteHero(int index)
    {
        if (board.getHero(index) == null)
            return false;
        board.deleteHero(index);
        return true;
    }

    public boolean deleteBeast(int index)
    {
        if (board.getBeast(index) == null)
            return false;
        board.deleteBeast(index);
        return true;
    }

    public void ascendHeroIndex(int index)
    {
        board.ascendHeroIndex(index);
    }

    public void descendHeroIndex(int index)
    {
        board.descendHeroIndex(index);
    }

    public void ascendBeastIndex(int index)
    {
        board.ascendBeastIndex(index);
    }

    public void descendBeastIndex(int index)
    {
        board.descendBeastIndex(index);
    }

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

    public ArrayList<String> showHeroArmy()
    {
        return getArmy(getHeroArmy());
    }

    public ArrayList<String> showBeastArmy()
    {
        return getArmy(getBeastArmy());
    }

    private ArrayList<String> getArmy(ArrayList<GameUnit> army)
    {
        ArrayList<String> armyStrings = new ArrayList<>();
        for (GameUnit c : army)
            armyStrings.add(c.toString());
        return armyStrings;
    }

    private ArrayList<GameUnit> copyArmy(ArrayList<GameUnit> army)
    {
        ArrayList<GameUnit> newArmy = new ArrayList<>();
        for (GameUnit c : army)
            newArmy.add(c.clone());
        return newArmy;
    }

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
            battleLogs.append("Turno " + turno);
            hasChanged = simulateTurn(battleLogs);
            turno++;
            battleLogs.append("\n");
        }
        // Resets to state before battle
        board.setHeroes(backUpHeroArmy);
        board.setBeasts(backUpBeastArmy);
        return battleLogs;
    }

    public ArrayList<String> getHeroArmyGUI() {
        ArrayList<String> armyStrings = new ArrayList<>();
        for (GameUnit unit : this.board.getHeroes())
            armyStrings.add(String.format("%s - %s (%d, %d)", unit.getName(), unit.getType().toString(), unit.getHealthPoints(), unit.getArmor()));
        return armyStrings;
    }

    public ArrayList<String> getBeastArmyGUI() {
        ArrayList<String> armyStrings = new ArrayList<>();
        for (GameUnit unit : this.board.getBeasts())
            armyStrings.add(String.format("%s - %s (%d, %d)", unit.getName(), unit.getType().toString(), unit.getHealthPoints(), unit.getArmor()));
        return armyStrings;
    }



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

    private boolean isDraw(GameUnit unit1, GameUnit unit2) {
        return maxDamageVs(unit1, unit2) <= 0 &&  maxDamageVs(unit2, unit1) <= 0;
    }

    private int maxDamageVs(GameUnit attacker, GameUnit defender) {
        int attackerPower = attacker.getMaxAttackPower(defender);
        int defenderArmor = attacker.calculateEnemyArmor(defender);

        // Check if the attacker's power is greater than the defender's armor
        return attackerPower - defenderArmor;
    }
}
