package model;

import java.util.*;

import static model.Character.TEAM_BEASTS;
import static model.Character.TEAM_HEROES;

public enum CharacterType
{
    ELF("Elfo", TEAM_HEROES),
    HOBBIT("Hobbit", TEAM_HEROES),
    HUMAN("Humano", TEAM_HEROES),
    ORC("Orco", TEAM_BEASTS),
    TROLL("Troll", TEAM_BEASTS);

    private final String name;
    private final String team;

    private static final Map<String, CharacterType> enumMap;

    // Build an immutable map of String name to enum pairs.
    static
    {
        Map<String, CharacterType> map = new HashMap<String, CharacterType>();
        for (CharacterType characterType : CharacterType.values())
            map.put(characterType.toString(), characterType);
        enumMap = Collections.unmodifiableMap(map);
    }

    private CharacterType(String name, String team)
    {
        this.name = name;
        this.team = team;
    }

    @Override
    public String toString()
    {
        return this.name;
    }

    public String getTeam()
    {
        return this.team;
    }

    public static CharacterType get(String name)
    {
        return enumMap.get(name);
    }

    static ArrayList<CharacterType> getTypesFromTeam(String team)
    {
        if (!team.equals(TEAM_HEROES) && !team.equals(TEAM_BEASTS))
            return null;
        // Get list of character types
        ArrayList<CharacterType> teamTypes = new ArrayList<>(Arrays.asList(CharacterType.values()));
        // Removes character types that are not heroes
        teamTypes.removeIf(characterType -> !characterType.getTeam().equals(team));
        return teamTypes;
    }
}
