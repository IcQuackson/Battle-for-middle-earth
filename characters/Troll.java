package characters;

import main_classes.Beast;
import main_classes.CharacterType;
import main_classes.GameCharacter;

public class Troll extends GameCharacter implements Beast {

    public Troll() {
        super(CharacterType.TROLL);
    }

    public Troll(String name, int healthPoints, int armor) {
        super(name, healthPoints, armor, CharacterType.TROLL);
    }

    @Override
    public void attack(GameCharacter enemyCharacter) {

        int attack = attackPointsLambdaSupplier.get();
        
        int enemyArmor = enemyCharacter.getArmor();

        int damageDone = attack - enemyArmor;

        damageDone = setZeroIfNegative(damageDone);

        enemyCharacter.decreaseHealthPoints(damageDone);

        System.out.printf("%s saca %d e tira %d de vida a %s.\n",
                            this.getName(), (int) attack , damageDone, enemyCharacter.getName());
    }

    @Override
    public GameCharacter clone() {
        return new Troll(this.getName(), this.getHealthPoints(), this.getArmor());
    }
    
}
