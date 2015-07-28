package com.morethanheroic.swords.user.repository.dao;

import com.morethanheroic.swords.attribute.enums.Attribute;
import com.morethanheroic.swords.attribute.model.AttributeModifierData;
import com.morethanheroic.swords.race.model.Race;
import com.morethanheroic.swords.skill.service.Skills;
import com.morethanheroic.swords.inventory.repository.dao.ItemDatabaseEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;

@Entity(name = "user")
public class UserDatabaseEntity {

    @Id
    @GeneratedValue
    private String id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Race race;

    @Column(name = "registration_date", nullable = false)
    private Date registrationDate = new Date();

    @Column(name = "last_login_date", nullable = false)
    private Date lastLoginDate = new Date();

    //TODO: Transient for now! fix em
    @Transient
    private HashMap<Attribute, AttributeModifierData> attributeModifierMap = new HashMap<>();
    @Transient
    private Skills skills = new Skills();
    @Transient
    private HashMap<Integer, ItemDatabaseEntity> inventory = new HashMap<>();
    @Transient
    private PositionDatabaseEntity position = new PositionDatabaseEntity();

    public UserDatabaseEntity() {
    }

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