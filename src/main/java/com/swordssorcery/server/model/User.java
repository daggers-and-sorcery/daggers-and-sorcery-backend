package com.swordssorcery.server.model;

import com.swordssorcery.server.game.attribute.Attribute;
import com.swordssorcery.server.game.attribute.AttributeModifierType;
import com.swordssorcery.server.game.attribute.data.AttributeModifierData;
import com.swordssorcery.server.game.attribute.data.DefaultAttributeModifierData;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.LinkedHashMap;

@Document
public class User {

    @Id
    private String id;

    @Indexed(unique = true)
    private String email;

    @Indexed(unique = true)
    private String username;
    
    private String password;
    private int race;
    private HashMap<Attribute, AttributeModifierData> attributeModifierMap = new HashMap<>();

    public User(String username, String password) {
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

    public int getRace() {
        return race;
    }

    public void setRace(int race) {
        this.race = race;
    }

    public void setAttributeModifier(Attribute attribute, AttributeModifierData attributeModifierData) {
        attributeModifierMap.put(attribute, attributeModifierData);
    }

    public AttributeModifierData getAttributeModifier(Attribute attribute) {
        return attributeModifierMap.get(attribute);
    }
}