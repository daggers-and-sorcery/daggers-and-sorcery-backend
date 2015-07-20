package com.swordssorcery.server.model.db.user;

import com.swordssorcery.server.game.attribute.Attribute;
import com.swordssorcery.server.game.attribute.data.AttributeModifierData;
import com.swordssorcery.server.game.race.Race;
import com.swordssorcery.server.model.db.Skills;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.awt.*;
import java.util.Date;
import java.util.HashMap;

@Document(collection = "user")
public class UserDatabaseEntity {

    @Id
    private String id;

    @Indexed(unique = true)
    private String email;

    @Indexed(unique = true)
    private String username;

    private String password;
    private Race race;
    private HashMap<Attribute, AttributeModifierData> attributeModifierMap = new HashMap<>();
    private Skills skills = new Skills();
    private HashMap<Integer, ItemDatabaseEntity> inventory = new HashMap<>();
    private Date registrationDate = new Date();
    private Date lastLoginDate = new Date();
    private PositionDatabaseEntity position = new PositionDatabaseEntity();

    public UserDatabaseEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getId() {
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

    public HashMap<Integer, ItemDatabaseEntity> getInventory() {
        return inventory;
    }

    public PositionDatabaseEntity getPosition() {
        return position;
    }

    public void setPosition(PositionDatabaseEntity position) {
        this.position = position;
    }
}