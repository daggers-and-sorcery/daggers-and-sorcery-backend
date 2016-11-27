package com.morethanheroic.swords.equipment.repository.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipmentDatabaseEntity {

    private int userId;

    private int weapon;
    private int offhand;
    private int helm;
    private int gloves;
    private int ring;
    private int amulet;
    private int boots;
    private int bracer;
    private int chest;
    private int legs;
    private int quiver;
    private int belt;

    private int quiverAmount;

    private boolean weaponIdentified;
    private boolean offhandIdentified;
    private boolean helmIdentified;
    private boolean glovesIdentified;
    private boolean ringIdentified;
    private boolean amuletIdentified;
    private boolean bootsIdentified;
    private boolean bracerIdentified;
    private boolean chestIdentified;
    private boolean legsIdentified;
    private boolean quiverIdentified;
    private boolean beltIdentified;
}
