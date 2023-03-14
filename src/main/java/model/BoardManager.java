package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import static model.Character.TEAM_BEASTS;
import static model.Character.TEAM_HEROES;

public class BoardManager
{
    private final Board board;
    private final Scanner scanner;

    public BoardManager(Board board, Scanner scanner)
    {
        this.board = board;
        this.scanner = scanner;
    }

    private ArrayList<Character> getHeroArmy()
    {
        return board.getHeroes();
    }

    private ArrayList<Character> getBeastArmy()
    {
        return board.getBeasts();
    }

    public boolean addHero(String name, String type, int healthPoints, int armor)
    {
        Character hero = createCharacter(name, type, healthPoints, armor);
        if (hero == null)
            return false;
        board.addHero((Hero) hero);
        return true;
    }

    public boolean addBeast(String name, String type, int healthPoints, int armor)
    {
        Character beast = createCharacter(name, type, healthPoints, armor);
        if (beast == null)
            return false;
        board.addBeast((Beast) beast);
        return true;
    }

    private Character createCharacter(String name, String type, int healthPoints, int armor)
    {
        Character character;
        CharacterType characterType;

        // Get CharacterType enum from string
        characterType = CharacterType.get(type);
        if (name.isEmpty() || healthPoints < 0 || armor < 0)
            return null;
        // Get character from factory by providing characterType
        character = CharacterFactory.getCharacter(characterType);
        // Set attributes
        character.setName(name);
        character.setHealthPoints(healthPoints);
        character.setArmor(armor);
        return character;
    }

    private Character getHero(int i)
    {
        return board.getHero(i);
    }

    private Character getBeast(int i)
    {
        return board.getBeast(i);
    }

    public String getHeroDescription(int i)
    {
        Character hero;

        hero = board.getHero(i);
        if (hero == null)
            return null;
        return hero.toString();
    }

    public String getBeastDescription(int i)
    {
        Character beast;

        beast = board.getBeast(i);
        if (beast == null)
            return null;
        return beast.toString();
    }

    public String[] getHeroTypes()
    {
        return getTypesFromTeam(TEAM_HEROES);
    }

    public String[] getBeastTypes()
    {
        return getTypesFromTeam(TEAM_BEASTS);
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
        Iterator<Character> heroesIterator;
        Iterator<Character> beastsIterator;

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

    private ArrayList<String> getArmy(ArrayList<Character> army)
    {
        ArrayList<String> armyStrings = new ArrayList<>();
        for (Character c : army)
            armyStrings.add(c.toString());
        return armyStrings;
    }

    private ArrayList<Character> copyArmy(ArrayList<Character> army)
    {
        ArrayList<Character> newArmy = new ArrayList<>();
        for (Character c : army)
            newArmy.add(c.clone());
        return newArmy;
    }

    public void simulateBattle()
    {
        int turno = 1;
        boolean hasChanged = true;
        ArrayList<Character> backUpHeroArmy = copyArmy(getHeroArmy());
        ArrayList<Character> backUpBeastArmy = copyArmy(getBeastArmy());

        while (hasChanged)
        {
            System.out.println("Turno " + turno);
            hasChanged = simulateTurn();
            turno++;
            System.out.println();
        }
        // Resets to state before battle
        board.setHeroes(backUpHeroArmy);
        board.setBeasts(backUpBeastArmy);
    }

    private boolean simulateTurn()
    {
        int heroArmySize = getHeroArmy().size();
        int beastArmySize = getBeastArmy().size();
        int smallerArmySize;
        int hp;
        int attack;

        // Find smaller army size because only the first nth characters fight (n being the smaller army size)
        if (heroArmySize >= beastArmySize)
            smallerArmySize = beastArmySize;
        else
            smallerArmySize = heroArmySize;

        // For each position the hero strikes the beast first and then the beast strikes back
        for (int i = 0; i < smallerArmySize; i++)
        {
            Character hero = getHero(i);
            Character beast = getBeast(i);

            System.out.printf("   Luta entre %s (Vida=%d Armadura=%d) e %s (Vida=%d Armadura=%d)\n",
                    hero.getName(), hero.getHealthPoints(), hero.getArmor(),
                    beast.getName(), beast.getHealthPoints(), beast.getArmor());

            System.out.print("      ");
            hp = beast.getHealthPoints();
            attack = hero.attack(beast);
            System.out.printf("%s saca %d e tira %d de vida a %s.\n",
                    hero.getName(), attack , hp - beast.getHealthPoints(), beast.getName());

            System.out.print("      ");
            hp = hero.getHealthPoints();
            attack = beast.attack(hero);
            beast.attack(hero);
            System.out.printf("%s saca %d e tira %d de vida a %s.\n",
                    beast.getName(), attack , hp - hero.getHealthPoints(), hero.getName());

            if (hero.getHealthPoints() <= 0)
            {
                System.out.print("   ");
                System.out.println("Morre " + hero.getType() + " " + hero.getName() + "!");
            }
            else if (beast.getHealthPoints() <= 0)
            {
                System.out.print("   ");
                System.out.println("Morre " + beast.getType() + " " + beast.getName() + "!");
            }
        }
        // Clear dead characters from battle
        deleteDeadCharacters();
        // If one of the armies is dead the battle is over
        if (getBeastArmy().isEmpty())
        {
            System.out.println("VITÓRIA DOS HERÓIS!");
            return false;
        }
        else if (getHeroArmy().isEmpty())
        {
            System.out.println("VITÓRIA DAS BESTAS!");
            return false;
        }
        return true;
    }
}
