package main_classes;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import characters.Elf;
import characters.Hobbit;
import characters.Human;
import characters.Orc;
import characters.Troll;

public abstract class GameCharacterFactory {

    private static Map<CharacterType, Supplier<GameCharacter>> gameCharactersByType = new HashMap<>();

    static {
        gameCharactersByType.put(CharacterType.ELF, Elf::new);
        gameCharactersByType.put(CharacterType.HOBBIT, Hobbit::new);
        gameCharactersByType.put(CharacterType.HUMAN, Human::new);
        gameCharactersByType.put(CharacterType.ORC, Orc::new);
        gameCharactersByType.put(CharacterType.TROLL, Troll::new);
    }
    
    public static GameCharacter getGameCharacter(CharacterType type) {

        return gameCharactersByType.get(type).get();
    }

    public void addGameCharacter(CharacterType type, Supplier<GameCharacter> characterConstructor) {
        gameCharactersByType.put(type, characterConstructor);
    }
}
