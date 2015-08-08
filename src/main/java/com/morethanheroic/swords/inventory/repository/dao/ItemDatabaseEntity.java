package com.morethanheroic.swords.inventory.repository.dao;

public class ItemDatabaseEntity {

    private int user_id;
    private int item_id;
    private int amount;

    public int getUserId() {return  user_id; }

    public int getItemId() {
        return item_id;
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
