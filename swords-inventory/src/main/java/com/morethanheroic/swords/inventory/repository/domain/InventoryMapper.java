package com.morethanheroic.swords.inventory.repository.domain;

import com.morethanheroic.swords.inventory.repository.dao.ItemDatabaseEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InventoryMapper {

    //UNSAFE! If we ever going to change to master-slave based mysql setup we should fix this!
    @Insert("INSERT INTO inventory SET user_id = #{user_id}, item_id = #{item_id}, amount = #{amount}, identified = #{identified} ON DUPLICATE KEY UPDATE amount = amount + #{amount}")
    void addItem(@Param("user_id") int userId, @Param("item_id") int itemId, @Param("amount") int amount, @Param("identified") int identified);

    @Insert("INSERT INTO inventory SET user_id = #{user_id}, item_id = #{item_id}, amount = #{amount}, identified = #{identified} ON DUPLICATE KEY UPDATE amount = #{amount}")
    void setItem(@Param("user_id") int userId, @Param("item_id") int itemId, @Param("amount") int amount, @Param("identified") int identified);

    @Select("SELECT * FROM inventory WHERE user_id = #{user_id} AND identified = #{identified}")
    List<ItemDatabaseEntity> getItems(@Param("user_id") int userId, @Param("identified") int identified);

    @Select("SELECT * FROM inventory WHERE user_id = #{user_id}")
    List<ItemDatabaseEntity> getAllItems(@Param("user_id") int userId);

    @Select("SELECT * FROM inventory WHERE user_id = #{user_id} AND item_id = #{item_id} AND identified = #{identified}")
    ItemDatabaseEntity getItem(@Param("user_id") int userId, @Param("item_id") int itemId, @Param("identified") int identified);

    @Update("UPDATE inventory SET amount = #{amount} WHERE user_id = #{user_id} AND item_id = #{item_id} AND identified = #{identified}")
    void removeItem(@Param("user_id") int userId, @Param("item_id") int itemId, @Param("amount") int amount, @Param("identified") int identified);

    @Delete("DELETE FROM inventory WHERE user_id = #{user_id} AND item_id = #{item_id} AND identified = #{identified}")
    void deleteItem(@Param("user_id") int userId, @Param("item_id") int itemId, @Param("identified") int identified);
}
