package com.tokioschool.view;

import com.tokioschool.model.BoardManager;

import java.util.*;

import static com.tokioschool.model.GameUnit.TEAM_BEASTS;
import static com.tokioschool.model.GameUnit.TEAM_HEROES;

/**
 * The BattleConsole class allows the user to manage hero and beast armies, simulate battles, and
 * navigate through a menu.
 */
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

    /**
     * The printHeroArmy function prints the hero army.
     *
     * @return The hero army
     */
	public void printHeroArmy()
    {
        System.out.printf("Exército - %s:\n", TEAM_HEROES);
        printArmy(boardManager.showHeroArmy());
        System.out.println();
    }

    /**
     * The printHeroArmy function prints the hero army.
     *
     * @return The hero army
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

    /**
     * The addHero function adds a hero to the game.
     *
     * @return The hero that was added
     */
    private void addHero()
    {
        addCharacter(TEAM_HEROES);
    }

    /**
     * The addBeast function adds a beast to the game.
     *
     * @return The beast object that was added
     */
    private void addBeast() {
        addCharacter(TEAM_BEASTS);
    }

    /**
     * The deleteHero function deletes a hero from the board.
     *
     * @return A boolean, true if the hero was deleted and false otherwise
     */
	public void deleteHero() {
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

    /**
     * The deleteBeast function deletes a beast from the board.
     *
     * @return Void
     */
	public void deleteBeast() {
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

    /**
     * The getHeroIndex function returns the index of the hero in the army.
     *
     * @return The index of the hero in the army
     */
	private int getHeroIndex() {
        return getCharacterIndex(boardManager.showHeroArmy());
    }

    /**
     * The getBeastIndex function returns the index of the beast in the army.
     *
     * @return The index of the beast in the arraylist
     */
	private int getBeastIndex() {
        return getCharacterIndex(boardManager.showBeastArmy());
    }

    /**
     * The ascendHeroIndex function is used to increment the index of the hero army.
     *
     * @return The index of the hero in the army
     *
     */
	public void ascendHeroIndex()
    {
        int i;

        i = getCharacterIndex(boardManager.showHeroArmy());
        boardManager.ascendHeroIndex(i);
    }

    /**
     * The descendHeroIndex function is used to decrement the index of the hero in the army.
     *
     * @return The index of the hero in the army
     */
	public void descendHeroIndex()
    {
         int i;

        i = getCharacterIndex(boardManager.showHeroArmy());
        boardManager.descendHeroIndex(i);
    }

    /**
    * The above code is a method in Java that is called "ascendBeastIndex". However, the code provided
	* is incomplete and does not contain any logic or functionality.
	*/
	public void ascendBeastIndex()
    {
        int i;

        i = getCharacterIndex(boardManager.showBeastArmy());
        boardManager.ascendBeastIndex(i);
    }

    /**
     * The descendBeastIndex function is used to decrement the index of the beast army.
     *
     * @return The index of the beast that is to be moved down
     *
     */
	public void descendBeastIndex()
    {
        int i;

        i = getCharacterIndex(boardManager.showBeastArmy());
        boardManager.descendBeastIndex(i);
    }

    /**
     * The getCharacterIndex function is used to get the index of a character in an army.
     *
     * @param ArrayList<String> army: Print the characters in the army
     *
     * @return The index of the character selected by the player
     *
     * @docauthor Trelent
     */
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

    /**
     * The method is used to add a character to a team. It prompts the user to enter the character's
	 * name, type, health points, and armor. It then calls the `addCharacter()` method from the
	 * `boardManager` object to add the character to the team. If the addition is successful, it
	 * returns true. Otherwise, it returns false.
	 * 
	 * @param String team
     * @return boolean
	 * 
     */
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

    /**
     * The printCharacter function prints the name, type, health points and armor of a character.
     *
     * @param String name Print the name of the character
     * @param String type Store the name of the character
     * @param int healthPoints Print the health points of a character
     * @param int armor Print the armor value of the character
     *
     * @return Nothing, it only prints the information of a character
     */
	private void printCharacter(String name, String type, int healthPoints, int armor)
    {
        System.out.printf("nome:%s tipo:%s vida:%d armadura:%d",
                name,
                type,
                healthPoints,
                armor);
    }

    /**
     * The getInt function is used to get an integer from the user.
     * It will keep asking for input until a valid integer is given.
     *
     * @param String message Display a message to the user
     * @param int numberOfOptions Set the range of valid inputs
     *
     * @return A number between 1 and the given parameter
     */
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

    /**
     * The simulateBattle function simulates a battle between the two armies.
     * It returns a string that describes the outcome of the battle.
     *
     * @return A string that tells the user what happened in the battle
     */
	public void simulateBattle() {
        System.out.println(boardManager.simulateBattle());
    }
}
