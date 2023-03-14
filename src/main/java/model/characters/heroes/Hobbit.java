package model.characters.heroes;

import model.Character;
import model.Hero;

import static model.CharacterType.HOBBIT;
import static model.CharacterType.TROLL;

public class Hobbit extends Hero
{
    public Hobbit()
    {
        super("", HOBBIT, 0, 0);
    }

    public Hobbit(String name, int healthPoints, int armor)
    {
        super(name, HOBBIT, healthPoints, armor);
    }

    @Override
    protected int calculateDamage(Character character)
    {
        int damage;

        damage = super.rollAttackPower();
        if (character.getType().equals(TROLL))
            damage -= 5;
        return damage;
    }

    @Override
    protected Character clone()
    {
        return new Hobbit(getName(), getHealthPoints(), getArmor());
    }
}
