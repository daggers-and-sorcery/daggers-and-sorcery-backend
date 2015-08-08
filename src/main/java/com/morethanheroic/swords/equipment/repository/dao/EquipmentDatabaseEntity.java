package com.morethanheroic.swords.equipment.repository.dao;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getWeapon() {
        return weapon;
    }

    public void setWeapon(int weapon) {
        this.weapon = weapon;
    }

    public int getOffhand() {
        return offhand;
    }

    public void setOffhand(int offhand) {
        this.offhand = offhand;
    }

    public int getHelm() {
        return helm;
    }

    public void setHelm(int helm) {
        this.helm = helm;
    }

    public int getGloves() {
        return gloves;
    }

    public void setGloves(int gloves) {
        this.gloves = gloves;
    }

    public int getRing() {
        return ring;
    }

    public void setRing(int ring) {
        this.ring = ring;
    }

    public int getAmulet() {
        return amulet;
    }

    public void setAmulet(int amulet) {
        this.amulet = amulet;
    }

    public int getBoots() {
        return boots;
    }

    public void setBoots(int boots) {
        this.boots = boots;
    }

    public int getBracer() {
        return bracer;
    }

    public void setBracer(int bracer) {
        this.bracer = bracer;
    }

    public int getChest() {
        return chest;
    }

    public void setChest(int chest) {
        this.chest = chest;
    }

    public int getLegs() {
        return legs;
    }

    public void setLegs(int legs) {
        this.legs = legs;
    }
}
