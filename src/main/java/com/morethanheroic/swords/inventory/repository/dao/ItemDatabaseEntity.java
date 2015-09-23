package com.morethanheroic.swords.inventory.repository.dao;

public class ItemDatabaseEntity {

    private int userId;
    private int itemId;
    private int amount;

    public int getUserId() {return  userId; }

    public int getItemId() {
        return itemId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void increaseAmount(int amount) {
        this.amount += amount;
    }

    public void decreaseAmount(int amount) {
        this.amount -= amount;
    }
}
