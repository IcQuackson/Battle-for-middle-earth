package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

import main_classes.CharacterType;
import main_classes.GameCharacter;
import main_classes.GameCharacterFactory;

public class BattleConsole {
    
    private Battle battle;

    private Battle backupBattle;

    private Scanner scanner;

    public BattleConsole(Battle battle, Scanner scanner) {
        this.battle = battle;
        this.scanner = scanner;
    }

    public void run() {

        int input;
        do {   
             
            input = getIntFromUserInput(Message.initialBattleMessage, 4);

            switch (input) {
                case 1:
                    manageHeroArmy();
                    break;
                case 2:
                    manageBeastArmy();
                    break;
                case 3:
                    // Creates battle backup
                    backupBattle = new Battle(battle.getHeroArmy(), battle.getBeastArmy());
                    battle.startBattle();
                    // Resets battle to previous state
                    battle = backupBattle;
                    break;
                default:
            }
            
        }
        while (input != 4);
    }

    private void manageHeroArmy()
    {
        GameCharacter hero;
        int heroIndex;
        int input;
        
        do {
            
            input = getIntFromUserInput(Message.manageHerosMessage, 6);

            switch (input)
            {
                case 1:
                    System.out.println(System.out.printf("Exército - %s:\n", GameCharacter.TEAM_HEROES));
                    printArmy(battle.getHeroArmy());
                    System.out.println();
                    break;
                case 2:
                    hero = createHero();
                    battle.addHero(hero);
                    break;
                case 3:
                    // Get hero index and delete it
                    heroIndex = getHeroIndex();
                    GameCharacter deletedHero = battle.getHeroArmy().get(heroIndex);
                    battle.deleteHeroByIndex(heroIndex);
                    System.out.printf("Herói apagado: " + deletedHero.toString() + "\n");
                    break;
                case 4:
                    // Get hero index and ascend it
                    heroIndex = getHeroIndex();
                    battle.ascendHeroInArmy(heroIndex);
                    break;
                case 5:
                    // Get hero index and descend it
                    heroIndex = getHeroIndex();
                    battle.descendHeroInArmy(heroIndex);
                    break;
                default:
            }
        }
        while (input != 6);
    }

    private void manageBeastArmy()
    {
        GameCharacter beast;
        int beastIndex;
        int input;
        
        do {
            
            input = getIntFromUserInput(Message.manageBeastsMessage, 6);

            switch (input)
            {
                case 1:
                    System.out.println(System.out.printf("Exército - %s:\n", GameCharacter.TEAM_BEASTS));
                    printArmy(battle.getBeastArmy());
                    System.out.println();
                    break;
                case 2:
                    beast = createBeast();
                    battle.addBeast(beast);
                    break;
                case 3:
                    // Get hero index and delete it
                    beastIndex = getBeastIndex();
                    GameCharacter deletedBeast = battle.getBeastArmy().get(beastIndex);
                    battle.deleteBeastByIndex(beastIndex);
                    System.out.printf("Besta apagada: " + deletedBeast.toString() + "\n");
                    break;
                case 4:
                    // Get hero index and ascend it
                    beastIndex = getBeastIndex();
                    battle.ascendBeastInArmy(beastIndex);
                    break;
                case 5:
                    // Get hero index and descend it
                    beastIndex = getBeastIndex();
                    battle.descendBeastInArmy(beastIndex);
                    break;
                default:
            }

        } while (input != 6);
    }

    private int getHeroIndex()
    {
        return getCharacterIndex(GameCharacter.TEAM_HEROES);
    }

    private int getBeastIndex()
    {
        return getCharacterIndex(GameCharacter.TEAM_BEASTS);
    }

    private int getCharacterIndex(String team)
    {

        ArrayList<GameCharacter> selectedTeamArmy = null;

        if (team.equals(GameCharacter.TEAM_HEROES)) {
            selectedTeamArmy = battle.getHeroArmy();
        }
        else if (team.equals(GameCharacter.TEAM_BEASTS)) {
            selectedTeamArmy = battle.getBeastArmy();
        }
        else {
            try {
                throw new Exception("Choose between Heroes and Beasts");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        String message = "Selecione o index da personagem:\n";
        int i = 1;
        for (GameCharacter character : selectedTeamArmy) {
            message += String.format("-> %d. " + character.toString() + "\n", i);
            i++;
        }
        message += "index: ";

        return getIntFromUserInput(message, selectedTeamArmy.size()) - 1;
    }

    private GameCharacter createHero()
    {
        return createCharacter(GameCharacter.TEAM_HEROES);
    }

    private GameCharacter createBeast()
    {
        return createCharacter(GameCharacter.TEAM_BEASTS);
    }

    private GameCharacter createCharacter(String team)
    {

        String name;
        CharacterType type;
        int healthPoints;
        int armor;

        List<CharacterType> selectedTeamTypes = new ArrayList<CharacterType>(Arrays.asList(CharacterType.values()));

        if (team.equals(GameCharacter.TEAM_HEROES)) {
            selectedTeamTypes.removeIf(characterType -> characterType.getTeam().equals(GameCharacter.TEAM_HEROES));  // Get heroes by removing beasts
        }
        else if (team.equals(GameCharacter.TEAM_BEASTS)) {
            selectedTeamTypes.removeIf(characterType -> characterType.getTeam().equals(GameCharacter.TEAM_BEASTS));  // Get beasts by removing heroes
        }
        else {
            try {
                throw new Exception("Team doesn't exist!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        // Gets Name
        System.out.print("Nome: ");
        name = scanner.nextLine();
        System.out.println();

        // Gets Character Type
        int optionNumber = 1;
        // Creates message to print all selected team types
        String message = "Tipos:\n";
        for (CharacterType selectedTeamType : selectedTeamTypes) {
            message += (optionNumber++ + ". " + selectedTeamType.toString() + "\n");
        }
        message += "Escolha o tipo: ";

        int typeIndex = getIntFromUserInput(message, selectedTeamTypes.size());

        type = selectedTeamTypes.get(typeIndex - 1);

        // Gets Health Points
        healthPoints = getIntFromUserInput("Pontos de vida: ", x -> x > 0);

        // Gets Armor
        armor = getIntFromUserInput("Armadura: ", x -> x > 0);

        // Creates hero with selected type using the game character factory
        GameCharacter gameCharacter = GameCharacterFactory.getGameCharacter(type);
        // Set hero properties
        gameCharacter.setName(name);
        gameCharacter.setHealthPoints(healthPoints);
        gameCharacter.setArmor(armor);

        return gameCharacter;
    }

    public int getIntFromUserInput(String message, Predicate<Integer> constraint) {

        int input = -1;
        do {
            System.out.print(message);
            try {
                input = scanner.nextInt();
            } catch (InputMismatchException e) {
                // Clears input
                scanner.next();
                System.out.println("Valor tem que ser um número inteiro positivo!\n");
                continue;
            }
            if (!constraint.test(input)) {
                System.out.println("Valor inapropriado!\n");
            }
        } while(!constraint.test(input));
        System.out.println();

        return input;
    }

    public int getIntFromUserInput(String message, int numberOfOptions) {

        Predicate<Integer> constraint = x -> x >= 1 && x <= numberOfOptions;   // Only lines with options are counted
        
        return getIntFromUserInput(message, constraint); 
    }

    public static void printArmy(ArrayList<GameCharacter> army)
    {   
        int i = 1;
        for (GameCharacter character : army) {
            System.out.printf("-> %d. " + character.toString() + "\n", i);
            i++;
        }
    }
}