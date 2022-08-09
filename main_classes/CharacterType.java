package main_classes;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum CharacterType {
    ELF("Elfo", "Heróis"),
    HOBBIT("Hobbit", "Heróis"),
    HUMAN("Humano", "Heróis"),
    ORC("Orco", "Bestas"),
    TROLL("Troll", "Bestas");

    private String name;

    private String team;

    private static final Map<String, CharacterType> ENUM_MAP;

    // Build an immutable map of String name to enum pairs.
    static {
        Map<String, CharacterType> map = new HashMap<String, CharacterType>();
        for (CharacterType characterType : CharacterType.values()) {
            map.put(characterType.toString(), characterType);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    private CharacterType(String name, String team) {
        this.name = name;
        this.team = team;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public static CharacterType get(String name) {
        return ENUM_MAP.get(name);
    } 
}
