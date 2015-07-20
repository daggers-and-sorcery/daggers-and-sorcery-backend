package com.swordssorcery.server.model.db;

public class Item {

    private final int id;
    private int amount;

    public Item(int id, int amount) {
        this.id = id;
        this.setAmount(amount);
    }

    public int getId() {
        return id;
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
