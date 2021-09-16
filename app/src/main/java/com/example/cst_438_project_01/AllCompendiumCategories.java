package com.example.cst_438_project_01;

import java.util.List;

public class AllCompendiumCategories {

    private List<CompendiumEntry> monsters;

    private List<CompendiumEntry> treasure;

    private List<CompendiumEntry> equipment;

    private List<CompendiumEntry> materials;

    private AllCompendiumCreatures creatures;

    public AllCompendiumCreatures getCreatures() {
        return creatures;
    }

    public List<CompendiumEntry> getMonsters() {
        return monsters;
    }

    public List<CompendiumEntry> getTreasure() {
        return treasure;
    }

    public List<CompendiumEntry> getEquipment() {
        return equipment;
    }

    public List<CompendiumEntry> getMaterials() {
        return materials;
    }
}
