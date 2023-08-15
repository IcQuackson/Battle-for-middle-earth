package com.tokioschool.model;

import static com.tokioschool.model.CharacterType.TROLL;

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
    
    /**
	 * The function returns a new instance of the Troll class with the same name, health points, and
	 * armor as the original instance.
	 * 
	 * @return The method is returning a new instance of the Troll class, with the same name, health
	 * points, and armor as the original Troll object.
	 */
	@Override
	protected GameUnit clone()
    {
        return new Troll(getName(), getHealthPoints(), getArmor());
    }
}
