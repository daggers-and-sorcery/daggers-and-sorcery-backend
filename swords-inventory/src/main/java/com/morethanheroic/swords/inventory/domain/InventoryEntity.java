package com.morethanheroic.swords.inventory.domain;

import com.morethanheroic.entity.domain.Entity;
import com.morethanheroic.swords.inventory.repository.dao.ItemDatabaseEntity;
import com.morethanheroic.swords.inventory.repository.domain.InventoryMapper;
import com.morethanheroic.swords.inventory.service.InventoryManipulator;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.definition.cache.ItemDefinitionCache;
import com.morethanheroic.swords.journal.domain.JournalType;
import com.morethanheroic.swords.journal.service.JournalEntityFactory;
import com.morethanheroic.swords.money.domain.*;
import com.morethanheroic.swords.money.service.MoneyFacade;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @deprecated Use {@link InventoryManipulator} instead.
 */
@Deprecated
@Configurable
@RequiredArgsConstructor
public class InventoryEntity implements Entity {

    @Autowired
    private InventoryMapper inventoryMapper;

    @Autowired
    private JournalEntityFactory journalManager;

    @Autowired
    private MoneyFacade moneyFacade;

    @Autowired
    private ItemDefinitionCache itemDefinitionCache;

    @Autowired
    private InventoryManipulator inventoryManipulator;

    private final UserEntity userEntity;

    /**
     * @deprecated Use {@link InventoryManipulator#hasItem(UserEntity, ItemDefinition)}.
     */
    public boolean hasItem(ItemDefinition item) {
        return hasItem(item, IdentificationType.IDENTIFIED);
    }

    /**
     * @deprecated Use {@link #hasItem(ItemDefinition, IdentificationType)} instead.
     */
    public boolean hasItem(int itemId, boolean identified) {
        return getItemAmount(itemId, identified) > 0;
    }

    /**
     * @deprecated Use {@link InventoryManipulator#hasItem(UserEntity, ItemDefinition, IdentificationType)}.
     */
    public boolean hasItem(ItemDefinition item, IdentificationType identified) {
        return getItemAmount(item, identified) > 0;
    }

    public boolean hasItemAmount(ItemDefinition item, int amount) {
        return hasItemAmount(item.getId(), amount, IdentificationType.IDENTIFIED);
    }

    public boolean hasItemAmount(ItemDefinition item, int amount, IdentificationType identified) {
        return hasItemAmount(item.getId(), amount, identified);
    }

    private boolean hasItemAmount(int itemId, int amount, IdentificationType identified) {
        return getItemAmount(itemId, identified) >= amount;
    }

    /**
     * @deprecated Use {@link #hasItemAmount(ItemDefinition, int, IdentificationType)} instead.
     */
    public boolean hasItemAmount(int itemId, int amount, boolean identified) {
        return hasItemAmount(itemId, amount, identified ? IdentificationType.IDENTIFIED : IdentificationType.UNIDENTIFIED);
    }

    /**
     * @deprecated Use {@link #getItemAmount(ItemDefinition)} instead.
     */
    public int getItemAmount(int itemId) {
        return getItemAmount(itemId, IdentificationType.IDENTIFIED);
    }

    public int getItemAmount(ItemDefinition item) {
        return getItemAmount(item, IdentificationType.IDENTIFIED);
    }

    /**
     * @deprecated Use {@link #getItemAmount(int, IdentificationType)} instead.
     */
    public int getItemAmount(int itemId, boolean identified) {
        return getItemAmount(itemDefinitionCache.getDefinition(itemId), identified ? IdentificationType.IDENTIFIED : IdentificationType.UNIDENTIFIED);
    }

    /**
     * @deprecated Use {@link #getItemAmount(ItemDefinition, IdentificationType)}.
     */
    public int getItemAmount(int itemId, IdentificationType identified) {
        return getItemAmount(itemDefinitionCache.getDefinition(itemId), identified);
    }

    /**
     * @deprecated Use {@link InventoryManipulator#getItemAmount(UserEntity, ItemDefinition, IdentificationType)}
     * instead.
     */
    public int getItemAmount(ItemDefinition item, IdentificationType identified) {
        final ItemDatabaseEntity dbEntity = inventoryMapper.getItem(userEntity.getId(), item.getId(), identified.getId());

        if (dbEntity != null) {
            return dbEntity.getAmount();
        }

        return 0;
    }

    public void addItem(ItemDefinition item, int amount) {
        addItem(item, amount, IdentificationType.IDENTIFIED);
    }

    public void addItem(ItemDefinition item, int amount, IdentificationType identified) {
        if (identified == IdentificationType.IDENTIFIED) {
            journalManager.createJournalEntry(userEntity, JournalType.ITEM, item.getId());
        }

        inventoryMapper.addItem(userEntity.getId(), item.getId(), amount, identified.getId());
    }

    /**
     * @deprecated Use {@link #addItem(ItemDefinition, int, IdentificationType)} instead.
     */
    public void addItem(ItemDefinition item, int amount, boolean identified) {
        addItem(item.getId(), amount, identified);
    }

    /**
     * @deprecated Use {@link #addItem(ItemDefinition, int)} instead.
     */
    public void addItem(int itemId, int amount) {
        addItem(itemDefinitionCache.getDefinition(itemId), amount, IdentificationType.IDENTIFIED);
    }

    /**
     * @deprecated Use {@link #addItem(ItemDefinition, int, IdentificationType)} instead.
     */
    public void addItem(int itemId, int amount, boolean identified) {
        if (identified) {
            addItem(itemDefinitionCache.getDefinition(itemId), amount, IdentificationType.IDENTIFIED);
        } else {
            addItem(itemDefinitionCache.getDefinition(itemId), amount, IdentificationType.UNIDENTIFIED);
        }
    }

    /**
     * @deprecated Use {@link InventoryManipulator#removeItem(UserEntity, ItemDefinition, int)} instead.
     */
    public void removeItem(ItemDefinition item, int amount) {
        removeItem(item, amount, IdentificationType.IDENTIFIED);
    }

    /**
     * @deprecated Use {@link InventoryManipulator#removeItem(UserEntity, ItemDefinition, int, IdentificationType)}
     * instead.
     */
    public void removeItem(ItemDefinition item, int amount, IdentificationType identified) {
        inventoryManipulator.removeItem(userEntity, item, amount, identified);
    }

    /**
     * @deprecated use {@link #removeItem(ItemDefinition, int)} instead.
     */
    public void removeItem(int itemId, int amount) {
        removeItem(itemId, amount, true);
    }

    /**
     * @deprecated Use {@link #removeItem(ItemDefinition, int, IdentificationType)} instead.
     */
    public void removeItem(int itemId, int amount, boolean identified) {
        removeItem(itemDefinitionCache.getDefinition(itemId), amount, identified ? IdentificationType.IDENTIFIED : IdentificationType.UNIDENTIFIED);
    }

    public void setItem(ItemDefinition item, int amount, IdentificationType identified) {
        if (amount == 0) {
            inventoryMapper.deleteItem(userEntity.getId(), item.getId(), identified.getId());
        } else {
            inventoryMapper.setItem(userEntity.getId(), item.getId(), amount, identified.getId());
        }
    }

    public List<InventoryItem> getItems() {
        return Collections.unmodifiableList(
                inventoryMapper.getAllItems(userEntity.getId()).stream()
                        .map(this::buildInventoryItem)
                        .collect(Collectors.toList())
        );
    }

    public List<InventoryItem> getItems(final IdentificationType identified) {
        return Collections.unmodifiableList(
                inventoryMapper.getItems(userEntity.getId(), identified.getId()).stream()
                        .map(this::buildInventoryItem)
                        .collect(Collectors.toList())
        );
    }

    private InventoryItem buildInventoryItem(final ItemDatabaseEntity itemDatabaseEntity) {
        return InventoryItem.builder()
                .item(itemDefinitionCache.getDefinition(itemDatabaseEntity.getItemId()))
                .amount(itemDatabaseEntity.getAmount())
                .identified(itemDatabaseEntity.isIdentified() ? IdentificationType.IDENTIFIED : IdentificationType.UNIDENTIFIED)
                .build();
    }

    public int getMoneyAmount(final MoneyType moneyType) {
        final MoneyDefinition moneyDefinition = moneyFacade.getDefinition(moneyType);
        final MoneyCalculationQuery moneyCalculationQuery = buildMoneyCalculationQuery(moneyDefinition);

        return moneyFacade.getMoneyAmount(MoneyType.MONEY, moneyCalculationQuery);
    }

    public boolean hasMoneyAmount(final MoneyType moneyType, final int amount) {
        return getMoneyAmount(moneyType) >= amount;
    }

    public void decreaseMoneyAmount(final MoneyType moneyType, final int amount) {
        final MoneyDefinition moneyDefinition = moneyFacade.getDefinition(moneyType);
        final MoneyCalculationQuery moneyCalculationQuery = buildMoneyCalculationQuery(moneyDefinition);

        applyMoneyCalculationResult(moneyDefinition, moneyFacade.decreaseMoneyAmount(moneyType, moneyCalculationQuery, amount));
    }

    public void increaseMoneyAmount(final MoneyType moneyType, final int amount) {
        final MoneyDefinition moneyDefinition = moneyFacade.getDefinition(moneyType);
        final MoneyCalculationQuery moneyCalculationQuery = buildMoneyCalculationQuery(moneyDefinition);

        applyMoneyCalculationResult(moneyDefinition, moneyFacade.increaseMoneyAmount(moneyType, moneyCalculationQuery, amount));
    }

    private MoneyCalculationQuery buildMoneyCalculationQuery(MoneyDefinition moneyDefinition) {
        final MoneyCalculationQuery moneyCalculationQuery = new MoneyCalculationQuery();

        for (Conversion conversion : moneyDefinition.getConversions()) {
            moneyCalculationQuery.setCurrency(conversion.getTargetId(), getItemAmount(conversion.getTargetId()));
        }

        return moneyCalculationQuery;
    }

    private void applyMoneyCalculationResult(final MoneyDefinition moneyDefinition, final MoneyCalculationResult moneyCalculationResult) {
        for (Conversion conversion : moneyDefinition.getConversions()) {
            setItem(itemDefinitionCache.getDefinition(conversion.getTargetId()), moneyCalculationResult.getCurrency(conversion.getTargetId()), IdentificationType.IDENTIFIED);
        }
    }

    @Override
    public int getId() {
        return userEntity.getId();
    }
}
