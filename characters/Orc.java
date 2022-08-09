package characters;

import main_classes.Beast;
import main_classes.CharacterType;
import main_classes.GameCharacter;

public class Orc extends GameCharacter implements Beast{

    private double ENEMY_ARMOR_DECREASE_MODIFIER = 0.1;

    public Orc() {
        super(CharacterType.ORC);
    }

    public Orc(String name, int healthPoints, int armor) {
        super(name, healthPoints, armor, CharacterType.ORC);
    }

    @Override
    public void attack(GameCharacter enemyCharacter) {
        
        int attack = attackPointsLambdaSupplier.get();
        
        // When orcs attack they decrease enemy armor before dealing damage
        int enemyArmor = (int) (enemyCharacter.getArmor() - (int) enemyCharacter.getArmor()*ENEMY_ARMOR_DECREASE_MODIFIER);

        int damageDone = (int) (attack) - enemyArmor;

        damageDone = setZeroIfNegative(damageDone);

        enemyCharacter.decreaseHealthPoints(damageDone);
        
        System.out.printf("%s saca %d e tira %d de vida a %s.\n",
                            this.getName(), (int) attack , damageDone, enemyCharacter.getName());

        
    }
}
