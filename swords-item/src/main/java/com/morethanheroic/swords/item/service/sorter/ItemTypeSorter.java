package com.morethanheroic.swords.item.service.sorter;

import com.morethanheroic.swords.item.domain.ItemType;

import java.util.List;
import java.util.Map;

public interface ItemTypeSorter<T> {

    Map<ItemType, List<T>> sortByType(final List<T> inventoryItems);
}
