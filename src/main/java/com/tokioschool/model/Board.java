package com.tokioschool.model;

import java.util.ArrayList;
import java.util.Collections;

public class Board
{
    private ArrayList<GameUnit> heroes;
    private  ArrayList<GameUnit> beasts;

    public Board()
    {
        this.heroes = new ArrayList<>();
        this.beasts = new ArrayList<>();
    }

    /**
     * The getHeroes function returns the heroes ArrayList.
     *
     * @return An arraylist of gameunit objects
     */
    ArrayList<GameUnit> getHeroes()
    {
        return this.heroes;
    }

    /**
     * The getBeasts function returns the beasts ArrayList.
     *
     * @return An arraylist of gameunit objects
     */
    ArrayList<GameUnit> getBeasts()
    {
        return this.beasts;
    }

    /**
     * The getHero function returns the hero at a given index.
     *
     * @param int i Get the hero at index i in the heroes arraylist
     *
     * @return A gameunit object
     */
    GameUnit getHero(int i)
    {
        if (i < 0 || i >= this.heroes.size())
            return null;
        return this.heroes.get(i);
    }

    /**
     * The setHeroes function sets the heroes ArrayList to a new ArrayList of GameUnit objects.
     *
     * @param ArrayList GameUnit; heroes Set the heroes arraylist of gameunit objects
     *
     * @return Void
     *
     */
    void setHeroes(ArrayList<GameUnit> heroes)
    {
        this.heroes = heroes;
    }

    /**
     * The setBeasts function sets the beasts ArrayList to a new ArrayList of GameUnit objects.
     *
     * @param ArrayList GameUnit; beasts Set the beasts arraylist to a new one
     *
     * @return Nothing
     */
    void setBeasts(ArrayList<GameUnit> beasts)
    {
        this.beasts = beasts;
    }

    /**
     * The getBeast function returns the beast at a given index.
     *
     * @param int i Get the beast at a specific index in the array list
     *
     * @return A gameunit
     */
    GameUnit getBeast(int i)
    {
        if (i < 0 || i >= this.beasts.size())
            return null;
        return this.beasts.get(i);
    }

    /**
     * The addHero function adds a hero to the heroes array.
     *
     * @param Hero hero Add a hero to the heroes arraylist
     *
     * @return Nothing
     */
    void addHero(Hero hero)
    {
        this.heroes.add(hero);
    }

    /**
     * The addBeast function adds a beast to the list of beasts.
     *
     * @param Beast beast Add a new beast to the list of beasts
     *
     * @return Void
     */
    void addBeast(Beast beast)
    {
        this.beasts.add(beast);
    }

    /**
     * The deleteHero function removes a hero from the heroes array.
     *
     * @param int i Specify the index of the hero to be deleted
     *
     * @return Nothing
     *
     */
    void deleteHero(int i)
    {
        if (i >= 0 && i < this.heroes.size())
            this.heroes.remove(i);
    }

    /**
     * The deleteBeast function removes a beast from the list of beasts.
     *
     * @param int i Specify the index of the beast to be deleted
     *
     * @return Void
     */
    void deleteBeast(int i)
    {
        if (i >= 0 && i < this.beasts.size())
            this.beasts.remove(i);
    }

    void ascendHeroIndex(int i)
    {
        if (i > 0 && i < this.heroes.size())
            Collections.swap(this.heroes, i, i - 1);
    }

    void descendHeroIndex(int i)
    {
        if (i >= 0 && i < this.heroes.size() - 1)
            Collections.swap(this.heroes, i, i + 1);
    }

    void ascendBeastIndex(int i)
    {
        if (i > 0 && i < this.beasts.size())
            Collections.swap(this.beasts, i, i - 1);
    }

    void descendBeastIndex(int i)
    {
        if (i >= 0 && i < this.beasts.size() - 1)
            Collections.swap(this.beasts, i, i + 1);
    }
}
