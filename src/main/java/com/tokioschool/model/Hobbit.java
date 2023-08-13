package com.tokioschool.model;

import static com.tokioschool.model.CharacterType.HOBBIT;
import static com.tokioschool.model.CharacterType.TROLL;

public class Hobbit extends Hero {
    public Hobbit() {
        super("", HOBBIT, 0, 0);
    }

    public Hobbit(String name, int healthPoints, int armor) {
        super(name, HOBBIT, healthPoints, armor);
    }

    @Override
    protected int calculateDamage(GameUnit character) {
        int damage;

        damage = super.rollAttackPower();
        if (character.getType().equals(TROLL))
            damage -= 5;
        return damage;
    }

    @Override
    protected GameUnit clone() {
        return new Hobbit(getName(), getHealthPoints(), getArmor());
    }

    @Override
    protected int getMaxAttackPower(GameUnit character) {
        int damage;

        damage = super.getMaxAttackPower(null);
        if (character.getType().equals(TROLL))
            damage -= 5;
        return damage;
    }
}
