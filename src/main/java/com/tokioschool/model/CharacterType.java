package com.tokioschool.model;

import java.util.*;

public enum CharacterType
{
    ELF("Elfo", GameUnit.TEAM_HEROES),
    HOBBIT("Hobbit", GameUnit.TEAM_HEROES),
    HUMAN("Humano", GameUnit.TEAM_HEROES),
    ORC("Orco", GameUnit.TEAM_BEASTS),
    TROLL("Troll", GameUnit.TEAM_BEASTS);

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

    /**
	 * The function returns the team name as a string.
	 * 
	 * @return The method is returning a String value, which is the team name.
	 */
	public String getTeam()
    {
        return this.team;
    }

    /**
	 * The function returns the CharacterType associated with a given name.
	 * 
	 * @param name The name parameter is a String that represents the name of the character type.
	 * @return The method is returning a CharacterType object.
	 */
	public static CharacterType get(String name)
    {
        return enumMap.get(name);
    }

    /**
	 * The function "getTypesFromTeam" returns a list of character types that belong to a specific team
	 * (either "heroes" or "beasts").
	 * 
	 * @param team A string representing the team name. It can be either "heroes" or "beasts".
	 * @return The method is returning an ArrayList of CharacterType objects.
	 */
	static ArrayList<CharacterType> getTypesFromTeam(String team)
    {
        if (!team.equals(GameUnit.TEAM_HEROES) && !team.equals(GameUnit.TEAM_BEASTS))
            return null;
        // Get list of character types
        ArrayList<CharacterType> teamTypes = new ArrayList<>(Arrays.asList(CharacterType.values()));
        // Removes character types that are not heroes
        teamTypes.removeIf(characterType -> !characterType.getTeam().equals(team));
        return teamTypes;
    }
}
