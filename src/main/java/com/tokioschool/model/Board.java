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

    ArrayList<GameUnit> getHeroes()
    {
        return this.heroes;
    }

    ArrayList<GameUnit> getBeasts()
    {
        return this.beasts;
    }

    GameUnit getHero(int i)
    {
        if (i < 0 || i >= this.heroes.size())
            return null;
        return this.heroes.get(i);
    }

    void setHeroes(ArrayList<GameUnit> heroes)
    {
        this.heroes = heroes;
    }

    void setBeasts(ArrayList<GameUnit> beasts)
    {
        this.beasts = beasts;
    }

    GameUnit getBeast(int i)
    {
        if (i < 0 || i >= this.beasts.size())
            return null;
        return this.beasts.get(i);
    }

    void addHero(Hero hero)
    {
        this.heroes.add(hero);
    }

    void addBeast(Beast beast)
    {
        this.beasts.add(beast);
    }

    void deleteHero(int i)
    {
        if (i >= 0 && i < this.heroes.size())
            this.heroes.remove(i);
    }

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
