package characters;

import main_classes.CharacterType;
import main_classes.GameCharacter;
import main_classes.Hero;


public class Hobbit extends GameCharacter implements Hero {

    private final int ATTACK_DECREASE_VS_TROLL = 5;

    public Hobbit() {
        super(CharacterType.ELF);
    }

    public Hobbit(String name, int healthPoints, int armor) {
        super(name, healthPoints, armor, CharacterType.HOBBIT);
    }

    @Override
    public void attack(GameCharacter enemyCharacter) {

        int attack = attackPointsLambdaSupplier.get();
        
        int enemyArmor = enemyCharacter.getArmor();

        // When Hobbits attack Trolls they decrease striking damage
        if (enemyCharacter.getType() == CharacterType.TROLL) {
            attack -= ATTACK_DECREASE_VS_TROLL;

            attack = setZeroIfNegative(attack);
        }

        int damageDone = (int) (attack - enemyArmor);

        damageDone = setZeroIfNegative(damageDone);

        enemyCharacter.decreaseHealthPoints(damageDone);
        
        System.out.printf("%s saca %d e tira %d de vida a %s.\n",
                            this.getName(), (int) attack , damageDone, enemyCharacter.getName());
        
    }
}
