package model.characters.beasts;

import model.Beast;
import model.Character;

import static model.CharacterType.ORC;

public class Orc extends Beast
{
    public Orc()
    {
        super("", ORC, 0, 0);
    }

    public Orc(String name, int healthPoints, int armor)
    {
        super(name, ORC, healthPoints, armor);
    }

    @Override
    public int calculateEnemyArmor(Character enemy)
    {
        return (int) (enemy.getArmor() - enemy.getArmor() * 0.1);
    }

    @Override
    protected Character clone()
    {
        return new Orc(getName(), getHealthPoints(), getArmor());
    }
}
