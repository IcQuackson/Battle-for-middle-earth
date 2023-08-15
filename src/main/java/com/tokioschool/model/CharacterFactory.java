package com.tokioschool.model;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * The CharacterFactory class is a factory class that creates different types of GameUnit objects based
 * on the specified CharacterType.
 */
public class CharacterFactory
{
    private static final Map<CharacterType, Supplier<GameUnit>> characters = new HashMap<>();

    static
    {
        characters.put(CharacterType.ELF, Elf::new);
        characters.put(CharacterType.HOBBIT, Hobbit::new);
        characters.put(CharacterType.ORC, Orc::new);
        characters.put(CharacterType.HUMAN, Human::new);
        characters.put(CharacterType.TROLL, Troll::new);
    }

    public static GameUnit getCharacter(CharacterType type)
    {
        return characters.get(type).get();
    }
}
