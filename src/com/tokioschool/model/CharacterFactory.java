package com.tokioschool.model;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

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
