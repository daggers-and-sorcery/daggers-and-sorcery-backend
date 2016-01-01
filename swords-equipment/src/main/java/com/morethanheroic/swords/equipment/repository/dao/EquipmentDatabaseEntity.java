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

    public boolean isWeaponIdentified() {
        return weaponIdentified;
    }

    public void setWeaponIdentified(boolean weaponIdentified) {
        this.weaponIdentified = weaponIdentified;
    }

    public boolean isOffhandIdentified() {
        return offhandIdentified;
    }

    public void setOffhandIdentified(boolean offhandIdentified) {
        this.offhandIdentified = offhandIdentified;
    }

    public boolean isHelmIdentified() {
        return helmIdentified;
    }

    public void setHelmIdentified(boolean helmIdentified) {
        this.helmIdentified = helmIdentified;
    }

    public boolean isGlovesIdentified() {
        return glovesIdentified;
    }

    public void setGlovesIdentified(boolean glovesIdentified) {
        this.glovesIdentified = glovesIdentified;
    }

    public boolean isRingIdentified() {
        return ringIdentified;
    }

    public void setRingIdentified(boolean ringIdentified) {
        this.ringIdentified = ringIdentified;
    }

    public boolean isAmuletIdentified() {
        return amuletIdentified;
    }

    public void setAmuletIdentified(boolean amuletIdentified) {
        this.amuletIdentified = amuletIdentified;
    }

    public boolean isBootsIdentified() {
        return bootsIdentified;
    }

    public void setBootsIdentified(boolean bootsIdentified) {
        this.bootsIdentified = bootsIdentified;
    }

    public boolean isBracerIdentified() {
        return bracerIdentified;
    }

    public void setBracerIdentified(boolean bracerIdentified) {
        this.bracerIdentified = bracerIdentified;
    }

    public boolean isChestIdentified() {
        return chestIdentified;
    }

    public void setChestIdentified(boolean chestIdentified) {
        this.chestIdentified = chestIdentified;
    }

    public boolean isLegsIdentified() {
        return legsIdentified;
    }

    public void setLegsIdentified(boolean legsIdentified) {
        this.legsIdentified = legsIdentified;
    }
}
