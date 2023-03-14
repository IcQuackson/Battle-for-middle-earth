package model.characters.beasts;

import model.Beast;
import model.Character;

import static model.CharacterType.TROLL;

public class Troll extends Beast
{
    public Troll()
    {
        super("", TROLL, 0, 0);
    }

    public Troll(String name, int healthPoints, int armor)
    {
        super(name, TROLL, healthPoints, armor);
    }

    @Override
    protected Character clone()
    {
        return new Troll(getName(), getHealthPoints(), getArmor());
    }
}
