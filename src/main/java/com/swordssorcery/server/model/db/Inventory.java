package com.swordssorcery.server.model.db;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Inventory {

    private HashMap<Integer, Item> itemMap = new HashMap<>();

    public void addItem(int itemId, int amount) {
        if(itemMap.containsKey(itemId)) {
            itemMap.get(itemId).increaseAmount(amount);
        } else {
            itemMap.put(itemId, new Item(itemId, amount));
        }
    }

    public void removeItem(int itemId, int amount) {
        Item item = itemMap.get(itemId);

        if(item.getAmount() - amount <= 0) {
            itemMap.remove(itemId);
        } else {
            item.decreaseAmount(amount);
        }
    }

    public List<Item> getItemList() {
        return Collections.unmodifiableList(new ArrayList<>(itemMap.values()));
    }
}
