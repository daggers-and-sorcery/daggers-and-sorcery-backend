package com.morethanheroic.swords.inventory.domain;

import com.morethanheroic.swords.inventory.repository.dao.ItemDatabaseEntity;
import com.morethanheroic.swords.inventory.repository.domain.InventoryMapper;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.journal.model.JournalType;
import com.morethanheroic.swords.journal.service.JournalManager;
import com.morethanheroic.swords.money.domain.ConversionDefinition;
import com.morethanheroic.swords.money.domain.MoneyType;
import com.morethanheroic.swords.money.domain.MoneyCalculationQuery;
import com.morethanheroic.swords.money.domain.MoneyCalculationResult;
import com.morethanheroic.swords.money.domain.MoneyDefinition;
import com.morethanheroic.swords.money.service.MoneyFacade;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.sun.istack.internal.NotNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

//TODO: instead of true or false use an enum like IdentificationType.IDENTIFIED
@RequiredArgsConstructor
public class InventoryEntity {

    @NotNull
    private final UserEntity userEntity;

    @NotNull
    private final InventoryMapper inventoryMapper;

    @NotNull
    private final JournalManager journalManager;

    @NotNull
    private final MoneyFacade moneyFacade;

    public boolean hasItem(int itemId) {
        return hasItem(itemId, true);
    }

    public boolean hasItem(int itemId, boolean identified) {
        return getItemAmount(itemId, identified) > 0;
    }

    public boolean hasItemAmount(int itemId, int amount) {
        return hasItemAmount(itemId, amount, true);
    }

    public boolean hasItemAmount(int itemId, int amount, boolean identified) {
        return getItemAmount(itemId, identified) > amount;
    }

    public int getItemAmount(int itemId) {
        return getItemAmount(itemId, true);
    }

    public int getItemAmount(int itemId, boolean identified) {
        final ItemDatabaseEntity dbEntity = inventoryMapper.getItem(userEntity.getId(), itemId, identified);

        if (dbEntity != null) {
            return dbEntity.getAmount();
        }

        return 0;
    }

    public void addItem(ItemDefinition item, int amount) {
        addItem(item.getId(), amount, true);
    }

    public void addItem(ItemDefinition item, int amount, boolean identified) {
        addItem(item.getId(), amount, identified);
    }

    public void addItem(int itemId, int amount) {
        addItem(itemId, amount, true);
    }

    public void addItem(int itemId, int amount, boolean identified) {
        if (identified) {
            journalManager.createJournalEntry(userEntity, JournalType.ITEM, itemId);
        }

        inventoryMapper.addItem(userEntity.getId(), itemId, amount, identified);
    }

    public void removeItem(ItemDefinition item, int amount) {
        removeItem(item.getId(), amount, true);
    }

    public void removeItem(ItemDefinition item, int amount, boolean identified) {
        removeItem(item.getId(), amount, identified);
    }

    public void removeItem(int itemId, int amount) {
        removeItem(itemId, amount, true);
    }

    public void removeItem(int itemId, int amount, boolean identified) {
        final int amountBeforeRemove = getItemAmount(itemId, identified);

        if (amountBeforeRemove - amount > 0) {
            inventoryMapper.removeItem(userEntity.getId(), itemId, amountBeforeRemove - amount, identified);
        } else {
            inventoryMapper.deleteItem(userEntity.getId(), itemId, identified);
        }
    }

    public void setItem(int itemId, int amount, boolean identified) {
        if (amount == 0) {
            inventoryMapper.deleteItem(userEntity.getId(), itemId, identified);
        } else {
            inventoryMapper.setItem(userEntity.getId(), itemId, amount, identified);
        }
    }

    public List<ItemDatabaseEntity> getItems() {
        return inventoryMapper.getAllItems(userEntity.getId());
    }

    public List<ItemDatabaseEntity> getItems(boolean identified) {
        return inventoryMapper.getItems(userEntity.getId(), identified);
    }

    public int getMoneyAmount(final MoneyType moneyType) {
        final MoneyDefinition moneyDefinition = moneyFacade.getDefinition(moneyType);
        final MoneyCalculationQuery moneyCalculationQuery = buildMoneyCalculationQuery(moneyDefinition);

        return moneyFacade.getMoneyAmount(MoneyType.MONEY, moneyCalculationQuery);
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

        for (ConversionDefinition conversionDefinition : moneyDefinition.getConversionDefinitions()) {
            moneyCalculationQuery.setCurrency(conversionDefinition.getTargetId(), getItemAmount(conversionDefinition.getTargetId()));
        }

        return moneyCalculationQuery;
    }

    private void applyMoneyCalculationResult(final MoneyDefinition moneyDefinition, final MoneyCalculationResult moneyCalculationResult) {
        for (ConversionDefinition conversionDefinition : moneyDefinition.getConversionDefinitions()) {
            setItem(conversionDefinition.getTargetId(), moneyCalculationResult.getCurrency(conversionDefinition.getTargetId()), true);
        }
    }
}
