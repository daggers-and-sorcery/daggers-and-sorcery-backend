package com.morethanheroic.swords.user.repository.dao;

import com.morethanheroic.swords.attribute.enums.Attribute;
import com.morethanheroic.swords.attribute.model.AttributeModifierData;
import com.morethanheroic.swords.race.model.Race;
import com.morethanheroic.swords.skill.service.Skills;

import java.util.Date;
import java.util.HashMap;

public class UserDatabaseEntity {

    private int id;
    private String email;
    private String username;
    private String password;
    private Race race;
    private Date registrationDate = new Date();
    private Date lastLoginDate = new Date();
    private Date lastRegenerationDate = new Date();
    private int map;
    private int x;
    private int y;
    private int health;
    private int mana;
    private int movement;

    //TODO: Transient for now! fix em
    private HashMap<Attribute, AttributeModifierData> attributeModifierMap = new HashMap<>();
    private Skills skills = new Skills();

    public UserDatabaseEntity() {
    }

    public UserDatabaseEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public void setAttributeModifier(Attribute attribute, AttributeModifierData attributeModifierData) {
        attributeModifierMap.put(attribute, attributeModifierData);
    }

    public AttributeModifierData getAttributeModifier(Attribute attribute) {
        return attributeModifierMap.get(attribute);
    }

    public String toString() {
        return "[id: " + id + " name: " + username + "]";
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Skills getSkills() {
        return skills;
    }


    public int getMap() {
        return map;
    }

    public void setMap(int map) {
        this.map = map;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Date getLastRegenerationDate() {
        return lastRegenerationDate;
    }

    public void setLastRegenerationDate(Date lastRegenerationDate) {
        this.lastRegenerationDate = lastRegenerationDate;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getMovement() {
        return movement;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }
}