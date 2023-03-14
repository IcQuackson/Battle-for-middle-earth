package model.characters.heroes;

import model.Character;
import model.Hero;

import static model.CharacterType.ELF;
import static model.CharacterType.ORC;

public class Elf extends Hero
{
    public Elf()
    {
        super("", ELF, 0, 0);
    }

    public Elf(String name, int healthPoints, int armor)
    {
        super(name, ELF, healthPoints, armor);
    }

    @Override
    public int calculateDamage(Character character)
    {
        int damage;

        damage = super.rollAttackPower();
        if (character.getType().equals(ORC))
            damage += 10;
        return damage;
    }

    @Override
    protected Character clone()
    {
        return new Elf(getName(), getHealthPoints(), getArmor());
    }
}
