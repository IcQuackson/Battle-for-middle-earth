package application;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

import main_classes.GameCharacter;

public class Battle {
    
    private ArrayList<GameCharacter> heroArmy;

    private ArrayList<GameCharacter> beastArmy;

    public Battle() {
        this.heroArmy = new ArrayList<>();
        this.beastArmy = new ArrayList<>();
    }

    Battle(ArrayList<GameCharacter> heroArmy, ArrayList<GameCharacter> beastArmy) {
        this.heroArmy = heroArmy;
        this.beastArmy = beastArmy;
    }

    public ArrayList<GameCharacter> getHeroArmy()
    {
        try
        {
            return deepCopyArmyWithSerialization(heroArmy);
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<GameCharacter> getBeastArmy()
    {
        try
        {
            return deepCopyArmyWithSerialization(beastArmy);
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public void addHero(GameCharacter hero)
    {
        heroArmy.add(hero);
    }
    
    public void addBeast(GameCharacter beast)
    {
        beastArmy.add(beast);
    }

    public boolean ascendHeroInArmy(int index)
    {
        if (index > 0) {
            Collections.swap(this.heroArmy, index, index - 1);
            return true;
        }
        return false;
    }
    
    public boolean ascendBeastInArmy(int index) {
        if (index > 0) {
            Collections.swap(this.beastArmy, index, index - 1);
            return true;
        }
        return false;
    }

    public boolean descendHeroInArmy(int index) {
        if (index >= 0 && index < this.heroArmy.size() - 1) {
            Collections.swap(this.heroArmy, index, index + 1);
            return true;
        }
        return false;
    }

    public boolean descendBeastInArmy(int index) {
        if (index >= 0 && index < this.beastArmy.size() - 1) {
            Collections.swap(this.beastArmy, index, index + 1);
            return true;
        }
        return false;
    }

    // TODO return iterator because of abstraction
    @SuppressWarnings("unchecked")
    private static ArrayList<GameCharacter> deepCopyArmyWithSerialization(ArrayList<GameCharacter> army) throws Exception {

        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            // Sets up a ByteArrayOutputStream which is used to create the ObjectOutputStream 
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);

            // Serialize and pass the object
            // The writeObject() method recursively traverses the object's graph, generates a new object in byte form,
            // and sends it to the ByteArrayOutputStream.
            oos.writeObject(army);

            // ensures the whole object has been sent.
            
            oos.flush();
            

            ByteArrayInputStream bin = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bin);

            // return the new object
            return (ArrayList<GameCharacter>) ois.readObject();

        } catch (Exception e ) {
            System.out.println("Exception in deepCopyArmyWithSerialization = " + e);
            throw(e);
        }
    }

    private static void deleteDeadCharacters(ArrayList<GameCharacter> army) {

        ListIterator<GameCharacter> listIterator = army.listIterator();

        while(listIterator.hasNext()) {
            if (listIterator.next().getHealthPoints() == 0) {
                listIterator.remove();
            }
        }
    }

    private boolean simulateTurn()
    {
        System.out.printf("Exército - %s:\n", GameCharacter.TEAM_HEROES);
        BattleConsole.printArmy(heroArmy);
        System.out.println("------");
        System.out.printf("Exército - %s:\n", GameCharacter.TEAM_BEASTS);
        BattleConsole.printArmy(beastArmy);
        System.out.println();

        int smallerArmysize;

        // Find smaller army size because only the first nth characters fight (n being the smaller army size)
        if (heroArmy.size() >= beastArmy.size()) {
            smallerArmysize = beastArmy.size();
        }
        else {
            smallerArmysize = heroArmy.size();
        }

        // For each position the hero strikes the beast first and then the beast strikes back
        for (int i = 0; i < smallerArmysize; i++) {
            GameCharacter hero = heroArmy.get(i);
            GameCharacter beast = beastArmy.get(i);

            System.out.printf("   Luta entre %s (Vida=%d Armadura=%d) e %s (Vida=%d Armadura=%d)\n",
                                hero.getName(), hero.getHealthPoints(), hero.getArmor(),
                                    beast.getName(), beast.getHealthPoints(), beast.getArmor());

            System.out.print("      ");
            hero.attack(beast);
            System.out.print("      ");
            beast.attack(hero);
            
            if (beast.isDead()) {
                System.out.print("   ");
                System.out.println("Morre " + beast.getType() + " " + beast.getName() + "!");
            }

            else if (hero.isDead()) {
                System.out.print("   ");
                System.out.println("Morre " + hero.getType() + " " + hero.getName() + "!");
            }
        }
        
        // Clear dead characters from battle
        deleteDeadCharacters(heroArmy);
        deleteDeadCharacters(beastArmy);

        // If one of the armies is dead the battle is over
        if (beastArmy.size() == 0) {
            System.out.println("VITÓRIA DOS HERÓIS!");
            return false;
        }
        else if (heroArmy.size() == 0) {
            System.out.println("VITÓRIA DAS BESTAS!");
            return false;
        }
        else {
            return true;
        }
    }

    public void deleteHeroByIndex(int index)
    {
        if (index >= 0 && index < this.heroArmy.size()) {
            this.heroArmy.remove(index);
        }
    }
    
    public void deleteBeastByIndex(int index)
    {
        if (index >= 0 && index < this.beastArmy.size()) {
            this.beastArmy.remove(index);
        }
    }

    public void startBattle()
    {
        int turno = 1;
        boolean hasChanged = true;
        ArrayList<GameCharacter> backUpHeroArmy = getHeroArmy();
        ArrayList<GameCharacter> backUpBeastArmy = getBeastArmy();

        while (hasChanged)
        {
            System.out.println("Turno " + turno);
            hasChanged = simulateTurn();
            turno++;
            System.out.println();
        }
        // Resets to state before battle
        this.heroArmy = backUpHeroArmy;
        this.beastArmy = backUpBeastArmy;
    }
}
