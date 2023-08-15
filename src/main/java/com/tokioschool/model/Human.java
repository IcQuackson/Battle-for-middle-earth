package com.tokioschool.model;

import static com.tokioschool.model.CharacterType.HUMAN;

/**
 * The Human class is a subclass of the Hero class and represents a human character in a game.
 */
public class Human extends Hero
{
    public Human()
    {
        super("", HUMAN, 0, 0);
    }

    public Human(String name, int healthPoints, int armor)
    {
        super(name, HUMAN, healthPoints, armor);
    }

    /**
	 * The function returns a new instance of the Human class with the same name, health points, and
	 * armor as the original instance.
	 * 
	 * @return The method is returning a new instance of the Human class, which is a subclass of the
	 * GameUnit class.
	 */
	@Override
	protected GameUnit clone()
    {
        return new Human(getName(), getHealthPoints(), getArmor());
    }
}
