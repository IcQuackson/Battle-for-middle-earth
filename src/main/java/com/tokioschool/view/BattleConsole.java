package com.tokioschool.view;

import com.tokioschool.model.BoardManager;

import java.util.*;

import static com.tokioschool.model.GameUnit.TEAM_BEASTS;
import static com.tokioschool.model.GameUnit.TEAM_HEROES;

public class BattleConsole
{
    private BoardManager boardManager;
    private Scanner scanner;

    public BattleConsole(BoardManager boardManager)
    {
        this.boardManager = boardManager;
        this.scanner = new Scanner(System.in);
    }

    /**
	 * The "run" function presents a menu to the user and allows them to choose between managing the
	 * hero army, managing the beast army, simulating a battle, or exiting the program.
	 */
	public void run()
    {
        int input;
        String message = """
                Por favor escolha uma das seguintes opções:
                1. Gerir exército dos heróis
                2. Gerir exército das bestas
                3. Lutar!
                4. Voltar
                Prima o número da opção que quer escolher:\s""";
        do
        {
            input = getInt(message, 4);
            switch (input)
            {
                case 1 -> manageHeroArmy();
                case 2 -> manageBeastArmy();
                case 3 -> simulateBattle();
                default -> {}
            }
        }
        while (input != 4);
    }

    /**
	 * The function "manageHeroArmy" allows the user to perform various operations on a hero army, such
	 * as displaying the army, adding a hero, removing a hero, and changing the position of a hero.
	 */
	private void manageHeroArmy()
    {
        int input;
        String message = """
                Por favor escolha uma das seguintes opções:
                1. Mostrar exército
                2. Adicionar herói
                3. Remover herói
                4. Subir posição do herói
                5. Descer posição do herói
                6. Voltar
                Prima o número da opção que quer escolher:\s""";
        do
        {
            input = getInt(message, 6);
            switch (input)
            {
                case 1 -> printHeroArmy();
                case 2 -> addHero();
                case 3 -> deleteHero();
                case 4 -> ascendHeroIndex();
                case 5 -> descendHeroIndex();
                default -> {}
            }
        }
        while (input != 6);
    }

    /**
	* The `manageBeastArmy()` method allows the user to perform various operations on a beast army. It
	* presents a menu to the user with the following options:
	*/
	private void manageBeastArmy()
    {
        int input;
        String message = """
                Por favor escolha uma das seguintes opções:
                1. Mostrar exército
                2. Adicionar besta
                3. Remover besta
                4. Subir posição da besta
                5. Descer posição da besta
                6. Voltar
                Prima o número da opção que quer escolher:\s""";
        do
        {
            input = getInt(message, 6);
            switch (input)
            {
                case 1 -> printBeastArmy();
                case 2 -> addBeast();
                case 3 -> deleteBeast();
                case 4 -> ascendBeastIndex();
                case 5 -> descendBeastIndex();
                default -> {}
            }
        } while (input != 6);
    }

    /*
	* The `printHeroArmy()` method is responsible for printing the hero army.
	* It first prints the label "Exército - HEROES". Then, it calls the
	* `printArmy()` method, passing the hero army obtained from the `boardManager.showHeroArmy()`
	* method. Finally, it prints a new line.
	*/
	public void printHeroArmy()
    {
        System.out.printf("Exército - %s:\n", TEAM_HEROES);
        printArmy(boardManager.showHeroArmy());
        System.out.println();
    }

    /*
	* The `printBeastArmy()` method is responsible for printing the beast army. It first prints the
	* label "Exército - BEASTS". Then, it calls the `printArmy()` method, passing the beast army
	* obtained from the `boardManager.showBeastArmy()` method. Finally, it prints a new line.
	*/
	public void printBeastArmy()
    {
        System.out.printf("Exército - %s:\n", TEAM_BEASTS);
        printArmy(boardManager.showBeastArmy());
        System.out.println();
    }

	/**
	 * The printArmy function prints the army passed as a parameter.
	 * @param army The army to be printed.
	 */
	private void printArmy(ArrayList<String> army)
    {
        int i = 1;
        for (String c : army)
        {
            System.out.printf("-> %d. " + c + "\n", i);
            i++;
        }
    }

    private void addHero()
    {
        addCharacter(TEAM_HEROES);
    }

    private void addBeast()
    {
        addCharacter(TEAM_BEASTS);
    }

    public void deleteHero()
    {
        int i;
        String heroDescription;

        i = getHeroIndex();
        if (i == -1)
            return;
        heroDescription = boardManager.getHeroDescription(i);
        if (!boardManager.deleteHero(i))
            System.out.println("Ocorreu um erro!\n");
        System.out.println("Herói apagado: \n" + heroDescription);

    }

    public void deleteBeast()
    {
        int i;
        String beastDescription;

        i = getBeastIndex();
        if (i == -1)
            return;
        beastDescription = boardManager.getBeastDescription(i);
        if (!boardManager.deleteBeast(i))
            System.out.println("Ocorreu um erro!\n");
        System.out.println("Besta apagada: \n" + beastDescription);
    }

    private int getHeroIndex()
    {
        return getCharacterIndex(boardManager.showHeroArmy());
    }

    private int getBeastIndex()
    {
        return getCharacterIndex(boardManager.showBeastArmy());
    }

    public void ascendHeroIndex()
    {
        int i;

        i = getCharacterIndex(boardManager.showHeroArmy());
        boardManager.ascendHeroIndex(i);
    }

    public void descendHeroIndex()
    {
        int i;

        i = getCharacterIndex(boardManager.showHeroArmy());
        boardManager.descendHeroIndex(i);
    }

    public void ascendBeastIndex()
    {
        int i;

        i = getCharacterIndex(boardManager.showBeastArmy());
        boardManager.ascendBeastIndex(i);
    }

    public void descendBeastIndex()
    {
        int i;

        i = getCharacterIndex(boardManager.showBeastArmy());
        boardManager.descendBeastIndex(i);
    }


    private int getCharacterIndex(ArrayList<String> army)
    {
        if (army.isEmpty())
            return -1;
        String message = "Selecione o index da personagem:\n";
        int i = 1;
        for (String str : army)
        {
            message += String.format("-> %d. " + str + "\n", i);
            i++;
        }
        message += "index: ";
        return getInt(message, army.size()) - 1;
    }

    private boolean addCharacter(String team)
    {
        String name;
        String message;
        String type;
        String[] types;
        int healthPoints;
        int armor;
        int optionNum;
        boolean result;

        // Prints all team character types
        if (team.equals(TEAM_HEROES))
            types = boardManager.getHeroTypes();
        else if (team.equals(TEAM_BEASTS))
            types = boardManager.getBeastTypes();
        else
            return false;
        message = "Tipos:\n";
        int i = 1;
        for (String t : types)
            message += (i++ + ". " + t + "\n");
        message += "Escolha o tipo premindo o número da opção: ";
        optionNum = getInt(message, types.length);
        type = types[optionNum - 1];

        // Gets Name
        System.out.print("Nome: ");
        name = scanner.nextLine();
        System.out.println();

        // Gets Health Points
        healthPoints = getInt("Pontos de vida: ", 0);

        // Gets Armor
        armor = getInt("Armadura: ", 0);

        // Create character
        if (team.equals(TEAM_HEROES))
            result = boardManager.addHero(name, type, healthPoints, armor);
        else
            result = boardManager.addBeast(name, type, healthPoints, armor);
        if (!result)
        {
            System.out.println("Dados inválidos!");
            return false;
        }
        System.out.print("Adicionado: ");
        printCharacter(name, type, healthPoints, armor);
        System.out.println("\n");
        return true;
    }

    private void printCharacter(String name, String type, int healthPoints, int armor)
    {
        System.out.printf("nome:%s tipo:%s vida:%d armadura:%d",
                name,
                type,
                healthPoints,
                armor);
    }

    public int getInt(String message, int numberOfOptions)
    {
        int input;

        do
        {
            System.out.print(message);
            try
            {
                input = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e)
            {
                System.out.println("Valor inválido!\n");
                continue;
            }
            if (numberOfOptions == 0 || input >= 1 && input <= numberOfOptions)
                break;
            System.out.println("Valor inválido!\n");
        } while (true);
        System.out.println();
        return input;
    }

    public void simulateBattle()
    {
        System.out.println(boardManager.simulateBattle());
    }
}
