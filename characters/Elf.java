package characters;

import main_classes.CharacterType;
import main_classes.GameCharacter;
import main_classes.Hero;

public class Elf extends GameCharacter implements Hero {

    private final int ATTACK_INCREASE_VS_ORC = 10;

    public Elf() {
        super(CharacterType.ELF);
    }

    public Elf(String name, int healthPoints, int armor) {
        super(name, healthPoints, armor, CharacterType.ELF);
    }

    @Override
    public void attack(GameCharacter enemyCharacter) {

        int attack = attackPointsLambdaSupplier.get();
        
        int enemyArmor = enemyCharacter.getArmor();

        // When elfs attack orcs they increase striking damage
        if (enemyCharacter.getType() == CharacterType.ORC) {
            attack += ATTACK_INCREASE_VS_ORC;
        }

        int damageDone = (int) (attack) - enemyArmor;

        damageDone = setZeroIfNegative(damageDone);

        enemyCharacter.decreaseHealthPoints(damageDone);
        
        System.out.printf("%s saca %d e tira %d de vida a %s.\n",
                            this.getName(), (int) attack , damageDone, enemyCharacter.getName());
    }
}
