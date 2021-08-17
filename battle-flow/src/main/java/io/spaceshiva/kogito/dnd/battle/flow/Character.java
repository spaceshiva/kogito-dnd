package io.spaceshiva.kogito.dnd.battle.flow;

import java.util.Objects;
import java.util.TreeMap;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a character in the game
 */
public class Character {
    /**
     * Their name, for example "Conan"
     */
    private String name;
    /**
     * How hard they are going to hit
     */
    private int strength;
    /**
     * To calculate who goes first
     */
    private int dexterity;
    /**
     * Total HP
     */
    private int hitPoints;
    /**
     * Damage taken
     */
    private int damage;
    /**
     * To calculate how damage is taken
     */
    private int armor;
    /**
     * To give a visual cue for UI
     */
    @JsonProperty("class")
    private String clazz;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public int getArmorClass() {
        return armor + dexterity;
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public boolean isDead() {
        return hitPoints - damage <= 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Character character = (Character) o;
        return strength == character.strength && dexterity == character.dexterity && hitPoints == character.hitPoints && armor == character.armor && Objects.equals(name, character.name) && Objects.equals(clazz, character.clazz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, strength, dexterity, hitPoints, armor, clazz);
    }

    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", hitPoints=" + hitPoints +
                ", damage=" + damage +
                ", clazz='" + clazz + '\'' +
                '}';
    }
}