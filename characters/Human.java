package characters;

import main_classes.CharacterType;
import main_classes.GameCharacter;
import main_classes.Hero;

public class Human extends GameCharacter implements Hero {

    public Human() {
        super(CharacterType.HUMAN);
    }

    public Human(String name, int healthPoints, int armor) {
        super(name, healthPoints, armor, CharacterType.HUMAN);
    }

    @Override
    public void attack(GameCharacter enemyCharacter) {
        
        int attack = attackPointsLambdaSupplier.get();
        
        int enemyArmor = enemyCharacter.getArmor();

        int damageDone = (int) (attack - enemyArmor);

        damageDone = setZeroIfNegative(damageDone);

        enemyCharacter.decreaseHealthPoints(damageDone);
        
        System.out.printf("%s saca %d e tira %d de vida a %s.\n",
                            this.getName(), (int) attack , damageDone, enemyCharacter.getName());
        
    }
    
}
