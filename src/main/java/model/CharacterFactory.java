package model;

import model.characters.beasts.Orc;
import model.characters.beasts.Troll;
import model.characters.heroes.Elf;
import model.characters.heroes.Hobbit;
import model.characters.heroes.Human;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static model.CharacterType.*;

class CharacterFactory
{
    private static final Map<CharacterType, Supplier<Character>> characters = new HashMap<>();

    static
    {
        characters.put(ELF, Elf::new);
        characters.put(HOBBIT, Hobbit::new);
        characters.put(ORC, Orc::new);
        characters.put(HUMAN, Human::new);
        characters.put(TROLL, Troll::new);
    }

    static Character getCharacter(CharacterType type)
    {
        return characters.get(type).get();
    }
}
