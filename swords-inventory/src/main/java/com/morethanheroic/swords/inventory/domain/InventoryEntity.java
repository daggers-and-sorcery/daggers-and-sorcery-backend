package com.morethanheroic.swords.inventory.domain;

import com.morethanheroic.entity.domain.Entity;
import com.morethanheroic.swords.inventory.repository.dao.ItemDatabaseEntity;
import com.morethanheroic.swords.inventory.repository.domain.InventoryMapper;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.journal.model.JournalType;
import com.morethanheroic.swords.journal.service.JournalManager;
import com.morethanheroic.swords.money.domain.Conversion;
import com.morethanheroic.swords.money.domain.MoneyCalculationQuery;
import com.morethanheroic.swords.money.domain.MoneyCalculationResult;
import com.morethanheroic.swords.money.domain.MoneyDefinition;
import com.morethanheroic.swords.money.domain.MoneyType;
import com.morethanheroic.swords.money.service.MoneyFacade;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import java.util.List;

@Configurable
@RequiredArgsConstructor
public class InventoryEntity implements Entity {

    @Autowired
    private InventoryMapper inventoryMapper;

    @Autowired
    private JournalManager journalManager;

    @Autowired
    private MoneyFacade moneyFacade;

    @Autowired
    private ItemDefinitionCache itemDefinitionCache;

    private final UserEntity userEntity;

    public boolean hasItem(ItemDefinition item) {
        return hasItem(item, IdentificationType.IDENTIFIED);
    }

    /**
     * @deprecated Use {@link #hasItem(ItemDefinition)} instead.
     */
    public boolean hasItem(int itemId) {
        return hasItem(itemId, true);
    }

    /**
     * @deprecated Use {@link #hasItem(ItemDefinition, IdentificationType)} instead.
     */
    public boolean hasItem(int itemId, boolean identified) {
        return getItemAmount(itemId, identified) > 0;
    }

    public boolean hasItem(ItemDefinition item, IdentificationType identified) {
        return getItemAmount(item, identified) > 0;
    }

    public boolean hasItemAmount(ItemDefinition item, int amount) {
        return hasItemAmount(item.getId(), amount, IdentificationType.IDENTIFIED);
    }

    /**
     * @deprecated Use {@link #hasItemAmount(int, int, IdentificationType)} instead.
     */
    public boolean hasItemAmount(ItemDefinition item, int amount, boolean identified) {
        return identified ? hasItemAmount(item, amount, IdentificationType.IDENTIFIED) : hasItemAmount(item, amount, IdentificationType.UNIDENTIFIED);
    }

    public boolean hasItemAmount(ItemDefinition item, int amount, IdentificationType identified) {
        return hasItemAmount(item.getId(), amount, identified);
    }

    private boolean hasItemAmount(int itemId, int amount, IdentificationType identified) {
        return getItemAmount(itemId, identified) >= amount;
    }

    /**
     * @deprecated Use {@link #hasItemAmount(ItemDefinition, int)}
     */
    public boolean hasItemAmount(int itemId, int amount) {
        return hasItemAmount(itemId, amount, IdentificationType.IDENTIFIED);
    }

    /**
     * @deprecated Use {@link #hasItemAmount(ItemDefinition, int, IdentificationType)} instead.
     */
    public boolean hasItemAmount(int itemId, int amount, boolean identified) {
        return identified ? hasItemAmount(itemId, amount, IdentificationType.IDENTIFIED) : hasItemAmount(itemId, amount, IdentificationType.UNIDENTIFIED);
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
        return identified ? getItemAmount(itemId, IdentificationType.IDENTIFIED) : getItemAmount(itemId, IdentificationType.UNIDENTIFIED);
    }

    /**
     * @deprecated Use {@link #getItemAmount(ItemDefinition, IdentificationType)}.
     */
    public int getItemAmount(int itemId, IdentificationType identified) {
        final ItemDatabaseEntity dbEntity = inventoryMapper.getItem(userEntity.getId(), itemId, identified.getId());

        if (dbEntity != null) {
            return dbEntity.getAmount();
        }

        return 0;
    }

    public int getItemAmount(ItemDefinition item, IdentificationType identified) {
        //TODO: Once #getItemAmount(int, IdentificationType) functionality is removed from everywhere, move the body of that method here.
        return getItemAmount(item.getId(), identified);
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

    public void removeItem(ItemDefinition item, int amount) {
        removeItem(item.getId(), amount, true);
    }

    /**
     * @deprecated Use {@link #removeItem(ItemDefinition, int, IdentificationType)} instead.
     */
    public void removeItem(ItemDefinition item, int amount, boolean identified) {
        removeItem(item, amount, identified ? IdentificationType.IDENTIFIED : IdentificationType.UNIDENTIFIED);
    }

    public void removeItem(ItemDefinition item, int amount, IdentificationType identified) {
        final int amountBeforeRemove = getItemAmount(item, identified);

        if (amountBeforeRemove - amount > 0) {
            inventoryMapper.removeItem(userEntity.getId(), item.getId(), amountBeforeRemove - amount, identified.getId());
        } else {
            inventoryMapper.deleteItem(userEntity.getId(), item.getId(), identified.getId());
        }
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
        removeItem(itemDefinitionCache.getDefinition(itemId), amount, identified ? IdentificationType.IDENTIFIED : IdentificationType.IDENTIFIED);
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

        for (Conversion conversion : moneyDefinition.getConversions()) {
            moneyCalculationQuery.setCurrency(conversion.getTargetId(), getItemAmount(conversion.getTargetId()));
        }

        return moneyCalculationQuery;
    }

    private void applyMoneyCalculationResult(final MoneyDefinition moneyDefinition, final MoneyCalculationResult moneyCalculationResult) {
        for (Conversion conversion : moneyDefinition.getConversions()) {
            setItem(conversion.getTargetId(), moneyCalculationResult.getCurrency(conversion.getTargetId()), true);
        }
    }

    @Override
    public int getId() {
        return userEntity.getId();
    }
}
