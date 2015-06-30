package com.swordssorcery.server.model;

public class InventoryItem {

    private int item;
    private long amount;

    public InventoryItem(int item, long amount) {
        this.setItem(item);
        this.setAmount(amount);
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
