package com.swordssorcery.server.model;

import java.util.HashMap;

public class Inventory {

    private HashMap<Integer, Item> itemMap = new HashMap<>();

    private void addItem(int itemId, int amount) {
        if(itemMap.containsKey(itemId)) {
            itemMap.get(itemId).increaseAmount(amount);
        } else {
            itemMap.put(itemId, new Item(itemId, amount));
        }
    }

    private void removeItem(int itemId, int amount) {
        Item item = itemMap.get(itemId);

        if(item.getAmount() - amount <= 0) {
            itemMap.remove(itemId);
        } else {
            item.decreaseAmount(amount);
        }
    }
}
